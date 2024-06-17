package com.ulascan.app.utils

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentWidth
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.ehsanmsz.mszprogressindicator.progressindicator.BallClipRotateProgressIndicator
import com.ulascan.app.R
import com.ulascan.app.ui.theme.Error600
import com.ulascan.app.ui.theme.UlaScanTheme

@Composable
fun ErrorMessage(message: String, modifier: Modifier = Modifier, onClickRetry: () -> Unit) {
  Row(
      modifier = modifier.padding(10.dp),
      horizontalArrangement = Arrangement.SpaceBetween,
      verticalAlignment = Alignment.CenterVertically,
  ) {
    Text(
        text = message,
        color = Error600,
        modifier = Modifier.weight(1f),
        maxLines = 2,
        style = MaterialTheme.typography.labelMedium,
        fontWeight = FontWeight.Bold)
    OutlinedButton(
        colors = ButtonDefaults.buttonColors(containerColor = Color.Transparent),
        border = BorderStroke(2.dp, Error600),
        onClick = onClickRetry,
    ) {
      Text(
          text = stringResource(id = R.string.retry),
          color = Error600,
      )
    }
  }
}

@Composable
fun LoadingNextPageItem(modifier: Modifier) {
  BallClipRotateProgressIndicator(
      modifier =
          modifier.fillMaxWidth().padding(10.dp).wrapContentWidth(Alignment.CenterHorizontally))
}

@Composable
@Preview
fun ErrorMessagePreview() {
  UlaScanTheme {
    ErrorMessage(message = "Failed to connect to /34.101.79.15.180", onClickRetry = {})
  }
}

@Composable
@Preview
fun LoadingNextPageItemPreview() {
  UlaScanTheme { LoadingNextPageItem(modifier = Modifier) }
}
