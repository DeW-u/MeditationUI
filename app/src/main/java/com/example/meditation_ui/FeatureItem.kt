package com.example.meditation_ui

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Canvas
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Path
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.meditation_ui.ui.theme.ButtonBlue
import com.example.meditation_ui.ui.theme.TextWhite

@Composable
fun FeatureItem(
    feature: Feature
) {
    BoxWithConstraints(
        modifier = Modifier
            .padding(7.5.dp)
            .aspectRatio(1f)
            .clip(RoundedCornerShape(10.dp))
            .background(feature.darkColor)
    ) {
        val width = constraints.maxWidth
        val height = constraints.maxHeight

        //Medium colored path
        val mediumColoredPath1 = Offset(0f, height * 0.3f)
        val mediumColoredPath2 = Offset(width * 0.1f, height * 0.35f)
        val mediumColoredPath3 = Offset(width * 0.4f, height * 0.05f)
        val mediumColoredPath4 = Offset(width * 0.75f, height * 0.7f)
        val mediumColoredPath5 = Offset(width * 1.4f, height * 1.4f)

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
        val lightColoredPath1 = Offset(0f, height * 0.35f)
        val lightColoredPath2 = Offset(width * 0.1f, height * 0.4f)
        val lightColoredPath3 = Offset(width * 0.3f, height * 0.35f)
        val lightColoredPath4 = Offset(width * 0.65f, height.toFloat())
        val lightColoredPath5 = Offset(width * 1.4f, height * 1.4f * 3f)

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

        Canvas(modifier = Modifier.fillMaxSize()) {
            drawPath(path = mediumColorPath, color = feature.mediumColor)
            drawPath(path = lightColorPath, color = feature.lightColor)
        }
        Box(
            modifier = Modifier
                .fillMaxSize()
                .padding(15.dp)
        ) {
            Text(
                text = feature.title,
                color = TextWhite,
                style = MaterialTheme.typography.h6,
                lineHeight = 25.sp,
                modifier = Modifier.align(Alignment.TopStart)
            )
            Icon(
                painter = painterResource(id = feature.iconId),
                contentDescription = "Icon #${feature.title}",
                tint = TextWhite,
                modifier = Modifier.align(Alignment.BottomStart)
            )
//            Text(
//                text = "Start",
//                color = TextWhite,
//                fontSize = 14.sp,
//                fontWeight = FontWeight.Bold,
//                modifier = Modifier
//                    .align(Alignment.BottomEnd)
//                    .clip(RoundedCornerShape(10.dp))
//                    .background(ButtonBlue)
//                    .padding(vertical = 7.dp, horizontal = 15.dp)
//                    .clickable(true){}
//            )
            Button(
                onClick = {},
                shape = RoundedCornerShape(10.dp),
                colors = ButtonDefaults.buttonColors(ButtonBlue),
                modifier = Modifier.align(Alignment.BottomEnd),
                contentPadding = PaddingValues(horizontal = 10.dp, vertical = 5.dp)
            ) {
                Text(
                    text = "Start",
                    color = TextWhite,
                    fontSize = 14.sp,
                    fontWeight = FontWeight.Bold,
                )
            }
        }
    }
}