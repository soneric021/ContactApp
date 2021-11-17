package com.ericson.contactapp.ui.adapters

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.os.bundleOf
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.RecyclerView
import com.ericson.contactapp.R
import com.ericson.contactapp.data.models.Contact
import com.ericson.contactapp.databinding.ContactLayoutBinding
import com.ericson.contactapp.ui.fragments.ContactFragmentDirections

class ContactAdapter(private val fragment: Fragment):RecyclerView.Adapter<ContactAdapter.ViewModel>() {
    private var list = listOf<Contact>()
    fun submitList(l:List<Contact>){
        list = l
        notifyDataSetChanged()
    }
    class ViewModel(itemview:View):RecyclerView.ViewHolder(itemview){
        val binding: ContactLayoutBinding = ContactLayoutBinding.bind(itemView)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewModel {
        val view = LayoutInflater.from(parent.context).inflate( R.layout.contact_layout, parent, false)
        return ContactAdapter.ViewModel(view)
    }

    override fun onBindViewHolder(holder: ViewModel, position: Int) {
        holder.binding.tvName.text = list[position].name
        holder.binding.tvPhone.text = list[position].phoneNumber
        holder.binding.tvletter.text = list[position].name.toUpperCase().subSequence(0,1)


        holder.binding.root.setOnClickListener {
            val bundle = bundleOf("contact" to list[position])
            fragment.findNavController().navigate(R.id.action_contactFragment_to_contactFormFragment, bundle)
        }
    }

    override fun getItemCount(): Int = list.size
}