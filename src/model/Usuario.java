package model;

import java.util.Objects;

public class Usuario {

    private Integer id;
    private String nome;
    private String login;
    private String senha;
    private String email;
    private String perfi;

    public Usuario() {
    }

    public Usuario(Integer id, String nome, String login, String senha, String email, String perfi) {
        this.id = id;
        this.nome = nome;
        this.login = login;
        this.senha = senha;
        this.email = email;
        this.perfi = perfi;
    }

    public Usuario(String nome, String login, String senha, String email, String perfi) {
        this.nome = nome;
        this.login = login;
        this.senha = senha;
        this.email = email;
        this.perfi = perfi;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getSenha() {
        return senha;
    }

    public void setSenha(String senha) {
        this.senha = senha;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPerfi() {
        return perfi;
    }

    public void setPerfi(String perfi) {
        this.perfi = perfi;
    }

    @Override
    public int hashCode() {
        int hash = 3;
        hash = 19 * hash + Objects.hashCode(this.id);
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Usuario other = (Usuario) obj;
        return Objects.equals(this.id, other.id);
    }

}
