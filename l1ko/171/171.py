# -*- coding: utf-8 -*-
# !/usr/bin/env python3

"""
171. Excel Sheet Column Number

Given a column title as appear in an Excel sheet, return its corresponding column number.

For example:

    A -> 1
    B -> 2
    C -> 3
    ...
    Z -> 26
    AA -> 27
    AB -> 28 
    ...

### Example 1:
Input: "A"
Output: 1

### Example 2:
Input: "AB"
Output: 28

### Example 3:
Input: "ZY"
Output: 701

Source : https://leetcode.com/problems/excel-sheet-column-number/
Date : 2020/8/11
"""

import unittest
from collections import namedtuple
from typing import List

TestCase = namedtuple("TestCase", ["x", "y"])


class Solution:
    def solution(self):
        pass


class Solution:
    def titleToNumber(self, s: str) -> int:
        d = {c: i + 1 for i, c in enumerate("ABCDEFGHIJKLMNOPQRSTUVWXYZ")}
        a = 0
        for i, c in enumerate(s):
            # print(i, c, d[c], max((len(s)-i-1), 0))
            a += d[c] * (26 ** max((len(s) - i - 1), 0))
        return a


class SolutionTest(unittest.TestCase):
    def setUp(self):
        pass

    def tearDown(self):
        pass

    def test_solution_function(self):
        sol = Solution()

        testCases = [
            TestCase("A", 1),
            TestCase("AAC", 705),
            TestCase("FXSHRXW", 2147483647),
        ]

        for testCase in testCases:
            self.assertEqual(sol.titleToNumber(testCase.x), testCase.y)


if __name__ == "__main__":
    unittest.main()

