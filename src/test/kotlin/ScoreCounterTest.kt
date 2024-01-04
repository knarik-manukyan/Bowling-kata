import org.junit.jupiter.api.Test
import kotlin.test.assertEquals

class ScoreCounterTest {
    @Test
    fun `check simple case with no spare and strike`() {
        val sc = ScoreCounter()
        // modulo 6 is taken to have a valid game, where spare and strikes are not present
        val game = getGame(1)
        assertEquals(48, sc.countScore(game))
    }

    @Test
    fun `check spare with bonus`() {
        val sc = ScoreCounter()
        val game = getGame(1)
        // make second throw spare
        game[1] = '/'
        assertEquals(58, sc.countScore(game))
    }

    @Test
    fun `check two spare`() {
        val sc = ScoreCounter()
        val game = getGame(1)
        // make second throw spare
        game[1] = '/'
        // make forth throw spare
        game[3] = '/'
        assertEquals(66, sc.countScore(game))
    }

    @Test
    fun `check strike with bonus`() {
        val sc = ScoreCounter()
        // modulo 6 is taken to have a valid game, where spare and strikes are not present
        var game = getGame(3)
        // make first throw strike
        game = arrayOf('X') + game
        assertEquals(62, sc.countScore(game))
    }

    @Test
    fun `check two strike`() {
        val sc = ScoreCounter()
        // modulo 6 is taken to have a valid game, where spare and strikes are not present
        var game = getGame(5)
        // make first two throw strike
        game = arrayOf('X', 'X') + game
        assertEquals(78, sc.countScore(game))
    }

    @Test
    fun `check three strike`() {
        val sc = ScoreCounter()
        // modulo 6 is taken to have a valid game, where spare and strikes are not present
        var game = getGame(7)
        // make first three throw strike
        game = arrayOf('X', 'X', 'X') + game
        assertEquals(97, sc.countScore(game))
    }

    @Test
    fun `check strike and spare`() {
        val sc = ScoreCounter()
        // modulo 6 is taken to have a valid game, where spare and strikes are not present
        var game = getGame(5)
        // make first throw strike, and third one spare
        game = arrayOf('X', '5', '/') + game
        assertEquals(73, sc.countScore(game))
    }

    @Test
    fun `check last spare`() {
        val sc = ScoreCounter()
        // modulo 6 is taken to have a valid game, where spare and strikes are not present
        var game = getGame(1)
        // make last throw spare
        game[19] = '/'
        game = game + arrayOf('5')
        assertEquals(60, sc.countScore(game))
    }

    @Test
    fun `check last strike`() {
        val sc = ScoreCounter()
        // modulo 6 is taken to have a valid game, where spare and strikes are not present
        var game = getGame(1, 18)
        // make last throw strike
        game = game + arrayOf('X', '5', '4')
        assertEquals(64, sc.countScore(game))
    }

    @Test
    fun `check last strike and spare`() {
        val sc = ScoreCounter()
        // modulo 6 is taken to have a valid game, where spare and strikes are not present
        var game = getGame(1, 18)
        // make last throw strike
        game = game + arrayOf('X', '5', '/')
        assertEquals(65, sc.countScore(game))
    }

    @Test
    fun `check all strikes`() {
        val sc = ScoreCounter()
        var game = (1..12).map { 'X' }.toTypedArray()
        assertEquals(300, sc.countScore(game))
    }

    @Test
    fun `check all spare`() {
        val sc = ScoreCounter()
        var game = (0..20).map { if (it % 2 == 0) '5' else '/' }.toTypedArray()
        assertEquals(150, sc.countScore(game))
    }

    private fun getGame(startIndex: Int, endIndex: Int = 20) = (startIndex..endIndex).map { (it % 6).digitToChar() }.toTypedArray()
}
