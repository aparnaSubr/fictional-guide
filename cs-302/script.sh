#!/bin/bash
#cd Downloads/grading/
#cd /afs/cs.wisc.edu/u/a/p/aparnasubr/Downloads/summer-grading/trial/Grades
javac DigitsInANumber.java
#javac $1
java DigitsInANumber < <(printf "1\r")
java DigitsInANumber < <(printf "12\r")
java DigitsInANumber < <(printf "132\r")
java DigitsInANumber < <(printf "1234\r")
java DigitsInANumber < <(printf "%d\r" -132)
java DigitsInANumber < <(printf "12345\r")
java DigitsInANumber < <(printf "4.56\r")
java DigitsInANumber < <(printf "codderrrrr\r")
