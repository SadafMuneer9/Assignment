package android.example.assignment

import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.Toast
import androidx.core.content.edit

class HomeActivity : AppCompatActivity() {

    lateinit var logoutBtn: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_home)

        val loggedInEmail = intent.getStringExtra("user_email")
        Toast.makeText(this, loggedInEmail, Toast.LENGTH_SHORT).show()

        logoutBtn = findViewById<View>(R.id.logoutbtn) as Button

        logoutBtn.setOnClickListener {
            clearPrefData()
            moveBackToLogin()

            val logoutbtn = findViewById<View>(R.id.logoutbtn) as Button

            logoutbtn.setOnClickListener {
                val intent = Intent(this, MainActivity::class.java)
                startActivity(intent)
                finish()
            }
        }

    }

    private fun clearPrefData() {
        val preferences = getSharedPreferences("my_preference", Context.MODE_PRIVATE);
        preferences.edit().clear().apply()
    }

    private fun moveBackToLogin() {

        val intent = Intent(this, MainActivity::class.java)
        startActivity(intent)
        finish()

    }
}