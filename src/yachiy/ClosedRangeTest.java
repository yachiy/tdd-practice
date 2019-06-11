package yachiy;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class ClosedRangeTest {

    @Test
    public void 文字列として表現できること() {
        final int min = 1;
        final int max = 2;
        ClosedRange closedRange = new ClosedRange(min, max);

        assertEquals(String.format("[%s,%s]", min, max), closedRange.toString());
    }

    @Nested
    class 上端点より下端点が大きい閉区間は生成できないこと {
        private static final int MIN = 1;
        private static final int MAX = 2;

        @Test
        public void 上端点より下端点が大きい閉区間を生成できないこと() throws IllegalArgumentException {
            IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, () -> new ClosedRange(MAX, MIN));
            assertEquals(String.format("%s>%s", MAX, MIN), exception.getMessage());
        }

        @Test
        public void 上端点が下端点より大きい閉区間を生成できること() {
            assertDoesNotThrow(() -> new ClosedRange(MIN, MAX));
        }

        @Test
        public void 下端点と上端点が同じ閉区間を生成できること() {
            assertDoesNotThrow(() -> new ClosedRange(MIN, MIN));
        }
    }

    @Nested
    class 指定した整数を含むかどうかを判定できること {
        private ClosedRange sut;
        // 中間点の判定をしたいのでMINとMAXは2以上離すこと
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
        private static final int MIN = 1;
        private static final int MAX = 2;
        private static final int RANGE = MAX - MIN;

        @BeforeEach
        public void 閉区間生成() {
            sut = new ClosedRange(MIN, MAX);
        }

        @Test
        public void 下端点と上端点が一致する閉区間は等価であると判定すること() {
            assertTrue(sut.isEqual(new ClosedRange(MIN, MAX)));
        }

        @Test
        public void 下端点と上端点が違うが幅が同じ閉区間は等価ではないと判定すること() {
            assertFalse(sut.isEqual(new ClosedRange(MAX + 1, MAX + 1 + RANGE)));
        }
    }

    @Nested
    class 別の閉区間が完全に含まれるかどうか判定できること {
        private ClosedRange sut;
        // 内包判定のため、MINとMAXは3以上離すこと
        private static final int MIN = 1;
        private static final int MAX = 4;

        @BeforeEach
        public void 閉区間生成() {
            sut = new ClosedRange(MIN, MAX);
        }

        @Test
        public void 等価な閉区間は含まれていると判定すること() {
            assertTrue(sut.contains(new ClosedRange(MIN, MAX)));
        }

        @Test
        public void 内包される閉区間は含まれていると判定すること() {
            assertTrue(sut.contains(new ClosedRange(MIN + 1, MAX - 1)));
        }

        @Test
        public void 上端点のみが区間内にある閉区間は含まれていないと判定すること() {
            assertFalse(sut.contains(new ClosedRange(MIN - 1, MIN + 1)));
        }

        @Test
        public void 下端点のみが区間内にある閉区間は含まれていないと判定すること() {
            assertFalse(sut.contains(new ClosedRange(MAX - 1, MAX + 1)));
        }

        @Test
        public void 下端点と上端点のどちらも区間内にないの閉区間は含まれていないと判定すること() {
            assertFalse(sut.contains(new ClosedRange(MIN - 1, MAX + 1)));
        }
    }
}