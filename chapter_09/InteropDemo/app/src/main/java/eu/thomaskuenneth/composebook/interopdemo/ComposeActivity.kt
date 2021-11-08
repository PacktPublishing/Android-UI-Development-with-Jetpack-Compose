package eu.thomaskuenneth.composebook.interopdemo

import android.content.Intent
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Scaffold
import androidx.compose.material.Slider
import androidx.compose.material.Text
import androidx.compose.material.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.viewinterop.AndroidViewBinding
import eu.thomaskuenneth.composebook.interopdemo.databinding.CustomBinding

class ComposeActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val viewModel: MyViewModel by viewModels()
        viewModel.setSliderValue(intent.getFloatExtra(KEY, 0F))
        setContent {
            ViewIntegrationDemo(viewModel) {
                val i = Intent(
                    this,
                    ViewActivity::class.java
                )
                i.putExtra(KEY, viewModel.sliderValue.value)
                startActivity(i)
            }
        }
    }
}

@Composable
fun ViewIntegrationDemo(viewModel: MyViewModel, onClick: () -> Unit) {
    val sliderValueState = viewModel.sliderValue.observeAsState()
    Scaffold(modifier = Modifier.fillMaxSize(),
        topBar = {
            TopAppBar(title =
            {
                Text(text = stringResource(id = R.string.compose_activity))
            })
        }) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(16.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Slider(
                modifier = Modifier.fillMaxWidth(),
                onValueChange = {
                    viewModel.setSliderValue(it)
                },
                value = sliderValueState.value ?: 0F
            )
            AndroidViewBinding(
                modifier = Modifier.fillMaxWidth(),
                factory = CustomBinding::inflate
            ) {
                textView.text = sliderValueState.value.toString()
                button.setOnClickListener {
                    onClick()
                }
            }
        }
    }
}