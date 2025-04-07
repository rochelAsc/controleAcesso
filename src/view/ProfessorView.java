package view;

import models.AccessLog;
import models.Professor;
import models.Usuario;
import services.LogService;
import services.ProfessorService;
import services.UsuarioService;

import java.util.ArrayList;
import java.util.Scanner;

public class ProfessorView {
    private Professor professor;
    private LogService logsvc;
    private Scanner scanner = new Scanner(System.in);
    //private UsuarioService usuarioService;
    private ProfessorService professorService;

    public ProfessorView(Professor professor, LogService logsvc, ProfessorService professorService) {
        this.professor = professor;
        this.logsvc = logsvc;
        //this.usuarioService = usuarioService;
        this.professorService = professorService;
    }

    private void ClScreen(){
        for(int i = 0; i < 50; i++){
            System.out.println();
        }
    }

    public void init(){
        label:
        while(true){
            System.out.println("Bem-vindo(a)" + professor.getNome() + "!\n");
            System.out.println("""
                    O que deseja fazer?
                    Digite 1 para alterar seus dados
                    Digite 2 para registrar um aluno novo ou professor
                    Digite 3 para remover um usuario do dociê
                    Digite 4 para resetar o sistema
                    Digite 5 para entrar no menu de registros
                    Digite 6 para sair do sistema
                    Digite 7 para mostrar a lista de usuários""");
            String escolha = scanner.nextLine();
            switch (escolha) {
                case "1":
                    ClScreen();
                    System.out.println("""
                            Digite 1 para alterar seu login
                            Digite 2 para alterar sua senha
                            Digite 3 para alterar seu nome
                            Digite 4 para alterar seu e-mail
                            """);
                    String dadoEscolha = scanner.nextLine();
                    switch (dadoEscolha) {
                        case "1":
                            System.out.println("Digite o seu login");
                            String novoLogin = scanner.nextLine();
                            professorService.UpdateLogin(professor, novoLogin);
                            return;
                        case "2":
                            System.out.println("Digite a sua senha");
                            String novoSenha = scanner.nextLine();
                            professorService.UpdatePassword(professor, novoSenha);
                            return;
                        case "3":
                            System.out.println("Digite o seu nome");
                            String novoNome = scanner.nextLine();
                            professorService.UpdateName(professor, novoNome);
                            return;
                        case "4":
                            System.out.println("Digite o seu e-mail");
                            String novoEmail = scanner.nextLine();
                            professorService.UpdateEmail(professor, novoEmail);
                            return;
                        default:
                            break;
                    }
                    break;
                case "2":
                    ClScreen();
                    System.out.println("O usuário que deseja cadastrar é um professor ou um aluno? ");
                    String tipo = scanner.nextLine();
                    if (!tipo.equalsIgnoreCase("professor") && !tipo.equalsIgnoreCase("aluno")) {
                        break label;
                    }
                    System.out.println("Informe o login do usuario:");
                    String login = scanner.nextLine();
                    System.out.println("Informe a senha do usuario:");
                    String senha = scanner.nextLine();
                    System.out.println("Informe a nome do usuario:");
                    String nome = scanner.nextLine();
                    System.out.println("Informe a e-mail do usuario:");
                    String email = scanner.nextLine();
                    professorService.RegisterUser(nome, senha, email, login, tipo);
                    break;
                case "3":
                    ClScreen();
                    for (Usuario usuario : professorService.getUserList()) {
                        System.out.println(usuario.getLogin());
                    }
                    System.out.println("Informe que usuario deseja remover");
                    String Remover = scanner.nextLine();
                    professorService.RemoveUserByLogin(Remover);
                    break;
                case "4":
                    ClScreen();
                    System.out.println("Você tem certeza que quer resetar o sistema? Diga SIM ou NÃO");
                    String decision = scanner.nextLine();
                    switch (decision) {
                        case "SIM":
                            professorService.SystemReset();
                            break;
                        case "NÃO":
                            return;
                    }
                    break;
                case "5":
                    ClScreen();
                    System.out.println("""
                            Diga o que deseja fazer
                            Digite 1 para acessar os registros diarios
                            Digite 2 para acessar os registros semanasi
                            """);
                    String escolhaLog = scanner.nextLine();
                    switch (escolhaLog) {
                        case "1":
                            System.out.println("""
                                    Especifique como deseja acessar
                                    Digite 1 novamente para acessar os registros de hoje
                                    Digite 2 para acessar todos os registros
                                    Digite 3 para escolher uma data e acessar os registro dessa data""");
                            String escolhaDir = scanner.nextLine();
                            switch (escolhaDir) {
                                case "1":
                                    System.out.println();
                                    ArrayList<AccessLog> todayLogs = logsvc.fetchTodayLog();
                                    if (!todayLogs.isEmpty()) {
                                        for (AccessLog log : todayLogs) {
                                            System.out.println("Logs de hoje:" + log.getDataHora() + " - " + log.getUsuario().getLogin() + "\n");
                                        }
                                    } else {
                                        System.out.println("Nenhum registro encontrado");
                                    }
                                    break;
                                case "2":
                                    System.out.println();
                                    ArrayList<AccessLog> allLogs = logsvc.getLogTree();
                                    if (!allLogs.isEmpty()) {
                                        for (AccessLog log : allLogs) {
                                            System.out.println("LogList:" + log.getDataHora() + " - " + log.getUsuario().getLogin() + "\n");
                                        }
                                    } else {
                                        System.out.println("Nenhum registro encontrado");
                                    }
                                    break;
                                case "3":
                                    System.out.println("Digite uma data no formato dd/MM-HH:mm, lê-se dia/mes-hora-minuto");
                                    String data = scanner.nextLine();
                                    ArrayList<AccessLog> dateLogs = logsvc.fetchLogbyDate(data);
                                    if (!dateLogs.isEmpty()) {
                                        for (AccessLog log : dateLogs) {
                                            System.out.printf("Logs do dia %s - Usuário: %s\n%n", log.getDataHora(), log.getUsuario().getLogin());
                                        }
                                    } else {
                                        System.out.println("Nenhum registro encontrado");
                                    }
                                    break;
                                default:
                                    System.out.println("Opção invalida");
                                    break;

                            }
                            break;
                        case "2":
                            System.out.println("""
                                    Especifique como deseja acessar
                                    Digite 1 novamente para realizar uma busca semanal por usuário
                                    Digite 2 para visualizar os acessos ao longo da semana
                                    Digite 3 para calcular a quantidade de acessos acumuladas
                                    Digite 4 para visualizar os acesso em um periodo especifico
                                    """);
                            String escolhaSem = scanner.nextLine();
                            switch (escolhaSem) {
                                case "1":
                                    System.out.println();
                                    System.out.println("Digite o login do usuario:");
                                    String logindata = scanner.nextLine();
                                    ArrayList<AccessLog> userLog = logsvc.fetchByUser(logindata);
                                    if (!userLog.isEmpty()) {
                                        for (AccessLog log : userLog) {
                                            System.out.printf("Logs do Usuário: %s\n%n", log.getUsuario().getLogin());
                                        }
                                    } else {
                                        System.out.println("Nenhum registro encontrado");
                                    }
                                    break;
                                case "2":
                                    System.out.println();
                                    ArrayList<AccessLog> weeklyLogs = logsvc.fetchWeeklyLogs();
                                    if (!weeklyLogs.isEmpty()) {
                                        for (AccessLog log : weeklyLogs) {
                                            System.out.printf("Log da última semana: %s - Usuário: %s%n", log.getUsuario().getLogin());
                                        }
                                    } else {
                                        System.out.println("Nenhum registro encontrado");
                                    }
                                    break;
                                case "3":
                                    System.out.println();
                                    System.out.printf("A quantidade de acessos é: %d%n", logsvc.fetchAccessCount());
                                    break;
                                case "4":
                                    System.out.println();
                                    System.out.println("Digite um periodo no formato dd/MM-HH:mm, lê-se dia/mes-hora-minuto, comece com uma data inicial");
                                    String datastart = scanner.nextLine();
                                    System.out.println("Agora digite, no mesmo formato, uma data fim");
                                    String dataend = scanner.nextLine();
                                    ArrayList<AccessLog> weeklyTimedLogs = logsvc.fetchLogsInPeriod(datastart, dataend);
                                    if (!weeklyTimedLogs.isEmpty()) {
                                        for (AccessLog log : weeklyTimedLogs) {
                                            System.out.printf("Log encontrado: %s - Usuário: %s - Data e Hora: %s%n", log.getDataHora(), log.getUsuario().getLogin(), log.getDataHora());
                                        }
                                    } else {
                                        System.out.println("Nenhum registro encontrado");
                                    }
                                    break;
                                default:
                                    break;
                            }
                        default:
                            break;
                    }
                    break;
                case "6":
                    return;
                    case "7":
                    ArrayList<Usuario> usuarios = professorService.getUserList();
                    for(Usuario usuario : usuarios){
                        System.out.printf("Nome: %s | Login: %s%n", usuario.getNome(), usuario.getLogin());
                }
                        break;
            }
        }
    }
}
