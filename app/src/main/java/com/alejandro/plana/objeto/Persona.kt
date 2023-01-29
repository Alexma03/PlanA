package com.alejandro.plana.objeto

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

}