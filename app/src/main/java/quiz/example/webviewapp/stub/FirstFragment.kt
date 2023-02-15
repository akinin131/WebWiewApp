package quiz.example.webviewapp.stub

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import kotlinx.coroutines.DelicateCoroutinesApi
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import org.jsoup.Jsoup
import quiz.example.webviewapp.R
import quiz.example.webviewapp.adapter.NewsAdapter
import quiz.example.webviewapp.adapter.NewsItem
import quiz.example.webviewapp.databinding.FragmentFirstBinding


class FirstFragment : Fragment() {

    lateinit var binding: FragmentFirstBinding
    private lateinit var recyclerView: RecyclerView
    private lateinit var newsAdapter: NewsAdapter
    private val url = "https://www.foxnews.com/sports"
    private val url1 = "https://www.foxnews.com"
    private val newsItems = mutableListOf<NewsItem>()


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = FragmentFirstBinding.inflate(layoutInflater, container, false)
        return binding.root
    }
    @OptIn(DelicateCoroutinesApi::class)
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        recyclerView = binding.recyclerView
        newsAdapter = NewsAdapter()
        recyclerView.layoutManager = LinearLayoutManager(context)
        recyclerView.adapter = newsAdapter
        GlobalScope.launch {
            getRes()
        }

    }
    private fun getRes() {

        try {
            println("gwegfiwgf")
            val doc = Jsoup.connect(url).get()//
            val doc1 = Jsoup.connect(url1).get()//
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

                val urlDetails = doc1.baseUri() + elements.select("article.article")
                    .select("h4.title")
                    .select("a")
                    .eq(element)
                    .attr("href")

                val newsItem = NewsItem(title, subtitle, imageUrl,urlDetails)

                println(urlDetails)
                println("url")
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