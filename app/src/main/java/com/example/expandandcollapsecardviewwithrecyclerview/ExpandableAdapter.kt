package com.example.expandandcollapsecardviewwithrecyclerview

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageButton
import android.widget.LinearLayout
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class ExpandableAdapter(
    private val personList: List<Person>
) : RecyclerView.Adapter<ExpandableAdapter.MyViewHolder>() {

    class MyViewHolder(
        itemView: View
    ) : RecyclerView.ViewHolder(itemView) {
        fun bind(person: Person) {
            val txtName = itemView.findViewById<TextView>(R.id.txt_name)
            val imgMore = itemView.findViewById<ImageButton>(R.id.img_more)
            val layoutExpand = itemView.findViewById<LinearLayout>(R.id.layout_expand)
            val txtContent = itemView.findViewById<TextView>(R.id.contentTv)

            txtName.text = person.name
            txtContent.text = person.description

            imgMore.setOnClickListener {
                // 1
                val show = toggleLayout(!person.isExpanded, it, layoutExpand)
                person.isExpanded = show
            }
        }

        private fun toggleLayout(isExpanded: Boolean, view: View, layoutExpand: LinearLayout): Boolean {
            // 2
            ToggleAnimation.toggleArrow(view, isExpanded)
            if (isExpanded) {
                ToggleAnimation.expand(layoutExpand)
            } else {
                ToggleAnimation.collapse(layoutExpand)
            }
            return isExpanded
        }
    }


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        return MyViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.item_row, parent, false))
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        holder.bind(personList[position])
    }

    override fun getItemCount(): Int {
        return personList.size
    }

}