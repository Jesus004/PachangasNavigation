package com.txus.pachangasnavigation.ui.fragments

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.NavHostFragment
import com.google.android.material.snackbar.Snackbar
import com.google.firebase.auth.FirebaseAuthInvalidUserException
import com.txus.pachangasnavigation.App
import com.txus.pachangasnavigation.R
import com.txus.pachangasnavigation.databinding.FragmentLoginBinding
import com.txus.pachangasnavigation.viewmodel.UsuarioViewModel

class LoginFragment : Fragment() {


    private var _binding: FragmentLoginBinding? = null
    private val model: UsuarioViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        _binding = FragmentLoginBinding.inflate(layoutInflater, container, false)

        val view = _binding!!.root
        val binding = _binding!!
        // Inflate the layout for this fragment


        binding.btnRegistro.setOnClickListener {

            NavHostFragment.findNavController(this).navigate(R.id.registroFragment)

        }

        binding.btnLogin.setOnClickListener {


            val email = binding.loginTieEmail
            val password = binding.loginTiePassword

            if (email.text.toString().isBlank()) {

                Snackbar.make(
                    view,
                    "los campos no pueden estar vacios...cohone!!",
                    Snackbar.LENGTH_SHORT
                ).show()
                return@setOnClickListener
            }
            if (password.text.toString().isBlank()) {

                Snackbar.make(
                    view,
                    "los campos no pueden estar vacios...cohone!!",
                    Snackbar.LENGTH_SHORT
                ).show()
                return@setOnClickListener
            }

            model.login(email.text.toString(), password.text.toString())
                .observe(viewLifecycleOwner, { task ->

                    if (task.isSuccessful) {
                        NavHostFragment.findNavController(this)
                            .navigate(R.id.action_loginFragment_to_bottom_nav_graph)
                    } else {


                        when (task.exception) {

                            is FirebaseAuthInvalidUserException -> {

                                Snackbar.make(
                                    view,
                                    "Debes registrarte para entrar...cohone!!",
                                    Snackbar.LENGTH_SHORT
                                ).show()
                            }
                            else -> {

                                Snackbar.make(
                                    view,
                                    "Error...no me digas porque pero no puedes entrar...cohone!",
                                    Snackbar.LENGTH_SHORT
                                ).show()
                            }

                        }
                    }


                })


        }
        return view


    }
/*
    public override fun onStart() {
        super.onStart()
        // Check if user is signed in (non-null) and update UI accordingly.
        val currentUser = App.getAuth().currentUser
        if (currentUser != null) {

            goToMain()
        }
    }*/

    private fun goToMain() {
        NavHostFragment.findNavController(this)
            .navigate(R.id.action_loginFragment_to_bottom_nav_graph)


    }


}