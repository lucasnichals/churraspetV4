package c.churraspetv3;

import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class HomeActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        String username = "u";
        String password = "p";
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
                if (enteredUsername.equals(username) && enteredPassword.equals(password)) {
                    Toast.makeText(HomeActivity.this, "Bem-vindo", Toast.LENGTH_SHORT).show();
                    Intent intent = new Intent(HomeActivity.this, MainActivity.class);
                    startActivity(intent);
                } else {
                    Toast.makeText(HomeActivity.this, "Usuario ou senha INVALIDOS", Toast.LENGTH_LONG).show();
                }
            }
        });
    }
}