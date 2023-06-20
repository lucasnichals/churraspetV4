package c.churraspetv3;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class RegisterActivity extends AppCompatActivity {
    Button btn_cadastro_registrar;
    EditText et_cadastro_username;
    EditText et_cadastro_password;
    AcessoBD acessoBD;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        btn_cadastro_registrar = findViewById(R.id.btn_cadastro_registrar);
        et_cadastro_username = findViewById(R.id.et_cadastro_username);
        et_cadastro_password = findViewById(R.id.et_cadastro_password);
        acessoBD = new AcessoBD(RegisterActivity.this);
        btn_cadastro_registrar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Usuario usuario = null;
                try {
                    usuario = new Usuario(-1,
                            et_cadastro_username.getText().toString(),
                            et_cadastro_password.getText().toString());
                    boolean sucesso = acessoBD.adicionarUsuario(usuario);
                    Toast.makeText(RegisterActivity.this, "Cadastrado com sucesso: " + sucesso, Toast.LENGTH_SHORT).show();
                } catch (Exception e) {
                    Toast.makeText(RegisterActivity.this, "Erro na criação do usuário!", Toast.LENGTH_LONG).show();
                    usuario = new Usuario(-1, "error", "error");
                }
            }
        });
    }
}