package com.slapstick.nat20redux2.ui.journal

import android.app.AlertDialog
import android.os.Bundle
import android.view.*
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.StaggeredGridLayoutManager
import com.slapstick.nat20redux2.R
import com.slapstick.nat20redux2.adapters.JournalAdapter
import com.slapstick.nat20redux2.viewmodel.JournalViewModel
import kotlinx.android.synthetic.main.fragment_journal.view.*

class JournalFragment : Fragment() {

    private lateinit var viewModel: JournalViewModel

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        val view = inflater.inflate(R.layout.fragment_journal, container, false)

        val adapter = JournalAdapter()
        val recyclerView = view.rv_journalRecyclerView
        recyclerView.adapter = adapter
        recyclerView.layoutManager = StaggeredGridLayoutManager(3, StaggeredGridLayoutManager.VERTICAL)

        viewModel = ViewModelProvider(this).get(JournalViewModel::class.java)
        viewModel.readAllJournal.observe(viewLifecycleOwner, Observer { journal ->
            adapter.setData(journal)
        })

        view.fab_journalAddButton.setOnClickListener {
            findNavController().navigate(R.id.action_journalFragment_to_addJournalFragment)
        }

        setHasOptionsMenu(true)

        return view
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        inflater.inflate(R.menu.delete_menu, menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if(item.itemId == R.id.menu_delete) {
            deleteAllJournals()
        }
        return super.onOptionsItemSelected(item)
    }

    private fun deleteAllJournals() {
        val builder = AlertDialog.Builder(requireContext())
        builder.setPositiveButton("Yes") {_,_ ->
            viewModel.deleteAllJournals()
            Toast.makeText(requireContext(), "All Entries Deleted",
                Toast.LENGTH_LONG).show()
        }
        builder.setNegativeButton("No") {_,_ -> }
        builder.setTitle("Delete all entries?")
        builder.setMessage("Are you sure you want to delete everything?")
        builder.create().show()
    }

}