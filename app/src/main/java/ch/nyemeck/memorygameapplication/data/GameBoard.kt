package ch.nyemeck.memorygameapplication.data

import ch.nyemeck.memorygameapplication.util.ICONS

class GameBoard {

    lateinit var cardItems : List<CardItem>
    var previousSelectedCardIndex :Int? = null
    var numberOfPairFounds = 0
    val level = GameLevel.EASY
    init {
        initialize()
    }

    private fun initialize() {
        val chosenImageIds = ICONS.shuffled().take(level.getNumberOfPairs())
        val randomizedChosenImageIds = (chosenImageIds + chosenImageIds).shuffled()
        cardItems = randomizedChosenImageIds.map { id -> CardItem(id) }
    }


    fun flipCard(position: Int): Boolean {
        val cardItem = cardItems[position]
        // 0 card previously flip over => flip over the selected card
        // 1 card previously flip over => flip over the selected card + check the match
        // 2 card previously flip over => restore cards + flip over the selected card
        var matchFound = false
        if(previousSelectedCardIndex==null){
            restorePreviousSelectedCards()
            previousSelectedCardIndex = position
        }else{
            previousSelectedCardIndex?.let {
                index -> checkMatch(index, position)
                previousSelectedCardIndex=null
            }
        }
        cardItem.isFaceUp = !cardItem.isFaceUp
        return matchFound
    }

    private fun checkMatch(index1: Int, index2: Int): Boolean {
        val match = cardItems[index1].id == cardItems[index2].id
        if(match){
            cardItems[index1].isMatched = true
            cardItems[index2].isMatched = true
            numberOfPairFounds++
        }
        return match
    }


    private fun restorePreviousSelectedCards() {
        cardItems.forEach { cardItem ->
            if (!cardItem.isMatched){ cardItem.isFaceUp = false }
        }
    }

    fun finish(): Boolean {
        return numberOfPairFounds == level.getNumberOfPairs()
    }

    fun cardItemFacingUp(position: Int): Boolean {
        return cardItems[position].isFaceUp
    }

    fun startGame() {
        initialize()
    }

}