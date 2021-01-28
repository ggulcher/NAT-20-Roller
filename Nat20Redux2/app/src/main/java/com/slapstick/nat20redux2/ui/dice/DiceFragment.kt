package com.slapstick.nat20redux2.ui.dice

import android.app.AlertDialog
import android.media.MediaPlayer
import android.os.Bundle
import android.util.Log
import android.view.Gravity
import android.view.View
import android.widget.TextView
import androidx.fragment.app.Fragment
import com.slapstick.nat20redux2.R
import kotlinx.android.synthetic.main.fragment_dice.*

class DiceFragment : Fragment(R.layout.fragment_dice) {

    private var numDice = 1
    private var amountBonus = 0
    private var mediaPlayer: MediaPlayer? = MediaPlayer()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        d3.setOnClickListener{ rollResult(3) }
        d4.setOnClickListener{ rollResult(4) }
        d6.setOnClickListener{ rollResult(6) }
        d8.setOnClickListener{ rollResult(8) }
        d10.setOnClickListener{ rollResult(10) }
        d12.setOnClickListener{ rollResult(12) }
        d20.setOnClickListener{ rollResult(20) }
        d100.setOnClickListener{ rollResult(100) }

        selectBonus()
        selectDice()
        Log.d("DiceFragment", "Running")
    }

    private fun rollResult(numSides: Int) {
        var result = 0
        var range = 1..numSides
        var numDiceRolled = numDice + 1
        var firstPass = true

        while(numDiceRolled != 1) {
            result += range.random()
            if(firstPass && numSides == 20 && result == 20) {
                playSoundFile(R.raw.fanfare)
                firstPass = false
            }
            numDiceRolled--
        }
        result += amountBonus

        displayResult(result)

        Log.i("Result", result.toString())
        playSoundFile(R.raw.diceroll)
        mediaPlayer?.setOnCompletionListener { stopSoundFile() }

    }

    private fun selectDice() {
        tv_numberDice.text = "${numDice}d"
        iv_dicePlus.setOnClickListener {
            numDice++
            tv_numberDice.text = "${numDice}d"
            Log.d("Clicked", "Click works")
        }
        iv_diceMinus.setOnClickListener {
            if(numDice > 1) {
                iv_diceMinus.isClickable = true
                numDice--
                tv_numberDice.text = "${numDice}d"
            }
        }
    }

    private fun selectBonus() {
        tv_numberMod.text = "${amountBonus}"
        iv_modPlus.setOnClickListener {
            amountBonus++
            tv_numberMod.text = "${amountBonus}"
            Log.d("Clicked", "Click works")
        }
        iv_modMinus.setOnClickListener {
            amountBonus--
            tv_numberMod.text = "${amountBonus}"
        }
    }

    private fun playSoundFile(fileName: Int) {
        mediaPlayer = MediaPlayer.create(context, fileName)
        mediaPlayer?.start()
    }

    private fun stopSoundFile() {
        mediaPlayer?.release()
        mediaPlayer = null
    }

    private fun displayResult(result: Int) {
        val alertDialog = AlertDialog.Builder(context).create()
        alertDialog.setMessage("[${result - amountBonus} + $amountBonus]")
        val title = TextView(context)
        title.text = result.toString()
        title.setPadding(10, 10, 10, 10)
        title.gravity = Gravity.CENTER
        title.textSize = 80F
        alertDialog.setCustomTitle(title)

        alertDialog.show()

        val mw = alertDialog.findViewById<TextView>(android.R.id.message)
        mw.gravity = Gravity.CENTER
    }

}