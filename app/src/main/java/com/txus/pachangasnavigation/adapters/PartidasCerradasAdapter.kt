package com.txus.pachangasnavigation.adapters

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.txus.pachangasnavigation.databinding.ItemPartidasCerradasBinding
import com.txus.pachangasnavigation.models.Partida

class PartidasCerradasAdapter :
    RecyclerView.Adapter<PartidasCerradasAdapter.ViewHolder>() {

    val lista = mutableListOf<Partida>()

    fun PartidasCerradasAdapter(lista: List<Partida>) {

        this.lista.addAll(lista)
    }


    class ViewHolder(val binding: ItemPartidasCerradasBinding) :
        RecyclerView.ViewHolder(binding.root) {


        @SuppressLint("SetTextI18n")
        fun rellenarDatos(partida: Partida) {
            binding.tvUsuario.text = partida.usuario
            binding.editTextNumJug.text =
                partida.usuariosApuntados.toString() + "/" + partida.numJug
            binding.editTextDeporte.text = partida.deporte
            binding.editTextFecha.text = partida.fecha
            binding.editTextHora.text = partida.hora
            binding.editTextLugar.text = partida.lugar

        }

        companion object {

            fun crearViewHolder(parent: ViewGroup): ViewHolder {
                val layoutInflater = LayoutInflater.from(parent.context)
                val binding = ItemPartidasCerradasBinding.inflate(layoutInflater, parent, false)
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