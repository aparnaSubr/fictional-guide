###################################################################################
###################################################################################
###
###	Filenames are replaced with <LastName><FirstName>
###
###################################################################################
###################################################################################

import os
import sys
from shutil import move

if len(sys.argv)!=2:
    print "Usage: python formatFileName.py <directory>"
    sys.exit(1)

print "\n\nFormatting file names...\n"

path = sys.argv[1]
listOfFiles = os.listdir(path)

countOfFiles = 0
for filename in listOfFiles:
    print filename
    new_dir_name = filename.split(' - ')[1]
    if not os.path.exists(os.path.join(path, new_dir_name)):
        os.makedirs(os.path.join(path, new_dir_name))

    new_filename = filename.split(' - ')[-1]
    filename = os.path.join(path, filename)
    os.rename(filename, new_filename)
    move(new_filename, os.path.join(path, new_dir_name))
    countOfFiles += 1

print "\n\n{0} files renamed.".format(countOfFiles)
print "Done.\n"
