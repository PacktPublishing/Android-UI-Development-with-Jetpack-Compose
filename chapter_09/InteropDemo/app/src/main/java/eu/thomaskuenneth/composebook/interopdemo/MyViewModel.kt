package eu.thomaskuenneth.composebook.interopdemo

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class MyViewModel : ViewModel() {

    private val _sliderValue: MutableLiveData<Float> =
        MutableLiveData<Float>(0.5F)

    val sliderValue: LiveData<Float>
        get() = _sliderValue

    fun setSliderValue(value: Float) {
        _sliderValue.value = value
    }
}