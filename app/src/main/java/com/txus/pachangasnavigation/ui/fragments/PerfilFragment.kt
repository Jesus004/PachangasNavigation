package com.txus.pachangasnavigation.ui.fragments

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.provider.MediaStore
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.swiperefreshlayout.widget.CircularProgressDrawable
import com.txus.pachangasnavigation.App
import com.txus.pachangasnavigation.R
import com.bumptech.glide.Glide
import com.google.android.material.snackbar.Snackbar
import com.txus.pachangasnavigation.databinding.FragmentPerfilBinding
import com.txus.pachangasnavigation.models.Usuario
import com.txus.pachangasnavigation.utils.Constantes
import com.txus.pachangasnavigation.viewmodel.UsuarioViewModel


class PerfilFragment : Fragment() {

    private var _binding:FragmentPerfilBinding?=null

    private val TAG="PROFILE_FRAGMENT"
    private val CODE_IMAGE_PICK=1

    private var uriFoto: Uri?=null
    private val model:UsuarioViewModel by viewModels()
    private lateinit var usuario:Usuario

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        _binding= FragmentPerfilBinding.inflate(layoutInflater,container,false)
        var binding=_binding!!
        val view=binding.root

        model.findOneById(App.getAuth().currentUser!!.uid).observe(viewLifecycleOwner,{
            usuario=it
            if (!usuario.imageUrl.isNullOrBlank()){

                val circularProgress=CircularProgressDrawable(requireContext())
                circularProgress.strokeWidth=5f
                circularProgress.centerRadius=30f
                circularProgress.start()

                Glide
                    .with(this)
                    .load(usuario.imageUrl)
                    .fitCenter()
                    .placeholder(circularProgress)
                    .into(binding.perfilImageProfile)
            }
            binding.perfilTieEdad.setText(it.edad.toString())
            binding.perfilTieEmail.setText(it.email)
            binding.perfilTieNombre.setText(it.nombre)



        })

        binding.perfilImageProfile.setOnClickListener {
            val intent=Intent(Intent.ACTION_PICK,MediaStore.Images.Media.EXTERNAL_CONTENT_URI)
            startActivityForResult(intent,CODE_IMAGE_PICK)
        }

        binding.perfilBtnActualizar.setOnClickListener {
            save()
        }
        return view
    }

    private fun save() {
       if (uriFoto!=null){

           val storage=App.getStorage()
           val reference=
               storage.reference.child(Constantes.IMAGENES+"/"+App.getAuth().currentUser!!.uid+"/image_profile")

               reference.putFile(uriFoto!!)
                   .addOnSuccessListener {
                       it.storage.downloadUrl.addOnSuccessListener { url->
                           usuario.imageUrl=url.toString()
                           saveUser()
                       }
                   }
                   .addOnFailureListener {
                       Snackbar.make(
                           _binding!!.root,
                           "Nos se ha podido subir la imagen a Storage",
                           Snackbar.LENGTH_SHORT
                       ).show()
                   }

       }else{

           saveUser()
       }
    }

    private fun saveUser() {
       model.updateUsuario(usuario).observe(viewLifecycleOwner,{

           if (it){
               Snackbar.make(
                   _binding!!.root,
                   "Usuario actualizado!",
                   Snackbar.LENGTH_SHORT
               ).show()

           }else{

               Snackbar.make(
                   _binding!!.root,
                   "Error al actualizar",
                   Snackbar.LENGTH_SHORT
               ).show()
           }
       })
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)

        when(requestCode){

            CODE_IMAGE_PICK->{

                uriFoto=data?.data
                _binding!!.perfilImageProfile.setImageURI(uriFoto)
            }
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding=null
    }


}