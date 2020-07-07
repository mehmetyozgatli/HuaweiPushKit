package com.huawei.expertisepushkit

import android.os.Bundle
import android.text.TextUtils
import android.util.Log
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.FragmentActivity
import com.huawei.agconnect.config.AGConnectServicesConfig
import com.huawei.hmf.tasks.OnCompleteListener
import com.huawei.hmf.tasks.Task
import com.huawei.hms.aaid.HmsInstanceId
import com.huawei.hms.push.HmsMessaging
import com.onesignal.OneSignal


class MainActivity : AppCompatActivity() {

    private var pushTokenText: String? = null
    private var subscribeButton: Button? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        // One Signal
        OneSignal.startInit(this)
            .inFocusDisplaying(OneSignal.OSInFocusDisplayOption.Notification)
            .unsubscribeWhenNotificationsAreDisabled(true)
            .init()

        // Get Huawei Push Token
        getToken()

        subscribeButton = findViewById(R.id.subscribeButton)
        subscribeButton?.setOnClickListener {
            subscribe("testSubscribe")
        }

    }

    private fun subscribe(topic: String?) {
        try {
            HmsMessaging.getInstance(this).subscribe(topic)
                .addOnCompleteListener { task ->
                    if (task.isSuccessful) {
                        Log.i("Subscribe", "subscribe Complete")
                    } else {
                        Log.e("Subscribe", "subscribe failed: ret=" + task.exception.message)
                    }
                }
        } catch (e: java.lang.Exception) {
            Log.e("Subscribe", "subscribe failed: exception=" + e.message)
        }
    }

    private fun getToken() {
        object : Thread() {
            override fun run() {
                try {
                    val appId: String =
                        AGConnectServicesConfig.fromContext(this@MainActivity)
                            .getString("client/app_id")
                    pushTokenText =
                        HmsInstanceId.getInstance(this@MainActivity).getToken(appId, "HCM")

                    if (!TextUtils.isEmpty(pushTokenText)) {
                        Log.i("Push Token", "token:$pushTokenText")
                    }
                } catch (e: Exception) {
                    Log.i("Push Token Error", "getToken failed, $e")
                }
            }
        }.start()
    }
}