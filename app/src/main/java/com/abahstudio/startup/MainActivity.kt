package com.abahstudio.startup

import android.content.Intent
import android.graphics.Color
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.provider.Settings
import android.view.View
import android.widget.Button
import android.widget.Toast
import kotlinx.android.synthetic.main.activity_main.*
import java.util.*
import kotlin.collections.ArrayList
import kotlin.concurrent.schedule
import kotlin.system.exitProcess

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)


    }

    fun buClick(view:View) {
        val buSelected= view as Button
        var cellID = 0
        when(buSelected.id){
            R.id.bu1->cellID=1
            R.id.bu2->cellID=2
            R.id.bu3->cellID=3
            R.id.bu4->cellID=4
            R.id.bu5->cellID=5
            R.id.bu6->cellID=6
            R.id.bu7->cellID=7
            R.id.bu8->cellID=8
            R.id.bu9->cellID=9
        }
        PlayGame(cellID,buSelected)
    }

    var Player1=ArrayList<Int>()
    var Player2=ArrayList<Int>()
    var ActivePlayer=1
    var winner:Int? = null


    fun PlayGame(cellID:Int,buSelected:Button){
        if (ActivePlayer==1){
            buSelected.text="X"
            buSelected.setBackgroundResource(R.color.blue)
            Player1.add(cellID)
            ActivePlayer=2
            var emptyStatus=false
            for (cellID in 1..9){
                if (!( Player1.contains(cellID) || Player2.contains(cellID))){
                    emptyStatus=true
                }
            }
            if (emptyStatus==true){
                Handler().postDelayed({     // for delay app
                    AutoPlay()
                }, 1000)

            }

        }else{
            buSelected.text="O"
            buSelected.setBackgroundResource(R.color.darkgreen)
            Player2.add(cellID)
            ActivePlayer=1
        }
        buSelected.isEnabled=false
        CheckWinner()
    }

    fun CheckWinner(){
        winner=-1
        // Player 1
        // row
        if (Player1.contains(1) && Player1.contains(2) && Player1.contains(3)){
            winner=1
        }
        if (Player1.contains(4) && Player1.contains(5) && Player1.contains(6)){
            winner=1
        }
        if (Player1.contains(7) && Player1.contains(8) && Player1.contains(9)){
            winner=1
        }
        // column
        if (Player1.contains(1) && Player1.contains(4) && Player1.contains(7)){
            winner=1
        }
        if (Player1.contains(2) && Player1.contains(5) && Player1.contains(8)){
            winner=1
        }
        if (Player1.contains(3) && Player1.contains(6) && Player1.contains(9)){
            winner=1
        }
        // cross
        if (Player1.contains(1) && Player1.contains(5) && Player1.contains(9)){
            winner=1
        }
        if (Player1.contains(3) && Player1.contains(5) && Player1.contains(7)){
            winner=1
        }

        // Player 2
        // row
        if (Player2.contains(1) && Player2.contains(2) && Player2.contains(3)){
            winner=2
        }
        if (Player2.contains(4) && Player2.contains(5) && Player2.contains(6)){
            winner=2
        }
        if (Player2.contains(7) && Player2.contains(8) && Player2.contains(9)){
            winner=2
        }
        // column
        if (Player2.contains(1) && Player2.contains(4) && Player2.contains(7)){
            winner=2
        }
        if (Player2.contains(2) && Player2.contains(5) && Player2.contains(8)){
            winner=2
        }
        if (Player2.contains(3) && Player2.contains(6) && Player2.contains(9)){
            winner=2
        }
        // cross
        if (Player2.contains(1) && Player2.contains(5) && Player2.contains(9)){
            winner=2
        }
        if (Player2.contains(3) && Player2.contains(5) && Player2.contains(7)){
            winner=2
        }

        if (winner != -1){
            if (winner == 1){
                Toast.makeText(this,"Player 1 win the game", Toast.LENGTH_LONG).show()
            }else{
                Toast.makeText(this,"Player 2 win the game", Toast.LENGTH_LONG).show()
            }
            Toast.makeText(this,"Play again!!", Toast.LENGTH_LONG).show()
            Handler().postDelayed({
                Reset()
            }, 5000)



        }

    }

    fun AutoPlay (){
        var cellID=0

        if (Player2.contains(1) && Player2.contains(2) && (!( Player2.contains(3) || Player1.contains(3)))){
            cellID=3
        }
        else if (Player2.contains(1) && Player2.contains(3) && (!( Player2.contains(2) || Player1.contains(2)))){
            cellID=2
        }
        else if (Player2.contains(1) && Player2.contains(4) && (!( Player2.contains(7) || Player1.contains(7)))){
            cellID=7
        }
        else if (Player2.contains(1) && Player2.contains(7) && (!( Player2.contains(4) || Player1.contains(4)))){
            cellID=4
        }
        else if (Player2.contains(1) && Player2.contains(5) && (!( Player2.contains(9) || Player1.contains(9)))){
            cellID=9
        }
        else if (Player2.contains(1) && Player2.contains(9) && (!( Player2.contains(5) || Player1.contains(5)))){
            cellID=5
        }
        else if (Player2.contains(2) && Player2.contains(3) && (!( Player2.contains(1) || Player1.contains(1)))){
            cellID=1
        }
        else if (Player2.contains(4) && Player2.contains(7) && (!( Player2.contains(1) || Player1.contains(1)))){
            cellID=1
        }
        else if (Player2.contains(9) && Player2.contains(5) && (!( Player2.contains(1) || Player1.contains(1)))){
            cellID=1
        }
        else if (Player2.contains(2) && Player2.contains(5) && (!( Player2.contains(8) || Player1.contains(8)))){
            cellID=8
        }
        else if (Player2.contains(2) && Player2.contains(8) && (!( Player2.contains(5) || Player1.contains(5)))){
            cellID=5
        }
        else if (Player2.contains(5) && Player2.contains(8) && (!( Player2.contains(2) || Player1.contains(2)))){
            cellID=2
        }
        else if (Player2.contains(3) && Player2.contains(5) && (!( Player2.contains(7) || Player1.contains(7)))){
            cellID=7
        }
        else if (Player2.contains(3) && Player2.contains(7) && (!( Player2.contains(5) || Player1.contains(5)))){
            cellID=5
        }
        else if (Player2.contains(5) && Player2.contains(7) && (!( Player2.contains(3) || Player1.contains(3)))){
            cellID=3
        }
        else if (Player2.contains(4) && Player2.contains(5) && (!( Player2.contains(6) || Player1.contains(6)))){
            cellID=6
        }
        else if (Player2.contains(4) && Player2.contains(6) && (!( Player2.contains(5) || Player1.contains(5)))){
            cellID=5
        }
        else if (Player2.contains(5) && Player2.contains(6) && (!( Player2.contains(4) || Player1.contains(4)))){
            cellID=4
        }
        else if (Player2.contains(7) && Player2.contains(8) && (!( Player2.contains(9) || Player1.contains(9)))){
            cellID=9
        }
        else if (Player2.contains(7) && Player2.contains(9) && (!( Player2.contains(8) || Player1.contains(8)))){
            cellID=8
        }
        else if (Player2.contains(8) && Player2.contains(9) && (!( Player2.contains(7) || Player1.contains(7)))){
            cellID=7
        }
        else if (Player2.contains(3) && Player2.contains(6) && (!( Player2.contains(9) || Player1.contains(9)))){
            cellID=9
        }
        else if (Player2.contains(3) && Player2.contains(9) && (!( Player2.contains(6) || Player1.contains(6)))){
            cellID=6
        }
        else if (Player2.contains(9) && Player2.contains(6) && (!( Player2.contains(3) || Player1.contains(3)))){
            cellID=3
        }
        else if (Player1.contains(1) && Player1.contains(2) && (!( Player1.contains(3) || Player2.contains(3)))){ // player 2
            cellID=3
        }
        else if (Player1.contains(1) && Player1.contains(3) && (!( Player1.contains(2) || Player2.contains(2)))){
                cellID=2
        }
        else if (Player1.contains(1) && Player1.contains(4) && (!( Player1.contains(7) || Player2.contains(7)))){
            cellID=7
        }
        else if (Player1.contains(1) && Player1.contains(7) && (!( Player1.contains(4) || Player2.contains(4)))){
            cellID=4
        }
        else if (Player1.contains(1) && Player1.contains(5) && (!( Player1.contains(9) || Player2.contains(9)))){
            cellID=9
        }
        else if (Player1.contains(1) && Player1.contains(9) && (!( Player1.contains(5) || Player2.contains(5)))){
            cellID=5
        }
        else if (Player1.contains(2) && Player1.contains(3) && (!( Player1.contains(1) || Player2.contains(1)))){
            cellID=1
        }
        else if (Player1.contains(4) && Player1.contains(7) && (!( Player1.contains(1) || Player2.contains(1)))){
            cellID=1
        }
        else if (Player1.contains(9) && Player1.contains(5) && (!( Player1.contains(1) || Player2.contains(1)))){
            cellID=1
        }
        else if (Player1.contains(2) && Player1.contains(5) && (!( Player1.contains(8) || Player2.contains(8)))){
            cellID=8
        }
        else if (Player1.contains(2) && Player1.contains(8) && (!( Player1.contains(5) || Player2.contains(5)))){
            cellID=5
        }
        else if (Player1.contains(5) && Player1.contains(8) && (!( Player1.contains(2) || Player2.contains(2)))){
            cellID=2
        }
        else if (Player1.contains(3) && Player1.contains(5) && (!( Player1.contains(7) || Player2.contains(7)))){
            cellID=7
        }
        else if (Player1.contains(3) && Player1.contains(7) && (!( Player1.contains(5) || Player2.contains(5)))){
            cellID=5
        }
        else if (Player1.contains(5) && Player1.contains(7) && (!( Player1.contains(3) || Player2.contains(3)))){
            cellID=3
        }
        else if (Player1.contains(4) && Player1.contains(5) && (!( Player1.contains(6) || Player2.contains(6)))){
            cellID=6
        }
        else if (Player1.contains(4) && Player1.contains(6) && (!( Player1.contains(5) || Player2.contains(5)))){
            cellID=5
        }
        else if (Player1.contains(5) && Player1.contains(6) && (!( Player1.contains(4) || Player2.contains(4)))){
            cellID=4
        }
        else if (Player1.contains(7) && Player1.contains(8) && (!( Player1.contains(9) || Player2.contains(9)))){
            cellID=9
        }
        else if (Player1.contains(7) && Player1.contains(9) && (!( Player1.contains(8) || Player2.contains(8)))){
            cellID=8
        }
        else if (Player1.contains(8) && Player1.contains(9) && (!( Player1.contains(7) || Player2.contains(7)))){
            cellID=7
        }
        else if (Player1.contains(3) && Player1.contains(6) && (!( Player1.contains(9) || Player2.contains(9)))){
            cellID=9
        }
        else if (Player1.contains(3) && Player1.contains(9) && (!( Player1.contains(6) || Player2.contains(6)))){
            cellID=6
        }
        else if (Player1.contains(9) && Player1.contains(6) && (!( Player1.contains(3) || Player2.contains(3)))){
            cellID=3
        }else {
            var emptyCells = ArrayList<Int>()
            for (collID in 1..9) {
                if (!(Player1.contains(collID) || Player2.contains(collID))) {
                    emptyCells.add(collID)
                }
            }
            val r = Random()
            val randIndex = r.nextInt(emptyCells.size - 0) + 0
            cellID = emptyCells[randIndex]
        }
        var buSelect:Button?
        when(cellID){
            1-> buSelect=bu1
            2-> buSelect=bu2
            3-> buSelect=bu3
            4-> buSelect=bu4
            5-> buSelect=bu5
            6-> buSelect=bu6
            7-> buSelect=bu7
            8-> buSelect=bu8
            9-> buSelect=bu9
            else->{
                buSelect=bu1
            }
        }

        PlayGame(cellID,buSelect)
    }

    fun buReset(view: View){
       Reset()
    }

    fun Reset(){
        // reset background button
        bu1.setBackgroundResource(R.color.white)
        bu2.setBackgroundResource(R.color.white)
        bu3.setBackgroundResource(R.color.white)
        bu4.setBackgroundResource(R.color.white)
        bu5.setBackgroundResource(R.color.white)
        bu6.setBackgroundResource(R.color.white)
        bu7.setBackgroundResource(R.color.white)
        bu8.setBackgroundResource(R.color.white)
        bu9.setBackgroundResource(R.color.white)

        // reset text button
        bu1.text=""
        bu2.text=""
        bu3.text=""
        bu4.text=""
        bu5.text=""
        bu6.text=""
        bu7.text=""
        bu8.text=""
        bu9.text=""

        // enable button
        bu1.isEnabled=true
        bu2.isEnabled=true
        bu3.isEnabled=true
        bu4.isEnabled=true
        bu5.isEnabled=true
        bu6.isEnabled=true
        bu7.isEnabled=true
        bu8.isEnabled=true
        bu9.isEnabled=true


        winner=-1
        Player1.clear()
        Player2.clear()
    }



}
