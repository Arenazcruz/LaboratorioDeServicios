# Laboratorio de Servicios

## Organización de ramas

```text
master (predeterminada)
└── main
    └── crud
        ├── editar
        ├── eliminar
        │   ├── logico
        │   └── fisico
        └── reportes
```

Cada rama de trabajo parte de su rama superior. Los cambios se integran mediante pull requests hacia esa misma rama superior:

| Rama | Integrar en | Propósito |
| --- | --- | --- |
| `main` | `master` | Integración general del proyecto |
| `crud` | `main` | Integración de las funcionalidades CRUD |
| `editar` | `crud` | Edición de registros |
| `eliminar` | `crud` | Integración de las opciones de eliminación |
| `reportes` | `crud` | Generación de reportes |
| `logico` | `eliminar` | Eliminación lógica de registros |
| `fisico` | `eliminar` | Eliminación física de registros |

`master` conserva la versión estable y es la rama predeterminada del repositorio.

Git no almacena una relación fija entre ramas padre e hijas. Este árbol define el flujo de trabajo del proyecto; inicialmente todas las ramas comparten el mismo commit.

## Reportes de dispositivos

Consultas trasladadas desde `demoooo`, adaptadas a los paquetes de este proyecto:

| Método | Ruta | Resultado |
| --- | --- | --- |
| GET | `/dispositivos/marca/{marca}` | Dispositivos activos de la marca indicada, sin distinguir mayúsculas y minúsculas. |
| GET | `/dispositivos/desactivados` | Dispositivos desactivados de todas las marcas. |

Ambas rutas devuelven una lista JSON con `id`, `nombre`, `marca`, `tipo` y `precio`; si no hay coincidencias, devuelven `[]` con estado HTTP 200. Ejemplo: `GET /dispositivos/marca/Samsung`.

Los reportes consultan la tabla `dispositivos` de la base de datos configurada. Se incluyen la entidad, el DTO y el repositorio necesarios para estas consultas. Este avance no incorpora altas, edición, eliminación ni reactivación de dispositivos. Con la configuración H2 actual, la tabla comienza vacía y los datos no persisten entre reinicios.

Para ejecutar las pruebas con Java 21: `./gradlew test` (Windows: `.\gradlew.bat test`).
