package eu.thomaskuenneth.composebook.composeunitconverter.viewmodels

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import eu.thomaskuenneth.composebook.composeunitconverter.Repository

class ViewModelFactory(private val repository: Repository) :
    ViewModelProvider.NewInstanceFactory() {
    override fun <T : ViewModel?> create(modelClass: Class<T>): T =
        if (modelClass.isAssignableFrom(TemperatureViewModel::class.java))
            TemperatureViewModel(repository) as T
        else
            DistancesViewModel() as T
}