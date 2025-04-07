package services;
import models.Usuario;
import java.util.ArrayList;


public class UsuarioService {
    private ArrayList<Usuario> UserList = new ArrayList<>();
    public ArrayList<Usuario> getUserList() {
        return UserList;
    }

    public UsuarioService(){
    }

    public void UpdateLogin(Usuario caller, String login){
        if(login != null && !login.trim().isEmpty()){
            if(login.length()>4 && login.length()<10){
                if(login.matches("[a-zA-Z]+")){
                    caller.setLogin(login);
                }else{throw new IllegalArgumentException("Formato invalido");}
            }else{throw new IllegalArgumentException("Tamanho invalido");}
        }else{throw new IllegalArgumentException("Não pode ser vazio");}
    }

    public void UpdateName(Usuario caller, String name){
        if(name != null && !name.trim().isEmpty()){
            if(name.length()>4 && name.length()<32){
                if(name.matches("[a-zA-Z]+")){
                    caller.setNome(name);
                }else{throw new IllegalArgumentException("Formato invalido");}
            }else{throw new IllegalArgumentException("Tamanho invalido");}
        }else{throw new IllegalArgumentException("Não pode ser vazio");}
    }

    public void UpdateEmail(Usuario caller, String email){
        if(email != null && !email.trim().isEmpty()){
            if(!email.contains("@") && !email.contains(".")){
                caller.setEmail(email);
            }else{throw new IllegalArgumentException("Formato invalido");}
        }else{throw new IllegalArgumentException("Não pode ser nulo");}
    }

    public void UpdatePassword(Usuario caller, String password){
        if(password != null && !password.trim().isEmpty()){
            if(password.length()>4 && password.length()<16){
                if(password.matches("[a-zA-Z0-9]+")){
                    caller.setSenha(password);
                }else{throw new IllegalArgumentException("Formato invalido");}
            }else{throw new IllegalArgumentException("Tamanho invalido");}
        }else{throw new IllegalArgumentException("Não pode ser vazio");}
    }


    public Usuario getUserByLogin(String login) {
        return UserList.stream()
                .filter(u -> u.getLogin().trim().equalsIgnoreCase(login.trim()))
                .findFirst()
                .orElse(null);
    }

}
