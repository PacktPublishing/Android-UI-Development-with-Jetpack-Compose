package eu.thomaskuenneth.composebook.composeunitconverter.viewmodels

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import eu.thomaskuenneth.composebook.composeunitconverter.R
import eu.thomaskuenneth.composebook.composeunitconverter.Repository

class TemperatureViewModel(private val repository: Repository) : ViewModel() {

    private val _scale: MutableLiveData<Int> = MutableLiveData(
        repository.getInt("scale", R.string.celsius)
    )

    val scale: LiveData<Int>
        get() = _scale

    fun setScale(value: Int) {
        _scale.value = value
        repository.putInt("scale", value)
    }

    private val _temperature: MutableLiveData<String> = MutableLiveData(
        repository.getString("temperature", "")
    )

    val temperature: LiveData<String>
        get() = _temperature

    fun getTemperatureAsFloat(): Float = (_temperature.value ?: "").let {
        return try {
            it.toFloat()
        } catch (e: NumberFormatException) {
            Float.NaN
        }
    }

    fun setTemperature(value: String) {
        _temperature.value = value
        repository.putString("temperature", value)
    }

    fun convert() = getTemperatureAsFloat().let {
        if (!it.isNaN())
            if (_scale.value == R.string.celsius)
                (it * 1.8F) + 32F
            else
                (it - 32F) / 1.8F
        else
            Float.NaN
    }
}