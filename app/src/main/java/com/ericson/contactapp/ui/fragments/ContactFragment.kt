package com.ericson.contactapp.ui.fragments

import android.os.Bundle
import android.view.View
import androidx.fragment.app.viewModels
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.ericson.contactapp.R
import com.ericson.contactapp.databinding.ContactFragmentBinding
import com.ericson.contactapp.ui.adapters.ContactAdapter
import com.ericson.contactapp.ui.base.BaseFragment
import com.ericson.contactapp.viewmodel.ContactViewModel

class ContactFragment : BaseFragment<ContactFragmentBinding>(ContactFragmentBinding::inflate) {
    private val contactViewModel:ContactViewModel by viewModels()
    private val cAdapter = ContactAdapter(this)
    private var contactSize = 0
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
         binding.rvcontacts.apply {
             adapter = cAdapter
             layoutManager = LinearLayoutManager(requireContext())
         }

        binding.btnAddContact.setOnClickListener {
            val action = ContactFragmentDirections.actionContactFragmentToContactFormFragment()
            findNavController().navigate(action)
        }
        contactViewModel.getAll().observe(requireActivity(), {
            cAdapter.submitList(it)
        })


    }
}