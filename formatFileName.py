import os
import sys

if len(sys.argv)!=2:
	print "Usage: python formatFileName.py <directory>"
	sys.exit(1)

print "\n\nFormatting file names...\n"

path = sys.argv[1]
listOfFiles = os.listdir(path)
#os.chdir(sys.argv[1])

for f in listOfFiles:
	print f
	f = path + "/" + f
	os.rename(f, f.replace(" ",""))

print "\n\nDone.\n"
