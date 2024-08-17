package com.acdm.apilastfm.presentation.ui

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import coil.compose.AsyncImage
import com.acdm.apilastfm.R
import com.acdm.apilastfm.core.model.Artist
import com.acdm.apilastfm.presentation.intent.LastFMArtistIntent
import com.acdm.apilastfm.presentation.viewmodel.ApiViewModel
import com.example.pruebatecnicabolsiyo.core.model.Routes


@Composable
fun ContentPrincipalView(
    apiViewModel: ApiViewModel, navController: NavHostController
) {

    val artistFMStates by apiViewModel.state.collectAsState()

    if (artistFMStates.isLoadingApi) {
        Column(
            Modifier.fillMaxSize(),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            //  LinearProgressIndicator()
        }
    } else if (artistFMStates.topArtists != null) {

        Column(
            Modifier
                .fillMaxWidth()
                .background(Color.Gray),
        ) {
            Text(
                text = artistFMStates.topArtists!!.topartists.attr.country,
                textAlign = TextAlign.Center,
                fontSize = 20.sp,
                fontFamily = FontFamily.Monospace,
                fontWeight = FontWeight.Bold,
                modifier = Modifier.align(Alignment.CenterHorizontally)
            )
            Spacer(modifier = Modifier.padding(4.dp))
            LazyVerticalGrid(columns = GridCells.Fixed(1),
                horizontalArrangement = Arrangement.spacedBy(8.dp),
                verticalArrangement = Arrangement.spacedBy(8.dp),

                content = {
                    items(artistFMStates.topArtists!!.topartists.artist.size) {
                        ItemCharacter(
                            it,
                            artistFMStates.topArtists!!.topartists.artist[it],
                            navController,
                        )
                    }
                })
        }


    } else {
        FetchArtistTopButton(apiViewModel)
    }

    LaunchedEffect(false) {
        apiViewModel.processIntent(LastFMArtistIntent.FetchArtistTop)
    }
}


@Composable
fun FetchArtistTopButton(apiViewModel: ApiViewModel) {
    val charactersStates by apiViewModel.state.collectAsState()
    Column(
        Modifier
            .fillMaxSize()
            .padding(8.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Text(text = "Error: " + charactersStates.error, textAlign = TextAlign.Center)
        Spacer(modifier = Modifier.padding(8.dp))
        Button(onClick = { apiViewModel.processIntent(LastFMArtistIntent.FetchArtistTop) }) {
            Text(text = stringResource(R.string.retry_loading))
        }
    }
}

@Composable
fun ItemCharacter(
    position: Int,
    artistFMArtist: Artist,
    navController: NavHostController,
) {
    Box(
        modifier = Modifier
            .clip(RoundedCornerShape(16.dp))
            .padding(8.dp)
    ) {

        Card(shape = MaterialTheme.shapes.medium, modifier = Modifier
            .clickable {
                navController.navigate(Routes.Screen2.createRoute(position)) {
                    popUpTo(
                        Routes.Screen2.createRoute(
                            position
                        )
                    ) {
                        inclusive = false
                    }
                }

            }
            .height(80.dp)
            .fillMaxWidth()) {
            Row(modifier = Modifier.fillMaxSize()) {
                AsyncImage(
                    modifier = Modifier.fillMaxHeight(),
                    model = artistFMArtist.image[1].text,
                    contentDescription = stringResource(R.string.photo),
                    contentScale = ContentScale.FillHeight
                )
                Spacer(modifier = Modifier.padding(4.dp))
                DescriptionArtist(
                    name = artistFMArtist.name,
                    listeners = artistFMArtist.listeners,
                    url = artistFMArtist.url
                )
            }
        }
    }
}

@Composable
fun DescriptionArtist(name: String, listeners: String, url: String) {
    Column(
        modifier = Modifier.fillMaxWidth()
    ) {
        Text(
            text = name,
            textAlign = TextAlign.Left,
            fontSize = 20.sp,
            fontFamily = FontFamily.Monospace,
            fontWeight = FontWeight.Bold,
            color = Color.Black
        )
        Spacer(modifier = Modifier.padding(4.dp))
        Text(
            text = stringResource(R.string.listeners, listeners),
            textAlign = TextAlign.Left,
            fontSize = 10.sp,
            fontFamily = FontFamily.Monospace,
            color = Color.Gray
        )
        Spacer(modifier = Modifier.padding(2.dp))
        Text(
            text = url,
            textAlign = TextAlign.Justify,
            fontSize = 10.sp,
            fontFamily = FontFamily.Monospace,
            maxLines = 1,
            color = Color.Gray
        )
    }
}
