package com.txus.pachangasnavigation.adapters

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.txus.pachangasnavigation.R
import com.txus.pachangasnavigation.databinding.ItemPartidaRecyclerViewBinding
import com.txus.pachangasnavigation.models.Partida

class PartidaAdapter (val context: Context, val listener: PartidaAdapterListener): RecyclerView.Adapter<PartidaAdapter.ViewHolder>() {

    val lista = mutableListOf<Partida>()

    fun PartidaAdapter(lista: List<Partida>) {

        this.lista.addAll(lista)
    }

    fun addFav(partida: Partida){
        listener.addFav(partida)
    }
    fun delFav(partida: Partida){
        listener.delFav(partida)
    }


    class ViewHolder(val binding: ItemPartidaRecyclerViewBinding) :
        RecyclerView.ViewHolder(binding.root) {


        fun rellenarDatos(partida: Partida,adapter:PartidaAdapter) {
            binding.tvUsuario.text=partida.usuario
            binding.editTextNumJug.text = partida.numJug
            binding.editTextDeporte.text = partida.deporte
            binding.editTextFecha.text = partida.fecha
            binding.editTextHora.text = partida.hora
            binding.editTextLugar.text = partida.lugar

            if(partida.fav){
                binding.btnCrear.iconTint=adapter.context.getColorStateList(R.color.red)
            }
            else{
                binding.btnCrear.iconTint=adapter.context.getColorStateList(R.color.grey)
            }

            binding.btnCrear.setOnClickListener {

                if (partida.fav){

                    partida.fav=false
                    binding.btnCrear.iconTint=adapter.context.getColorStateList(R.color.grey)
                    adapter.delFav(partida)
                } else {

                    partida.fav=true
                    binding.btnCrear.iconTint=adapter.context.getColorStateList(R.color.red)
                    adapter.addFav(partida)
                }
            }

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
        holder.rellenarDatos(lista[position],this )
    }

    override fun getItemCount(): Int {
        return lista.size
    }

    interface PartidaAdapterListener{

        fun addFav(partida: Partida)
        fun delFav(partida: Partida)
    }
}