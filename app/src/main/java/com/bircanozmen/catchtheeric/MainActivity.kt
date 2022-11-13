package com.bircanozmen.catchtheeric

import android.annotation.SuppressLint
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.CountDownTimer
import android.os.Looper
import android.os.Handler
import android.view.View
import android.widget.ImageView
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import kotlinx.android.synthetic.main.activity_main.*
import java.util.*
import kotlin.collections.ArrayList

class MainActivity : AppCompatActivity() {

    var score=0
    var imageArray= ArrayList<ImageView>()
    var handler= Handler(Looper.getMainLooper())
    var runnable=Runnable{ }


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        hideImages()

        image10.visibility=View.INVISIBLE

        //ImageArray
        imageArray.add(image1)
        imageArray.add(image2)
        imageArray.add(image3)
        imageArray.add(image4)
        imageArray.add(image5)
        imageArray.add(image6)
        imageArray.add(image7)
        imageArray.add(image8)
        imageArray.add(image9)




        // Initialize CounDownTimer
        object: CountDownTimer(15000, 1000) {
            override fun onFinish() {

                timeText.text="Time: 0"

                handler.removeCallbacks(runnable)

                for(image in imageArray){
                    image.visibility=View.INVISIBLE
                }

                image10.visibility=View.VISIBLE

                //Alert
                var alert= AlertDialog.Builder(this@MainActivity)

                alert.setTitle("Game Over")
                alert.setMessage("Restart the Game?")
                alert.setPositiveButton("Yes") {dialog, which ->

                    //restart
                    val intent= intent
                    finish()
                    startActivity(intent)
                }

                alert.setNegativeButton("No"){dialog, which ->
                    Toast.makeText(this@MainActivity,"Game Over",Toast.LENGTH_SHORT).show()
                    image10.visibility=View.VISIBLE
                }
                alert.show()
            }

            override fun onTick(p0: Long) {
                timeText.text="Time " +p0/1000

            }

        }.start()
    }


    fun hideImages() {

        runnable=object: Runnable{
            override fun run() {
                image10.visibility=View.INVISIBLE
                for(image in imageArray){
                    image.visibility=View.INVISIBLE
                }

                val random= Random()
                val randomIndex= random.nextInt(9) // between 0-8
                imageArray[randomIndex].visibility=View.VISIBLE

                handler.postDelayed(runnable, 400)
            }
            }
        handler.post(runnable)

        }



    fun increase(view: View){

        score= score+1
        scoreText.text= "Score: $score"

    }
}