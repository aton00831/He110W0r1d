# -*- coding: utf-8 -*-
# !/usr/bin/env python3

"""
{title}

{question}

{example}

Source : {Source}
Date : {Date}
"""

import unittest
from collections import namedtuple
from typing import List

TestCase = namedtuple("TestCase", ["x", "y"])


class Solution:
    def solution(self):
        pass


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

