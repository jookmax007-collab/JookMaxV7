package com.jookmax.v7.brain.confidence


data class ConfidenceHistory(

    val feedbacks: List<ConfidenceFeedback> = emptyList()

) {



    fun add(

        feedback: ConfidenceFeedback

    ): ConfidenceHistory {


        return copy(

            feedbacks =

                feedbacks + feedback

        )

    }



    fun averageReward(): Double {


        if (feedbacks.isEmpty())

            return 0.0



        return feedbacks

            .map { it.reward }

            .average()

    }

}