package com.example.businesscardapp

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.Email
import androidx.compose.material.icons.rounded.Email
import androidx.compose.material.icons.rounded.Phone
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.businesscardapp.ui.theme.BusinessCardAppTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            BusinessCardAppTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(), color = MaterialTheme.colorScheme.background
                ) {}
            }
        }
    }
}

@Composable
fun ShowIntro(image: Int, name: String, title: String, modifier: Modifier= Modifier) {
    Column(horizontalAlignment = Alignment.CenterHorizontally, modifier = Modifier.padding(8.dp)) {
        Image(
            painter = painterResource(id = image),
            contentDescription = null,
            contentScale = ContentScale.Crop,
            modifier = Modifier
                .size(200.dp)
                .clip(CircleShape)
        )
        Text(
            text = name,
            style = MaterialTheme.typography.headlineSmall,
            fontWeight = FontWeight.Bold,
            modifier = Modifier.padding(top = 8.dp)
        )
        Text(text = title, style = MaterialTheme.typography.titleMedium)
    }
}

@Composable
fun ContactDetails() {
    Column(horizontalAlignment = Alignment.Start, modifier = Modifier.padding(8.dp)) {
        Row(verticalAlignment = Alignment.CenterVertically, modifier = Modifier.padding(4.dp)) {
            Icon(Icons.Rounded.Phone, contentDescription = null, modifier = Modifier.size(32.dp))
            Text(text = "02020201010", modifier = Modifier.padding(start = 8.dp))
        }
        Row (verticalAlignment = Alignment.CenterVertically, modifier = Modifier.padding(4.dp)){
            //TODO:  add alternate email emoji
            Icon(Icons.Outlined.Email, contentDescription = null, modifier = Modifier.size(32.dp))
            Text(text = "@how are you i am fine", modifier = Modifier.padding(start = 8.dp))
        }
        Row (verticalAlignment = Alignment.CenterVertically, modifier = Modifier.padding(4.dp)){
            Icon(Icons.Rounded.Email, contentDescription = null, modifier = Modifier.size(32.dp))
            Text(text = "02020201010", modifier = Modifier.padding(start = 8.dp))
        }

    }
}

@Composable
fun DrawTheCard() {
    Column(horizontalAlignment = Alignment.CenterHorizontally, modifier = Modifier.padding(8.dp)) {
        ShowIntro(image = R.drawable.me, name = "Mohammad Zaid Aziz", title = "Student")
        ContactDetails()
    }
}

@Preview(showBackground = true, showSystemUi = true)
@Composable
fun ContactDetailsPreview() {
    BusinessCardAppTheme {
        ContactDetails()
    }
}

@Preview(showBackground = true, showSystemUi = true)
@Composable
fun GreetingPreview() {
    BusinessCardAppTheme {
        ShowIntro(
            image = R.drawable.me,
            name = "Mohammad Zaid Aziz",
            title = "Student"
        )
    }
}

@Preview(showBackground = true, showSystemUi = true)
@Composable
fun DrawTheCardPreview() {
    BusinessCardAppTheme {
        DrawTheCard()
    }
}