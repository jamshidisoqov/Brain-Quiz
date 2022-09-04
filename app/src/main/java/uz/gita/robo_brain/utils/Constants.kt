package uz.gita.robo_brain.utils

import android.content.ActivityNotFoundException
import android.content.Intent
import android.net.Uri
import androidx.core.content.ContextCompat.startActivity
import uz.gita.robo_brain.MainActivity
import uz.gita.robo_brain.R

// Created by Jamshid Isoqov an 8/21/2022
object Constants {

    private val backgroundList = listOf(
        R.drawable.bg_header,
        R.drawable.default_background,
        R.drawable.bg_4,
        R.drawable.bg_8,
        R.drawable.bg_16,
        R.drawable.bg_32,
        R.drawable.bg_64,
        R.drawable.bg_128,
        R.drawable.bg_256,
        R.drawable.bg_512,
        R.drawable.bg_1024,
        R.drawable.bg_2048
    )

    fun getBackground(number: Int): Int = backgroundList[number.degreeOfTwo()]

    fun goToPlayMarket(activity: MainActivity) {
        try {
            activity.startActivity(
                Intent(
                    Intent.ACTION_VIEW,
                    Uri.parse("market://details?id=uz.gita.robo_brain")
                )
            )
        } catch (e: ActivityNotFoundException) {
            activity.startActivity(
                Intent(
                    Intent.ACTION_VIEW,
                    Uri.parse("https://play.google.com/store/apps/details?id=uz.gita.robo_brain")
                )
            )
        }
    }

    fun shareApp(activity: MainActivity){
        val intent = Intent(Intent.ACTION_SEND)
        intent.type = "text/plain"
        intent.putExtra(Intent.EXTRA_SUBJECT, "Robo Brain")
        intent.putExtra(
            Intent.EXTRA_TEXT,
            "link: https://play.google.com/store/apps/details?id=uz.gita.robo_brain"
        )
        activity.startActivity(Intent.createChooser(intent, "Robo Brain"))
    }

}

fun Int.degreeOfTwo(): Int {
    var value = this
    var deg = 0
    while (value > 1) {
        value /= 2
        deg++
    }
    return deg
}