package com.txus.pachangasnavigation.viewmodel

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.google.firebase.firestore.FieldValue
import com.google.firebase.firestore.ktx.toObject
import com.txus.pachangasnavigation.App
import com.txus.pachangasnavigation.models.Partida
import com.txus.pachangasnavigation.utils.Constantes

class MisPartidasViewModel : ViewModel() {

    private val firestore = App.getFirestore()
    private val auth = App.getAuth()

    fun addPartida(partida: Partida) {

        firestore.collection(Constantes.USUARIOS).document(auth.currentUser!!.uid)
            .update(Constantes.MISPARTIDAS, FieldValue.arrayUnion(partida.id))

        firestore.collection(Constantes.PARTIDAS).document(partida.id)
            .update(
                "nommbresUsuariosApuntados",
                FieldValue.arrayUnion(auth.currentUser!!.displayName)
            )


        firestore.collection(Constantes.PARTIDAS).document(partida.id)
            .update(
                "usuariosApuntados",
                FieldValue.increment(1)


            )



        firestore.collection(Constantes.PARTIDAS).document(partida.id).get().addOnSuccessListener {

            val jugApuntados = it.get("usuariosApuntados") as Long
            val jugTotal = it.get("numJug") as Long


            if (jugApuntados == jugTotal) {

                partida.completa = true
                firestore.collection(Constantes.PARTIDAS).document(partida.id)
                    .update("partidaCompleta", partida.completa)


            }

            Log.d("Partida completa", "${partida.completa}  ${partida.deporte} ")
        }


    }


    fun delPartida(partida: Partida) {
        partida.completa = false
        firestore.collection(Constantes.PARTIDAS).document(partida.id)
            .update("partidaCompleta", partida.completa)

        firestore.collection(Constantes.USUARIOS).document(auth.currentUser!!.uid)
            .update(Constantes.MISPARTIDAS, FieldValue.arrayRemove(partida.id))

        firestore.collection(Constantes.PARTIDAS).document(partida.id)
            .update(
                "nommbresUsuariosApuntados",
                FieldValue.arrayRemove(auth.currentUser!!.displayName)
            )
        firestore.collection(Constantes.PARTIDAS).document(partida.id)
            .update(
                "usuariosApuntados",
                FieldValue.increment(-1)
            )
        Log.d("Datos doc", "   ${partida.completa}  ${partida.deporte}")


    }


    fun findAllPartidas(): LiveData<Partida?> {

        val liveData = MutableLiveData<Partida?>()

        firestore.collection(Constantes.USUARIOS).document(App.getAuth().currentUser!!.uid)
            .get().addOnSuccessListener { usuario ->


                val favs = usuario.data?.get(Constantes.MISPARTIDAS)

                if (favs != null) {

                    for (fav in favs as ArrayList<String>) {

                        firestore.collection(Constantes.PARTIDAS).document(fav).get()
                            .addOnSuccessListener {
                                liveData.value = it.toObject<Partida>()!!
                            }

                    }

                } else {

                    liveData.value = null


                }


            }




        return liveData
    }

}
