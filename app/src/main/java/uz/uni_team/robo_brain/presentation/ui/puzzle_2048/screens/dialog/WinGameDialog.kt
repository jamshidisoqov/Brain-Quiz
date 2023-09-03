package uz.uni_team.robo_brain.presentation.ui.puzzle_2048.screens.dialog

import android.app.Dialog
import android.content.Context
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.media.MediaPlayer
import android.os.Bundle
import uz.uni_team.robo_brain.R
import uz.uni_team.robo_brain.databinding.DialogWinnerBinding

// Created by Jamshid Isoqov an 7/28/2022
class WinGameDialog(ctx: Context) : Dialog(ctx) {
    private lateinit var binding: DialogWinnerBinding
    private var retry: (() -> Unit)? = null
    private var dismissListener: (() -> Unit)? = null
    private val player = MediaPlayer.create(ctx, R.raw.winner)
    override fun onCreate(savedInstanceState: Bundle?) {
        binding = DialogWinnerBinding.inflate(layoutInflater)
        window?.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))
        setContentView(binding.root)
        player.start()
        binding.apply {
            btnNo.setOnClickListener {
                dismissListener?.invoke()
            }
            btnYes.setOnClickListener {
                retry?.invoke()
            }
        }
    }

    fun setRetryClickListener(t: () -> Unit) {
        retry = t
    }

    fun setDismissListener(v: () -> Unit) {
        dismissListener = v
    }
}