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
import mx.com.randyfull8.fragmentos.databinding.FragmentMisJuegosBinding;
import mx.com.randyfull8.fragmentos.gui.components.MisJuegosAdapter;
import mx.com.randyfull8.fragmentos.gui.components.NavigationIconClickListener;
import mx.com.randyfull8.fragmentos.model.MisJuego;

public class MisJuegos extends Fragment {

    private FragmentMisJuegosBinding binding;
    private View view;
    private Context context;
    private List<MisJuego> misJuegos = new ArrayList<>();

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
        MainActivity.GLOBALS.put("MisJuegosFragment",this);
    }
    private void configView(LayoutInflater inflater, ViewGroup container) {
        binding = FragmentMisJuegosBinding.inflate(inflater,container,false);
        view = binding.getRoot();
        context = container.getContext();
    }
    private void configToolbar() {
        AppCompatActivity activity = (AppCompatActivity)getActivity();
        if(activity!=null){
            activity.setSupportActionBar(binding.appBar);
        }
        binding.appBar.setNavigationOnClickListener(new NavigationIconClickListener(
                context,
                view.findViewById(R.id.gridMisJuegos),
                new AccelerateDecelerateInterpolator(),
                context.getDrawable(R.drawable.menu),
                context.getDrawable(R.drawable.menu_open)
        ));
    }

    private void configUI() {
        if(Build.VERSION.SDK_INT>=Build.VERSION_CODES.M){
            view.findViewById(R.id.gridMisJuegos).setBackground(getContext().getDrawable(R.drawable.product_grid_background_shape));
        }
    }
    private void configRecycler() {
        misJuegos.add(new MisJuego(1,"gta5","GTA V",5,"el sana de ahorita"));
        misJuegos.add(new MisJuego(2,"clash","Clash Royale",3,"Pay to win"));
        misJuegos.add(new MisJuego(3,"kof","The King Of Fighters",2,"Que se arme la reta"));
        misJuegos.add(new MisJuego(4,"naruto","Naruto Storm 4",1,"La neta del planeta"));
        misJuegos.add(new MisJuego(5,"cof","Call Of Duty",0,"Free Fire para los ricos"));
        binding.rclvMisJuegos.setHasFixedSize(true);
        LinearLayoutManager layoutManager = new LinearLayoutManager(context, RecyclerView.VERTICAL,false);
        binding.rclvMisJuegos.setLayoutManager(layoutManager);
        binding.rclvMisJuegos.setAdapter(new MisJuegosAdapter(misJuegos));
    }}

