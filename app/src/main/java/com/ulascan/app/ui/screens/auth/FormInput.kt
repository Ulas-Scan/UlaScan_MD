package com.ulascan.app.ui.screens.auth

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.OutlinedTextFieldDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.ulascan.app.ui.theme.UlaScanTheme
import com.ulascan.app.ui.theme.Weak100

@Composable
fun FormInput(
    title: String,
    value: String,
    onValueChange: (String) -> Unit,
    label: String,
    isPassword: Boolean = false
) {
  Text(
      text = title,
      style = MaterialTheme.typography.labelMedium,
  )
  Box(
      modifier =
      Modifier
          .shadow(elevation = 8.dp, shape = RoundedCornerShape(36.dp))
          .clip(RoundedCornerShape(30.dp))
          .background(Weak100)) {
        OutlinedTextField(
            value = value,
            onValueChange = onValueChange,
            label = {
              if (value.isNotBlank()) {
                Text(
                    text = "",
                    style = MaterialTheme.typography.labelMedium,
                    )
              } else {
                Text(
                    text = label,
                    style = MaterialTheme.typography.labelMedium,
                )
              }
            },
            shape = RoundedCornerShape(30.dp),
            modifier = Modifier
                .fillMaxWidth()
                .offset(y = (-4).dp),
            colors =
                OutlinedTextFieldDefaults.colors(
                    unfocusedBorderColor = Color.Transparent,
                    focusedBorderColor = Color.Transparent,
                    focusedTextColor = Color.Black,
                    focusedLabelColor = Color.Transparent,
                    unfocusedSupportingTextColor = Color.Transparent),
            visualTransformation =
                if (isPassword) PasswordVisualTransformation() else VisualTransformation.None,
            keyboardOptions =
                KeyboardOptions(
                    keyboardType = if (isPassword) KeyboardType.Password else KeyboardType.Text,
                    imeAction = if (isPassword) ImeAction.Done else ImeAction.Next))
      }
}

@Composable
@Preview
fun FormInputPreview() {
    UlaScanTheme {
        FormInput(
            title = "Email",
            value = "",
            onValueChange = {},
            label = "Masukkan Email Anda",
            isPassword = false
        )
    }
}