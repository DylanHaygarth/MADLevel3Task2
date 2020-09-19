package com.example.madlevel3task2

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.Toast
import androidx.core.os.bundleOf
import androidx.fragment.app.setFragmentResult
import androidx.navigation.fragment.findNavController
import kotlinx.android.synthetic.main.fragment_add_portal.*

const val ARG_PORTAL_TITLE = "arg_portal_name"
const val ARG_PORTAL_URL = "arg_portal_url"

class AddPortalFragment : Fragment() {

    override fun onCreateView(
            inflater: LayoutInflater, container: ViewGroup?,
            savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_add_portal, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

//        view.findViewById<Button>(R.id.button_second).setOnClickListener {
//            findNavController().navigate(R.id.action_SecondFragment_to_FirstFragment)
//        }
        btnAddPortal.setOnClickListener {
            onAddPortal()
        }
    }

    private fun onAddPortal() {
        val portalTitleText = etTitle.text.toString()
        val portalUrlText = etUrl.text.toString()

        if (portalTitleText.isNotBlank() && portalUrlText.isNotBlank()) {
            val args = Bundle()
            args.putString(ARG_PORTAL_TITLE, portalTitleText)
            args.putString(ARG_PORTAL_URL, portalUrlText)

            findNavController().navigate(R.id.action_addPortalFragment_to_portalOverviewFragment, args)
        } else {
            Toast.makeText(
                activity,
                "Title and url have to be filled in", Toast.LENGTH_SHORT
            ).show()
        }
    }
}