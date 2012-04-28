#!/usr/bin/env python -u

import sys

moves = []
while True:
    # poor predictable Bart; always takes rock 
    print "paper"
    move = raw_input()
    if move == "goodbye":
        break
    moves.append(move)
