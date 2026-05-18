package com.example.keep.presentation.todayScreen

import android.content.Intent
import android.content.res.Configuration
import android.net.Uri
import android.util.Log
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.PickVisualMediaRequest
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.animation.AnimatedContent
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.animateContentSize
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.gestures.detectTapGestures
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.IntrinsicSize
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.calculateEndPadding
import androidx.compose.foundation.layout.calculateStartPadding
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.widthIn
import androidx.compose.foundation.layout.wrapContentWidth
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.foundation.text.input.TextFieldLineLimits
import androidx.compose.foundation.text.input.rememberTextFieldState
import androidx.compose.material.icons.automirrored.rounded.List
import androidx.compose.material.icons.rounded.Close
import androidx.compose.material.icons.rounded.Image
import androidx.compose.material.icons.rounded.Menu
import androidx.compose.material.icons.rounded.Mic
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
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.material3.VerticalDivider
import androidx.compose.material3.VerticalFloatingToolbar
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.focus.FocusDirection
import androidx.compose.ui.focus.FocusRequester
import androidx.compose.ui.focus.focusRequester
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.input.key.Key
import androidx.compose.ui.input.key.KeyEventType
import androidx.compose.ui.input.key.key
import androidx.compose.ui.input.key.onPreviewKeyEvent
import androidx.compose.ui.input.key.type
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.layout.ModifierLocalBeyondBoundsLayout
import androidx.compose.ui.layout.onGloballyPositioned
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.platform.LocalWindowInfo
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.sp
import coil3.compose.AsyncImage
import com.example.keep.domain.TimeStamp
import com.example.keep.domain.TimeStampElement
import com.example.keep.presentation.components.KeepEntryTextField
import com.example.keep.ui.theme.MyAppIconsAutoMirrored
import java.time.ZoneId
import java.time.format.DateTimeFormatter
import kotlin.time.ExperimentalTime
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.LayoutDirection
import java.time.Instant
import java.time.format.FormatStyle
import java.util.Locale

const val debugTag = "Debug"
val dateTimeFormatter = DateTimeFormatter.ofLocalizedTime(FormatStyle.SHORT)


@Composable
fun TodayScreenRoot(
    todayScreenViewmodel: TodayScreenViewmodel
){
    val uiState by todayScreenViewmodel.uiState.collectAsState()

    TodayScreen(
        uiState = uiState
    ) { action ->
        todayScreenViewmodel.onAction(action)
    }
}


@Composable
fun TodayScreen(
    uiState: TodayScreenState,
    modifier: Modifier = Modifier,
    onViewmodelAction: (TodayScreenAction) -> Unit
) {
    //Log.d(debugTag, "TodayScreen Composition with State: $uiState")

    val listState = rememberLazyListState()

    Scaffold(
        bottomBar = { KeepBottomBar() },
        modifier = modifier,
        containerColor = MaterialTheme.colorScheme.background,
        floatingActionButton = {
            FABMenu()
        }
    ) { contentPadding ->
        Box(modifier.padding(contentPadding)){
            val screenWidth = LocalWindowInfo.current.containerSize.width
            LazyColumn(
                state = listState,
                modifier = modifier
                    .fillMaxSize()
                    .pointerInput(null) {
                        detectTapGestures { offset ->
                            val lastVisibleItem = listState.layoutInfo.visibleItemsInfo.lastOrNull()
                            if (lastVisibleItem != null) {
                                val lastVisibleItemOffset = lastVisibleItem.offset
                                val lastVisibleItemSize = lastVisibleItem.size
                                val threshold = lastVisibleItemOffset + lastVisibleItemSize
                                val listStart = listState.layoutInfo.viewportStartOffset

                                val xBuffer = 24.dp.toPx()
                                val yBuffer = 12.dp.toPx()
                                if (
                                    xBuffer < offset.x &&
                                    screenWidth - xBuffer > offset.x &&
                                    threshold < offset.y + listStart - yBuffer
                                ) {
                                    onViewmodelAction(TodayScreenAction.OnEmptySpaceClicked)
                                }
                            }


                        }
                    }
                ,
                horizontalAlignment = Alignment.Start,
                verticalArrangement = Arrangement.spacedBy(12.dp),
                contentPadding = PaddingValues(24.dp)
            ) {
                item{
                    Title(
                        onTitleDone = {
                            onViewmodelAction(
                                TodayScreenAction.OnTitleDone
                            )
                        }
                    )
                }

                item {
                    DateAndImage(
                        imageOfTheDayUri = uiState.imageOfTheDay,
                        onImageOfTheDayPicked = {action ->
                            onViewmodelAction(action)
                        }
                    )
                }
                items(
                    items = uiState.timeStamps,
                    key = {it.timeStampId}
                ){
                        timeStamp ->
                    val firstElement = timeStamp.timeStampElements.firstOrNull()
                    val showDeleteButton =
                        uiState.timeStamps.lastIndex == uiState.timeStamps.indexOf(timeStamp)
                                && firstElement is TimeStampElement.Text
                                && firstElement.text.isEmpty()

                    TimeStamp(
                        modifier = Modifier
                            .animateItem(),
                        timeStamp = timeStamp,
                        showDeleteButton = showDeleteButton,
                        onTimeStampDeleted = {
                                action ->
                            onViewmodelAction(action)
                        },
                        onTextFieldEdited = {
                                action ->
                            onViewmodelAction(action)
                        }
                    )
                }

            }
        }
    }
}

@Composable
fun Title(
    modifier: Modifier = Modifier,
    onTitleDone: () -> Unit
) {
    TextField(
        state = rememberTextFieldState(),
        textStyle = MaterialTheme.typography.titleLarge.copy(lineHeight = 30.sp),
        placeholder = {
            Text(
                text = "Title...",
                style = MaterialTheme.typography.titleLarge.copy(fontWeight = FontWeight.ExtraLight),
                modifier = Modifier.fillMaxHeight()
            )
        },
        lineLimits = TextFieldLineLimits.SingleLine,
        contentPadding = PaddingValues(0.dp),
        colors = TextFieldDefaults.colors(
            focusedIndicatorColor = Color.Transparent,
            unfocusedIndicatorColor = Color.Transparent,
            focusedContainerColor = Color.Transparent,
            unfocusedContainerColor = Color.Transparent
        ),
        modifier = modifier
            .fillMaxWidth()
            .onPreviewKeyEvent {
            when {
                KeyEventType.KeyUp == it.type && Key.Enter == it.key -> {
                    onTitleDone()
                    true
                }
                else -> false
            }
        }
    )
}

@Composable
fun DateAndImage(
    imageOfTheDayUri: Uri?,
    modifier: Modifier = Modifier,
    onImageOfTheDayPicked: (TodayScreenAction) -> Unit
) {
    Row (
        modifier = modifier
            .clip(MaterialTheme.shapes.small)
            .fillMaxWidth()
            .height(230.dp)
            .border(
                1.dp,
                color = MaterialTheme.colorScheme.outline,
                shape = MaterialTheme.shapes.small
            )
            .background(
                MaterialTheme.colorScheme.surfaceVariant,
                shape = MaterialTheme.shapes.small
            ),
        horizontalArrangement = Arrangement.spacedBy(6.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {

        ImageOfTheDay(
            modifier = Modifier
                .weight(1f)
                .height(230.dp),
            imageUri = imageOfTheDayUri,
            onImageOfTheDayPicked = {action ->
                onImageOfTheDayPicked(action)
            }
        )
        Date()
    }
}

@Composable
fun ImageOfTheDay(
    imageUri: Uri?,
    modifier: Modifier = Modifier,
    onImageOfTheDayPicked: (TodayScreenAction) -> Unit
){
    val context = LocalContext.current
    val imageOfTheDayPickerLauncher = rememberLauncherForActivityResult(
        contract = ActivityResultContracts.PickVisualMedia(),
        onResult = {uri ->
            if (uri != null){
                context.contentResolver.takePersistableUriPermission(
                    uri,
                    Intent.FLAG_GRANT_READ_URI_PERMISSION
                )
            }

            onImageOfTheDayPicked(TodayScreenAction.OnImageOfTheDayPicked(uri))
        }
    )

    val modifier =  modifier
        .clickable(
            onClick = {
                Log.d("Debug", "OnImageClicked")
                imageOfTheDayPickerLauncher.launch(
                    input = PickVisualMediaRequest(ActivityResultContracts.PickVisualMedia.ImageOnly)
                )
            }
        )
    if (
        imageUri != null
    ){
        AsyncImage(
            model = imageUri,
            placeholder = painterResource(
                R.drawable.placeholder_image
            ),
            contentDescription = "Your Image of the Day. Click to Select a different Image",
            modifier = modifier,
            contentScale = ContentScale.Crop,
        )
    }
    else{
        Image(
            painter = painterResource(R.drawable.placeholder_image1),
            contentDescription = "Select Your Image of the Day",
            modifier = modifier,
            contentScale = ContentScale.Crop,
        )
    }
}

@Composable
fun FullImageOfTheDay(
    imageUri: Uri?,
    modifier: Modifier = Modifier
) {
    
    AsyncImage(
        model = imageUri,
        placeholder = painterResource(
            R.drawable.placeholder_image
        ),
        contentDescription = "Your Image of the Day",
        modifier = modifier
            .fillMaxWidth()
            .aspectRatio(1f),
        contentScale = ContentScale.Crop,
    )
}

@Composable
fun Date(modifier: Modifier = Modifier) {

    Column(
        modifier = Modifier
            .fillMaxHeight()
            .width(60.dp)
            .padding(end = 6.dp)
            ,
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Text(
            text = "MON",
            style = MaterialTheme.typography.titleMedium
        )
        Text(
            text = "04\nNOV\n 2026",
            style = MaterialTheme.typography.bodyMedium,
            textAlign = TextAlign.Center
        )
    }
}

@OptIn(ExperimentalTime::class)
@Composable
fun TimeStamp(
    modifier: Modifier = Modifier,
    showDeleteButton: Boolean = false,
    timeStamp: TimeStamp,
    onTimeStampDeleted: (TodayScreenAction) -> Unit,
    onTextFieldEdited: (TodayScreenAction) -> Unit
) {

    Row(
        modifier = modifier.fillMaxWidth().height(IntrinsicSize.Min),
        horizontalArrangement = Arrangement.spacedBy(6.dp)
    ){
        Log.d("Debug", "Time: ${timeStamp.time}")



        Column (
            modifier = Modifier.weight(1f).fillMaxWidth(),
            verticalArrangement = Arrangement.spacedBy(6.dp),
            horizontalAlignment = Alignment.Start
        ) {
            val focusRequester = remember { FocusRequester() }

            LaunchedEffect(showDeleteButton) {
                focusRequester.requestFocus()
            }



            val lastText = timeStamp.timeStampElements.filterIsInstance<TimeStampElement.Text>().lastOrNull()

            for (element in timeStamp.timeStampElements) {
                when (element) {
                    is TimeStampElement.Text -> {
                        var mod = Modifier.fillMaxWidth()
                        if (element == lastText){
                            mod = mod.focusRequester(focusRequester)
                        }
                        KeepEntryTextField(
                            value = element.text,
                            onValueChange = { newValue ->
                                onTextFieldEdited(
                                    TodayScreenAction.OnTextFieldEdited(
                                        timeStamp.timeStampId,
                                        element.elementId,
                                        newValue
                                    )
                                )
                            },
                            modifier = mod

                        )
                    }

                    is TimeStampElement.ImageList ->
                        ImageRow(
                            element.images
                        )
                }
            }

        }
        Row(
            modifier = Modifier.width(65.dp)
        ){
            VerticalDivider(
                thickness = Dp.Hairline,
                modifier = Modifier.padding(end = 6.dp)
            )
            AnimatedContent(targetState = showDeleteButton) { showButton ->
                if (showButton) {
                    IconButton(
                        onClick = {onTimeStampDeleted(
                            TodayScreenAction.OnTimeStampDeleted(
                                timeStamp.timeStampId
                            )
                        ) },
                        modifier = Modifier
                            .padding(start = 6.dp)
                            .size(24.dp)
                    ) {
                        Icon(
                            imageVector = MyAppIcons.Close,
                            contentDescription = "Remove Timestamp",
                            tint = MaterialTheme.colorScheme.outline
                        )
                    }
                } else {
                    Text(
                        text = dateTimeFormatter.format(timeStamp.time.atZone(ZoneId.systemDefault())),
                        style = MaterialTheme.typography.labelLarge,
                        color = MaterialTheme.colorScheme.outlineVariant
                    )
                }
            }

        }
    }


//        Row(
//            verticalAlignment = Alignment.Top,
//            modifier = Modifier.height(40.dp)
//        ){
//            Column (
//                modifier = Modifier
//                    .weight(1f)
//                    .offset(y = 11.dp),
//                verticalArrangement = Arrangement.Bottom
//            ) {
//                HorizontalDivider(modifier = Modifier)
//                Text(
//                    text = dateTimeFormatter.format(timeStamp.time),
//                    style = MaterialTheme.typography.labelLarge,
//                    color = MaterialTheme.colorScheme.outlineVariant
//                )
//            }
//            AnimatedVisibility(showDeleteButton){
//                IconButton(
//                    onClick = {onTimeStampDeleted(
//                        TodayScreenAction.OnTimeStampDeleted(
//                            timeStamp.timeStampId
//                        )
//                    ) },
//                    modifier = Modifier
//                        .padding(start = 6.dp)
//                        .size(24.dp)
//                ) {
//                    Icon(
//                        imageVector = MyAppIcons.Close,
//                        contentDescription = "Remove Timestamp",
//                        tint = MaterialTheme.colorScheme.outline
//                    )
//                }
//            }
//        }


}

@Composable
fun ImageRow(
    imageUris: List<Uri>,
    modifier: Modifier = Modifier
) {
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



@Preview(showBackground = true, widthDp = 390, heightDp = 800, uiMode = Configuration.UI_MODE_NIGHT_NO,
    device = "spec:width=1080px,height=2340px,dpi=440,isRound=true",
    showSystemUi = false,
)
@Preview(showBackground = true, widthDp = 390, heightDp = 800, uiMode = Configuration.UI_MODE_NIGHT_YES)
@Composable
fun TodayScreenPreview(modifier: Modifier = Modifier) {
    KeepTheme {
        TodayScreen(
            TodayScreenState(
                timeStamps = listOf(
                    TimeStamp(
                        timeStampId = 0,
                        time = Instant.now(),
                        timeStampElements = listOf(
                            TimeStampElement.Text("")
                        )
                    )
                )
            )
        ) { }
    }
}