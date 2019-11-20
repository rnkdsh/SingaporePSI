package com.singaporepsi.ui.home

import androidx.lifecycle.MutableLiveData
import com.singaporepsi.data.AppRepository
import com.singaporepsi.model.ApiResponse
import com.singaporepsi.model.RegionMetadata
import com.singaporepsi.result.Result
import com.singaporepsi.result.succeeded
import com.singaporepsi.ui.base.BaseViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject
import kotlin.math.roundToInt

class HomeViewModel @Inject constructor(
    private val appRepository: AppRepository
) : BaseViewModel() {

    val regionsLiveData = MutableLiveData<List<RegionMetadata>>()
    val psiLiveData = MutableLiveData<ApiResponse>()

    init {
        fetchPSI()
    }

    fun fetchPSI() {
        scope.launch {
            val psi = appRepository.psi()
            if (psi is Result.Success) {
                if (psi.succeeded) {
                    psiLiveData.postValue(psi.data)
                    psi.data.regionMetadata.forEach {
                        when (it.name) {
                            "national" -> {
                                it.psi =
                                    psi.data.items[0].readings.psiTwentyFourHourly.national.roundToInt()
                            }
                            "central" -> {
                                it.psi =
                                    psi.data.items[0].readings.psiTwentyFourHourly.central.roundToInt()
                            }
                            "east" -> {
                                it.psi =
                                    psi.data.items[0].readings.psiTwentyFourHourly.east.roundToInt()
                            }
                            "west" -> {
                                it.psi =
                                    psi.data.items[0].readings.psiTwentyFourHourly.west.roundToInt()
                            }
                            "north" -> {
                                it.psi =
                                    psi.data.items[0].readings.psiTwentyFourHourly.north.roundToInt()
                            }
                            "south" -> {
                                it.psi =
                                    psi.data.items[0].readings.psiTwentyFourHourly.south.roundToInt()
                            }
                        }
                    }
                    regionsLiveData.postValue(psi.data.regionMetadata.filter { !it.isNational() })
                }
            } else if (psi is Result.Error) {
                showMessage(psi.error.message)
            }
        }
    }

    fun showRegion(tag: String?) {
        psiLiveData.value?.items?.get(0)?.readings
    }
}