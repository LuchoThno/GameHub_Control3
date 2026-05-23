/**
 * Clase que representa una acción registrada en el historial del sistema.
 * Almacena el tipo de acción, la descripción visible y opcionalmente
 * el objeto involucrado (para permitir deshacer acciones con estado).
 */
public class Accion {

    // Tipos de acción reconocidos por el sistema
    public static final String JUEGO_AGREGADO   = "JUEGO_AGREGADO";
    public static final String JUEGO_ELIMINADO  = "JUEGO_ELIMINADO";
    public static final String SOLICITUD_REGISTRADA = "SOLICITUD_REGISTRADA";
    public static final String SOLICITUD_ATENDIDA   = "SOLICITUD_ATENDIDA";

    private String tipo;
    private String descripcion;
    private Object objeto; // puede ser Videojuego o SolicitudJugador

    public Accion(String tipo, String descripcion, Object objeto) {
        this.tipo        = tipo;
        this.descripcion = descripcion;
        this.objeto      = objeto;
    }

    public String getTipo()        { return tipo; }
    public String getDescripcion() { return descripcion; }
    public Object getObjeto()      { return objeto; }

    @Override
    public String toString() { return descripcion; }
}
