package yachiy;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

@DisplayName("閉区間の単体試験")
class ClosedRangeTest {

    @Test
    void 文字列として表現できること() {
        final int lowerEndpoint = 1;
        final int upperEndpoint = 2;
        ClosedRange closedRange = new ClosedRange(lowerEndpoint, upperEndpoint);

        assertEquals(String.format("[%s,%s]", lowerEndpoint, upperEndpoint), closedRange.toString());
    }

    @Nested
    class 上端点より下端点が大きい閉区間は生成できないこと {
        private static final int LOWER_ENDPOINT = 1;
        private static final int UPPER_ENDPOINT = 2;

        @Test
        void 上端点より下端点が大きい閉区間を生成できないこと() throws IllegalArgumentException {
            IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, () -> new ClosedRange(UPPER_ENDPOINT, LOWER_ENDPOINT));
            assertEquals(String.format("%s>%s", UPPER_ENDPOINT, LOWER_ENDPOINT), exception.getMessage());
        }

        @Test
        void 上端点が下端点より大きい閉区間を生成できること() {
            assertDoesNotThrow(() -> new ClosedRange(LOWER_ENDPOINT, UPPER_ENDPOINT));
        }

        @Test
        void 下端点と上端点が同じ閉区間を生成できること() {
            assertDoesNotThrow(() -> new ClosedRange(LOWER_ENDPOINT, LOWER_ENDPOINT));
        }
    }

    @Nested
    class 指定した整数を含むかどうかを判定できること {
        private ClosedRange sut;
        // 中間点の判定をしたいのでMINとMAXは2以上離すこと
        private static final int LOWER_ENDPOINT = 1;
        private static final int UPPER_ENDPOINT = 3;

        @BeforeEach
        void 閉区間生成() {
            sut = new ClosedRange(LOWER_ENDPOINT, UPPER_ENDPOINT);
        }

        @Test
        void 下端点の値が閉区間に含まれると判定されること() {
            assertTrue(sut.contains(LOWER_ENDPOINT));
        }

        @Test
        void 上端点の値が閉区間に含まれると判定されること() {
            assertTrue(sut.contains(UPPER_ENDPOINT));
        }

        @Test
        void 中間点の値が閉区間に含まれると判定されること() {
            assertTrue(sut.contains((int) ((UPPER_ENDPOINT + LOWER_ENDPOINT) / 2.0)));
        }

        @Test
        void 下端点未満の値が閉区間に含まれないと判定されること() {
            assertFalse(sut.contains(LOWER_ENDPOINT - 1));
        }

        @Test
        void 上端点より大きい値が閉区間に含まれないと判定されること() {
            assertFalse(sut.contains(UPPER_ENDPOINT + 1));
        }
    }

    @Nested
    class 別の閉区間と等価かどうかを判定できること {
        private ClosedRange sut;
        private static final int LOWER_ENDPOINT = 1;
        private static final int UPPER_ENDPOINT = 2;
        private static final int RANGE = UPPER_ENDPOINT - LOWER_ENDPOINT;

        @BeforeEach
        void 閉区間生成() {
            sut = new ClosedRange(LOWER_ENDPOINT, UPPER_ENDPOINT);
        }

        @Test
        void 下端点と上端点が一致する閉区間は等価であると判定すること() {
            assertTrue(sut.isEqual(new ClosedRange(LOWER_ENDPOINT, UPPER_ENDPOINT)));
        }

        @Test
        void 下端点と上端点が違うが幅が同じ閉区間は等価ではないと判定すること() {
            assertFalse(sut.isEqual(new ClosedRange(UPPER_ENDPOINT + 1, UPPER_ENDPOINT + 1 + RANGE)));
        }
    }

    @Nested
    class 別の閉区間が完全に含まれるかどうか判定できること {
        private ClosedRange sut;
        // 内包判定のため、MINとMAXは3以上離すこと
        private static final int LOWER_ENDPOINT = 1;
        private static final int UPPER_ENDPOINT = 4;

        @BeforeEach
        void 閉区間生成() {
            sut = new ClosedRange(LOWER_ENDPOINT, UPPER_ENDPOINT);
        }

        @Test
        void 等価な閉区間は含まれていると判定すること() {
            assertTrue(sut.contains(new ClosedRange(LOWER_ENDPOINT, UPPER_ENDPOINT)));
        }

        @Test
        void 内包される閉区間は含まれていると判定すること() {
            assertTrue(sut.contains(new ClosedRange(LOWER_ENDPOINT + 1, UPPER_ENDPOINT - 1)));
        }

        @Test
        void 上端点のみが区間内にある閉区間は含まれていないと判定すること() {
            assertFalse(sut.contains(new ClosedRange(LOWER_ENDPOINT - 1, LOWER_ENDPOINT + 1)));
        }

        @Test
        void 下端点のみが区間内にある閉区間は含まれていないと判定すること() {
            assertFalse(sut.contains(new ClosedRange(UPPER_ENDPOINT - 1, UPPER_ENDPOINT + 1)));
        }

        @Test
        void 下端点と上端点のどちらも区間内にないの閉区間は含まれていないと判定すること() {
            assertFalse(sut.contains(new ClosedRange(LOWER_ENDPOINT - 1, UPPER_ENDPOINT + 1)));
        }
    }
}