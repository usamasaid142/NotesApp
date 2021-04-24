package com.example.roominkotlin.fragment.update

import android.app.AlertDialog
import android.os.Bundle
import android.text.TextUtils
import android.view.*
import androidx.fragment.app.Fragment
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.NavArgs
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.example.roominkotlin.R
import com.example.roominkotlin.model.User
import com.example.roominkotlin.ui.UserViewmodel
import kotlinx.android.synthetic.main.fragment_update.*
import kotlinx.android.synthetic.main.fragment_update.view.*


class UpdateFragment : Fragment() {

    private val args by navArgs<UpdateFragmentArgs>()

    private lateinit var viewmodel: UserViewmodel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view= inflater.inflate(R.layout.fragment_update, container, false)

        view.updatefirstname.setText(args.currentuser.firstname)
        view.update_lastname.setText(args.currentuser.lastname)
        view.update_age.setText(args.currentuser.age.toString())

        viewmodel=ViewModelProvider(this).get(UserViewmodel::class.java)

        view.btnupdate.setOnClickListener {

            updateIem()
        }


        // option menu

        setHasOptionsMenu(true)
        return view
    }

    private fun updateIem() {

        val firstname=updatefirstname.text.toString()
        val lastname=update_lastname.text.toString()
        val age=update_age.text.toString()

        val userupdate=User(args.currentuser.id,firstname,lastname,age.toInt())

       if (inputCheck(firstname,lastname,age.toString()))
       {

           viewmodel.updateUser(userupdate)
           Toast.makeText(requireContext(),"Updated successfully ",Toast.LENGTH_LONG).show()
           findNavController().navigate(R.id.action_updateFragment_to_firstfragment)

       }else
       {
           Toast.makeText(requireContext(),"fields is empty ",Toast.LENGTH_LONG).show()
       }
    }

    private fun inputCheck(firstname:String,lastname:String,age:String):Boolean
    {
        return !(TextUtils.isEmpty(firstname) && TextUtils.isEmpty(lastname) && age.isEmpty())
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        inflater.inflate(R.menu.delete_menu,menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if (item.itemId==R.id.delete_item)
        {
            deleteUser()
        }

        return super.onOptionsItemSelected(item)
    }

    private fun deleteUser() {

        val builder =AlertDialog.Builder(requireContext())
        builder.setPositiveButton("yes"){_,_ ->

            viewmodel.deleteUser(args.currentuser)
            Toast.makeText(requireContext(),"Deleted successfully ",Toast.LENGTH_LONG).show()
            findNavController().navigate(R.id.action_updateFragment_to_firstfragment)

        }
        builder.setNegativeButton("No"){_,_ ->

        }
        builder.setTitle("Delete ${args.currentuser.firstname}?")
        builder.setMessage("Are you sure you want do delete ${args.currentuser.firstname}?")
        builder.create().show()
    }

}