package com.movingroot.customseekbar

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.activity.viewModels
import com.movingroot.customseekbar.custom_view.TwoWaySeeker
import com.movingroot.customseekbar.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    private val viewModel: MainViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        initActivity()
    }

    private fun initActivity() {
        bindViewModel()
        setView()
    }

    private fun bindViewModel() {
        binding.viewModel = viewModel
        binding.lifecycleOwner = this
    }

    private fun setView() {
        binding.sliderContainer.removeAllViews()
        val seekers = viewModel.getStartAndEnd()
        binding.sliderContainer.addView(
            TwoWaySeeker.addView(this, seekers[0], seekers[1])
        )
    }
}
