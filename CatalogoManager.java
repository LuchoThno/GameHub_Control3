import java.util.ArrayList;

/**
 * Módulo de gestión del catálogo de videojuegos.
 * Utiliza ArrayList como estructura dinámica (agregar, recorrer y eliminar).
 */
public class CatalogoManager {

    private ArrayList<Videojuego> catalogo;

    public CatalogoManager() {
        this.catalogo = new ArrayList<>();
    }

    /** Agrega un videojuego al catálogo. */
    public void agregarVideojuego(Videojuego juego) {
        catalogo.add(juego);
        System.out.println("  [OK] Videojuego '" + juego.getNombre() + "' agregado al catalogo.");
    }

    /** Muestra todos los videojuegos en el orden en que fueron agregados. */
    public void mostrarCatalogo() {
        separador("CATALOGO DE VIDEOJUEGOS  (" + catalogo.size() + " juego(s))");
        if (catalogo.isEmpty()) {
            System.out.println("  El catalogo esta vacio.");
        } else {
            for (int i = 0; i < catalogo.size(); i++) {
                System.out.println("  [" + (i + 1) + "]");
                catalogo.get(i).mostrarInformacion();
            }
        }
        lineaSeparadora();
    }

    /**
     * Elimina un videojuego del catálogo por nombre.
     * @return el Videojuego eliminado, o null si no se encontró.
     */
    public Videojuego eliminarVideojuego(String nombre) {
        for (int i = 0; i < catalogo.size(); i++) {
            if (catalogo.get(i).getNombre().equalsIgnoreCase(nombre)) {
                Videojuego eliminado = catalogo.remove(i);
                System.out.println("  [OK] Videojuego '" + nombre + "' eliminado del catalogo.");
                return eliminado;
            }
        }
        System.out.println("  [!] No se encontro el videojuego '" + nombre + "' en el catalogo.");
        return null;
    }

    public ArrayList<Videojuego> getCatalogo() { return catalogo; }
    public int getTamanio()                    { return catalogo.size(); }

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
