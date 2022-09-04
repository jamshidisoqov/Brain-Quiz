package uz.gita.robo_brain.repository.input_math.impl

import uz.gita.robo_brain.data.pref.impl.SharedPrefImpl
import uz.gita.robo_brain.repository.input_math.InputMathRepository
import uz.gita.robo_brain.repository.models.InputMathData
import uz.gita.robo_brain.utils.GenerateMathQuiz

class InputMathRepositoryImpl private constructor() : InputMathRepository {

    private val sharedPref = SharedPrefImpl.getInstance()

    override fun getQuestion(level: Int): InputMathData {
        return GenerateMathQuiz.generate(level).toInputMath()
    }

    override fun setBestResult(result: Int) {
        if (getBestResult() < result) {
            sharedPref.setBestResultInputMath(result)
        }
    }

    override fun getBestResult(): Int = sharedPref.getBestResultInputMath()

    companion object {
        private lateinit var instance: InputMathRepository

        fun getInstance(): InputMathRepository {
            if (!Companion::instance.isInitialized) {
                instance = InputMathRepositoryImpl()
            }
            return instance
        }
    }
}