package ru.lukaville.dockerui.ui.android.image

import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import ru.lukaville.dockerui.R
import ru.lukaville.dockerui.entities.Image
import ru.lukaville.dockerui.ui.android.core.OnItemClickListener
import ru.lukaville.dockerui.util.bindView
import ru.lukaville.dockerui.util.getRandomColor

class ImageAdapter : RecyclerView.Adapter<ImageAdapter.ImageViewHolder>() {

    var itemClickListener: OnItemClickListener? = null

    var images: MutableList<Image> = arrayListOf()
        set(s: MutableList<Image>) {
            field = s
            notifyDataSetChanged()
        }

    override fun onBindViewHolder(holder: ImageViewHolder, position: Int) {
        val repository = images[position]

        holder.title.text = repository.name
        holder.subtitle.text = repository.tags.joinToString()
        holder.icon.setImageResource(R.drawable.ic_photo_black_24dp)
        holder.icon.setColorFilter(getRandomColor(repository.name))
    }

    override fun getItemCount(): Int {
        return images.size
    }

    override fun onCreateViewHolder(parent: ViewGroup?, viewType: Int): ImageViewHolder {
        val view = LayoutInflater.from(parent?.context).inflate(R.layout.item_repository, parent, false)
        return ImageViewHolder(view)
    }

    inner class ImageViewHolder(val view: View) : RecyclerView.ViewHolder(view) {
        val title: TextView by bindView(R.id.title)
        val subtitle: TextView by bindView(R.id.subtitle)
        val icon: ImageView by bindView(R.id.icon)

        init {
            if (itemClickListener != null) {
                view.setOnClickListener {
                    itemClickListener?.onItemClicked(adapterPosition, view)
                }
            }
        }
    }
}