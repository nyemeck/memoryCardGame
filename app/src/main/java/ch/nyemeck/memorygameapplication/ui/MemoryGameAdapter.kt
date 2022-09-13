package ch.nyemeck.memorygameapplication.ui

import android.content.Context
import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.core.view.ViewCompat
import androidx.recyclerview.widget.RecyclerView
import ch.nyemeck.memorygameapplication.R
import ch.nyemeck.memorygameapplication.data.GameLevel
import ch.nyemeck.memorygameapplication.data.CardItem
import ch.nyemeck.memorygameapplication.databinding.LayoutCardItemViewBinding
import kotlin.math.min

class MemoryGameAdapter(
    private val gameLevel: GameLevel,
    private val cards: List<CardItem>,
    private val onCardItemClicked: (position: Int) -> Unit
    ) : RecyclerView.Adapter<MemoryGameAdapter.MemoryCardViewHolder>() {

    private lateinit var myContext: Context;
    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): MemoryGameAdapter.MemoryCardViewHolder {
        Log.d(TAG, "onCreateViewHolder: ")
        val cardWidth: Int =  (parent.width/gameLevel.getColumn())-(2*CARD_MARGIN)
        val cardHeight =  (parent.height / gameLevel.getRow())-(2*CARD_MARGIN)
        val cardSize = min(cardWidth, cardHeight)
        myContext = parent.context
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

    override fun getItemCount() = gameLevel.numberOfCards

    inner class MemoryCardViewHolder(val layoutCardItemViewBinding: LayoutCardItemViewBinding): RecyclerView.ViewHolder(layoutCardItemViewBinding.root) {
        fun bind(position: Int) {
            val myCard = cards[position]
            layoutCardItemViewBinding.imageButtonCardItemId.setImageResource(if(myCard.isFaceUp) myCard.id else R.drawable.ic_baseline_texture_24)
            val colorStateList = if (myCard.isMatched) ContextCompat.getColorStateList(myContext, androidx.appcompat.R.color.material_blue_grey_800) else null
            ViewCompat.setBackgroundTintList(layoutCardItemViewBinding.imageButtonCardItemId, colorStateList)
            layoutCardItemViewBinding.imageButtonCardItemId.setOnClickListener{
                onCardItemClicked(position)
                Log.d(TAG, "bind Click on position: $position")
            }
        }
    }

    companion object {
        private val TAG = MemoryGameAdapter::class.java.simpleName
        private const val CARD_MARGIN = 10
    }


}