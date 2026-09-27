# Hollow Memory — NetBeans 24

Hollow Memory es un juego de memoria desarrollado en JavaFX e inspirado en el universo de *Hollow Knight*. Este repositorio conserva el proyecto original y presenta una versión reparada y modernizada para poder abrirla, compilarla y ejecutarla con herramientas actuales.

## Versiones incluidas

### Proyecto original de 2023

El proyecto original se conserva, sin reemplazar, dentro de la carpeta:

`Original 2023/Hollow Memory/`

Esta carpeta sirve como respaldo histórico del proyecto tal como fue desarrollado en 2023. Es posible que esa versión necesite configuraciones específicas del IDE, del JDK o del SDK de JavaFX para funcionar correctamente. La versión recomendada para ejecutar es la versión reparada ubicada en la raíz del repositorio.

### Versión actual reparada

El contenido principal de este repositorio corresponde a la reparación y actualización realizada para trabajar con NetBeans 24 y con los entornos modernos de Java utilizados durante 2025 y 2026.

La versión actual utiliza:

- Java 21.
- JavaFX 21.0.2.
- Apache Maven.
- NetBeans 24.

La actualización reorganiza el proyecto con la estructura estándar de Maven, administra JavaFX mediante dependencias y adapta la carga de recursos para facilitar su ejecución en instalaciones modernas.

## Características

- Juego de memoria con temática de *Hollow Knight*.
- Partidas de jugador contra jugador o jugador contra computadora.
- Diferentes niveles de dificultad.
- Imágenes, música, sonidos y voces.
- Registro y revisión de partidas.
- Opciones especiales de bendición y maldición.

## Requisitos

Para ejecutar la versión reparada se recomienda tener instalado:

- JDK 21 o una versión compatible.
- Apache NetBeans 24 o posterior.
- Maven, incluido normalmente con NetBeans.

No es necesario descargar JavaFX por separado: Maven obtiene las dependencias declaradas en `pom.xml`.

## Ejecutar en NetBeans

1. Abre NetBeans.
2. Selecciona **File > Open Project**.
3. Elige la carpeta raíz de este repositorio.
4. Espera a que Maven descargue las dependencias.
5. Ejecuta el proyecto con **Run Project**.

La clase principal es `hollowmemory.HollowMemory`.

## Ejecutar desde una terminal

Desde la carpeta raíz del proyecto, ejecuta:

```bash
mvn clean javafx:run
```

## Estructura principal

```text
.
├── Original 2023/       # Respaldo del proyecto original de 2023
├── src/main/java/       # Código fuente de la versión reparada
├── src/main/resources/  # FXML, estilos, imágenes, sonidos y demás recursos
├── nbactions.xml        # Acción de ejecución utilizada por NetBeans
└── pom.xml              # Configuración Maven y dependencias
```

## Nota

Este repositorio tiene fines educativos y de preservación del proyecto. *Hollow Knight* y sus personajes pertenecen a sus respectivos propietarios.
