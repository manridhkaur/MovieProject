package com.mandrid.omdb

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.chip.Chip
import com.google.android.material.dialog.MaterialAlertDialogBuilder
import com.squareup.picasso.Picasso

class MovieAdapter(var list: List<Movie>, var movieInteraction: OnMovieInteraction): RecyclerView.Adapter<MovieAdapter.VH>() {


    fun updateMovieList(list: List<Movie>) {
        this.list = list
        notifyDataSetChanged()
    }

    inner class VH(val view: View): RecyclerView.ViewHolder(view) {

        fun bind(movieModelItem: Movie, onMovieInteraction: OnMovieInteraction) {
            view.findViewById<TextView>(R.id.tvTitle).text =movieModelItem.title
            view.findViewById<TextView>(R.id.tvSubtitle).text = movieModelItem.type
            view.findViewById<Chip>(R.id.criticRating).text = movieModelItem.year.toString()

            if(movieModelItem.poster.isNotEmpty()) {
                 Picasso.get().load(movieModelItem.poster).into(view.findViewById<ImageView>(R.id.ivImage))
                 }
            view.rootView.setOnClickListener {
                onMovieInteraction.onClick(movieModelItem)
            }

            view.findViewById<Button>(R.id.btnFav).setOnClickListener {

                MaterialAlertDialogBuilder(view.context).
                    setTitle("You sure you want to add to favorite ?")
                    .setPositiveButton("Yes") { _, _ ->
                        onMovieInteraction.onFavorite(movieModelItem)
                    }
                    .setNegativeButton("No") { _, _ ->

                    }.show()



            }
        }

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): VH {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.rv_item,parent,false)
        return VH(view)
    }

    override fun getItemCount(): Int {
        return list.size
    }

    fun deleteItem(model: Movie) {
        notifyItemRemoved(list.indexOf(model))
    }

    override fun onBindViewHolder(holder: VH, position: Int) {
        return holder.bind(list[position], movieInteraction)
    }
}

interface OnMovieInteraction {
    fun onClick(movie: Movie)
    fun onFavorite(movie: Movie)

    fun onAdd(movie: Movie)
}