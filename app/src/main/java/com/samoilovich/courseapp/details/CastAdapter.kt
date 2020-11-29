package com.samoilovich.courseapp.details

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.samoilovich.courseapp.R
import com.samoilovich.courseapp.databinding.ItemActorBinding

class CastAdapter(var actors: List<Actor>) : RecyclerView.Adapter<CastAdapter.ActorViewHolder>() {

    private var actorNamePlaceholder: String? = null

    inner class ActorViewHolder(private val binding: ItemActorBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind(actor: Actor) {
            binding.actorAvatar.setImageResource(actor.avatar)
            binding.actorName.text = actorNamePlaceholder?.format(actor.firstName, actor.secondName)
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ActorViewHolder {
        if (actorNamePlaceholder == null) {
            actorNamePlaceholder = parent.context.getString(R.string.actor_name_placeholder)
        }
        return ActorViewHolder(ItemActorBinding.inflate(LayoutInflater.from(parent.context)))
    }

    override fun onBindViewHolder(holder: ActorViewHolder, position: Int) {
        holder.bind(actors[position])
    }

    override fun getItemCount(): Int = actors.size
}