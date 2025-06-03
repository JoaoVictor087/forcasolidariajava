package forcasolidaria.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;

public class Solicitacao {
    private String dsc;
    private String titulo;
    private String status;
    @JsonIgnore private int id_usuario;
    @JsonIgnore private int id_categoria;
    @JsonIgnore private int id_zona;
    private String endereco;
    private String nm_usuario;
    private String categoria;
    private String zona;

    public Solicitacao() {
    }

    public Solicitacao(String dsc, String titulo, int id_usuario, String status, int id_categoria, int id_zona, String nm_usuario, String endereco, String categoria, String zona) {
        this.dsc = dsc;
        this.titulo = titulo;
        this.id_usuario = id_usuario;
        this.status = status;
        this.id_categoria = id_categoria;
        this.id_zona = id_zona;
        this.nm_usuario = nm_usuario;
        this.endereco = endereco;
        this.categoria = categoria;
        this.zona = zona;
    }

    public String getDsc() {
        return dsc;
    }

    public void setDsc(String dsc) {
        this.dsc = dsc;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public int getId_usuario() {
        return id_usuario;
    }

    public void setId_usuario(int id_usuario) {
        this.id_usuario = id_usuario;
    }

    public int getId_zona() {
        return id_zona;
    }

    public void setId_zona(int id_zona) {
        this.id_zona = id_zona;
    }

    public int getId_categoria() {
        return id_categoria;
    }

    public void setId_categoria(int id_categoria) {
        this.id_categoria = id_categoria;
    }

    public String getEndereco() {
        return endereco;
    }

    public void setEndereco(String endereco) {
        this.endereco = endereco;
    }

    public String getCategoria() {
        return categoria;
    }

    public void setCategoria(String categoria) {
        this.categoria = categoria;
    }

    public String getNm_usuario() {
        return nm_usuario;
    }

    public void setNm_usuario(String nm_usuario) {
        this.nm_usuario = nm_usuario;
    }

    public String getZona() {
        return zona;
    }

    public void setZona(String zona) {
        this.zona = zona;
    }

    @Override
    public String toString() {
        return "Solicitacao{" +
                "dsc='" + dsc + '\'' +
                ", titulo='" + titulo + '\'' +
                ", status='" + status + '\'' +
                ", id_usuario=" + id_usuario +
                ", id_categoria=" + id_categoria +
                ", id_zona=" + id_zona +
                ", endereco='" + endereco + '\'' +
                ", nm_usuario='" + nm_usuario + '\'' +
                ", categoria='" + categoria + '\'' +
                ", zona='" + zona + '\'' +
                '}';
    }
}

