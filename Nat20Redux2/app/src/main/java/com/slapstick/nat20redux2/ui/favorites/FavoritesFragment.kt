package com.slapstick.nat20redux2.ui.favorites

import android.app.AlertDialog
import android.os.Bundle
import android.view.*
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.slapstick.nat20redux2.R
import com.slapstick.nat20redux2.adapters.FavoritesAdapter
import com.slapstick.nat20redux2.viewmodel.FavoritesViewModel
import kotlinx.android.synthetic.main.fragment_favorites.view.*

class FavoritesFragment: Fragment() {

    private lateinit var viewModel: FavoritesViewModel

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate layout for fragment
        val view = inflater.inflate(R.layout.fragment_favorites, container, false)

        // RecyclerView
        val adapter = FavoritesAdapter()
        val recyclerView = view.rv_favoritesRecycler
        recyclerView.adapter = adapter
        recyclerView.layoutManager = LinearLayoutManager(requireContext())

        // RollViewModel
        viewModel = ViewModelProvider(this).get(FavoritesViewModel::class.java)
        viewModel.readAllFavorites.observe(viewLifecycleOwner, { roll -> adapter.setData(roll) })

        view.floatingActionButton.setOnClickListener{
            findNavController().navigate(R.id.action_favoritesFragment_to_addFavoriteFragment)
        }

        setHasOptionsMenu(true)

        return view
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        inflater.inflate(R.menu.delete_menu, menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if(item.itemId == R.id.menu_delete) {
            deleteAllRolls()
        }
        return super.onOptionsItemSelected(item)
    }

    private fun deleteAllRolls() {
        val builder = AlertDialog.Builder(requireContext())
        builder.setPositiveButton("Yes") {_,_ ->
            viewModel.deleteAllRolls()
            Toast.makeText(requireContext(), "All Rolls Deleted",
                Toast.LENGTH_LONG).show()
        }
        builder.setNegativeButton("No") {_,_ -> }
        builder.setTitle("Delete all rolls?")
        builder.setMessage("Are you sure you want to delete everything?")
        builder.create().show()
    }

}