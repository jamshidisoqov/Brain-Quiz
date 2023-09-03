package uz.uni_team.robo_brain.repository.sorted_math.impl

import uz.uni_team.robo_brain.data.pref.impl.SharedPrefImpl
import uz.uni_team.robo_brain.repository.models.SortedMath
import uz.uni_team.robo_brain.repository.sorted_math.SortedMathRepository
import uz.uni_team.robo_brain.utils.GenerateMathQuiz.generate

class SortedMathRepositoryImpl private constructor() : SortedMathRepository {

    private val sharedPref = SharedPrefImpl.getInstance()

    override fun getQuestions(question: Int): List<SortedMath> {
        val list = ArrayList<SortedMath>()
        for (i in 0..4) {
            list.add(generate(question).toSortedMath())
        }
        list.sortBy {
            it.answer
        }
        for (i in 0..4) {
            list[i] = list[i].copy(order = i)
        }

        return list
    }

    override fun setBestResult(result: Int) {
        if (getBestResult() < result) {
            sharedPref.setBestResultSortedMath(result)
        }
    }

    override fun getBestResult(): Int = sharedPref.getBestResultSortedMath()

    override fun getMusic(): Boolean  = sharedPref.getMusic()

    companion object {
        private lateinit var instance: SortedMathRepository

        fun getInstance(): SortedMathRepository {
            if (!Companion::instance.isInitialized) {
                instance = SortedMathRepositoryImpl()
            }
            return instance
        }
    }
}