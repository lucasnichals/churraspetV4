package c.churraspetv3;

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
    protected static final String USUARIO_ID = "ID";
    protected static final String USUARIO_NOME = "USUARIO_NOME";
    protected static final String USUARIO_SENHA = "USUARIO_IDADE";


    public AcessoBD(@Nullable Context context) {
        super(context, "ClienteBD", null, 1);
    }
    //É chamado na primeira vez que o banco de Dados(BD) é acessado.
    //Usado também para a criação do banco de dados
    @Override
    public void onCreate(SQLiteDatabase db) {
        String statement = "CREATE TABLE " + TABELA_USUARIO +
                " (" + USUARIO_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, "
                + USUARIO_NOME + " TEXT, " + USUARIO_SENHA + " TEXT)";
        db.execSQL(statement);
    }
    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
    }
    //INSERT INTO tabela_usuario(id, nome, idade) values (1,"Ronan", 30)
    //Para adicionar um usuário
    public boolean adicionarUsuario(Usuario usuario) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(USUARIO_NOME, usuario.getNomeUsuario());
        contentValues.put(USUARIO_SENHA, usuario.getSenhaUsuario());
        long inserirSucedido = db.insert(TABELA_USUARIO, null, contentValues);
        db.close();
        return inserirSucedido != -1;
    }
    public boolean atualizarUsuario(Usuario usuario) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(USUARIO_NOME, usuario.getNomeUsuario());
        contentValues.put(USUARIO_SENHA, usuario.getSenhaUsuario());
        contentValues.put(USUARIO_ID, usuario.getIdUsuario());
        long atualizarSucedido = db.update(TABELA_USUARIO, contentValues, USUARIO_ID + "=" + usuario.getIdUsuario(), null);//nullColumnHack é null quando deseja-se adicionar uma linha não nula. Quando intencionalmente deseja inserir uma linha vazia, é necessário informar o valor de uma coluna da tabela usada. No caso da tabela usuário, pode ser nome ou idade.
        db.close();
        return atualizarSucedido != -1;
    }

    /**
     * Método para retornar a lista de usuarios completa.
     * Cria uma lista (listaUsuarios) vazia para preenchimento com as tuplas resultantes da consulta no banco de dados.
     *
     * @return List de objetos Usuario
     */
    public List<Usuario> getListaUsuarios() {//Para importar List, pode ser usado o atalho com Alt+Enter (para escolher a ação) ou Shift+Alt+Enter (para pegar o pacote sugerido pela ferramenta Android Studio). Tem que ser do pacote "java.util.List"

        List<Usuario> listaUsuarios = new ArrayList<>();

        //String queryStatement = "SELECT * FROM TABELA_USUARIO";
        String queryStatement = "SELECT * FROM " + TABELA_USUARIO;//Considere a linha acima para visualizar a sentença como ela é na prática.

        SQLiteDatabase db = this.getReadableDatabase();//Readable para operações de Select. Fechar após uso.

        //O retorno Cursor serve para receber o resultado do banco e navegar entre os resultados. Fechar após uso.
        //selectionArgs é usado para preencher os parâmetros da SQL para os campus da cláusula WHERE
        try (Cursor cursor = db.rawQuery(queryStatement, null)) {
            //Exemplo com selectionArgs ->
            // rawQuery("SELECT ID, USUARIO_NOME, USUARIO_IDADE FROM TABELA_USUARIO WHERE id = ? AND USUARIO_NOME = ? AND USUARIO_IDADE = ?", new String[] {"1", "David", "2"});
            //rawQuery("SELECT ID, USUARIO_NOME, USUARIO_IDADE FROM TABELA_USUARIO WHERE id = 1 AND USUARIO_NOME = David AND USUARIO_IDADE = 2",null);
            if (cursor.moveToFirst()) {//moveToFirst retorna true no caso de haver registro(s( proveniente(s) da consulta. Posiciona cursor no primeiro resultado
                //do/while Percorrer o cursor para instanciar objetos da classe Usuario.
                do {
                    int usuarioCod = cursor.getInt(0);//A primeira coluna da tabela usuário é código
                    String usuarioNome = cursor.getString(1);
                    String usuarioSenha = cursor.getString(2);
                    //int usuarioIdade = cursor.getInt(cursor.getColumnIndex("idade"));
                    //String dataNascimentoUsuario = cursor.getString(3);
                    //Não há cursor.getBoolean. Precisa converter um int para boolean. Pode ser feito com cast, SE/ENTÃO ou operador ternário

                    Usuario usuario = new Usuario(usuarioCod, usuarioNome, usuarioSenha);//Sempre confira a ordem do construtor
                    listaUsuarios.add(usuario);//Adiciona o objeto usuário a lista.
                } while (cursor.moveToNext());//Enquanto houver um próximo registro (moveToNext)
            } else {
                //A lista está vazia por falta de registro no banco de dados.
            }
            //fechar cursor e base de dados. Com isso, permite que outras pessoas/processos possam utilizar.
            cursor.close();
        }
        db.close();//Fechar o banco de dados para liberar memória e permitir que outros processos assumam a prioridade de consulta de maneira mais rápida
        return listaUsuarios; //retorna a lista com os usuários encontrados no banco de dados
    }

    /**
     * Método para encontrar um usuário no banco de dados e excluí-lo.
     *
     * @param usuario
     * @return true no caso do usuário ser encontrado e excluído
     */
    public boolean excluirUsuario(Usuario usuario) {
        SQLiteDatabase db = this.getWritableDatabase();
        String queryString =
                "DELETE FROM " + TABELA_USUARIO + " WHERE " + USUARIO_ID + " = " + usuario.getIdUsuario();
        Cursor cursor = db.rawQuery(queryString, null);
        if (cursor.moveToFirst()) {
            return true;
        } else {
            return false;
        }
    }
}
