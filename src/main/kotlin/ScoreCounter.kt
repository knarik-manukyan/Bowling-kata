class ScoreCounter {
    /**
     * counts the score of a bowling game
     * the input is array of chars, where each element represents a single throw
     * '/' represents spare, and 'X' represents strike
     * in case of a strike, there should be only one throw in the input with char 'X'
     * Some examples of a valid input
     * 1. ['1', '2', '3', '4', '5', '0', '1', '2', '3', '4', '5', '0', '1', '2', '3', '4', '5', '0', '1', '2']
     * 2. ['1', '2', '3', '4', '5', '0', '1', '2', '3', '4', '5', '0', '1', '2', '3', '4', '5', '0', 'X', '5', '/']
     * 3. ['X', 'X', 'X', 'X', 'X', 'X', 'X', 'X', 'X', 'X', 'X', 'X']
     * 4. ['5', '/', '5', '/', '5', '/', '5', '/', '5', '/', '5', '/', '5', '/', '5', '/', '5', '/', '5', '/', '5']
     *
     * Corresponding expected scores of the examples
     * 1. 48
     * 2. 65
     * 3. 300
     * 4. 150
     */
    fun countScore(throws: Array<Char>): Int {
        return (throws.take(getLastTurnIndex(throws) + 1).mapIndexed { id, singleThrow ->
            when(singleThrow) {
                '/' -> handleSpare(throws, id)
                'X' -> handleStrike(throws, id)
                else -> handleNumberScore(throws, id)
            }
        }).sum()
    }

    /**
     * returns the points from the throw with index id without bonus points
     */
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

    /**
     * return the index of the 10th last turn, so the bonus points of the last turn will not be counted
     * as a new turn
     */
    private fun getLastTurnIndex(throws: Array<Char>): Int {
        var throwIndex = 0
        var id = -1
        while (throwIndex < 20 && id < throws.size) {
            id++
            throwIndex += if (throws[id] == 'X') 2 else 1
        }
        return id
    }
}