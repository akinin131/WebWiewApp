package quiz.example.webviewapp.stub

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.navigation.NavController
import androidx.navigation.Navigation

import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch

import org.jsoup.Jsoup
import quiz.example.webviewapp.APPQ
import quiz.example.webviewapp.R
import quiz.example.webviewapp.adapter.NewsAdapter
import quiz.example.webviewapp.adapter.NewsItem


class News : AppCompatActivity() {

    lateinit var navController: NavController

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_news)
        APPQ = this
        navController = Navigation.findNavController(this, R.id.nav_fragment)

    }




}

