package forcasolidaria.entities;

import java.util.Date;

public class Login {
    private String email;
    private String senha;
    private String nome;
    private String cpf;
    private Date dataNascimento;
    private char genero;

    public Login() {
    }

    public Login(String email, String cpf, String nome, String senha, Date dataNascimento, char genero) {
        this.email = email;
        this.cpf = cpf;
        this.nome = nome;
        this.senha = senha;
        this.dataNascimento = dataNascimento;
        this.genero = genero;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getSenha() {
        return senha;
    }

    public void setSenha(String senha) {
        this.senha = senha;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public Date getDataNascimento() {
        return dataNascimento;
    }

    public void setDataNascimento(Date dataNascimento) {
        this.dataNascimento = dataNascimento;
    }

    public String getCpf() {
        return cpf;
    }

    public void setCpf(String cpf) {
        this.cpf = cpf;
    }

    public char getGenero() {
        return genero;
    }

    public void setGenero(char genero) {
        this.genero = genero;
    }
}
