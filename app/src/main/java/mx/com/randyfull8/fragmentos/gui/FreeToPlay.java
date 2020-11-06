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
import mx.com.randyfull8.fragmentos.databinding.FragmentFreeToPlayBinding;
import mx.com.randyfull8.fragmentos.gui.components.JuegosAdapter;
import mx.com.randyfull8.fragmentos.gui.components.NavigationIconClickListener;
import mx.com.randyfull8.fragmentos.model.Juego;

public class FreeToPlay extends Fragment {

private FragmentFreeToPlayBinding binding;

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
        MainActivity.GLOBALS.put("FreeToPlayFragment",this);
    }


    private void configToolbar() {
        AppCompatActivity activity = (AppCompatActivity) getActivity();
        if (activity != null) {
            activity.setSupportActionBar(binding.appBar);
        }
        binding.appBar.setNavigationOnClickListener(new NavigationIconClickListener(
                context,
                view.findViewById(R.id.gridTopGames), new AccelerateDecelerateInterpolator(), context.getDrawable(R.drawable.menu), context.getDrawable(R.drawable.menu_open)));
    }

    private void configUI() {
        if (Build.VERSION.SDK_INT>=Build.VERSION_CODES.M){
            view.findViewById(R.id.gridTopGames).setBackground(getContext().getDrawable(R.drawable.product_grid_background_shape));
        }
    }

    private void configRecycler() {
        juegos.add(new Juego("1","angrybirds", "Angry Birds", 1,"El mejor juego para dispositivo movil de hace años"));
        juegos.add(new Juego("2","among", "Among Us", 2,"Quien fue?"));
        juegos.add(new Juego("3","dreamleague", "Dream League 2020", 3,"Fifa de los celulares"));
        juegos.add(new Juego("4","freefire", "Free Fire", 1,"Call Of Duty de los pobres"));
        juegos.add(new Juego("5","clash", "Clash Royale", 5,"Pay To Win"));
binding.rclvFreeToPlay.setHasFixedSize(true);

        LinearLayoutManager layoutManager = new LinearLayoutManager(context, RecyclerView.HORIZONTAL, false);
        binding.rclvFreeToPlay.setLayoutManager(layoutManager);
        binding.rclvFreeToPlay.setAdapter(new JuegosAdapter(juegos));
    }


    private void configView(LayoutInflater inflater,ViewGroup container) {
        binding=FragmentFreeToPlayBinding.inflate(inflater,container,false);
        view=binding.getRoot();
        context=container.getContext();

    }


}