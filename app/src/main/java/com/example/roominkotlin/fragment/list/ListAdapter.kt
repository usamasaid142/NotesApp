package com.example.roominkotlin.fragment.list

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.navigation.findNavController
import androidx.recyclerview.widget.RecyclerView
import com.example.roominkotlin.R
import com.example.roominkotlin.model.User
import kotlinx.android.synthetic.main.list_fragment_item.view.*

class ListAdapter : RecyclerView.Adapter<ListAdapter.MyViewholdetr>() {


    var listuser = emptyList<User>()

    class MyViewholdetr(itemView: View) : RecyclerView.ViewHolder(itemView) {

        val  firstname:TextView=itemView.findViewById(R.id.firstname)
        val  lastname:TextView=itemView.findViewById(R.id.lastname)
        val  id:TextView=itemView.findViewById(R.id.id)
        val  age:TextView=itemView.findViewById(R.id.age)


    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewholdetr {

        val view= LayoutInflater.from(parent.context).inflate(R.layout.list_fragment_item,parent,false)
        return MyViewholdetr(view)

    }

    override fun getItemCount(): Int {
        if (listuser.size==null)
            return 0
        return listuser.size
    }

    override fun onBindViewHolder(holder: MyViewholdetr, position: Int) {

        val user:User=listuser.get(position)

        holder.id.text=user.id.toString()
        holder.firstname.text=user.firstname
        holder.lastname.text=user.lastname
        holder.age.text=user.age.toString()
        holder.itemView.list_item_view.setOnClickListener {

            val action=FirsttfragmentDirections.actionFirstfragmentToUpdateFragment(user)
            holder.itemView.findNavController().navigate(action)
        }


    }

    fun setData(listuser:List<User>)
    {
        this.listuser = listuser
        notifyDataSetChanged()
    }
}