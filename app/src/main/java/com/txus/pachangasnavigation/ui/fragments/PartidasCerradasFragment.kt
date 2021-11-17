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
import com.google.android.material.snackbar.Snackbar
import com.txus.pachangasnavigation.R
import com.txus.pachangasnavigation.adapters.PartidasCerradasAdapter
import com.txus.pachangasnavigation.databinding.FragmentPartidasCerradasBinding
import com.txus.pachangasnavigation.listeners.MainListener
import com.txus.pachangasnavigation.models.Partida
import com.txus.pachangasnavigation.ui.activities.MainActivity
import com.txus.pachangasnavigation.viewmodel.PartidasCerradasViewModel


class PartidasCerradasFragment : Fragment() {

    private var listener:MainListener?=null

    private var binding:FragmentPartidasCerradasBinding?=null
    private val model:PartidasCerradasViewModel by viewModels()
    private lateinit var mAdapter: PartidasCerradasAdapter




    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        binding= FragmentPartidasCerradasBinding.inflate(layoutInflater,container,false)
        val view=binding!!.root


        mAdapter=PartidasCerradasAdapter()

        model.getPartidas().observe(viewLifecycleOwner,{

            if (it.isEmpty())
                Snackbar.make(
                    view,
                    "Aún no hay partidas cerradas",
                    Snackbar.LENGTH_SHORT
                ).show()
            else


                createRecyclerView(it)


        })



        return view
    }

    private fun createRecyclerView(partidas:List<Partida>){

        val madapter=PartidasCerradasAdapter()
        madapter.PartidasCerradasAdapter(partidas)

        val recyclerView=binding!!.rvPartidasCerradas
        recyclerView.apply {

            layoutManager=LinearLayoutManager(requireContext(),RecyclerView.VERTICAL,false)
            adapter=madapter
        }
    }




}