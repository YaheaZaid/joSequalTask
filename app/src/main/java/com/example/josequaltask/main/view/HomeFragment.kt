package com.example.josequaltask.main.view

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.josequaltask.R
import com.example.josequaltask.base.BaseFragment
import com.example.josequaltask.databinding.HomeFragmentBinding
import com.example.josequaltask.main.adabter.ItemsAdapter
import com.example.josequaltask.main.viewModel.HomeViewModel
import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.OnMapReadyCallback
import com.google.android.gms.maps.SupportMapFragment
import com.google.android.gms.maps.model.LatLng

class HomeFragment  : BaseFragment(), OnMapReadyCallback {
    private lateinit var viewModel:HomeViewModel
    private lateinit var binding: HomeFragmentBinding
    private lateinit var googleMap: GoogleMap
    private lateinit var recyclerView: RecyclerView
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding= HomeFragmentBinding.inflate(inflater,container,false)
        viewModel=ViewModelProvider(this).get(HomeViewModel::class.java)
        val mapFragment = childFragmentManager.findFragmentById(R.id.map) as SupportMapFragment
        mapFragment.getMapAsync(this)
        return binding.root

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupRecyclerView()
    }
    override fun onMapReady(map: GoogleMap) {
        googleMap = map

        googleMap.uiSettings.isZoomControlsEnabled = true

        val defaultLocation = LatLng(39.7749, 36.4194)
        googleMap.moveCamera(CameraUpdateFactory.newLatLngZoom(defaultLocation, 6f))
        viewModel.addMarkers(googleMap)
        googleMap.setOnMarkerClickListener { marker ->
            viewModel.showItemDetailDialog(marker,requireContext())
            true
        }
    }
    private fun setupRecyclerView() {
        val layoutManager = LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false)
        recyclerView = binding.recyclerView
        recyclerView.layoutManager = layoutManager
        val adapter = ItemsAdapter(viewModel.mapItems) { position ->
            viewModel.moveCameraToPosition(position,googleMap)
        }
        recyclerView.adapter = adapter
    }

}