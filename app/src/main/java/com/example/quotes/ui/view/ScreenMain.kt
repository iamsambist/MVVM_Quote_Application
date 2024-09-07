package com.example.quotes.ui.view

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentWidth
import androidx.compose.material3.Button
import androidx.compose.material3.Slider
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableFloatStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.quotes.model.datasource.Quote
import com.example.quotes.ui.theme.QuotesTheme
import com.example.quotes.ui.viewmodel.MainScreenUIState
import com.example.quotes.ui.viewmodel.ViewModelMain

@Composable
fun ScreenMainView(mainViewModel: ViewModelMain = viewModel()) {
    val mainUIState by mainViewModel.mainUIState.collectAsState()
    Box(
        modifier = Modifier.fillMaxSize(),
        contentAlignment = Alignment.Center
    ) {
        Column(
            modifier = Modifier
                .wrapContentWidth()
                .align(Alignment.Center)
                .shadow(8.dp)
        ) {

            StepSliderSample(mainViewModel, mainUIState)
            Spacer(modifier = Modifier.height(20.dp))
            QuoteBackground(mainUIState.quote)
            Spacer(modifier = Modifier.height(20.dp))
            Row(modifier = Modifier.padding(20.dp)) {
                Button(
                    onClick = { mainViewModel.getPreviousQuote() },
                    modifier = Modifier.weight(1f)
                ) {
                    Text("Previous")
                }

                Spacer(modifier = Modifier.width(16.dp)) // Add some space between the buttons

                // Right button
                Button(
                    onClick = { mainViewModel.getNextQuote() },
                    modifier = Modifier
                        .weight(1f)
                        .align(Alignment.CenterVertically)
                ) {
                    Text("Next")
                }
            }

        }

    }

}


@Composable
fun StepSliderSample(mainViewModel: ViewModelMain, mainUIState: MainScreenUIState) {
//    val sliderPosition by mainViewModel.quoteIndex.collectAsState()
    val totalListItems = mainViewModel.getDataSize().toFloat()
    var sp by remember { mutableFloatStateOf(mainUIState.quoteIndex) }

    LaunchedEffect(mainUIState.quoteIndex) {
        sp = mainUIState.quoteIndex
    }

    Column(modifier = Modifier.padding(20.dp)) {
        Text(text = sp.toInt().toString())

        Slider(
            value = sp,
            onValueChange = { sp = it },
            valueRange = 0f..totalListItems,
            onValueChangeFinished = {
                mainViewModel.upDateCurrentIndex(sp)
            })
    }

}

@Composable
fun QuoteBackground(quote: Quote) {
    Box(
        modifier = Modifier
            .padding(25.dp)
    ) {
        Column(
            modifier = Modifier
                .align(Alignment.CenterStart)
        ) {
            Text(
                text = quote.quote,
                style = TextStyle(fontSize = 20.sp)
            )
            Spacer(modifier = Modifier.height(10.dp))
            Text(
                text = quote.author,
                style = TextStyle(fontSize = 20.sp),
                modifier = Modifier.align(Alignment.End)
            )
        }

    }
}

@Preview(showBackground = true)
@Composable
fun ScreenMainViewPreview() {
    QuotesTheme {
        ScreenMainView()
    }
}