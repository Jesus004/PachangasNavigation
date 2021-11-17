package com.txus.pachangasnavigation.ui.fragments

import android.app.NotificationChannel
import android.app.NotificationManager
import android.content.Context
import android.graphics.Color
import android.os.Build
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.content.ContextCompat.getSystemService
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.MutableLiveData
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.snackbar.Snackbar
import com.google.firebase.firestore.DocumentSnapshot
import com.google.firebase.firestore.ktx.toObject
import com.txus.pachangasnavigation.App
import com.txus.pachangasnavigation.adapters.PartidaAdapter
import com.txus.pachangasnavigation.databinding.FragmentPartidasAbiertasBinding
import com.txus.pachangasnavigation.databinding.ItemPartidaRecyclerViewBinding
import com.txus.pachangasnavigation.listeners.MainListener
import com.txus.pachangasnavigation.models.Partida
import com.txus.pachangasnavigation.models.Usuario
import com.txus.pachangasnavigation.ui.activities.MainActivity
import com.txus.pachangasnavigation.utils.Constantes
import com.txus.pachangasnavigation.viewmodel.MisPartidasViewModel
import com.txus.pachangasnavigation.viewmodel.PartidasViewModel

class PartidasAbiertasFragment : Fragment(), PartidaAdapter.PartidaAdapterListener {
    private var listener: MainListener? = null
    private var binding: FragmentPartidasAbiertasBinding? = null
    private val viewModel: MisPartidasViewModel by viewModels()
    private val model: PartidasViewModel by viewModels()
    private lateinit var mAdapter: PartidaAdapter
    val firestore = App.getFirestore()
    val usuario = App.getAuth()


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        binding = FragmentPartidasAbiertasBinding.inflate(inflater, container, false)
        val view = binding!!.root


        // Inflate the layout for this fragment

        mAdapter = PartidaAdapter(requireContext(), this)

        model.getPartidas().observe(viewLifecycleOwner, {

            if (it.isEmpty())
                Snackbar.make(
                    view,
                    "Aún no hay partidas registradas",
                    Snackbar.LENGTH_SHORT
                ).show()
            else


                createRecyclerView(it)


        })


        return view
    }


    private fun createRecyclerView(partidas: List<Partida>) {


        val madpater = PartidaAdapter(requireContext(), this)
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


    override fun onAttach(context: Context) {
        super.onAttach(context)

        listener = context as MainActivity

        listener!!.showBottomNavigation()




        firestore.collection(Constantes.USUARIOS).document(usuario.uid!!)
            .get()
            .addOnSuccessListener { documentos ->

                val favs = documentos.data?.get("mispartidas")

                if (favs != null) {

                    for (fav in favs as ArrayList<String>)

                        firestore.collection(Constantes.PARTIDAS).document(fav)
                            .get()
                            .addOnSuccessListener {

                                val completa = it.getBoolean("partidaCompleta")!!



                                if (completa) {

                                    listener!!.createNotificationChannel()
                                }


                            }

                    println("A IMPRIMIR " + favs.toString())


                }


            }


    }
}


/* firestore.collection(Constantes.PARTIDAS).document("L3x3s7lhzOdJmu8QAASp")
     .get()
     .addOnSuccessListener { documentos ->

         var list = mutableListOf<Partida>()
        // for (document in documentos) {

             val partida = documentos.toObject<Partida>()!!
             partida.id = documentos.id!!
             Log.e("MIERDA", partida.fav.toString())

             if (partida.completa ){
                 Log.e("MIERDA", partida.fav.toString())
                 listener!!.createNotificationChannel()
             }
           }*/
// esto funciona


/*firestore.collection(Constantes.PARTIDAS).document("CjtTf8FCv1t1IcLDfAlx").get()
         .addOnSuccessListener { documento ->

 val completa = documento.getBoolean("partidaCompleta")!!


 val usuarios=documento.data!!.map {

     it.value

 }





 if (documento.exists()) {

     val notificar = documento.getBoolean("notificacion")!!


     if (notificar) {

         listener!!.createNotificationChannel()

     }

 }*/




