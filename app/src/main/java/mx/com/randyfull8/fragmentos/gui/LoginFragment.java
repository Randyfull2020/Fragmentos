package mx.com.randyfull8.fragmentos.gui;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.fragment.app.Fragment;

import com.google.android.material.button.MaterialButton;
import com.google.android.material.textfield.TextInputEditText;

import java.util.Objects;

import mx.com.randyfull8.fragmentos.R;
import mx.com.randyfull8.fragmentos.databinding.FragmentLoginBinding;
import mx.com.randyfull8.fragmentos.gui.components.NavigationHost;

public class LoginFragment extends Fragment {

    private FragmentLoginBinding binding;
    private View view;
    private Context context;
    private MaterialButton cancel;
    private TextInputEditText txtUser;
    private TextInputEditText txtPassword;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        configGlobal();
        configView(inflater, container);
        configListeners();

        return view;
    }

    private void configListeners() {

        Objects.requireNonNull(binding.cancel).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                System.exit(0);

            }
        });

        binding.okok.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                txtUser = view.findViewById(R.id.username_edit_text);
                txtPassword = view.findViewById(R.id.password_edit_text);
                if (txtUser.getText().toString().equals("") && txtPassword.getText().toString().equals("")){
                    ((NavigationHost)MainActivity.GLOBALS.get("app")).navigateTo(new TopJuegos(),false);
                }else{
                    Toast.makeText(getActivity(), "Solo tienes que dar en iniciar sesión", Toast.LENGTH_SHORT).show();
                    txtUser.setText("");
                    txtUser.setHint("Solo da en iniciar sesión");
                    txtPassword.setText("");
                    txtPassword.setHint("Password");

                }
            }
        });


    }

    private void configGlobal() {
        MainActivity.GLOBALS.put("loginFragment",this);
    }

    private void configView(LayoutInflater inflater, ViewGroup container) {
        binding = FragmentLoginBinding.inflate(inflater, container, false);
        view = binding.getRoot();
        context = container.getContext();
    }
}

