package com.example.exercise202

import android.app.NotificationChannel
import android.app.NotificationManager
import android.app.PendingIntent
import android.app.PendingIntent.FLAG_IMMUTABLE
import android.app.Service
import android.content.Intent
import android.os.Build
import android.os.Handler
import android.os.HandlerThread
import android.os.IBinder
import androidx.core.app.NotificationCompat
import androidx.core.content.ContextCompat


class WaterTrackingService : Service() {

    private var fluidBalanceMilliliters = 0f

    private lateinit var notificationBuilder:
            NotificationCompat.Builder

    private lateinit var serviceHandler: Handler

    override fun onCreate() {
        super.onCreate()
        notificationBuilder = startForegroundService()
        val handlerThread = HandlerThread("WaterTracking").apply { start() }
        serviceHandler = Handler(handlerThread.looper)
        updateFluidBalance()
    }

    override fun onStartCommand(intent: Intent?, flags: Int, startId: Int): Int {
        val returnValue = super.onStartCommand(intent, flags, startId)

        val intakeAmountMilliliters = intent?.getFloatExtra(EXTRA_INTAKE_AMOUNT_MILLILITERS, 0f)
        intakeAmountMilliliters?.let {
            addToFluidBalance(it)
        }

        return returnValue
    }

    override fun onDestroy() {
        serviceHandler.removeCallbacksAndMessages(null)
    }

    override fun onBind(p0: Intent?): IBinder? = null

    private fun getPendingIntent(): PendingIntent {
        val flag = if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.S) FLAG_IMMUTABLE else 0
        return PendingIntent.getActivity(
            this, 0, Intent(
                this,
                MainActivity::class.java
            ), flag
        )
    }

    private fun addToFluidBalance(amountMillilitres: Float) {
        synchronized(this) {
            fluidBalanceMilliliters += amountMillilitres
        }
    }

    private fun updateFluidBalance() {
        serviceHandler.postDelayed({
            updateFluidBalance()
            addToFluidBalance(-0.144f)
            notificationBuilder.setContentText(
                "Your fluid balance: %.2f".format(fluidBalanceMilliliters)
            )
            startForeground(NOTIFICATION_ID, notificationBuilder.build())
        }, 5000L)
    }

    private fun createNotificationChannel(): String =
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            val newChannelId = "FluidBalanceTracking"
            val channelName = "Fluid Balance Tracking"
            val channel = NotificationChannel(
                newChannelId, channelName,
                NotificationManager.IMPORTANCE_DEFAULT
            )
            val service = requireNotNull(
                ContextCompat.getSystemService(this, NotificationManager::class.java)
            )
            service.createNotificationChannel(channel)
            newChannelId
        } else { "" }

    private fun getNotificationBuilder(pendingIntent: PendingIntent, channelId: String) =
        NotificationCompat.Builder(this, channelId)
            .setContentTitle("Tracking your fluid balance")
            .setContentText("Tracking")
            .setSmallIcon(R.drawable.ic_launcher_foreground)
            .setContentIntent(pendingIntent)
            .setTicker("Fluid balance tracking started")

    private fun startForegroundService(): NotificationCompat.Builder {
        val pendingIntent = getPendingIntent()
        val channelId = if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            createNotificationChannel()
        } else { "" }

        val notificationBuilder = getNotificationBuilder(pendingIntent, channelId)
        startForeground(NOTIFICATION_ID, notificationBuilder.build())
        return notificationBuilder
    }

    companion object {
        const val NOTIFICATION_ID = 0x3A7A
        const val EXTRA_INTAKE_AMOUNT_MILLILITERS = "intake"
    }
}