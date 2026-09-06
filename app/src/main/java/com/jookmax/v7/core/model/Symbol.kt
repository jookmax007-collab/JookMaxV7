package com.jookmax.v7.core.model


/**
 * Represents a tradable market symbol.
 * Example: XAUUSD, EURUSD
 */
data class Symbol(

    val code: String,

    val description: String? = null

)