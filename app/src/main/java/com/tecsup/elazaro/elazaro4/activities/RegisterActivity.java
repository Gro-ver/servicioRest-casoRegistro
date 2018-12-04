package com.tecsup.elazaro.elazaro4.activities;

import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import com.tecsup.elazaro.elazaro4.R;
import com.tecsup.elazaro.elazaro4.models.Registro;
import com.tecsup.elazaro.elazaro4.services.RegloginService;
import com.tecsup.elazaro.elazaro4.services.RegloginServiceGenerator;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class RegisterActivity extends AppCompatActivity {

    private EditText fullnameInput;
    private EditText emailInput;
    private EditText passwordInput;
    private Button btnRegistrar;
    private Spinner tipo;
    private String name, email, password, opc;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        fullnameInput = findViewById(R.id.fullname_input);
        emailInput = findViewById(R.id.email_input);
        passwordInput = findViewById(R.id.password_input);

        Spinner tipos = findViewById(R.id.tipo_spinner);
        String[] arreglo = {"Administrador", "Diseñador", "Jefe de coordinacion"};

        tipos.setAdapter(new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, arreglo));

        tipos.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {

            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int pos, long id)
            {
                opc = (String)adapterView.getItemAtPosition(pos);
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent)
            {    }
        });

        //identidi¿ficando al id del boton registrar de la vista
        btnRegistrar = findViewById(R.id.btn_registrar);
        //creando un evento al hacer clic en el boton de registro
         btnRegistrar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                name = fullnameInput.getText().toString();
                email = emailInput.getText().toString();
                password = passwordInput.getText().toString();

                RegloginService service = RegloginServiceGenerator.createService(RegloginService.class);
                Call<Registro> call = null;
                call = service.Login(name, email, password, opc);

                call.enqueue(new Callback<Registro>() {
                    @Override
                    public void onResponse(Call<Registro> call, Response<Registro> response) {
                        try{
                            if (response.isSuccessful()) {

                                Toast.makeText(RegisterActivity.this, "Registro completo", Toast.LENGTH_SHORT).show();
                                //una vez registrado se envia a la actividad HomeActivity
                                SharedPreferences preferences = getApplicationContext().getSharedPreferences("preferencias", MODE_PRIVATE);

                                preferences.edit().putString("name", name).commit();

                                emailInput.setText("");
                                fullnameInput.setText("");
                                passwordInput.setText("");

                                startActivity(new Intent(RegisterActivity.this, HomeActivity.class));
                                finish();

                            } else {
                                Toast.makeText(RegisterActivity.this, "Error al registrar", Toast.LENGTH_SHORT).show();
                            }
                        }catch(Throwable t){

                        }
                    }

                    @Override
                    public void onFailure(Call<Registro> call, Throwable t) {
                        Toast.makeText(RegisterActivity.this, "Error al en la conexion", Toast.LENGTH_SHORT).show();
                    }
                });



            }
        });



    }

}
