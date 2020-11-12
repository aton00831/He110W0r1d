# -*- coding: utf-8 -*-
# !/usr/bin/env python3

"""
{title}

{question}

{example}

Source : https://leetcode.com/problems/how-many-numbers-are-smaller-than-the-current-number/
Date : {Date}
"""

import unittest
from collections import namedtuple
from typing import List

TestCase = namedtuple("TestCase", ["x", "y"])


from itertools import accumulate
class Solution:
    def smallerNumbersThanCurrent(self, nums: List[int]) -> List[int]:
        c = [0] * 101
        for num in nums:
            c[num] += 1
        ac = list(accumulate(c))
        return [ac[num-1] if num !=0 else 0 for num in nums]


class SolutionTest(unittest.TestCase):
    def setUp(self):
        pass

    def tearDown(self):
        pass

    def test_solution_function(self):
        sol = Solution()

        testCases = [TestCase([1, 2, 3], 2)]
        for testCase in testCases:
            self.assertEqual(sol.solution(testCase.x), testCase.y)


if __name__ == "__main__":
    unittest.main()

