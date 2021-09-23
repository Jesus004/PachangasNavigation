package com.txus.pachangasnavigation.ui.fragments

import android.annotation.SuppressLint
import android.content.Context
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.AdapterView
import androidx.fragment.app.viewModels
import com.google.firebase.firestore.FirebaseFirestore
import com.txus.pachangasnavigation.App
import com.txus.pachangasnavigation.R
import com.txus.pachangasnavigation.databinding.FragmentCrearPartidaBinding
import com.txus.pachangasnavigation.listeners.MainListener
import com.txus.pachangasnavigation.ui.activities.MainActivity
import com.txus.pachangasnavigation.viewmodel.UsuarioViewModel

class CrearPartidaFragment : Fragment() {

    private var listener: MainListener? = null
    private val db = FirebaseFirestore.getInstance()
    private var _binding: FragmentCrearPartidaBinding? = null


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        _binding = FragmentCrearPartidaBinding.inflate(layoutInflater, container, false)
        val view = _binding!!.root
        val binding = _binding!!
        // Inflate the layout for this fragment


        binding.crearPartidaTieFecha.setOnClickListener {
            showDatePickerDialog()
        }
        binding.btnCrearPartida.setOnClickListener {

            crearPartida()

        }

        return view
    }

    private fun showDatePickerDialog() {

        val datePicker = DatePickerFragment { day, month, year -> onDateSelected(day, month, year) }
        getFragmentManager()?.let { datePicker.show(it, "datePicker") }

    }

    @SuppressLint("SetTextI18n")
    fun onDateSelected(day: Int, month: Int, year: Int) {
        val mes = month + 1

        _binding!!.crearPartidaTieFecha.setText("$day-$mes-$year")

    }

    private fun crearPartida() {

        db.collection("partidas").document().set(
            hashMapOf(
                "deporte" to _binding!!.crearPartidaTieDeporte.text.toString(),
                "nº jugadores" to _binding!!.tieNumjug.text.toString(),
                "lugar" to _binding!!.crearPartidaTieLugar.text.toString(),
                "fecha" to _binding!!.crearPartidaTieFecha.text.toString(),

            )

        )

    }

    override fun onAttach(context: Context) {
        super.onAttach(context)
        listener = context as MainActivity

        listener!!.showBottomNavigation()

    }

}