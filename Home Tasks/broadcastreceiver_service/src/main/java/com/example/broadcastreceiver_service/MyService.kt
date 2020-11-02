package com.example.broadcastreceiver_service

import android.app.Service
import android.content.Context
import android.content.Intent
import android.os.Environment
import android.os.IBinder
import androidx.core.app.NotificationCompat
import androidx.core.content.ContextCompat
import com.example.broadcastreceiver_service.STORAGE_TYPE.EXTERNAL
import com.example.broadcastreceiver_service.STORAGE_TYPE.INTERNAL
import com.example.broadcastreceiver_service.StorageManager.getStorageManager
import java.io.File
import java.io.FileWriter
import java.io.IOException
import java.util.concurrent.TimeUnit

private const val SERVICE_NOTIFICATION_ID = 1
private const val EXCEPTION_NOTIFICATION_ID = 2

class MyService : Service() {

    private val fileNameInternalStorage = "ActionRecords-InternalStorage.txt"
    private var internalFile: File? = null
    private lateinit var fileWriter: FileWriter

    private val fileNameExternalStorage = "ActionRecords-ExternalStorage.txt"
    private var externalFile: File? = null
    private var externalStorageVolumes: Array<out File>? = null
    private var primaryExternalStorage: File? = null

    private var generalizedFile: File? = null
    private lateinit var thread: Thread

    override fun onBind(intent: Intent?): IBinder? = null

    override fun onStartCommand(intent: Intent?, flags: Int, startId: Int): Int {
        val action = intent?.getStringExtra(ACTION_KEY)
        val date = intent?.getStringExtra(DATE_KEY)
        recordAction(date as String, action as String, startId)
        return START_REDELIVER_INTENT
    }

    override fun onDestroy() {
        super.onDestroy()
        stopForeground(true)
        if (thread.isAlive){
            thread.interrupt()
        }
    }

    @Synchronized private fun recordAction(date: String, action: String, startId: Int){
        thread = Thread(Runnable {
            createServiceNotification(baseContext)
            val content = "$date - $action \n"
            val storageManager = getStorageManager(applicationContext)
            val storageType = STORAGE_TYPE.valueOf(storageManager.loadStorageType())

            when (storageType) {
                INTERNAL -> initializeInternalStorage()
                EXTERNAL -> initializeExternalStorage()
            }

            writeToStorage(content)
            stopSelf(startId)
        })
        thread.start()
    }

    private fun initializeInternalStorage(){
        if (internalFile == null){
            try {
                internalFile = File(applicationContext.filesDir, fileNameInternalStorage)
            }catch (e: IOException){
                createExceptionNotification(baseContext)
            }
        }
        generalizedFile = internalFile
    }

    private fun initializeExternalStorage(){
        if (isExternalStorageWritable()){
            externalStorageVolumes = ContextCompat.getExternalFilesDirs(applicationContext, null)
            primaryExternalStorage = (externalStorageVolumes as Array<out File>)[0]

            if (externalFile == null){
                try {
                    externalFile = File(applicationContext.getExternalFilesDir(null), fileNameExternalStorage)
                }catch (e: IOException){
                    createExceptionNotification(baseContext)
                }
            }
        }else{
            createExceptionNotification(baseContext)
        }
        generalizedFile = externalFile
    }

    private fun isExternalStorageWritable(): Boolean {
        return Environment.getExternalStorageState() == Environment.MEDIA_MOUNTED
    }

    private fun writeToStorage(content: String){
        try{
            fileWriter = FileWriter(generalizedFile, true)
            fileWriter.write(content)
            fileWriter.flush()
            TimeUnit.SECONDS.sleep(5) // Это здесь для того, чтобы уведомление не пропало быстро
        }catch (e: IOException){
            createExceptionNotification(baseContext)
        }finally {
            try {
                fileWriter.close()
            }catch (e: IOException){
                createExceptionNotification(baseContext)
            }
        }
    }

    private fun createServiceNotification(context: Context){
        val notification = NotificationCompat.Builder(context, "CHANNEL")
                .setSmallIcon(R.drawable.icon_service_notification)
                .setContentTitle(getText(R.string.serviceNotificationTitle))
                .setContentText(getText(R.string.serviceNotificationText))
                .build()
        startForeground(SERVICE_NOTIFICATION_ID, notification)
    }

    private fun createExceptionNotification(context: Context){
        val notification = NotificationCompat.Builder(context, "CHANNEL")
                .setSmallIcon(R.drawable.icon_exception_notification)
                .setContentTitle(getText(R.string.serviceNotificationTitle))
                .setContentText(getText(R.string.exceptionNotificationText))
                .build()
        startForeground(EXCEPTION_NOTIFICATION_ID, notification)
    }
}