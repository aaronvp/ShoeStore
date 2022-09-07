package com.udacity.shoestore

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.navigation.findNavController
import com.google.android.material.snackbar.Snackbar
import com.udacity.shoestore.databinding.FragmentShoeDetailBinding

import com.udacity.shoestore.models.ShoeListViewModel


class ShoeDetailFragment : Fragment() {

    private val shoeListViewModel: ShoeListViewModel by activityViewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val binding: FragmentShoeDetailBinding = DataBindingUtil.inflate(
            inflater,
            R.layout.fragment_shoe_detail,
            container,
            false
        )

        binding.shoeListViewModel = shoeListViewModel

        binding.btnSave.setOnClickListener {
            if (shoeListViewModel.addNewShoe()) {
                it.findNavController().navigate(
                    ShoeDetailFragmentDirections.actionNewShoeFragmentToShoeListFragment()
                )
            } else {
                Snackbar.make(
                    requireView(),
                    getString(R.string.warn_invalid_shoe),
                    Snackbar.LENGTH_LONG
                ).show()
            }
        }

        binding.btnCancel.setOnClickListener {
            it.findNavController()
                .navigate(ShoeDetailFragmentDirections.actionNewShoeFragmentToShoeListFragment())
        }

        return binding.root
    }


}