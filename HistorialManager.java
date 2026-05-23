import java.util.Stack;

/**
 * Módulo de gestión del historial de acciones del sistema.
 * Utiliza una Pila (Stack) LIFO: la última acción registrada es la primera en consultarse.
 * Guarda objetos Accion para poder deshacer acciones con efecto sobre el estado del sistema.
 */
public class HistorialManager {

    private Stack<Accion> historial;

    public HistorialManager() {
        this.historial = new Stack<>();
    }

    /** Registra una nueva acción en el historial. */
    public void registrarAccion(Accion accion) {
        historial.push(accion);
    }

    /** Consulta la última acción registrada SIN eliminarla. */
    public void consultarUltimaAccion() {
        if (historial.isEmpty()) {
            System.out.println("  El historial está vacío.");
            return;
        }
        separador("ÚLTIMA ACCIÓN REGISTRADA");
        System.out.println("  >> " + historial.peek().getDescripcion());
        lineaSeparadora();
    }

    /**
     * Retira la última acción del historial y la retorna
     * para que Main pueda aplicar el efecto de deshacer.
     * @return la Accion retirada, o null si el historial está vacío.
     */
    public Accion deshacerUltimaAccion() {
        if (historial.isEmpty()) {
            System.out.println("  No hay acciones en el historial para deshacer.");
            return null;
        }
        Accion retirada = historial.pop();
        System.out.println("  [Historial] Accion retirada: " + retirada.getDescripcion());
        return retirada;
    }

    /** Muestra todo el historial desde la acción más reciente. */
    public void mostrarHistorial() {
        if (historial.isEmpty()) {
            System.out.println("  El historial está vacío.");
            return;
        }
        separador("HISTORIAL DE ACCIONES  (más reciente primero)");
        Stack<Accion> copia = (Stack<Accion>) historial.clone();
        int pos = 1;
        while (!copia.isEmpty()) {
            System.out.println("  [" + pos + "] " + copia.pop().getDescripcion());
            pos++;
        }
        lineaSeparadora();
    }

    public boolean estaVacio() { return historial.isEmpty(); }

    // ── Utilidades visuales ──────────────────────────────────────
    private void separador(String titulo) {
        System.out.println("\n  ╔══════════════════════════════════════════════╗");
        System.out.printf ("  ║ %-44s ║%n", titulo);
        System.out.println("  ╠══════════════════════════════════════════════╣");
    }
    private void lineaSeparadora() {
        System.out.println("  ╚══════════════════════════════════════════════╝");
    }
}
