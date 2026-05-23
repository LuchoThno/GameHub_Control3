/**
 * Clase que representa un videojuego en el catálogo de GameHub.
 */
public class Videojuego {

    private String nombre;
    private String genero;
    private String plataforma;
    private double precio;

    public Videojuego(String nombre, String genero, String plataforma, double precio) {
        this.nombre     = nombre;
        this.genero     = genero;
        this.plataforma = plataforma;
        this.precio     = precio;
    }

    public String getNombre()     { return nombre; }
    public String getGenero()     { return genero; }
    public String getPlataforma() { return plataforma; }
    public double getPrecio()     { return precio; }

    /** Muestra la información del videojuego con formato claro. */
    public void mostrarInformacion() {
        String nom = nombre.length()     > 29 ? nombre.substring(0,26)     + "..." : nombre;
        String gen = genero.length()     > 29 ? genero.substring(0,26)     + "..." : genero;
        String pla = plataforma.length() > 29 ? plataforma.substring(0,26) + "..." : plataforma;
        System.out.println("    ┌────────────────────────────────────────────┐");
        System.out.printf ("    │  Nombre     : %-29s│%n", nom);
        System.out.printf ("    │  Genero     : %-29s│%n", gen);
        System.out.printf ("    │  Plataforma : %-29s│%n", pla);
        System.out.printf ("    │  Precio     : $%-28.2f│%n", precio);
        System.out.println("    └────────────────────────────────────────────┘");
    }

    @Override
    public String toString() { return nombre + " [" + plataforma + "]"; }
}
