package quiz.example.webviewapp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch

import org.jsoup.Jsoup
import quiz.example.webviewapp.adapter.NewsAdapter
import quiz.example.webviewapp.adapter.NewsItem

class News : AppCompatActivity() {
    private lateinit var recyclerView: RecyclerView
    private lateinit var newsAdapter: NewsAdapter
    private val url = "https://www.foxnews.com/sports"
    private val newsItems = mutableListOf<NewsItem>()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_news)

        recyclerView = findViewById(R.id.recyclerView)
        newsAdapter = NewsAdapter()
        recyclerView.layoutManager = LinearLayoutManager(this)
        recyclerView.adapter = newsAdapter
        GlobalScope.launch {
            getRes()
        }


    }

    private fun getRes() {

        try {
            println("gwegfiwgf")
            val doc = Jsoup.connect(url).get()//
            val elements = doc.select("div[class=content article-list]")
            for (element in 0 until elements.size) {

                val title = elements.select("article.article")
                    .select("h4.title")
                    .select("a")
                    .eq(element)
                    .text()

                val subtitle = elements.select("div[class=info]")
                    .select(".content")
                    .select("p[class=dek]")
                    .select("a").eq(element)
                    .text()
                val imageUrl = elements.select("div[class=m]")
                    .select("img")
                    .eq(element)
                    .attr("abs:src")

                val newsItem = NewsItem(title, subtitle, imageUrl)

                println(title + "title")
                println(subtitle + "subtitle")
                println(imageUrl + "url")
                newsItems.add(newsItem)
            }

            GlobalScope.launch(Dispatchers.Main) {
                newsAdapter.setNewsList(newsItems)
            }

            /*val newsAdapter = NewsAdapter()
            newsAdapter.setNewsList(newsItems)
            recyclerView.adapter = newsAdapter*/

            newsAdapter.setNewsList(newsItems)
        } catch (e: Exception) {
            // Handle exception
        }


    }
}

