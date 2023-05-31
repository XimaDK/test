package com.example.owngame

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.util.Log
import android.view.View
import android.widget.Button
import com.example.owngame.databinding.ActivityPlayerLobbyBinding

class PlayerLobby : AppCompatActivity() {

    private lateinit var playerLobby : ActivityPlayerLobbyBinding
    private lateinit var conn : Client
    private lateinit var handler: Handler




    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        playerLobby = ActivityPlayerLobbyBinding.inflate(layoutInflater)
        setContentView(playerLobby.root)
        val nickname = intent.getStringExtra("name")
        playerLobby.username.text = nickname.toString()
        conn = Client(playerLobby.viewInfo)
        Thread { conn.connectClient(9999) }.start()
        handler = Handler(Looper.getMainLooper())
        conn.setHandler(handler)

    }


    fun clickToAnswer(view: View) {
        val button = findViewById<Button>(R.id.clickToAnswer)
        button.setOnClickListener {
            val nickname = playerLobby.username.text.toString()
            conn.sendToHost("ClickToAnswer", nickname)

        }
    }
}