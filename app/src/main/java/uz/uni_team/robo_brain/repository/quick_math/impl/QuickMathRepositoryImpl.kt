package uz.uni_team.robo_brain.repository.quick_math.impl

import uz.uni_team.robo_brain.data.pref.impl.SharedPrefImpl
import uz.uni_team.robo_brain.repository.models.MathData
import uz.uni_team.robo_brain.repository.quick_math.QuickMathRepository
import uz.uni_team.robo_brain.utils.GenerateMathQuiz
import kotlin.math.pow

class QuickMathRepositoryImpl private constructor() : QuickMathRepository {

    private val sharedPref = SharedPrefImpl.getInstance()

    override fun getQuestion(level: Int): MathData {
        val question = GenerateMathQuiz.generate(level)
        val list = ArrayList<Int>()
        var i = 0
        if (question.answer >= -1 && question.answer <= 1) {
            list.addAll(listOf(-1, 0, 1, 2))
        } else {
            while (i <= 1) {
                val num =
                    question.answer + (-1.0).pow(i.toDouble())
                        .toInt() * (Math.random() * question.answer * 2).toInt()
                if (!list.contains(num) && num != question.answer) {
                    list.add(num)
                    i++
                }
            }
            i = 0
            while (i <= 1) {
                val num =
                    question.answer + (-1.0).pow(i.toDouble())
                        .toInt() * (Math.random() * question.answer * 4).toInt()
                if (!list.contains(num) && num != question.answer) {
                    list.add(num)
                    i++
                }
            }
        }
        val random = (Math.random() * 4).toInt()
        list[random] = question.answer
        return MathData("${question.question}=?", list, random)
    }

    override fun setBestResult(result: Int) {
        if (getBestResult() < result) {
            sharedPref.setBestResultQuickMath(result)
        }
    }

    override fun getBestResult(): Int = sharedPref.getBestResultQuickMath()

    override fun getMusic(): Boolean = sharedPref.getMusic()

    companion object {
        private lateinit var instance: QuickMathRepository

        fun getInstance(): QuickMathRepository {
            if (!Companion::instance.isInitialized) {
                instance = QuickMathRepositoryImpl()
            }
            return instance
        }
    }
}