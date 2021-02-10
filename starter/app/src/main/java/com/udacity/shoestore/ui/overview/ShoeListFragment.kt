package com.udacity.shoestore.ui.overview

import android.os.Bundle
import android.view.*
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.udacity.shoestore.R
import com.udacity.shoestore.databinding.FragmentShoeListBinding
import com.udacity.shoestore.databinding.ItemShoeBinding
import com.udacity.shoestore.ui.viewmodel.ShoeStoreViewModel

class ShoeListFragment : Fragment() {

    lateinit var binding: FragmentShoeListBinding
    private lateinit var shoeStoreViewModel: ShoeStoreViewModel

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_shoe_list, container, false)
        binding.floatAddShoeButton.setOnClickListener {
            shoeStoreViewModel.initializeShoe()
            findNavController().navigate(R.id.action_shoeListFragment_to_shoeDetailFragment)
        }

        shoeStoreViewModel =
            ViewModelProvider(requireActivity()).get(ShoeStoreViewModel::class.java)

        shoeStoreViewModel.shoes.observe(viewLifecycleOwner, Observer { shoes ->
            if (shoes.isNotEmpty()) binding.emptyListText.visibility = View.GONE
            for (shoe in shoes) {
                DataBindingUtil.inflate<ItemShoeBinding>(
                    layoutInflater,
                    R.layout.item_shoe,
                    binding.shoeList,
                    true
                ).apply {
                    this.shoe = shoe

                }
            }
        })
        setHasOptionsMenu(true)

        return binding.root
    }


    private fun logout() {
        findNavController().navigate(ShoeListFragmentDirections.actionShoeListFragmentToLoginFragment())
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        super.onCreateOptionsMenu(menu, inflater)
        inflater.inflate(R.menu.logout_menu, menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        logout()
        return super.onOptionsItemSelected(item)
    }
}