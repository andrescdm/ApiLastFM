package com.acdm.apilastfm.presentation.ui

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.semantics.isTraversalGroup
import androidx.compose.ui.semantics.semantics
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import com.acdm.apilastfm.presentation.intent.LastFMArtistIntent
import com.acdm.apilastfm.presentation.viewmodel.ApiViewModel
import com.example.pruebatecnicabolsiyo.core.model.Routes


@Composable
fun ContentPrincipalView(
    apiViewModel: ApiViewModel,
    navController: NavHostController
) {

    val artistFMStates by apiViewModel.state.collectAsState()

    if (artistFMStates.isLoadingApi) {
        Column(
            Modifier.fillMaxSize(),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            CircularProgressIndicator(Modifier.size(50.dp))
        }
    } else if (artistFMStates.lastFMArtist != null) {

        Box(modifier = Modifier
            .fillMaxSize()
            .semantics { isTraversalGroup = true }
        ) {

            Column(
                Modifier
                    .fillMaxWidth()
                    .padding(8.dp)
            ) {
                LazyVerticalGrid(columns = GridCells.Adaptive(120.dp),
                    horizontalArrangement = Arrangement.spacedBy(8.dp),
                    verticalArrangement = Arrangement.spacedBy(8.dp),

                    content = {
                        items(2) {
                            ItemCharacter(
                                it,
                                navController,
                            )
                        }
                    })
            }
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
            Text(text = "Volver a cargar")
        }
    }
}

@Composable
fun ItemCharacter(
    position: Int,
    navController: NavHostController,
    ) {
    Column(modifier = Modifier.fillMaxSize(), horizontalAlignment = Alignment.CenterHorizontally) {
        Box {
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
                .height(200.dp)
                .width(200.dp)) {
                /*AsyncImage(
                    model = artistFM.image,
                    contentDescription = "photoCharacter",
                    contentScale = ContentScale.FillHeight,
                    modifier = Modifier.fillMaxSize()
                )*/
            }

        }
        Spacer(modifier = Modifier.padding(4.dp))
        Text(
            text = "Andres", textAlign = TextAlign.Center
        )
    }
}

