package view;
import models.*;
import services.*;
import java.util.Scanner; import java.time.format.DateTimeFormatter; import java.time.LocalDateTime; import java.time.LocalDate;

public class UserView
{
        private LogService log;
        private Scanner scanner = new Scanner(System.in);
        public ProfessorService professorService;
        public AlunoView Alunoview;
        public ProfessorView ProfessorView;
        public UserView(LogService log, ProfessorService professorService){
            this.log = log;
            this.professorService = professorService;
        }

        public void init() {
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy-HH:mm");
            if (professorService.getUserList().isEmpty()) {
                Usuario admin = new Professor("admin", "admin", "admin@admin.com", "admin");
                professorService.getUserList().add(admin);
                System.out.println("Usuário admin criado!");
            }
            System.out.println("Lista de usuários: ");
            for (Usuario usuario : professorService.getUserList()) {
                System.out.println(usuario.getLogin());
            }
            while (true) {

                System.out.println("Digite seu login:");
                String login = scanner.nextLine();
                System.out.println("Digite sua senha:");
                String senha = scanner.nextLine();
                Usuario usuario = professorService.getUserByLogin(login);
                if (usuario == null) {
                    System.out.println("""
                            Usuário não existe
                            Por favor, peça para um professor cadastra-lo
                            Deseja tentar novamente com outro login ? Se sim, digite 1, se deseja sair, digite 2
                            """);
                    String selecao = scanner.nextLine();

                    if(selecao.equals("2")) {
                        System.out.println("Saindo, tenha um excelente dia");
                        break;
                    }else if(!selecao.equals("1")){
                        System.out.println("Não é uma opção valida");
                    }
                }
                else if(!usuario.getLogin().equals(login) && !usuario.getSenha().equals(senha)) {
                    System.out.println("Dados incorretos");
                    break;
                }
                try {
                    System.out.println("Digite a data de hoje, deve estar no formato dd/MM/aaaa-HH:mm");
                    String dataStr = scanner.nextLine();
                    LocalDateTime data = LocalDateTime.parse(dataStr, formatter);
                    if (usuario instanceof Professor) {
                        log.addLogElement(usuario, data);
                        ProfessorView = new ProfessorView((Professor) usuario, log, professorService);
                        ProfessorView.init();
                    } else if (usuario instanceof Aluno) {
                        log.addLogElement(usuario, data);
                        Alunoview = new AlunoView((Aluno) usuario, log, professorService);
                        Alunoview.init();
                    }
                }catch (Exception alph) {
                    System.out.println("data invalida");
                    break;
                }
            }
        }
    }
