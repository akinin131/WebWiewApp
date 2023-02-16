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

        val newsItem = NewsItem("Michigan State University gunman had two...", R.drawable.newsone,"Michigan State University gunman Anthony McRae had two handguns and multiple loaded...")

        adapter.setList(listOf(newsItem))

    }


}