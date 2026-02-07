package com.example.movies_presentation.component

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.unit.dp
import com.example.movies_presentation.R

val Assent = Color(0xFFFFC700)
@Composable
fun Header() {
    Column(
        modifier = Modifier.padding(start = 12.dp)
    ) {
        Spacer(modifier = Modifier.height(15.dp))
        Text(
            style = MaterialTheme.typography.headlineLarge.copy(fontWeight = FontWeight.Bold),
            text = buildAnnotatedString {
                append(stringResource(R.string.discover))
                withStyle(SpanStyle(color = Assent)){
                    append(stringResource(R.string.dot))
                }
            }
        )
        Spacer(modifier = Modifier.height(15.dp))
    }
}