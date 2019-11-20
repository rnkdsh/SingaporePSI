package com.singaporepsi.ui.home

import androidx.lifecycle.MutableLiveData
import com.singaporepsi.data.AppRepository
import com.singaporepsi.model.RegionMetadata
import com.singaporepsi.result.Result
import com.singaporepsi.result.succeeded
import com.singaporepsi.ui.base.BaseViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

class HomeViewModel @Inject constructor(
    private val appRepository: AppRepository
) : BaseViewModel() {

    val regionsLiveData = MutableLiveData<List<RegionMetadata>>()

    init {
        fetchPSI()
    }

    fun fetchPSI() {
        scope.launch {
            val psi = appRepository.psi()
            if (psi is Result.Success) {
                if (psi.succeeded) {
                    regionsLiveData.postValue(psi.data.regionMetadata.filter { !it.isNational() })
                }
            } else if (psi is Result.Error) {
                showMessage(psi.error.message)
            }
        }
    }
}