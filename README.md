# 🎮 GameHub — Sistema de Gestión de Videojuegos

> Proyecto universitario desarrollado en Java para el curso **ICIL030-11 Programación Orientada al Objeto**  
> Universidad Gabriela Mistral — Docente: Dr. Mauricio Hidalgo

---

## 📋 Descripción

**GameHub** es un sistema de consola que permite administrar una plataforma de videojuegos. Gestiona un catálogo de juegos, una cola de solicitudes de jugadores y un historial de acciones del sistema, todo mediante estructuras dinámicas de datos implementadas en Java puro.

---

## 🏗️ Estructura del Proyecto

```
GameHub/
├── Videojuego.java          # Clase modelo: nombre, género, plataforma, precio
├── SolicitudJugador.java    # Clase modelo: jugador y motivo de solicitud
├── Accion.java              # Clase modelo: tipo de acción + objeto para deshacer
├── CatalogoManager.java     # Gestión del catálogo con ArrayList
├── SolicitudesManager.java  # Gestión de solicitudes con Queue (Cola FIFO)
├── HistorialManager.java    # Gestión del historial con Stack (Pila LIFO)
└── Main.java                # Menú interactivo principal con submenús
```

---

## 🧩 Estructuras de Datos Utilizadas

| Módulo | Estructura | Tipo | Justificación |
|---|---|---|---|
| `CatalogoManager` | `ArrayList<Videojuego>` | Dinámica | Permite agregar, recorrer y eliminar juegos manteniendo el orden de inserción |
| `SolicitudesManager` | `Queue<SolicitudJugador>` (LinkedList) | FIFO | El primer jugador en llegar es el primero en ser atendido |
| `HistorialManager` | `Stack<Accion>` | LIFO | La acción más reciente es la primera en consultarse o deshacerse |

---

## ✨ Funcionalidades

### 🗂️ Catálogo de Videojuegos
- Agregar videojuegos con nombre, género, plataforma y precio
- Mostrar el catálogo respetando el orden de ingreso
- Eliminar un videojuego por nombre

### 📬 Solicitudes de Jugadores
- Registrar solicitudes en cola (orden de llegada)
- Consultar la próxima solicitud **sin eliminarla** (`peek`)
- Atender y retirar la solicitud más antigua (`poll`)

### 🕐 Historial de Acciones
- Registrar automáticamente cada acción del sistema
- Consultar la última acción **sin eliminarla** (`peek`)
- **Deshacer** la última acción con efecto real sobre el sistema:
  - Si fue *"solicitud atendida"* → la solicitud **vuelve al frente de la cola** (estado PENDIENTE)
  - Si fue *"videojuego eliminado"* → el juego **vuelve al catálogo**

---

## 🖥️ Cómo Ejecutar

### Requisitos
- Java JDK 8 o superior
- Terminal con soporte UTF-8 (para los caracteres del menú)

### Compilar

```bash
javac *.java
```

### Ejecutar

```bash
java Main
```

---

## 📸 Vista del Sistema

```
  ╔══════════════════════════════════════════════╗
  ║            GAMEHUB  -  MENU PRINCIPAL        ║
  ╠══════════════════════════════════════════════╣
  ║                                              ║
  ║   [1]  Catalogo de Videojuegos               ║
  ║         Agregar, ver y eliminar juegos        ║
  ║                                              ║
  ║   [2]  Solicitudes de Jugadores              ║
  ║         Registrar, consultar y atender        ║
  ║                                              ║
  ║   [3]  Historial de Acciones                 ║
  ║         Ver y deshacer acciones               ║
  ║                                              ║
  ║   [0]  Salir del sistema                     ║
  ╚══════════════════════════════════════════════╝
```

---

## 🔁 Lógica de Deshacer (Undo)

La clase `Accion` almacena no solo la descripción textual sino también el **objeto involucrado**, lo que permite revertir acciones con efecto real:

```java
// Al deshacer una solicitud atendida:
if (accion.getTipo().equals(Accion.SOLICITUD_ATENDIDA)) {
    SolicitudJugador sol = (SolicitudJugador) accion.getObjeto();
    solicitudes.reinsertarAlFrente(sol); // vuelve al frente de la cola
}
```

---

## 🛡️ Manejo de Errores

- `try-catch` en toda entrada numérica (`precio`, opciones de menú)
- Validación de campos vacíos antes de crear objetos
- Truncado automático de texto largo en los cuadros de visualización (`...`)
- Mensajes de error claros con indicador `[!]`

---

## 📐 Convención Visual de Cuadros

Todos los cuadros del sistema usan **46 caracteres de ancho visual** con la siguiente regla:

```
"  ╔" + 42×═ + "╗"          →  46 chars (borde)
"  ║ " + campo(40) + " ║"   →  46 chars (contenido)

Regla: campo_printf = cantidad_de_═ − 2
```

---

## 📚 Conceptos Aplicados

- Programación Orientada al Objeto (clases, encapsulamiento, métodos)
- Estructuras dinámicas de datos: `ArrayList`, `Queue`, `Stack`
- Manejo de errores con `try-catch`
- Entrada de datos con `Scanner`
- Organización modular del código

---

## 👤 Autor

Proyecto desarrollado como Control 3 del curso ICIL030-11  
Carrera de Ingeniería Civil en Informática — Universidad Gabriela Mistral
