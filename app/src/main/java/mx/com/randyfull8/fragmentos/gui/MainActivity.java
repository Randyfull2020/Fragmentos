package mx.com.randyfull8.fragmentos.gui;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentTransaction;
import android.os.Bundle;
import android.view.View;
import java.util.HashMap;
import mx.com.randyfull8.fragmentos.R;
import mx.com.randyfull8.fragmentos.core.FragmentosApplication;
import mx.com.randyfull8.fragmentos.gui.components.NavigationHost;

public class MainActivity extends AppCompatActivity implements NavigationHost{

    public static HashMap<String, Object> GLOBALS= new HashMap<>();
//hola
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        configContext();
        configGlobals();
        configFragmentsManager(savedInstanceState);

    }
    private void configContext() {
        FragmentosApplication.setAppContext(getApplicationContext());
    }

    private void configGlobals() {
      GLOBALS.put("app",this);
    }

    private void configFragmentsManager(Bundle savedInstanceState) {
     if (savedInstanceState==null){

         getSupportFragmentManager()
                 .beginTransaction()
                 .add(R.id.contentPanel,new TopJuegos())
                 .commit();
     }
    }
    public void nuevoranked(View view){
       getSupportFragmentManager()
                .beginTransaction()
                .add(R.id.contentPanel,new TopRanked())
                .commit();
    }

    public void nuevomisjuegos(View view){

        getSupportFragmentManager()
                .beginTransaction()
                .add(R.id.contentPanel,new MisJuegos())
                .commit();
    }

    public void nuevocategoria(View view){
        getSupportFragmentManager()
                .beginTransaction()
                .add(R.id.contentPanel,new Categorias())
                .commit();
    }

    public void nuevotop(View view){
        getSupportFragmentManager()
                .beginTransaction()
                .add(R.id.contentPanel,new TopJuegos())
                .commit();
    }
    public void nuevoold(View view){
        getSupportFragmentManager()
                .beginTransaction()
                .add(R.id.contentPanel,new ViejaEscuela())
                .commit();
    }
    public void nuevoadministrars(View view){

        getSupportFragmentManager()
                .beginTransaction()
                .add(R.id.contentPanel,new MisJuegos())
                .commit();
    }
    public void nuevofree(View view){
        getSupportFragmentManager()
                .beginTransaction()
                .add(R.id.contentPanel,new FreeToPlay())
                .commit();
    }
    @Override
    public void navigateTo(androidx.fragment.app.Fragment fragment, boolean addToBackStack) {
        FragmentTransaction transaction =
                getSupportFragmentManager()
                        .beginTransaction()
                        .replace(R.id.contentPanel, fragment);
        if(addToBackStack)
            transaction.addToBackStack(null);
        transaction.commit();
    }
}