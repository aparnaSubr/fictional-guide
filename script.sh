#!/bin/bash
#cd Downloads/grading/
#cd /afs/cs.wisc.edu/u/a/p/aparnasubr/Downloads/summer-grading/trial/Grades
javac DigitsInANumber.java
#javac $1
java DigitsInANumber < <(printf "3\r")
java DigitsInANumber < <(printf "99\r")
java DigitsInANumber < <(printf "123\r")
java DigitsInANumber < <(printf "9001\r")
java DigitsInANumber < <(printf "%d\r" -12)
java DigitsInANumber < <(printf "10000\r")
java DigitsInANumber < <(printf "3.14\r")
java DigitsInANumber < <(printf "code\r")
