package models;

public abstract class Usuario {
    protected String nome;
    protected String senha;
    protected String email;
    protected String login;

    public Usuario(String nome, String senha, String email, String login) {
        this.nome = nome;
        this.senha = senha;
        this.email = email;
        this.login = login;
    }

    public String getLogin() {return login;}
    public void setLogin(String login) {this.login = login;}
    public String getNome() {return nome;}
    public void setNome(String nome) {this.nome = nome;}
    public String getSenha() {return senha;}
    public void setSenha(String senha) {this.senha = senha;}
    public String getEmail() {return email;}
    public void setEmail(String email) {this.email = email;}

}
