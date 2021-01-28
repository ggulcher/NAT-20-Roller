package com.slapstick.nat20redux2.ui.favorites

import android.app.AlertDialog
import android.os.Bundle
import android.text.TextUtils
import android.view.*
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.slapstick.nat20redux2.R
import com.slapstick.nat20redux2.db.favorites.Favorite
import com.slapstick.nat20redux2.viewmodel.FavoritesViewModel
import kotlinx.android.synthetic.main.fragment_favorite_update.*
import kotlinx.android.synthetic.main.fragment_favorite_update.view.*

class UpdateFavoriteFragment : Fragment() {

    private val args by navArgs<UpdateFavoriteFragmentArgs>()
    private lateinit var viewModel: FavoritesViewModel

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        val view =  inflater.inflate(R.layout.fragment_favorite_update, container, false)

        viewModel = ViewModelProvider(this).get(FavoritesViewModel::class.java)

        view.et_titleFaveUpdate.setText(args.currentFavorite.titleFave)
        view.et_rollFaveUpdate.setText(args.currentFavorite.rollFave)

        view.btn_update.setOnClickListener {
            updateRoll()
        }

        setHasOptionsMenu(true)

        return view
    }

    private fun updateRoll() {
        val faveTitleUpdate = et_titleFaveUpdate.text.toString()
        val faveRollUpdate = et_rollFaveUpdate.text.toString()

        if(inputCheck(faveTitleUpdate, faveRollUpdate)) {
            // Create roll object
            val updatedRoll = Favorite(args.currentFavorite.id, faveTitleUpdate, faveRollUpdate)
            // Update current roll
            viewModel.updateRoll(updatedRoll)
            Toast.makeText(requireContext(), "Update Successful!", Toast.LENGTH_SHORT).show()
            // Navigate back
            findNavController().navigate(R.id.action_updateFavoriteFragment_to_favoritesFragment)
        } else {
            Toast.makeText(requireContext(), "Please fill all fields", Toast.LENGTH_LONG).show()
        }
    }

    private fun inputCheck(titleFave: String, rollFave: String): Boolean {
        return !(TextUtils.isEmpty(titleFave) && TextUtils.isEmpty(rollFave))
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        inflater.inflate(R.menu.delete_menu, menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if(item.itemId == R.id.menu_delete) {
            deleteRoll()
        }
        return super.onOptionsItemSelected(item)
    }

    private fun deleteRoll() {
        val builder = AlertDialog.Builder(requireContext())
        builder.setPositiveButton("Yes") {_,_ ->
            viewModel.deleteRoll(args.currentFavorite)
            Toast.makeText(requireContext(), "Roll ${args.currentFavorite.titleFave} Deleted",
                Toast.LENGTH_LONG).show()
            findNavController().navigate(R.id.action_updateFavoriteFragment_to_favoritesFragment)
        }
        builder.setNegativeButton("No") {_,_ -> }
        builder.setTitle("Delete ${args.currentFavorite.titleFave}?")
        builder.setMessage("Are you sure you want to delete ${args.currentFavorite.titleFave}")
        builder.create().show()
    }

}