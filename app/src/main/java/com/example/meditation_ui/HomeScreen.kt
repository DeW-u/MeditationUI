package com.example.meditation_ui

import android.widget.Button
import androidx.compose.animation.animateColorAsState
import androidx.compose.animation.core.*
import androidx.compose.foundation.Canvas
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.GridCells
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.LazyVerticalGrid
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Path
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.meditation_ui.ui.theme.*


@ExperimentalFoundationApi
@Preview
@Composable
fun HomeScreen() {
    var currentlyPlaying: String by remember {
        mutableStateOf("Sweet Sleep")
    }
    Box(
        modifier = Modifier
            .background(DeepBlue)
            .fillMaxSize()
    ) {
        Column {
            GreetingRow(name = "Daniel")
            ChipSection(chips = listOf("Sweet Sleep", "Insomnia", "Depression", "Happiness")){
                currentlyPlaying = it
            }
            CurrentMeditation(currentlyPlaying = currentlyPlaying)
            FeatureSection(features = listOf(
                Feature(
                    title = "Sleep Meditation",
                    R.drawable.ic_headset,
                    BlueViolet1,
                    BlueViolet2,
                    BlueViolet3
                ),
                Feature(
                    title = "Tips for sleeping",
                    R.drawable.ic_videocam,
                    LightGreen1,
                    LightGreen2,
                    LightGreen3
                ),
                Feature(
                    title = "Night Island",
                    R.drawable.ic_headset,
                    OrangeYellow1,
                    OrangeYellow2,
                    OrangeYellow3
                ),
                Feature(
                    title = "Calming Sounds",
                    R.drawable.ic_headset,
                    Beige1,
                    Beige2,
                    Beige3
                )
            ))
        }
        BottomMenu(items = listOf(
            BottomMenuContent("Home", R.drawable.ic_home),
            BottomMenuContent("Meditate", R.drawable.ic_meditation),
            BottomMenuContent("Sleep", R.drawable.ic_bed),
            BottomMenuContent("Music", R.drawable.ic_music),
            BottomMenuContent("Profile", R.drawable.ic_account)
        ), modifier = Modifier.align(Alignment.BottomCenter))
    }

}

@Composable
fun GreetingRow(
    name: String = "Poggers"
) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(15.dp)
            .wrapContentHeight(),
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically
    ) {
        Column(verticalArrangement = Arrangement.Center)
        {
            Text(
                text = "What's up, ${name}?",
                fontWeight = FontWeight.Bold,
                color = TextWhite,
                style = MaterialTheme.typography.h5,
                maxLines = 1
            )
            Spacer(modifier = Modifier.height(5.dp))
            Text(
                text = "I hope you are just fine!",
                style = MaterialTheme.typography.body1,
                color = DarkerButtonBlue,
                textAlign = TextAlign.Start,
                maxLines = 1
            )
        }
        Icon(
            painter = painterResource(id = R.drawable.ic_search),
            contentDescription = "Search",
            tint = TextWhite,
            modifier = Modifier.size(40.dp)

        )
    }
}

@Composable
fun ChipSection(
    chips: List<String>,
    updateCurrentlyPlaying: (String) -> Unit
) {
    var selectedChipIndex by remember {
        mutableStateOf(0)
    }

    LazyRow(
        modifier = Modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.SpaceEvenly
    ) {
        items(chips.size) {

            val color: Color by animateColorAsState(
                targetValue = if(selectedChipIndex == it) ButtonBlue else DarkerButtonBlue,
                tween(750, 10, easing = FastOutSlowInEasing)
            )
            Box(modifier = Modifier
                .padding(
                    start = 15.dp,
                    top = 15.dp,
                    bottom = 15.dp,
                    end = if (it != chips.size - 1) 0.dp else 15.dp
                )
                .clickable {
                    selectedChipIndex = it
                    updateCurrentlyPlaying(
                        chips[it]
                    )
                }
                .clip(RoundedCornerShape(10.dp))
                .background(
                    color
                )
                .padding(15.dp)
            ) {
                Text(text = chips[it], color = TextWhite)
            }
        }
    }
}

@Composable
fun CurrentMeditation(
    color: Color = LightRed1,
    currentlyPlaying: String
) {
    BoxWithConstraints(
        modifier = Modifier
            .padding(15.dp)
            .aspectRatio(4f)
            .clip(RoundedCornerShape(10.dp))
            .background(color)
            .padding(15.dp)
    ) {
        val width = constraints.maxWidth
        val height = constraints.maxHeight

        //Medium colored path
        val mediumColoredPath1 = Offset(0f, height.toFloat())
        val mediumColoredPath2 = Offset(width*0.1f, height * 0.2f)
        val mediumColoredPath3 = Offset(width*0.4f, height * 0.6f)
        val mediumColoredPath4 = Offset(width*0.75f, height * 0.3f)
        val mediumColoredPath5 = Offset(width*1.4f, 0f)

        val mediumColorPath = Path().apply {
            moveTo(mediumColoredPath1.x, mediumColoredPath1.y)
            standardQuadFromTo(mediumColoredPath1, mediumColoredPath2)
            standardQuadFromTo(mediumColoredPath2, mediumColoredPath3)
            standardQuadFromTo(mediumColoredPath3, mediumColoredPath4)
            standardQuadFromTo(mediumColoredPath4, mediumColoredPath5)
            lineTo(width.toFloat() + 100f, height.toFloat() + 100f)
            lineTo(-100f, height.toFloat() + 100f)
            close()
        }

        // Light colored path
        val lightColoredPath1 = Offset(0f, height.toFloat())
        val lightColoredPath2 = Offset(width*0.1f, height * 0.4f)
        val lightColoredPath3 = Offset(width*0.4f, height * 0.9f)
        val lightColoredPath4 = Offset(width*0.75f, height * 0.6f)
        val lightColoredPath5 = Offset(width*1.4f, height * 0.3f)

        val lightColorPath = Path().apply {
            moveTo(lightColoredPath1.x, lightColoredPath1.y)
            standardQuadFromTo(lightColoredPath1, lightColoredPath2)
            standardQuadFromTo(lightColoredPath2, lightColoredPath3)
            standardQuadFromTo(lightColoredPath3, lightColoredPath4)
            standardQuadFromTo(lightColoredPath4, lightColoredPath5)
            lineTo(width.toFloat() + 100f, height.toFloat() + 100f)
            lineTo(-100f, height.toFloat() + 100f)
            close()
        }

        Canvas(modifier = Modifier.fillMaxSize()){
            drawPath(path = mediumColorPath, color = LightRed2)
            drawPath(path = lightColorPath, color = LightRed3)
        }
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .wrapContentHeight(),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {

            Column {
                Text(
                    text = "Currently Playing:",
                    textAlign = TextAlign.Start,
                    color = TextWhite,
                    maxLines = 1
                )
                Spacer(modifier = Modifier.padding(3.dp))
                Text(
                    text = currentlyPlaying,
                    fontWeight = FontWeight.SemiBold,
                    color = TextWhite,
                    style = MaterialTheme.typography.h5,
                    textAlign = TextAlign.Start,
                    maxLines = 1
                )

            }
            Box(modifier = Modifier
                .wrapContentSize()
                .clip(CircleShape)
                .background(ButtonBlue)
            ) {
                Button(
                    onClick = {},
                    colors = ButtonDefaults.buttonColors(backgroundColor = ButtonBlue),
                    shape = CircleShape,
                    contentPadding = PaddingValues(0.dp),
                    modifier = Modifier.size(40.dp)
                ) {
                    Icon(
                    painter = painterResource(id = R.drawable.ic_play),
                    contentDescription = "Play",
                    tint = TextWhite,
                    modifier = Modifier
                        .size(40.dp)
                    )
                }
            }
        }
    }
}

@ExperimentalFoundationApi
@Composable
fun FeatureSection(
    features: List<Feature>
) {
    Column(modifier = Modifier.fillMaxWidth()) {
        Text(
            text = "Featured",
            color = TextWhite,
            style = MaterialTheme.typography.h4,
            fontWeight = FontWeight.Bold,
            modifier = Modifier.padding(start = 15.dp, top = 10.dp, end = 15.dp, bottom = 10.dp)
        )
        LazyVerticalGrid(
            cells = GridCells.Fixed(2),
            contentPadding = PaddingValues(start = 7.5.dp, top = 15.dp, bottom = 100.dp, end = 7.5.dp),
            modifier = Modifier.fillMaxHeight()
        ){
            items(count = features.size){
                FeatureItem(feature = features[it])
            }
        }

    }
}

@Composable
fun BottomMenu(
    items: List<BottomMenuContent>,
    modifier: Modifier = Modifier,
    activeHighlightColor: Color = ButtonBlue,
    activeTextColor: Color = TextWhite,
    inactiveTextColor: Color = AquaBlue,
    initialSelectedItem: Int = 0
){
    var selectedItemIndex by remember {
        mutableStateOf(initialSelectedItem)
    }
    Row(
        horizontalArrangement = Arrangement.SpaceAround,
        verticalAlignment = Alignment.CenterVertically,
        modifier = modifier
            .fillMaxWidth()
            .background(DeepBlue)
            .padding(15.dp)
    ) {
        items.forEachIndexed { index, item ->
            BottomMenuItem(
                item = item,
                isSelected = index == selectedItemIndex,
                activeHighlightColor = activeHighlightColor,
                activeTextColor = activeTextColor,
                inactiveTextColor = inactiveTextColor
            ) {
                selectedItemIndex = index
            }
        }
    }
}

@Composable
fun BottomMenuItem(
    item: BottomMenuContent,
    isSelected: Boolean = false,
    activeHighlightColor: Color,
    activeTextColor: Color,
    inactiveTextColor: Color,
    onItemClick: ()->Unit
){
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center,
        modifier = Modifier.clickable {
            onItemClick()
        }
    ) {
        Box(
            contentAlignment = Alignment.Center,
            modifier = Modifier
                .clip(RoundedCornerShape(10.dp))
                .background(if (isSelected) activeHighlightColor else Color.Transparent)
                .padding(10.dp)
        ) {
            Icon(
                painter = painterResource(id = item.iconId),
                contentDescription = item.title,
                tint = if (isSelected) activeTextColor else inactiveTextColor,
                modifier = Modifier.size(20.dp)
            )
        }
        Spacer(modifier = Modifier.height(5.dp))
        Text(
            text = item.title,
            color = if (isSelected) activeTextColor else inactiveTextColor
        )
    }
}