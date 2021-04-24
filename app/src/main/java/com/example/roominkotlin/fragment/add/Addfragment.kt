package com.example.roominkotlin.fragment.add

import android.os.Bundle
import android.text.TextUtils
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.core.text.isDigitsOnly
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.example.roominkotlin.R
import com.example.roominkotlin.model.User
import com.example.roominkotlin.ui.UserViewmodel


class Addfragment : Fragment() {


    lateinit var firstname: EditText
    lateinit var laststname: EditText
    lateinit var Age: EditText
    lateinit var btnadd: Button

    private lateinit var myviewmodel: UserViewmodel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment

        val view = inflater.inflate(R.layout.fragment_addfragment, container, false)

        firstname = view.findViewById(R.id.firstname)
        laststname = view.findViewById(R.id.lastname)
        Age = view.findViewById(R.id.age)
        btnadd = view.findViewById(R.id.btnadd)


       myviewmodel = ViewModelProvider(this).get(UserViewmodel::class.java)

          btnadd.setOnClickListener {
               addUser()
           }


        return view
    }

    private fun addUser()
    {

        val Firstname:String=firstname.text.toString()
        val Lastname:String=laststname.text.toString()
        val Age=Age.text.toString()


        if (inputcheck(Firstname,Lastname,Age.toString())){
            val user= User(0,Firstname,Lastname,Age.toInt())

            
                myviewmodel.addUser(user)
                Toast.makeText(requireContext(),"add successfully",Toast.LENGTH_LONG).show()
                findNavController().navigate(R.id.action_addfragment_to_firstfragment)


        }
        else
        {
            Toast.makeText(requireContext(),"add fialled",Toast.LENGTH_LONG).show()

        }

    }

    private fun inputcheck(firstname:String, lastname:String, age: String):Boolean{

        return !(TextUtils.isEmpty(firstname) && lastname.isEmpty() && age.isEmpty())

    }

}