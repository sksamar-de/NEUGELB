package com.example.details_presentation.components

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.FlowRow
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.common_utls.Constants
import com.example.details_domain.model.Detail
import com.example.details_presentation.GradientBackground
import com.example.details_presentation.R
import com.gowtham.ratingbar.RatingBar
import com.gowtham.ratingbar.RatingBarStyle
import java.text.DecimalFormat

val DetailColor = Color(0xFFFFC700)

@Composable
fun Details(detail: Detail) {
    Box(
        modifier = Modifier.fillMaxSize()
    ) {
        Box {
            AutoScrollingImages(
                modifier = Modifier.align(Alignment.TopCenter),
                items = listOf(
                    Constants.ORIGINAL_SIZE_PATH + detail.poster_path,
                    Constants.ORIGINAL_SIZE_PATH + detail.backdrop_path
                )
            )
            GradientBackground()
        }
        Column(
            modifier = Modifier
                .verticalScroll(rememberScrollState())
                .fillMaxHeight(.8f).padding(18.dp).align(Alignment.BottomStart)
        ) {
            Column {
                Text(
                    text = detail.title ?: "N/A",
                    style = MaterialTheme.typography.titleLarge.copy(
                        fontWeight = FontWeight.Bold,
                        fontSize = 30.sp,
                        color = DetailColor
                    )
                )

                Text(
                    text = detail.release_date ?: "N/A",
                    style = MaterialTheme.typography.titleLarge.copy(
                        fontWeight = FontWeight.Bold,
                        fontSize = 20.sp,
                        color = DetailColor
                    )
                )
            }

            Spacer(modifier = Modifier.height(8.dp))

            Text(
                text = buildAnnotatedString {
                    detail.runtime?.let {
                        append("Runtime: ")
                        withStyle(SpanStyle(color = Color.Green)){
                            append( " ${detail.runtime} Min")
                        }
                    }
                },
                style = MaterialTheme.typography.titleLarge.copy(
                    fontWeight = FontWeight.Bold,
                    fontSize = 14.sp,
                    color = DetailColor
                )
            )

            Spacer(modifier = Modifier.height(8.dp))


            Text(
                text = buildAnnotatedString {
                    append(stringResource(R.string.revenue))
                    withStyle(SpanStyle(color = Color.Green)){
                        append( " ${detail.revenue?:"N/A"}")
                    }
                },
                style = MaterialTheme.typography.titleLarge.copy(
                    fontWeight = FontWeight.Bold,
                    fontSize = 14.sp,
                    color = DetailColor
                )
            )

            Spacer(modifier = Modifier.height(8.dp))

            Text(
                text = buildAnnotatedString {
                    append(stringResource(R.string.budget))
                    withStyle(SpanStyle(color = Color.Red)){
                        append( " ${detail.budget?:"N/A"} ")
                    }
                },
                style = MaterialTheme.typography.titleLarge.copy(
                    fontWeight = FontWeight.Bold,
                    fontSize = 14.sp,
                    color = DetailColor
                )
            )

            Spacer(modifier = Modifier.height(8.dp))

            detail.tagline?.let {
                Text(
                    text = detail.tagline?:"",
                    style = MaterialTheme.typography.titleLarge.copy(
                        fontWeight = FontWeight.Bold,
                        fontSize = 22.sp,
                        color = Color.Red
                    )
                )

                Spacer(modifier = Modifier.height(8.dp))
            }

            Row(
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text(
                    text = DecimalFormat("#.##").format(detail.vote_average) ?: "N/A",
                    style = MaterialTheme.typography.titleMedium.copy(
                        fontSize = 22.sp,
                        color = DetailColor
                    )
                )
                Spacer(modifier = Modifier.width(4.dp))
                RatingBar(
                    value = detail.vote_average?.toFloat() ?: 0f,
                    style = RatingBarStyle.Fill(),
                    numOfStars = 10,
                    size = 20.dp,
                    spaceBetween = 4.dp,
                    onValueChange = {

                    },
                    onRatingChanged = {

                    }
                )
            }
            Spacer(modifier = Modifier.height(8.dp))
            FlowRow {
                detail.genres?.forEach { genre ->
                    Text(
                        text = buildAnnotatedString {
                            append(genre?.name ?: "Unknown")
                            if (detail.genres?.lastOrNull() != genre) {
                                append(", ")
                            }
                        },
                        style = MaterialTheme.typography.bodySmall.copy(
                            fontSize = 14.sp,
                            color = DetailColor
                        )
                    )
                }
            }

            Spacer(modifier = Modifier.height(8.dp))

            Text(
                text = detail.overview ?: "N/A",
                style = MaterialTheme.typography.titleSmall.copy(fontSize = 13.sp),
                color = DetailColor,
                maxLines = 6,
                overflow = TextOverflow.Ellipsis
            )

            Spacer(modifier = Modifier.height(8.dp))

            Companies(detail = detail)

            Countries(detail = detail)

            Languages(detail = detail)

        }
    }
}