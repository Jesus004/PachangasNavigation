package com.txus.pachangasnavigation.ui.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.txus.pachangasnavigation.adapters.PartidaAdapter
import com.txus.pachangasnavigation.databinding.FragmentPartidasAbiertasBinding
import com.txus.pachangasnavigation.models.Partida
import com.txus.pachangasnavigation.viewmodel.MisPartidasViewModel
import com.txus.pachangasnavigation.viewmodel.PartidasViewModel

class PartidasAbiertasFragment : Fragment(),PartidaAdapter.PartidaAdapterListener {
    private var binding: FragmentPartidasAbiertasBinding? = null
    private val viewModel:MisPartidasViewModel by viewModels()
    private val model: PartidasViewModel by viewModels()
    private lateinit var mAdapter: PartidaAdapter


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        binding = FragmentPartidasAbiertasBinding.inflate(inflater, container, false)
        val view = binding!!.root


        // Inflate the layout for this fragment

        mAdapter = PartidaAdapter(requireContext(),this)

        model.getPartidas().observe(viewLifecycleOwner, {


            createRecyclerView(it)


        })


        return view
    }


    private fun createRecyclerView(partidas: List<Partida>) {


        val madpater = PartidaAdapter(requireContext(),this)
        madpater.PartidaAdapter(partidas)

        val recyclerView = binding!!.rvPartidas
        recyclerView.apply {
            layoutManager = LinearLayoutManager(requireContext(), RecyclerView.VERTICAL, false)
            adapter = madpater
        }


    }

    override fun addFav(partida: Partida) {

        viewModel.addPartida(partida)

    }

    override fun delFav(partida: Partida) {

        viewModel.delPartida(partida)

    }
/*

      private fun cargarPartidas(): List<Partida> {
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

      }
*/


}