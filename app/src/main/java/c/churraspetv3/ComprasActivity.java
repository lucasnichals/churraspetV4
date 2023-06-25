package c.churraspetv3;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.util.List;

public class ComprasActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getSupportActionBar().hide();
        setContentView(R.layout.activity_compras);
        ListView lv_compras = findViewById(R.id.lv_compras);
        AcessoBD acessoBD = new AcessoBD(this);
        List<String> compras = acessoBD.mostraCompras();
        ArrayAdapter<String> adapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, compras);
        lv_compras.setAdapter(adapter);
    }
}