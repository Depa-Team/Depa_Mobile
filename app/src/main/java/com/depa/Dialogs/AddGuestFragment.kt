package com.depa.Dialogs

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import androidx.fragment.app.DialogFragment
import com.depa.R


class AddGuestFragment : DialogFragment() {


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_add_guest, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val txtFlat_id_add_name: EditText =view.findViewById(R.id.txtFlat_id_add_name)
        val txtFlat_id_add_email: EditText =view.findViewById(R.id.txtFlat_id_add_email)
        val txtFlat_id_add_password: EditText =view.findViewById(R.id.txtFlat_id_add_password)
        val txtFlat_id_add_phone: EditText =view.findViewById(R.id.txtFlat_id_add_phone)

        val accept_add_guest: Button =view.findViewById(R.id.accept_add_guest)
        accept_add_guest.setOnClickListener(){
            dismiss()
        }
        val cancel_add_guest: Button =view.findViewById(R.id.cancel_add_guest)
        cancel_add_guest.setOnClickListener(){
            dismiss()
        }

    }
}