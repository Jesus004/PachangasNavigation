package com.txus.pachangasnavigation.models

data class Partida constructor (

    val usuario:String,
    val deporte: String,
    val numJug:String,
    val fecha: String,
    val hora:String,
    val lugar: String,
    val completa:Boolean,
    ) {


   /* constructor(

       deporte: String,
       numJug: String,
       fecha: String,
       hora: String,
       lugar: String,
       ldld:Int

    ) :
            this(
                deporte, numJug, fecha, hora, lugar
            )*/

    constructor() : this("","","","","","",false)

    override fun toString(): String {
        return "Partida(deporte='$deporte', numJug='$numJug', fecha='$fecha', hora='$hora', lugar='$lugar')"
    }
}








