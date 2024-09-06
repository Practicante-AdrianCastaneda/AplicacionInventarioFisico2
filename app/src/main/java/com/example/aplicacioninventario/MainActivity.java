package com.example.aplicacioninventario;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.view.animation.AlphaAnimation;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;
import com.example.aplicacioninventario.R;

public class MainActivity extends AppCompatActivity {

    private EditText ingresarUsuario, ingresarPassword;
    private Button btnIngresar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ingresarUsuario = findViewById(R.id.ingresarUsuario);
        ingresarPassword = findViewById(R.id.Ingresarpassword);
        btnIngresar = findViewById(R.id.ingresar);

        // Inicialmente ocultar el botón
        btnIngresar.setVisibility(View.GONE);

        // TextWatcher para verificar cambios en los campos de texto
        TextWatcher textWatcher = new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {}

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                validarCampos();
            }

            @Override
            public void afterTextChanged(Editable s) {}
        };

        ingresarUsuario.addTextChangedListener(textWatcher);
        ingresarPassword.addTextChangedListener(textWatcher);

        // Acción del botón Ingresar
        btnIngresar.setOnClickListener(view -> {
            // Simulación de comprobación de la contraseña
            String password = ingresarPassword.getText().toString();
            if (password.equals("12345")) { // Aquí puedes verificar con la lógica real
                Toast.makeText(MainActivity.this, "Login Correcto", Toast.LENGTH_SHORT).show();
            } else {
                Toast.makeText(MainActivity.this, "Contraseña Incorrecta", Toast.LENGTH_SHORT).show();
                ocultarBotonIngresar();
            }
        });
    }

    // Verifica si los campos están llenos y muestra o esconde el botón
    private void validarCampos() {
        String usuario = ingresarUsuario.getText().toString().trim();
        String password = ingresarPassword.getText().toString().trim();

        if (!usuario.isEmpty() && !password.isEmpty()) {
            mostrarBotonIngresar();
        } else {
            ocultarBotonIngresar();
        }
    }

    // Método para mostrar el botón con animación
    private void mostrarBotonIngresar() {
        if (btnIngresar.getVisibility() == View.GONE) {
            btnIngresar.setVisibility(View.VISIBLE);
            AlphaAnimation fadeIn = new AlphaAnimation(0.0f, 1.0f);
            fadeIn.setDuration(500);
            btnIngresar.startAnimation(fadeIn);
        }
    }

    // Método para ocultar el botón con animación
    private void ocultarBotonIngresar() {
        if (btnIngresar.getVisibility() == View.VISIBLE) {
            AlphaAnimation fadeOut = new AlphaAnimation(1.0f, 0.0f);
            fadeOut.setDuration(500);
            btnIngresar.startAnimation(fadeOut);
            btnIngresar.setVisibility(View.GONE);
        }
    }
}
