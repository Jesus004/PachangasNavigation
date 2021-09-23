package com.txus.pachangasnavigation.viewmodel

import android.provider.Telephony
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.QueryDocumentSnapshot
import com.google.firebase.firestore.ktx.toObject
import com.google.firebase.firestore.ktx.toObjects
import com.txus.pachangasnavigation.App
import com.txus.pachangasnavigation.models.Partida
import com.txus.pachangasnavigation.utils.Constantes

class PartidasViewModel : ViewModel() {

    private val firestore= App.getFirestore()
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
               /* val list = mutableListOf<Partida>()
                for (document in documentos) {

                    val partida=document.toObject<Partida>()
                    partida.id=document.id
                    list.add(partida)
                }*/

                val lista=documentos.toObjects<Partida>()
                _partidas.value=lista
            }
    }
}



