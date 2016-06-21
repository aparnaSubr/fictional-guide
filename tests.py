import sys
import os
from subprocess import call

if len(sys.argv) != 3:
	print "Usage: python trial.py <source-path>"
	sys.exit(0)
	
status = call(["./createGradingDirectory.sh", sys.argv[1], sys.argv[2]])
print status

#listOfFiles = os.listdir(sys.argv[1])
#for filename in listOfFiles:
print "done"
