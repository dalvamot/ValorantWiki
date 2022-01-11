package com.vt.valorantwiki.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.squareup.picasso.Picasso
import com.vt.valorantwiki.R
import com.vt.valorantwiki.model.agents.Agent
import com.vt.valorantwiki.model.agents.Agents
import com.vt.valorantwiki.utils.AgentButtonClick

class AgentsAdapter(
    private val agentCardInfoClick: AgentButtonClick,
    private val listOfAgents: MutableList<Agent> = mutableListOf()
) : RecyclerView.Adapter<AgentsViewHolder>() {

    fun setAgents(agents: List<Agent>){
        listOfAgents.clear()
        listOfAgents.addAll(agents)
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): AgentsViewHolder {
        LayoutInflater.from(parent.context).inflate(R.layout.fragment_agent_cardview, parent, false).apply {
            return AgentsViewHolder(this)
        }
    }

    override fun onBindViewHolder(holder: AgentsViewHolder, position: Int) {
        val agent = listOfAgents[position]
        holder.setInformationToTheViewHolder(agent)

        holder.itemView.setOnClickListener {
            agentCardInfoClick.moveToAgentsFragment(agent.displayName)
        }
    }
    override fun getItemCount(): Int = listOfAgents.size
}

class AgentsViewHolder(itemView: View): RecyclerView.ViewHolder(itemView){
    val agentName: TextView = itemView.findViewById(R.id.agentName_textview)
    val agentRole: TextView = itemView.findViewById(R.id.agentRole_textview)
    val agentAvatar: ImageView = itemView.findViewById(R.id.agentAvatar_imageview)

    fun setInformationToTheViewHolder(agentItem: Agent){
        agentName.text = agentItem.displayName
        agentRole.text = agentItem.role?.displayName

        Picasso
            .get()
            .load(agentItem.bustPortrait)
            .into(agentAvatar)
    }
}