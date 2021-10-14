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

    lateinit var id:String
    var fav : Boolean=false


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
        return "Partida(usuario='$usuario', deporte='$deporte', numJug='$numJug', fecha='$fecha', hora='$hora', lugar='$lugar', completa=$completa, id='$id', fav=$fav)"
    }


}








