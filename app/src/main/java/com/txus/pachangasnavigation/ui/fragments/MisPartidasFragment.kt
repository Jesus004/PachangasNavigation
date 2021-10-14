package com.txus.pachangasnavigation.ui.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.txus.pachangasnavigation.R
import com.txus.pachangasnavigation.adapters.MisPartidasAdapter
import com.txus.pachangasnavigation.adapters.PartidaAdapter
import com.txus.pachangasnavigation.databinding.FragmentMisPartidasBinding
import com.txus.pachangasnavigation.viewmodel.MisPartidasViewModel


class MisPartidasFragment : Fragment() {

    val model: MisPartidasViewModel by viewModels()

    private var binding: FragmentMisPartidasBinding? = null

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        binding = FragmentMisPartidasBinding.inflate(layoutInflater, container, false)
        val view = binding!!.root

        val madpater = MisPartidasAdapter()

        val rv = binding!!.rvMisPartidas
        rv.apply {
            layoutManager = LinearLayoutManager(requireContext(), RecyclerView.VERTICAL, false)
            adapter = madpater

        }

        model.findAllPartidas().observe(viewLifecycleOwner,){

            madpater.addFav(it!!)
        }

        return view
    }


}