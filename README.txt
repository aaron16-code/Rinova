
INFRAESTRUCTURA
---------------
  eureka-server    → http://localhost:8761
  config-server    → http://localhost:8888
  api-gateway      → http://localhost:8090


=========================================================
  autor-service   · puerto 8084
=========================================================

CRUD
  GET    /api/v1/autores               - listar todos
  GET    /api/v1/autores/{id}          - buscar por id
  POST   /api/v1/autores               - crear autor
  PUT    /api/v1/autores/{id}          - actualizar autor
  DELETE /api/v1/autores/{id}          - eliminar autor

REPORTES
  GET    /api/v1/autores/buscar?nombre=          - buscar por nombre (parcial, ignora mayusculas)
  GET    /api/v1/autores/nacionalidad/{n}        - filtrar por nacionalidad
  GET    /api/v1/autores/anio/{anio}             - autores nacidos en un año exacto
  GET    /api/v1/autores/rango-anio?desde=&hasta= - autores por rango de anos, orden ASC

=========================================================
  libro-service   · puerto 8083
=========================================================

CRUD
  GET    /api/v1/libros                - listar todos
  GET    /api/v1/libros/{id}           - buscar por id
  POST   /api/v1/libros                - crear libro
  PUT    /api/v1/libros/{id}           - actualizar libro
  DELETE /api/v1/libros/{id}           - eliminar libro

REPORTES
  GET    /api/v1/libros/buscar?titulo=           - buscar por titulo (parcial, ignora mayusculas)
  GET    /api/v1/libros/genero/{genero}          - filtrar por genero
  GET    /api/v1/libros/autor/{autorId}          - libros de un autor
  GET    /api/v1/libros/precio?min=&max=         - libros en rango de precio


=========================================================
  usuario-service   · puerto 8085
=========================================================

CRUD
  GET    /api/v1/usuarios              - listar todos
  GET    /api/v1/usuarios/{id}         - buscar por id
  POST   /api/v1/usuarios              - crear usuario
  PUT    /api/v1/usuarios/{id}         - actualizar usuario
  DELETE /api/v1/usuarios/{id}         - eliminar usuario

REPORTES
  GET    /api/v1/usuarios/email/{email}          - busqueda exacta por email
  GET    /api/v1/usuarios/buscar?nombre=         - buscar por nombre (parcial)
  GET    /api/v1/usuarios/rol/{rol}              - usuarios por rol, orden alfabetico ASC


=========================================================
  inventario-service   · puerto 8086
=========================================================

CRUD
  GET    /api/v1/inventario            - listar todos
  GET    /api/v1/inventario/{id}       - buscar por id
  POST   /api/v1/inventario            - crear registro
  PUT    /api/v1/inventario/{id}       - actualizar registro
  DELETE /api/v1/inventario/{id}       - eliminar registro

REPORTES
  GET    /api/v1/inventario/libro/{libroId}      - inventario de un libro especifico
  GET    /api/v1/inventario/bajo-minimo          - libros con stock <= stockMinimo
  GET    /api/v1/inventario/stock/{cantidad}     - libros con stock menor a una cantidad dada


=========================================================
  pedido-service   · puerto 8087
=========================================================

CRUD
  GET    /api/v1/pedidos               - listar todos
  GET    /api/v1/pedidos/{id}          - buscar por id (datos de usuario y libro por Feign)
  POST   /api/v1/pedidos               - crear pedido
  PUT    /api/v1/pedidos/{id}          - actualizar pedido
  DELETE /api/v1/pedidos/{id}          - eliminar pedido

REPORTES
  GET    /api/v1/pedidos/usuario/{usuarioId}     - pedidos de un usuario
  GET    /api/v1/pedidos/estado/{estado}         - pedidos por estado (PENDIENTE / PAGADO / CANCELADO)
  GET    /api/v1/pedidos/libro/{libroId}         - pedidos de un libro
  GET    /api/v1/pedidos/recientes               - todos los pedidos, más reciente primero


=========================================================
  pago-service   · puerto 8088
=========================================================

CRUD
  GET    /api/v1/pagos                 - listar todos
  GET    /api/v1/pagos/{id}            - buscar por id
  POST   /api/v1/pagos                 - registrar pago
  PUT    /api/v1/pagos/{id}            - actualizar pago
  DELETE /api/v1/pagos/{id}            - eliminar pago

REPORTES
  GET    /api/v1/pagos/pedido/{pedidoId}         - pago asociado a un pedido
  GET    /api/v1/pagos/estado/{estado}           - pagos por estado
  GET    /api/v1/pagos/metodo/{metodoPago}       - pagos por metodo (EFECTIVO / TARJETA / etc.)


=========================================================
  resena-service   · puerto 8089
=========================================================

CRUD
  GET    /api/v1/resenas               - listar todas
  GET    /api/v1/resenas/{id}          - buscar por id
  POST   /api/v1/resenas               - crear resena
  PUT    /api/v1/resenas/{id}          - actualizar resena
  DELETE /api/v1/resenas/{id}          - eliminar resena

REPORTES
  GET    /api/v1/resenas/libro/{libroId}         - resenas de un libro
  GET    /api/v1/resenas/usuario/{usuarioId}     - resenas de un usuario
  GET    /api/v1/resenas/puntuacion/{p}          - resenas con puntuacion >= p, orden DESC


=========================================================
  notificacion-service   · puerto 8091
=========================================================

CRUD
  GET    /api/v1/notificaciones        - listar todas
  GET    /api/v1/notificaciones/{id}   - buscar por id
  POST   /api/v1/notificaciones        - crear notificacion
  PUT    /api/v1/notificaciones/{id}   - actualizar notificacion
  DELETE /api/v1/notificaciones/{id}   - eliminar notificación

REPORTES
  GET    /api/v1/notificaciones/usuario/{usuarioId} - notificaciones de un usuario
  GET    /api/v1/notificaciones/leidas/{bool}       - filtrar leidas (true) o no leidas (false)
  GET    /api/v1/notificaciones/tipo/{tipo}          - filtrar por tipo de notificación

=========================================================
