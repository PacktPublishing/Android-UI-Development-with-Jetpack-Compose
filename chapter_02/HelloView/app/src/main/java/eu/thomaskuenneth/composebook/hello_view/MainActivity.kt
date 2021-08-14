package eu.thomaskuenneth.composebook.hello_view

import android.os.Bundle
import android.view.View.GONE
import android.view.View.VISIBLE
import androidx.appcompat.app.AppCompatActivity
import androidx.core.widget.doAfterTextChanged
import eu.thomaskuenneth.composebook.hello_view.databinding.MainBinding

class MainActivity : AppCompatActivity() {

    private lateinit var binding: MainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = MainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        binding.message.text = getString(R.string.welcome)
        binding.name.run {
            setOnEditorActionListener { _, _, _ ->
                binding.done.performClick()
                true
            }
            doAfterTextChanged {
                enableOrDisableButton()
            }
            visibility = VISIBLE
        }
        binding.done.run {
            setOnClickListener {
                val name = binding.name.text
                if (name.isNotBlank()) {
                    binding.message.text = getString(R.string.hello, name)
                    binding.name.visibility = GONE
                    it.visibility = GONE
                }
            }
            visibility = VISIBLE
        }
        enableOrDisableButton()
    }

    private fun enableOrDisableButton() {
        binding.done.isEnabled = binding.name.text.isNotBlank()
    }
}
