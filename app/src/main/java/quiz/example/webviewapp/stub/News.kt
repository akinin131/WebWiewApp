package quiz.example.webviewapp.stub

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.core.content.ContextCompat.startActivity
import androidx.navigation.NavController
import androidx.navigation.Navigation
import androidx.recyclerview.widget.RecyclerView
import quiz.example.webviewapp.APPQ
import quiz.example.webviewapp.R

import quiz.example.webviewapp.adapter.NewsItem
import quiz.example.webviewapp.adapter.NoteAdapter
import quiz.example.webviewapp.databinding.ActivityNewsBinding


class News : AppCompatActivity() {

    lateinit var binding: ActivityNewsBinding
    lateinit var navController: NavController


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityNewsBinding.inflate(layoutInflater)
        setContentView(binding.root)
        APPQ = this
        navController = Navigation.findNavController(this, R.id.nav_fragment)

    }


}

