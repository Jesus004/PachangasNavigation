package com.txus.pachangasnavigation.models

import java.util.*

data class Usuario (
    var nombre:String,
    val email:String,
    val edad: Int,
    var imageUrl:String,
    val createAt:Date,
    val updateAt: Date
){
    constructor(): this("", "", 0, "", Date(), Date())

    override fun toString(): String {
        return "Usuario(nombre='$nombre', email='$email', edad=$edad, imageUrl='$imageUrl', createAt=$createAt, updateAt=$updateAt)"
    }


}