package eu.thomaskuenneth.composebook.composeunitconverter.screens

import androidx.annotation.DrawableRes
import androidx.annotation.StringRes
import eu.thomaskuenneth.composebook.composeunitconverter.R

sealed class ComposeUnitConverterScreen(
    val route: String,
    @StringRes val label: Int,
    @DrawableRes val icon: Int
) {
    companion object {
        val screens = listOf(
            Temperature,
            Distances
        )

        const val route_temperature = "temperature"
        const val route_distances = "distances"
    }

    private object Temperature : ComposeUnitConverterScreen(
        route_temperature,
        R.string.temperature,
        R.drawable.baseline_thermostat_24
    )

    private object Distances : ComposeUnitConverterScreen(
        route_distances,
        R.string.distances,
        R.drawable.baseline_square_foot_24
    )
}
