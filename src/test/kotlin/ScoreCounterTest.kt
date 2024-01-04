import org.junit.jupiter.api.Test
import kotlin.test.assertEquals

class ScoreCounterTest {
    @Test
    fun `check simple case with no spare and strike`() {
        val sc = ScoreCounter()
        // modulo 6 is taken to have a valid game, where spare and strikes are not present
        val game = (1..20).map { (it%6).digitToChar() }.toTypedArray()
        assertEquals(48, sc.countScore(game))
    }

    @Test
    fun `check spare with bonus`() {
        val sc = ScoreCounter()
        val game = (1..20).map { (it%6).digitToChar() }.toTypedArray()
        // make second throw spare
        game[1] = '/'
        assertEquals(58, sc.countScore(game))
    }

    @Test
    fun `check two spare`() {
        val sc = ScoreCounter()
        val game = (1..20).map { (it%6).digitToChar() }.toTypedArray()
        // make second throw spare
        game[1] = '/'
        // make forth throw spare
        game[3] = '/'
        assertEquals(66, sc.countScore(game))
    }

    @Test
    fun `check strike without bonus`() {
        val sc = ScoreCounter()
        // modulo 6 is taken to have a valid game, where spare and strikes are not present
        val game = (1..20).map { (it%6).digitToChar() }.toTypedArray()
        // make first throw strike
        game[0] = 'X'
        game[1] = '0'
        assertEquals(55, sc.countScore(game))
    }
}
