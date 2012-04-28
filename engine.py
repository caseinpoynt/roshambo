#!/usr/bin/env python

import sys
import subprocess

"""
p1 = subprocess.Popen([sys.argv[1]],stdout=subprocess.PIPE,stderr=subprocess.PIPE,stdin=subprocess.PIPE)
p2 = subprocess.Popen([sys.argv[2]],stdout=subprocess.PIPE,stderr=subprocess.PIPE,stdin=subprocess.PIPE)

for x in range(1000):
  print p1.communicate()
  print p2.stdout.read()
  p1move = p1.stdout.readline()
  p2move = p2.stdout.readline()
  print "***", p1move, p2move
  #p1.stdin.write(p1move)
  #p2.stdin.write(p2move)
"""
