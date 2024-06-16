package com.ulascan.app.ui.screens.auth.login

import android.widget.Toast
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
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
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.ulascan.app.NavigationItem
import com.ulascan.app.R
import com.ulascan.app.ui.screens.auth.FormInput
import com.ulascan.app.ui.screens.auth.register.LoginUiState
import com.ulascan.app.ui.screens.auth.register.LoginViewModel
import com.ulascan.app.ui.theme.Brand900
import com.ulascan.app.ui.theme.UlaScanTheme
import com.ulascan.app.ui.theme.Weak100
import com.ulascan.app.utils.toCapitalize
import io.github.muddz.styleabletoast.StyleableToast


@Composable
fun LoginScreen(
    viewModel: LoginViewModel = viewModel(), navController: NavController = rememberNavController()
) {
    var password by remember { mutableStateOf("") }
    var email by remember { mutableStateOf("") }
    val uiState by viewModel.uiState.collectAsState()

    val context = LocalContext.current
    
    LaunchedEffect(uiState) {
        if (uiState is LoginUiState.Success) {
            email = ""
            password = ""
            navController.navigate(NavigationItem.Chat.route) {
                popUpTo(NavigationItem.Chat.route) { inclusive = true }
            }
        } else if (uiState is LoginUiState.Error) {
            StyleableToast.makeText(
                context, 
                (uiState as LoginUiState.Error).message.toCapitalize(), 
                Toast.LENGTH_SHORT, 
                R.style.toastError
            ).show()
        }
    }

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
                painter = painterResource(id = R.drawable.logo_ulascan),
                contentDescription = stringResource(id = R.string.app_name),
                modifier = Modifier
                    .size(120.dp)
            )
            Spacer(modifier = Modifier.height(8.dp))

            Text(
                text = stringResource(id = R.string.login_text),
                style = MaterialTheme.typography.titleLarge.copy(
                    color = Color.White,
                )
            )
            Spacer(modifier = Modifier.height(8.dp))
            Text(
                text = stringResource(id = R.string.login_guides),
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


                    FormInput(
                        title = stringResource(id = R.string.email),
                        value = email,
                        onValueChange = { email = it },
                        label = stringResource(id = R.string.email_guides)
                    )

                    FormInput(
                        title = stringResource(id = R.string.password),
                        value = password,
                        onValueChange = { password = it },
                        label = stringResource(id = R.string.password_guides),
                        isPassword = true
                    )

                    Spacer(modifier = Modifier.height(8.dp))

                    Button(onClick = {
                        viewModel.loginUser(email, password)
                    },
                        modifier = Modifier
                            .fillMaxWidth()
                            .height(48.dp),
                        colors = ButtonDefaults.buttonColors(containerColor = Brand900),
                            ) {
                        Text(text = stringResource(id = R.string.enter))
                    }

                    Row(
                        modifier = Modifier.fillMaxWidth(),

                        horizontalArrangement = Arrangement.Center
                    ) {
                        Text(text = stringResource(id = R.string.sign_in_message))
                        Text(text = stringResource(id = R.string.register_hint), fontWeight = FontWeight.Bold, modifier = Modifier.clickable { navController.navigate(NavigationItem.Register.route)})
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

