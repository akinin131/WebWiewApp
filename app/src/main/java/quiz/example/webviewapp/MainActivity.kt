package quiz.example.webviewapp

import android.annotation.SuppressLint
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.webkit.WebView
import android.webkit.WebViewClient
import android.widget.TextView
import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import com.google.firebase.remoteconfig.FirebaseRemoteConfig

class MainActivity : AppCompatActivity() {

    // Declare the variables
    private lateinit var webView: WebView
    private lateinit var sharedPreferences: SharedPreferences
    private lateinit var editor: SharedPreferences.Editor
    private lateinit var firebaseRemoteConfig: FirebaseRemoteConfig

    @SuppressLint("MissingInflatedId")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        webView = findViewById(R.id.webView)
        webView.webViewClient = WebViewClient()
        webView.settings.javaScriptEnabled = true



        // Get shared preferences
        sharedPreferences = getSharedPreferences("mySharedPreferences", Context.MODE_PRIVATE)
        editor = sharedPreferences.edit()

        // Check if link exists in shared preferences
        val link = sharedPreferences.getString("myLink", null)
        if (link != null) {
            webView.loadUrl(link)
        } else {
            firebaseRemoteConfig = FirebaseRemoteConfig.getInstance()
            try {
                // Load the link from Firebase Remote Config and save it in shared preferences
                firebaseRemoteConfig.fetchAndActivate().addOnCompleteListener(this) { task ->
                    if (task.isSuccessful) {
                        val url = firebaseRemoteConfig.getString("myLink")
                        if (url.isNotEmpty()) {
                            webView.loadUrl(url)
                            editor.putString("myLink", url)
                            editor.apply()
                        }else{
                            val intent = Intent(this, News::class.java)
                            startActivity(intent)
                            finish()
                        }
                    } else {
                        // Handle error
                        val error = task.exception
                        // Show error screen
                        // For example:
                        setContentView(R.layout.activity_error)
                        val errorTextView = findViewById<TextView>(R.id.errorTextView)
                        errorTextView.text = error.toString()
                    }
                }
            } catch (e: Exception) {
                // Handle error
                // Show error screen
                // For example:
                setContentView(R.layout.activity_error)
                val errorTextView = findViewById<TextView>(R.id.errorTextView)
                errorTextView.text = e.toString()
            }
        }
    }

    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
        webView.saveState(outState)
    }

    override fun onRestoreInstanceState(savedInstanceState: Bundle) {
        super.onRestoreInstanceState(savedInstanceState)
        webView.restoreState(savedInstanceState)
    }
}