package com.txus.pachangasnavigation.ui.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.navigation.NavHostController
import androidx.navigation.fragment.NavHostFragment
import com.google.android.material.snackbar.Snackbar
import com.google.firebase.auth.FirebaseAuthInvalidUserException
import com.google.firebase.auth.FirebaseAuthUserCollisionException
import com.google.firebase.auth.FirebaseAuthWeakPasswordException
import com.google.firebase.firestore.FirebaseFirestoreException
import com.txus.pachangasnavigation.R
import com.txus.pachangasnavigation.databinding.FragmentRegistroBinding
import com.txus.pachangasnavigation.models.Usuario
import com.txus.pachangasnavigation.viewmodel.UsuarioViewModel
import java.util.*

class RegistroFragment : Fragment() {

    private var binding: FragmentRegistroBinding? = null

    private val model: UsuarioViewModel by viewModels()


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?

    ): View? {

        binding = FragmentRegistroBinding.inflate(layoutInflater, container, false)

        val view = binding!!.root

        binding!!.registroBtnRegistrar.setOnClickListener {

            val nombre = binding!!.registroTieNombre
            val email = binding!!.registroTieEmail
            val password1 = binding!!.registroTiePass1
            val password2 = binding!!.registroTiePass2

            if (nombre.text.toString().isNullOrBlank()) {

                Snackbar.make(
                    view,
                    "Revisa los campos, no pueden estar vacíos...cohone!",
                    Snackbar.LENGTH_SHORT
                ).show()
                return@setOnClickListener
            }

            if (email.text.toString().isNullOrBlank()) {

                Snackbar.make(
                    view,
                    "Revisa los campos, no pueden estar vacíos...cohone!",
                    Snackbar.LENGTH_SHORT
                ).show()
                return@setOnClickListener
            }

            if (password1.text.toString().isNullOrBlank()) {

                Snackbar.make(
                    view,
                    "Revisa los campos, no pueden estar vacíos...cohone!",
                    Snackbar.LENGTH_SHORT
                ).show()
                return@setOnClickListener
            }

            if (password2.text.toString().isNullOrBlank()) {

                Snackbar.make(
                    view,
                    "Revisa los campos, no pueden estar vacíos...cohone!",
                    Snackbar.LENGTH_SHORT
                ).show()
                return@setOnClickListener
            }

            if (password1.text.toString() != password2.text.toString()) {
                Snackbar.make(
                    view,
                    "Las contraseñas no coinciden  ...cohone!",
                    Snackbar.LENGTH_SHORT
                ).show()
                return@setOnClickListener

            }

            val usuario = Usuario(
                binding!!.registroTieNombre.text.toString(),
                binding!!.registroTieEmail.text.toString(),
                0,
                "",
                Date(System.currentTimeMillis()),
                Date(System.currentTimeMillis())
            )

            model.registro(usuario, password1.text.toString()).observe(viewLifecycleOwner,
                { exception ->

                    if (exception == null) {
                        Snackbar.make(
                            view,
                            "Usuario registrado con éxito...olé!!",
                            Snackbar.LENGTH_SHORT
                        ).show()
                        NavHostFragment.findNavController(this).navigate(R.id.loginFragment)
                    } else {


                        when (exception) {

                            is FirebaseAuthUserCollisionException -> {

                                Snackbar.make(
                                    view,
                                    "El email ya se usa en otra cuenta",
                                    Snackbar.LENGTH_SHORT
                                ).show()
                            }


                            is FirebaseFirestoreException -> {

                                Snackbar.make(
                                    view,
                                    "No se ha podido registrar al nuevo usuario",
                                    Snackbar.LENGTH_SHORT
                                ).show()
                            }

                            is FirebaseAuthInvalidUserException -> {

                                Snackbar.make(
                                    view,
                                    "No se ha podido actualizar nombre de usuario",
                                    Snackbar.LENGTH_SHORT
                                ).show()
                            }

                            is FirebaseAuthWeakPasswordException -> {

                                Snackbar.make(
                                    view,
                                    "Contraseña muuuy débil, debes poner 6 caracteres",
                                    Snackbar.LENGTH_SHORT
                                ).show()
                            }
                            else ->

                                Snackbar.make(
                                    view,
                                    "No se ha podido registrar el usuario de forma correcta",
                                    Snackbar.LENGTH_SHORT
                                ).show()
                        }


                    }


                })


        }

        return view
    }


}