import androidx.compose.foundation.Image
import androidx.compose.foundation.background
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
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
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
import com.ulascan.app.ui.screens.detail.NormalPieChart
import com.ulascan.app.ui.screens.detail.PieChart
import com.ulascan.app.ui.theme.Brand200
import com.ulascan.app.ui.theme.Brand600
import com.ulascan.app.ui.theme.Brand900
import com.ulascan.app.ui.theme.Neutral900
import com.ulascan.app.ui.theme.UlaScanTheme
import com.ulascan.app.utils.Helper
import com.ulascan.app.utils.castTo
import com.ulascan.app.utils.castToDoubleThenToInt

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun DetailScreen(navController: NavController = rememberNavController(),
                     data: AnalysisData
) {
    val scrollState = rememberScrollState()
    Scaffold(
        topBar = {
            TopAppBar(
                title = { 
                    Text(
                        text = data.productName,
                        fontSize = 16.sp,
                        fontWeight = FontWeight.SemiBold,
                        color = Color.White,
                        modifier = Modifier.fillMaxWidth(),
                        maxLines = 1,
                        overflow = TextOverflow.Ellipsis,
                        textAlign = TextAlign.Center,
                    )
                },
                navigationIcon = {
                    IconButton(onClick = { navController.popBackStack() }) {
                        Icon(
                            modifier = Modifier.size(46.dp),
                            painter = painterResource(id = R.drawable.ic_back),
                            contentDescription = "Back")
                    }
                },
                colors = TopAppBarDefaults.topAppBarColors(
                    containerColor = Brand600,
                    navigationIconContentColor = Color.White
                )
            )
        }
    ) {
        paddingValues ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .verticalScroll(scrollState)
                .padding(paddingValues)
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
            PieChart(
                data = mapOf(
                    stringResource(R.string.positive_review_key) to data.countPositive,
                    stringResource(R.string.negative_review_key) to data.countNegative
                )
            )

            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 16.dp)
                    .clip(shape = RoundedCornerShape(20.dp))
                    .background(Color.White)
            ) {
                Text(
                    text = stringResource(id = R.string.overview, ((data.countPositive.toDouble() / (data.countPositive + data.countNegative)) * 100).toInt()),
                    color = Brand900,
                    fontSize = 11.sp,
                    lineHeight = 14.sp,
                    letterSpacing = 0.5.sp,
                    fontWeight = FontWeight.ExtraBold,
                    textAlign = TextAlign.Center,
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(16.dp)
                )
            }
            Spacer(modifier = Modifier.height(12.dp))
            
            ProductStats(
                rating = data.rating,
                stars = data.bintang.castTo<Double>(),
                reviews = data.ulasan
            )

            Spacer(modifier = Modifier.height(12.dp))

            // Ringkasan Analisis
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 16.dp)
                    .clip(shape = RoundedCornerShape(20.dp))
            ) {
                Column(
                    modifier = Modifier
                        .fillMaxWidth()
                        .clip(shape = RoundedCornerShape(6.dp))
                        .background(Color.White)
                        .padding(16.dp),
                    verticalArrangement = Arrangement.spacedBy(8.dp)
                ) {
                    Text(
                        text = stringResource(R.string.analysis_summary),
                        color = Neutral900,
                        style = MaterialTheme.typography.labelSmall,
                        fontWeight = FontWeight.ExtraBold,
                        lineHeight = 14.sp,
                        textAlign = TextAlign.Start,
                        modifier = Modifier
                            .fillMaxWidth()
                    )
                    Text(
                        text = data.summary,
                        color = Neutral900,
                        style = MaterialTheme.typography.labelSmall,
                        fontSize = 10.sp,
                        lineHeight = 14.sp,
                        fontWeight = FontWeight.SemiBold,
                        textAlign = TextAlign.Justify,
                        modifier = Modifier.fillMaxWidth()
                    )
                }
            }

            Spacer(modifier = Modifier.height(12.dp))

            SentimentAnalysis(
                packaging = data.packaging.castToDoubleThenToInt(),
                delivery = data.delivery.castToDoubleThenToInt(),
                adminResponse = data.adminResponse.castToDoubleThenToInt()
            )

            Spacer(modifier = Modifier.height(12.dp))

            // Product Condition
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(start = 16.dp, end = 16.dp, bottom = 16.dp, top = 0.dp)
                    .clip(shape = RoundedCornerShape(20.dp))
            ) {


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
                        fontSize = 8.sp,
                        lineHeight = 14.sp,
                        letterSpacing = 0.15.sp,                        
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
                        NormalPieChart(
                            data = mapOf(
                                stringResource(R.string.negative_key) to (100 - data.productCondition.toInt()),
                                stringResource(R.string.positive_key) to data.productCondition.toInt()
                            )
                        )

                        Text(
                            text = stringResource(
                                id = R.string.product_condition_message,
                                data.productCondition.toInt()
                            ),
                            color = Color.Black,
                            fontSize = 10.sp,
                            lineHeight = 14.sp,
                            letterSpacing = 0.5.sp,
                            fontWeight = FontWeight.SemiBold,
                            textAlign = TextAlign.Justify,
                            modifier = Modifier.fillMaxWidth()
                        )
                    }
                }
            }
        }
    }
}

@Composable
fun SentimentAnalysis(packaging: Int, delivery: Int, adminResponse: Int) {
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 16.dp)
            .clip(shape = RoundedCornerShape(20.dp))
    ) {

        Column(
            modifier =
            Modifier
                .fillMaxWidth()
                .clip(shape = RoundedCornerShape(20.dp))
                .background(Color.White)
                .padding(horizontal = 15.dp, vertical = 16.dp),
            verticalArrangement = Arrangement.spacedBy(8.dp)
        ) {
            SentimentItems(
                percentage = packaging,
                title = stringResource(R.string.packaging),
                content = stringResource(id = R.string.packaging_satisfaction, packaging)
            )
            SentimentItems(
                percentage = delivery,
                title = stringResource(R.string.delivery),
                content = stringResource(id = R.string.delivery_satisfaction, delivery)
            )
            SentimentItems(
                percentage = adminResponse,
                title = stringResource(R.string.admin_response_text),
                content = stringResource(id = R.string.admin_response, adminResponse)
            )
        }
    }
}


@Composable
fun ProductStats(rating: Int, stars: Double, reviews: Int) {
    Row(modifier = Modifier
        .fillMaxWidth()
        .padding(horizontal = 16.dp), horizontalArrangement = Arrangement.spacedBy(16.dp)) {
        Column(
            modifier =
            Modifier
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
                modifier = Modifier.size(46.dp))
            Text(
                text = "$rating",
                color = Neutral900,
                style = MaterialTheme.typography.titleSmall,
                fontWeight = FontWeight.ExtraBold,
                textAlign = TextAlign.Center,
                modifier = Modifier.fillMaxWidth())
            Text(
                text = stringResource(id = R.string.rating),
                color = Neutral900,
                style = MaterialTheme.typography.labelMedium,
                fontWeight = FontWeight.Normal,
                textAlign = TextAlign.Center,
                modifier = Modifier.fillMaxWidth())
        }

        Column(
            modifier =
            Modifier
                .fillMaxWidth()
                .weight(1f)
                .clip(shape = RoundedCornerShape(20.dp))
                .background(Color.White)
                .padding(vertical = 12.dp, horizontal = 18.dp),
            verticalArrangement = Arrangement.spacedBy(6.dp),
            horizontalAlignment = Alignment.CenterHorizontally) {
            Image(
                painter = painterResource(id = R.drawable.ic_star),
                contentDescription = stringResource(R.string.star),
                modifier = Modifier.size(46.dp))
            Text(
                text = "${String.format("%.1f", (stars.toString().toDoubleOrNull() ?: 0.0 ))}/5.0",
                color = Neutral900,
                style = MaterialTheme.typography.titleSmall,
                fontWeight = FontWeight.ExtraBold,
                textAlign = TextAlign.Center,
                modifier = Modifier.fillMaxWidth())
            Text(
                text = stringResource(R.string.star),
                color = Neutral900,
                style = MaterialTheme.typography.labelMedium,
                fontWeight = FontWeight.Normal,
                textAlign = TextAlign.Center,
                modifier = Modifier.fillMaxWidth())
        }
        Column(
            modifier =
            Modifier
                .fillMaxWidth()
                .weight(1f)
                .clip(shape = RoundedCornerShape(20.dp))
                .background(Color.White)
                .padding(vertical = 12.dp, horizontal = 18.dp),
            verticalArrangement = Arrangement.spacedBy(6.dp),
            horizontalAlignment = Alignment.CenterHorizontally) {
            Image(
                painter = painterResource(id = R.drawable.ic_pen),
                contentDescription = stringResource(id = R.string.rating),
                modifier = Modifier.size(46.dp))
            Text(
                text = "$reviews",
                color = Neutral900,
                style = MaterialTheme.typography.titleSmall,
                fontWeight = FontWeight.ExtraBold,
                textAlign = TextAlign.Center,
                modifier = Modifier.fillMaxWidth())
            Text(
                text = stringResource(R.string.review),
                color = Neutral900,
                style = MaterialTheme.typography.labelMedium,
                fontWeight = FontWeight.Normal,
                textAlign = TextAlign.Center,
                modifier = Modifier.fillMaxWidth())
        }
    }
}
@Composable
fun SentimentItems(
    percentage: Int,
    title: String,
    content: String,
) {
    Row(
        modifier =
        Modifier
            .fillMaxWidth()
            .clip(shape = RoundedCornerShape(20.dp))
            .background(Color.White),
        horizontalArrangement = Arrangement.spacedBy(8.dp),
        verticalAlignment = Alignment.CenterVertically) {
        CircleWithNumber(number = percentage)

        Column(
            modifier = Modifier.fillMaxWidth()) {
            Text(
                text = title,
                color = Color.Black,
                fontSize = 8.sp,
                lineHeight = 14.sp,
                letterSpacing = 0.15.sp,
                fontWeight = FontWeight.Normal,
                textAlign = TextAlign.Start)
            Text(
                text = content,
                color = Color.Black,
                fontSize = 10.sp,
                lineHeight = 14.sp,
                letterSpacing = 0.5.sp,
                fontWeight = FontWeight.SemiBold,
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
            .size(42.dp)
            .background(color = Brand200, shape = CircleShape)) {
        Text(
            text = "$number%",
            color = Color.Black,
            style = MaterialTheme.typography.titleSmall,
            fontSize = 12.sp,
            fontWeight = FontWeight.SemiBold,
            textAlign = TextAlign.Center)
    }
}

@Preview(showBackground = true)
@Composable
fun ColumnWithTopBarPreview() {
    val data = Helper.generateAnalysisData()
    UlaScanTheme {
        DetailScreen(data = data)   
    }
}
