package com.depa.Fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ProgressBar
import android.widget.TextView
import com.depa.R

class GuestHomeFragment : Fragment() {

    private var progress=10
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_guest_home, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val progress_bar: ProgressBar=view.findViewById(R.id.progress_bar)
        val text_view_progress: TextView=view.findViewById(R.id.text_view_progress)
        progress_bar.progress=progress
        text_view_progress.setText(" Te quedan \n    30 dias")
    }
}