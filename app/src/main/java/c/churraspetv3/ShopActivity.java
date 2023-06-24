package c.churraspetv3;
import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class ShopActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_shop);
        getSupportActionBar().hide();
        TextView editMenu = findViewById(R.id.editMenu);
        editMenu.setFocusable(false);
        editMenu.setClickable(false);
        EditText editP1 = findViewById(R.id.editP1);
        editP1.setFocusable(false);
        editP1.setClickable(false);
        EditText editP2 = findViewById(R.id.editP2);
        editP2.setFocusable(false);
        editP2.setClickable(false);
        EditText editP3 = findViewById(R.id.editP3);
        editP3.setFocusable(false);
        editP3.setClickable(false);
        EditText editP4 = findViewById(R.id.editP4);
        editP4.setFocusable(false);
        editP4.setClickable(false);
        TextView textV1 = findViewById(R.id.textV1);
        TextView textV2 = findViewById(R.id.textV2);
        TextView textV3 = findViewById(R.id.textV3);
        TextView textV4 = findViewById(R.id.textV4);
        Button bComprar = findViewById(R.id.bComprar);
        bComprar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(ShopActivity.this, CarrinhoActivity.class);
                intent.putExtra("produto1", editP1.getText().toString());
                intent.putExtra("produto2", editP2.getText().toString());
                intent.putExtra("produto3", editP3.getText().toString());
                intent.putExtra("produto4", editP4.getText().toString());
                intent.putExtra("valor1", textV1.getText().toString());
                intent.putExtra("valor2", textV2.getText().toString());
                intent.putExtra("valor3", textV3.getText().toString());
                intent.putExtra("valor4", textV4.getText().toString());
                startActivity(intent);
            }
        });
    }
}