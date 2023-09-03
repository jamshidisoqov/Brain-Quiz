package uz.uni_team.robo_brain.repository.help.impl

import uz.uni_team.robo_brain.repository.help.HelpRepository
import uz.uni_team.robo_brain.repository.models.HelpData

class HelpRepositoryImpl private constructor() : HelpRepository {

    override fun getHelpList(): List<HelpData> {
        return listOf(
            HelpData(
                "Puzzle 15",
                "You have to sort the words from 1 to 15 in this month in sequence using capital letters.Your best score is calculated based on time and number of moves"
            ),
            HelpData(
                "Math Sort",
                "You have to calculate the value of the given expressions in this game and move the highest value to the top and the lowest value to the bottom"
            ),
            HelpData(
                "Puzzle 2048",
                "In this game you have to match the same numbers given to each other and reach the highest number to 2048"
            ),
            HelpData(
                "Input Math",
                "You have to calculate the value of the given expression in this game and generate this number using the buttons. Do not forget that the answer does not exceed 5 digits, so the length of the selected answer does not exceed 5 digits."
            ),
            HelpData(
                "Table Of Grow",
                "You can improve your recall in this game that is you are given numbers in 16 cells and task is to find which number below and you have to click on that number"
            ),
        )
    }

    companion object {
        private lateinit var instance: HelpRepository

        fun getInstance(): HelpRepository {
            if (!Companion::instance.isInitialized) {
                instance = HelpRepositoryImpl()
            }
            return instance
        }
    }
}