package com.txus.pachangasnavigation.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.txus.pachangasnavigation.databinding.ItemPartidaRecyclerViewBinding
import com.txus.pachangasnavigation.models.Partida
import com.txus.pachangasnavigation.models.Usuario
import com.txus.pachangasnavigation.viewmodel.UsuarioViewModel

class RecyclerViewAdapter : RecyclerView.Adapter<RecyclerViewAdapter.ViewHolder>() {

    val lista = mutableListOf<Partida>()

    fun PartidaAdapter(lista: List<Partida>) {

        this.lista.addAll(lista)
    }


    class ViewHolder(val binding: ItemPartidaRecyclerViewBinding) :
        RecyclerView.ViewHolder(binding.root) {


        fun rellenarDatos(partida: Partida) {

            binding.tvUsuario.text=partida.nombreUsuario


            binding.editTextDeporte.text = partida.deporte
            binding.editTextNumJug.text = partida.numJug.toString()
            binding.editTextFecha.text = partida.fecha
            // binding.editTextHora.text = partida.hora.toString()
            binding.editTextLugar.text = partida.lugar
        }

        companion object {

            fun crearViewHolder(parent: ViewGroup): ViewHolder {
                val layoutInflater = LayoutInflater.from(parent.context)
                val binding = ItemPartidaRecyclerViewBinding.inflate(layoutInflater, parent, false)
                return ViewHolder(binding)
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {

        return ViewHolder.crearViewHolder(parent)
    }


    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.rellenarDatos(lista[position])
    }

    override fun getItemCount(): Int {
        return lista.size
    }
}