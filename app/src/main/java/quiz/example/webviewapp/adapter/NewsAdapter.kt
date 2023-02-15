package quiz.example.webviewapp.adapter

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.navigation.findNavController
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import quiz.example.webviewapp.R


class NewsAdapter : RecyclerView.Adapter<NewsAdapter.NewsViewHolder>() {
    private val newsItems = mutableListOf <NewsItem>()

    class NewsViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val titleTextView: TextView = itemView.findViewById(R.id.titleTextView)
        val subtitleTextView: TextView = itemView.findViewById(R.id.subtitleTextView)
        val imageView: ImageView = itemView.findViewById(R.id.imageView)
        var link = "grtgrgregre"

        init {
            itemView.setOnClickListener {
                val bundle = Bundle()
                bundle.putString("Link",link)
                println(link)
                println("ЭТО ЛИИИИИИНК!!!!!!!!!!!!")

                itemView.findNavController().navigate(R.id.action_firstFragment_to_secondFragment,bundle)


            }
        }

        fun bind(newsItem: NewsItem){
            titleTextView.text=newsItem.title
            subtitleTextView.text=newsItem.subtitle
            Glide.with(itemView.context)
                .load(newsItem.imageUrl)
                .into(imageView)
            link=newsItem.DetailsUrl
        }
    }
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): NewsViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item, parent, false)
        return NewsViewHolder(view)
    }
    override fun onBindViewHolder(holder: NewsViewHolder, position: Int) {

        holder.bind(newsItems[position])

       /* val item = newsItems[position]
        holder.titleTextView.text = item.title
        holder.subtitleTextView.text = item.subtitle
       */
    }

    override fun getItemCount(): Int {
        return newsItems.size
    }

    fun setNewsList(list: List<NewsItem>) {
        newsItems.clear()
        newsItems.addAll(list)
        notifyDataSetChanged()
    }

}


