package com.example.notifyme

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.TextView

class ResultActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_result)

        // get the ID of the TextView and Button

        var textView = findViewById<TextView>(R.id.text_view)
        var button = findViewById<Button>(R.id.back)

        //create a variable to receive a message
        var receivedMessage: Bundle? = getIntent().getExtras()

           if (receivedMessage != null){
               val message = receivedMessage.getString("MESSAGE")
               textView.text = message
           }
       button.setOnClickListener {
           finish()
       }
    }
}

