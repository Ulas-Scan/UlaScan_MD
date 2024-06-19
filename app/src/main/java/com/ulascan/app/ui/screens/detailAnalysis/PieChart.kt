package com.ulascan.app.ui.screens.detailAnalysis

import androidx.compose.animation.core.LinearOutSlowInEasing
import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.core.tween
import androidx.compose.foundation.Canvas
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.rotate
import androidx.compose.ui.draw.scale
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.StrokeCap
import androidx.compose.ui.graphics.drawscope.Stroke
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.IntOffset
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.ulascan.app.ui.theme.Brand700
import com.ulascan.app.ui.theme.ChartNegative
import com.ulascan.app.ui.theme.Error600

@Composable
fun PieChart(
    data: Map<String, Int>,
    radiusOuter: Dp = 190.dp,
    animDuration: Int = 1000,
) {
    val configuration = LocalConfiguration.current
    val screenWidth = configuration.screenWidthDp.dp
    val chartBarWidth = (screenWidth * 0.3f).value

    val totalSum = data.values.sum()
    val floatValue = mutableListOf<Float>()

    data.values.forEachIndexed { index, values ->
        floatValue.add(index, 360 * values.toFloat() / totalSum.toFloat())
    }

    val colors = listOf(
        Brand700,
        ChartNegative,
    )

    var animationPlayed by remember { mutableStateOf(false) }

    var lastValue = 0f

    val animateSize by animateFloatAsState(
        targetValue = if (animationPlayed) radiusOuter.value * 1.5f else 0f,
        animationSpec = tween(durationMillis = animDuration, delayMillis = 0, easing = LinearOutSlowInEasing),
        label = ""
    )

    val animateRotation by animateFloatAsState(
        targetValue = if (animationPlayed) 90f * 11f else 0f,
        animationSpec = tween(durationMillis = animDuration, delayMillis = 0, easing = LinearOutSlowInEasing),
        label = ""
    )

    LaunchedEffect(key1 = true) { animationPlayed = true }

    Column(
        modifier = Modifier.fillMaxWidth(),
        horizontalAlignment = Alignment.CenterHorizontally,
    ) {
        Box(
            modifier = Modifier.size(animateSize.dp),
            contentAlignment = Alignment.Center,
        ) {
            Canvas(
                modifier = Modifier.offset { IntOffset.Zero }
                    .size(radiusOuter * 1f)
                    .rotate(animateRotation)
                    .scale(1f)
            ) {
                drawCircle(color = Color.White, radius = radiusOuter.toPx() - radiusOuter.toPx() / 3)
                floatValue.forEachIndexed { index, value ->
                    drawArc(
                        color = colors[index],
                        startAngle = lastValue - chartBarWidth,
                        sweepAngle = value - chartBarWidth/3.5f,
                        useCenter = false,
                        style = Stroke(width = chartBarWidth, cap = StrokeCap.Round)
                    )
                    //testing
                    lastValue += value
                }
            }
        }
        DetailsPieChart(data = data, colors = colors, size = animateSize)
    }
}

@Composable
fun DetailsPieChart(data: Map<String, Int>, colors: List<Color>, size: Float) {
    Column(
        modifier = Modifier.padding(horizontal = 32.dp).padding(bottom = 12.dp).width(size.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        data.values.forEachIndexed { index, value ->
            DetailsPieChartItem(data = Pair(data.keys.elementAt(index), value), color = colors[index])
        }
    }
}

@Composable
fun DetailsPieChartItem(data: Pair<String, Int>, height: Dp = 32.dp, color: Color) {
    Surface(modifier = Modifier, color = Color.Transparent) {
        Row(
            modifier = Modifier.fillMaxWidth().padding(bottom = 8.dp),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.Center
        ) {
            Box(
                modifier = Modifier.background(color = color, shape = RoundedCornerShape(10.dp)).size(height)
            )
            Column(modifier = Modifier.fillMaxWidth()) {
                Text(
                    modifier = Modifier.padding(start = 15.dp),
                    text = data.first,
                    fontWeight = FontWeight.Medium,
                    fontSize = 14.sp,
                    color = Color.Black
                )
                Text(
                    modifier = Modifier.padding(start = 15.dp),
                    text = data.second.toString(),
                    fontWeight = FontWeight.Medium,
                    fontSize = 14.sp,
                    color = Color.Gray
                )
            }
        }
    }
}

@Composable
fun NormalPieChart(
    data: Map<String, Int>,
    radiusOuter: Dp = 25.dp,
    animDuration: Int = 1000,
) {
    val configuration = LocalConfiguration.current
    val screenWidth = configuration.screenWidthDp.dp
    val chartBarWidth = (screenWidth * 0.1f).value

    val totalSum = data.values.sum()
    val floatValue = mutableListOf<Float>()

    data.values.forEachIndexed { index, values ->
        floatValue.add(index, 360 * values.toFloat() / totalSum.toFloat())
    }

    val colors = listOf(
        Error600,
        Brand700,
    )

    var animationPlayed by remember { mutableStateOf(false) }

    var lastValue = 0f

    val animateSize by animateFloatAsState(
        targetValue = if (animationPlayed) radiusOuter.value * 2f else 0f,
        animationSpec = tween(durationMillis = animDuration, delayMillis = 0, easing = LinearOutSlowInEasing),
        label = ""
    )

    val animateRotation by animateFloatAsState(
        targetValue = if (animationPlayed) 90f * 11f else 0f,
        animationSpec = tween(durationMillis = animDuration, delayMillis = 0, easing = LinearOutSlowInEasing),
        label = ""
    )

    LaunchedEffect(key1 = true) { animationPlayed = true }

    Box(modifier = Modifier.size(animateSize.dp), contentAlignment = Alignment.Center) {
        Canvas(
            modifier = Modifier.offset { IntOffset.Zero }.size(radiusOuter * 2f).rotate(animateRotation)
        ) {
            floatValue.forEachIndexed { index, value ->
                drawArc(
                    color = colors[index],
                    startAngle = lastValue + 90f,
                    sweepAngle = value,
                    useCenter = false,
                    style = Stroke(chartBarWidth, cap = StrokeCap.Butt)
                )
                lastValue += value
            }
        }
    }
}
