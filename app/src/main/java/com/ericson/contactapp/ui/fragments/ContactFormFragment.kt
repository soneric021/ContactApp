package com.ericson.contactapp.ui.fragments

import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.core.view.isEmpty
import androidx.core.widget.addTextChangedListener
import androidx.fragment.app.viewModels
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.ericson.contactapp.data.models.Contact
import com.ericson.contactapp.data.models.Result
import com.ericson.contactapp.databinding.ContactFormFragmentBinding
import com.ericson.contactapp.ui.base.BaseFragment
import com.ericson.contactapp.viewmodel.ContactViewModel

class ContactFormFragment : BaseFragment<ContactFormFragmentBinding>(ContactFormFragmentBinding::inflate) {
    private val contactViewModel:ContactViewModel by viewModels()
    private  var contact: Contact? = null
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.backbtn.setOnClickListener {
            activity?.onBackPressed()
        }

        contactViewModel.message.observe(requireActivity(), {
            when(it){
                 is Result.Success ->{
                     Toast.makeText(requireContext(), "Se han guardado sus cambios", Toast.LENGTH_SHORT).show()
                     activity?.onBackPressed()
                 }
                is Result.Error -> {
                    Toast.makeText(requireContext(), "Hubo un error al crear su contacto", Toast.LENGTH_SHORT).show()
                }
                else -> {

                }
            }
        })
        contact = arguments?.get("contact") as Contact?
        contact?.let {
            binding.tvtitle.setText("Editar contacto")
            binding.etEmail.editText?.setText(it.email)
            binding.etName.editText?.setText(it.name)
            binding.etPhone.editText?.setText(it.phoneNumber)
        }
        binding.etName.editText?.addTextChangedListener() {
            binding.etName.isErrorEnabled = false
        }
        binding.etPhone.editText?.addTextChangedListener() {
            binding.etPhone.isErrorEnabled = false
        }
        binding.etEmail.editText?.addTextChangedListener() {
            binding.etEmail.isErrorEnabled = false
        }
        binding.btnAdd.setOnClickListener {
            addContact()
        }
    }

    fun addContact(){
        val name = binding.etName.editText?.text.toString()
        val phone = binding.etPhone.editText?.text.toString()
        val email = binding.etEmail.editText?.text.toString()
        when {
            binding.etName.editText?.text.isNullOrEmpty() -> {
                binding.etName.error = "El nombre es requerido*"
                return
            }
            binding.etPhone.editText?.text.isNullOrEmpty() -> {
                binding.etPhone.error = "El telefono es requerido*"
                return
            }
            binding.etEmail.editText?.text.isNullOrEmpty() -> {
                binding.etEmail.error = "El email es requerido*"
                return
            }
            contact == null -> {
                contact = Contact(
                        name = name,
                        phoneNumber = phone,
                        email = email
                )
                contactViewModel.save(contact!!)
            }
            else -> {
                contact?.let {
                    it.name = name
                    it.email = email
                    it.phoneNumber = phone
                    contactViewModel.update(it)
                }

            }
        }

    }
}