package quiz.example.webviewapp.stub

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import quiz.example.webviewapp.R
import quiz.example.webviewapp.databinding.FragmentDetailsBinding
import quiz.example.webviewapp.stub.DetailsFragment.MyClass.Companion.EXTRA_URL


class DetailsFragment : Fragment() {
    // TODO: Rename and change types of parameters

    private lateinit var binding: FragmentDetailsBinding

    class MyClass {
        companion object {
            var EXTRA_URL = "PhotoActivity.EXTRA_URL"
        }
    }
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = FragmentDetailsBinding.inflate(layoutInflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.link.text = EXTRA_URL
    }

}