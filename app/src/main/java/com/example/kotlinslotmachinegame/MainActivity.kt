package com.example.kotlinslotmachinegame

import android.content.pm.ActivityInfo
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Toast
import com.example.kotlinslotmachinegame.ImageViewScrolling.IEventEnd
import kotlinx.android.synthetic.main.activity_main.*
import kotlin.random.Random

class MainActivity : AppCompatActivity(),IEventEnd {

    internal var count_down=0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        requestedOrientation= ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE
        image.setEventEnd(this@MainActivity)
        image1.setEventEnd(this@MainActivity)
        image2.setEventEnd(this@MainActivity)
        up.setOnClickListener{
            if(Common.SCORE>=50)
            {
                up.visibility=View.GONE
                down.visibility=View.VISIBLE
                image.setValueRandom(Random.nextInt(6),
                    Random.nextInt(15-5+1)+5)

                image1.setValueRandom(Random.nextInt(6),
                    Random.nextInt(15-5+1)+5)

                image2.setValueRandom(Random.nextInt(6),
                    Random.nextInt(15-5+1)+5)

                Common.SCORE-=50
                txt_score.text=Common.SCORE.toString()
            }
            else{
                Toast.makeText(this,"You do not have enough money",Toast.LENGTH_SHORT).show()

            }
        }

    }

    override fun eventEnd(result: Int, count: Int) {
        if(count_down<2)
        {
            count_down++
        }
        else{
            down.visibility= View.GONE
            up.visibility=View.VISIBLE

            count_down=0
            if(image.value==image1.value && image1.value== image2.value){
                Toast.makeText(this,"You win Big prize",Toast.LENGTH_SHORT).show()
                Common.SCORE+=300
                txt_score.text= Common.SCORE.toString()
            }
            else if(image.value==image1.value || image1.value== image2.value||image.value==image2.value){
                Toast.makeText(this,"You win Small prize",Toast.LENGTH_SHORT).show()
                Common.SCORE+=100
                txt_score.text= Common.SCORE.toString()
            }
            else{
                Toast.makeText(this,"You lose",Toast.LENGTH_SHORT).show()

            }
        }
    }
}
