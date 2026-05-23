import java.util.LinkedList;
import java.util.Queue;

/**
 * Módulo de gestión de solicitudes de jugadores.
 * Utiliza una Cola (Queue) FIFO: el primero en llegar es el primero en ser atendido.
 */
public class SolicitudesManager {

    private Queue<SolicitudJugador> colaSolicitudes;

    public SolicitudesManager() {
        this.colaSolicitudes = new LinkedList<>();
    }

    /** Registra una nueva solicitud al final de la cola. */
    public void registrarSolicitud(SolicitudJugador solicitud) {
        colaSolicitudes.offer(solicitud);
        System.out.println("  [OK] Solicitud de '" + solicitud.getNombreJugador() + "' registrada en la cola.");
    }

    /**
     * Reinserta una solicitud al FRENTE de la cola.
     * Se usa al deshacer una atención: la solicitud vuelve a ser la próxima.
     */
    public void reinsertarAlFrente(SolicitudJugador solicitud) {
        // LinkedList permite addFirst para reinsertar al frente
        ((LinkedList<SolicitudJugador>) colaSolicitudes).addFirst(solicitud);
        System.out.println("  [OK] Solicitud de '" + solicitud.getNombreJugador()
                + "' devuelta al frente de la cola (estado: PENDIENTE).");
    }

    /** Consulta la próxima solicitud a atender SIN eliminarla. */
    public void consultarProximaSolicitud() {
        separador("PROXIMA SOLICITUD A ATENDER");
        if (colaSolicitudes.isEmpty()) {
            System.out.println("  No hay solicitudes pendientes.");
        } else {
            colaSolicitudes.peek().mostrarInformacion();
        }
        lineaSeparadora();
    }

    /**
     * Atiende (elimina) la solicitud que llegó primero.
     * @return la solicitud atendida, o null si la cola está vacía.
     */
    public SolicitudJugador atenderSolicitud() {
        if (colaSolicitudes.isEmpty()) {
            System.out.println("  No hay solicitudes para atender.");
            return null;
        }
        SolicitudJugador atendida = colaSolicitudes.poll();
        System.out.println("  [OK] Solicitud de '" + atendida.getNombreJugador() + "' atendida y retirada.");
        return atendida;
    }

    /** Muestra todas las solicitudes pendientes en orden de llegada. */
    public void mostrarSolicitudes() {
        separador("SOLICITUDES PENDIENTES  (" + colaSolicitudes.size() + " en cola)");
        if (colaSolicitudes.isEmpty()) {
            System.out.println("  No hay solicitudes pendientes.");
        } else {
            int pos = 1;
            for (SolicitudJugador s : colaSolicitudes) {
                System.out.println("  [" + pos + "]");
                s.mostrarInformacion();
                System.out.println();
                pos++;
            }
        }
        lineaSeparadora();
    }

    public boolean estaVacia() { return colaSolicitudes.isEmpty(); }

    // ── Utilidades visuales ──────────────────────────────────────
    private void separador(String titulo) {
        System.out.println("\n  ╔══════════════════════════════════════════╗");
        System.out.printf ("  ║  %-42s║%n", titulo);
        System.out.println("  ╠══════════════════════════════════════════╣");
    }
    private void lineaSeparadora() {
        System.out.println("  ╚══════════════════════════════════════════╝");
    }
}
