# Hotelia
 Hotel management Application

**Escenarios de uso MVP V1:**
La aplicación se lanza en la consola. Se solicita al usuario que introduzca su correo electrónico y contraseña. Dependiendo de las credenciales introducidas, se identifica el rol del usuario: huésped, gerente o recepcionista. Después del inicio de sesión exitoso, se muestra el menú correspondiente al rol.

Para el huésped (correo: luis@mail.com, contraseña: 1234), el menú incluye: ver habitaciones disponibles, hacer una reserva, ver historial de reservas y salir. Al hacer una reserva, el sistema solicita el número de la habitación y las fechas de inicio y fin. Si la habitación está disponible, se confirma la reserva.

Para el gerente (correo: ana@hotel.com, contraseña: admin), el menú permite ver la lista de habitaciones y agregar nuevas habitaciones indicando su número, tipo y precio.

Para el recepcionista (correo: pedro@hotel.com, contraseña: rec), el menú permite ver todas las reservas confirmadas con el nombre del huésped y el número de la habitación.

Flujo de demostración sugerido: iniciar sesión como huésped, consultar las habitaciones, realizar una reserva, verificar el historial personal, iniciar sesión como recepcionista para visualizar la reserva, luego iniciar sesión como gerente para añadir una nueva habitación y finalmente volver a ingresar como huésped para hacer una reserva en la habitación recién creada.