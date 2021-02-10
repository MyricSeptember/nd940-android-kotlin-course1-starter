package com.udacity.shoestore.ui.detail

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.udacity.shoestore.R
import com.udacity.shoestore.databinding.FragmentShoeDetailBinding
import com.udacity.shoestore.ui.viewmodel.ShoeStoreViewModel

class ShoeDetailFragment : Fragment() {

    lateinit var binding: FragmentShoeDetailBinding
    private lateinit var shoeStoreViewModel: ShoeStoreViewModel

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding =
            DataBindingUtil.inflate(inflater, R.layout.fragment_shoe_detail, container, false)

        shoeStoreViewModel =
            ViewModelProvider(requireActivity()).get(ShoeStoreViewModel::class.java)

        binding.shoeStoreViewModel = shoeStoreViewModel
        binding.lifecycleOwner = this

        shoeStoreViewModel.navigateBack.observe(viewLifecycleOwner, Observer {
            if (it) {
                shoeStoreViewModel.shoeAddedSucessfully()
                findNavController().navigateUp()
            }
        })

        shoeStoreViewModel.hasMissingFields.observe(
            viewLifecycleOwner,
            Observer { hasMissingFields ->
                if (hasMissingFields) {
                    Toast.makeText(
                        requireContext(),
                        getString(R.string.complete_all_fields_text),
                        Toast.LENGTH_SHORT
                    ).show()
                }
            })

        return binding.root
    }
}