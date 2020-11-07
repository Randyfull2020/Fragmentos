package mx.com.randyfull8.fragmentos.gui;

import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.widget.AppCompatImageView;
import androidx.appcompat.widget.AppCompatRatingBar;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.fragment.app.Fragment;
import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.bumptech.glide.request.RequestOptions;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.snackbar.BaseTransientBottomBar;
import com.google.android.material.snackbar.Snackbar;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;
import java.io.IOException;
import mx.com.randyfull8.fragmentos.R;
import mx.com.randyfull8.fragmentos.databinding.FragmentAddBinding;
import mx.com.randyfull8.fragmentos.gui.components.NavigationHost;
import mx.com.randyfull8.fragmentos.model.Juego;
import static android.app.Activity.RESULT_OK;

public class AddFragment extends Fragment {

    private AppCompatImageView imageView;
    private EditText edit1;
    private EditText edit2;
    private Button guardar;
    private Button agregar;
    private AppCompatRatingBar clasifi;
    private ImageButton btnDelete;
    private ConstraintLayout cL;
    private static final String MY_PHOTO = "my_photo";
    private static final String PATH_PROFILE = "topJuegos";
    private static final int RC_GALERY = 21;
    private static final String PATH_PHOTO_URL = "1";
    private TextView lblMessage;
    private FragmentAddBinding binding;
    private StorageReference storageReference;
    private DatabaseReference databaseReference;
    private DatabaseReference newJuego;
    private String currentPhotoPath;
    private Uri photoSelectedUri;
    private String my_photo="";
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setHasOptionsMenu(true);
    }
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_add, container, false);
        imageView = view.findViewById(R.id.imgJuego);
        edit1 = view.findViewById(R.id.txtTitulo);
        edit2 = view.findViewById(R.id.txtDescripcion);
        guardar = view.findViewById(R.id.btnGuardar);
        agregar = view.findViewById(R.id.btnCancelar);
        clasifi = view.findViewById(R.id.rbClasificacion);
        btnDelete = view.findViewById(R.id.addtt);
        cL = view.findViewById(R.id.container);
        imageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                fromGallery();
            }
        });
        guardar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                databaseReference = FirebaseDatabase.getInstance().getReference().child("topJuegos");
                storageReference = FirebaseStorage.getInstance().getReference();
                newJuego = databaseReference.push();
                Juego juego = new Juego();
                juego.setIdJuego(newJuego.getKey());
                juego.setClasificacion((int)clasifi.getRating());
                juego.setDescripcion(edit2.getText().toString());
                juego.setImagen("halo");
                juego.setTitulo(edit1.getText().toString());
                my_photo=newJuego.getKey();
                newJuego.setValue(juego, new DatabaseReference.CompletionListener() {
                    @Override
                    public void onComplete(@Nullable DatabaseError error, @NonNull DatabaseReference ref) {
                        UploadImage();
                    }
                });
                Toast.makeText(getActivity(), "Se ha agregado correctamente", Toast.LENGTH_SHORT).show();

                ((NavigationHost) getActivity()).navigateTo(new Administrar(), true);
            }

        });
        agregar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ((NavigationHost) getActivity()).navigateTo(new Administrar(), true);
            }
        });
        return view;
    }
    private void fromGallery() {
        Intent intent = new Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
        startActivityForResult(intent, RC_GALERY);
    }
    private void UploadImage() {
        if (photoSelectedUri == null) {
            Snackbar.make(cL, R.string.main_message_uri_null, BaseTransientBottomBar.LENGTH_LONG).show();
            return;
        }
        StorageReference profileReference = storageReference.child(PATH_PROFILE);
        StorageReference photoReference = profileReference.child(my_photo);
        StorageReference urlReference = photoReference.child("imagen");
        urlReference.putFile(photoSelectedUri).addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
            @Override
            public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {
                taskSnapshot.getMetadata().getReference().getDownloadUrl().addOnCompleteListener(new OnCompleteListener<Uri>() {
                    @Override
                    public void onComplete(@NonNull Task<Uri> task) {
                        savePhotoUrl(task.getResult());
                    }
                });

            }
        }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {
                Snackbar.make(cL, R.string.main_message_upload_error, BaseTransientBottomBar.LENGTH_LONG).show();
            }
        });
    }

    private void savePhotoUrl(Uri downloadUri) {
        newJuego.child("imagen").setValue(downloadUri.toString());
    }

    private void configPhotoProfile() {
        storageReference.child(PATH_PROFILE).child(MY_PHOTO).child("imagen").getDownloadUrl()
                .addOnSuccessListener(new OnSuccessListener<Uri>() {
                    @Override
                    public void onSuccess(Uri uri) {
                        RequestOptions options = new RequestOptions()
                                .diskCacheStrategy(DiskCacheStrategy.RESOURCE)
                                .centerCrop();

                        Glide.with(AddFragment.this)
                                .load(uri)
                                .placeholder(R.drawable.ic_placeholder)
                                .error(R.drawable.ic_error)
                                .into(binding.imgJuego);
                        btnDelete.setVisibility(View.VISIBLE);
                    }
                })
                .addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        btnDelete.setVisibility(View.GONE);
                        Snackbar.make(cL, R.string.main_message_error_notfound, BaseTransientBottomBar.LENGTH_LONG).show();
                    }
                });
    }
    @Override
    public void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == RESULT_OK) {
            switch (requestCode) {
                case RC_GALERY:
                    if (data != null) {
                        photoSelectedUri = data.getData();
                        try {
                            Bitmap bitmap = MediaStore.Images.Media.getBitmap(getContext().getContentResolver(), photoSelectedUri);
                            imageView.setImageBitmap(bitmap);

                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                    }
                    break;
            }
        }
    }
}
