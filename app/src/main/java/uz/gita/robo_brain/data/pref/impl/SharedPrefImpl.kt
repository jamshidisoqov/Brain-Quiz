package uz.gita.robo_brain.data.pref.impl

import android.content.Context
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import uz.gita.robo_brain.data.pref.SharedPref

class SharedPrefImpl private constructor(ctx: Context) : SharedPref {

    private val sharedPref = ctx.getSharedPreferences(SHARED_NAME, Context.MODE_PRIVATE)
    private val editor = sharedPref.edit()
    private val gson = Gson()

    override fun getBestScorePuzzle2048(): Int = sharedPref.getInt(BEST_SCORE_PUZZLE_2048, 0)

    override fun setBestScorePuzzle2048(bestScore: Int) {
        editor.putInt(BEST_SCORE_PUZZLE_2048, bestScore).apply()
    }

    override fun getMatrixPuzzle2048(): Array<Array<Int>> {
        val type = object : TypeToken<Array<Array<Int>>>() {}.type
        val gsonString =
            sharedPref.getString(MATRIX_PUZZLE_2048, "[[0,0,0,0],[0,0,0,0],[0,0,0,0],[0,0,0,0]]")
        return gson.fromJson(gsonString, type)
    }

    override fun setMatrixPuzzle2048(matrix: Array<Array<Int>>) {
        val type = object : TypeToken<Array<Array<Int>>>() {}.type
        val gsonString = gson.toJson(matrix, type)
        editor.putString(MATRIX_PUZZLE_2048, gsonString).apply()
    }

    override fun getCurrentScorePuzzle2048(): Int = sharedPref.getInt(CURRENT_SCORE_PUZZLE_2048, 0)

    override fun setCurrentScorePuzzle2048(currentScore: Int) {
        editor.putInt(CURRENT_SCORE_PUZZLE_2048, currentScore).apply()
    }

    override fun getNumberListPuzzle15(): List<Int> {
        val type = object : TypeToken<List<Int>>() {}.type
        return gson.fromJson(
            sharedPref.getString(
                NUMBERS_PUZZLE_15,
                "[0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0]"
            ), type
        )
    }

    override fun setNumberListPuzzle15(list: List<Int>) {
        val type = object : TypeToken<List<Int>>() {}.type
        val jsonString = gson.toJson(list, type)
        editor.putString(NUMBERS_PUZZLE_15, jsonString).apply()
    }

    override fun getMovedPuzzle15(): Int = sharedPref.getInt(CURRENT_MOVED_PUZZLE_15, 0)

    override fun setMovedPuzzle15(moved: Int) {
        editor.putInt(CURRENT_MOVED_PUZZLE_15, moved).apply()
    }

    override fun getTimePuzzle15(): Int = sharedPref.getInt(CURRENT_TIME_PUZZLE_15, 0)

    override fun setTimePuzzle15(time: Int) {
        editor.putInt(CURRENT_TIME_PUZZLE_15, time).apply()
    }


    companion object {
        const val SHARED_NAME = "app_data"
        const val BEST_SCORE_PUZZLE_2048 = "best_score_2048"
        const val MATRIX_PUZZLE_2048 = "matrix_puzzle_2048"
        const val CURRENT_SCORE_PUZZLE_2048 = "current_score_2048"

        const val CURRENT_TIME_PUZZLE_15 = "current_time_puzzle_15"
        const val CURRENT_MOVED_PUZZLE_15 = "current_moved_puzzle_15"
        const val NUMBERS_PUZZLE_15 = "numbers_puzzle_15"

        private lateinit var instance: SharedPref

        fun init(ctx: Context) {
            instance = SharedPrefImpl(ctx)
        }

        fun getInstance() = instance

    }
}