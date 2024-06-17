package com.ulascan.app.ui.screens.chat.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.ulascan.app.ui.theme.D1Chat
import com.ulascan.app.ui.theme.UlaScanTheme

@Composable
fun Guide(text: String, modifier: Modifier = Modifier) {
  Box(
      modifier =
          Modifier.clip(RoundedCornerShape(30)).background(Color.White).padding(15.dp, 14.dp)) {
        Text(
            text = text,
            textAlign = TextAlign.Center,
            style = MaterialTheme.typography.labelMedium,
            color = D1Chat)
      }
}

@Composable
@Preview(showBackground = true)
fun GuidePreview() {
  UlaScanTheme {
    Guide("Nam et dictum erat. Nunc pulvinar pretium dapibus. Cras tempus maximus tortor.")
  }
}
