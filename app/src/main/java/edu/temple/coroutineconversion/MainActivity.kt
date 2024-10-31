package edu.temple.coroutineconversion

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import java.util.Locale

class MainActivity : AppCompatActivity() {


    private val cakeImageView: ImageView by lazy {
        findViewById(R.id.imageView)
    }

    private val currentTextView: TextView by lazy {
        findViewById(R.id.currentTextView)
    }


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val scope = CoroutineScope(Job() + Dispatchers.Default)

        findViewById<Button>(R.id.revealButton).setOnClickListener{
            scope.launch {
                opacityChanger()
            }
        }

    }
    suspend fun opacityChanger() {
        repeat(100){
            (0+it).toString().run {
                withContext(Dispatchers.Main) {
                    cakeImageView.alpha = it / 100f
                    currentTextView.text = "Current opacity " + it
                }
                Thread.sleep(40)
            }
        }
    }



}