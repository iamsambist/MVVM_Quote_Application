package com.example.quotes.model.datasource

import kotlinx.coroutines.flow.MutableStateFlow

// the work of data Source is to hold or store the data required for the Application
data class Quote (val quote : String,
    val author : String)