# pet-shop


# Final de la materia Taller de Programación 3

# Sobre el proyecto realizado como "TP_FINAL" se debe realizar nuevas Funcionalidades (Feature)
* 1. Agregar Firebase Authentication (Reemplazar el Login Hardcodeado)
* 2. Agregar Firestore Database para persistir el Carrito de compra únicamente cuando hace un Checkout.

# Resolucion:
1. Para agregar la autenticación de Firebase, se debe crear un proyecto en Firebase Console y
   agregar la configuración necesaria en el proyecto de Android.

2. Para agregar Firestore Database..


## 📱 Cómo correr la app
- Abre Android Studio.
- Importa el proyecto clonado.
- Asegúrate de tener un emulador o dispositivo conectado.
- Ejecuta la aplicación.
-

# Observaciones y conclusiones:
* Al utilizar Firebase Authentication en lugar de un sistema de login hardcodeado, se obtienen múltiples beneficios:
1) Mejor experiencia de usuario: la autenticación es rápida, segura y confiable, mejorando la interacción del usuario con la aplicación.

2) Mayor seguridad: firebase mantiene actualizado el sistema de autenticación, lo que reduce riesgos de vulnerabilidades.
Evita que tengamos que desarrollar y mantener nuestra propia lógica de seguridad (hashing de contraseñas, tokens, etc.).

3) Soporte flexible: desde la consola de Firebase se puede cambiar el método de login (email, Google, teléfono, etc.) sin necesidad de modificar el código fuente.

4) Alta escalabilidad: está preparado para manejar desde pocos usuarios hasta millones, sin necesidad de modificar la infraestructura.

5) Menor mantenimiento: gran parte de la lógica de autenticación ya está resuelta, lo que reduce el tiempo y esfuerzo de desarrollo y mantenimiento a largo plazo.

# Bibliografia y links útiles:
Link del repositorio: https://github.com/EzequielMeister/pet-shop.git
Link console: https://console.firebase.google.com/u/0/project/my-aw-1748b/authentication/users
Documentacion Firebase Auth: https://firebase.google.com/docs/auth/android/start
Documentacion Firebase Firestore: https://firebase.google.com/docs/firestore/quickstart


# Integrantes:
* Benjamin Francisco
* Ezequiel Meister

--------------------------------------------------------------------------------------------------

# 🐶 Pet-Shop

Aplicación Android desarrollada en Kotlin que permite a los usuarios autenticarse (es un mock),
explorar productos y gestionar su carrito de compras. Se conecta a una API REST pública y utiliza 
buenas prácticas como arquitectura limpia, inyección de dependencias y almacenamiento local.

---

## 🚀 Funcionalidades

- 🔐 **Login de usuario** (API: `https://dummyjson.com/auth/login`)
- 🛍️ **Listado de productos**
- - 🛍️ **Listado de favoritos**
- 🧺 **Carrito de compras**
- 🔄 **Consumo de API REST con Retrofit**
- 💾 **Persistencia local con Room (para los favoritos)**
- 💉 **Inyección de dependencias con Hilt**

---

## 🛠️ Tecnologías utilizadas

- **Kotlin**
- **Retrofit** – para consumo de APIs REST
- **Room** – base de datos local
- **Hilt** – inyección de dependencias
- **ViewModel StateFlow** 
- **Coroutines** – para llamadas asincrónicas

---

## 🔗 API utilizada

La app utiliza [DummyJSON](https://dummyjson.com/) como backend falso para simular funcionalidades reales:

- Autenticación: `/auth/login`
- Carrito de compras: `/carts/1`
- Productos: `/products`

---

## 📱 Cómo correr la app

1. Cloná el repositorio:
   ```bash
   git clone https://github.com/Andressubero/pet-shop.git
   
----------------------------------------------------------------------------------------------






