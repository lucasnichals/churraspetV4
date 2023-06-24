package c.churraspetv3;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class MenuActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getSupportActionBar().hide();
        setContentView(R.layout.activity_menu);
        TextView menuTittle = findViewById(R.id.tv_menu_tittle);
        Button btnAcessarPerfil = findViewById(R.id.btn_menu_acessar_perfil);
        Button btnAcessarLoja = findViewById(R.id.btn_menu_acessarloja);
        Button btnSair = findViewById(R.id.btn_menu_sair);

        btnAcessarPerfil.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MenuActivity.this, UserActivity.class);
                startActivity(intent);
            }
        });

        btnAcessarLoja.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MenuActivity.this, ShopActivity.class);
                startActivity(intent);
            }
        });

        btnSair.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }
}
