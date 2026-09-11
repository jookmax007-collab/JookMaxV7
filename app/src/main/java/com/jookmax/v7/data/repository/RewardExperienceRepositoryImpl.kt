package com.jookmax.v7.data.repository


import com.jookmax.v7.brain.reward.model.RewardExperience
import com.jookmax.v7.data.local.dao.RewardExperienceDao
import com.jookmax.v7.data.mapper.RewardExperienceMapper
import com.jookmax.v7.domain.repository.RewardExperienceRepository


import javax.inject.Inject
import javax.inject.Singleton



@Singleton
class RewardExperienceRepositoryImpl @Inject constructor(

    private val dao: RewardExperienceDao

) : RewardExperienceRepository {



    override suspend fun saveExperience(

        experience: RewardExperience

    ) {


        dao.insert(

            RewardExperienceMapper.toEntity(
                experience
            )

        )

    }





    override suspend fun getExperiences():

            List<RewardExperience> {


        return dao.getAll()

            .map {

                RewardExperienceMapper.toDomain(it)

            }

    }





    override suspend fun getAverageReward():

            Double {


        return dao.getAverageReward()
            ?: 0.0

    }





    override suspend fun clear() {


        dao.clear()

    }

}