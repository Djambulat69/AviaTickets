package com.isaev.main

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView.ViewHolder
import com.isaev.common.Offer
import com.isaev.common.formatPrice

class FlyCardAdapter :
    ListAdapter<Offer, FlyCardAdapter.FlyCardViewHolder>(OfferDiffUtilItemCallback) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): FlyCardViewHolder {

        val view = LayoutInflater.from(parent.context).inflate(R.layout.fly_card, parent, false)

        return FlyCardViewHolder(view)
    }

    override fun onBindViewHolder(holder: FlyCardViewHolder, position: Int) {
        holder.bind(getItem(position))
    }

    class FlyCardViewHolder(itemView: View) : ViewHolder(itemView) {

        private val image = itemView.findViewById<ImageView>(R.id.fly_card_image)
        private val title = itemView.findViewById<TextView>(R.id.fly_card_title)
        private val city = itemView.findViewById<TextView>(R.id.fly_card_city)
        private val price = itemView.findViewById<TextView>(R.id.fly_card_price)

        fun bind(offer: Offer) {
            title.text = offer.title
            city.text = offer.town
            price.text = "от ${offer.price.value.formatPrice()} ₽"

            when (offer.id) {
                1 -> {
                    image.setImageResource(
                        R.drawable.die_antwood
                    )
                }

                2 -> {
                    image.setImageResource(
                        R.drawable.socrat_i_lera
                    )
                }

                3 -> {
                    image.setImageResource(
                        R.drawable.lampabikt
                    )
                }
            }
        }
    }

    object OfferDiffUtilItemCallback : DiffUtil.ItemCallback<Offer>() {
        override fun areItemsTheSame(oldItem: Offer, newItem: Offer): Boolean {
            return oldItem.id == newItem.id
        }

        override fun areContentsTheSame(oldItem: Offer, newItem: Offer): Boolean {
            return oldItem == newItem
        }
    }
}