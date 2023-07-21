package com.example.basiclayoutcodelab

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.paddingFromBaseline
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyHorizontalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.basiclayoutcodelab.ui.theme.BasicLayoutCodelabTheme
import java.util.Locale

class MainActivity : ComponentActivity() {


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            App()
        }
    }
}

@Composable
fun App() {
    BasicLayoutCodelabTheme() {
        Surface(
            modifier = Modifier.fillMaxSize(),
            color = MaterialTheme.colorScheme.background
        ) {

        }
    }
}

@Composable
fun HomeScreen(
    modifier: Modifier = Modifier,
) {
    Column(
        modifier
            .padding(vertical = 16.dp)
            .verticalScroll(rememberScrollState())
    ) {
        Spacer(Modifier.height(16.dp))
        SearchBar(modifier = Modifier.padding(horizontal = 16.dp))
        HomeSection(title = "Align Your Body", {
            AlignYourBodyRow(alignYourBodyPairs = alignYourBodyPairs)
        })
        HomeSection(title = "Favourite Collection", {
            FavouriteCollectionGrid(favouriteCollections = alignYourBodyPairs)
        })
        Spacer(modifier = Modifier.height(16.dp))
    }
}

@Preview(showBackground = true)
@Composable
fun HomeScreenPreview() {
    BasicLayoutCodelabTheme {
        HomeScreen(
        )

    }
}

@Composable
fun HomeSection(
    title: String,
    content: @Composable () -> Unit,
    modifier: Modifier = Modifier
) {

    Column(modifier = modifier) {
        Text(
            text = title.uppercase(Locale.getDefault()),
            style = MaterialTheme.typography.headlineSmall,
            modifier = Modifier
                .paddingFromBaseline(top = 40.dp, bottom = 8.dp)
                .padding(horizontal = 16.dp)
        )
        content()

    }
}

@Preview(showBackground = true)
@Composable
fun HomeSectionPreview() {
    BasicLayoutCodelabTheme() {

        HomeSection(
            "Align Your Body",
            {
                AlignYourBodyRow(
                    alignYourBodyPairs = listOf(
                        Pair(R.drawable.ab1_inversions, "Inversions"),
                        Pair(R.drawable.ab1_inversions, "Quick Yoga"),
                        Pair(R.drawable.ab1_inversions, "Stretching"),
                        Pair(R.drawable.ab1_inversions, "Tabata"),
                        Pair(R.drawable.ab1_inversions, "Stretching"),
                        Pair(R.drawable.ab1_inversions, "Stretching"),
                        Pair(R.drawable.ab1_inversions, "Stretching"),
                        Pair(R.drawable.ab1_inversions, "Stretching"),
                    )
                )
            }
        )
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SearchBar(modifier: Modifier = Modifier) {
    TextField(
        value = "",
        onValueChange = {},
        leadingIcon = {
            Icon(
                imageVector = Icons.Filled.Search,
                contentDescription = null
            )
        },
        colors = TextFieldDefaults.textFieldColors(),
        placeholder = { Text(text = "Search") },
        modifier = modifier
            .fillMaxWidth()
            .heightIn(min = 56.dp)
            .background(MaterialTheme.colorScheme.surface)
    )
}

@Composable
fun AlignYourBodyRow(
    modifier: Modifier = Modifier,
    alignYourBodyPairs: List<Pair<Int, String>>
) {
    LazyRow(
        modifier = modifier,
        horizontalArrangement = Arrangement.spacedBy(8.dp),
        contentPadding = PaddingValues(horizontal = 16.dp)
    ) {
        items(alignYourBodyPairs) { alignYourBodyPair ->
            AlignYourBodyElement(
                image = alignYourBodyPair.first,
                text = alignYourBodyPair.second
            )
        }
    }
}

@Composable
fun AlignYourBodyElement(
    modifier: Modifier = Modifier,
    image: Int,
    text: String
) {
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = modifier
    ) {
        Image(
            painter = painterResource(id = image),
            contentDescription = null,
            contentScale = ContentScale.Crop,
            modifier = Modifier
                .clip(CircleShape)
                .size(88.dp)
        )
        Text(
            text = text,
            style = MaterialTheme.typography.titleSmall,
            modifier = Modifier.paddingFromBaseline(top = 24.dp, bottom = 8.dp)
        )
    }
}

@Composable
fun FavouriteCollectionGrid(
    modifier: Modifier = Modifier,
    favouriteCollections: List<Pair<Int, String>>
) {
    LazyHorizontalGrid(
        horizontalArrangement = Arrangement.spacedBy(8.dp),
        verticalArrangement = Arrangement.spacedBy(8.dp),
        contentPadding = PaddingValues(horizontal = 16.dp),
        rows = GridCells.Fixed(2),
        modifier = modifier.height(120.dp)
    ) {
        items(favouriteCollections) { favouriteCollection ->
            FavouriteCollectionElement(
                modifier = Modifier.height(56.dp),
                image = favouriteCollection.first,
                text = favouriteCollection.second
            )
        }
    }
}

@Preview()
@Composable
fun SearchBarPreview() {
    BasicLayoutCodelabTheme {
        SearchBar()
    }
}

@Preview(showBackground = true)
@Composable
fun AlignYourBodyElementPreview() {
    BasicLayoutCodelabTheme {
        AlignYourBodyElement(
            modifier = Modifier.padding(8.dp),
            image = R.drawable.ab1_inversions,
            text = "Inversions"
        )
    }
}

@Preview(showBackground = true)
@Composable
fun AlignYourBodyRowPreview() {
    BasicLayoutCodelabTheme {
        AlignYourBodyRow(
            modifier = Modifier.padding(top = 8.dp, bottom = 8.dp),
            alignYourBodyPairs = listOf(
                Pair(R.drawable.ab1_inversions, "Inversions"),
                Pair(R.drawable.ab1_inversions, "Quick Yoga"),
                Pair(R.drawable.ab1_inversions, "Stretching"),
                Pair(R.drawable.ab1_inversions, "Tabata"),
                Pair(R.drawable.ab1_inversions, "Stretching"),
                Pair(R.drawable.ab1_inversions, "Stretching"),
                Pair(R.drawable.ab1_inversions, "Stretching"),
                Pair(R.drawable.ab1_inversions, "Stretching"),
            )
        )
    }
}

@Composable
fun FavouriteCollectionElement(
    modifier: Modifier = Modifier,
    image: Int, text: String
) {
    Surface(
        shape = MaterialTheme.shapes.small, modifier = modifier,
    ) {
        Row(
            modifier = Modifier.width(192.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Image(
                painter = painterResource(id = image),
                contentDescription = null,
                contentScale = ContentScale.Crop,
                modifier = Modifier
                    .size(56.dp)
            )
            Text(
                text = text,
                style = MaterialTheme.typography.titleSmall,
                modifier = Modifier.padding(horizontal = 16.dp)
            )
        }
    }
}

@Preview(showBackground = true)
@Composable
fun FavouriteCollectionElementPreview() {
    BasicLayoutCodelabTheme {
        FavouriteCollectionElement(
            modifier = Modifier.padding(8.dp),
            image = R.drawable.ab1_inversions,
            text = "Inversions"
        )
    }
}

@Preview(showBackground = true)
@Composable
fun FavouriteCollectionGridPreview() {
    BasicLayoutCodelabTheme {
        FavouriteCollectionGrid(
            modifier = Modifier.padding(vertical = 8.dp),
            favouriteCollections = listOf(
                Pair(R.drawable.ab1_inversions, "Inversions"),
                Pair(R.drawable.ab1_inversions, "Quick Yoga"),
                Pair(R.drawable.ab1_inversions, "Stretching"),
                Pair(R.drawable.ab1_inversions, "Tabata"),
                Pair(R.drawable.ab1_inversions, "Stretching"),
                Pair(R.drawable.ab1_inversions, "Stretching"),
                Pair(R.drawable.ab1_inversions, "Stretching"),
                Pair(R.drawable.ab1_inversions, "Stretching"),
            )
        )
    }


}

private val alignYourBodyPairs: List<Pair<Int, String>> = listOf(
    R.drawable.ab1_inversions to "Quick pack",
    R.drawable.ab1_inversions to "Quick pack",
    R.drawable.ab1_inversions to "first Heading",
    R.drawable.ab1_inversions to "second Heading",
    R.drawable.ab1_inversions to "third Heading",
    R.drawable.ab1_inversions to "fourth Heading",
    R.drawable.ab1_inversions to "fifth Heading",
    R.drawable.ab1_inversions to "sixth Heading",
    R.drawable.ab1_inversions to "seventh Heading",
    R.drawable.ab1_inversions to "eighth Heading",
    R.drawable.ab1_inversions to "ninth Heading",
    R.drawable.ab1_inversions to "tenth Heading",
    R.drawable.ab1_inversions to "eleventh Heading",
)