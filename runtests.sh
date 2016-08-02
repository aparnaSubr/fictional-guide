#!bin/bash


# Arg 1: Directory of submitted source files
# Arg 2: Name of Grading directory
# Arg 3: Directory with calls.py and script.sh
python formatFileName.py $1
./createGradingDirectory.sh $1 $2
#cd $1
#mkdir $2
#python tests.py $1 $2
#cd $1
#cd $2
#python calls.py $1 $2


#~~~~~~~~~~~~~~~~~ MIHIR ~~~~~~~~~~~~~~~~~~~~~~
#cp $3/calls.py $2
#cp $3/script.sh $2
#cd $2
#python calls.py <arg> <arg> |& tee run.log		#This will show you output on screen and also write to file called run.log
