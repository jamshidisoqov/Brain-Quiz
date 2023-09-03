package uz.uni_team.robo_brain.data.pref

import uz.uni_team.robo_brain.data.models.StatisticsByPuzzle15

// Created by Jamshid Isoqov an 8/19/2022
interface SharedPref {

    //puzzle2048
    fun getBestScorePuzzle2048(): Int

    fun setBestScorePuzzle2048(bestScore: Int)

    fun getMatrixPuzzle2048(): Array<Array<Int>>

    fun setMatrixPuzzle2048(matrix: Array<Array<Int>>)

    fun getCurrentScorePuzzle2048(): Int

    fun setCurrentScorePuzzle2048(currentScore: Int)

    //puzzle 15

    fun getNumberListPuzzle15(): List<Int>

    fun setNumberListPuzzle15(list: List<Int>)

    fun getMovedPuzzle15(): Int

    fun setMovedPuzzle15(moved: Int)

    fun getTimePuzzle15(): Int

    fun setTimePuzzle15(time: Int)

    fun setNewGame(newGame: Boolean)

    fun getNewGame(): Boolean

    fun getBestResult(): StatisticsByPuzzle15

    fun setBestResult(statisticsByPuzzle15: StatisticsByPuzzle15)

    //sorted math
    fun getBestResultSortedMath(): Int

    fun setBestResultSortedMath(result: Int)

    //true false
    fun getBestResultTrueFalse(): Int

    fun setBestResultTrueFalse(result: Int)

    //quick math
    fun getBestResultQuickMath(): Int

    fun setBestResultQuickMath(result: Int)

    //table of grow

    fun setBestResultTableOfGrow(result: Int)

    fun getBestResultTableOfGrow():Int

    //input math
    fun setBestResultInputMath(result: Int)

    fun getBestResultInputMath():Int

    //settings
    fun getName(): String

    fun setName(name: String)

    fun getImageUri():String

    fun setImageUri(uri:String)

    fun getMusic():Boolean

    fun setMusic(music:Boolean)

}