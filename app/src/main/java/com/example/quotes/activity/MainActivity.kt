package com.example.quotes.activity

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import com.example.quotes.model.repo.QuoteManager
import com.example.quotes.ui.theme.QuotesTheme
import com.example.quotes.ui.view.ScreenMainView
import com.example.quotes.ui.viewmodel.ViewModelMain

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        val data = QuoteManager()
        data.getQuotesFromAssets(this, "quotes.json")
        val mainViewModel = ViewModelMain(data)
        setContent {
            QuotesTheme {
                ScreenMainView(mainViewModel)
            }
        }
    }
}
