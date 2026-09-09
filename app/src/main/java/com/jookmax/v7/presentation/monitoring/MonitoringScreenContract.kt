package com.jookmax.v7.presentation.monitoring



/**
 * User actions from Monitoring screen.
 *
 * UI sends these actions to ViewModel.
 */
sealed interface MonitoringAction {



    /**
     * Refresh monitoring data.
     */
    data object Refresh : MonitoringAction





    /**
     * Reset monitoring state.
     */
    data object Reset : MonitoringAction



}





/**
 * One-time events from Monitoring layer.
 *
 * Used for future:
 * - Navigation
 * - Notifications
 * - Messages
 */
sealed interface MonitoringEvent {



    /**
     * Generic error event.
     */
    data class ShowError(

        val message: String

    ) : MonitoringEvent



}
