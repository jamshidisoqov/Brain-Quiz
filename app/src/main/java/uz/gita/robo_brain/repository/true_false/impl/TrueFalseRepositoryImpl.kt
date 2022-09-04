package uz.gita.robo_brain.repository.true_false.impl

import uz.gita.robo_brain.data.pref.impl.SharedPrefImpl
import uz.gita.robo_brain.repository.models.TrueFalseData
import uz.gita.robo_brain.repository.true_false.TrueFalseRepository
import uz.gita.robo_brain.utils.GenerateMathQuiz

class TrueFalseRepositoryImpl private constructor() : TrueFalseRepository {

    private val sharedPref = SharedPrefImpl.getInstance()

    override fun getQuestion(level: Int): TrueFalseData {
        val question = GenerateMathQuiz.generate(level)
        val random = (Math.random() * 2).toInt()
        return if (random == 0) {
            TrueFalseData("${question.question}=${question.answer}", true)
        } else {
            val number = (Math.random() * 2 * question.answer).toInt()
            TrueFalseData("${question.question}=$number", false)
        }
    }

    override fun setBestResult(result: Int) {
        if (getBestResult() < result) {
            sharedPref.setBestResultTrueFalse(result)
        }
    }

    override fun getBestResult(): Int = sharedPref.getBestResultTrueFalse()

    override fun getMusic(): Boolean = sharedPref.getMusic()

    companion object {
        private lateinit var instance: TrueFalseRepository

        fun getInstance(): TrueFalseRepository {
            if (!Companion::instance.isInitialized) {
                instance = TrueFalseRepositoryImpl()
            }
            return instance
        }
    }


}