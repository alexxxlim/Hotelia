# Hotelia - README.md

README: 

# 🏨 Hotelia

Hotelia es una aplicación de gestión hotelera que permite a huéspedes, recepcionistas y gerentes
interactuar con el sistema de reservas, habitaciones y usuarios. Incluye GUI, datos desde archivos
y documentación JavaDoc completa.

  ---

## 📌 Descripción del Proyecto

Hotelia permite gestionar una cadena hotelera de forma eficiente:
- 👤 Huésped: reservas, historial, reseñas, VIP
- 🧑‍💼 Recepcionista: reservas activas, asistencia
- 🧑‍💼 Gerente: habitaciones, métricas, usuarios

  ---

## 📂 Estructura de Carpetas

Hotelia/

.idea/                # Configuración de IntelliJ

data/

    app/               # Carpeta auxiliar
    hotel/             # Carpeta auxiliar
    fondo.png          # Imagen GUI
    logo.png           # Logotipo
    gerentes.txt       # Lista de gerentes
    habitaciones.txt   # Habitaciones
    huespedes.txt      # Huéspedes
    recepcionistas.txt # Recepcionistas
    reservas.txt       # Reservas

doc/                 # JavaDoc generado

lib/                 # Librerías externas

out/                 # Archivos compilados

src/

    control/           # Controladores
    model/             # Clases de dominio
    view/              # Interfaces Swing

.gitignore           # Git

Hotelia.iml          # Config IntelliJ

README.md            # !!!Este archivo!!!

  ---

## 📄 Archivos de Datos (`/data`)

huespedes.txt:
- id;idVIP;nombre;correo;contrasena;esSocioVIP
- 1;101;Luis;luis@mail.com;1234;false

gerentes.txt:
- id;nombre;correo;contrasena
- 2;Ana;ana@hotel.com;admin

recepcionistas.txt:
- id;nombre;correo;contrasena
- 3;Pedro;pedro@hotel.com;rec

habitaciones.txt:
- numero;tipo;precio;esDisponible
- 1;Estándar;50.0;true
- 2;VIP;120.0;true
- 3;Suite;200.0;true

  ---

## 🚀 Cómo Ejecutar

Requisitos:
- Java 17 o superior
- IntelliJ IDEA

Pasos:
- Clona el repositorio
- Abre el proyecto en IntelliJ
- Ejecuta: control.MainGUI

  ---

## 👥 Roles de Usuario

Huésped:
- Ver habitaciones
- Hacer reservas
- Ver historial
- Dejar reseñas
- Pagar VIP

Recepcionista:
- Ver reservas
- Buscar habitaciones
- Asistencia a clientes

Gerente:
- Crear/eliminar habitaciones
- Ver ingresos
- Ver ratings

  ---

## 📚 Documentación JavaDoc

- Local: /doc/javadoc/index.html
- Idioma: español
- Contenido: clases, métodos, atributos, lógica
- Se puede subir a GitHub Pages u otro hosting

  ---

## ✅ Funcionalidades Incluidas

- Login por rol
- Validación de disponibilidad
- Historial de reservas
- Reseñas y ratings
- Precios con descuento VIP
- Asistencia recepcionista
- Datos cargados desde .txt

  ---

## 🔧 Funcionalidades Planeadas

- Filtro de fechas
- Panel de estadísticas
- Integración con base de datos
- Mensajes de asistencia personalizados

  ---

## 👨‍💼 Autores

- Aleksei Limin
- Lucas Henrique Rangel Resende
- Miguel Márquez Martínez
- Ricardo Recio Villodre
- Gabriel Jiménez Bustos

Universidad Europea de Madrid — Curso 2024–2025