package com.movingroot.customseekbar

import androidx.lifecycle.LiveData
import androidx.lifecycle.MediatorLiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import kotlin.math.roundToInt

class MainViewModel : ViewModel() {
    private val _startSeeker = MutableLiveData<Float>().apply {
        value = 1f
    }
    private val _endSeeker = MutableLiveData<Float>().apply {
        value = 100f
    }
    val seekingInterval = MediatorLiveData<String>()

    init {
        seekingInterval.run {
            addSource(_startSeeker) {
                this.value = "${_startSeeker.floor()} ~ ${_endSeeker.floor()}"
            }
            addSource(_endSeeker) {
                this.value = "${_startSeeker.floor()} ~ ${_endSeeker.floor()}"
            }
        }
    }

    private fun LiveData<Float>.floor(): Int {
        val flooredValue = kotlin.math.floor(this.value ?: 0f)
        return flooredValue.roundToInt()
    }

    fun getStartAndEnd(): Array<MutableLiveData<Float>> = arrayOf(_startSeeker, _endSeeker)
}
