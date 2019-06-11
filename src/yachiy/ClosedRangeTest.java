package yachiy;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class ClosedRangeTest {

    @Test
    public void 文字列として表現できる() throws Exception{
        ClosedRange closedRange = new ClosedRange(1, 3);

        assertEquals("[1,3]", closedRange.toString());
    }
}