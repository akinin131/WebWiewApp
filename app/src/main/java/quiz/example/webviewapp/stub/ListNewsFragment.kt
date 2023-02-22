package quiz.example.webviewapp.stub

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.NavController
import androidx.recyclerview.widget.RecyclerView
import quiz.example.webviewapp.R
import quiz.example.webviewapp.adapter.NewsItem
import quiz.example.webviewapp.adapter.NoteAdapter
import quiz.example.webviewapp.databinding.FragmentBlank2Binding


class ListNewsFragment : Fragment() {



    lateinit var recyclerView: RecyclerView
    lateinit var navController: NavController
    lateinit var binding: FragmentBlank2Binding
    lateinit var adapter: NoteAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = FragmentBlank2Binding.inflate(layoutInflater, container, false)
        return binding.root
    }

    companion object {

        @JvmStatic
        fun newInstance(param1: String, param2: String) =
            ListNewsFragment().apply {

            }
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        init()
    }
    private fun init() {

        recyclerView = binding.recyclerViewHome
        adapter = NoteAdapter()
        recyclerView.adapter = adapter

        val newsItem = NewsItem("Gisele Bündchen lets loose during Carnival in...", R.drawable.fon2,"Gisele Bündchen exuded happiness while celebrating Brazil's Carnival...")
        val newsItem1 = NewsItem("Ex-NFL player Greg Hardy knocked out cold in...", R.drawable.fon3,"Former NFL defensive end Greg Hardy has taken up a new...")
        val newsItem2 = NewsItem("LSU's Olivia Dunne shuts down fan after weird...", R.drawable.fon4,"LSU gymnast Olivia Dunne has had to go to battle on all fronts this...")
        val newsItem3 = NewsItem("Ex-Bills punter Matt Araiza not playing...", R.drawable.fon5,"Former Buffalo Bills punter Matt Araiza, who was released from the team...")
        val newsItem4 = NewsItem("NBA All-Star Game 'is the worst basketball...", R.drawable.fon6,"The NBA All-Star Game did not appear to sit well with Denver Nuggets...")
        val newsItem5 = NewsItem("Jonathan Toews, who helped Blackhawks to 3...", R.drawable.fon7,"Jonathan Toews helped bring three Stanley Cup championships to the Chicago...")
        val newsItem6 = NewsItem("Georgia Bulldogs player sounds off after White...", R.drawable.fon8,"Kirby Smart has built a dynasty in Athens, Georgia...")
        val newsItems = listOf(newsItem, newsItem1,newsItem2,newsItem3 ,newsItem4, newsItem5,newsItem6)
        adapter.setList(newsItems)
            //adapter.setList(listOf(newsItem1))
    }
}