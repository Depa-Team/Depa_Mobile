package com.depa.Dialogs

import Beans.Flats
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import android.widget.TextSwitcher
import android.widget.TextView
import androidx.fragment.app.DialogFragment
import com.depa.R

class EditFlatFragment(flat: Flats) : DialogFragment() {

    val flat_=flat

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_edit_flat, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val txtEditInquilino: TextView=view.findViewById(R.id.txtEditInquilino)
        val txtFlat_id_edit_name: EditText=view.findViewById(R.id.txtFlat_id_edit_name)
        val txtFlat_id_edit_cost: EditText=view.findViewById(R.id.txtFlat_id_edit_cost)

        txtEditInquilino.setText(flat_.guestId.toString())
        txtFlat_id_edit_name.setText(flat_.flatName)
        txtFlat_id_edit_cost.setText(flat_.price.toString())

        val editFlatDialogButton=view.findViewById<Button>(R.id.editFlatDialogButton)
        editFlatDialogButton.setOnClickListener(){
            dismiss()
        }

    }

}