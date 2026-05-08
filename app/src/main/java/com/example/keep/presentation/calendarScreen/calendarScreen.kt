package com.example.keep.presentation.calendarScreen

import android.content.res.Configuration
import androidx.compose.animation.core.FastOutSlowInEasing
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyHorizontalGrid
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.material3.ExperimentalMaterial3ExpressiveApi
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.keep.R
import com.example.keep.presentation.components.KeepBottomBar
import com.example.keep.presentation.todayScreen.TodayScreen
import com.example.keep.ui.theme.KeepTheme

@Composable
fun CalendarScreenRoot(modifier: Modifier = Modifier) {
    Scaffold (
        bottomBar = { KeepBottomBar() }
    ){ contentPadding ->
        contentPadding
        Surface(
            color = MaterialTheme.colorScheme.background
        ) {
            CalendarScreen()
        }
    }

}

@Composable
fun CalendarScreen(modifier: Modifier = Modifier) {
    Column() {
        Calendar()
        HorizontalDivider()
    }
}
    
@Composable
fun Calendar(modifier: Modifier = Modifier) {
    LazyVerticalGrid(
        columns = GridCells.Fixed(7),
        userScrollEnabled = false,
        verticalArrangement = Arrangement.spacedBy(3.dp),
        horizontalArrangement = Arrangement.spacedBy(3.dp),
        contentPadding = PaddingValues(6.dp),
        modifier = modifier
            .fillMaxWidth()
    ) {
        for (i in 0..4){
            for (j in 0 ..6){
                item{
                    CalendarImage(day = j + 1 + i * 7)
                }
            }
        }

    }
}

@Composable
fun CalendarImage(
    modifier: Modifier = Modifier,
    day: Int
) {
    Box(
        contentAlignment = Alignment.Center,
        modifier = modifier.wrapContentSize()
    ){
        Image(
            painter = painterResource(R.drawable.placeholder_image),
            contentDescription = "Your Image",
            contentScale = ContentScale.Crop,
            modifier = Modifier
                .aspectRatio(1f)
                .clip(MaterialTheme.shapes.extraSmall)

        )
//        Text(
//            text = day.toString(),
//            style = MaterialTheme.typography.titleLarge,
//            color = Color.White,
//            modifier = Modifier
//        )
    }

}

@Preview
@Composable
fun CalendarPreview(modifier: Modifier = Modifier) {
    KeepTheme {
        Calendar()
    }
}

@Preview(showBackground = true, widthDp = 390, heightDp = 800, uiMode = Configuration.UI_MODE_NIGHT_NO,
    device = "spec:width=1080px,height=2340px,dpi=440,isRound=true",
    showSystemUi = false,
)
@Preview(showBackground = true, widthDp = 390, heightDp = 800, uiMode = Configuration.UI_MODE_NIGHT_YES)
@Composable
fun CalendarScreenPreview(modifier: Modifier = Modifier) {
    KeepTheme {
        CalendarScreenRoot()
    }

}