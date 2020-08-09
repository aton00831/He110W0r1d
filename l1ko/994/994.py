# -*- coding: utf-8 -*-
# !/usr/bin/env python3

"""
994. Rotting Oranges

In a given grid, each cell can have one of three values:

the value 0 representing an empty cell;
the value 1 representing a fresh orange;
the value 2 representing a rotten orange.
Every minute, any fresh orange that is adjacent (4-directionally) to a rotten orange becomes rotten.

Return the minimum number of minutes that must elapse until no cell has a fresh orange.  If this is impossible, return -1 instead.

### Example 1:
Input: [[2,1,1],[1,1,0],[0,1,1]]
Output: 4

### Example 2:
Input: [[2,1,1],[0,1,1],[1,0,1]]
Output: -1
Explanation:  The orange in the bottom left corner (row 2, column 0) is never rotten, because rotting only happens 4-directionally.

### Example 3:
Input: [[0,2]]
Output: 0
Explanation:  Since there are already no fresh oranges at minute 0, the answer is just 0.

Source : https://leetcode.com/problems/rotting-oranges/
Date : 2020/8/9
"""

import unittest
from collections import namedtuple
from typing import List


TestCase = namedtuple("TestCase", ["x", "y"])


class Solution:
    def orangesRotting(self, grid: List[List[int]]) -> int:
        step = 0
        n_fresh = 0
        for row in grid:
            for orange in row:
                if orange == 1:
                    n_fresh += 1
        while n_fresh > 0:
            target = set()
            for i in range(len(grid)):
                for j in range(len(grid[i])):
                    if grid[i][j] == 2:
                        if i + 1 < len(grid) and grid[i + 1][j] == 1:
                            target.add((i + 1, j))
                        if i - 1 >= 0 and grid[i - 1][j] == 1:
                            target.add((i - 1, j))
                        if j + 1 < len(grid[i]) and grid[i][j + 1] == 1:
                            target.add((i, j + 1))
                        if j - 1 >= 0 and grid[i][j - 1] == 1:
                            target.add((i, j - 1))
            if len(target) > 0:
                for i, j in target:
                    grid[i][j] = 2
                    n_fresh -= 1
                step += 1
            else:
                break
        if n_fresh > 0:
            return -1
        else:
            return step


class SolutionTest(unittest.TestCase):
    def setUp(self):
        pass

    def tearDown(self):
        pass

    def test_solution_function(self):
        sol = Solution()

        testCases = [
            TestCase([[2, 1, 1], [1, 1, 0], [0, 1, 1]], 4),
            TestCase([[2, 1, 1], [0, 1, 1], [1, 0, 1]], -1),
            TestCase([[0, 2]], 0),
        ]

        for testCase in testCases:
            self.assertEqual(sol.orangesRotting(testCase.x), testCase.y)


if __name__ == "__main__":
    unittest.main()
