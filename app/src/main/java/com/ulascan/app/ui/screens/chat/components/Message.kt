package com.ulascan.app.ui.screens.chat.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.ulascan.app.R
import com.ulascan.app.data.remote.response.AnalysisData
import com.ulascan.app.ui.theme.Brand100
import com.ulascan.app.ui.theme.Brand900
import com.ulascan.app.ui.theme.UlaScanTheme
import com.ulascan.app.utils.Helper

@Composable
fun UserMessage(text: String) {
  Column(verticalArrangement = Arrangement.spacedBy(8.dp)) {
    Text(
        text = stringResource(id = R.string.username),
        style = MaterialTheme.typography.titleSmall,
        color = Color.Black,
        fontWeight = FontWeight.Bold)
    Text(
        text = text,
        style = MaterialTheme.typography.labelMedium,
        color = Color.Black,
        fontWeight = FontWeight.Normal)
  }
}

@Composable
fun ProductDescription(productName: String, shopName: String, productDescription: String) {
  Column(
      modifier =
      Modifier
          .fillMaxWidth()
          .clip(RoundedCornerShape(6.dp))
          .background(Brand100)
          .padding(16.dp),
  ) {
    Text(
        text = productName,
        color = Color.Black,
        style = MaterialTheme.typography.titleSmall,
        fontWeight = FontWeight.Bold,
    )
    Spacer(modifier = Modifier.height(12.dp))
    Row(
        horizontalArrangement = Arrangement.spacedBy(4.dp),
        verticalAlignment = Alignment.CenterVertically) {
          Image(
              painter = painterResource(id = R.drawable.logo_item),
              contentDescription = stringResource(id = R.string.app_name),
              modifier = Modifier.size(30.dp))
          Text(
              text = shopName,
              color = Color.Black,
              style = MaterialTheme.typography.labelMedium,
              fontWeight = FontWeight.Normal,
          )
        }
    Spacer(modifier = Modifier.height(8.dp))
    Text(
        text = productDescription,
        color = Color.Black,
        style = MaterialTheme.typography.labelMedium,
        fontWeight = FontWeight.Normal,
    )
  }
}

@Composable
fun AnalysisSummary(summary: String) {
  Column(
      modifier =
      Modifier
          .fillMaxWidth()
          .clip(RoundedCornerShape(6.dp))
          .background(Brand100)
          .padding(16.dp)) {
        Text(
            text = stringResource(id = R.string.analysis_summary),
            color = Color.Black,
            style = MaterialTheme.typography.labelSmall,
            fontWeight = FontWeight.ExtraBold,
        )
        Spacer(modifier = Modifier.height(10.dp))
        Text(
            text = summary,
            color = Color.Black,
            style = MaterialTheme.typography.labelMedium,
            fontWeight = FontWeight.Normal,
        )
      }
}

@Composable
fun ResponseMessage(
    data: AnalysisData,
    onAnalyzeRouteNavigation: (AnalysisData) -> Unit
) {
  Column(
      modifier = Modifier.fillMaxHeight(),
      verticalArrangement = Arrangement.spacedBy(8.dp)
  ) {
    Image(
        painter = painterResource(id = R.drawable.logo_item_long),
        contentDescription = stringResource(id = R.string.app_name),
        modifier = Modifier
            .width(80.dp)
            .height(25.dp))
    Column(
        modifier =
        Modifier
            .fillMaxWidth()
            .clip(RoundedCornerShape(8.dp))
            .background(Color.White)
            .padding(15.dp)
    ) {
          Column(
              verticalArrangement = Arrangement.spacedBy(12.dp),
          ) {
            Text(
                text = stringResource(id = R.string.product_review),
                color = Color.Black,
                style = MaterialTheme.typography.titleSmall,
                fontWeight = FontWeight.Bold,
            )
            ProductDescription(
                shopName = data.shopName,
                productName = data.productName,
                productDescription = data.productDescription)
            AnalysisSummary(data.summary)
          }
        Spacer(modifier = Modifier.height(10.dp))
        Button(
            onClick = { 
                onAnalyzeRouteNavigation(data)
            },
            modifier = Modifier
                .fillMaxWidth(),
            shape = RoundedCornerShape(6.dp),
            colors = ButtonDefaults.buttonColors(
                containerColor = Brand900,
            )
        ) {
            Text(
                text = stringResource(id = R.string.sentiment_analysis),
                style = MaterialTheme.typography.labelMedium,
                color = Color.White,
                fontWeight = FontWeight.Normal,
            )
        }
    }
  }
}

@Composable
@Preview
fun UserMessagePreview() {
  UlaScanTheme {
    UserMessage(
        "https://www.tokopedia.com/nutrimartid/tropicana-slim-cafe-latte-10-sch-kopi-bebas-gula?src=topads")
  }
}

@Composable
@Preview
fun ResponseMessagePreview() {
  val instance = Helper.generateAnalysisData()
  UlaScanTheme { ResponseMessage(data = instance, onAnalyzeRouteNavigation = { } ) }
}
