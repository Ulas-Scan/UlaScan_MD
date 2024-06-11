package com.ulascan.app.ui.screens.auth.register

import android.content.Context
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
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
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.OutlinedTextFieldDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.ulascan.app.R
import com.ulascan.app.data.remote.UserPreferences
import com.ulascan.app.ui.ViewModelFactory
import com.ulascan.app.ui.theme.Brand900
import com.ulascan.app.ui.theme.UlaScanTheme
import com.ulascan.app.ui.theme.Weak100

@Composable
fun RegisterScreen(
    viewModel: RegisterViewModel = viewModel(), navController: NavController = rememberNavController()) {
    var name by remember { mutableStateOf("") }
    var email by remember { mutableStateOf("") }
    var password by remember { mutableStateOf("") }


//    val uiState by viewModel.uiState.collectAsState()

    Box(
        modifier = Modifier.fillMaxSize()
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
            modifier = Modifier.fillMaxSize(),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Image(
                painter = painterResource(id = R.drawable.logo_ulascan),
                contentDescription = "Ulascan Logo",
                modifier = Modifier.size(120.dp)
            )
            Spacer(modifier = Modifier.height(16.dp))

            Text(
                text = "Daftarkan Dirimu !",
                style = MaterialTheme.typography.titleLarge.copy(
                    color = Color.White,
                )
            )
            Spacer(modifier = Modifier.height(8.dp))
            Text(
                text = "Masukkan datamu untuk mendaftar\n pada aplikasi ini!",
                style = MaterialTheme.typography.labelMedium.copy(
                    color = Color.White,
                    textAlign = TextAlign.Center
                )
            )

            Spacer(modifier = Modifier.height(16.dp))

            Box(
                modifier = Modifier
                    .fillMaxWidth(0.9f)
                    .shadow(elevation = 8.dp, shape = RoundedCornerShape(16.dp))
                    .clip(RoundedCornerShape(16.dp))
                    .background(Weak100)
            ) {
                Column(
                    modifier = Modifier.padding(24.dp),
                    verticalArrangement = Arrangement.spacedBy(12.dp)
                ) {


                    Text(text = "Nama Lengkap")
                    Box(
                        modifier = Modifier
                            .shadow(elevation = 8.dp, shape = RoundedCornerShape(36.dp))
                            .clip(RoundedCornerShape(30.dp))
                            .background(Weak100)
                    ) {
                        OutlinedTextField(
                            value = name,
                            onValueChange = {name = it},
                            label = {
                                if (name.isNotBlank()) {
                                    Text(text = "")
                                } else {
                                    Text(text = "Masukkan Nama Anda")
                                }
                            },
                            shape = RoundedCornerShape(30.dp),
                            modifier = Modifier
                                .fillMaxWidth()
                                .offset(y = (-4).dp),
                            colors = OutlinedTextFieldDefaults.colors(
                                unfocusedBorderColor = Color.Transparent,
                                focusedBorderColor = Color.Transparent,
                                focusedTextColor = Color.Black,
                                focusedLabelColor = Color.Transparent,
                                unfocusedSupportingTextColor = Color.Transparent
                            )
                        )
                    }

                    Text(text = "Email")
                    Box(
                        modifier = Modifier
                            .shadow(elevation = 8.dp, shape = RoundedCornerShape(36.dp))
                            .clip(RoundedCornerShape(30.dp))
                            .background(Weak100)
                    ) {
                        OutlinedTextField(
                            value = email,
                            onValueChange = {email = it},
                            label = {
                                if (email.isNotBlank()) {
                                    Text(text = "")
                                } else {
                                    Text(text = "Masukkan Email Anda")
                                }
                            },
                            shape = RoundedCornerShape(30.dp),
                            modifier = Modifier
                                .fillMaxWidth()
                                .offset(y = (-4).dp),
                            colors = OutlinedTextFieldDefaults.colors(
                                unfocusedBorderColor = Color.Transparent,
                                focusedBorderColor = Color.Transparent,
                                focusedTextColor = Color.Black,
                                focusedLabelColor = Color.Transparent,
                                unfocusedSupportingTextColor = Color.Transparent
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
                            value = password,
                            onValueChange = {password = it},
                            label = {
                                if (password.isNotBlank()) {
                                    Text(text = "")
                                } else {
                                    Text(text = "Masukkan Kata Sandi Anda")
                                }
                            },
                            shape = RoundedCornerShape(30.dp),
                            modifier = Modifier
                                .fillMaxWidth()
                                .offset(y = (-4).dp),
                            colors = OutlinedTextFieldDefaults.colors(
                                unfocusedBorderColor = Color.Transparent,
                                focusedBorderColor = Color.Transparent,
                                focusedTextColor = Color.Black,
                                focusedLabelColor = Color.Transparent,
                                unfocusedSupportingTextColor = Color.Transparent
                            ),
                            visualTransformation = PasswordVisualTransformation(),
                            keyboardOptions = KeyboardOptions(
                                keyboardType = KeyboardType.Password,
                                imeAction = ImeAction.Done
                            )

                        )
                    }

                    Spacer(modifier = Modifier.height(8.dp))

                    Button(onClick = {
                        viewModel.registerUser(name, email, password)
                    },
                        modifier = Modifier
                            .fillMaxWidth()
                            .height(48.dp),
                        colors = ButtonDefaults.buttonColors(containerColor = Brand900),
                    ) {
                        Text(text = "Daftar")
                    }

                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.Center
                    ) {
                        Text(text = "Sudah Memiliki akun ? ")
                        Text(text = "Masuk", fontWeight = FontWeight.Bold,
                            modifier = Modifier.clickable { navController.navigate("login")})
                    }
                }
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun RegisterPreview() {
    UlaScanTheme {
        RegisterScreen(viewModel = RegisterViewModel(), navController = rememberNavController())
    }
}
