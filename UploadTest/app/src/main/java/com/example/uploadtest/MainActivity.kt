package com.example.uploadtest

import android.content.ContentResolver
import android.database.Cursor
import android.net.Uri
import android.os.Bundle
import android.provider.OpenableColumns
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Button
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.material3.contentColorFor
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.State
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.uploadtest.ui.theme.UploadTestTheme
import com.google.firebase.ktx.Firebase
import com.google.firebase.storage.StorageReference
import com.google.firebase.storage.ktx.storage
import com.google.firebase.storage.ktx.storageMetadata
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import java.util.UUID

class MainActivity : ComponentActivity() {

    private lateinit var storageRef: StorageReference
    private lateinit var downloadRef: StorageReference

    private var stateFlow = MutableStateFlow(0.0f)
    private var fileUriUiState = mutableStateOf("")
    private var cursorState = mutableStateOf("")
    private var uriMetaDataState = MutableStateFlow(List<String>(0) { "" })
    private var listState = MutableStateFlow(listOf<String>())
    private lateinit var content: ContentResolver


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        storageRef = Firebase.storage.reference
        content = applicationContext.contentResolver
        setContent {
            UploadTestTheme {
                // A surface container using the 'background' color from the theme
                App(state = stateFlow,
                    fileUri = fileUriUiState,
                    cursorState = cursorState,
                    uriMetaDataState = uriMetaDataState.asStateFlow(),
                    list = listState.collectAsState(),
                    onClick = { fileUploader.launch("application/pdf") })
            }
        }
        fileListDownloader()
    }

    private val fileUploader = registerForActivityResult(ActivityResultContracts.GetMultipleContents()) { uriList ->
        if (uriList == null) {
            return@registerForActivityResult
        }
        for ((i, uri) in uriList.withIndex()) {

            val fileRef = storageRef.child("files").child(UUID.randomUUID().toString() + "-major.pdf")
            val metadata = storageMetadata {
                setCustomMetadata("papper-type", "major")
            }
            val uploadTask = fileRef.putFile(uri, metadata)
            fileUriUiState.value = uri.toString()

            uploadTask.addOnProgressListener() { taskSnapshot ->
                val progress = (100.0 * taskSnapshot.bytesTransferred) / taskSnapshot.totalByteCount
                stateFlow.value = progress.toFloat()
            }
            getUriMetaData(uri)
        }
    }

    fun getUriMetaData(uri: Uri) {
        val cursor: Cursor? = content.query(uri, null, null, null, null, null)

        cursorState.value = cursor?.toString() ?: "null"

        cursor?.use {
            if (it.moveToFirst()) {
                val index = it.getColumnIndex(OpenableColumns.DISPLAY_NAME)
                val displayName: String = it.getString(index)
                val sizeIndex: Int = cursor.getColumnIndex(OpenableColumns.SIZE)
                val size: String = if (!cursor.isNull(sizeIndex)) {
                    cursor.getString(sizeIndex)
                } else {
                    "Unknown"
                }
                uriMetaDataState.value = uriMetaDataState.value + "Name: $displayName, Size: $size"
            }

        }
    }

    private fun fileListDownloader() {
        downloadRef = Firebase.storage.reference.child("files")
        downloadRef.listAll().addOnSuccessListener { listResult ->
            val list = mutableListOf<String>()
            var i = 1;
            listResult.items.forEach { item ->
                list.add("item ${i++} : ${item}")
            }
            //            listResult.prefixes.forEach { prefix ->
            //                list.add(prefix.name)
            //            }
            listState.value = list
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun App(onClick: () -> Unit,
        state: MutableStateFlow<Float>,
        fileUri: MutableState<String>,
        cursorState: MutableState<String>,
        uriMetaDataState: StateFlow<List<String>>,
        list: State<List<String>>) {
    Scaffold(
            topBar = {
                TopAppBar(title = { Text(text = "UploadTest") },
                          colors = TopAppBarDefaults.smallTopAppBarColors(
                                  containerColor = MaterialTheme.colorScheme.primaryContainer,
                                  titleContentColor = contentColorFor(MaterialTheme.colorScheme.onPrimaryContainer),
                                  navigationIconContentColor = contentColorFor(MaterialTheme.colorScheme.onPrimaryContainer),
                                  actionIconContentColor = contentColorFor(MaterialTheme.colorScheme.onPrimaryContainer)
                          ))
            }
    ) { innerPadding ->


        Surface(modifier = Modifier
                .fillMaxSize()
                .padding(innerPadding)
                .background(MaterialTheme.colorScheme.tertiaryContainer)) {

            val statef: Float by state.collectAsState()
            val fileMetadata by uriMetaDataState.collectAsState()

            Column(modifier = Modifier.fillMaxSize(),
                   horizontalAlignment = Alignment.CenterHorizontally) {
                Button(onClick = onClick) {
                    Text(text = "UploadTest")
                }
                if (statef != 0.0f) {
                    CircularProgressIndicator(
                            progress = statef,
                            modifier = Modifier.padding(16.dp)
                    )
                }
                Text(text = fileUri.value)
                Text(text = cursorState.value)
                Spacer(modifier = Modifier.width(20.dp))
                Text(text = "UriMetaDataState", style = MaterialTheme.typography.headlineMedium)
                LazyColumn(modifier = Modifier.padding(16.dp)
                        ) {
                    items(fileMetadata.size) {
                        Text(text = fileMetadata[it])
                    }

                    item {
                        Spacer(modifier = Modifier.width(20.dp))
                        Text(text = "ListState", style = MaterialTheme.typography.headlineMedium)
                    }
                    items(list.value.size) {
                        Text(text = list.value[it])
                    }
                }
            }
        }

    }
}
