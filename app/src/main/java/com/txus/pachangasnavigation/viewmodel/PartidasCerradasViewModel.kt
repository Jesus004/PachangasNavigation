package com.txus.pachangasnavigation.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.google.firebase.firestore.FieldValue
import com.google.firebase.firestore.ktx.toObject
import com.txus.pachangasnavigation.App
import com.txus.pachangasnavigation.models.Partida
import com.txus.pachangasnavigation.utils.Constantes

class PartidasCerradasViewModel : ViewModel() {

    private val auth = App.getAuth()

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

                    val usuariosApuntados = partida.usuario


                    partida.completaaa = document.getBoolean("partidaCompleta")!!

                    partida.id = document.id

                    if (partida.completaaa) {

                        list.add(partida)
                        _partidas.value = list


                    }


                }


            }
    }




}