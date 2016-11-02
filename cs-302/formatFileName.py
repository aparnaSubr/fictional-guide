###################################################################################
###################################################################################
###
###	Filenames are replaced with <LastName><FirstName>
###
###################################################################################
###################################################################################

import os
import sys

if len(sys.argv)!=2:
	print "Usage: python formatFileName.py <directory>"
	sys.exit(1)

print "\n\nFormatting file names...\n"

path = sys.argv[1]
listOfFiles = os.listdir(path)
#os.chdir(sys.argv[1])

countOfFiles = 0
for filename in listOfFiles:
	print filename
	new_filename = filename.replace(" ","").split("-")[2]
	new_filename = path + "/" + new_filename
	filename = path + "/" + filename
	os.rename(filename, new_filename)
	countOfFiles += 1

print "\n\n{0} files renamed.".format(countOfFiles)
print "Done.\n"
