package com.vunlph30245.lab6_ph30245

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.foundation.lazy.staggeredgrid.LazyVerticalStaggeredGrid
import androidx.compose.foundation.lazy.staggeredgrid.StaggeredGridCells
import androidx.compose.foundation.lazy.staggeredgrid.rememberLazyStaggeredGridState
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material.icons.filled.Edit
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import coil.compose.AsyncImage
import com.google.gson.annotations.SerializedName
import com.vunlph30245.lab6_ph30245.ui.theme.Lab6_PH30245Theme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            val mainViewModel: MovieViewModel by viewModels()
            val moviesState by mainViewModel.movies.observeAsState(initial = emptyList())
            //MovieScreen(moviesState)
            ScreenNavigation()
        }
    }
}

data class Movie(
    @SerializedName("filmId") val id: String,
    @SerializedName("filmName") val filmName: String,
    @SerializedName("duration") val duration: String,
    @SerializedName("image") val image: String,
    @SerializedName("description") val description: String,
    @SerializedName("releaseDate") val releaseDate: String,
    @SerializedName("genre") val genre: String,
    @SerializedName("national") val national: String



) {
    companion object {
        fun getSampleMovies() = listOf(
            Movie(
                "1", "The Shawshank Redemption", "1994", "https://image.tmdb.org/t/p/w500/q6y0Go1tsGEsmtFryDOJo3dEmqu.jpg",
                "142 min", "1994-09-23", "Drama", "Two imprisoned men bond over a number of years, finding solace and eventual redemption through acts of common decency."
            ),
            Movie(
                "2", "The Godfather", "1972", "https://image.tmdb.org/t/p/w500/iVZ3JAcAjmguGPnRNfWFOtLHOuY.jpg",
                "175 min", "1972-03-24", "Crime, Drama", "The aging patriarch of an organized crime dynasty transfers control of his clandestine empire to his reluctant son."
            ),
            Movie(
                "3", "The Dark Knight", "2008", "https://image.tmdb.org/t/p/w500/qJ2tW6WMUDux911r6m7haRef0WH.jpg",
                "152 min", "2008-07-18", "Action, Crime, Drama", "When the menace known as the Joker emerges from his mysterious past, he wreaks havoc and chaos on the people of Gotham."
            ),
            Movie(
                "4", "Pulp Fiction", "1994", "https://image.tmdb.org/t/p/w500/dM2w364MScsjFf8pfMbaWUcWrR.jpg",
                "154 min", "1994-10-14", "Crime, Drama", "The lives of two mob hitmen, a boxer, a gangster's wife, and a pair of diner bandits intertwine in four tales of violence and redemption."
            ),
            Movie(
                "5", "The Lord of the Rings: The Return of the King", "2003", "https://image.tmdb.org/t/p/w500/rCzpDGLbOoPwLjy3OAm5NUPOTrC.jpg",
                "201 min", "2003-12-17", "Action, Adventure, Drama", "Gandalf and Aragorn lead the World of Men against Sauron's army to draw his gaze from Frodo and Sam as they approach Mount Doom with the One Ring."
            )
        )
    }
}

enum class ListType {
    ROW, COLUMN, GRID
}

@Composable
fun MovieItem(movie: Movie, listType: ListType) {
    Card(
        colors = CardDefaults.cardColors(containerColor = Color.White),
        elevation = CardDefaults.cardElevation(defaultElevation = 6.dp),
    ) {
        Column(
            modifier = Modifier
                .then(getItemSizeModifier(listType))
                .wrapContentHeight()
        ) {
            AsyncImage(
                model = movie.filmName,
                contentScale = ContentScale.Crop,
                contentDescription = null,
                modifier = Modifier.fillMaxWidth()
            )
            Column(modifier = Modifier.padding(8.dp)) {
                Text(
                    text = movie.description,
                    style = MaterialTheme.typography.titleSmall,
                    maxLines = 2,
                    overflow = TextOverflow.Ellipsis
                )
                BoldValueText(label = "Thời lượng: ", value = movie.duration)
                BoldValueText(label = "Khởi chiếu: ", value = movie.releaseDate)
                BoldValueText(label = "Thể loại: ", value = movie.genre)
                Text(
                    text = "Tóm tắt nội dung",
                    style = MaterialTheme.typography.bodySmall,
                    fontWeight = FontWeight.Bold,
                    modifier = Modifier.padding(top = 4.dp, bottom = 2.dp)
                )
                Text(
                    text = movie.duration,
                    style = MaterialTheme.typography.bodySmall,
                    maxLines = 5,
                    overflow = TextOverflow.Ellipsis,
                    modifier = Modifier.padding(end = 2.dp)
                )
            }
        }
    }
}

@Composable
fun MovieScreen(
    navigationController: NavController // Thêm tham số này để điều hướng
) {
    var listType by remember { mutableStateOf(ListType.ROW) }

    val mainViewModel: MovieViewModel = viewModel()
    val moviesState = mainViewModel.movies.observeAsState(initial = emptyList())
    val movies = moviesState.value // Sử dụng movies thay vì moviesState.value

    Column {
        Button(onClick = {
            navigationController.navigate(Screens.ADD.route)
        }) {
            Text("Thêm")
        }
        MovieColumn(
            movies = movies, // Sử dụng movies thay vì moviesState.value
            onEditClick = { filmId ->
                navigationController.navigate("${Screens.EDIT.route}/$filmId")
            },
            onDeleteClick = { /* Xử lý xóa */ }
        )

        Row(
            modifier = Modifier
                .padding(8.dp)
                .fillMaxWidth(),
            horizontalArrangement = Arrangement.Center
        ) {
            Button(onClick = { listType = ListType.ROW }) {
                Text("Row")
            }
            Spacer(modifier = Modifier.width(8.dp))
            Button(onClick = { listType = ListType.COLUMN }) {
                Text("Column")
            }
            Spacer(modifier = Modifier.width(8.dp))
            Button(onClick = { listType = ListType.GRID }) {
                Text("Grid")
            }
        }
        when (listType) {
            ListType.ROW -> MovieRow(movies)
            ListType.COLUMN -> MovieColumn(movies,{},{})
            ListType.GRID -> MovieGrid(movies)
        }
    }
}

@Composable
fun MovieRow(movies: List<Movie>) {
    LazyRow(
        state = rememberLazyListState(),
        contentPadding = PaddingValues(horizontal = 8.dp, vertical = 16.dp),
        horizontalArrangement = Arrangement.spacedBy(8.dp)
    ) {
        items(movies.size) { index ->
            MovieItem(movie = movies[index], listType = ListType.ROW)
        }
    }
}

@Composable
fun MovieColumn(movies: List<Movie>,
                onEditClick: (id: String) -> Unit,
                onDeleteClick: (id: String) -> Unit
                ) {

        LazyColumn(
            state = rememberLazyListState(),
            contentPadding = PaddingValues(horizontal = 8.dp, vertical = 16.dp),
            verticalArrangement = Arrangement.spacedBy(8.dp)
        ) {
            items(movies.size) { index ->
                MovieColumnItem(
                    movie = movies[index],
                    onEditClick = onEditClick,
                    onDeleteClick = onDeleteClick)
            }
        }
    }


@Composable
fun MovieColumnItem(
    movie: Movie,
    onEditClick: (id: String) -> Unit,
    onDeleteClick: (id: String) -> Unit
) {
    Card(
        colors = CardDefaults.cardColors(containerColor = Color.White),
        elevation = CardDefaults.cardElevation(defaultElevation = 6.dp),
    ) {
        Row(
            modifier = Modifier.fillMaxWidth()
        ) {
            AsyncImage(
                model = movie.filmName,
                contentDescription = null,
                contentScale = ContentScale.FillWidth,
                modifier = Modifier
                    .width(130.dp)
                    .wrapContentHeight()
            )
            Column(
                modifier = Modifier.padding(8.dp)
            ) {
                Text(
                    text = movie.duration,
                    style = MaterialTheme.typography.titleSmall,
                    maxLines = 2,
                    overflow = TextOverflow.Ellipsis
                )
                BoldValueText(label = "Thời lượng: ", value = movie.duration)
                BoldValueText(label = "Khởi chiếu: ", value = movie.releaseDate)
                BoldValueText(label = "Thể loại: ", value = movie.genre)
                Text(
                    text = "Tóm tắt nội dung",
                    style = MaterialTheme.typography.bodySmall,
                    fontWeight = FontWeight.Bold,
                    modifier = Modifier.padding(top = 4.dp, bottom = 2.dp)
                )
                Text(
                    text = movie.description,
                    style = MaterialTheme.typography.bodySmall,
                    maxLines = 5,
                    overflow = TextOverflow.Ellipsis,
                    modifier = Modifier.padding(end = 2.dp)
                )
                Row(
                    modifier = Modifier.padding(end = 4.dp)
                ) {
                    IconButton(
                        onClick = { onEditClick(movie.id) },
                        modifier = Modifier.size(32.dp)
                    ) {
                        Icon(
                            Icons.Filled.Edit,
                            contentDescription = "Edit",
                            tint = MaterialTheme.colorScheme.primary
                        )
                    }
                    Spacer(modifier = Modifier.height(8.dp))
                    IconButton(
                        onClick = { onDeleteClick(movie.id) },
                        modifier = Modifier.size(32.dp)
                    ) {
                        Icon(
                            Icons.Filled.Delete,
                            contentDescription = "Delete",
                            tint = MaterialTheme.colorScheme.error
                        )
                    }
                }
            }
        }
    }
}


@Composable
fun BoldValueText(label: String, value: String, style: TextStyle = MaterialTheme.typography.bodySmall) {
    Text(buildAnnotatedString {
        append(label)
        withStyle(style = SpanStyle(fontWeight = FontWeight.Bold)) {
            append(value)
        }
    }, style = style)
}

@Composable
private fun getItemSizeModifier(listType: ListType): Modifier {
    return when (listType) {
        ListType.ROW -> Modifier.width(175.dp)
        ListType.COLUMN -> Modifier.width(130.dp)
        ListType.GRID -> Modifier.fillMaxWidth()
    }
}

@Composable
fun MovieGrid(movies: List<Movie>) {
    val gridState = rememberLazyStaggeredGridState()
    LazyVerticalStaggeredGrid(
        columns = StaggeredGridCells.Fixed(2),
        state = gridState,
        horizontalArrangement = Arrangement.spacedBy(8.dp),
        verticalItemSpacing = 8.dp,
        contentPadding = PaddingValues(8.dp)
    ) {
        items(movies.size) { index ->
            MovieItem(movie = movies[index], listType = ListType.GRID)
        }
    }
}

@Composable
fun Greeting(name: String, modifier: Modifier = Modifier) {
    Text(
        text = "Hello $name!",
        modifier = modifier
    )
}

@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    Lab6_PH30245Theme {
        Greeting("Android")
    }
}
