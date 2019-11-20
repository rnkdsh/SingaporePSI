package com.singaporepsi.ui.home

import android.os.Bundle
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.Observer
import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.OnMapReadyCallback
import com.google.android.gms.maps.model.BitmapDescriptorFactory
import com.google.android.gms.maps.model.LatLngBounds
import com.google.android.gms.maps.model.Marker
import com.google.android.gms.maps.model.MarkerOptions
import com.singaporepsi.R
import com.singaporepsi.ui.base.BaseFragment
import kotlinx.android.synthetic.main.fragment_home.*

class HomeFragment : BaseFragment(R.layout.fragment_home), OnMapReadyCallback {

    private val homeViewModel: HomeViewModel by activityViewModels { viewModelFactory }
    private var map: GoogleMap? = null

    override fun getViewModel() = homeViewModel

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        mapView?.onCreate(savedInstanceState)
        mapView?.getMapAsync(this)
    }

    override fun onMapReady(p0: GoogleMap?) {
        map = p0
        map?.setOnMapLoadedCallback {
            homeViewModel.regionsLiveData.observe(viewLifecycleOwner, Observer {
                val bounds = LatLngBounds.builder()
                val markers = mutableListOf<Marker?>()
                it.forEach {
                    bounds.include(it.getLatLng())
                    markers.add(
                        map?.addMarker(
                            MarkerOptions().title(it.name).position(it.getLatLng()).icon(
                                BitmapDescriptorFactory.defaultMarker()
                            )
                        )
                    )
                }
                map?.setLatLngBoundsForCameraTarget(bounds.build())
                map?.moveCamera(CameraUpdateFactory.newLatLngBounds(bounds.build(), 50))
            })
        }
    }

    override fun onResume() {
        mapView?.onResume()
        super.onResume()
    }

    override fun onPause() {
        super.onPause()
        mapView?.onPause()
    }

    override fun onStart() {
        super.onStart()
        mapView?.onStart()
    }

    override fun onStop() {
        super.onStop()
        mapView?.onStop()
    }

    override fun onLowMemory() {
        super.onLowMemory()
        mapView?.onLowMemory()
    }

    override fun onDestroy() {
        super.onDestroy()
        mapView?.onDestroy()
    }
}