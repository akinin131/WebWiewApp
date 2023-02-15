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
import quiz.example.webviewapp.stub.News

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
            // Получение экземпляра FirebaseRemoteConfig для доступа к параметрам удаленной конфигурации.
            firebaseRemoteConfig = FirebaseRemoteConfig.getInstance()
            try {
                // // Извлекает и активирует конфигурацию из Firebase Remote Config
                firebaseRemoteConfig.fetchAndActivate().addOnCompleteListener(this) { task ->
                    // Проверка, была ли задача выполнена успешно
                    if (task.isSuccessful) {
                        // Получить URL-адрес из удаленной конфигурации
                        val url = firebaseRemoteConfig.getString("myLink")
                        // Проверка, не является ли URL пустым
                        if (url.isNotEmpty()) {
                            // Загрузка URL-адрес в WebView
                            webView.loadUrl(url)
                            // Сохранение URL-адрес в SharedPreferences
                            editor.putString("myLink", url)
                            editor.apply()

                        }else{
                            // Открыть заглушку если firebase возвращает пустое значение
                            val intent = Intent(this, News::class.java)
                            startActivity(intent)
                            finish()
                        }
                    } else {

                        val error = task.exception

                        setContentView(R.layout.activity_error)
                        val errorTextView = findViewById<TextView>(R.id.errorTextView)
                        errorTextView.text = error.toString()
                    }
                }
            } catch (e: Exception) {
                // Показать экран ошибки
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