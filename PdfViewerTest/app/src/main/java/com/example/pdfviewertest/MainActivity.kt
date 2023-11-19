package com.example.pdfviewertest

import android.net.Uri
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.viewinterop.AndroidView
import com.example.pdfviewertest.ui.theme.PdfViewerTestTheme
import com.github.barteksc.pdfviewer.PDFView

class MainActivity : ComponentActivity() {


    var pdfFile by mutableStateOf<Uri?>(null)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            PdfViewerTestTheme { // A surface container using the 'background' color from the theme
                Surface(modifier = Modifier.fillMaxSize(),
                        color = MaterialTheme.colorScheme.background) {
                    Greeting("Android", pdfFile, onClicked = {
                        launcher.launch("application/pdf")
                    })
                }
            }
        }
    }

    private val launcher = registerForActivityResult(ActivityResultContracts.GetContent()) { uri ->
        uri?.let { // Do something with the URI here
            pdfFile = uri
        }
    }
}

@Composable
fun Greeting(name: String, pdfFile: Uri? = null, onClicked: () -> Unit = {}) {

    Column(modifier = Modifier.fillMaxSize(), horizontalAlignment = Alignment.CenterHorizontally) {

        AndroidView(
            modifier = Modifier.fillMaxSize(0.9f),
            factory = { context ->
                val pdfView = PDFView(context, null)
                pdfView
            },
            update = { pdfView ->
                pdfFile?.let {
                    pdfView.fromUri(it).load()
                }
            }
        )
        Button(onClick = onClicked) {
            Text("Open PDF")
        }
    }
}

@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    PdfViewerTestTheme {
        Greeting("Android")
    }
}