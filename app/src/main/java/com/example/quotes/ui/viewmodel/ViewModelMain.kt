package com.example.quotes.ui.viewmodel

import androidx.lifecycle.ViewModel
import com.example.quotes.model.datasource.Quote
import com.example.quotes.model.repo.QuoteManager
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow

data class MainScreenUIState(
    val quote: Quote = Quote("", ""),
    val quoteIndex: Float = 0f
)

class ViewModelMain(
    private val data: QuoteManager
) : ViewModel() {
    private val _mainUIState = MutableStateFlow(MainScreenUIState())
    val mainUIState: StateFlow<MainScreenUIState> = _mainUIState.asStateFlow()

    init {
        _mainUIState.value = _mainUIState.value.copy(
            quote = data.getPreviousQuote(),
            quoteIndex = data.getCurrentIndex().toFloat()
        )
    }

    fun getNextQuote() {
        if (data.getCurrentIndex() < data.getListSize() - 1) {
            _mainUIState.value = _mainUIState.value.copy(
                quote = data.getNextQuote(),
                quoteIndex = (data.getCurrentIndex() ).toFloat()
            )
        }
    }

    fun getPreviousQuote() {
        if (data.getCurrentIndex() > 0) {
            _mainUIState.value = _mainUIState.value.copy(
                quote = data.getPreviousQuote(),
                quoteIndex = (data.getCurrentIndex().toFloat())
            )
        }

    }

    fun getDataSize(): Int {
        return data.getListSize()
    }

    fun upDateCurrentIndex(sliderPosition: Float) {
        val sliderPositionInt = sliderPosition.toInt()
        _mainUIState.value = _mainUIState.value.copy(
            quote = data.getQuoteAtIndex(sliderPositionInt),
            quoteIndex = sliderPositionInt.toFloat()
        )

    }
}