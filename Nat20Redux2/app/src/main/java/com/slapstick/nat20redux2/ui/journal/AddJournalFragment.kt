package com.slapstick.nat20redux2.ui.journal

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
import com.slapstick.nat20redux2.db.journal.Journal
import com.slapstick.nat20redux2.viewmodel.JournalViewModel
import kotlinx.android.synthetic.main.fragment_journal_add.*
import kotlinx.android.synthetic.main.fragment_journal_add.view.*

class AddJournalFragment : Fragment() {

    private lateinit var viewModel: JournalViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_journal_add, container, false)

        viewModel = ViewModelProvider(this).get(JournalViewModel::class.java)

        view.btn_addJournal.setOnClickListener {
            insertDataToDatabase()
        }

        return view
    }

    private fun insertDataToDatabase() {
        val journalTitle = et_titleJournal.text.toString()
        val journalContent = et_contentJournal.text.toString()

        if(inputCheck(journalTitle, journalContent)) {
            val journal = Journal(0, journalTitle, journalContent)
            viewModel.addJournal(journal)
            Toast.makeText(requireContext(), "New Entry Created!", Toast.LENGTH_LONG).show()
            findNavController().navigate(R.id.action_addJournalFragment_to_journalFragment)
        } else {
            Toast.makeText(requireContext(), "Please fill all fields", Toast.LENGTH_LONG).show()
        }

    }

    private fun inputCheck(journalTitle: String, journalContent: String): Boolean {
        return !(TextUtils.isEmpty(journalTitle) && TextUtils.isEmpty(journalContent))
    }

}