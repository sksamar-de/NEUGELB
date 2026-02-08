package com.example.details_presentation

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.unit.dp
import androidx.hilt.lifecycle.viewmodel.compose.hiltViewModel
import com.example.common_utls.Assent
import com.example.common_utls.LocalNavHostController
import com.example.details_presentation.components.Details


@Composable
fun DetailScreen(
    viewModel: DetailViewModel = hiltViewModel()
) {
    val navHostController = LocalNavHostController.current
    val state = viewModel.state
    Scaffold {
        Box(
            modifier = Modifier
                .fillMaxSize()
                .padding(it),
            contentAlignment = Alignment.Center
        ) {
            if (state.isLoading) CircularProgressIndicator()
            if (state.error.trim().isNotEmpty()) Text(state.error)
            Column {
                Row(
                    modifier = Modifier
                        .padding(12.dp),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    IconButton(
                        onClick = {
                            navHostController.popBackStack()
                        }
                    ) {
                        Icon(
                            modifier = Modifier.testTag("back_icon"),
                            painter = painterResource(R.drawable.baseline_arrow_back_ios_24),
                            contentDescription = stringResource(R.string.nav_back_icon)
                        )
                    }
                    Spacer(modifier = Modifier.width(12.dp))

                    Text(
                        style = MaterialTheme.typography.headlineLarge.copy(fontWeight = FontWeight.Bold),
                        text = buildAnnotatedString {
                            append(stringResource(R.string.details))
                            withStyle(SpanStyle(color = Assent)) {
                                append(stringResource(R.string.dot))
                            }
                        }
                    )
                }
                state.detail?.let {
                    Details(it)
                }
            }

        }
    }
}
