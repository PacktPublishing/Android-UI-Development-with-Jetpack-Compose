package eu.thomaskuenneth.composebook.interopdemo

import android.content.Intent
import android.os.Bundle
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material.Button
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.ViewCompositionStrategy
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import eu.thomaskuenneth.composebook.interopdemo.databinding.LayoutBinding

const val KEY = "key"

class ViewActivity : AppCompatActivity() {

    private lateinit var binding: LayoutBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val viewModel: MyViewModel by viewModels()
        viewModel.setSliderValue(intent.getFloatExtra(KEY, 0F))
        binding = LayoutBinding.inflate(layoutInflater)
        setContentView(binding.root)
        viewModel.sliderValue.observe(this) {
            binding.slider.value = it
        }
        binding.slider.addOnChangeListener { _, value, _ -> viewModel.setSliderValue(value) }
        binding.composeView.run {
            setViewCompositionStrategy(ViewCompositionStrategy.DisposeOnViewTreeLifecycleDestroyed)
            setContent {
                val sliderValue = viewModel.sliderValue.observeAsState()
                sliderValue.value?.let {
                    ComposeDemo(it) {
                        val i = Intent(
                            context,
                            ComposeActivity::class.java
                        )
                        i.putExtra(KEY, it)
                        startActivity(i)
                    }
                }
            }
        }
    }
}

@Composable
fun ComposeDemo(value: Float, onClick: () -> Unit) {
    Column(
        modifier = Modifier
            .fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .background(MaterialTheme.colors.secondary)
                .height(64.dp),
            contentAlignment = Alignment.Center
        ) {
            Text(
                text = value.toString()
            )
        }
        Button(
            onClick = onClick,
            modifier = Modifier.padding(top = 16.dp)
        ) {
            Text(text = stringResource(id = R.string.compose_activity))
        }
    }
}