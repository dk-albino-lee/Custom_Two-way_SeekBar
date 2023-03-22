package com.movingroot.customseekbar.custom_view

import android.content.Context
import android.view.LayoutInflater
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.lifecycle.MutableLiveData
import com.movingroot.customseekbar.databinding.TwoWaySeekerBinding

class TwoWaySeeker : ConstraintLayout {
    private lateinit var binding: TwoWaySeekerBinding
    private val startSeeker: MutableLiveData<Float>
    private val endSeeker: MutableLiveData<Float>

    constructor(
        context: Context,
        _start: MutableLiveData<Float>,
        _end: MutableLiveData<Float>
    ) : super(context) {
        startSeeker = _start
        endSeeker = _end
        init()
    }

    private fun init() {
        binding = TwoWaySeekerBinding.inflate(
            LayoutInflater.from(context), this, true
        )
        setSlider()
    }

    private fun setSlider() = binding.rangeSlider.run {
        valueFrom = 1f
        valueTo = 100f
        stepSize = 1f
        isTickVisible = false
        values = listOf(startSeeker.value!!, endSeeker.value!!)
        addOnChangeListener { slider, _, _ ->
            slider.values.setSeekers()
        }
    }

    private fun List<Float>.setSeekers() {
        startSeeker.value = this[0]
        endSeeker.value = this[1]
    }

    companion object {
        fun addView(
            context: Context,
            start: MutableLiveData<Float>,
            end: MutableLiveData<Float>
        ): TwoWaySeeker {
            return TwoWaySeeker(context, start, end)
        }
    }
}
