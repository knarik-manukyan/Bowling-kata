class ScoreCounter {
    fun countScore(throws: Array<Char>): Int {
        return (throws.mapIndexed { id, singleThrow ->
            when(singleThrow) {
                '/' -> handleSpare(throws, id)
                'X' -> handleStrike(throws, id)
                else -> handleNumberScore(throws, id)
            }
        }).sum()
    }

    private fun handleNumberScore(throws: Array<Char>, id: Int): Int {
        return when(val singleThrow = throws[id]) {
            'X' -> 10
            '/' -> 10 - handleNumberScore(throws, id - 1)
            else -> singleThrow.digitToInt()
        }
    }

    private fun handleSpare(throws: Array<Char>, id: Int): Int {
        val pointsForThisThrow = handleNumberScore(throws, id)
        val bonusPoints = handleNumberScore(throws, id + 1)
        return pointsForThisThrow + bonusPoints
    }

    private fun handleStrike(throws: Array<Char>, id: Int): Int {
        val pointsForThisThrow = handleNumberScore(throws, id)
        val bonusPoints = handleNumberScore(throws, id + 1) + handleNumberScore(throws, id + 2)
        return pointsForThisThrow + bonusPoints
    }
}