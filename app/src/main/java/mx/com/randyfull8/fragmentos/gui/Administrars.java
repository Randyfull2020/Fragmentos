package mx.com.randyfull8.fragmentos.gui;

import android.content.Context;
import android.os.Build;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.AccelerateDecelerateInterpolator;

import java.util.ArrayList;
import java.util.List;

import mx.com.randyfull8.fragmentos.R;
import mx.com.randyfull8.fragmentos.databinding.FragmentAdministrarsBinding;
import mx.com.randyfull8.fragmentos.databinding.FragmentTopJuegosBinding;
import mx.com.randyfull8.fragmentos.gui.components.JuegosAdapter;
import mx.com.randyfull8.fragmentos.gui.components.MisJuegosAdapter;
import mx.com.randyfull8.fragmentos.gui.components.NavigationHost;
import mx.com.randyfull8.fragmentos.gui.components.NavigationIconClickListener;
import mx.com.randyfull8.fragmentos.model.Categoria;
import mx.com.randyfull8.fragmentos.model.Juego;
import mx.com.randyfull8.fragmentos.model.MisJuego;

public class Administrars extends Fragment {

    private FragmentAdministrarsBinding binding;

    private View view;
    private Context context;
    private List<MisJuego> Misjuegos = new ArrayList<>();
    private LayoutInflater inflater;
    private ViewGroup container;
    private Bundle savedInstanceState;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setHasOptionsMenu(true);
//binding.


    }
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,Bundle savedInstanceState) {
        this.inflater = inflater;
        this.container = container;
        this.savedInstanceState = savedInstanceState;
        configGlobals();
        configView(inflater, container);
        configToolBar();
        configUI();
        configRecycler();

        binding.icMas1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ((NavigationHost)getActivity()).navigateTo(new AddFragment(), true);
            }
        });
        return view;
    }


    private void configGlobals() {
        MainActivity.GLOBALS.put("AdministrarFragment",this);
    }
    private void configToolbar() {
        AppCompatActivity activity = (AppCompatActivity) getActivity();
        if (activity != null) {
            activity.setSupportActionBar(binding.appBar);
        }
        binding.appBar.setNavigationOnClickListener(new NavigationIconClickListener(
                context,
                view.findViewById(R.id.gridAdministrars),
                new AccelerateDecelerateInterpolator(),
                context.getDrawable(R.drawable.menu),
                context.getDrawable(R.drawable.menu_open)
        ));
    }
    private void configUI() {
        if (Build.VERSION.SDK_INT>=Build.VERSION_CODES.M){
            view.findViewById(R.id.gridAdministrars).setBackground(getContext().getDrawable(R.drawable.product_grid_background_shape));
        }
    }
    private void configToolBar() {
        AppCompatActivity activity = (AppCompatActivity)getActivity();
        if (activity!=null){
            activity.setSupportActionBar(binding.appBar);
        }
        binding.appBar.setNavigationOnClickListener(new NavigationIconClickListener(
                context, view.findViewById(R.id.gridAdministrars),
                new AccelerateDecelerateInterpolator(),
                context.getDrawable(R.drawable.menu_open),
                context.getDrawable(R.drawable.menu)
        ));
    }

    private void configRecycler() {
        Misjuegos.add(new MisJuego(1,"halo", "Halo", 5,"Master chief es la onda!"));
        Misjuegos.add(new MisJuego(2,"cof", "Call of duty", 2,"Free Fire para fresas"));
        Misjuegos.add(new MisJuego(3,"mariokart", "Mario Kart ", 5,"Un clásico"));
        Misjuegos.add(new MisJuego(4,"maincra", "Maincra", 1,"Sin comentarios"));
        Misjuegos.add(new MisJuego(5,"dest2", "Destinity 2", 5,"El legado de Halo"));
        Misjuegos.add(new MisJuego(6,"angrybirds", "Angry Birds", 1,"El mejor juego para dispositivo movil de hace años"));
        Misjuegos.add(new MisJuego(7,"among", "Among Us", 2,"Quien fue?"));
        Misjuegos.add(new MisJuego(8,"dreamleague", "Dream League 2020", 3,"Fifa de los celulares"));
        Misjuegos.add(new MisJuego(9,"freefire", "Free Fire", 1,"Call Of Duty de los pobres"));
        Misjuegos.add(new MisJuego(10,"clash", "Clash Royale", 5,"Pay To Win"));
        Misjuegos.add(new MisJuego(11,"halo", "Halo", 5,"Master chief es la onda!"));
        Misjuegos.add(new MisJuego(12,"cof", "Call of duty", 2,"Free Fire para fresas"));
        Misjuegos.add(new MisJuego(13,"mariokart", "Mario Kart ", 5,"Un clásico"));
        Misjuegos.add(new MisJuego(14,"maincra", "Maincra", 1,"Sin comentarios"));
        Misjuegos.add(new MisJuego(15,"dest2", "Destinity 2", 5,"El legado de Halo"));
        Misjuegos.add(new MisJuego(16,"mario", "Super Mario Bros", 5,"La infancia de todos"));
        Misjuegos.add(new MisJuego(17,"kof", "The King Of Fighters", 4,"El peso de las tortillas"));
        Misjuegos.add(new MisJuego(18,"street", "Street Fighter", 3,"La competencia de KOF"));
        Misjuegos.add(new MisJuego(19,"pacman", "Pac-Man", 2,"Uno de los primeros hits"));
        Misjuegos.add(new MisJuego(20,"galaga", "Galaga", 1,"casi casi Star Wars"));

        binding.rclvAdministrars.setHasFixedSize(true);
        LinearLayoutManager layoutManager = new LinearLayoutManager(context, RecyclerView.VERTICAL, false);
        binding.rclvAdministrars.setLayoutManager(layoutManager);
        binding.rclvAdministrars.setAdapter(new MisJuegosAdapter(Misjuegos));
    }
    private void configView(LayoutInflater inflater,ViewGroup container) {
        binding=FragmentAdministrarsBinding.inflate(inflater,container,false);
        view=binding.getRoot();
        context=container.getContext();
    }
}