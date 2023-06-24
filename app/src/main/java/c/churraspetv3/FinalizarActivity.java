package c.churraspetv3;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

public class FinalizarActivity extends AppCompatActivity {
    private TextView textValorTotal;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_finalizar);
        getSupportActionBar().hide();
        String valorTotal = getIntent().getStringExtra("valorTotal");
        textValorTotal = findViewById(R.id.textValorTotal);
        Spinner spinnerPagamento = findViewById(R.id.spinPgto);
        TextView editPgto = findViewById(R.id.editPgto);
        Button bFinalizar = findViewById(R.id.bFinalizar);
        Button bVoltar = findViewById(R.id.bVoltar);

        textValorTotal.setText("Valor total: R$ " + valorTotal);
        editPgto.setFocusable(false);
        editPgto.setClickable(false);

        List<String> opcoesPagamento = new ArrayList<>();
        opcoesPagamento.add("PIX");
        opcoesPagamento.add("Cartão de crédito");
        opcoesPagamento.add("Boleto bancário");
        opcoesPagamento.add("Dinheiro");
        ArrayAdapter<String> adapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, opcoesPagamento);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinnerPagamento.setAdapter(adapter);

        bFinalizar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(FinalizarActivity.this, AgradecimentoActivity.class);
                startActivity(intent);
            }
        });

        bVoltar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(FinalizarActivity.this, CarrinhoActivity.class);
                startActivity(intent);
            }
        });
    }
}