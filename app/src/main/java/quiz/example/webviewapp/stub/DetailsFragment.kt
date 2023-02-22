package quiz.example.webviewapp.stub

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import quiz.example.webviewapp.R
import quiz.example.webviewapp.databinding.FragmentDetailsBinding



class DetailsFragment : Fragment() {
    private lateinit var binding: FragmentDetailsBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentDetailsBinding.inflate(layoutInflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val title = arguments?.getString("title")
        val title1 = arguments?.getString("titledesc")
        val subtitle = arguments?.getString("subtitle")
        val imageUrl = arguments?.getInt("imageUrl")
        val imageResourceId = arguments?.getInt("imageResourceId")
        (imageResourceId ?: imageUrl)?.let { binding.linkUrl.setImageResource(it) }
        //binding.textTitle.text = title
        binding.link.text=title
        binding.linkTwo.text=title1

    }
}