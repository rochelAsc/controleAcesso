package view;

import services.LogService;
import services.UsuarioService; import java.util.Scanner;
import models.Aluno;


public class AlunoView {
    private Aluno aluno;
    private LogService logservice;
    private Scanner scanner = new Scanner(System.in);
    private UsuarioService usuarioService;

    public AlunoView(Aluno aluno, LogService logservice, UsuarioService usuarioService) {
        this.aluno = aluno;
        this.logservice = logservice;
        this.usuarioService = usuarioService;
    }
    private void ClScreen(){
        for(int i = 0; i < 50; i++){
            System.out.println();
        }
    }
    public void init(){
        while(true){
            System.out.println("Bem-vindo(a)" + aluno.getNome() + "!\n");
            System.out.println("""
                    O que deseja fazer?
                    Digite 1 para alterar seus dados
                    Digite 2 para sair
                    """);
            String escolha = scanner.nextLine();

            if(escolha.equals("1")){
                ClScreen();
                System.out.println("""
                        Digite 1 para alterar seu login
                        Digite 2 para alterar sua senha
                        Digite 3 para alterar seu nome
                        Digite 4 para alterar seu e-mail
                        """);
                String dadoEscolha = scanner.nextLine();
                switch(dadoEscolha){
                    case "1": System.out.println("Digite o seu login");
                        String novoLogin = scanner.nextLine();
                    usuarioService.UpdateLogin(aluno, novoLogin);
                        break;
                    case "2": System.out.println("Digite a sua senha");
                        String novoSenha = scanner.nextLine();
                    usuarioService.UpdatePassword(aluno, novoSenha);
                        break;
                    case "3": System.out.println("Digite o seu nome");
                        String novoNome = scanner.nextLine();
                    usuarioService.UpdateName(aluno, novoNome);
                        break;
                    case "4": System.out.println("Digite o seu e-mail");
                        String novoEmail = scanner.nextLine();
                    usuarioService.UpdateEmail(aluno, novoEmail);
                        break;
                    default:
                        break;
                }
            }
            else if(escolha.equals("2")){
                System.out.println("Volte sempre!");
                return;
            }



        }
    }

}
