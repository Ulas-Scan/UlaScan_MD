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
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.ulascan.app.R
import com.ulascan.app.data.remote.response.AnalysisData
import com.ulascan.app.ui.theme.Brand200
import com.ulascan.app.ui.theme.Brand900
import com.ulascan.app.ui.theme.Neutral900
import com.ulascan.app.ui.theme.UlaScanTheme
import com.ulascan.app.utils.Helper
import com.ulascan.app.utils.castTo
import com.ulascan.app.utils.castToDoubleThenToInt

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun DetailAnalysisScreen(navController: NavController = rememberNavController(), data: AnalysisData) {
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
                HeaderRow(navController, title = data.productName, backgroundColor)
            }
            items(1) {
                Column(modifier = Modifier.padding(horizontal = 16.dp)) {

                    PieChart(data = mapOf(stringResource(R.string.positive_review_key) to data.countPositive,
                        stringResource(
                            R.string.negative_review_key
                        ) to data.countNegative))

                    Box(
                        modifier = Modifier
                            .fillMaxWidth()
                            .clip(shape = RoundedCornerShape(20.dp))
                            .background(Color.White)
                    ) {
                        Text(
                            text = "${((data.countPositive.toDouble()/(data.countPositive + data.countNegative))*100).toInt()}% of buyers are satisfied \nwith purchasing the product overall",
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

                    ProductStats(rating = data.rating, stars = data.bintang.castTo<Double>(), reviews = data.ulasan)

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
                            text = stringResource(R.string.analysis_summary),
                            color = Neutral900,
                            fontSize = 16.sp,
                            fontWeight = FontWeight.Bold,
                            textAlign = TextAlign.Start,
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(bottom = 8.dp)
                        )
                        Text(
                            text = data.summary,
                            color = Neutral900,
                            fontSize = 12.sp,
                            fontWeight = FontWeight.Medium,
                            textAlign = TextAlign.Justify,
                            modifier = Modifier.fillMaxWidth()
                        )
                    }

                    Spacer(modifier = Modifier.size(16.dp))

                    SentimentAnalysis(packaging = data.packaging.castToDoubleThenToInt(), delivery = data.delivery.castToDoubleThenToInt(), adminResponse = data.adminResponse.castToDoubleThenToInt())

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
                            text = stringResource(R.string.product_condition),
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
                            NormalPieChart(data = mapOf(stringResource(R.string.negative_key) to (100-data.productCondition.toInt()),
                                stringResource(
                                    R.string.positive_key
                                ) to data.productCondition.toInt()))

                            Text(
                                text = stringResource(id = R.string.product_condition_message, data.productCondition.toInt()),
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
            contentDescription = stringResource(id = R.string.app_name),
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
                textAlign = TextAlign.Center,
                maxLines = 1,
                overflow = TextOverflow.Ellipsis
        )
    }
}
@Composable
fun ProductStats(
    rating: Int, stars:Double, reviews: Int
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
                contentDescription = stringResource(id = R.string.app_name),
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
                text = stringResource(id = R.string.rating),
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
                contentDescription = stringResource(R.string.star),
                modifier = Modifier.size(46.dp)
            )
            Text(
                text = "${String.format("%.1f", stars).toDouble()}/5.0",
                color = Neutral900,
                fontSize = 16.sp,
                fontWeight = FontWeight.Bold,
                textAlign = TextAlign.Center,
                modifier = Modifier
                    .fillMaxWidth()
            )
            Text(
                text = stringResource(R.string.star),
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
                contentDescription = stringResource(id = R.string.rating),
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
                text = stringResource(R.string.review),
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
fun SentimentAnalysis(packaging: Int, delivery: Int, adminResponse: Int) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .clip(shape = RoundedCornerShape(20.dp))
            .background(Color.White)
            .padding(16.dp),
        verticalArrangement = Arrangement.spacedBy(8.dp)
    ) {
        SentimentItems(
        percentage = packaging, title = stringResource(R.string.packaging), content = stringResource(id = R.string.packaging_satisfaction, packaging)
        )
        SentimentItems(
            percentage = delivery, title = stringResource(R.string.delivery), content = stringResource(id = R.string.delivery_satisfaction, delivery)
        )
        SentimentItems(
            percentage = adminResponse, title = stringResource(R.string.admin_response_text), content = stringResource(id = R.string.admin_response, adminResponse)
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
    val data = Helper.generateAnalysisData()
    UlaScanTheme {
        DetailAnalysisScreen(data = data)
    }
}