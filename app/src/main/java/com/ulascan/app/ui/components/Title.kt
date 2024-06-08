package com.ulascan.app.ui.components

import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.sp
import com.ulascan.app.ui.theme.Brand900
import com.ulascan.app.ui.theme.UlaScanTheme

@Composable
fun AppTitle(modifier: Modifier = Modifier) {
    val text = buildAnnotatedString {
        append("Ula")
        withStyle(style = SpanStyle(fontWeight = FontWeight.ExtraBold)) {
            append("Scan")
        }
    }
    Text(
        modifier = modifier,
        text = text,
        style = MaterialTheme.typography.titleLarge,
        fontWeight = FontWeight.Medium,
        textAlign = TextAlign.Center,
        fontSize = 26.sp,
        color = Brand900
    )
}

@Composable
@Preview
fun AppTitlePreview() {
    UlaScanTheme {
        AppTitle()
    }
}