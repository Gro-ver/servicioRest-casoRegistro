package com.tecsup.elazaro.elazaro4.activities;

import android.content.Intent;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.CardView;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import com.tecsup.elazaro.elazaro4.R;
import com.tecsup.elazaro.elazaro4.models.Registro;
import com.tecsup.elazaro.elazaro4.services.RegloginService;
import com.tecsup.elazaro.elazaro4.services.RegloginServiceGenerator;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity {

    private static final String TAG = "Pruebas";
    private SharedPreferences sharedPreferences;
    private EditText usernameInput, passwordInput;
    private CardView btnLogin , btnRegistro;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btnLogin = findViewById(R.id.card_view_login);
        btnRegistro =findViewById(R.id.cardView_registro);

        //referenciando desde xml a codigo
        usernameInput = findViewById(R.id.username_input);
        passwordInput = findViewById(R.id.password_input);

        //creando un evento on Click
        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                String username = usernameInput.getText().toString();
                String password = passwordInput.getText().toString();

                RegloginService service = RegloginServiceGenerator.createService(RegloginService.class);
                Call<List<Registro>> call = null;
                call = service.getRegistros();

                call.enqueue(new Callback<List<Registro>>() {
                    @Override
                    public void onResponse(Call<List<Registro>> call, Response<List<Registro>> response) {
                        try{
                            if(response.isSuccessful()){

                                Toast.makeText(MainActivity.this, response.body().get(i), Toast.LENGTH_LONG).show();


                            } else {

                            }
                        }catch (Throwable t){

                        }
                    }

                    @Override
                    public void onFailure(Call<List<Registro>> call, Throwable t) {

                    }
                });

            }
        });

        //creando un evento on Click
        btnRegistro.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                startActivity(new Intent(MainActivity.this, RegisterActivity.class));
                finish();
                //Intent registro=new Intent(MainActivity.this,RegisterActivity.class);
                //startActivity(registro);

            }
        });



    }

}


