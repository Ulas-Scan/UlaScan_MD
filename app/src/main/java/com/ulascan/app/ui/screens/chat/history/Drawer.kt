package com.ulascan.app.ui.screens.chat.history

import android.util.Log
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.OutlinedTextFieldDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.ulascan.app.R
import com.ulascan.app.ui.theme.Brand900
import com.ulascan.app.ui.theme.Keyboard
import com.ulascan.app.ui.theme.UlaScanTheme
import io.eyram.iconsax.IconSax

@Composable
fun Drawer(
    isLoggedIn: Boolean = false,
    onLoginDrawerNavigation: () -> Unit,
    onRegisterDrawerNavigation: () -> Unit,
    modifier: Modifier = Modifier,
) {
    Column(
        modifier = modifier
            .fillMaxHeight()
            .fillMaxWidth(fraction = 0.8f),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Column(
            modifier = Modifier,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            if (isLoggedIn) {
                Box(
                    modifier = Modifier
                        .padding(horizontal = 12.dp, vertical = 24.dp)
                        .shadow(elevation = 3.dp, shape = RoundedCornerShape(36.dp))
                        .clip(RoundedCornerShape(30.dp))
                        .background(Color.White)
                ) {
                    OutlinedTextField(
                        value = "",
                        onValueChange = {},
                        placeholder = {
                            Text(
                                text = stringResource(id = R.string.search),
                                style = MaterialTheme.typography.labelMedium,
                                color = Keyboard,
                            )
                        },
                        leadingIcon = {
                            Image(
                                painter = painterResource(IconSax.Linear.SearchNormal),
                                contentDescription = stringResource(id = R.string.search),
                                modifier = Modifier.size(24.dp)
                            )
                        },
                        colors = OutlinedTextFieldDefaults.colors(
                            unfocusedBorderColor = Color.Transparent,
                            focusedBorderColor = Color.Transparent,
                            focusedTextColor = Color.Transparent,
                            focusedLabelColor = Color.Transparent,
                            focusedContainerColor = MaterialTheme.colorScheme.background,
                            unfocusedContainerColor = MaterialTheme.colorScheme.background
                        )
                    )
                }
            } else {
                Spacer(Modifier.weight(1f))
                Column(
                    modifier = Modifier
                        .fillMaxWidth(),
                    verticalArrangement = Arrangement.spacedBy(8.dp),
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    Text(
                        text = stringResource(R.string.sign_in_or_create_an_account),
                        style = MaterialTheme.typography.titleMedium,
                        color = Color.Black,
                        fontWeight = FontWeight.ExtraBold
                    )
                    Text(
                        text = stringResource(id = R.string.auth_description),
                        style = MaterialTheme.typography.labelMedium,
                        color = Keyboard
                    )
                    Spacer(modifier = Modifier.height(6.dp))
                    Column (
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(horizontal = 24.dp),
                        verticalArrangement = Arrangement.spacedBy(2.dp),
                        horizontalAlignment = Alignment.CenterHorizontally
                    ) {
                        Button(
                            onClick = { onLoginDrawerNavigation() },
                            modifier =  Modifier
                                .fillMaxWidth(0.7f),
                            shape = RoundedCornerShape(6.dp),
                            colors = ButtonDefaults.buttonColors(
                                containerColor = Brand900,
                            )
                        ) {
                            Text(
                                text = stringResource(id = R.string.login_hint),
                                style = MaterialTheme.typography.labelMedium,
                                color = Color.White,
                                fontWeight = FontWeight.Normal
                            )
                        }
                        OutlinedButton(
                            onClick = { onRegisterDrawerNavigation() },
                            shape = RoundedCornerShape(6.dp),
                            modifier =  Modifier
                                .fillMaxWidth(0.7f),
                            border = BorderStroke(
                                width = 2.dp,
                                color = Brand900
                            )
                        ) {
                            Text(
                                text = stringResource(id = R.string.register_hint),
                                style = MaterialTheme.typography.labelMedium,
                                color = Color.Black,
                                fontWeight = FontWeight.Normal
                            )
                        }
                    }
                }
            }
            Spacer(Modifier.weight(1f))
            HorizontalDivider(thickness = 0.5.dp, color = Keyboard)
            Image(
                painter = painterResource(id = R.drawable.logo_item_long),
                contentDescription = stringResource(id = R.string.app_name),
                modifier = Modifier
                    .size(90.dp)
            )
        }
    }
}

@Composable
@Preview(showBackground = true)
fun DrawerPreview() {
    val historyItems = listOf(
        HistoryItem(
            id = "chat-ebs123",
            title = "Example 1"
        ),
        HistoryItem(
            id = "chat-ebs321",
            title = "Example 2"
        ),
        HistoryItem(
            id = "chat-ebs213",
            title = "Example 3"
        ),
    )
    UlaScanTheme {
        Drawer(
            onLoginDrawerNavigation = { Log.d("ChatScreen", "Navigate to login screen") },
            onRegisterDrawerNavigation = { Log.d("ChatScreen", "Navigate to register screen") },
            modifier = Modifier.fillMaxWidth()
        )
    }
}