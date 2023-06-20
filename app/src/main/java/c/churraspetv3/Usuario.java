package c.churraspetv3;

public class Usuario {
    public Usuario(int idUsuario, String nomeUsuario, String senhaUsuario) {
        this.setIdUsuario(idUsuario);
        this.setNomeUsuario(nomeUsuario);
        this.setSenhaUsuario(senhaUsuario);
    }
    @Override
    public String toString() {
        return "Usuario{" + "idUsuario=" + idUsuario + ", nomeUsuario='" + nomeUsuario + '\'' + ", senhaUsuario=" + senhaUsuario + '}';
    }
    private int idUsuario;
    private String nomeUsuario;
    private String senhaUsuario;
    protected int getIdUsuario() {
        return idUsuario;
    }
    protected void setIdUsuario(int idUsuario) {
        this.idUsuario = idUsuario;
    }
    protected String getNomeUsuario() {
        return nomeUsuario;
    }
    protected void setNomeUsuario(String nomeUsuario) {
        this.nomeUsuario = nomeUsuario;
    }
    protected String getSenhaUsuario() {
        return senhaUsuario;
    }
    protected void setSenhaUsuario(String senhaUsuario) {
        this.senhaUsuario = senhaUsuario;
    }
}