import view.*;
import services.*;

public class Main {
    public static void main(String[] args) {
        LogService MainLogService = new LogService();
        ProfessorService MainProfessorService = new ProfessorService();
        UserView MainUserView = new UserView(MainLogService, MainProfessorService);

        MainUserView.init();
    }
}