#!/usr/bin/env python -u

import sys

moves = []
while True:
    print "paper"
    move = raw_input()
    if move == "goodbye":
        break
    moves.append(move)
