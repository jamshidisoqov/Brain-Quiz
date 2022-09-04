package uz.gita.robo_brain.repository.help

import uz.gita.robo_brain.repository.models.HelpData

// Created by Jamshid Isoqov an 9/4/2022
interface HelpRepository {

    fun getHelpList(): List<HelpData>

}