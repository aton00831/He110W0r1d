# -*- coding: utf-8 -*-
# !/usr/bin/env python3

"""
1286. Iterator for Combination

Design an Iterator class, which has:

A constructor that takes a string characters of sorted distinct lowercase English letters and a number combinationLength as arguments.
A function next() that returns the next combination of length combinationLength in lexicographical order.
A function hasNext() that returns True if and only if there exists a next combination.
 

Example:

CombinationIterator iterator = new CombinationIterator("abc", 2); // creates the iterator.

iterator.next(); // returns "ab"
iterator.hasNext(); // returns true
iterator.next(); // returns "ac"
iterator.hasNext(); // returns true
iterator.next(); // returns "bc"
iterator.hasNext(); // returns false
 

Constraints:

1 <= combinationLength <= characters.length <= 15
There will be at most 10^4 function calls per test.
It's guaranteed that all calls of the function next are valid.

Source : https://leetcode.com/problems/iterator-for-combination/
Date : 2020/8/13
"""

import unittest
from collections import namedtuple
from typing import List

TestCase = namedtuple("TestCase", ["x", "y"])


# from math import comb
from itertools import combinations


class CombinationIterator:
    def __init__(self, characters: str, combinationLength: int):
        # self.l = comb(len(characters), combinationLength)
        self.i = 0
        self.combs = ["".join(c) for c in combinations(characters, combinationLength)]

    def next(self) -> str:
        if not self.hasNext():
            return ""
        a = self.combs[self.i]
        self.i += 1
        return a

    def hasNext(self) -> bool:
        return self.i < len(self.combs)


# Your CombinationIterator object will be instantiated and called as such:
# obj = CombinationIterator(characters, combinationLength)
# param_1 = obj.next()
# param_2 = obj.hasNext()


class SolutionTest(unittest.TestCase):
    def setUp(self):
        pass

    def tearDown(self):
        pass

    def test_solution_function(self):
        # I don't like command based practice
        pass


if __name__ == "__main__":
    unittest.main()

