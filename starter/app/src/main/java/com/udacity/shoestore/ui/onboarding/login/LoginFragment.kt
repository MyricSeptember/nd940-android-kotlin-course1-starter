package com.udacity.shoestore.ui.onboarding.login

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.udacity.shoestore.R
import com.udacity.shoestore.databinding.FragmentLoginBinding

class LoginFragment : Fragment() {

    lateinit var binding: FragmentLoginBinding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_login, container, false)

        binding.loginButton.setOnClickListener {
            allFieldCompleted()
        }

        binding.signUpButton.setOnClickListener {
            allFieldCompleted()
        }

        return binding.root
    }


    private fun allFieldCompleted() {
        val userNameValue = binding.userNameEditText.text
        val userPasswordValue = binding.passwordEditText.text

        if (userNameValue.isEmpty() || userPasswordValue.isEmpty()) {
            Toast.makeText(
                requireContext(),
                getString(R.string.complete_all_fields_text),
                Toast.LENGTH_SHORT
            ).show()
        } else {
            findNavController().navigate(R.id.action_loginFragment_to_welcomeFragment)
        }
    }
}