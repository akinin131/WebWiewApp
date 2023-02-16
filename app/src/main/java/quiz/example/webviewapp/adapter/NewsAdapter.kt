package quiz.example.webviewapp.adapter


import android.annotation.SuppressLint
import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.navigation.findNavController
import androidx.recyclerview.widget.RecyclerView
import quiz.example.webviewapp.APPQ
import quiz.example.webviewapp.R
import quiz.example.webviewapp.databinding.ItemBinding
import quiz.example.webviewapp.stub.DetailsFragment.MyClass.Companion.EXTRA_URL
import java.util.*
import android.content.res.Resources
import quiz.example.webviewapp.R.*


class NoteAdapter : RecyclerView.Adapter<NoteAdapter.NoteViewHolder>() {

    var ListNote = Collections.emptyList<NewsItem>()


    class NoteViewHolder (view:View):RecyclerView.ViewHolder(view){
        private val binding = ItemBinding.bind(view)

        init {
            view.setOnClickListener{
                val bundle = Bundle()
                bundle.putString("Link","link")

                itemView.findNavController().navigate(id.action_blankFragment2_to_detailFragment)
                val extraURL = itemView.getContext().getString(R.string.new_one_title);

                EXTRA_URL= extraURL
            }
        }

        fun bind(note: NewsItem, position: Int) {
            binding.apply{
                binding.textTitle.text = note.title.toString()
                binding.textRandom.text = note.subtitle
                binding.aImage.setImageResource(note.imageUrl)
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): NoteViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(layout.item, parent,false)
        return NoteViewHolder(view)


    }

    override fun onBindViewHolder(holder: NoteViewHolder, position: Int) {
        holder.bind(ListNote [position], position)
        println(position)

    }

    override fun getItemCount(): Int {
        return ListNote.size
    }

    @SuppressLint("NotifyDataSetChanged")
    fun setList(list: List<NewsItem>) {
        ListNote = list
        notifyDataSetChanged()
    }

  fun onViewAttachedToWindow(holder: NoteViewHolder, position: Int) {

        super.onViewAttachedToWindow(holder)
        holder.bind(ListNote [position], position)
        holder.itemView.setOnClickListener {
            //StartFragment.clickNote(ListNote[holder.adapterPosition])

        }
    }


    override fun onViewDetachedFromWindow(holder: NoteViewHolder) {
        holder.itemView.setOnClickListener(null)
    }
}