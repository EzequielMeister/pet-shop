package com.example.tp3_petshop.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import kotlinx.coroutines.tasks.await

class SessionViewModel : ViewModel() {
    private val _userId = MutableStateFlow<Int?>(null)
    val userId: StateFlow<Int?> = _userId

    init {
        // mapeamos el UID de Firebase al userId num y lo usamos por toda la app
        val user = FirebaseAuth.getInstance().currentUser
        val uid = user?.uid // Guardamos el uid del usuario autenticado Auth
        println("UID de Firebase: $uid")
        println(user)
        if (uid != null) {
            viewModelScope.launch {
                try {
                    val doc = Firebase.firestore
                        .collection("user_mappings")
                        .document(uid)
                        .get()
                        .await()
                    val backendId = doc.getLong("userId")?.toInt()

                    // Extraemos userID y actualizamos el flow
                    _userId.value = backendId
                } catch (e: Exception) {
                    println("Error obteniendo userId de Firestore: ${e.message}")
                }
            }
        }
    }
}