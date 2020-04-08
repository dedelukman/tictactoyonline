package com.abahstudio.startup

import android.os.Bundle
import android.os.Handler
import android.view.View
import android.view.contentcapture.ContentCaptureSessionId
import android.widget.Button
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.google.firebase.analytics.FirebaseAnalytics
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener
import kotlinx.android.synthetic.main.activity_main.*
import java.lang.Exception
import java.util.*
import kotlin.collections.ArrayList


class MainActivity : AppCompatActivity() {

    private var mFirebaseAnalytics: FirebaseAnalytics? = null

    //Database instance
    private var database: FirebaseDatabase = FirebaseDatabase.getInstance();
    private var myRef=database.reference

    var myEmail:String?=null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        // Obtain the FirebaseAnalytics instance.
        mFirebaseAnalytics = FirebaseAnalytics.getInstance(this)
        var b: Bundle? = intent.extras
        myEmail=b!!.getString("email")
        IncomingCalls()

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
//        PlayGame(cellID,buSelected)
        myRef.child("PlayerOnline").child(sessionID!!).child(cellID.toString()).setValue(myEmail)
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
//                    AutoPlay()
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

    fun AutoPlay (cellID: Int){

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

    fun buRequestEvent(view: View) {
        var userDemail = etEmail.text.toString()
        myRef.child("Users").child(SplitString(userDemail)).child("Request").push().setValue(myEmail)

        PlayerOnline(SplitString(myEmail!!)+SplitString(userDemail))
        PlayerSymbol="X"

    }
    fun buAcceptEvent(view: View) {
        var userDemail = etEmail.text.toString()
        myRef.child("Users").child(SplitString(userDemail)).child("Request").push().setValue(myEmail)

        PlayerOnline(SplitString(userDemail)+SplitString(myEmail!!))
        PlayerSymbol="O"
    }

    var sessionID:String?=null
    var PlayerSymbol:String?=null
    fun PlayerOnline(sessionId: String){
        this.sessionID=sessionID
        myRef.child("PlayerOnline").removeValue()
        myRef.child("PlayerOnline").child(sessionID!!)
            .addValueEventListener(object :ValueEventListener{
                override fun onDataChange(p0: DataSnapshot) {
                    try {
                        Player1.clear()
                        Player2.clear()
                        val td=p0.value as HashMap<String,Any>
                        if (td!=null){
                            var value:String
                            for (key in td.keys){
                                value=td[key] as String
                               if (value!=myEmail){
                                   ActivePlayer= if (PlayerSymbol==="X") 1 else 2
                               }else{
                                   ActivePlayer= if (PlayerSymbol==="X") 2 else 1
                               }

                                AutoPlay(key.toInt())
                            }

                        }
                    }catch (ex:Exception){}
                }

                override fun onCancelled(p0: DatabaseError) {
                    
                }
            })
    }




    var number=0
    fun IncomingCalls(){
        myRef.child("Users").child(SplitString(myEmail!!)).child("Request")
            .addValueEventListener(object :ValueEventListener{
                override fun onDataChange(p0: DataSnapshot) {
                    try {
                        val td=p0.value as HashMap<String,Any>
                        if (td!=null){
                            var value:String
                            for (key in td.keys){
                                value=td[key] as String
                                etEmail.setText(value)
                                val notifyme = Notifications()
                                notifyme.Notify(applicationContext, value +" want to play tic tac toy", number)
                                myRef.child("Users").child(SplitString(myEmail!!)).child("Request").setValue(true)
                                break
                            }

                        }
                    }catch (ex:Exception){}
                }

                override fun onCancelled(p0: DatabaseError) {

                }
            })
    }

    fun SplitString(str:String):String{
//        var split1=str.split("\\.")
//        var str2 = split1.toString()
        var split = str.split("@")
        return split[0]
    }

}
