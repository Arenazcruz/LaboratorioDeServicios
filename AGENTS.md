# Flujo de trabajo

Respetar la estructura de ramas definida por el usuario:

- `master` es la rama predeterminada y estable.
- `main` parte de `master`; `crud` parte de `main`.
- `editar`, `eliminar` y `reportes` parten de `crud`.
- `logico` y `fisico` parten de `eliminar`.
- Trabajar cada funcionalidad en su rama correspondiente e integrar los cambios hacia su rama superior mediante pull requests.
- No renombrar estas ramas ni cambiar la rama predeterminada sin una nueva indicación del usuario.
