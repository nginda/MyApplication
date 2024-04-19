package com.example.myapplication

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.TextView

class UserAdapter(
    context: Context,
    users: List<DatabaseHelper.User>
) : ArrayAdapter<DatabaseHelper.User>(context, 0, users) {

    override fun getView(position: Int, convertView: View?, parent: ViewGroup): View {
        var itemView = convertView
        if (itemView == null) {
            itemView = LayoutInflater.from(context).inflate(android.R.layout.simple_list_item_1, parent, false)
        }

        val user = getItem(position)
        val textView = itemView!!.findViewById<TextView>(android.R.id.text1)
        textView.text = "${user?.firstName} ${user?.lastName}"

        return itemView
    }
}
