package c.churraspetv3;

import android.annotation.SuppressLint;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

import java.util.ArrayList;
import java.util.List;

public class AcessoBD extends SQLiteOpenHelper {
    protected static final String TABELA_USUARIO = "TABELA_USUARIO";
    protected static final String USUARIO_ID = "USUARIO_ID";
    protected static final String USUARIO_NOME = "USUARIO_NOME";
    protected static final String USUARIO_SENHA = "USUARIO_SENHA";
    protected static final String TABELA_PRODUTO = "TABELA_PRODUTO";
    protected static final String PRODUTO_ID = "PRODUTO_ID";
    protected static final String PRODUTO_NOME = "PRODUTO_NOME";
    protected static final String PRODUTO_DESCRICAO = "PRODUTO_DESCRICAO";
    protected static final String PRODUTO_QUANTIDADE = "PRODUTO_QUANTIDADE";
    protected static final String PRODUTO_PRECO = "PRODUTO_PRECO";
    protected static final String TABELA_VENDAS = "TABELA_VENDAS";
    protected static final String VENDA_ID = "VENDA_ID";

    public AcessoBD(@Nullable Context context) {
        super(context, "ChurrasPetBD", null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {

        String statementUsuario = "CREATE TABLE " + TABELA_USUARIO +
                " (" + USUARIO_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, "
                + USUARIO_NOME + " TEXT, " + USUARIO_SENHA + " TEXT)";
        db.execSQL(statementUsuario);

        String statementProduto = "CREATE TABLE " + TABELA_PRODUTO +
                " (" + PRODUTO_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, "
                + PRODUTO_NOME + " TEXT, " + PRODUTO_DESCRICAO + " TEXT, "
                + PRODUTO_QUANTIDADE + " INT, "
                + PRODUTO_PRECO + " FLOAT)";
        db.execSQL(statementProduto);

        String statementVendas = "CREATE TABLE " + TABELA_VENDAS +
                " (" + VENDA_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, "
                + USUARIO_ID + " INTEGER, "
                + PRODUTO_ID + " INTEGER, "
                + "FOREIGN KEY (" + USUARIO_ID + ") REFERENCES " + TABELA_USUARIO + "(" + USUARIO_ID + "), "
                + "FOREIGN KEY (" + PRODUTO_ID + ") REFERENCES " + TABELA_PRODUTO + "(" + PRODUTO_ID + "))";
        db.execSQL(statementVendas);

        String nomeUsuario = "admin";
        String senhaUsuatio = "admin";
        ContentValues contentValuesUser = new ContentValues();
        contentValuesUser.put(USUARIO_NOME, nomeUsuario);
        contentValuesUser.put(USUARIO_SENHA, senhaUsuatio);
        db.insert(TABELA_USUARIO, null, contentValuesUser);

        String racaoGatoCpet = "racaoGatoCpet";
        String descricaoProduto1 = "Deliciosa";
        int quantidadeProduto1 = 10;
        float precoProduto1 = (float) 25.00;
        ContentValues contentValues1 = new ContentValues();
        contentValues1.put(PRODUTO_NOME, racaoGatoCpet);
        contentValues1.put(PRODUTO_DESCRICAO, descricaoProduto1);
        contentValues1.put(PRODUTO_QUANTIDADE, quantidadeProduto1);
        contentValues1.put(PRODUTO_PRECO, precoProduto1);
        db.insert(TABELA_PRODUTO, null, contentValues1);

        String racaoCachorroCpet = "racaoCachorroCpet";
        String descricaoProduto2 = "Deliciosa";
        int quantidadeProduto2 = 15;
        float precoProduto2 = (float) 38.00;
        ContentValues contentValues2 = new ContentValues();
        contentValues2.put(PRODUTO_NOME, racaoCachorroCpet);
        contentValues2.put(PRODUTO_DESCRICAO, descricaoProduto2);
        contentValues2.put(PRODUTO_QUANTIDADE, quantidadeProduto2);
        contentValues2.put(PRODUTO_PRECO, precoProduto2);
        db.insert(TABELA_PRODUTO, null, contentValues2);

        String sacheGatoCpet = "sacheGatoCpet";
        String descricaoProduto3 = "Delicioso";
        int quantidadeProduto3 = 20;
        float precoProduto3 = (float) 5.00;
        ContentValues contentValues3 = new ContentValues();
        contentValues3.put(PRODUTO_NOME, sacheGatoCpet);
        contentValues3.put(PRODUTO_DESCRICAO, descricaoProduto3);
        contentValues3.put(PRODUTO_QUANTIDADE, quantidadeProduto3);
        contentValues3.put(PRODUTO_PRECO, precoProduto3);
        db.insert(TABELA_PRODUTO, null, contentValues3);

        String sacheCachorroCpet = "sacheCachorroCpet";
        String descricaoProduto4 = "Delicioso";
        int quantidadeProduto4 = 8;
        float precoProduto4 = (float) 5.50;
        ContentValues contentValues4 = new ContentValues();
        contentValues4.put(PRODUTO_NOME, sacheCachorroCpet);
        contentValues4.put(PRODUTO_DESCRICAO, descricaoProduto4);
        contentValues4.put(PRODUTO_QUANTIDADE, quantidadeProduto4);
        contentValues4.put(PRODUTO_PRECO, precoProduto4);
        db.insert(TABELA_PRODUTO, null, contentValues4);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
    }

    public boolean verificarUsuario(String username) {
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = null;
        try {
            String query = "SELECT * FROM TABELA_USUARIO WHERE USUARIO_NOME = ?";
            cursor = db.rawQuery(query, new String[]{username});
            if (cursor != null && cursor.getCount() > 0) {
                return true;
            }
            return false;
        } finally {
            if (cursor != null) {
                cursor.close();
            }
            db.close();
        }
    }
    public boolean adicionarUsuario(Usuario usuario) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(USUARIO_NOME, usuario.getNomeUsuario());
        contentValues.put(USUARIO_SENHA, usuario.getSenhaUsuario());
        long inserirSucedido = db.insert(TABELA_USUARIO, null, contentValues);
        db.close();
        return inserirSucedido != -1;
    }

    public boolean verificarCredenciais(String enteredUsername, String enteredPassword) {
        SQLiteDatabase db = this.getReadableDatabase();
        String[] columns = {USUARIO_NOME, USUARIO_SENHA};
        String selection = USUARIO_NOME + " = ? AND " + USUARIO_SENHA + " = ?";
        String[] selectionArgs = {enteredUsername, enteredPassword};
        Cursor cursor = db.query(TABELA_USUARIO, columns, selection, selectionArgs, null, null, null);
        boolean result = cursor.moveToFirst();
        cursor.close();
        db.close();
        return result;
    }

    public boolean registrarVenda(int usuarioId, int produtoId, int quantidade) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(USUARIO_ID, usuarioId);
        contentValues.put(PRODUTO_ID, produtoId);
        long inserirSucedido = db.insert(TABELA_VENDAS, null, contentValues);
        if (inserirSucedido != -1) {
            String whereClause = PRODUTO_ID + " = ?";
            String[] whereArgs = {String.valueOf(produtoId)};
            ContentValues updateValues = new ContentValues();
            updateValues.put(PRODUTO_QUANTIDADE, quantidade);
            int atualizarSucedido = db.update(TABELA_PRODUTO, updateValues, whereClause, whereArgs);
            db.close();
            return atualizarSucedido > 0;
        }
        db.close();
        return false;
    }

    @SuppressLint("Range")
    public int getIdUsuario(String nomeUsuario, String senhaUsuario) {
        SQLiteDatabase db = this.getReadableDatabase();
        String[] columns = {USUARIO_ID};
        String selection = USUARIO_NOME + " = ? AND " + USUARIO_SENHA + " = ?";
        String[] selectionArgs = {nomeUsuario, senhaUsuario};
        Cursor cursor = db.query(TABELA_USUARIO, columns, selection, selectionArgs, null, null, null);
        int usuarioId = 1;
        if (cursor.moveToFirst()) {
            usuarioId = cursor.getInt(cursor.getColumnIndex(USUARIO_ID));
        }
        cursor.close();
        db.close();
        return usuarioId;
    }

    public List<String> mostraCompras() {
        List<String> compras = new ArrayList<>();
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery("SELECT * FROM " + TABELA_VENDAS, null);
        if (cursor.moveToFirst()) {
            do {
                @SuppressLint("Range") int vendaId = cursor.getInt(cursor.getColumnIndex(VENDA_ID));
                @SuppressLint("Range") int usuarioId = cursor.getInt(cursor.getColumnIndex(USUARIO_ID));
                @SuppressLint("Range") int produtoId = cursor.getInt(cursor.getColumnIndex(PRODUTO_ID));
                String compra = "Venda ID: " + vendaId +
                        ", Usuário ID: " + usuarioId +
                        ", Produto ID: " + produtoId;
                compras.add(compra);
            } while (cursor.moveToNext());
        }
        cursor.close();
        db.close();
        return compras;
    }
}

