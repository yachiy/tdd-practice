package yachiy;

import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

class ClosedRangeTest {

    @Test
    public void 文字列として表現できる() throws Exception {
        ClosedRange closedRange = new ClosedRange(1, 2);

        assertEquals("[1,2]", closedRange.toString());
    }

    @Nested
    class 上端点より下端点が大きい閉区間を作ることはできない {
        @Test
        public void 下端点が2で上端点が1の閉区間を生成できないこと() throws IllegalArgumentException {
            IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, () -> new ClosedRange(2, 1));
            assertEquals("2>1", exception.getMessage());
        }
    }
}