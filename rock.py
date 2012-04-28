#!/usr/bin/env python -u

import sys

moves = []
while True:
    # good old rock, nothing beats rock
    print "rock"
    move = raw_input()
    if move == "goodbye":
        break
    moves.append(move)

