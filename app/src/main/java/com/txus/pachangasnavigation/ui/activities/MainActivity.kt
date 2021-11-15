package com.txus.pachangasnavigation.ui.activities

import android.app.NotificationChannel
import android.app.NotificationManager
import android.graphics.Color
import android.os.Build
import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.NotificationCompat
import androidx.core.app.NotificationManagerCompat
import androidx.navigation.findNavController
import androidx.navigation.ui.setupWithNavController
import com.txus.pachangasnavigation.App
import com.txus.pachangasnavigation.R
import com.txus.pachangasnavigation.databinding.ActivityMainBinding
import com.txus.pachangasnavigation.listeners.MainListener

class MainActivity : AppCompatActivity(), MainListener {

    private val channelID = "channelID"
    private val channelName = "channelName"
    private val firestore = App.getFirestore()
    private val notificationId = 0


    private lateinit var binding: ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)


        binding = ActivityMainBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)

        val bottomNavigationView = binding.bottomNavView
        val navController = findNavController(R.id.nav_host_fragment)
        bottomNavigationView.setupWithNavController(navController)


    }

    override fun showBottomNavigation() {
        binding.bottomNavView.visibility = View.VISIBLE
    }

    override fun createNotificationChannel() {


        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {

            val importance = NotificationManager.IMPORTANCE_HIGH

            val channel = NotificationChannel(channelID, channelName, importance).apply {
                lightColor = Color.GREEN
                enableLights(true)
            }

            val manager = getSystemService(NOTIFICATION_SERVICE) as NotificationManager

            manager.createNotificationChannel(channel)
        }

        val notification = NotificationCompat.Builder(this, channelID).also {

            it.setContentTitle("Mensaje de Pachangas")
            it.setContentText("Tienes una partida completa ")
            it.setSmallIcon(R.drawable.ic_baseline_sports_baseball_24)
            it.setPriority(NotificationCompat.PRIORITY_HIGH)
        }.build()

        val notificationManager = NotificationManagerCompat.from(this)

        notificationManager.notify(notificationId, notification)

    }


}