package android.example.assignment

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.ActionBar
import androidx.appcompat.app.AppCompatActivity

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        supportActionBar?.displayOptions = ActionBar.DISPLAY_SHOW_CUSTOM
        supportActionBar?.setCustomView(R.layout.action_bar_layout)
        val email = findViewById<View>(R.id.email) as TextView
        val password = findViewById<View>(R.id.password) as TextView
        val loginbtn = findViewById<View>(R.id.loginbtn) as Button
        loginbtn.setOnClickListener {
            if (android.util.Patterns.EMAIL_ADDRESS.matcher(email.text.toString()).matches()&&(password.text.toString().isNotEmpty()))
            {
                loginbtn.setOnClickListener{
                    val intent= Intent(this,HomeActivity::class.java)
                    startActivity(intent)
                } }
            else
                Toast.makeText(this@MainActivity, "LOGIN FAILED!", Toast.LENGTH_SHORT).show()
        }
    }
}
