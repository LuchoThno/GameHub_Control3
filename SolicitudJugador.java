/**
 * Clase que representa una solicitud realizada por un jugador en GameHub.
 */
public class SolicitudJugador {

    private String nombreJugador;
    private String motivoSolicitud;

    public SolicitudJugador(String nombreJugador, String motivoSolicitud) {
        this.nombreJugador   = nombreJugador;
        this.motivoSolicitud = motivoSolicitud;
    }

    public String getNombreJugador()   { return nombreJugador; }
    public String getMotivoSolicitud() { return motivoSolicitud; }

    /** Muestra la información de la solicitud con formato. */
    public void mostrarInformacion() {
        System.out.println("    ┌────────────────────────────────────────┐");
        System.out.printf ("    │  Jugador : %-26s│%n", nombreJugador);
        System.out.printf ("    │  Motivo  : %-26s│%n", motivoSolicitud);
        System.out.println("    └────────────────────────────────────────┘");
    }

    @Override
    public String toString() { return nombreJugador + " - " + motivoSolicitud; }
}
