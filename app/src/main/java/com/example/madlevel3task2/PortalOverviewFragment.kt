package com.example.madlevel3task2

import android.content.ContentValues.TAG
import android.net.Uri
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.Toast
import androidx.browser.customtabs.CustomTabsIntent
import androidx.fragment.app.setFragmentResultListener
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.fragment_portal_overview.*

private val portals = arrayListOf<Portal>()

class PortalOverviewFragment : Fragment() {
    private val portalAdapter = PortalAdapter(portals) {partItem: Portal -> partItemClicked(partItem)}

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
    }

    // items within in the recycler view can be clicked using a click listener
    private fun partItemClicked(partItem : Portal) {
        val builder = CustomTabsIntent.Builder()
        val customTabsIntent = builder.build()
        
        customTabsIntent.launchUrl(this.requireContext(), Uri.parse(partItem.url))
    }
}