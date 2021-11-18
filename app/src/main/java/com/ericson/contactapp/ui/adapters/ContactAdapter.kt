package com.ericson.contactapp.ui.adapters

import android.content.res.ColorStateList
import android.graphics.Color
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
import java.util.*
import kotlin.collections.ArrayList


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
        val rnd = Random()
        val color: Int = Color.argb(255, rnd.nextInt(256), rnd.nextInt(256), rnd.nextInt(256))
        if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.LOLLIPOP) {
            holder.binding.tvletter.backgroundTintList = ColorStateList.valueOf(color)
        }
        holder.binding.root.setOnClickListener {
            val bundle = bundleOf("contact" to listFiltered[position])
            fragment.findNavController().navigate(R.id.action_contactFragment_to_contactFormFragment, bundle)
        }
    }

    override fun getItemCount(): Int = listFiltered.size
}