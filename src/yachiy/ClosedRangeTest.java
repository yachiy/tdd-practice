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
        private static final int MIN = 1;
        private static final int MAX = 3;

        @BeforeEach
        public void 閉区間生成() {
            sut = new ClosedRange(MIN, MAX);
        }

        @Test
        public void 下端点の値が閉区間に含まれると判定されること() {
            assertTrue(sut.contains(MIN));
        }

        @Test
        public void 上端点の値が閉区間に含まれると判定されること() {
            assertTrue(sut.contains(MAX));
        }

        @Test
        public void 中間点の値が閉区間に含まれると判定されること() {
            assertTrue(sut.contains((int) ((MAX + MIN) / 2.0)));
        }

        @Test
        public void 下端点未満の値が閉区間に含まれないと判定されること() {
            assertFalse(sut.contains(MIN - 1));
        }

        @Test
        public void 上端点より大きい値が閉区間に含まれないと判定されること() {
            assertFalse(sut.contains(MAX + 1));
        }
    }

    @Nested
    class 別の閉区間と等価かどうかを判定できること {
        private ClosedRange sut;

        @BeforeEach
        public void 閉区間生成() {
            sut = new ClosedRange(1, 2);
        }

        @Test
        public void 下端点1と上端点2の閉区間は等価であると判定すること() {
            assertTrue(sut.isEqual(new ClosedRange(1, 2)));
        }

        @Test
        public void 下端点3と上端店4の閉区間は等価ではないと判定すること() {
            assertFalse(sut.isEqual(new ClosedRange(3, 4)));
        }
    }

}