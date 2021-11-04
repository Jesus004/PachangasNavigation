package com.txus.pachangasnavigation.models

data class Partida constructor(

    val usuario: String,
    val deporte: String,
    val fecha: String,
    val hora: String,
    val lugar: String,
    var numJug: Int,
    var completa: Boolean,
    var usuariosApuntados: Int
) {

    lateinit var id: String
    var fav: Boolean = false


    constructor() : this("", "", "", "", "", 0, false, 0)

    override fun toString(): String {
        return "Partida(usuario='$usuario', deporte='$deporte', fecha='$fecha', hora='$hora', lugar='$lugar', numJug=$numJug, completa=$completa, id='$id', fav=$fav, numJugApuntados=$usuariosApuntados)"
    }


}








