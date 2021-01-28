package com.slapstick.nat20redux2.ui.favorites

import android.os.Bundle
import android.text.TextUtils
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.slapstick.nat20redux2.R
import com.slapstick.nat20redux2.db.favorites.Favorite
import com.slapstick.nat20redux2.viewmodel.FavoritesViewModel
import kotlinx.android.synthetic.main.fragment_favorite_add.*
import kotlinx.android.synthetic.main.fragment_favorite_add.view.*

class AddFavoriteFragment : Fragment() {

    private lateinit var viewModel: FavoritesViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view =  inflater.inflate(R.layout.fragment_favorite_add, container, false)

        viewModel = ViewModelProvider(this).get(FavoritesViewModel::class.java)

        view.btn_add.setOnClickListener {
            insertDataToDatabase()
        }

        return view
    }

    private fun insertDataToDatabase() {
        val titleFave = et_titleFave.text.toString()
        val rollFave = et_rollFave.text.toString()

        if(inputCheck(titleFave, rollFave)) {
            val roll = Favorite(0, titleFave, rollFave)
            viewModel.addRoll(roll)
            Toast.makeText(requireContext(), "New Roll Added!", Toast.LENGTH_LONG).show()
            findNavController().navigate(R.id.action_addFavoriteFragment_to_favoritesFragment)
        } else {
            Toast.makeText(requireContext(), "Please fill all fields", Toast.LENGTH_LONG)
        }
    }

    private fun inputCheck(titleFave: String, rollFave: String): Boolean {
        return !(TextUtils.isEmpty(titleFave) && TextUtils.isEmpty(rollFave))
    }

}