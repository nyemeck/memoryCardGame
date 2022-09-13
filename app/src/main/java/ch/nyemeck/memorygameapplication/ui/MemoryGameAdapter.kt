package ch.nyemeck.memorygameapplication.ui

import android.content.Context
import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import ch.nyemeck.memorygameapplication.data.BoardGameLevel
import ch.nyemeck.memorygameapplication.databinding.LayoutCardItemViewBinding
import kotlin.math.min

class MemoryGameAdapter(private val context: Context, private val boardGameLevel: BoardGameLevel, private val cards: List<Int>) : RecyclerView.Adapter<MemoryGameAdapter.MemoryCardViewHolder>() {
    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): MemoryGameAdapter.MemoryCardViewHolder {
        Log.d(TAG, "onCreateViewHolder: ")
        val cardWidth: Int =  (parent.width/boardGameLevel.getColumn())-(2*CARD_MARGIN)
        val cardHeight =  (parent.height / boardGameLevel.getRow())-(2*CARD_MARGIN)
        val cardSize = min(cardWidth, cardHeight)
        val inflater = LayoutInflater.from(parent.context)
        val layoutCardItemViewBinding = LayoutCardItemViewBinding.inflate(inflater, parent, false)
        val layoutParams = layoutCardItemViewBinding.cardViewItemId.layoutParams as ViewGroup.MarginLayoutParams
        layoutParams.height = cardSize
        layoutParams.width = cardSize
        layoutParams.setMargins(CARD_MARGIN,CARD_MARGIN,CARD_MARGIN,CARD_MARGIN)
        return MemoryCardViewHolder(layoutCardItemViewBinding)
    }

    override fun onBindViewHolder(holder: MemoryGameAdapter.MemoryCardViewHolder, position: Int) {
        holder.bind(position)
    }

    override fun getItemCount() = boardGameLevel.numberOfCards

    inner class MemoryCardViewHolder(val layoutCardItemViewBinding: LayoutCardItemViewBinding): RecyclerView.ViewHolder(layoutCardItemViewBinding.root) {
        fun bind(position: Int) {
            layoutCardItemViewBinding.imageButtonCardItemId.setImageResource(cards[position])
            layoutCardItemViewBinding.imageButtonCardItemId.setOnClickListener{
                Log.d(TAG, "bind Click on position: $position")
            }
        }
    }

    companion object {
        private val TAG = MemoryGameAdapter::class.java.simpleName
        private const val CARD_MARGIN = 10
    }


}