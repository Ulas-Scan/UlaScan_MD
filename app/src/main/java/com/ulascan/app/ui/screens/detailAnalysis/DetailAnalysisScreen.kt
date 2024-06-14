package com.ulascan.app.ui.screens.detailAnalysis


import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.ulascan.app.R
import com.ulascan.app.ui.theme.Brand200
import com.ulascan.app.ui.theme.Brand900
import com.ulascan.app.ui.theme.Neutral900
import com.ulascan.app.ui.theme.UlaScanTheme

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun DetailAnalysisScreen(navController: NavController = rememberNavController()) {
    val scrollState = rememberLazyListState()
    val backgroundColor by remember {
        derivedStateOf {
            if (scrollState.firstVisibleItemScrollOffset > 0) Color.White else Color.Transparent
        }
    }

    Box(modifier = Modifier.fillMaxSize()) {
        LazyColumn(
            state = scrollState,
            modifier = Modifier
                .fillMaxSize()
                .background(
                    brush = Brush.verticalGradient(
                        colors = listOf(
                            Color(0xFF50AF6E),
                            Color(0xFFAAC3B2),
                            Color(0xFFE1F4E7),
                            Color.White
                        ),
                        startY = 0.0f,
                        endY = Float.POSITIVE_INFINITY
                    )
                )
        ) {
            stickyHeader {
                HeaderRow(navController, title = "Produk makanan ini judul produk ditaruh sini ya", backgroundColor)
            }
            items(1) {
                Column(modifier = Modifier.padding(horizontal = 16.dp)) {

                    PieChart(data = mapOf("Review Positif" to 78, "Review Negatif" to 22))

                    Box(
                        modifier = Modifier
                            .fillMaxWidth()
                            .clip(shape = RoundedCornerShape(20.dp))
                            .background(Color.White)
                    ) {
                        Text(
                            text = "80% of buyers are satisfied \nwith purchasing the product overall",
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

                    ProductStats(rating = 17, stars = 4.8f, reviews = 99)

                    Spacer(modifier = Modifier.size(16.dp))

                    // Ringkasan Analisis
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
                            text = "Lorem Ipsum is simply dummy text of the printing and typesetting industry...",
                            color = Neutral900,
                            fontSize = 12.sp,
                            fontWeight = FontWeight.Medium,
                            textAlign = TextAlign.Justify,
                            modifier = Modifier.fillMaxWidth()
                        )
                    }

                    Spacer(modifier = Modifier.size(16.dp))

                    SentimentAnalysis()

                    Spacer(modifier = Modifier.size(16.dp))

                    // Product Condition
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
                                .padding(16.dp),
                            horizontalArrangement = Arrangement.spacedBy(20.dp),
                            verticalAlignment = Alignment.CenterVertically
                        ) {
                            NormalPieChart(data = mapOf("Sample-1" to 50, "Sample-2" to 50))

                            Text(
                                text = "50% say that the item is not defective at all and functions well.",
                                color = Neutral900,
                                fontSize = 14.sp,
                                fontWeight = FontWeight.Normal,
                                textAlign = TextAlign.Justify,
                                modifier = Modifier.fillMaxWidth()
                            )
                        }
                    }
                }

            }
        }
    }
}

@Composable
fun HeaderRow(navController: NavController, title: String, backgroundColor: Color) {
    val imageResource = if (backgroundColor == Color.White) {
        R.drawable.ic_back_brand
    } else {
        R.drawable.ic_back
    }

    Row(
        modifier = Modifier
            .fillMaxWidth()
            .height(85.dp)
            .background(backgroundColor),
        horizontalArrangement = Arrangement.SpaceEvenly,
        verticalAlignment = Alignment.CenterVertically
    ) {
        Image(
            painter = painterResource(id = imageResource),
            contentDescription = "back",
            modifier = Modifier
                .size(46.dp)
                .clickable { navController.popBackStack() }
        )

        Text(
            modifier = Modifier.fillMaxWidth(0.75f),
                text = title,
                fontSize = 16.sp,
                fontWeight = FontWeight.SemiBold,
                color = if (backgroundColor == Color.White) Brand900 else Color.White,
                textAlign = TextAlign.Center
        )

        Text(
            text = "test",
            fontSize = 16.sp,
            fontWeight = FontWeight.SemiBold,
            color = Color.Transparent,
        )
    }
}
@Composable
fun ProductStats(
    rating: Int, stars:Float, reviews: Int
){
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
                text = "$rating",
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
                text = "$stars/5.0",
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
                text = "$reviews",
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
}


@Composable
fun SentimentAnalysis() {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .clip(shape = RoundedCornerShape(20.dp))
            .background(Color.White)
            .padding(16.dp),
        verticalArrangement = Arrangement.spacedBy(8.dp)
    ) {
        SentimentItems(
        percentage = 90, title = "Packaging", content = "90% pembeli puas dengan pekejing"
        )
        SentimentItems(
            percentage = 70, title = "fungsi", content = "70% pembeli puas dengan fungsi barang"
        )
        SentimentItems(
            percentage = 80, title = "gatau", content = "80% pembeli gatau ini barang apa"
        )
    }
}

@Composable
fun SentimentItems(
    percentage: Int,
    title: String,
    content: String,
) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .clip(shape = RoundedCornerShape(20.dp))
            .background(Color.White)
            .padding(8.dp),
        horizontalArrangement = Arrangement.spacedBy(8.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {

        CircleWithNumber(number = percentage)

        Column(
            modifier = Modifier.fillMaxWidth(),
            verticalArrangement = Arrangement.spacedBy(4.dp)
        ) {
            Text(
                text = title,
                color = Color.Black,
                fontSize = 12.sp,
                fontWeight = FontWeight.Normal,
                textAlign = TextAlign.Start
            )
            Text(
                text = content,
                color = Color.Black,
                fontSize = 14.sp,
                fontWeight = FontWeight.Normal,
                textAlign = TextAlign.Start
            )
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
    ) {
        Text(
            text = "$number%",
            color = Color.Black,
            fontSize = 22.sp,
            fontWeight = FontWeight.Normal,
            textAlign = TextAlign.Center
        )
    }
}

@Preview(showBackground = true)
@Composable
fun DetailAnalysisPreview() {
    UlaScanTheme {
        DetailAnalysisScreen()
    }
}