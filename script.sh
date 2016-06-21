#!bin/sh
#cd Downloads/grading/
#javac DigitsInANumber.java
javac $1
java DigitsInANumber < <(printf "3\r")
java DigitsInANumber < <(printf "99\r")
java DigitsInANumber < <(printf "123\r")
java DigitsInANumber < <(printf "9001\r")
java DigitsInANumber < <(printf "%d\r" -12)
java DigitsInANumber < <(printf "10000\r")
java DigitsInANumber < <(printf "3.14\r")
java DigitsInANumber < <(printf "code\r")
