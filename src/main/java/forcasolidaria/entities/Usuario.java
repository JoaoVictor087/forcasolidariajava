package forcasolidaria.entities;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import java.sql.Date;
import java.time.LocalDate;

public class Usuario {
    private String nm_usuario;
    private java.sql.Date dt_nascimento_usuario;
    private String gen_usuario;

    public Usuario() {
    }

    public Usuario(String nm_usuario, Date dt_nascimento_usuario, String gen_usuario) {
        this.nm_usuario = nm_usuario;
        this.dt_nascimento_usuario = dt_nascimento_usuario;
        this.gen_usuario = gen_usuario;
    }

    public String getNm_usuario() {
        return nm_usuario;
    }

    public void setNm_usuario(String nm_usuario) {
        this.nm_usuario = nm_usuario;
    }

    public String getGen_usuario() {
        return gen_usuario;
    }

    public void setGen_usuario(String gen_usuario) {
        this.gen_usuario = gen_usuario;
    }


    public Date getDt_nascimento_usuario() {
        return dt_nascimento_usuario;
    }

    public void setDt_nascimento_usuario(Date dt_nascimento_usuario) {
        this.dt_nascimento_usuario = dt_nascimento_usuario;
    }
}
