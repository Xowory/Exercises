import com.company.Board;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class BoardTest {
    private Board board;
    @BeforeEach
    public void inicjalizacja(){
        board = new Board();
        board.init2();
    }
    @Test
    public void spr_srodek(){
        Assertions.assertEquals(0,board.liczbaSasiadow(4,4));
    }
    @Test
    public void spr_rog() { Assertions.assertEquals(5,board.liczbaSasiadow(0,0)); }
    @Test
    public void spr_bok(){
        Assertions.assertEquals(0,board.liczbaSasiadow(7,4));
    }
    @Test
    public void spr_pkt1(){
        board.step();
        Assertions.assertEquals(false,board.zmiana(7,4));
    }
    @Test
    public void spr_pkt2(){
        board.step();
        Assertions.assertEquals(true,board.zmiana(3,1));
    }
    @Test
    public void spr_pkt3(){
        board.step();
        Assertions.assertEquals(false,board.zmiana(7,7));
    }
    @Test
    public void spr_pkt4(){
        board.step();
        Assertions.assertEquals(true,board.zmiana(1,0));
    }
}
