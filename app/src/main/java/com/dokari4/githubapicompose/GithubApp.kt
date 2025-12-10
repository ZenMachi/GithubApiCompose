package com.dokari4.githubapicompose

import android.app.Application
import android.util.Log
import android.widget.Toast
import com.aheaditec.talsec_security.security.api.SuspiciousAppInfo
import com.aheaditec.talsec_security.security.api.Talsec
import com.aheaditec.talsec_security.security.api.TalsecConfig
import com.aheaditec.talsec_security.security.api.ThreatListener
import dagger.hilt.android.HiltAndroidApp

@HiltAndroidApp
open class GithubApp : Application(), ThreatListener.ThreatDetected {

    private val expectedPackageName = BuildConfig.PKG_NAME // Don't use Context.getPackageName!
    private val expectedSigningCertificateHashBase64 = arrayOf(
        BuildConfig.CERT_HASH_BASE64
    ) // Replace with your release (!) signing certificate hash(es)

    private val watcherMail = BuildConfig.EMAIL // for Alerts and Reports
    private val supportedAlternativeStores = arrayOf(
        "com.sec.android.app.samsungapps" // Add other stores, such as the Samsung Galaxy Store
    )
    private val isProd = true

    override fun onCreate() {
        super.onCreate()

        val config = TalsecConfig.Builder(
            expectedPackageName,
            expectedSigningCertificateHashBase64)
            .watcherMail(watcherMail)
            .supportedAlternativeStores(supportedAlternativeStores)
            .prod(isProd)
            .build()

        ThreatListener(this).registerListener(this)
        Talsec.start(this, config)
    }

    override fun onRootDetected() {
        Log.d("RootDetected", "Root Detected")
        Toast.makeText(this, "Root Detected", Toast.LENGTH_SHORT).show()
    }

    override fun onDebuggerDetected() {
        Log.d("DebuggerDetected", "Debugger Detected")
    }

    override fun onEmulatorDetected() {
        Log.d("EmulatorDetected", "Emulator Detected")
        Toast.makeText(this, "Emulator Detected", Toast.LENGTH_SHORT).show()
    }

    override fun onTamperDetected() {
        Log.d("TamperDetected", "Tamper Detected")
    }

    override fun onUntrustedInstallationSourceDetected() {
        Log.d("UntrustedInstallationSourceDetected", "Untrusted Installation Source Detected")
    }

    override fun onHookDetected() {
        Log.d("HookDetected", "Hook Detected")
    }

    override fun onDeviceBindingDetected() {
        Log.d("DeviceBindingDetected", "Device Binding Detected")
    }

    override fun onObfuscationIssuesDetected() {
        Log.d("ObfuscationIssuesDetected", "Obfuscation Issues Detected")
    }

    override fun onMalwareDetected(p0: MutableList<SuspiciousAppInfo>?) {
        Log.d("MalwareDetected", "Malware Detected")
    }

    override fun onScreenshotDetected() {
        Log.d("ScreenshotDetected", "Screenshot Detected")
    }

    override fun onScreenRecordingDetected() {
        Log.d("ScreenRecordingDetected", "Screen Recording Detected")
    }

    override fun onMultiInstanceDetected() {
        Log.d("MultiInstanceDetected", "Multi Instance Detected")
    }

}