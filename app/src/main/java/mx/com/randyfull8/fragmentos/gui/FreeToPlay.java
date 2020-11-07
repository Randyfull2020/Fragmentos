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
        juegos.add(new Juego("1","Angry birds", "Pajaros enojados", 1,"angrybirds"));
        juegos.add(new Juego("2","Among Us", "quien fue?", 2,"among"));
        juegos.add(new Juego("3","Dream League", "fifa de celulares", 3,"dreamleague"));
        juegos.add(new Juego("4","Free Fire", "call de pobres", 1,"freefire"));
        juegos.add(new Juego("5","Clash Royale", "Pay to win", 5,"clash"));
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