package com.example.madlevel3task2

import android.content.ContentValues.TAG
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.fragment.app.setFragmentResultListener
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.fragment_portal_overview.*

private val portals = arrayListOf<Portal>()

class PortalOverviewFragment : Fragment() {
    private val portalAdapter = PortalAdapter(portals)

    override fun onCreateView(
            inflater: LayoutInflater, container: ViewGroup?,
            savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_portal_overview, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        initViews()
        observeAddPortal()
    }

    private fun initViews () {
        rvPortals.adapter = portalAdapter
        rvPortals.layoutManager = GridLayoutManager(
            context, 2
        )
    }

    private fun observeAddPortal() {
        val portalName = arguments?.getString(ARG_PORTAL_TITLE)
        val portalUrl = arguments?.getString(ARG_PORTAL_URL)

        if (portalName != null && portalUrl != null) {
            val portal = Portal(portalName, portalUrl)

            portals.add(portal)
            portalAdapter.notifyDataSetChanged()
        }
        Log.d(TAG, portals.size.toString())
    }
}