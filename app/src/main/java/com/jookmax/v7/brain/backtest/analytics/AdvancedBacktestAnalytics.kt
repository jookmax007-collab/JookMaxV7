package com.jookmax.v7.brain.backtest.analytics

import com.jookmax.v7.brain.backtest.model.BacktestTrade
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class AdvancedBacktestAnalytics @Inject constructor() {

    fun generateReport(
        trades: List<BacktestTrade>
    ): PerformanceReport {

        val equityCurve = buildEquityCurve(trades)

        val statistics = calculateTradeStatistics(trades)

        val riskMetrics = calculateRiskMetrics(trades)

        return PerformanceReport(
            equityCurve = equityCurve,
            tradeStatistics = statistics,
            riskMetrics = riskMetrics
        )
    }

    private fun buildEquityCurve(
        trades: List<BacktestTrade>
    ): EquityCurve {

        var balance = 0.0

        val points = trades.map {

            balance += it.profitLoss

            EquityPoint(
                timestamp = it.closedAt ?: it.openedAt,
                balance = balance
            )
        }

        return EquityCurve(
            points = points
        )
    }

    private fun calculateTradeStatistics(
        trades: List<BacktestTrade>
    ): TradeStatistics {

        val wins = trades.filter {
            it.profitLoss > 0
        }

        val losses = trades.filter {
            it.profitLoss < 0
        }

        var maxWin = 0.0
        var maxLoss = 0.0

        trades.forEach {

            if (it.profitLoss > maxWin)
                maxWin = it.profitLoss

            if (it.profitLoss < maxLoss)
                maxLoss = it.profitLoss
        }

        var winStreak = 0
        var lossStreak = 0

        var currentWin = 0
        var currentLoss = 0

        trades.forEach {

            if (it.profitLoss > 0) {

                currentWin++
                currentLoss = 0

                if (currentWin > winStreak)
                    winStreak = currentWin

            } else {

                currentLoss++
                currentWin = 0

                if (currentLoss > lossStreak)
                    lossStreak = currentLoss
            }
        }

        return TradeStatistics(
            totalTrades = trades.size,
            winningTrades = wins.size,
            losingTrades = losses.size,

            winRate =
                if (trades.isEmpty())
                    0.0
                else
                    wins.size.toDouble() /
                            trades.size.toDouble(),

            averageTrade =
                if (trades.isEmpty())
                    0.0
                else
                    trades.sumOf {
                        it.profitLoss
                    } /
                    trades.size,

            largestWin = maxWin,
            largestLoss = maxLoss,

            consecutiveWins = winStreak,
            consecutiveLosses = lossStreak
        )
    }

    private fun calculateRiskMetrics(
        trades: List<BacktestTrade>
    ): RiskMetrics {

        val profits =
            trades.filter {
                it.profitLoss > 0
            }.sumOf {
                it.profitLoss
            }

        val losses =
            kotlin.math.abs(
                trades.filter {
                    it.profitLoss < 0
                }.sumOf {
                    it.profitLoss
                }
            )

        val maxDrawdown =
            calculateDrawdown(trades)

        return RiskMetrics(
            maxDrawdown = maxDrawdown,

            sharpeRatio =
                calculateSharpe(trades),

            recoveryFactor =
                if (maxDrawdown == 0.0)
                    0.0
                else
                    profits / maxDrawdown,

            riskRewardRatio =
                if (losses == 0.0)
                    0.0
                else
                    profits / losses
        )
    }

    private fun calculateDrawdown(
        trades: List<BacktestTrade>
    ): Double {

        var balance = 0.0
        var peak = 0.0
        var drawdown = 0.0

        trades.forEach {

            balance += it.profitLoss

            if (balance > peak)
                peak = balance

            val current =
                peak - balance

            if (current > drawdown)
                drawdown = current
        }

        return drawdown
    }

    private fun calculateSharpe(
        trades: List<BacktestTrade>
    ): Double {

        if (trades.isEmpty())
            return 0.0

        val values =
            trades.map {
                it.profitLoss
            }

        val average =
            values.average()

        val variance =
            values.sumOf {

                val diff =
                    it - average

                diff * diff

            } / values.size

        val deviation =
            kotlin.math.sqrt(
                variance
            )

        return if (deviation == 0.0)
            0.0
        else
            average / deviation
    }
}
