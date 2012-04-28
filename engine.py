#!/usr/bin/env python -u

import sys
import subprocess

table = set([
    ("rock", "scissors"),
    ("rock", "lizard"),
    ("paper", "rock"),
    ("paper", "spock"),
    ("scissors", "rock"),
    ("scissors", "lizard"),
    ("lizard", "paper"),
    ("lizard", "spock"),
    ("spock", "rock"),
    ("spock", "scissors")
])

def score(a, b):
    if (a,b) in table:
        return (1, 0)
    if (b,a) in table:
        return (0, 1)
    else:    
        return (0, 0)

p1 = subprocess.Popen([sys.argv[1]],stdout=subprocess.PIPE,stdin=subprocess.PIPE, bufsize=0)
p2 = subprocess.Popen([sys.argv[2]],stdout=subprocess.PIPE,stdin=subprocess.PIPE, bufsize=0)

p1score = 0
p2score = 0

for x in range(1000):
  p1move = p1.stdout.readline()
  p2move = p2.stdout.readline()
  p1.stdin.write(p2move)
  p2.stdin.write(p1move)

  p1inc, p2inc = score(p1move.strip(), p2move.strip())
  p1score += p1inc
  p2score += p2inc


p1.stdin.write("goodbye\n")
p2.stdin.write("goodbye\n")
p1.wait()
p2.wait()


print p1score, p2score
