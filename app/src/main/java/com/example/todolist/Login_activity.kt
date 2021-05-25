package com.example.todolist

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.EditText
import android.widget.Toast
import android.widget.Toolbar
import com.google.android.gms.auth.api.signin.GoogleSignInAccount
import com.google.android.gms.auth.api.signin.GoogleSignInOptions
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.GoogleAuthCredential

/**
 * Skeleton of an Android Things activity.
 *
 * Android Things peripheral APIs are accessible through the PeripheralManager
 * For example, the snippet below will open a GPIO pin and set it to HIGH:
 *
 * val manager = PeripheralManager.getInstance()
 * val gpio = manager.openGpio("BCM6").apply {
 *     setDirection(Gpio.DIRECTION_OUT_INITIALLY_LOW)
 * }
 * gpio.value = true
 *
 * You can find additional examples on GitHub: https://github.com/androidthings
 */
class Login_activity : AppCompatActivity() {
    var auth: FirebaseAuth = FirebaseAuth.getInstance();
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        supportActionBar?.hide()
        setContentView(R.layout.activity_login_activity)
    }

    fun oauthLogin(v: View) {
        Toast.makeText(this.baseContext, "Chưa làm", Toast.LENGTH_SHORT).show()
    }

    fun emailLogin(v: View) {
        var email = findViewById<EditText>(R.id.login_username)
        var password = findViewById<EditText>(R.id.login_password)
        auth.signInWithEmailAndPassword(email.text.toString(), password.text.toString())
    }

    fun anonymousLogin(v: View) {
        auth.signInAnonymously();
    }

    fun signUp(v: View) {
        Toast.makeText(this.baseContext, "Chưa làm", Toast.LENGTH_SHORT).show()
    }
}