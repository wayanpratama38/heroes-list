package com.example.herolist

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Devices
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import com.example.herolist.models.HeroesData
import com.example.herolist.ui.theme.HeroListTheme


@Composable
fun HeroesListItem(
    name : String,
    photoUrl : String,
    modifier: Modifier = Modifier
){
    Row(
        verticalAlignment = Alignment.CenterVertically,
        modifier = modifier.clickable {}
    ){
        AsyncImage(
            model = photoUrl,
            contentDescription = null,
            contentScale = ContentScale.Crop,
            modifier = Modifier
                .padding(8.dp)
                .size(60.dp)
                .clip(CircleShape)
        )
        Text(
            text = name,
            fontWeight = FontWeight.Medium,
            modifier = Modifier
                .fillMaxWidth()
                .weight(1f)
                .padding(start = 16.dp)
        )
    }
}

@Composable
fun EpicSevenApp(
    modifier: Modifier = Modifier
){
    Box(modifier = modifier){
        LazyColumn {
            items(HeroesData.heroes, key = { it.id }){ hero ->
                HeroesListItem(
                    name = hero.name,
                    photoUrl = hero.photoUrl,
                    modifier = Modifier.fillMaxWidth()
                )
            }
        }
    }
}




@Preview(showBackground = true, device = Devices.PIXEL_4)
@Composable
fun EpicSevenAppPreview(){
    HeroListTheme {
        EpicSevenApp()
    }
}
