package com.ulascan.app.ui.screens.chat.history

import androidx.compose.foundation.clickable
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.ulascan.app.ui.theme.Brand600
import com.ulascan.app.ui.theme.Neutral900
import com.ulascan.app.ui.theme.UlaScanTheme

@Composable
fun HistoryItemView(
    item: HistoryItem,
    selected: Boolean,
    onClick: () -> Unit
) {
    Text(
        text = item.title,
        style = MaterialTheme.typography.titleSmall,
        color = if (selected) Brand600 else Neutral900,
        modifier = Modifier
            .clickable { onClick() }
    )
}

@Composable
@Preview
fun HistoryItemViewPreview() {
    val navigationItem = HistoryItem(
        id = "chat-ebs123",
        title = "Preview"
    )
    UlaScanTheme {
        HistoryItemView(
            item = navigationItem,
            selected = false,
            onClick = {}
        )
    }
}