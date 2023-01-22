package com.alejandro.plana.objeto

import android.content.Context
import android.provider.Settings.Global.putString
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
import androidx.compose.foundation.rememberSharedPref
import androidx.compose.runtime.Composable

class Persona (val usuario: String){
    var name: String = ""
        get() = field
        set(value) {
            field = value
        }

    var codigoPostal: String = ""
        get() = field
        set(value) {
            field = value
        }

    var email: String = ""
        get() = field
        set(value) {
            field = value
        }

    var password: String = ""
        get() = field
        set(value) {
            field = value
        }

    constructor(name: String, codigoPostal: String, email: String, password:String) : this(name) {
        this.name = name
        this.codigoPostal = codigoPostal
        this.email = email
        this.password = password
    }


    fun Firebase (user: FirebaseUser?){
        val user = FirebaseAuth.getInstance().currentUser
        user?.getIdToken(true)?.addOnCompleteListener { task ->
            if (task.isSuccessful) {
                val idToken = task.result?.token
            }




        }

    }


}