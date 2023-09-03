package uz.uni_team.robo_brain.utils

import android.view.View
import com.daimajia.androidanimations.library.Techniques
import com.daimajia.androidanimations.library.YoYo

// Created by Jamshid Isoqov an 8/31/2022


fun View.visible() {
    this.visibility = View.VISIBLE
}

fun View.invisible() {
    this.visibility = View.INVISIBLE
}

fun View.zoomAnim() {
    YoYo.with(Techniques.ZoomIn)
        .delay(0)
        .duration(100)
        .playOn(this)
}