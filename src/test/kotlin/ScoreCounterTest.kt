import org.junit.jupiter.api.Test
import kotlin.test.assertEquals

class ScoreCounterTest {
    @Test
    fun `check simple case with no spare and strike`() {
        val sc = ScoreCounter()
        // modulo 6 is taken to have a valid game, where spare and strikes are not present
        val game = (0..19).map { (it%6).digitToChar() }.toTypedArray()
        assertEquals(46, sc.countScore(game))
    }
}
