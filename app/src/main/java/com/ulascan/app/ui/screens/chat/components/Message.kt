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
fun ResponseMessage(data: AnalysisData) {
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
                shopName = data.shopName as String,
                productName = data.productName as String,
                productDescription = data.productDescription as String)
            AnalysisSummary(data.summary as String)
          }
        Spacer(modifier = Modifier.height(10.dp))
        Button(
            onClick = { /*TODO*/ },
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
  val instance =
      AnalysisData(
          productName = "Tropicana Slim Cafe Latte (10 sch) - Kopi Bebas Gula",
          productDescription = "Tropicana Slim Cafe Latte adalah paduan kopi susu yang sempurna dengan rasa manis yang bisa dinikmati tanpa rasa khawatir karena diformulasikan tanpa penambahan gula pasir sehingga aman untuk diabetesi dan cocok untuk diet. \\nSemangati harimu dengan secangkir Tropicana Slim Cafe Latte!\\n\\nMengapa Tropicana Slim Cafe Latte?\\n- Rasa kopi susu yang nikmat\\n- Tanpa penambahan gula pasir \\n- Aman untuk diabetesi dan cocok untuk diet\\n- Lebih rendah kafein, aman untuk ibu hamil dan menyusui.\\n\\nCara Penyajian dan Petunjuk Konsumsi :\\nLarutkan 1 sachet Tropicana Slim Cafe Latte dalam 200 ml air hangat.  Aduk merata, dan secangkir kopi hangat yang nikmat lezat pun siap Anda nikmati.\\n\\nNutrimart merekomendasikan Tropicana Slim Cafe Latte untuk kamu yang:\\n - Mencari KOPI NIKMAT ala CAFE, TANPA GULA PASIR\\n - Memulai POLA HIDUP SEHAT dengan membatasi asupan gula\\n - MenJAGA KADAR GULA DARAH\\n - Memiliki DIABETES\\n - Menjalankan DIET\\n - Mementingkan KREDIBILITAS produk dengan pengalaman hampir 50 TAHUN",
          rating = 100,
          ulasan = 93,
          bintang = 4.91,
          imageUrls =
              listOf(
                  "https://images.tokopedia.net/img/cache/700/VqbcmM/2023/7/26/e799c724-ed29-43dc-b723-2854421553f3.jpg",
                  "https://images.tokopedia.net/img/cache/700/VqbcmM/2022/6/24/aef9ea0c-0e01-45f3-bc0f-8bc5b03dc581.jpg",
                  "https://images.tokopedia.net/img/cache/700/VqbcmM/2022/6/24/26e86f2d-373b-46cb-bda9-1ab4bd427907.jpg",
                  "https://images.tokopedia.net/img/cache/700/VqbcmM/2022/6/24/002e056c-cd07-4fbc-aefe-1f9a1d9ea739.jpg",
                  "https://images.tokopedia.net/img/cache/700/VqbcmM/2022/6/24/07280c5d-b3de-4818-87be-440355233f6c.jpg"),
          shopName = "NutriMart",
          countNegative = 48,
          countPositive = 45,
          packaging = 84.62,
          delivery = 90,
          adminResponse = 100,
          productCondition = 92.59,
          summary =
              "Secara keseluruhan, produk Tropicana Slim Cafe Latte mendapatkan ulasan yang positif. Pengguna menyukai rasanya yang enak, meskipun beberapa menganggapnya terlalu manis. Pengiriman cepat dan pengemasan aman diapresiasi oleh banyak pengguna. Produk ini juga dipuji karena rendah kalori dan bebas gula, menjadikannya pilihan yang lebih sehat. Beberapa pengguna menyebutkan bahwa mereka telah menjadi pelanggan tetap dan akan membeli lagi.")
  UlaScanTheme { ResponseMessage(instance) }
}
