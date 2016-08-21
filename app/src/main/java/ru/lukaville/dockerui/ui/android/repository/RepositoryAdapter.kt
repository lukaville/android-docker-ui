package ru.lukaville.dockerui.ui.android.repository

import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import ru.lukaville.dockerui.R
import ru.lukaville.dockerui.entities.Repository
import ru.lukaville.dockerui.ui.android.core.OnItemClickListener
import ru.lukaville.dockerui.util.bindView
import ru.lukaville.dockerui.util.getRandomColor

class RepositoryAdapter : RecyclerView.Adapter<RepositoryAdapter.RepositoryViewHolder>() {

    var itemClickListener: OnItemClickListener? = null

    var repositories: MutableList<Repository> = arrayListOf()
        set(s: MutableList<Repository>) {
            field = s
            notifyDataSetChanged()
        }

    override fun onBindViewHolder(holder: RepositoryViewHolder, position: Int) {
        val repository = repositories[position]

        holder.title.text = repository.name
        holder.subtitle.text = repository.tags.joinToString()
        holder.icon.setImageResource(R.drawable.ic_photo_black_24dp)
        holder.icon.setColorFilter(getRandomColor(repository.name))
    }

    override fun getItemCount(): Int {
        return repositories.size
    }

    override fun onCreateViewHolder(parent: ViewGroup?, viewType: Int): RepositoryViewHolder {
        val view = LayoutInflater.from(parent?.context).inflate(R.layout.item_repository, parent, false)
        return RepositoryViewHolder(view)
    }

    inner class RepositoryViewHolder(val view: View) : RecyclerView.ViewHolder(view) {
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