package idv.cnfang.leetcode;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

class Problem1663Test {
	private Problem1663 sol = new Problem1663();
	
	@Test
	void testGetSmallestString() {
		assertEquals("aay", sol.getSmallestString(3, 27));
		assertEquals("aaszz", sol.getSmallestString(5, 73));
	}

}
