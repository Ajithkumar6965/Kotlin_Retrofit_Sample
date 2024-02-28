package me.ajith.learning.retrofitsample.data.remote

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import me.ajith.learning.retrofitsample.R

class UserAdapter(private var users:ArrayList<UserInfo>) : RecyclerView.Adapter<UserAdapter.UserDataViewHolder>() {
    class UserDataViewHolder(itemView:View):RecyclerView.ViewHolder(itemView){
        private var title : TextView
        private var body : TextView
        init {
            title = itemView.findViewById(R.id.title)
            body = itemView.findViewById(R.id.body)
        }

        fun bind(userInfo: UserInfo){
            title.text = userInfo.title
            body.text = userInfo.body
        }

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): UserDataViewHolder {
        return UserDataViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.item_view_layout,parent,false))
    }

    override fun getItemCount(): Int = users.size

    override fun onBindViewHolder(holder: UserDataViewHolder, position: Int) {
        holder.bind(users[position])
    }

    fun addUsers(newUsers:List<UserInfo>){
        users.addAll(newUsers)
    }
}