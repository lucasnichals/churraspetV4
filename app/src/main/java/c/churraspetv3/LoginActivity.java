package c.churraspetv3;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class LoginActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        getSupportActionBar().hide();
        AcessoBD acessoBD;
        TextView tvRegister = findViewById(R.id.tv_main_cadastro);
        EditText etUsername = findViewById(R.id.et_main_username);
        EditText etPassword = findViewById(R.id.et_main_password);
        Button btnLogin = findViewById(R.id.btn_main_login);
        tvRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(LoginActivity.this, RegisterActivity.class);
                startActivity(intent);
            }
        });
        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String enteredUsername = etUsername.getText().toString();
                String enteredPassword = etPassword.getText().toString();
                AcessoBD acessoBD = new AcessoBD(LoginActivity.this);

                if (enteredUsername.isEmpty() || enteredPassword.isEmpty()) {
                    Toast.makeText(LoginActivity.this, "Digite um usuário e senha", Toast.LENGTH_LONG).show();
                } else if (acessoBD.verificarCredenciais(enteredUsername, enteredPassword)) {
                    Toast.makeText(LoginActivity.this, "Bem-vindo", Toast.LENGTH_SHORT).show();
                    Intent intent = new Intent(LoginActivity.this, MenuActivity.class);
                    startActivity(intent);
                } else {
                    Toast.makeText(LoginActivity.this, "Usuário ou senha incorreto ou inexistente", Toast.LENGTH_LONG).show();
                }
            }
        });
    }
}