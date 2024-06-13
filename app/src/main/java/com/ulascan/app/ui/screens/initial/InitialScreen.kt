package com.ulascan.app.ui.screens.initial

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.ulascan.app.NavigationItem
import com.ulascan.app.R
import com.ulascan.app.ui.theme.Brand100
import com.ulascan.app.ui.theme.Brand900
import com.ulascan.app.ui.theme.UlaScanTheme


@Composable
fun InitialScreen(navController: NavController = rememberNavController()) {
    Box(
        modifier = Modifier.fillMaxSize()
    ) {
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .fillMaxHeight(0.65f)
                .background(Brand100)
                .align(Alignment.TopCenter),
        ) {
            Column(
                modifier = Modifier.fillMaxSize(),
                verticalArrangement = Arrangement.Bottom
            ) {
                Image(
                    painter = painterResource(id = R.drawable.iphone_cut_hdpi),
                    contentDescription = "Ulascan Logo",
                    modifier = Modifier
                        .size(400.dp)
                )
            }
        }

        Box(
            modifier = Modifier
                .fillMaxWidth()
                .fillMaxHeight(0.35f)
                .align(Alignment.BottomCenter)
            ,
        ) {
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .fillMaxHeight()
                    .padding(24.dp),
                verticalArrangement = Arrangement.Center,

            ) {

                Text(text = "Temukan\nProduk Terbaikmu !",
                    style = MaterialTheme.typography.titleLarge.copy(
                        textAlign = TextAlign.Start,
                    )
                )

                Spacer(modifier = Modifier.height(16.dp))

                Text(text = "Lorem ipsum dolor sit amet, consectetur adipiscing elit. Praesent at malesuada arcu, a euismod ipsum. Maecenas pretium elit a accumsan feugiat.",
                        style = MaterialTheme.typography.labelMedium.copy(
                        textAlign = TextAlign.Start,
                            color = Color.Gray)
                )

                Spacer(modifier = Modifier.height(32.dp))

                Button(onClick = {
                    navController.navigate(NavigationItem.Register.route)
                },
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(64.dp),
                    colors = ButtonDefaults.buttonColors(containerColor = Brand900),
                ) {
                    Text(text = "Mulai Sekarang")
                }
            }

        }
    }
}


@Preview(showBackground = true)
@Composable
fun InitialPreview() {
    UlaScanTheme {
        InitialScreen()
    }
}
