package com.jookmax.v7.structure.model


import com.jookmax.v7.structure.analyzer.SwingClassification
import com.jookmax.v7.structure.model.BreakQuality


data class MarketStructure(


    val direction: StructureDirection,


    val lastSwingHigh: SwingPoint?,


    val lastSwingLow: SwingPoint?,


    val bosDetected: Boolean,


    val chochDetected: Boolean,


    val swingClassification: SwingClassification = SwingClassification.NONE,


    val pattern: StructurePattern = StructurePattern.UNKNOWN,


    val strength: StructureStrength =
        StructureStrength(
            score = 0.0,
            confidence = 0.0
        ),

    val breakQuality: BreakQuality? = null,


    val structureEvent: StructureEvent = StructureEvent.NONE,


    val timestamp: Long

)




