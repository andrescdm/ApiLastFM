package com.acdm.apilastfm.presentation.ui

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
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
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.acdm.apilastfm.R
import com.acdm.apilastfm.core.model.songs.Track
import com.acdm.apilastfm.presentation.intent.LastFMSongIntent
import com.acdm.apilastfm.presentation.ui.theme.AppColors
import com.acdm.apilastfm.presentation.viewmodel.ApiViewModelSongs

@Composable
fun ContentSecondaryScreen(
    apiViewModelSongs: ApiViewModelSongs,
    id: String
) {
    val songsFMStates by apiViewModelSongs.stateSongs.collectAsState()
    if (songsFMStates.isLoadingApi) {
        Column(
            Modifier
                .background(AppColors.BackgroundColor)
                .fillMaxSize(),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            CircularProgressIndicator(
                modifier = Modifier.size(50.dp),
                color = AppColors.CircularProgressColor,
                strokeWidth = 4.dp
            )
            Text(
                text = stringResource(R.string.loading),
                fontSize = 20.sp,
                fontFamily = FontFamily.Monospace,
                fontWeight = FontWeight.Bold,
                color = AppColors.PrimaryTextColor,
            )
        }
    } else if (songsFMStates.topSongs != null) {

        Column(
            Modifier
                .fillMaxSize()
                .background(AppColors.BackgroundColor)
        ) {
            Spacer(modifier = Modifier.padding(4.dp))
            Text(
                text = stringResource(
                    R.string.top_5_songs,
                    songsFMStates.topSongs!!.toptracks.track[0].artist.name
                ),
                textAlign = TextAlign.Center,
                fontSize = 20.sp,
                fontFamily = FontFamily.Monospace,
                fontWeight = FontWeight.Bold,
                modifier = Modifier.align(Alignment.CenterHorizontally),
                color = AppColors.PrimaryTextColor,

                )
            Spacer(modifier = Modifier.padding(4.dp))
            LazyVerticalGrid(columns = GridCells.Fixed(1),
                horizontalArrangement = Arrangement.spacedBy(8.dp),
                verticalArrangement = Arrangement.spacedBy(8.dp),

                content = {
                    items(songsFMStates.topSongs!!.toptracks.track.size) {
                        ItemSongs(
                            songsFMStates.topSongs!!.toptracks.track[it],
                        )
                    }
                })
        }


    } else {
        FetchSongsTopButton(apiViewModelSongs, id)
    }

    LaunchedEffect(false) {
        apiViewModelSongs.processIntent(LastFMSongIntent.FetchSongsTop(id))
    }
}

@Composable
fun ItemSongs(
    songsFMArtist: Track,
) {

    Card(
        shape = MaterialTheme.shapes.medium,
        modifier = Modifier
            .height(100.dp)
            .fillMaxWidth()
            .padding(start = 8.dp,end = 8.dp)
    )
    {
        Row(
            modifier = Modifier
                .height(100.dp)
                .fillMaxWidth()
                .background(AppColors.CardBackgroundColor)
        )
        {
            Image(
                painterResource(id = R.drawable.baseline_music_note_24),
                contentDescription = stringResource(R.string.icon),
                modifier = Modifier
                    .width(80.dp)
                    .height(70.dp)
                    .align(Alignment.CenterVertically),

                )
            Spacer(modifier = Modifier.padding(4.dp))
            DescriptionSong(
                name = songsFMArtist.name,
                playcount = songsFMArtist.playcount,
                listeners = songsFMArtist.listeners,
                url = songsFMArtist.url
            )
        }

    }
}

@Composable
fun FetchSongsTopButton(apiViewModelSongs: ApiViewModelSongs, id: String) {
    val songsStates by apiViewModelSongs.stateSongs.collectAsState()
    Column(
        Modifier
            .background(AppColors.BackgroundColor)
            .fillMaxSize()
            .padding(8.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Text(
            text = "Error: " + songsStates.error,
            textAlign = TextAlign.Center,
            color = AppColors.PrimaryTextColor
        )
        Spacer(modifier = Modifier.padding(8.dp))
        Button(modifier = Modifier.background(AppColors.ButtonColor),
            onClick = { apiViewModelSongs.processIntent(LastFMSongIntent.FetchSongsTop(id)) }) {
            Text(text = stringResource(R.string.retry_loading))
        }
    }
}

@Composable
fun DescriptionSong(name: String, playcount: String, listeners: String, url: String) {
    Column(
        modifier = Modifier.padding(top = 8.dp)
    ) {
        Spacer(modifier = Modifier.padding(4.dp))
        Text(
            text = name,
            textAlign = TextAlign.Left,
            fontSize = 21.sp,
            fontFamily = FontFamily.Monospace,
            fontWeight = FontWeight.Bold,
            maxLines = 1,
            color = AppColors.PrimaryTextColor
        )
        Spacer(modifier = Modifier.padding(4.dp))
        Text(
            lineHeight = 3.sp,
            text = stringResource(R.string.playcount, playcount),
            textAlign = TextAlign.Left,
            fontSize = 10.sp,
            fontFamily = FontFamily.Monospace,
            color = AppColors.SecondaryTextColor
        )
        Text(
            lineHeight = 3.sp,
            text = stringResource(R.string.listeners, listeners),
            textAlign = TextAlign.Left,
            fontSize = 10.sp,
            fontFamily = FontFamily.Monospace,
            color = AppColors.SecondaryTextColor
        )
        Text(
            lineHeight = 3.sp,
            text = url,
            textAlign = TextAlign.Justify,
            fontSize = 10.sp,
            fontFamily = FontFamily.Monospace,
            overflow = TextOverflow.Ellipsis,
            maxLines = 1,
            color = AppColors.SecondaryTextColor
        )
    }
}