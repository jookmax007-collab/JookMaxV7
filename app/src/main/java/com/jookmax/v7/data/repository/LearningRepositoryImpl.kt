package com.jookmax.v7.data.repository

import com.jookmax.v7.brain.learning.LearningExperience
import com.jookmax.v7.data.local.dao.LearningExperienceDao
import com.jookmax.v7.data.mapper.LearningExperienceMapper
import com.jookmax.v7.domain.repository.LearningRepository

import kotlinx.coroutines.flow.first

import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class LearningRepositoryImpl @Inject constructor(
    private val learningExperienceDao: LearningExperienceDao
) : LearningRepository {

    override suspend fun saveExperience(
        experience: LearningExperience
    ) {
        learningExperienceDao.insert(
            LearningExperienceMapper.toEntity(experience)
        )
    }

    override suspend fun getExperiences():
            List<LearningExperience> {

        return learningExperienceDao
            .getLatest(Int.MAX_VALUE)
            .first()
            .map(LearningExperienceMapper::fromEntity)
    }

    override suspend fun getLatestExperience():
            LearningExperience? {

        return learningExperienceDao
            .getLatest(1)
            .first()
            .firstOrNull()
            ?.let(LearningExperienceMapper::fromEntity)
    }

    override suspend fun getExperienceCount():
            Int {

        return learningExperienceDao.count()
    }

    override suspend fun getAverageReward():
            Double {

        val experiences = getExperiences()

        if (experiences.isEmpty()) {
            return 0.0
        }

        return experiences
            .map { it.reward }
            .average()
    }

    override suspend fun getSuccessRate():
            Double {

        val experiences = getExperiences()

        if (experiences.isEmpty()) {
            return 0.0
        }

        val successfulCount =
            experiences.count { it.success }

        return successfulCount.toDouble() /
            experiences.size.toDouble()
    }

    override suspend fun clearMemory() {
        learningExperienceDao.deleteAll()
    }
}
