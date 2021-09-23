package com.txus.pachangasnavigation.models

import java.util.*

data class Partida constructor(


    val deporte: String,
    val numJug: Int,
    val fecha: String,

    val lugar: String,
    val nombreUsuario:String
) {




    constructor(
        usuario: Usuario,
        deporte: String,
        numJug: Int,
        fecha: String,

        lugar: String,
        nombreUsuario: String
    ) :
            this(
                deporte, numJug, fecha,  lugar,nombreUsuario
            )

    constructor() : this("", 0, "",  "","")
}








