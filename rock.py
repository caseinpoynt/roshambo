#!/usr/bin/env python -u

import sys

moves = []
while True:
    # good old rock, nothing beats that
    print "rock"
    move = raw_input()
    if move == "goodbye":
        break
    moves.append(move)

