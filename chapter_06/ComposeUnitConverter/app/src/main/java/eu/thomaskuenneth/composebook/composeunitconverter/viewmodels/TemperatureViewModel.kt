package eu.thomaskuenneth.composebook.composeunitconverter.viewmodels

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import eu.thomaskuenneth.composebook.composeunitconverter.R

class TemperatureViewModel : ViewModel() {

    private val _scale: MutableLiveData<Int> =
        MutableLiveData<Int>()

    val scale: LiveData<Int>
        get() = _scale

    fun setScale(value: Int) {
        _scale.value = value
    }

    private val _convertedTemperature: MutableLiveData<Float> =
        MutableLiveData<Float>()

    val convertedTemperature: LiveData<Float>
        get() = _convertedTemperature

    fun updateConvertedTemperature(value: Float) {
        _convertedTemperature.value = if (_scale.value == R.string.celsius)
            (value * 1.8F) + 32F
        else
            (value - 32F) / 1.8F
    }
}