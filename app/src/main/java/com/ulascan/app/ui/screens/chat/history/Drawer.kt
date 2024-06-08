package com.ulascan.app.ui.screens.chat.history

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Divider
import androidx.compose.material3.MaterialTheme
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
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.ulascan.app.R
import com.ulascan.app.ui.theme.Keyboard
import com.ulascan.app.ui.theme.UlaScanTheme
import io.eyram.iconsax.IconSax

@Composable
fun Drawer(
    selectedNavigationItem: HistoryItem,
    onNavigationItemClick: (HistoryItem) -> Unit,
    onCloseClick: () -> Unit,
    modifier: Modifier = Modifier
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
            Box(
                modifier = modifier
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
                            modifier = modifier.size(24.dp)
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
            Spacer(modifier.weight(1f))
            Divider(color = Keyboard, thickness = 0.5.dp)
            Image(
                painter = painterResource(id = R.drawable.logo_item_long),
                contentDescription = stringResource(id = R.string.app_name),
                modifier = modifier
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
            selectedNavigationItem = historyItems.first(),
            onNavigationItemClick = {},
            onCloseClick = {}
        )
    }
}