package yachiy;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class ClosedRangeTest {

    @Test
    public void 文字列として表現できること() throws Exception {
        ClosedRange closedRange = new ClosedRange(1, 2);

        assertEquals("[1,2]", closedRange.toString());
    }

    @Nested
    class 上端点より下端点が大きい閉区間は生成できないこと {
        @Test
        public void 下端点が2で上端点が1の閉区間を生成できないこと() throws IllegalArgumentException {
            IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, () -> new ClosedRange(2, 1));
            assertEquals("2>1", exception.getMessage());
        }

        @Test
        public void 下端点が1で上端点が2の閉区間を生成できること() {
            assertDoesNotThrow(() -> new ClosedRange(1, 2));
        }

        @Test
        public void 下端点が1で上端点も1の閉区間を生成できること() {
            assertDoesNotThrow(() -> new ClosedRange(1, 1));
        }
    }

    @Nested
    class 指定した整数を含むかどうかを判定できること {
        private ClosedRange sut;

        @BeforeEach
        public void 閉区間生成() {
            sut = new ClosedRange(1, 3);
        }

        @Test
        public void 下端点の1が閉区間に含まれると判定されること() {
            assertTrue(sut.contains(1));
        }

        @Test
        public void 上端点の3が閉区間に含まれると判定されること() {
            assertTrue(sut.contains(3));
        }

        @Test
        public void 中間点の2が閉区間に含まれると判定されること() {
            assertTrue(sut.contains(2));
        }

        @Test
        public void 下端点未満の0が閉区間に含まれないと判定されること() {
            assertFalse(sut.contains(0));
        }

        @Test
        public void 上端点より大きい4が閉区間に含まれないと判定されること() {
            assertFalse(sut.contains(4));
        }
    }

}