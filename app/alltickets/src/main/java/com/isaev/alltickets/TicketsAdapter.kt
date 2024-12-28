package com.isaev.alltickets

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.core.view.isVisible
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView.ViewHolder
import com.isaev.alltickets.databinding.TicketCardItemBinding
import com.isaev.common.Ticket
import com.isaev.common.formatPrice
import java.text.SimpleDateFormat
import kotlin.time.Duration.Companion.milliseconds

class TicketsAdapter :
    ListAdapter<Ticket, TicketsAdapter.TicketViewHolder>(TicketDiffItemCallback) {

    private val inputDateFormat = SimpleDateFormat("yyyy'-'MM'-'dd'T'HH:mm:ss")
    private val outputDateFormat = SimpleDateFormat("HH:mm")


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TicketViewHolder {
        val binding =
            TicketCardItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)

        return TicketViewHolder(binding)
    }

    override fun onBindViewHolder(holder: TicketViewHolder, position: Int) {
        holder.bind(getItem(position))
    }

    inner class TicketViewHolder(
        private val binding: TicketCardItemBinding
    ) : ViewHolder(binding.root) {

        fun bind(ticket: Ticket) {

            if (ticket.badge == null) {
                binding.badge.isVisible = false
            } else {
                binding.badge.isVisible = true
                binding.badge.text = ticket.badge
            }

            binding.price.text = ticket.price.value.formatPrice()

            val depDate = inputDateFormat.parse(ticket.departure.date)
            val arrDate = inputDateFormat.parse(ticket.arrival.date)

            if (depDate != null && arrDate != null) {
                binding.startTime.isVisible = true
                binding.endTime.isVisible = true

                val flyTimeHours = (arrDate.time - depDate.time).milliseconds.inWholeHours

                binding.startTime.text = outputDateFormat.format(depDate)
                binding.endTime.text = outputDateFormat.format(arrDate)


                binding.departureAirline.text = ticket.departure.airport
                binding.arrivalAirline.text = ticket.arrival.airport

                val transferText = if (ticket.hasTransfer) "" else "/Без пересадок"

                binding.info.text = "${flyTimeHours}ч в пути${transferText}"
            } else {
                binding.startTime.isVisible = false
                binding.endTime.isVisible = false
            }
        }
    }

    object TicketDiffItemCallback : DiffUtil.ItemCallback<Ticket>() {
        override fun areItemsTheSame(oldItem: Ticket, newItem: Ticket): Boolean {
            return oldItem.id == newItem.id
        }

        override fun areContentsTheSame(oldItem: Ticket, newItem: Ticket): Boolean {
            return oldItem == newItem
        }
    }
}