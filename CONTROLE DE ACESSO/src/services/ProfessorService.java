package services;
import models.Aluno; import models.Professor; import models.Usuario;


public class ProfessorService extends UsuarioService {

    public void RemoveUserByLogin(String login) {
        boolean Verify = getUserList().stream().anyMatch(usuario -> usuario.getLogin().equals(login));
        if(Verify) {
            getUserList()
                    .removeIf(user -> user.getLogin().equals(login));
        }
        else{return;}
}

        public void RegisterUser(String nome, String senha, String email, String login, String key) {
        boolean Verify = getUserList().stream().anyMatch(usuario -> usuario.getLogin().equals(login));
        if(Verify) {
            System.out.println("Usuário já cadastrado");
            return;
        }
            Usuario newUsuario;
        switch (key.toUpperCase()) {
            case "ALUNO":

                newUsuario = new Aluno(nome, senha, email, login);
                break;
            case "PROFESSOR":
                newUsuario = new Professor(nome, senha, email, login);
                break;
            default:
                throw new IllegalArgumentException("USUÁRIO INVÁLIDO");
        }
            getUserList().add(newUsuario);
        System.out.println("Usuário cadastrado!");
        }

    public void SystemReset(){
        if(getUserList() != null){
            getUserList().clear();
        }
        else{
            System.out.println("Lista ja está vazia");
            return;
        }
    }

}
