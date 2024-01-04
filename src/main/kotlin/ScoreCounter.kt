class ScoreCounter {
    fun countScore(throws: Array<Char>): Int {
        return throws.sumOf { it.digitToInt() }
    }
}