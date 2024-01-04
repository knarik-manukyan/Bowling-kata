class ScoreCounter {
    fun countScore(throws: Array<Char>): Int {
        return (throws.mapIndexed { id, singleThrow ->
            when(singleThrow) {
                '/' -> handleSpare(throws, id)
                'X' -> handleStrike()
                else -> handleNumberScore(singleThrow)
            }
        }).sum()
    }

    private fun handleNumberScore(singleThrow: Char) = singleThrow.digitToInt()

    private fun handleSpare(throws: Array<Char>, id: Int): Int {
        val pointsForThisThrow = 10 - handleNumberScore(throws[id - 1])
        val bonusPoints = handleNumberScore(throws[id + 1])
        return pointsForThisThrow + bonusPoints
    }

    private fun handleStrike() = 10
}