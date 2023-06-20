package c.churraspetv3;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import androidx.annotation.Nullable;
public class AcessoBD extends SQLiteOpenHelper {
    protected static final String TABELA_USUARIO = "TABELA_USUARIO";
    protected static final String USUARIO_ID = "ID";
    protected static final String USUARIO_NOME = "USUARIO_NOME";
    protected static final String USUARIO_SENHA = "USUARIO_SENHA";
    protected static final String TABELA_PRODUTO = "TABELA_PRODUTO";
    protected static final String PRODUTO_ID = "ID";
    protected static final String PRODUTO_NOME = "PRODUTO_NOME";
    protected static final String PRODUTO_DESCRICAO = "PRODUTO_DESCRICAO";
    protected static final String PRODUTO_QUANTIDADE = "PRODUTO_QUANTIDADE";
    protected static final String PRODUTO_PRECO = "PRODUTO_PRECO";
    protected static final String TABELA_VENDA = "TABELA_VENDA";
    protected static final String VENDA_ID = "ID";

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

        String statementVenda = "CREATE TABLE " + TABELA_VENDA +
                " (" + VENDA_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, "
                + USUARIO_ID + " INTEGER, "
                + PRODUTO_ID + " INTEGER, "
                + "FOREIGN KEY (" + USUARIO_ID + ") REFERENCES " + TABELA_USUARIO + "(" + USUARIO_ID + "), "
                + "FOREIGN KEY (" + PRODUTO_ID + ") REFERENCES " + TABELA_PRODUTO + "(" + PRODUTO_ID + "))";
        db.execSQL(statementVenda);
    }
    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
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
}
