package c.churraspetv3;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class carrinho extends AppCompatActivity {

    private String produto1, produto2, produto3, produto4;
    private String valor1, valor2, valor3, valor4;
    private int qtde1 = 0, qtde2 = 0, qtde3 = 0, qtde4 = 0;
    private float total1 = 0, total2 = 0, total3 = 0, total4 = 0;

    AcessoBD acessoBD;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_carrinho);
        EditText editCarrinho = findViewById(R.id.editCarrinho);
        editCarrinho.setFocusable(false);
        editCarrinho.setClickable(false);
        TextView textP1 = findViewById(R.id.textP1);
        TextView textP2 = findViewById(R.id.textP2);
        TextView textP3 = findViewById(R.id.textP3);
        TextView textP4 = findViewById(R.id.textP4);
        Button bAdicionar1 = findViewById(R.id.bAdicionar1);
        Button bAdicionar2 = findViewById(R.id.bAdicionar2);
        Button bAdicionar3 = findViewById(R.id.bAdicionar3);
        Button bAdicionar4 = findViewById(R.id.bAdicionar4);
        Button bFinalizar = findViewById(R.id.bConfirmar);

        Bundle extras = getIntent().getExtras();

        if (extras != null) {

            produto1 = extras.getString("produto1");
            produto2 = extras.getString("produto2");
            produto3 = extras.getString("produto3");
            produto4 = extras.getString("produto4");

            valor1 = extras.getString("valor1");
            valor2 = extras.getString("valor2");
            valor3 = extras.getString("valor3");
            valor4 = extras.getString("valor4");

            textP1.setText(produto1 + " - " + " R$ " + valor1);
            textP2.setText(produto2 + " - " + " R$ " + valor2);
            textP3.setText(produto3 + " - " + " R$ " + valor3);
            textP4.setText(produto4 + " - " + " R$ " + valor4);
        }

        bAdicionar1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                qtde1++;
                total1 += Float.parseFloat(valor1);
                textP1.setText(produto1 + " " + qtde1 + " un " + " R$ " + total1);
            }
        });

        bAdicionar2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                qtde2++;
                total2 += Float.parseFloat(valor2);
                textP2.setText(produto2 + " " + qtde2 + " un " + " R$ " + total2);
            }
        });

        bAdicionar3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                qtde3++;
                total3 += Float.parseFloat(valor3);
                textP3.setText(produto3 + " " + qtde3 + " un " + " R$ " + total3);
            }
        });

        bAdicionar4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                qtde4++;
                total4 += Float.parseFloat(valor4);
                textP4.setText(produto4 + " " + qtde4 + " un " + " R$ " + total4);
            }
        });

        bFinalizar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                float valorTotal = total1 + total2 + total3 + total4;

                String nomeUsuario = AcessoBD.USUARIO_SENHA;
                String senhaUsuario = AcessoBD.USUARIO_SENHA;
                AcessoBD acessoBD = new AcessoBD(carrinho.this);
                int usuarioId = acessoBD.getIdUsuario(nomeUsuario, senhaUsuario);

                if (qtde1 > 0) {
                    acessoBD.registrarVenda(usuarioId, 1, qtde1);
                }
                if (qtde2 > 0) {
                    acessoBD.registrarVenda(usuarioId, 2, qtde2);
                }
                if (qtde3 > 0) {
                    acessoBD.registrarVenda(usuarioId, 3, qtde3);
                }
                if (qtde4 > 0) {
                    acessoBD.registrarVenda(usuarioId, 4, qtde4);
                }

                Intent intent = new Intent(carrinho.this, finalizar.class);
                intent.putExtra("valorTotal", String.valueOf(valorTotal));
                startActivity(intent);
            }
        });
    }
}