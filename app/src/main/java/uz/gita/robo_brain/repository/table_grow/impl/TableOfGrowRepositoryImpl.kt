package uz.gita.robo_brain.repository.table_grow.impl

import uz.gita.robo_brain.data.pref.impl.SharedPrefImpl
import uz.gita.robo_brain.repository.table_grow.TableOfGrowRepository

class TableOfGrowRepositoryImpl private constructor() : TableOfGrowRepository {

    private val sharedPref = SharedPrefImpl.getInstance()

    private var numberList = ArrayList<Int>(16)

    private var selectedList = ArrayList<Int>()

    override fun getNumberList(): List<Int> {
        numberList.clear()
        selectedList.clear()
        var i = 0
        while (i < 16) {
            val random = (Math.random() * 100).toInt()
            if (!numberList.contains(random)) {
                numberList.add(random)
                selectedList.add(random)
                i++
            }
        }
        return numberList
    }

    override fun getNumber(): Int {
        val size = selectedList.size
        if (size == 0) {
            return -1
        }
        val random = (Math.random() * size).toInt()
        return selectedList.removeAt(random)
    }

    override fun setBestResult(result: Int) {
        if (getBestResult() > result) {
            sharedPref.setBestResultTableOfGrow(result)
        }
    }

    override fun getBestResult(): Int = sharedPref.getBestResultTableOfGrow()

    override fun getMusic(): Boolean  = sharedPref.getMusic()

    companion object {
        private lateinit var instance: TableOfGrowRepository

        fun getInstance(): TableOfGrowRepository {
            if (!Companion::instance.isInitialized) {
                instance = TableOfGrowRepositoryImpl()
            }
            return instance
        }
    }
}