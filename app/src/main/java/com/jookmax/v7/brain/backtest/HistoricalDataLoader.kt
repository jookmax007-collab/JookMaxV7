package com.jookmax.v7.brain.backtest


import com.jookmax.v7.core.model.MarketCandle

import javax.inject.Inject
import javax.inject.Singleton



/**
 * Historical Market Data Loader
 *
 * Phase 10 - Backtesting Engine
 *
 * Responsibility:
 *
 * Historical Source
 *        |
 *        v
 * List<MarketCandle>
 *        |
 *        v
 * BacktestRunner
 *
 *
 * Future implementations:
 *
 * - CSV Loader
 * - Database Loader
 * - Historical API Loader
 *
 */
@Singleton
class HistoricalDataLoader @Inject constructor() {



    /**
     * Load historical candles
     *
     * Phase 10 foundation:
     * Empty implementation.
     *
     * Real data source will be connected later.
     */
    fun load(): List<MarketCandle> {


        return emptyList()


    }



}