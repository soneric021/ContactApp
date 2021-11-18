package com.ericson.contactapp.ui.adapters

import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Filter
import androidx.core.os.bundleOf
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.RecyclerView
import com.ericson.contactapp.R
import com.ericson.contactapp.data.models.Contact
import com.ericson.contactapp.databinding.ContactLayoutBinding


class ContactAdapter(private val fragment: Fragment):RecyclerView.Adapter<ContactAdapter.ViewModel>() {
    private var list = listOf<Contact>()
    private var listFiltered = listOf<Contact>()
    fun submitList(l: List<Contact>){
        list = l
        listFiltered = list
        notifyDataSetChanged()
    }
    fun getFilter(): Filter? {
        return object : Filter() {
             override fun performFiltering(charSequence: CharSequence): FilterResults? {
                val charString = charSequence.toString()
                 var result = listOf<Contact>()
                result = if (charString.isEmpty()) {
                    list
                } else {
                    val filteredList: MutableList<Contact> = ArrayList()
                    for (row in list) {

                        // name match condition. this might differ depending on your requirement
                        // here we are looking for name or phone number match
                        if (row.name.toLowerCase().startsWith(charString.toLowerCase())) {
                            filteredList.add(row)
                        }
                    }
                    filteredList
                }
                val filterResults = FilterResults()
                filterResults.values = result
                return filterResults
            }

            override fun publishResults(charSequence: CharSequence?, filterResults: FilterResults) {
                listFiltered = filterResults.values as ArrayList<Contact>

                // refresh the list with filtered data
                notifyDataSetChanged()
            }
        }
    }
    class ViewModel(itemview: View):RecyclerView.ViewHolder(itemview){
        val binding: ContactLayoutBinding = ContactLayoutBinding.bind(itemView)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewModel {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.contact_layout, parent, false)
        return ContactAdapter.ViewModel(view)
    }

    override fun onBindViewHolder(holder: ViewModel, position: Int) {
        holder.binding.tvName.text = listFiltered[position].name
        holder.binding.tvPhone.text = listFiltered[position].phoneNumber
        holder.binding.tvletter.text = listFiltered[position].name.toUpperCase().subSequence(0, 1)


        holder.binding.root.setOnClickListener {
            val bundle = bundleOf("contact" to listFiltered[position])
            fragment.findNavController().navigate(R.id.action_contactFragment_to_contactFormFragment, bundle)
        }
    }

    override fun getItemCount(): Int = listFiltered.size
}