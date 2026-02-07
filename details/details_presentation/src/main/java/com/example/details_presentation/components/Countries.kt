package com.example.details_presentation.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.FlowRow
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.details_domain.model.Detail

@Composable
fun Countries(detail: Detail) {
    detail.production_countries?.firstOrNull()?.let {
        Text(
            text = buildAnnotatedString {
                append(if (detail.production_countries?.size == 1) "Country:" else "Countries:")
            },
            style = MaterialTheme.typography.bodySmall.copy(
                fontSize = 14.sp,
                fontWeight = FontWeight.Bold,
            )
        )
        Spacer(modifier = Modifier.height(8.dp))
    }

    FlowRow {
        detail.production_countries?.forEach { country ->
            Row(
                modifier = Modifier
                    .height(45.dp)
                    .padding(bottom = 4.dp, end = 4.dp)
                    .background(
                        brush = Brush.verticalGradient(
                            colors = listOf(
                                Color.Transparent,
                                MaterialTheme.colorScheme.onSurface
                            )
                        )
                    ),
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text(
                    modifier = Modifier.padding(horizontal = 8.dp, vertical = 4.dp),
                    text = country?.name ?: "N/A",
                    style = MaterialTheme.typography.titleSmall.copy(fontSize = 13.sp),
                    maxLines = 6,
                    overflow = TextOverflow.Ellipsis
                )
            }
        }
    }
}