package ru.lukaville.dockerui.ui.android.registry

import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import ru.lukaville.dockerui.R
import ru.lukaville.dockerui.entities.registry.Registry
import ru.lukaville.dockerui.ui.android.core.OnItemClickListener
import ru.lukaville.dockerui.util.bindView

class RegistryAdapter : RecyclerView.Adapter<RegistryAdapter.RegistryViewHolder>() {

    var itemClickListener: OnItemClickListener? = null

    var registries: MutableList<Registry> = arrayListOf()
        set(s: MutableList<Registry>) {
            field = s
            notifyDataSetChanged()
        }

    override fun onBindViewHolder(holder: RegistryViewHolder, position: Int) {
        val registry = registries[position]

        holder.title.text = registry.name
        holder.subtitle.text = registry.url
        holder.icon.setImageResource(R.mipmap.ic_launcher)
    }

    override fun getItemCount(): Int {
        return registries.size
    }

    override fun onCreateViewHolder(parent: ViewGroup?, viewType: Int): RegistryViewHolder {
        val view = LayoutInflater.from(parent?.context).inflate(R.layout.item_registry, parent, false)
        return RegistryViewHolder(view)
    }

    inner class RegistryViewHolder(val view: View) : RecyclerView.ViewHolder(view) {
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