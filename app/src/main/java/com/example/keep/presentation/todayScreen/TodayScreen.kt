package com.example.keep.presentation.todayScreen

import android.content.res.Configuration
import android.text.Layout
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.calculateEndPadding
import androidx.compose.foundation.layout.calculateStartPadding
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.widthIn
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.material.icons.automirrored.rounded.List
import androidx.compose.material.icons.rounded.Image
import androidx.compose.material.icons.rounded.Menu
import androidx.compose.material.icons.rounded.Mic
import androidx.compose.material.icons.rounded.MoreVert
import androidx.compose.material.icons.rounded.TextFields
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3ExpressiveApi
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton

import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource

import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.keep.R
import com.example.keep.presentation.components.KeepBottomBar
import com.example.keep.ui.theme.KeepTheme
import com.example.keep.ui.theme.MyAppIcons

import androidx.compose.material3.FloatingActionButtonMenu
import androidx.compose.material3.FloatingActionButtonMenuItem
import androidx.compose.material3.OutlinedCard
import androidx.compose.material3.Surface
import androidx.compose.material3.VerticalFloatingToolbar
import androidx.compose.ui.Alignment
import androidx.compose.ui.layout.BeyondBoundsLayout
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.unit.LayoutDirection
import com.example.keep.ui.theme.MyAppIconsAutoMirrored

@Composable
fun TodayScreenRoot(modifier: Modifier = Modifier) {
    Scaffold(
        bottomBar = { KeepBottomBar() },
        modifier = modifier,
        containerColor = MaterialTheme.colorScheme.surface,
        floatingActionButton = {
            FABMenu()
        }
    ) { contentPadding ->
        TodayScreen(contentPadding = contentPadding)
    }
}
@Composable
fun TodayScreen(
    modifier: Modifier = Modifier,
    contentPadding: PaddingValues
) {
    val paddingValues = PaddingValues(
        top = contentPadding.calculateTopPadding() + 24.dp,
        end = contentPadding.calculateEndPadding(layoutDirection = LayoutDirection.Ltr) + 24.dp,
        bottom = contentPadding.calculateBottomPadding() + 24.dp,
        start = contentPadding.calculateStartPadding(layoutDirection = LayoutDirection.Ltr) + 24.dp
    )
    Surface (color = MaterialTheme.colorScheme.surfaceVariant){
        LazyColumn(
            modifier
                .fillMaxSize(),
            horizontalAlignment = Alignment.Start,
            verticalArrangement = Arrangement.spacedBy(12.dp),
            contentPadding = paddingValues
        ) {
            item {
                DateAndImage()
            }
            item{
                TimeStamp()
            }
            item{
                ImageRow()
            }
            item{
                TimeStamp()
            }
        }
    }
}

@Composable
fun DateAndImage(modifier: Modifier = Modifier){
    Row (
        modifier = modifier
            .fillMaxWidth()
            .height(230.dp)
            .border(1.dp,color = MaterialTheme.colorScheme.outline, shape = MaterialTheme.shapes.small)
            .background(MaterialTheme.colorScheme.surfaceDim, shape = MaterialTheme.shapes.small),
        horizontalArrangement = Arrangement.spacedBy(12.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {

        ImageOfTheDay(
            modifier = Modifier.weight(1f)
        )
        Date()

    }

}

@Composable
fun ImageOfTheDay(
    modifier: Modifier = Modifier
){
            Image(
                painterResource(id = R.drawable.placeholder_image),
                contentDescription = "PlaceHolder",
                modifier = modifier
                    .clip(MaterialTheme.shapes.small)
                    .height(230.dp)
                    .widthIn(max = 400.dp),
                contentScale = ContentScale.Crop,
            )
}

@Composable
fun Date(modifier: Modifier = Modifier) {
    Column(
        modifier = Modifier
            .fillMaxHeight()
            .padding(end = 12.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Text(
            text = "MON",
            style = MaterialTheme.typography.titleLarge
        )
        Text(
            text = buildAnnotatedString {
                withStyle(style = MaterialTheme.typography.bodyMedium.toSpanStyle()) {
                    append("04\n")
                }
                withStyle(style = MaterialTheme.typography.bodyMedium.toSpanStyle()) {
                    append(" NOV\n 2026")
                }
            },
            textAlign = TextAlign.Center
        )
    }
}


@Composable
fun TimeStamp(
    modifier: Modifier = Modifier,
    showDivider: Boolean = true
) {
    Column (
        modifier = modifier.fillMaxWidth(1f),
        verticalArrangement = Arrangement.spacedBy(6.dp),
        horizontalAlignment = Alignment.Start
    ) {
        if (showDivider){
            HorizontalDivider()
        }
        Text(
            text = "08:30 pm",
            style = MaterialTheme.typography.labelLarge,
            color = MaterialTheme.colorScheme.outlineVariant
        )
        Text(text = "Scale the source to maintain the aspect ratio inside the destination bounds. This is just placeholder Text that will change later. So don't overthink it.",
            style = MaterialTheme.typography.bodyLarge)
    }
}

@Composable
fun ImageRow(modifier: Modifier = Modifier) {
    LazyRow (
        modifier = modifier,
        horizontalArrangement = Arrangement.spacedBy(12.dp)
    ){
        items(4){
            Image(
                painter = painterResource(R.drawable.placeholder_image),
                modifier = Modifier
                    .size(90.dp)
                    .clip(MaterialTheme.shapes.small),
                contentDescription = null,
                contentScale = ContentScale.Crop
            )
        }

    }

}


@OptIn(ExperimentalMaterial3ExpressiveApi::class)
@Composable
fun FABMenu(modifier: Modifier = Modifier) {
    FloatingActionButtonMenu(
        expanded = false,
        button = {
            Button(
                onClick = {}
            ) {
                Icon(
                    imageVector = MyAppIcons.Menu,
                    contentDescription = "Edit Options"
                )
            }
        }
    ) {
        FloatingActionButtonMenuItem(
            onClick = {},
            text = {},
            icon = {
                Icon(
                    imageVector = MyAppIcons.Mic,
                    contentDescription = "Dictate"
                )
            }
        )
        FloatingActionButtonMenuItem(
            onClick = {},
            text = {},
            icon = {
                Icon(
                    imageVector = MyAppIcons.Image,
                    contentDescription = "Add Image"
                )
            }
        )
        FloatingActionButtonMenuItem(
            onClick = {},
            text = {},
            icon = {
                Icon(
                    imageVector = MyAppIcons.TextFields,
                    contentDescription = "Pick Font"
                )
            }
        )
    }
}

@OptIn(ExperimentalMaterial3ExpressiveApi::class)
@Composable
fun FloatingToolBar(modifier: Modifier = Modifier) {
    VerticalFloatingToolbar(
        expanded = false,
        modifier = modifier,
        leadingContent = {
            IconButton(
                onClick = {},
                content = {
                    Icon(
                        imageVector = MyAppIcons.Mic,
                        contentDescription = "Dictate"
                    )
                }
            )
            IconButton(
                onClick = {},
                content = {
                    Icon(
                        imageVector = MyAppIcons.Image,
                        contentDescription = "Add Image"
                    )
                }
            )

        },
        trailingContent = {
            IconButton(
                onClick = {},
                content = {
                    Icon(
                        imageVector = MyAppIcons.TextFields,
                        contentDescription = "Pick Font"
                    )
                }
            )
            IconButton(
                onClick = {},
                content = {
                    Icon(
                        imageVector = MyAppIconsAutoMirrored.List,
                        contentDescription = "List"
                    )
                }
            )
        }
    ) {

        IconButton(
            onClick = {},
            content = {
                Icon(
                    imageVector = MyAppIcons.MoreVert,
                    contentDescription = "Edit Options"
                )
            }
        )

    }
}

@Preview(showBackground = true, widthDp = 390, heightDp = 800, uiMode = Configuration.UI_MODE_NIGHT_NO,
    device = "spec:width=1080px,height=2340px,dpi=440,isRound=true",
    showSystemUi = false,
)
@Preview(showBackground = true, widthDp = 390, heightDp = 800, uiMode = Configuration.UI_MODE_NIGHT_YES)
@Composable
fun TodayScreenRootPreview(modifier: Modifier = Modifier) {
    KeepTheme {
        TodayScreenRoot(modifier)
    }
}