package forcasolidaria.entities;

public class Solicitacao {
    private String dsc;
    private String titulo;
    private String status;
    private int id_usuario;
    private int id_categoria;
    private int id_zona;

    public Solicitacao() {
    }

    public Solicitacao(String dsc, int id_zona, int id_categoria, int id_usuario, String status, String titulo) {
        this.dsc = dsc;
        this.id_zona = id_zona;
        this.id_categoria = id_categoria;
        this.id_usuario = id_usuario;
        this.status = status;
        this.titulo = titulo;
    }

    public String getDsc() {
        return dsc;
    }

    public void setDsc(String dsc) {
        this.dsc = dsc;
    }

    public int getId_categoria() {
        return id_categoria;
    }

    public void setId_categoria(int id_categoria) {
        this.id_categoria = id_categoria;
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

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public int getId_zona() {
        return id_zona;
    }

    public void setId_zona(int id_zona) {
        this.id_zona = id_zona;
    }
}

