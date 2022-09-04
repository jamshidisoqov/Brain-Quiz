package uz.gita.robo_brain.presentation.ui.puzzle_2048.screens.dialog

import android.app.Dialog
import android.content.Context
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.media.MediaPlayer
import android.os.Bundle
import uz.gita.robo_brain.R
import uz.gita.robo_brain.databinding.DialogGameOverBinding

// Created by Jamshid Isoqov an 7/27/2022
class GameOverDialog(ctx: Context) : Dialog(ctx) {
    private lateinit var binding: DialogGameOverBinding
    private val player = MediaPlayer.create(ctx, R.raw.over)
    private var retryListener: (() -> Unit)? = null
    private var finishListener: (() -> Unit)? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        player.start()
        binding = DialogGameOverBinding.inflate(layoutInflater)
        setContentView(binding.root)
        window?.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))
        setCancelable(false)
        binding.apply {
            btnNo.setOnClickListener { finishListener?.invoke() }
            btnYes.setOnClickListener { retryListener?.invoke() }
        }
    }

    fun setRetryListener(t: () -> Unit) {
        retryListener = t
    }

    fun setFinishListener(t: () -> Unit) {
        finishListener = t
    }
}