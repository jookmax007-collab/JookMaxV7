package com.jookmax.v7.structure.model


data class MarketStructure(


    val direction: StructureDirection,


    val lastSwingHigh: SwingPoint?,


    val lastSwingLow: SwingPoint?,


    val bosDetected: Boolean,


    val chochDetected: Boolean,


    val structureEvent: StructureEvent = StructureEvent.NONE,


    val timestamp: Long

)