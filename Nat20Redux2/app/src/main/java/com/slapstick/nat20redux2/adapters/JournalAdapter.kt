package com.slapstick.nat20redux2.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.findNavController
import androidx.recyclerview.widget.RecyclerView
import com.slapstick.nat20redux2.R
import com.slapstick.nat20redux2.db.journal.Journal
import com.slapstick.nat20redux2.ui.journal.JournalFragmentDirections
import kotlinx.android.synthetic.main.journal_item.view.*

class JournalAdapter: RecyclerView.Adapter<JournalAdapter.MyViewHolder>(){

    private var journalList = emptyList<Journal>()

    class MyViewHolder(itemView: View): RecyclerView.ViewHolder(itemView) {}

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        return MyViewHolder(LayoutInflater.from(parent.context)
                .inflate(R.layout.journal_item, parent, false))
    }

    override fun getItemCount(): Int {
        return journalList.size
    }

    override fun onBindViewHolder(holder:MyViewHolder, position: Int) {
        val currentJournal = journalList[position]
        holder.itemView.tv_journalItemTitle.text = currentJournal.title
        holder.itemView.tv_jounralItemContent.text = currentJournal.content

        holder.itemView.cl_journalItem.setOnClickListener {
            val action = JournalFragmentDirections
                    .actionJournalFragmentToUpdateJournalFragment(currentJournal)
            holder.itemView.findNavController().navigate(action)
        }
    }

    fun setData(journal: List<Journal>) {
        this.journalList = journal
        notifyDataSetChanged()
    }
}