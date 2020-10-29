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
import mx.com.randyfull8.fragmentos.databinding.FragmentViejaEscuelaBinding;
import mx.com.randyfull8.fragmentos.gui.components.JuegosAdapter;
import mx.com.randyfull8.fragmentos.gui.components.NavigationIconClickListener;
import mx.com.randyfull8.fragmentos.model.Juego;

public class ViejaEscuela extends Fragment {

private FragmentViejaEscuelaBinding binding;
    private View view;
    private Context context;
private List<Juego> juegos = new ArrayList<>();

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
        MainActivity.GLOBALS.put("ViejaEscuelaFragment",this);
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
        juegos.add(new Juego(1,"mario", "Super Mario Bros", 5,"La infancia de todos"));
        juegos.add(new Juego(2,"kof", "The King Of Fighters", 4,"El peso de las tortillas"));
        juegos.add(new Juego(3,"street", "Street Fighter", 3,"La competencia de KOF"));
        juegos.add(new Juego(4,"pacman", "Pac-Man", 2,"Uno de los primeros hits"));
        juegos.add(new Juego(5,"galaga", "Galaga", 1,"casi casi Star Wars"));
        binding.rclvViejaEscuela.setHasFixedSize(true);
        LinearLayoutManager layoutManager = new LinearLayoutManager(context, RecyclerView.HORIZONTAL, false);
        binding.rclvViejaEscuela.setLayoutManager(layoutManager);
        binding.rclvViejaEscuela.setAdapter(new JuegosAdapter(juegos));
    }
    private void configView(LayoutInflater inflater,ViewGroup container) {
        binding=FragmentViejaEscuelaBinding.inflate(inflater,container,false);
        view=binding.getRoot();
        context=container.getContext();
    }
}