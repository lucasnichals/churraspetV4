package c.churraspetv3;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.time.DayOfWeek;

public class HomeActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        getSupportActionBar().hide();
        AcessoBD acessoBD;
        TextView tvRegister = findViewById(R.id.tv_main_cadastro);
        EditText etUsername = findViewById(R.id.et_main_username);
        EditText etPassword = findViewById(R.id.et_main_password);
        Button btnLogin = findViewById(R.id.btn_main_login);
        tvRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(HomeActivity.this, RegisterActivity.class);
                startActivity(intent);
            }
        });
        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String enteredUsername = etUsername.getText().toString();
                String enteredPassword = etPassword.getText().toString();
                AcessoBD acessoBD = new AcessoBD(HomeActivity.this);

                if (enteredUsername.isEmpty() || enteredPassword.isEmpty()) {
                    Toast.makeText(HomeActivity.this, "Digite um usuário e senha", Toast.LENGTH_LONG).show();
                } else if (acessoBD.verificarCredenciais(enteredUsername, enteredPassword)) {
                    Toast.makeText(HomeActivity.this, "Bem-vindo", Toast.LENGTH_SHORT).show();
                    Intent intent = new Intent(HomeActivity.this, MainActivity.class);
                    startActivity(intent);
                } else {
                    Toast.makeText(HomeActivity.this, "Usuário ou senha inválidos", Toast.LENGTH_LONG).show();
                }
            }
        });
    }
}