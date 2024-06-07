package com.ulascan.app.ui.screens.detailAnalysis


import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import com.ulascan.app.ui.theme.Brand900
import com.ulascan.app.ui.theme.UlaScanTheme

@Composable
fun DetailAnalysisScreen(){
    Box(
        modifier = Modifier.fillMaxSize()
    ) {
        Column(
            modifier = Modifier
                .fillMaxHeight()
                .fillMaxWidth()
                .background(
                    brush = Brush.verticalGradient(
                        colors = listOf(
                            Color(0xFF50AF6E), // 0% - #50AF6E
                            Color(0xFFAAC3B2), // 44% - #AAC3B2
                            Color(0xFFE1F4E7), // 74% - #E1F4E7
                            Color.White      // 100% - White
                        ),
                        startY = 0.0f,
                        endY = Float.POSITIVE_INFINITY
                    )
                )
                .align(Alignment.TopCenter)
        ) {
            // Empty Column to provide the background
        }

        Column(
            modifier = Modifier.fillMaxSize(),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            PieChart(
             data = mapOf(
            Pair("Sample-1", 80),
            Pair("Sample-2", 20),
                        )
                    )
            
        }
    }
}
@Preview(showBackground = true)
@Composable
fun DetailAnalysisPreview() {
    UlaScanTheme {
        DetailAnalysisScreen()
    }
}