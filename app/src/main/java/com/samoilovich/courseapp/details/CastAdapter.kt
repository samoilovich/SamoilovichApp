package com.samoilovich.courseapp.details

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.samoilovich.courseapp.R
import com.samoilovich.courseapp.databinding.ItemActorBinding

class CastAdapter(var actors: List<Actor>) : RecyclerView.Adapter<CastAdapter.ActorViewHolder>() {

    inner class ActorViewHolder(private val binding: ItemActorBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind(actor: Actor) {
            binding.actorAvatar.setImageResource(actor.avatar)
            binding.actorName.text = actor.name
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ActorViewHolder {
        return ActorViewHolder(ItemActorBinding.inflate(LayoutInflater.from(parent.context)))
    }

    override fun onBindViewHolder(holder: ActorViewHolder, position: Int) {
        holder.bind(actors[position])
    }

    override fun getItemCount(): Int = actors.size
}