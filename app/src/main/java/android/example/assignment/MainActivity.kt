package android.example.assignment

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.ActionBar
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat.startActivity

class MainActivity : AppCompatActivity() {

    private lateinit var email: EditText
    private lateinit var password: EditText
    private lateinit var loginButton: Button

    private var isLoggedIn = false;
    private val IS_USER_LOGGED_IN = "isUserLoggedIn";
    private val LOGGED_USER_EMAIL = "logged_user_email";


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        email = findViewById(R.id.email)
        password = findViewById(R.id.password)
        loginButton = findViewById(R.id.loginbtn)

        isLoggedIn = getSharedPref()

        if (isLoggedIn) {
            var email = getEmailFromPref()

            if (email == null)
                email = "emptyUser"

            moveToHomeScreen(email)
        }


        loginButton.setOnClickListener {
            if (android.util.Patterns.EMAIL_ADDRESS.matcher(email.text.toString())
                    .matches() && (password.text.toString().isNotEmpty())
            ) {
                isLoggedIn = true;

                val email = email.text.toString();

                setSharedPref(isLoggedIn, email)
                moveToHomeScreen(email)

            } else {
                Toast.makeText(this@MainActivity, "LOGIN FAILED!", Toast.LENGTH_SHORT).show()
            }
        }

    }

    private fun moveToHomeScreen(email: String) {
        val intent = Intent(this, HomeActivity::class.java)
        intent.putExtra("user_email", email)
        startActivity(intent)
        finish()
    }

    private fun getSharedPref(): Boolean {
        val preference = getSharedPreferences("my_preference", Context.MODE_PRIVATE)
        return preference.getBoolean(IS_USER_LOGGED_IN, false);
    }

    private fun getEmailFromPref(): String? {
        val preference = getSharedPreferences("my_preference", Context.MODE_PRIVATE)
        return preference.getString(LOGGED_USER_EMAIL, "empty()");
    }

    private fun setSharedPref(isUserLoggedIn: Boolean, email: String) {
        val preference = getSharedPreferences("my_preference", Context.MODE_PRIVATE)
        val editor = preference.edit()
        editor.putBoolean(IS_USER_LOGGED_IN, isUserLoggedIn).apply()
        editor.putString(LOGGED_USER_EMAIL, email).apply()

        Log.i("MainActivity", "Session $isUserLoggedIn");
    }
}