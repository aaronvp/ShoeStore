package com.udacity.shoestore

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.navigation.findNavController
import com.udacity.shoestore.databinding.FragmentLoginBinding

class LoginFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val binding: FragmentLoginBinding = DataBindingUtil.inflate(
            inflater,
            R.layout.fragment_login,
            container,
            false
        )
        binding.btnLogin.setOnClickListener { view: View -> navigateToWelcomeFragment(view) }
        binding.btnRegister.setOnClickListener { view: View -> navigateToWelcomeFragment(view) }
        return binding.root
    }

    private fun navigateToWelcomeFragment(view: View) {
        view.findNavController().navigate(R.id.action_loginFragment_to_welcomeFragment)
    }

}