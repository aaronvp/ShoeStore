package com.udacity.shoestore

import android.os.Bundle
import android.util.Log
import android.view.*
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.findNavController
import androidx.navigation.fragment.findNavController
import com.udacity.shoestore.databinding.FragmentShoeListBinding
import com.udacity.shoestore.models.Shoe
import com.udacity.shoestore.models.ShoeListViewModel
import kotlinx.android.synthetic.main.fragment_shoe_list.*
import kotlinx.android.synthetic.main.item_shoe.view.tv_shoe_descr
import kotlinx.android.synthetic.main.shoe_list_item.view.*

class ShoeListFragment : Fragment() {

    private val shoeListViewModel: ShoeListViewModel by activityViewModels()
    private lateinit var binding: FragmentShoeListBinding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        // Inflate the layout for this fragment
        binding = DataBindingUtil.inflate(
            inflater,
            R.layout.fragment_shoe_list,
            container,
            false
        )
        binding.lifecycleOwner = this

        shoeListViewModel.shoeList.observe(viewLifecycleOwner, Observer { shoeList ->
            updateShoeList(shoeList)
        })

        binding.fab.setOnClickListener { view: View ->
            shoeListViewModel.clearShoe()
            view.findNavController().navigate(R.id.action_shoeListFragment_to_newShoeFragment)
        }

        this.setHasOptionsMenu(true)

        return binding.root
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        super.onCreateOptionsMenu(menu, inflater)
        inflater.inflate(R.menu.shoe_list_menu, menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            R.id.menu_item_logout ->
                findNavController().navigate(R.id.action_shoeListFragment_to_loginFragment)
        }
        return super.onOptionsItemSelected(item)
    }

    private fun updateShoeList(shoeList: MutableList<Shoe>) {
        for (shoe: Shoe in shoeList) {
            addShoeItemToView(shoe)
        }
    }

    private fun addShoeItemToView(shoe: Shoe) {
        val shoeView = layoutInflater.inflate(R.layout.shoe_list_item, null)
        shoeView.tv_shoe_name.text = shoe.name
        shoeView.tv_shoe_company.text = shoe.company
        shoeView.tv_shoe_size.text = shoe.size.toString()
        shoeView.tv_shoe_descr.text = shoe.description
        binding.shoeList.addView(shoeView)
    }

}