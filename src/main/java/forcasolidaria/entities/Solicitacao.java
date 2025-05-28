package forcasolidaria.entities;

public class Solicitacao {
    private String descricao;
    private int id_local;
    private int id_usuario;
    private String local;
    private String status;

    public Solicitacao() {
    }

    public Solicitacao(String descricao, String status, String local, int id_usuario, int id_local) {
        this.descricao = descricao;
        this.status = status;
        this.local = local;
        this.id_usuario = id_usuario;
        this.id_local = id_local;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public int getId_local() {
        return id_local;
    }

    public void setId_local(int id_local) {
        this.id_local = id_local;
    }

    public String getLocal() {
        return local;
    }

    public void setLocal(String local) {
        this.local = local;
    }

    public int getId_usuario() {
        return id_usuario;
    }

    public void setId_usuario(int id_usuario) {
        this.id_usuario = id_usuario;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}
