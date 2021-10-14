package com.txus.pachangasnavigation.viewmodel

import android.provider.Telephony
import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.google.firebase.firestore.FieldValue
import com.google.firebase.firestore.ktx.toObject
import com.txus.pachangasnavigation.App
import com.txus.pachangasnavigation.models.Partida
import com.txus.pachangasnavigation.utils.Constantes
import java.util.stream.Collectors

class MisPartidasViewModel : ViewModel() {

    private val firestore = App.getFirestore()
    private val auth = App.getAuth()

    fun addPartida(partida: Partida) {

        firestore.collection(Constantes.USUARIOS).document(auth.currentUser!!.uid)
            .update(Constantes.MISPARTIDAS, FieldValue.arrayUnion(partida.id))


    }

    fun delPartida(partida: Partida) {

        firestore.collection(Constantes.USUARIOS).document(auth.currentUser!!.uid)
            .update(Constantes.MISPARTIDAS, FieldValue.arrayRemove(partida.id))


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