package services;
import models.AccessLog; import models.Usuario;
import java.time.LocalDate; import java.util.ArrayList; import java.time.temporal.ChronoUnit;
import java.time.LocalDateTime; import java.util.stream.Collectors; import java.time.format.DateTimeFormatter;

public class LogService {
    protected final ArrayList<AccessLog> logtree = new ArrayList<>();
    protected final DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy-HH:mm");

    public void addLogElement(Usuario usuario, LocalDateTime dataHora) {
        AccessLog logRegister = new AccessLog(usuario, dataHora);
        logtree.add(logRegister);
    }

    public ArrayList<AccessLog> getLogTree() {
        return logtree;
    }

    public ArrayList<AccessLog> fetchLogbyDate(String datastr) {
            int dia = Integer.parseInt(datastr);
            if(dia > 31){throw new IllegalArgumentException("Data invalida");}
            return logtree.stream()
                    .filter(log -> log.getDataHora().getDayOfMonth() == dia)
                    .collect(Collectors.toCollection(ArrayList::new));
    }

    public ArrayList<AccessLog> fetchTodayLog(){
        String today = LocalDateTime.now().format(formatter);
        return logtree.stream()
                .filter(log -> log.getDataHora().format(formatter).equals(today))
                .collect(Collectors.toCollection(ArrayList::new));
    }

    public ArrayList<AccessLog> fetchByUser(String login){
        return logtree.stream()
                .filter(log -> log.getUsuario().getLogin().equals(login))
                .collect(Collectors.toCollection(ArrayList::new));
    }

    public ArrayList<AccessLog> fetchLogsInPeriod(String startDatestr, String endDatestr) {
        LocalDate startDate = LocalDate.parse(startDatestr, formatter);
        LocalDate endDate = LocalDate.parse(endDatestr, formatter);

        return logtree.stream()
                .filter(log -> !log.getDataHora().toLocalDate().isBefore(startDate)
                        && !log.getDataHora().toLocalDate().isAfter(endDate))
                .collect(Collectors.toCollection(ArrayList::new));
    }

    public ArrayList<AccessLog> fetchWeeklyLogs() {
        LocalDateTime today = LocalDateTime.now();
        LocalDateTime weekAgo = today.minusDays(7);

        return logtree.stream()
                .filter(log -> !log.getDataHora().isBefore(weekAgo))
                .filter(log -> !log.getDataHora().isAfter(today))
                .collect(Collectors.toCollection(ArrayList::new));
    }

    public long fetchAccessCount(){
        return logtree.stream().count();
    }

}
