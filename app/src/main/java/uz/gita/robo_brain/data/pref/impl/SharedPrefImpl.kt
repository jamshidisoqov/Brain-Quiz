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

    companion object {
        const val SHARED_NAME = "app_data"
        const val BEST_SCORE_PUZZLE_2048 = "best_score_2048"
        const val MATRIX_PUZZLE_2048 = "matrix_puzzle_2048"

        private lateinit var instance: SharedPref

        fun init(ctx: Context) {
            instance = SharedPrefImpl(ctx)
        }

        fun getInstance() = instance

    }
}