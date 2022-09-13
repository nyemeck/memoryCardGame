package ch.nyemeck.memorygameapplication.data

enum class GameLevel(val numberOfCards: Int) {
    EASY(8),
    MEDIUM(18),
    HARD(24);

    fun getColumn():Int=
        when(this){
            EASY -> 2
            MEDIUM -> 3
            HARD -> 4
        }


    fun getRow():Int = numberOfCards/getColumn()

    fun getNumberOfPairs():Int = numberOfCards / 2


}