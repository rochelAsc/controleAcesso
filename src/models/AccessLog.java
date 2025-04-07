package models;
import java.time.LocalDateTime; import java.time.LocalDate;


public class AccessLog {
    private final Usuario usuario;
    private final LocalDateTime dataHora;


    public AccessLog(Usuario usuario, LocalDateTime dataHora) {
        this.usuario = usuario;
        this.dataHora = dataHora;
    }

    public Usuario getUsuario() {
        return usuario;
    }

    public LocalDateTime getDataHora() {
        return dataHora;
    }
}
