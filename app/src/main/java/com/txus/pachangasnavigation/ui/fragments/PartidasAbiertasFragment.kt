package com.txus.pachangasnavigation.ui.fragments

import android.content.Context
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.txus.pachangasnavigation.App
import com.txus.pachangasnavigation.adapters.RecyclerViewAdapter
import com.txus.pachangasnavigation.databinding.FragmentPartidasAbiertasBinding
import com.txus.pachangasnavigation.listeners.MainListener
import com.txus.pachangasnavigation.models.Partida
import com.txus.pachangasnavigation.ui.activities.MainActivity
import com.txus.pachangasnavigation.viewmodel.PartidasViewModel

class PartidasAbiertasFragment : Fragment() {
    private var binding: FragmentPartidasAbiertasBinding? = null
    private val model:PartidasViewModel by viewModels()




    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        binding = FragmentPartidasAbiertasBinding.inflate(inflater, container, false)
        val view = binding!!.root


        // Inflate the layout for this fragment

        model.getPartidas().observe(viewLifecycleOwner,{

            createRecyclerView(it)
        })


        return view
    }


    private fun createRecyclerView(partidas: List<Partida>) {


        val madpater = RecyclerViewAdapter()
        madpater.PartidaAdapter(partidas)

        val recyclerView = binding!!.rvPartidas
        recyclerView.apply {
            layoutManager = LinearLayoutManager(requireContext(), RecyclerView.VERTICAL, false)
            adapter = madpater
        }


    }

  /*  private fun cargarPartidas(): List<Partida> {
        val partidas = mutableListOf<Partida>()




        partidas.add(Partida("frontenis", 4, "14-09-2021", 19, "Valcabado"))
        partidas.add(Partida("futbol", 22, "14-09-2021", 19, "Santa Maria"))
        partidas.add(Partida("padel", 4, "14-09-2021", 19, "Valencia de don juan"))
        partidas.add(Partida("pinpong", 2, "14-09-2021", 19, "Valencia de don juan"))
        partidas.add(Partida("frontenis", 4, "14-09-2021", 19, "Pobladura"))
        partidas.add(Partida("padel", 4, "14-09-2021", 19, "Castrogonzalo"))
        partidas.add(Partida("padel", 4, "14-09-2021", 19, "Valencia"))
        partidas.add(Partida("padel", 4, "14-09-2021", 19, "zpotes"))
        partidas.add(Partida("tenis", 4, "14-09-2021", 19, "Valencia de don juan"))
        partidas.add(Partida("frontenis", 4, "14-09-2021", 19, "Valcabado"))
        partidas.add(Partida("frontenis", 4, "14-09-2021", 19, "Valcabado"))

        return partidas

    }*/




}