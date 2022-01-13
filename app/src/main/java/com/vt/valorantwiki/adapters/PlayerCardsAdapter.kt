package com.vt.valorantwiki.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.squareup.picasso.Picasso
import com.vt.valorantwiki.R
import com.vt.valorantwiki.model.playercards.PlayerCard
import com.vt.valorantwiki.utils.PlayerCardsButtonClick

class PlayerCardsAdapter(
    private val listOfCards: MutableList<PlayerCard> = mutableListOf()
): RecyclerView.Adapter<PlayerCardsViewHolder>() {

    fun setCards(cards: List<PlayerCard>){
        listOfCards.clear()
        listOfCards.addAll(cards)
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PlayerCardsViewHolder {
        LayoutInflater.from(parent.context).inflate(R.layout.playercard_item, parent, false).apply {
            return PlayerCardsViewHolder(this)
        }
    }

    override fun onBindViewHolder(holder: PlayerCardsViewHolder, position: Int) {
        val card = listOfCards[position]
        holder.setInformationToTheViewHolder(card)

    }
    override fun getItemCount(): Int = listOfCards.size

}

class PlayerCardsViewHolder(itemview: View): RecyclerView.ViewHolder(itemview){
    val playerCardName: TextView = itemview.findViewById(R.id.playerCardTitle_textview)
    val playerCard: ImageView = itemview.findViewById(R.id.playerCard_imageview)

    fun setInformationToTheViewHolder(playerCardItem: PlayerCard){
        playerCardName.text = playerCardItem.displayName

        Picasso
            .get()
            .load(playerCardItem.largeArt)
            .into(playerCard)
    }


}