package com.example.roominkotlin.fragment.list

import android.app.AlertDialog
import android.os.Bundle
import android.view.*
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.roominkotlin.R
import com.example.roominkotlin.model.User
import com.example.roominkotlin.ui.UserViewmodel
import com.google.android.material.floatingactionbutton.FloatingActionButton


class Firsttfragment : Fragment() {


    lateinit var floatingActionButton : FloatingActionButton
    lateinit var resclerview:RecyclerView
    private lateinit var adapte:ListAdapter
    private lateinit var viewmodel:UserViewmodel



    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view= inflater.inflate(R.layout.fragment_firstfragment, container, false)
        floatingActionButton=view.findViewById(R.id.floatingActionButton)
        resclerview=view.findViewById(R.id.list_data)

        adapte= ListAdapter()
        resclerview.adapter=adapte
        resclerview.layoutManager = LinearLayoutManager(requireContext())

        viewmodel=ViewModelProvider(this).get(UserViewmodel::class.java)
        viewmodel.readAllData.observe(viewLifecycleOwner, Observer { User ->

            adapte.setData(User)
        })

        floatingActionButton.setOnClickListener{
            findNavController().navigate(R.id.action_firstfragment_to_addfragment)
        }


        setHasOptionsMenu(true)
            return view
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        inflater.inflate(R.menu.delete_menu,menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {

        if (item.itemId==R.id.delete_item)
        {
            deleteAllUsers()

        }
        return super.onOptionsItemSelected(item)
    }

    private fun deleteAllUsers() {

        val builder = AlertDialog.Builder(requireContext())
        builder.setPositiveButton("yes"){_,_ ->

            viewmodel.deleteAllUser()
            Toast.makeText(requireContext(),"Deleted successfully ", Toast.LENGTH_LONG).show()

        }
        builder.setNegativeButton("No"){_,_ ->

        }
        builder.setTitle("Delete")
        builder.setMessage("Are you sure you want do delete?")
        builder.create().show()
    }


}