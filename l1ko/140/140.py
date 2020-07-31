# -*- coding: utf-8 -*-
# !/usr/bin/env python3

"""
140. Word Break II

Given a non-empty string s and a dictionary wordDict containing a list of non-empty words, add spaces in s to construct a sentence where each word is a valid dictionary word. Return all such possible sentences.

** Note: **
- The same word in the dictionary may be reused multiple times in the segmentation.
- You may assume the dictionary does not contain duplicate words.
- Brute-force solution lead to TLE

### Example 1:
Input:
s = "catsanddog"
wordDict = ["cat", "cats", "and", "sand", "dog"]

Output:
[
  "cats and dog",
  "cat sand dog"
]

### Example 2:
Input:
s = "pineapplepenapple"
wordDict = ["apple", "pen", "applepen", "pine", "pineapple"]

Output:
[
  "pine apple pen apple",
  "pineapple pen apple",
  "pine applepen apple"
]

Explanation: Note that you are allowed to reuse a dictionary word.

### Example 3:
Input:
s = "catsandog"
wordDict = ["cats", "dog", "sand", "and", "cat"]

Output:
[]

Source : https://leetcode.com/problems/word-break-ii/
Date : {Date}
"""

import unittest
from collections import namedtuple
from typing import List

TestCase = namedtuple("TestCase", ["x", "y"])


class Solution:
    def wordBreak(self, s: str, wordDict: List[str]) -> List[str]:
        sol = []
        searches = [(0, None)]
        while searches:
            new_searches = []
            for i, cs in searches:
                for w in wordDict:
                    if s[i : i + len(w)] == w:
                        if cs:
                            ncs = cs + [w]
                        else:
                            ncs = [w]
                        if (i + len(w)) >= len(s):
                            sol.append(ncs)
                        else:
                            new_searches.append((i + len(w), ncs))
            searches = new_searches
        return [" ".join(s) for s in sol]


class SolutionTest(unittest.TestCase):
    def setUp(self):
        pass

    def tearDown(self):
        pass

    def test_solution_function(self):
        sol = Solution()

        testCases = [
            TestCase(
                ("catsanddog", ["cat", "cats", "and", "sand", "dog"]),
                ["cats and dog", "cat sand dog"],
            ),
            TestCase(
                (
                    "pineapplepenapple",
                    ["apple", "pen", "applepen", "pine", "pineapple"],
                ),
                ["pine apple pen apple", "pineapple pen apple", "pine applepen apple"],
            ),
            TestCase(("catsandog", ["cats", "dog", "sand", "and", "cat"]), []),
            # TLE Cases
            TestCase(
                (
                    "aaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaabaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaa",
                    [
                        "a",
                        "aa",
                        "aaa",
                        "aaaa",
                        "aaaaa",
                        "aaaaaa",
                        "aaaaaaa",
                        "aaaaaaaa",
                        "aaaaaaaaa",
                        "aaaaaaaaaa",
                    ],
                ),
                [],
            ),
        ]

        for testCase in testCases:
            s = testCase.x[0]
            wordDict = testCase.x[1]
            answer = sol.wordBreak(s, wordDict)

            for a in answer:
                self.assertEqual(a.replace(" ", ""), s)
            self.assertEqual(len(answer), len(testCase.y))
            # TODO: Check exactly


if __name__ == "__main__":
    unittest.main()
