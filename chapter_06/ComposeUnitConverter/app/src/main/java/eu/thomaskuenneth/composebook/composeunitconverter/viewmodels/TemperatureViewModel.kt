package eu.thomaskuenneth.composebook.composeunitconverter.viewmodels

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class TemperatureViewModel : ViewModel() {

    private val _scale: MutableLiveData<Int> =
        MutableLiveData<Int>()

    val scale: LiveData<Int>
        get() = _scale

    fun setScale(value: Int) {
        _scale.value = value
    }
}