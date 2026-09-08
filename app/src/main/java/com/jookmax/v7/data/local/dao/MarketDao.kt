package com.jookmax.v7.data.local.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query

import com.jookmax.v7.data.local.entity.MarketCandleEntity
import com.jookmax.v7.data.local.entity.MarketPriceEntity

import kotlinx.coroutines.flow.Flow


@Dao
interface MarketDao {


    @Insert(
        onConflict = OnConflictStrategy.REPLACE
    )
    suspend fun insertMarketPrice(
        entity: MarketPriceEntity
    )


    @Query(
        "SELECT * FROM market_price LIMIT 1"
    )
    suspend fun getMarketPrice(): MarketPriceEntity?


    @Query(
        "SELECT * FROM market_price LIMIT 1"
    )
    fun observeMarketPrice(): Flow<MarketPriceEntity?>


    @Insert(
        onConflict = OnConflictStrategy.REPLACE
    )
    suspend fun insertCandles(
        candles: List<MarketCandleEntity>
    )


    @Query(
        "SELECT * FROM market_candle ORDER BY timestamp DESC"
    )
    suspend fun getCandles(): List<MarketCandleEntity>


    @Query(
        "DELETE FROM market_price"
    )
    suspend fun clearPrice()


    @Query(
        "DELETE FROM market_candle"
    )
    suspend fun clearCandles()

}
