import org.junit.Assert;
import org.junit.Test;

public class SolutionTest {
    @Test
    public void test1() {
        int n = 6;
        int[][] edges = {
                {0, 2, 2},
                {0, 5, 6},
                {1, 0, 3},
                {1, 4, 5},
                {2, 1, 1},
                {2, 3, 3},
                {2, 3, 4},
                {3, 4, 2},
                {4, 5, 1},
        };
        int src1 = 0;
        int src2 = 1;
        int dest = 5;
        long expected = 9L;
        long actual = new Solution().minimumWeight(n, edges, src1, src2, dest);

        Assert.assertEquals(expected, actual);
    }
}
