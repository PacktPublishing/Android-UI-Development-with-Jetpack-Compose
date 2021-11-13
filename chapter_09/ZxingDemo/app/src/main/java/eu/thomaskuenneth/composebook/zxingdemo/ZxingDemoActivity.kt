package eu.thomaskuenneth.composebook.zxingdemo

import android.Manifest
import android.annotation.SuppressLint
import android.os.Bundle
import android.view.KeyEvent
import android.view.View
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.ui.viewinterop.AndroidView
import androidx.lifecycle.MutableLiveData
import com.google.zxing.BarcodeFormat
import com.google.zxing.client.android.BeepManager
import com.journeyapps.barcodescanner.BarcodeCallback
import com.journeyapps.barcodescanner.BarcodeResult
import com.journeyapps.barcodescanner.DecoratedBarcodeView
import com.journeyapps.barcodescanner.DefaultDecoderFactory

class ZxingDemoActivity : ComponentActivity() {

    private lateinit var barcodeView: DecoratedBarcodeView

    private val text = MutableLiveData("")

    private val requestPermission =
        registerForActivityResult(ActivityResultContracts.RequestPermission()) { isGranted ->
            if (isGranted) {
                barcodeView.resume()
            }
        }


    @SuppressLint("InflateParams")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val beepManager = BeepManager(this)
        val root = layoutInflater.inflate(R.layout.layout, null)
        barcodeView = root.findViewById(R.id.barcode_scanner)
        val formats = listOf(BarcodeFormat.QR_CODE, BarcodeFormat.CODE_39)
        barcodeView.barcodeView.decoderFactory = DefaultDecoderFactory(formats)
        barcodeView.initializeFromIntent(intent)
        val callback = object : BarcodeCallback {
            override fun barcodeResult(result: BarcodeResult) {
                if (result.text == null || result.text == text.value) {
                    return
                }
                text.value = result.text
                beepManager.playBeepSoundAndVibrate()
            }
        }
        barcodeView.decodeContinuous(callback)
        setContent {
            val tet = text.observeAsState()
            tet.value?.let {
                ZxingDemo(root, it)
            }
        }
    }

    override fun onResume() {
        super.onResume()
        requestPermission.launch(Manifest.permission.CAMERA)
    }

    override fun onPause() {
        super.onPause()
        barcodeView.pause()
    }

    override fun onKeyDown(keyCode: Int, event: KeyEvent?): Boolean {
        return barcodeView.onKeyDown(keyCode, event) || super.onKeyDown(keyCode, event)
    }
}

@Composable
fun ZxingDemo(root: View, value: String) {
    Box(
        modifier = Modifier.fillMaxSize(),
        contentAlignment = Alignment.TopCenter
    ) {
        AndroidView(modifier = Modifier.fillMaxSize(),
            factory = {
                root
            })
        if (value.isNotBlank()) {
            Text(
                modifier = Modifier.padding(16.dp),
                text = value,
                color = Color.White,
                style = MaterialTheme.typography.h4
            )
        }
    }
}