package com.jookmax.v7.brain.learning

import com.jookmax.v7.domain.repository.LearningRepository

import kotlinx.coroutines.runBlocking

import javax.inject.Inject
import javax.inject.Singleton


/**
 * Learning Experience Manager
 *
 * Bridge between Brain Learning Layer and persistent LearningRepository
 */
@Singleton
class LearningExperienceManager @Inject constructor(

    private val repository: LearningRepository

) {


    fun addExperience(

        experience: LearningExperience

    ) {

        runBlocking {

            repository.saveExperience(

                experience

            )

        }

    }



    fun getExperiences():

            List<LearningExperience> {


        return runBlocking {

            repository.getExperiences()

        }

    }




    fun getLatestExperience():

            LearningExperience? {


        return runBlocking {

            repository.getLatestExperience()

        }

    }




    fun getExperienceCount():

            Int {


        return runBlocking {

            repository.getExperienceCount()

        }

    }




    fun getAverageReward():

            Double {


        return runBlocking {

            repository.getAverageReward()

        }

    }




    fun getSuccessRate():

            Double {


        return runBlocking {

            repository.getSuccessRate()

        }

    }




    fun getFailureRate():

            Double {


        return 1.0 - getSuccessRate()

    }




    fun clear() {


        runBlocking {

            repository.clearMemory()

        }

    }




    fun reset() {


        clear()

    }


}
