package uz.gita.robo_brain.repository.sorted_math.impl

import uz.gita.robo_brain.repository.models.SortedMath
import uz.gita.robo_brain.repository.sorted_math.SortedMathRepository
import uz.gita.robo_brain.utils.GenerateMathQuiz.generate

class SortedMathRepositoryImpl : SortedMathRepository {
    override fun getQuestions(question: Int): List<SortedMath> {
        var list = ArrayList<SortedMath>()
        for (i in 0..4){
            list.add(generate(question/5).toSortedMath())
        }
        return emptyList()
    }
}