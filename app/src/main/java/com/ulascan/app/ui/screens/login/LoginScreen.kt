package com.ulascan.app.ui.screens.login

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.OutlinedTextFieldDefaults
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.ulascan.app.R
import com.ulascan.app.ui.theme.Brand900
import com.ulascan.app.ui.theme.UlaScanTheme
import com.ulascan.app.ui.theme.Weak100

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun LoginScreen() {
    Box(
        modifier = Modifier
            .fillMaxSize()
    ) {
        Column(
            modifier = Modifier
                .fillMaxHeight(0.65f)
                .fillMaxWidth()
                .background(Brand900)
                .align(Alignment.TopCenter)
        ) {
        }

        Column(
            modifier = Modifier
                .fillMaxSize(),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Image(
                painter = painterResource(id = R.drawable.logo_ulascan2),
                contentDescription = "Ulascan Logo",
                modifier = Modifier
                    .size(120.dp)
            )
            Spacer(modifier = Modifier.height(8.dp))

            Text(
                text = "Masuk Ke Akunmu!",
                style = MaterialTheme.typography.titleLarge.copy(
                    color = Color.White,
                )
            )
            Spacer(modifier = Modifier.height(8.dp))
            Text(
                text = "Masukkan email dan kata sandi untuk\n masuk ke akunmu",
                style = MaterialTheme.typography.labelMedium.copy(
                    color = Color.White,
                    textAlign = TextAlign.Center
                )
            )
            Spacer(modifier = Modifier.height(24.dp))

            Box(
                modifier = Modifier
                    .fillMaxWidth(0.85f)
                    .shadow(elevation = 8.dp, shape = RoundedCornerShape(20.dp))
                    .clip(RoundedCornerShape(16.dp))
                    .background(Weak100)
            ) {
                Column(
                    modifier = Modifier
                        .padding(24.dp),
                    verticalArrangement = Arrangement.spacedBy(12.dp)
                ) {

                    Text(text = "Email")
                    Box(
                        modifier = Modifier
                            .shadow(elevation = 8.dp, shape = RoundedCornerShape(36.dp))
                            .clip(RoundedCornerShape(36.dp))
                            .background(Weak100)
                    ) {
                        OutlinedTextField(
                            value = "",
                            onValueChange = {},
                            label = { Text(text = "Masukkan Email Anda") },
                            shape = RoundedCornerShape(30.dp),
                            modifier = Modifier
                                .fillMaxWidth()
                                .offset(y = (-4).dp),
                            colors = OutlinedTextFieldDefaults.colors(
                                unfocusedBorderColor = Color.Transparent,
                                focusedBorderColor = Color.Transparent,
                                focusedTextColor = Color.Transparent,
                                focusedLabelColor = Color.Transparent,
                            )
                        )
                    }


                    Text(text = "Kata Sandi")
                    Box(
                        modifier = Modifier
                            .shadow(elevation = 8.dp, shape = RoundedCornerShape(36.dp))
                            .clip(RoundedCornerShape(30.dp))
                            .background(Weak100)
                    ) {
                        OutlinedTextField(
                            value = "",
                            onValueChange = {},
                            label = { Text(text = "Masukkan Kata Sandi Anda") },
                            shape = RoundedCornerShape(30.dp),
                            modifier = Modifier
                                .fillMaxWidth()
                                .offset(y = (-4).dp),
                            colors = OutlinedTextFieldDefaults.colors(
                                unfocusedBorderColor = Color.Transparent,
                                focusedBorderColor = Color.Transparent,
                                focusedTextColor = Color.Transparent,
                                focusedLabelColor = Color.Transparent,
                            )
                        )
                    }

                    Spacer(modifier = Modifier.height(8.dp))

                    Row(
                        modifier = Modifier.fillMaxWidth(),

                        horizontalArrangement = Arrangement.Center
                    ) {
                        Text(text = "Belum Memiliki akun ? ")
                        Text(text = "Daftar", fontWeight = FontWeight.Bold )
                    }

                }
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun LoginPreview() {
    UlaScanTheme {
        LoginScreen()
    }
}
