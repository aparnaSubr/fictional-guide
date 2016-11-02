import sys
import os
#import shlex
import shutil
from subprocess import call

if len(sys.argv) != 3:
	print "Usage: python trial.py <source-path> <destination-directory>"
	sys.exit(0)
	
#status = call(["./createGradingDirectory.sh", sys.argv[1], "tests"])
#if status == 0:
#	print "\nTesting directory created.\n"
'''
gradingDirectory = sys.argv[1] + sys.argv[2] 
print gradingDirectory

try:
	status = call(["cd", gradingDirectory], shell=True)
except Exception as e:
	print "\nFailed to change directory!!"
if status == 0:
	print "\nChange to grading directory complete."
else:
	sys.exit(0)
'''

listOfFiles = os.listdir(sys.argv[1])
for filename in listOfFiles:
	print "Compiling submission for {0}".format(filename)
	completeFilename = os.path.join(sys.argv[1], filename)
	#print completeFilename
	#command = "cp " + completeFilename + " DigitsInANumber.java"
	#print command
	#print shlex.split(command)
	#status = call(shlex.split(command), shell=True)
	#status = call(command, shell=True)
	destination = os.path.join(sys.argv[1], "Grades/DigitsInANumber.java")
	shutil.copyfile(completeFilename, destination)
	command = "./script.sh " + destination + " >> " + os.path.join(sys.argv[1], "Grades", (filename + ".txt"))
	print command
	sys.exit(0)
	#status = call(["./script.sh", ">", (filename + ".txt")], shell=True)
	status = call(command, shell=True)
	if status != 0:
		print "\n\nTests did not run!!"
	else:
		print "Completed tests for " + filename
