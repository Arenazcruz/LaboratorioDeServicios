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
