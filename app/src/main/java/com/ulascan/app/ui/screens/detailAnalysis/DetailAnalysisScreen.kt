package com.ulascan.app.ui.screens.detailAnalysis


import android.provider.Telephony.Mms.Sent
import androidx.compose.foundation.Image
import androidx.compose.foundation.ScrollState
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.TextUnit
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.ulascan.app.R
import com.ulascan.app.ui.theme.Brand200
import com.ulascan.app.ui.theme.Brand900
import com.ulascan.app.ui.theme.Neutral900

@Composable

fun DetailAnalysisScreen(scrollState: ScrollState) {
    Box(
        modifier = Modifier.fillMaxSize()
    ) {
        Column(
            modifier = Modifier
                .fillMaxHeight()
                .fillMaxWidth()
                .background(
                    brush = Brush.verticalGradient(
                        colors = listOf(
                            Color(0xFF50AF6E), // 0% - #50AF6E
                            Color(0xFFAAC3B2), // 44% - #AAC3B2
                            Color(0xFFE1F4E7), // 74% - #E1F4E7
                            Color.White      // 100% - White
                        ),
                        startY = 0.0f,
                        endY = Float.POSITIVE_INFINITY
                    )
                )
                .align(Alignment.TopCenter)
        ) {
            // Empty Column to provide the background
        }

        Column(
            modifier = Modifier
                .fillMaxSize()
                .verticalScroll(scrollState)
                .padding(16.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Row(
                modifier = Modifier
                    .fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceAround,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Image(
                    painter = painterResource(id = R.drawable.ic_back),
                    contentDescription = "back",
                    modifier = Modifier.size(46.dp)
                )
                Row {
                    Text(
                        text = "Ula",
                        fontSize = 32.sp,
                        fontWeight = FontWeight.Normal,
                        color = Color.White
                    )
                    Text(
                        text = "Scan",
                        fontSize = 32.sp,
                        fontWeight = FontWeight.Bold,
                        color = Color.White
                    )
                }
                Text(
                    text = "Ula",
                    fontSize = 32.sp,
                    fontWeight = FontWeight.Normal,
                    color = Color.Transparent
                )
            }
            PieChart(
                data = mapOf(
                    Pair("Sample-1", 80),
                    Pair("Sample-2", 20),
                )
            )

            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .clip(shape = RoundedCornerShape(20.dp))
                    .background(Color.White),

                ) {
                Text(
                    text = "80% of buyers are satisfied \n" +
                            "with purchasing the product overall",
                    color = Brand900,
                    fontSize = 16.sp,
                    fontWeight = FontWeight.Bold,
                    textAlign = TextAlign.Center,
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(16.dp)
                )
            }

            Spacer(modifier = Modifier.size(16.dp))

            Row(
                modifier = Modifier
                    .fillMaxWidth(),
                horizontalArrangement = Arrangement.spacedBy(16.dp)
            ) {
                Column(
                    modifier = Modifier
                        .fillMaxWidth()
                        .weight(1f)
                        .clip(shape = RoundedCornerShape(20.dp))
                        .background(Color.White)
                        .padding(vertical = 12.dp, horizontal = 18.dp),
                    verticalArrangement = Arrangement.spacedBy(6.dp),
                    horizontalAlignment = Alignment.CenterHorizontally,

                    ) {
                    Image(
                        painter = painterResource(id = R.drawable.ic_person),
                        contentDescription = "back",
                        modifier = Modifier.size(46.dp)
                    )
                    Text(
                        text = "17",
                        color = Neutral900,
                        fontSize = 16.sp,
                        fontWeight = FontWeight.Bold,
                        textAlign = TextAlign.Center,
                        modifier = Modifier
                            .fillMaxWidth()
                    )
                    Text(
                        text = "Rating",
                        color = Neutral900,
                        fontSize = 16.sp,
                        fontWeight = FontWeight.Normal,
                        textAlign = TextAlign.Center,
                        modifier = Modifier
                            .fillMaxWidth()
                    )
                }

                Column(
                    modifier = Modifier
                        .fillMaxWidth()
                        .weight(1f)
                        .clip(shape = RoundedCornerShape(20.dp))
                        .background(Color.White)
                        .padding(vertical = 12.dp, horizontal = 18.dp),
                    verticalArrangement = Arrangement.spacedBy(6.dp),
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    Image(
                        painter = painterResource(id = R.drawable.ic_star),
                        contentDescription = "star",
                        modifier = Modifier.size(46.dp)
                    )
                    Text(
                        text = "4.9/5.0",
                        color = Neutral900,
                        fontSize = 16.sp,
                        fontWeight = FontWeight.Bold,
                        textAlign = TextAlign.Center,
                        modifier = Modifier
                            .fillMaxWidth()
                    )
                    Text(
                        text = "Bintang",
                        color = Neutral900,
                        fontSize = 16.sp,
                        fontWeight = FontWeight.Normal,
                        textAlign = TextAlign.Center,
                        modifier = Modifier
                            .fillMaxWidth()
                    )
                }
                Column(
                    modifier = Modifier
                        .fillMaxWidth()
                        .weight(1f)
                        .clip(shape = RoundedCornerShape(20.dp))
                        .background(Color.White)
                        .padding(vertical = 12.dp, horizontal = 18.dp),
                    verticalArrangement = Arrangement.spacedBy(6.dp),
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    Image(
                        painter = painterResource(id = R.drawable.ic_pen),
                        contentDescription = "pen",
                        modifier = Modifier.size(46.dp)
                    )
                    Text(
                        text = "9",
                        color = Neutral900,
                        fontSize = 16.sp,
                        fontWeight = FontWeight.Bold,
                        textAlign = TextAlign.Center,
                        modifier = Modifier
                            .fillMaxWidth()
                    )
                    Text(
                        text = "Ulasan",
                        color = Neutral900,
                        fontSize = 16.sp,
                        fontWeight = FontWeight.Normal,
                        textAlign = TextAlign.Center,
                        modifier = Modifier
                            .fillMaxWidth()
                    )
                }
            }

            Spacer(modifier = Modifier.size(16.dp))

//            Ringkasan Analisis
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .clip(shape = RoundedCornerShape(20.dp))
                    .background(Color.White)
                    .padding(16.dp),

                ) {

                Text(
                    text = "Ringkasan Analisis",
                    color = Neutral900,
                    fontSize = 16.sp,
                    fontWeight = FontWeight.Bold,
                    textAlign = TextAlign.Start,
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(bottom = 8.dp)
                )
                Text(
                    text = "Lorem Ipsum is simply dummy text of the printing and typesetting industry. Lorem Ipsum has been the industry's standard dummy text ever since the 1500s, when an unknown printer took a galley of type and scrambled it to make a type specimen book.",
                    color = Neutral900,
                    fontSize = 12.sp,
                    fontWeight = FontWeight.Medium,
                    textAlign = TextAlign.Justify,
                    modifier = Modifier
                        .fillMaxWidth()
                )
            }

            Spacer(modifier = Modifier.size(16.dp))

            // Sentiment Analysis

            SentimentAnalysis()

            Spacer(modifier = Modifier.size(16.dp))

//            Product Condition
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .clip(shape = RoundedCornerShape(20.dp))
                    .background(Color.White)
                    .padding(16.dp),

                ) {

                Text(
                    text = "Ringkasan Analisis",
                    color = Neutral900,
                    fontSize = 16.sp,
                    fontWeight = FontWeight.Bold,
                    textAlign = TextAlign.Start,
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(bottom = 8.dp)
                )

                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .clip(shape = RoundedCornerShape(20.dp))
                        .background(Color.White)
                        .padding(16.dp)
                    ,
                    horizontalArrangement = Arrangement.spacedBy(20.dp),
                    verticalAlignment = Alignment.CenterVertically
                    ) {

                    NormalPieChart(
                        data = mapOf(
                            Pair("Sample-1", 50),
                            Pair("Sample-2", 50),
                        )
                    )

                    Text(
                        text = "50% say that the item is not defective at all and functions well.",
                        color = Neutral900,
                        fontSize = 14.sp,
                        fontWeight = FontWeight.Normal,
                        textAlign = TextAlign.Justify,
                        modifier = Modifier
                            .fillMaxWidth()
                    )
                }
            }
        }
    }
}

@Composable
fun SentimentAnalysis(){
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .clip(shape = RoundedCornerShape(20.dp))
            .background(Color.White)
            .padding(16.dp),
        verticalArrangement = Arrangement.spacedBy(8.dp)
        ) {
        SentimentItems()
        SentimentItems()
        SentimentItems()
    }
}
@Composable
fun SentimentItems(){
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .clip(shape = RoundedCornerShape(20.dp))
            .background(Color.White)
            .padding(8.dp),
        horizontalArrangement = Arrangement.spacedBy(8.dp),
        verticalAlignment = Alignment.CenterVertically
        ) {

        CircleWithNumber(number = 90)

        Column(
            modifier = Modifier
                .fillMaxWidth()
//                .border(2.dp, color = Color.Black)
            ,
            verticalArrangement = Arrangement.spacedBy(4.dp)
        ) {
            Text(text = "Packaging",
                color = Color.Black,
                fontSize = 10.sp,
                fontWeight = FontWeight.Normal,
                textAlign = TextAlign.Start)

            Text(text = "90% of buyers are satisfied regarding packaging safety.",
                color = Color.Black,
                fontSize = 14.sp,
                fontWeight = FontWeight.Normal,
                textAlign = TextAlign.Start)
        }
    }
}

@Composable
fun CircleWithNumber(
    number: Int,
) {
    Box(
        contentAlignment = Alignment.Center,
        modifier = Modifier
            .size(60.dp)
            .background(color = Brand200, shape = CircleShape)
//            .border(2.dp, color = Color.Black)
    ) {
        Text(
            text = "$number%",
            color = Color.Black,
            fontSize = 24.sp,
            fontWeight = FontWeight.Normal,
            textAlign = TextAlign.Center
        )
    }
}

//@Preview(showBackground = true)
//@Composable
//fun DetailAnalysisPreview() {
//    UlaScanTheme {
//        DetailAnalysisScreen()
//    }
//}