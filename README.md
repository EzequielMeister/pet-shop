# pet-shop


# Final de la materia Taller de Programación 3

# Sobre el proyecto realizado como "TP_FINAL" se debe realizar nuevas Funcionalidades (Feature)
* 1. Agregar Firebase Authentication (Reemplazar el Login Hardcodeado)
* 2. Agregar Firestore Database para persistir el Carrito de compra únicamente cuando hace un Checkout.

# Resolucion:
1) Para agregar Firebase Authentication y reemplazar el login hardcodeado, se siguieron los siguientes pasos:
   - Se creó un proyecto en Firebase Console.
   - Se configuró la autenticación por email y contraseña.
   - Se modificó el código de las vistas de login, registro y el view model para poder impactar los cambios.

2. Para agregar Firestore Database y persistir el carrito de compra, se siguieron los siguientes pasos:
   - Se creó una colección en Firestore para almacenar los carritos de compra (usando la misma consola de Firebase del item anterior).
   - Se modificó el código del ViewModel para guardar y recuperar el carrito de compra desde Firestore.
   - Se implementó la lógica para guardar el carrito de compra solo cuando se realiza un checkout.
   
   
## 📱 Cómo correr la app
- Abre Android Studio.
- Importa el proyecto clonado.
- Tener un emulador o dispositivo conectado.
- Ejecuta la aplicación.

# Observaciones y conclusiones:
* Al utilizar Firebase Authentication en lugar de un sistema de login hardcodeado, podemos concluir lo siguiente:

1) Mejor experiencia de usuario: la autenticación es rápida, segura y confiable, mejorando la interacción del usuario con la aplicación.

2) Mayor seguridad: firebase mantiene actualizado el sistema de autenticación, lo que reduce riesgos de vulnerabilidades.
Evita que tengamos que desarrollar y mantener nuestra propia lógica de seguridad (hashing de contraseñas, tokens, etc.).

3) Soporte flexible: desde la consola de Firebase se puede cambiar el método de login (email, Google, teléfono, etc.) sin necesidad de modificar el código fuente.

4) Alta escalabilidad: está preparado para manejar desde pocos usuarios hasta millones, sin necesidad de modificar la infraestructura.

5) Menor mantenimiento: gran parte de la lógica de autenticación ya está resuelta, lo que reduce el tiempo y esfuerzo de desarrollo y mantenimiento a largo plazo.


* Respecto de lo realizado con Firestore Database para persistir el carrito de compra, se pueden concluir los siguientes puntos:

1) Persistencia de datos: permite que el carrito de compra se guarde de forma permanente, incluso si la aplicación se cierra o el dispositivo se reinicia.

2) Sincronización en tiempo real: Firestore permite que los datos se sincronicen automáticamente entre diferentes dispositivos.

3) Escalabilidad: debido a que permite manejar grandes volumenes de datos y usuarios.

4) Seguridad: proporciona reglas de seguridad que permiten controlar el acceso a los datos, lo que ayuda a proteger la información del usuario.

5) Facilidad de uso: nos ofrece una API sencilla y fácil de usar, lo que facilita la integración con la aplicación y reduce el tiempo de desarrollo.


# Bibliografia y links útiles:
Link del repositorio: https://github.com/EzequielMeister/pet-shop.git
Link console: https://console.firebase.google.com/u/0/project/com-example-tp3petshop/overview
Documentacion Firebase Auth: https://firebase.google.com/docs/auth/android/start
Documentacion Firebase Firestore: https://firebase.google.com/docs/firestore/quickstart


# Integrantes:
* Benjamin Francisco
* Ezequiel Meister






