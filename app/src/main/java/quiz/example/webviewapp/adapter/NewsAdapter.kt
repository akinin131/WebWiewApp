package quiz.example.webviewapp.adapter


import android.annotation.SuppressLint
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.findNavController
import androidx.recyclerview.widget.RecyclerView
import quiz.example.webviewapp.R
import quiz.example.webviewapp.databinding.ItemBinding

import java.util.*
import quiz.example.webviewapp.R.*


class NoteAdapter : RecyclerView.Adapter<NoteAdapter.NoteViewHolder>() {

    var ListNote = Collections.emptyList<NewsItem>()


    class NoteViewHolder (view: View): RecyclerView.ViewHolder(view){
        private val binding = ItemBinding.bind(view)

        init {
            view.setOnClickListener{
                val position = adapterPosition
                if (position == 0){
                    val bundle = Bundle()
                    val extraURL = itemView.getContext().getString(R.string.new_one_title);
                    val extraURLTw0 = itemView.getContext().getString(R.string.new_one_desc);
                    bundle.putInt("imageResourceId", R.drawable.fon2)
                    bundle.putString("title", extraURL)
                    bundle.putString("titledesc", extraURLTw0)
                    itemView.findNavController().navigate(R.id.action_blankFragment2_to_detailFragment, bundle)
                }

                if (position == 1){
                    val bundle = Bundle()
                    val extraURL = itemView.getContext().getString(R.string.new_two_title);
                    val extraURLTw0 = itemView.getContext().getString(R.string.new_two_desc);
                    bundle.putInt("imageResourceId", R.drawable.fon3)
                    bundle.putString("title", extraURL)
                    bundle.putString("titledesc", extraURLTw0)
                    itemView.findNavController().navigate(R.id.action_blankFragment2_to_detailFragment, bundle)
                }
                if (position == 2){
                    val bundle = Bundle()
                    val extraURL = itemView.getContext().getString(R.string.new_three_title);
                    val extraURLTw0 = itemView.getContext().getString(R.string.new_three_desc);
                    bundle.putInt("imageResourceId", R.drawable.fon4)
                    bundle.putString("title", extraURL)
                    bundle.putString("titledesc", extraURLTw0)
                    itemView.findNavController().navigate(R.id.action_blankFragment2_to_detailFragment, bundle)
                }
                if (position == 3){
                    val bundle = Bundle()
                    val extraURL = itemView.getContext().getString(R.string.new_four_title);
                    val extraURLTw0 = itemView.getContext().getString(R.string.new_four_desc);
                    bundle.putInt("imageResourceId", R.drawable.fon5)
                    bundle.putString("title", extraURL)
                    bundle.putString("titledesc", extraURLTw0)
                    itemView.findNavController().navigate(R.id.action_blankFragment2_to_detailFragment, bundle)
                }
                if (position == 4){
                    val bundle = Bundle()
                    val extraURL = itemView.getContext().getString(R.string.new_five_title);
                    val extraURLTw0 = itemView.getContext().getString(R.string.new_five_desc);
                    bundle.putInt("imageResourceId", R.drawable.fon6)
                    bundle.putString("title", extraURL)
                    bundle.putString("titledesc", extraURLTw0)
                    itemView.findNavController().navigate(R.id.action_blankFragment2_to_detailFragment, bundle)
                }
                if (position == 5){
                    val bundle = Bundle()
                    val extraURL = itemView.getContext().getString(R.string.new_six_title);
                    val extraURLTw0 = itemView.getContext().getString(R.string.new_six_desc);
                    bundle.putInt("imageResourceId", R.drawable.fon7)
                    bundle.putString("title", extraURL)
                    bundle.putString("titledesc", extraURLTw0)
                    itemView.findNavController().navigate(R.id.action_blankFragment2_to_detailFragment, bundle)
                }
                if (position == 6){
                    val bundle = Bundle()
                    val extraURL = itemView.getContext().getString(R.string.new_seven_title);
                    val extraURLTw0 = itemView.getContext().getString(R.string.new_seven_desc);
                    bundle.putInt("imageResourceId", R.drawable.fon9)
                    bundle.putString("title", extraURL)
                    bundle.putString("titledesc", extraURLTw0)
                    itemView.findNavController().navigate(R.id.action_blankFragment2_to_detailFragment, bundle)
                }

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