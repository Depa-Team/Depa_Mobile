package com.depa.Fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity
import com.depa.Dialogs.AddFlatFragment
import com.depa.Dialogs.EditFlatFragment
import com.depa.R

class HomeFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_home, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val AddFlatButton: Button =view.findViewById(R.id.addFlatButton)

        AddFlatButton.setOnClickListener(){
            val showPopUP= AddFlatFragment()
            showPopUP.show((activity as AppCompatActivity).supportFragmentManager,"showPopUp")


        }
    }


}