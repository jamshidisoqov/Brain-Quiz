package uz.uni_team.robo_brain.utils

import android.annotation.SuppressLint
import android.content.Context
import android.view.GestureDetector
import android.view.MotionEvent
import android.view.View
import uz.uni_team.robo_brain.repository.models.Movement
import kotlin.math.abs

// Created by Jamshid Isoqov an 8/21/2022
class MovementDetector(ctx: Context) : View.OnTouchListener {
    private var listener: ((Movement) -> Unit)? = null
    private val move = 80
    private val gesture =
        GestureDetector(ctx, object : GestureDetector.SimpleOnGestureListener() {
            override fun onFling(
                e1: MotionEvent,
                e2: MotionEvent,
                velocityX: Float,
                velocityY: Float
            ): Boolean {
                if (abs(e1.x - e2.x) > abs(e1.y - e2.y)) {
                    if (abs(e1.x - e2.x) < move) return false
                    listener?.invoke(if (e2.x > e1.x) Movement.RIGHT else Movement.LEFT)
                } else {
                    if (abs(e1.y - e2.y) < move) return false
                    listener?.invoke(if (e2.y > e1.y) Movement.DOWN else Movement.UP)
                }
                return true
            }
        })

    @SuppressLint("ClickableViewAccessibility")
    override fun onTouch(p0: View?, p1: MotionEvent?): Boolean {
        gesture.onTouchEvent(p1)
        return true
    }

    fun setOnMovementListener(block: (Movement) -> Unit) {
        listener = block
    }
}