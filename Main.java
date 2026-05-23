import java.util.Scanner;

/**
 * Clase principal del sistema GameHub.
 * Menu organizado en submenus por sección.
 * Al deshacer "Solicitud atendida", la solicitud vuelve al frente de la cola (PENDIENTE).
 */
public class Main {

    static CatalogoManager   catalogo   = new CatalogoManager();
    static SolicitudesManager solicitudes = new SolicitudesManager();
    static HistorialManager   historial  = new HistorialManager();
    static Scanner scanner = new Scanner(System.in);

    // ── Colores ANSI ─────────────────────────────────────────────
    static final String RESET  = "\u001B[0m";
    static final String CYAN   = "\u001B[36m";
    static final String GREEN  = "\u001B[32m";
    static final String YELLOW = "\u001B[33m";
    static final String RED    = "\u001B[31m";
    static final String BOLD   = "\u001B[1m";
    static final String BLUE   = "\u001B[34m";

    public static void main(String[] args) {
        limpiarPantalla();
        mostrarBienvenida();
        cargarDatosIniciales();
        pausar();

        int opcion = -1;
        do {
            limpiarPantalla();
            mostrarMenuPrincipal();
            try {
                System.out.print(BOLD + "  Seleccione una seccion: " + RESET);
                opcion = Integer.parseInt(scanner.nextLine().trim());
                switch (opcion) {
                    case 1: menuCatalogo();     break;
                    case 2: menuSolicitudes();  break;
                    case 3: menuHistorial();    break;
                    case 0: break;
                    default:
                        System.out.println(RED + "  [!] Opcion no valida. Intente nuevamente." + RESET);
                        pausar();
                }
            } catch (NumberFormatException e) {
                System.out.println(RED + "  [!] Error: ingrese un numero entero." + RESET);
                pausar();
            }
        } while (opcion != 0);

        limpiarPantalla();
        System.out.println(CYAN + BOLD);
        System.out.println("  ╔══════════════════════════════════════════╗");
        System.out.println("  ║       Gracias por usar GameHub!          ║");
        System.out.println("  ║         Hasta la proxima sesion          ║");
        System.out.println("  ╚══════════════════════════════════════════╝");
        System.out.println(RESET);
        scanner.close();
    }

    // ════════════════════════════════════════════════════════════
    //  BIENVENIDA Y CARGA INICIAL
    // ════════════════════════════════════════════════════════════

    private static void mostrarBienvenida() {
        System.out.println(CYAN + BOLD);
        System.out.println("  ╔══════════════════════════════════════════════╗");
        System.out.println("  ║                                              ║");
        System.out.println("  ║        ██████╗  █████╗ ███╗   ███╗███████╗   ║");
        System.out.println("  ║       ██╔════╝ ██╔══██╗████╗ ████║██╔════╝   ║");
        System.out.println("  ║       ██║  ███╗███████║██╔████╔██║█████╗     ║");
        System.out.println("  ║       ██║   ██║██╔══██║██║╚██╔╝██║██╔══╝     ║");
        System.out.println("  ║       ╚██████╔╝██║  ██║██║ ╚═╝ ██║███████╗   ║");
        System.out.println("  ║        ╚═════╝ ╚═╝  ╚═╝╚═╝     ╚═╝╚══════╝   ║");
        System.out.println("  ║                  H U B                       ║");
        System.out.println("  ║                                              ║");
        System.out.println("  ║      Sistema de Gestion de Videojuegos       ║");
        System.out.println("  ╚══════════════════════════════════════════════╝");
        System.out.println(RESET);
    }

    private static void cargarDatosIniciales() {
        System.out.println(YELLOW + "\n  >> Cargando datos iniciales del sistema..." + RESET);
        System.out.println();

        Videojuego v1 = new Videojuego("The Legend of Zelda: TOTK", "Aventura",    "Nintendo Switch", 59.99);
        Videojuego v2 = new Videojuego("Elden Ring",                "RPG",         "PlayStation 5",   49.99);
        Videojuego v3 = new Videojuego("Minecraft",                 "Sandbox",     "PC",              26.95);

        catalogo.agregarVideojuego(v1);
        historial.registrarAccion(new Accion(Accion.JUEGO_AGREGADO,
                "Videojuego agregado: " + v1.getNombre(), v1));

        catalogo.agregarVideojuego(v2);
        historial.registrarAccion(new Accion(Accion.JUEGO_AGREGADO,
                "Videojuego agregado: " + v2.getNombre(), v2));

        catalogo.agregarVideojuego(v3);
        historial.registrarAccion(new Accion(Accion.JUEGO_AGREGADO,
                "Videojuego agregado: " + v3.getNombre(), v3));

        System.out.println();

        SolicitudJugador s1 = new SolicitudJugador("Carlos Perez",   "Error en partida guardada");
        SolicitudJugador s2 = new SolicitudJugador("Ana Gonzalez",   "Reembolso por compra duplicada");
        SolicitudJugador s3 = new SolicitudJugador("Luis Martinez",  "Logro desbloqueado incorrectamente");

        solicitudes.registrarSolicitud(s1);
        historial.registrarAccion(new Accion(Accion.SOLICITUD_REGISTRADA,
                "Solicitud registrada: " + s1.getNombreJugador(), s1));

        solicitudes.registrarSolicitud(s2);
        historial.registrarAccion(new Accion(Accion.SOLICITUD_REGISTRADA,
                "Solicitud registrada: " + s2.getNombreJugador(), s2));

        solicitudes.registrarSolicitud(s3);
        historial.registrarAccion(new Accion(Accion.SOLICITUD_REGISTRADA,
                "Solicitud registrada: " + s3.getNombreJugador(), s3));

        System.out.println();
        System.out.println(GREEN + "  >> Datos iniciales cargados correctamente." + RESET);
    }

    // ════════════════════════════════════════════════════════════
    //  MENÚ PRINCIPAL
    // ════════════════════════════════════════════════════════════

    private static void mostrarMenuPrincipal() {
        System.out.println(BOLD + CYAN);
        System.out.println("  ╔══════════════════════════════════════════════╗");
        System.out.println("  ║            GAMEHUB  -  MENU PRINCIPAL        ║");
        System.out.println("  ╠══════════════════════════════════════════════╣");
        System.out.println("  ║                                              ║");
        System.out.println("  ║  " + GREEN  + " [1]  " + RESET + BOLD + CYAN + " Catalogo de Videojuegos              ║");
        System.out.println("  ║        Agregar, ver y eliminar juegos        ║");
        System.out.println("  ║                                              ║");
        System.out.println("  ║  " + YELLOW + " [2]  " + RESET + BOLD + CYAN + " Solicitudes de Jugadores             ║");
        System.out.println("  ║        Registrar, consultar y atender        ║");
        System.out.println("  ║                                              ║");
        System.out.println("  ║  " + BLUE   + " [3]  " + RESET + BOLD + CYAN + " Historial de Acciones                ║");
        System.out.println("  ║        Ver y deshacer acciones               ║");
        System.out.println("  ║                                              ║");
        System.out.println("  ║  " + RED    + " [0]  " + RESET + BOLD + CYAN + " Salir del sistema                    ║");
        System.out.println("  ║                                              ║");
        System.out.println("  ╚══════════════════════════════════════════════╝");
        System.out.println(RESET);
    }

    // ════════════════════════════════════════════════════════════
    //  SUBMENU CATÁLOGO
    // ════════════════════════════════════════════════════════════

    private static void menuCatalogo() {
        int opcion = -1;
        do {
            limpiarPantalla();
            System.out.println(BOLD + GREEN);
            System.out.println("  ╔══════════════════════════════════════════════╗");
            System.out.println("  ║          CATALOGO DE VIDEOJUEGOS             ║");
            System.out.println("  ╠══════════════════════════════════════════════╣");
            System.out.println("  ║                                              ║");
            System.out.println("  ║   [1]  Agregar nuevo videojuego              ║");
            System.out.println("  ║   [2]  Ver todos los videojuegos             ║");
            System.out.println("  ║   [3]  Eliminar un videojuego                ║");
            System.out.println("  ║                                              ║");
            System.out.println("  ║   [0]  Volver al menu principal              ║");
            System.out.println("  ║                                              ║");
            System.out.println("  ╚══════════════════════════════════════════════╝");
            System.out.println(RESET);
            try {
                System.out.print(BOLD + "  Seleccione: " + RESET);
                opcion = Integer.parseInt(scanner.nextLine().trim());
                switch (opcion) {
                    case 1: opcionAgregarVideojuego(); pausar(); break;
                    case 2: catalogo.mostrarCatalogo(); pausar(); break;
                    case 3: opcionEliminarVideojuego(); pausar(); break;
                    case 0: break;
                    default:
                        System.out.println(RED + "  [!] Opcion no valida." + RESET);
                        pausar();
                }
            } catch (NumberFormatException e) {
                System.out.println(RED + "  [!] Error: ingrese un numero entero." + RESET);
                pausar();
            }
        } while (opcion != 0);
    }

    // ════════════════════════════════════════════════════════════
    //  SUBMENU SOLICITUDES
    // ════════════════════════════════════════════════════════════

    private static void menuSolicitudes() {
        int opcion = -1;
        do {
            limpiarPantalla();
            System.out.println(BOLD + YELLOW);
            System.out.println("  ╔══════════════════════════════════════════════╗");
            System.out.println("  ║          SOLICITUDES DE JUGADORES            ║");
            System.out.println("  ╠══════════════════════════════════════════════╣");
            System.out.println("  ║                                              ║");
            System.out.println("  ║   [1]  Registrar nueva solicitud             ║");
            System.out.println("  ║   [2]  Ver solicitudes pendientes            ║");
            System.out.println("  ║   [3]  Consultar proxima solicitud           ║");
            System.out.println("  ║   [4]  Atender solicitud (retirar cola)      ║");
            System.out.println("  ║                                              ║");
            System.out.println("  ║   [0]  Volver al menu principal              ║");
            System.out.println("  ║                                              ║");
            System.out.println("  ╚══════════════════════════════════════════════╝");
            System.out.println(RESET);
            try {
                System.out.print(BOLD + "  Seleccione: " + RESET);
                opcion = Integer.parseInt(scanner.nextLine().trim());
                switch (opcion) {
                    case 1: opcionRegistrarSolicitud(); pausar(); break;
                    case 2: solicitudes.mostrarSolicitudes(); pausar(); break;
                    case 3: solicitudes.consultarProximaSolicitud(); pausar(); break;
                    case 4: opcionAtenderSolicitud(); pausar(); break;
                    case 0: break;
                    default:
                        System.out.println(RED + "  [!] Opcion no valida." + RESET);
                        pausar();
                }
            } catch (NumberFormatException e) {
                System.out.println(RED + "  [!] Error: ingrese un numero entero." + RESET);
                pausar();
            }
        } while (opcion != 0);
    }

    // ════════════════════════════════════════════════════════════
    //  SUBMENU HISTORIAL
    // ════════════════════════════════════════════════════════════

    private static void menuHistorial() {
        int opcion = -1;
        do {
            limpiarPantalla();
            System.out.println(BOLD + BLUE);
            System.out.println("  ╔══════════════════════════════════════════════╗");
            System.out.println("  ║           HISTORIAL DE ACCIONES              ║");
            System.out.println("  ╠══════════════════════════════════════════════╣");
            System.out.println("  ║                                              ║");
            System.out.println("  ║   [1]  Ver historial completo                ║");
            System.out.println("  ║   [2]  Consultar ultima accion               ║");
            System.out.println("  ║   [3]  Deshacer ultima accion                ║");
            System.out.println("  ║        (solicitud atendida -> vuelve a cola) ║");
            System.out.println("  ║                                              ║");
            System.out.println("  ║   [0]  Volver al menu principal              ║");
            System.out.println("  ║                                              ║");
            System.out.println("  ╚══════════════════════════════════════════════╝");
            System.out.println(RESET);
            try {
                System.out.print(BOLD + "  Seleccione: " + RESET);
                opcion = Integer.parseInt(scanner.nextLine().trim());
                switch (opcion) {
                    case 1: historial.mostrarHistorial(); pausar(); break;
                    case 2: historial.consultarUltimaAccion(); pausar(); break;
                    case 3: opcionDeshacerAccion(); pausar(); break;
                    case 0: break;
                    default:
                        System.out.println(RED + "  [!] Opcion no valida." + RESET);
                        pausar();
                }
            } catch (NumberFormatException e) {
                System.out.println(RED + "  [!] Error: ingrese un numero entero." + RESET);
                pausar();
            }
        } while (opcion != 0);
    }

    // ════════════════════════════════════════════════════════════
    //  OPERACIONES INDIVIDUALES
    // ════════════════════════════════════════════════════════════

    private static void opcionAgregarVideojuego() {
        System.out.println(BOLD + "\n  -- AGREGAR VIDEOJUEGO --" + RESET);
        try {
            System.out.print("  Nombre     : ");
            String nombre = scanner.nextLine().trim();
            if (nombre.isEmpty()) { System.out.println(RED + "  [!] El nombre no puede estar vacio." + RESET); return; }

            System.out.print("  Genero     : ");
            String genero = scanner.nextLine().trim();

            System.out.print("  Plataforma : ");
            String plataforma = scanner.nextLine().trim();

            System.out.print("  Precio     : $");
            double precio = Double.parseDouble(scanner.nextLine().trim());
            if (precio < 0) { System.out.println(RED + "  [!] El precio no puede ser negativo." + RESET); return; }

            Videojuego nuevo = new Videojuego(nombre, genero, plataforma, precio);
            catalogo.agregarVideojuego(nuevo);
            historial.registrarAccion(new Accion(Accion.JUEGO_AGREGADO,
                    "Videojuego agregado: " + nombre, nuevo));

        } catch (NumberFormatException e) {
            System.out.println(RED + "  [!] Precio invalido. Use formato numerico (ej: 29.99)." + RESET);
        }
    }

    private static void opcionEliminarVideojuego() {
        System.out.println(BOLD + "\n  -- ELIMINAR VIDEOJUEGO --" + RESET);
        catalogo.mostrarCatalogo();
        if (catalogo.getTamanio() == 0) return;
        System.out.print("  Ingrese el nombre del videojuego a eliminar: ");
        String nombre = scanner.nextLine().trim();
        Videojuego eliminado = catalogo.eliminarVideojuego(nombre);
        if (eliminado != null) {
            historial.registrarAccion(new Accion(Accion.JUEGO_ELIMINADO,
                    "Videojuego eliminado: " + nombre, eliminado));
        }
    }

    private static void opcionRegistrarSolicitud() {
        System.out.println(BOLD + "\n  -- REGISTRAR SOLICITUD --" + RESET);
        System.out.print("  Nombre del jugador : ");
        String jugador = scanner.nextLine().trim();
        if (jugador.isEmpty()) { System.out.println(RED + "  [!] El nombre no puede estar vacio." + RESET); return; }

        System.out.print("  Motivo             : ");
        String motivo = scanner.nextLine().trim();

        SolicitudJugador nueva = new SolicitudJugador(jugador, motivo);
        solicitudes.registrarSolicitud(nueva);
        historial.registrarAccion(new Accion(Accion.SOLICITUD_REGISTRADA,
                "Solicitud registrada: " + jugador, nueva));
    }

    private static void opcionAtenderSolicitud() {
        System.out.println(BOLD + "\n  -- ATENDER SOLICITUD --" + RESET);
        SolicitudJugador atendida = solicitudes.atenderSolicitud();
        if (atendida != null) {
            historial.registrarAccion(new Accion(Accion.SOLICITUD_ATENDIDA,
                    "Solicitud atendida: " + atendida.getNombreJugador(), atendida));
        }
    }

    /**
     * Deshace la última acción del historial con efecto real sobre el sistema:
     * - Si fue "Solicitud atendida" → la solicitud vuelve al FRENTE de la cola (PENDIENTE).
     * - Otros tipos: se retira del historial e informa al usuario.
     */
    private static void opcionDeshacerAccion() {
        System.out.println(BOLD + "\n  -- DESHACER ULTIMA ACCION --" + RESET);
        Accion accion = historial.deshacerUltimaAccion();
        if (accion == null) return;

        switch (accion.getTipo()) {
            case Accion.SOLICITUD_ATENDIDA:
                SolicitudJugador sol = (SolicitudJugador) accion.getObjeto();
                solicitudes.reinsertarAlFrente(sol);
                System.out.println(GREEN
                        + "\n  >> La solicitud de '" + sol.getNombreJugador()
                        + "' volvio al estado PENDIENTE (frente de la cola)." + RESET);
                break;

            case Accion.JUEGO_ELIMINADO:
                Videojuego juego = (Videojuego) accion.getObjeto();
                catalogo.agregarVideojuego(juego);
                System.out.println(GREEN
                        + "\n  >> El videojuego '" + juego.getNombre()
                        + "' fue restaurado en el catalogo." + RESET);
                break;

            default:
                System.out.println(YELLOW
                        + "\n  >> Accion retirada del historial: " + accion.getDescripcion() + RESET);
                break;
        }
    }

    // ════════════════════════════════════════════════════════════
    //  UTILIDADES
    // ════════════════════════════════════════════════════════════

    private static void limpiarPantalla() {
        // Funciona en terminales reales; en IDEs imprime líneas en blanco
        System.out.print("\033[H\033[2J");
        System.out.flush();
    }

    private static void pausar() {
        System.out.println(BOLD + "\n  Presione ENTER para continuar..." + RESET);
        scanner.nextLine();
    }
}
