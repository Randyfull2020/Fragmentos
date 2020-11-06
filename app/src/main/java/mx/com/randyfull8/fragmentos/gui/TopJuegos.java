package mx.com.randyfull8.fragmentos.gui;

import android.content.Context;
import android.os.Build;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.AccelerateDecelerateInterpolator;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

import mx.com.randyfull8.fragmentos.R;
import mx.com.randyfull8.fragmentos.databinding.FragmentTopJuegosBinding;
import mx.com.randyfull8.fragmentos.gui.components.JuegosAdapter;
import mx.com.randyfull8.fragmentos.gui.components.NavigationIconClickListener;
import mx.com.randyfull8.fragmentos.model.Juego;

public class TopJuegos extends Fragment {

    private FragmentTopJuegosBinding binding;

    private View view;
    private Context context;
    private List<Juego> juegos = new ArrayList<>();
    private static final String PATH_TOP="topJuegos";

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setHasOptionsMenu(true);
    }
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,Bundle savedInstanceState) {
        configGlobals();
        configView(inflater,container);
        configToolbar();
        configUI();
        configRecycler();
        return view;
    }
    private void configGlobals() {
        MainActivity.GLOBALS.put("TopJuegosFragment",this);
    }
    private void configToolbar() {
        AppCompatActivity activity = (AppCompatActivity) getActivity();
        if (activity != null) {
            activity.setSupportActionBar(binding.appBar);
        }
        binding.appBar.setNavigationOnClickListener(new NavigationIconClickListener(
                context,
                view.findViewById(R.id.gridTopGames),
                new AccelerateDecelerateInterpolator(),
                context.getDrawable(R.drawable.menu),
                context.getDrawable(R.drawable.menu_open)
        ));
    }
    private void configUI() {
        if (Build.VERSION.SDK_INT>=Build.VERSION_CODES.M){
            view.findViewById(R.id.gridTopGames).setBackground(getContext().getDrawable(R.drawable.product_grid_background_shape));
        }
    }
    private void configRecycler() {
        juegos.add(new Juego("1","halo", "master chief", 5,"halo"));
        juegos.add(new Juego("2","cof", "free fire de fresas", 2,"cof"));
        juegos.add(new Juego("3","mario kart", "clasicoco", 5,"mariokart"));
        juegos.add(new Juego("4","maincra", "sin comentarios", 1,"maincra"));
        juegos.add(new Juego("5","dest 2", "el nuevo halo", 5,"dest2"));

        binding.rclvTopJuegos.setHasFixedSize(true);
        LinearLayoutManager layoutManager = new LinearLayoutManager(context, RecyclerView.HORIZONTAL, false);
        binding.rclvTopJuegos.setLayoutManager(layoutManager);
        binding.rclvTopJuegos.setAdapter(new JuegosAdapter(juegos));



       /* FirebaseDatabase database=FirebaseDatabase.getInstance();
        DatabaseReference reference = database.getReference(PATH_TOP);
        reference.addChildEventListener(new ChildEventListener() {
            @Override
            public void onChildAdded(@NonNull DataSnapshot snapshot, @Nullable String previousChildName) {
                Juego juego = snapshot.getValue(Juego.class);

                if (!juegos.add(juego)) {
                    juegos.add(juego);
                }

                binding.rclvTopJuegos.getAdapter().notifyDataSetChanged();

            }

            @Override
            public void onChildChanged(@NonNull DataSnapshot snapshot, @Nullable String previousChildName) {

                Juego juego = snapshot.getValue(Juego.class);
                if (juego!=null){

                    Log.i("juego","onChildChanced"+juego.getIdJuego());
                }

                juegos.set(juegos.indexOf(juego),juego);
                binding.rclvTopJuegos.getAdapter().notifyDataSetChanged();


            }

            @Override
            public void onChildRemoved(@NonNull DataSnapshot snapshot) {

            }

            @Override
            public void onChildMoved(@NonNull DataSnapshot snapshot, @Nullable String previousChildName) {

            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });

        */

    }
    private void configView(LayoutInflater inflater,ViewGroup container) {
        binding=FragmentTopJuegosBinding.inflate(inflater,container,false);
        view=binding.getRoot();
        context=container.getContext();
    }
}