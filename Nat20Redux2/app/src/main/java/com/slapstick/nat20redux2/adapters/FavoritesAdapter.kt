package com.slapstick.nat20redux2.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.findNavController
import androidx.recyclerview.widget.RecyclerView
import com.slapstick.nat20redux2.R
import com.slapstick.nat20redux2.db.favorites.Favorite
import com.slapstick.nat20redux2.ui.favorites.FavoritesFragmentDirections
import kotlinx.android.synthetic.main.favorite_item.view.*

class FavoritesAdapter: RecyclerView.Adapter<FavoritesAdapter.MyViewHolder>() {

    private var favoritesList = emptyList<Favorite>()

    class MyViewHolder(itemView: View): RecyclerView.ViewHolder(itemView) {}

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        return MyViewHolder(LayoutInflater.from(parent.context)
            .inflate(R.layout.favorite_item, parent, false))
    }

    override fun getItemCount(): Int { return favoritesList.size }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        val currentFavorite = favoritesList[position]
        holder.itemView.tv_faveTitle.text = currentFavorite.titleFave
        holder.itemView.tv_faveRoll.text = currentFavorite.rollFave

        holder.itemView.cl_rollFavoriteItem.setOnClickListener {
            val action = FavoritesFragmentDirections
                .actionFavoritesFragmentToUpdateFavoriteFragment(currentFavorite)
            holder.itemView.findNavController().navigate(action)
        }
    }

    fun setData(favorite: List<Favorite>) {
        this.favoritesList = favorite
        notifyDataSetChanged()
    }

}