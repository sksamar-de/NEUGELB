package com.example.details_presentation.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.FlowRow
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil3.compose.AsyncImage
import com.example.common_utls.Constants
import com.example.details_domain.model.Detail

@Composable
fun Companies(detail: Detail) {
    detail.production_companies?.firstOrNull()?.let {
        Text(
            text = "Production:",
            style = MaterialTheme.typography.bodySmall.copy(
                fontSize = 14.sp,
                fontWeight = FontWeight.Bold,
                color = Color.White,
            )
        )
        Spacer(modifier = Modifier.height(8.dp))
    }

    FlowRow {
        detail.production_companies?.forEach { company ->
            Row(
                modifier = Modifier
                    .padding(bottom = 4.dp, end = 4.dp)
                    .background(
                        brush = Brush.verticalGradient(
                            colors = listOf(
                                Color.Transparent,
                                Color.Black
                            )
                        )
                    ),
                verticalAlignment = Alignment.CenterVertically
            ) {

                company?.logo_path?.let {
                    AsyncImage(
                        modifier = Modifier
                            .size(45.dp)
                            .padding(start = 8.dp, top = 4.dp, bottom = 4.dp),
                        model = Constants.COMPS_PATH + company.logo_path,
                        contentDescription = company.name
                    )
                }
                Spacer(modifier = Modifier.size( height = 45.dp, width = 4.dp))
                Text(
                    modifier = Modifier.padding(horizontal = 8.dp, vertical = 4.dp),
                    text = company?.name ?: "N/A",
                    style = MaterialTheme.typography.titleSmall.copy(fontSize = 13.sp, color = Color.White),
                    maxLines = 6,
                    overflow = TextOverflow.Ellipsis
                )
            }
        }
    }
}