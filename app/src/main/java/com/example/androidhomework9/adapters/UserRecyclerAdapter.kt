package com.example.androidhomework9.adapters

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.androidhomework9.R
import com.example.androidhomework9.api.dto.response.DataItem
import com.squareup.picasso.Picasso

class UserRecyclerAdapter(private val users: List<DataItem>) :
    RecyclerView.Adapter<UserRecyclerAdapter.UserViewHolder>() {

    class UserViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        private val image: ImageView = itemView.findViewById(R.id.imageView)
        private val nameView: TextView = itemView.findViewById(R.id.textView)
        private val mailView: TextView = itemView.findViewById(R.id.textView2)
        private lateinit var user: DataItem

        @SuppressLint("SetTextI18n")
        fun onBind(user: DataItem) {
            Picasso.get().load(user.avatar).into(image)
            nameView.text = "${user.firstName} ${user.lastName}"
            mailView.text = user.email
            this.user = user
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): UserViewHolder {
        val itemView = LayoutInflater.from(parent.context).inflate(R.layout.list_resources_activity, parent, false)

        return UserViewHolder(itemView)
    }

    override fun onBindViewHolder(holder: UserViewHolder, position: Int) {
        holder.onBind(users[position])
    }

    override fun getItemCount() = users.size
}