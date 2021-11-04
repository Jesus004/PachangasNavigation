package com.txus.pachangasnavigation.viewmodel

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.google.firebase.firestore.ktx.toObject
import com.txus.pachangasnavigation.App
import com.txus.pachangasnavigation.models.Partida
import com.txus.pachangasnavigation.utils.Constantes
import java.util.stream.Collectors

class PartidasViewModel : ViewModel() {

    private val firestore = App.getFirestore()
    private val _partidas: MutableLiveData<List<Partida>> by lazy {
        MutableLiveData<List<Partida>>().also {
            loadPartidas()
        }
    }


    fun getPartidas(): LiveData<List<Partida>> {
        return _partidas
    }

    private fun loadPartidas() {

        firestore.collection(Constantes.PARTIDAS)
            .get()
            .addOnSuccessListener { documentos ->
                var list = mutableListOf<Partida>()
                for (document in documentos) {
                    val partida = document.toObject<Partida>()
                    partida.id = document.id
                    list.add(partida)
                }

                firestore.collection(Constantes.USUARIOS).document(App.getAuth().currentUser!!.uid)
                    .get().addOnSuccessListener { usuario ->

                        val favs = usuario.data?.get(Constantes.MISPARTIDAS)

                        if (favs != null) {

                            for (fav in favs as ArrayList<String>) {

                                list = list.stream().map { p ->
                                    if (p.id == fav) {
                                        p.fav = true

                                    }; p
                                }.collect(
                                    Collectors.toList()
                                )
                            }

                            Log.e("TAG", list.toString())

                            _partidas.value = list


                        } else {

                            _partidas.value = list


                        }


                    }


            }
    }
}



