package com.jookmax.v7.brain.reward


import com.jookmax.v7.brain.decision.DecisionAction
import com.jookmax.v7.brain.learning.LearningBrain
import com.jookmax.v7.brain.learning.LearningExperience
import com.jookmax.v7.brain.learning.LearningExperienceManager
import com.jookmax.v7.brain.reward.model.RewardExperience
import com.jookmax.v7.brain.trading.model.TradeExitReason
import com.jookmax.v7.brain.trading.model.TradeOutcome
import com.jookmax.v7.domain.repository.LearningRepository
import com.jookmax.v7.domain.repository.RewardExperienceRepository

import kotlinx.coroutines.runBlocking

import org.junit.Assert.assertEquals
import org.junit.Assert.assertTrue
import org.junit.Test



class RewardEngineTest {



    @Test
    fun `successful trade should generate positive reward experience`() =
        runBlocking {



            val rewardRepository =
                FakeRewardExperienceRepository()



            val learningRepository =
                FakeLearningRepository()



            val learningExperienceManager =
                LearningExperienceManager(
                    learningRepository
                )



            val learningBrain =
                LearningBrain(
                    learningExperienceManager
                )



            val engine =
                RewardEngine(

                    rewardCalculator =
                    RewardCalculator(),

                    learningBrain =
                    learningBrain,

                    rewardExperienceRepository =
                    rewardRepository

                )



            val outcome =
                TradeOutcome(


                    positionId =
                    "TEST-1",


                    action =
                    DecisionAction.BUY,


                    entryPrice =
                    2500.0,


                    exitPrice =
                    2520.0,


                    stopLoss =
                    2490.0,


                    takeProfit =
                    2530.0,


                    positionSize =
                    1.0,


                    openedAt =
                    1L,


                    closedAt =
                    2L,


                    exitReason =
                    TradeExitReason.TAKE_PROFIT,


                    profitLoss =
                    20.0,


                    success =
                    true

                )




            val result =
                engine.evaluate(
                    outcome
                )




            assertTrue(

                result.reward > 0

            )




            assertTrue(

                result.success

            )




            assertEquals(

                1,

                rewardRepository.items.size

            )

        }

}





class FakeRewardExperienceRepository :
    RewardExperienceRepository {



    val items =
        mutableListOf<RewardExperience>()




    override suspend fun saveExperience(

        experience: RewardExperience

    ) {

        items.add(
            experience
        )

    }





    override suspend fun getExperiences():

            List<RewardExperience> {

        return items

    }





    override suspend fun getAverageReward():

            Double {


        if(items.isEmpty()) {

            return 0.0

        }



        return items
            .map {

                it.reward

            }
            .average()

    }





    override suspend fun clear() {

        items.clear()

    }


}








class FakeLearningRepository :
    LearningRepository {



    private val experiences =
        mutableListOf<LearningExperience>()




    override suspend fun saveExperience(

        experience: LearningExperience

    ) {

        experiences.add(
            experience
        )

    }





    override suspend fun getExperiences():

            List<LearningExperience> {

        return experiences

    }





    override suspend fun getLatestExperience():

            LearningExperience? {

        return experiences.lastOrNull()

    }





    override suspend fun getExperienceCount():

            Int {

        return experiences.size

    }





    override suspend fun getAverageReward():

            Double {


        if(experiences.isEmpty()) {

            return 0.0

        }


        return experiences
            .map {

                it.reward

            }
            .average()

    }





    override suspend fun getSuccessRate():

            Double {


        if(experiences.isEmpty()) {

            return 0.0

        }



        return experiences.count {

            it.success

        }.toDouble() / experiences.size

    }





    override suspend fun clearMemory() {

        experiences.clear()

    }


}