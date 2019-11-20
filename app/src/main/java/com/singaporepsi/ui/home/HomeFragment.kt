package com.singaporepsi.ui.home

import android.graphics.Bitmap
import android.graphics.Canvas
import android.graphics.Paint
import android.graphics.drawable.Drawable
import android.os.Bundle
import android.view.View
import androidx.core.content.ContextCompat
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.Observer
import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.OnMapReadyCallback
import com.google.android.gms.maps.model.*
import com.singaporepsi.R
import com.singaporepsi.ui.BottomSheetBehavior
import com.singaporepsi.ui.base.BaseFragment
import kotlinx.android.synthetic.main.fragment_home.*

class HomeFragment : BaseFragment(R.layout.fragment_home), OnMapReadyCallback {

    private val homeViewModel: HomeViewModel by activityViewModels { viewModelFactory }
    private var map: GoogleMap? = null
    private lateinit var bottomSheetBehavior: BottomSheetBehavior<*>

    override fun getViewModel() = homeViewModel

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        bottomSheetBehavior = BottomSheetBehavior.from(bottomSheet)
        bottomSheetBehavior.state = BottomSheetBehavior.STATE_HIDDEN
        val bottomSheetCallback = object : BottomSheetBehavior.BottomSheetCallback {
            override fun onStateChanged(bottomSheet: View, newState: Int) {
                // Orient the expand/collapse icon accordingly
                val rotation = when (newState) {
                    BottomSheetBehavior.STATE_EXPANDED -> 0f
                    BottomSheetBehavior.STATE_COLLAPSED -> 180f
                    BottomSheetBehavior.STATE_HIDDEN -> 180f
                    else -> return
                }
                expandIcon.animate().rotationX(rotation).start()
            }

            override fun onSlide(bottomSheet: View, slideOffset: Float) {
                // Due to content being visible beneath the navigation bar, apply a short alpha
                // transition
                //descriptionScrollview.alpha = slideOffsetToAlpha(slideOffset, ALPHA_TRANSITION_START, ALPHA_TRANSITION_END)
            }
        }
        bottomSheetBehavior.addBottomSheetCallback(bottomSheetCallback)
        // Trigger the callbacks once on layout to set the state of the sheet content & FAB.
        bottomSheet.post {
            val state = bottomSheetBehavior.state
            val slideOffset = when (state) {
                BottomSheetBehavior.STATE_EXPANDED -> 1f
                BottomSheetBehavior.STATE_COLLAPSED -> 0f
                else -> -1f // BottomSheetBehavior.STATE_HIDDEN
            }
            bottomSheetCallback.onStateChanged(bottomSheet, state)
            bottomSheetCallback.onSlide(bottomSheet, slideOffset)
        }

        // Make the header clickable.
        clickable?.setOnClickListener {
            if (bottomSheetBehavior.state == BottomSheetBehavior.STATE_COLLAPSED) {
                bottomSheetBehavior.state = BottomSheetBehavior.STATE_EXPANDED
            } else {
                bottomSheetBehavior.state = BottomSheetBehavior.STATE_COLLAPSED
            }
        }

        mapView?.onCreate(savedInstanceState)
        mapView?.getMapAsync(this)
    }

    override fun onMapReady(p0: GoogleMap?) {
        map = p0
        map?.uiSettings?.apply {
            isCompassEnabled = false
            isIndoorLevelPickerEnabled = false
            isMapToolbarEnabled = false
            isMyLocationButtonEnabled = false
            isRotateGesturesEnabled = false
            isScrollGesturesEnabled = false
            isScrollGesturesEnabledDuringRotateOrZoom = false
            isTiltGesturesEnabled = false
            isZoomControlsEnabled = false
            isZoomGesturesEnabled = false
        }
        map?.setOnMapLoadedCallback {
            map?.moveCamera(CameraUpdateFactory.newLatLngZoom(LatLng(1.360968, 103.810796), 10f))
            homeViewModel.regionsLiveData.observe(viewLifecycleOwner, Observer {
                val bounds = LatLngBounds.builder()
                val markers = mutableListOf<Marker?>()
                it.forEach {
                    bounds.include(it.getLatLng())
                    val marker = map?.addMarker(
                        MarkerOptions().title(it.name.capitalize()).position(it.getLatLng()).icon(
                            createMarker(it.psi)
                        )
                    )
                    marker?.tag = it.name
                    markers.add(marker)
                }
                map?.setLatLngBoundsForCameraTarget(
                    LatLngBounds(
                        LatLng(1.146583, 103.588823),
                        LatLng(1.492246, 104.114996)
                    )
                )
                map?.setMaxZoomPreference(11f)
                map?.setMinZoomPreference(11f)
                map?.moveCamera(
                    CameraUpdateFactory.newLatLngBounds(
                        bounds.build(),
                        (25.0 * requireContext().resources.displayMetrics.density).toInt()
                    )
                )
            })
        }
        map?.setOnMarkerClickListener {
            homeViewModel.showRegion(it.tag as String?)
            false
        }
    }

    private fun createMarker(quality: Int): BitmapDescriptor? {
        val canvas = Canvas()
        val drawable = getDrawable(quality)
        val bitmap = Bitmap.createBitmap(
            drawable.intrinsicWidth,
            drawable.intrinsicHeight,
            Bitmap.Config.ARGB_8888
        )
        canvas.setBitmap(bitmap)
        drawable.setBounds(0, 0, drawable.intrinsicWidth, drawable.intrinsicHeight)
        drawable.draw(canvas)
        val paint = Paint()
        paint.isAntiAlias = true
        paint.color = getColor(quality)
        paint.textSize = 14.0f * requireContext().resources.displayMetrics.density
        paint.textAlign = Paint.Align.CENTER
        canvas.drawText(
            quality.toString(),
            drawable.intrinsicWidth / 2.0f,
            (drawable.intrinsicHeight / 2.0f) - ((paint.descent() + paint.ascent()) / 2),
            paint
        )
        return BitmapDescriptorFactory.fromBitmap(bitmap)
    }

    private fun getDrawable(quality: Int): Drawable {
        val resId = when {
            quality <= 100 -> {
                R.drawable.circle_green
            }
            quality <= 300 -> {
                R.drawable.circle_yellow
            }
            else -> {
                R.drawable.circle_red
            }
        }
        return ContextCompat.getDrawable(requireContext(), resId)!!
    }

    private fun getColor(quality: Int): Int {
        val resId = when {
            quality <= 100 -> {
                R.color.colorGreen
            }
            quality <= 300 -> {
                R.color.colorYellow
            }
            else -> {
                R.color.colorRed
            }
        }
        return ContextCompat.getColor(requireContext(), resId)
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