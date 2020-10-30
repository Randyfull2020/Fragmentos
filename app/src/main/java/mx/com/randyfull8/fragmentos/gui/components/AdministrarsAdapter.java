package mx.com.randyfull8.fragmentos.gui.components;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.appcompat.widget.AppCompatImageView;
import androidx.appcompat.widget.AppCompatRatingBar;
import androidx.recyclerview.widget.RecyclerView;
import java.util.List;
import mx.com.randyfull8.fragmentos.R;
import mx.com.randyfull8.fragmentos.gui.Administrars;
import mx.com.randyfull8.fragmentos.model.MisJuego;

public class AdministrarsAdapter extends RecyclerView.Adapter<AdministrarsAdapter.ViewHolder> {
    private List<MisJuego> misJuegos;
    private Context context;
    public AdministrarsAdapter(List<MisJuego> misJuegos) {
        this.misJuegos = misJuegos;
    }
    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.row_administrars,parent,false);
        context = parent.getContext();
        return new ViewHolder(view);
//
        //
    }
    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {

        MisJuego misJuego = misJuegos.get(position);
        String imgUri = "@drawable/"+ misJuego.getImagen();
        int imgResource = context.getResources().getIdentifier(imgUri,null,context.getPackageName());
        holder.imgMisjuegos.setImageResource(imgResource);
        holder.txtTitulo.setText(misJuego.getTitulo());
        holder.rbClasificacion.setRating(misJuego.getClasificacion());
        holder.txtDescripcion.setText(misJuego.getDescripcion());
    }
    @Override
    public int getItemCount() {
        return misJuegos.size();
    }
    public class ViewHolder extends RecyclerView.ViewHolder {
        private View view;
        private AppCompatImageView imgMisjuegos;
        private TextView txtTitulo;
        private AppCompatRatingBar rbClasificacion;
        private  TextView txtDescripcion;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            imgMisjuegos= itemView.findViewById(R.id.gamev);
            txtTitulo = itemView.findViewById(R.id.txtTitulo);
            rbClasificacion = itemView.findViewById(R.id.rbClasificacion);
            txtDescripcion = itemView.findViewById(R.id.txtDescripcion);
            this.view= itemView;
        }
    }
}
