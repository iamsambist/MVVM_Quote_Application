package com.example.quotes.model.repo

import android.content.Context
import com.example.quotes.model.datasource.Quote
import com.google.gson.Gson

// The work of Repo is to provide the data to the view
class QuoteManager {
    private var quoteList = emptyArray<Quote>()
    private var currentQuoteIndex = 0

    fun getQuotesFromAssets(context: Context, fileName: String) {
        val inputStream = context.assets.open(fileName)
        val size: Int = inputStream.available()
        val buffer = ByteArray(size)
        inputStream.read(buffer)
        inputStream.close()

        val json = String(buffer, Charsets.UTF_8)
        val gson = Gson()
        quoteList = gson.fromJson(json, Array<Quote>::class.java)
    }

    fun getNextQuote(): Quote {
        if (currentQuoteIndex == quoteList.size - 1) return quoteList[currentQuoteIndex]
        return quoteList[++currentQuoteIndex]
    }

    fun getPreviousQuote(): Quote {
        if (currentQuoteIndex == 0) return quoteList[currentQuoteIndex]
        return quoteList[--currentQuoteIndex]
    }
    fun getListSize() : Int{
        return quoteList.size
    }
    fun getQuoteAtIndex(index : Int ) : Quote{
        if(index > 0 && index < quoteList.size){
            currentQuoteIndex = index
            return quoteList[currentQuoteIndex]
        }
        return quoteList[currentQuoteIndex]
    }
    fun getCurrentIndex() : Int{
        return currentQuoteIndex
    }
}