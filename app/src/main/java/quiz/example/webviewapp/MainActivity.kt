package quiz.example.webviewapp

import android.content.ActivityNotFoundException
import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import android.net.ConnectivityManager
import android.net.Uri
import android.os.Build
import android.os.Bundle
import android.webkit.CookieManager
import android.webkit.WebResourceRequest
import android.webkit.WebView
import android.webkit.WebViewClient
import android.widget.TextView
import androidx.annotation.RequiresApi
import androidx.appcompat.app.AppCompatActivity
import com.google.firebase.remoteconfig.FirebaseRemoteConfig
import quiz.example.webviewapp.stub.News
import java.io.IOException


class MainActivity : AppCompatActivity() {

    // Declare the variables
    private lateinit var webView: WebView
    private lateinit var sharedPreferences: SharedPreferences
    private lateinit var editor: SharedPreferences.Editor
    private lateinit var firebaseRemoteConfig: FirebaseRemoteConfig

    @RequiresApi(Build.VERSION_CODES.M)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        // Проверка на подключение к интернету
        if (!isOnline()) {

            val intent = Intent(this, NotInternet::class.java)
            startActivity(intent)
            finish()
            return
        }else {
            // Get shared preferences
            sharedPreferences = getSharedPreferences("mySharedPreferences", Context.MODE_PRIVATE)
            editor = sharedPreferences.edit()

            webView = findViewById(R.id.webView)
            webView.webViewClient = WebViewClient()
            webView.settings.javaScriptEnabled = true

            webView.settings.javaScriptCanOpenWindowsAutomatically = true
            CookieManager.getInstance().setAcceptCookie(true)
            CookieManager.getInstance().setAcceptThirdPartyCookies(webView, true)
            val cookieManager = CookieManager.getInstance()
            cookieManager.setAcceptCookie(true)
            cookieManager.setAcceptThirdPartyCookies(webView, true)
            val cookieString = sharedPreferences.getString("myCookies", null)
            if (cookieString != null) {
                val link = webView.url ?: sharedPreferences.getString("myLink", null)
                cookieManager.setCookie(link, cookieString)
            }

            // проверка shared preferences
            val link = sharedPreferences.getString("myLink", null)


            if (link != null) {
                webView.loadUrl(link)
            } else {
                // Получение экземпляра FirebaseRemoteConfig для доступа к параметрам удаленной конфигурации.
                firebaseRemoteConfig = FirebaseRemoteConfig.getInstance()
                try {
                    // Извлекает и активирует конфигурацию из Firebase Remote Config
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

                            } else {
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


            webView.webViewClient = object : WebViewClient() {
                override fun shouldOverrideUrlLoading(
                    view: WebView?,
                    request: WebResourceRequest?
                ): Boolean {
                    val url = request?.url.toString()
                    if (url.startsWith("http:") || url.startsWith("https:")) {
                        view?.loadUrl(url)
                    } else {
                        try {
                            val intent = Intent(Intent.ACTION_VIEW, Uri.parse(url))
                            view?.context?.startActivity(intent)


                        } catch (e: ActivityNotFoundException) {
                            // Handle the exception as appropriate
                        }
                    }
                    return true
                }
            }
            webView.webViewClient = object : WebViewClient() {
                override fun onPageFinished(view: WebView?, url: String?) {
                    super.onPageFinished(view, url)
                    val cookies = cookieManager.getCookie(url)
                    editor.putString("myCookies", cookies)
                    editor.apply()
                }
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
    fun isOnline(): Boolean {
        val cm = getSystemService(CONNECTIVITY_SERVICE) as ConnectivityManager
        val activeNetwork = cm.activeNetworkInfo
        return activeNetwork != null && activeNetwork.isConnectedOrConnecting
    }

    override fun onBackPressed() {
        if (webView.canGoBack()) {
            webView.goBack()
        } else {

        }
    }
}