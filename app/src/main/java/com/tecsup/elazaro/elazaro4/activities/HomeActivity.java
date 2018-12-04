package com.tecsup.elazaro.elazaro4.activities;

import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;
import android.widget.Toast;

import com.tecsup.elazaro.elazaro4.R;

public class HomeActivity extends AppCompatActivity {

    private TextView nameText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        nameText = findViewById(R.id.name_text);

        SharedPreferences preferences = getApplicationContext().getSharedPreferences("preferencias", MODE_PRIVATE);
        String name = preferences.getString("name", "");

        nameText.setText("Bienvenido "+name);

    }
}
