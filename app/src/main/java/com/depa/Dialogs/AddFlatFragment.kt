package com.depa.Dialogs

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.fragment.app.DialogFragment
import com.depa.R


class AddFlatFragment : DialogFragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_add_flat, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val addFlatDialogButton=view.findViewById<Button>(R.id.addFlatDialogButton)
        val cancelAddFlatDialogButton=view.findViewById<Button>(R.id.cancelAddFlatDialogButton)

        addFlatDialogButton.setOnClickListener(){
            dismiss()
        }

        cancelAddFlatDialogButton.setOnClickListener(){
            dismiss()
        }
    }
}