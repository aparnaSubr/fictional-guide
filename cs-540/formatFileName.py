###################################################################################
###################################################################################
###
###	Filenames are moved to a directory named <LastName> <FirstName>
### Zip files are unzipped into the directory named <LastName> <FirstName>
###
###################################################################################
###################################################################################

import os
import sys
from shutil import move
import zipfile

if len(sys.argv)!=2:
    print('Usage: python formatFileName.py <directory>')
    sys.exit(0)

print('\n\nFormatting file names...\n')

path = sys.argv[1]
list_of_files = os.listdir(path)

for filename in list_of_files:
    print(filename)
    new_dir_name = filename.split(' - ')[1]
    if not os.path.exists(os.path.join(path, new_dir_name)):
        os.makedirs(os.path.join(path, new_dir_name))

    new_filename = filename.split(' - ')[-1]
    filename = os.path.join(path, filename)
    os.rename(filename, new_filename)
    move(new_filename, os.path.join(path, new_dir_name))

    if new_filename.endswith('.zip'):
        zip_ref = zipfile.ZipFile(os.path.join(path, new_dir_name, new_filename))
        zip_ref.extractall(os.path.join(path, new_dir_name))
        zip_ref.close()
print "Done.\n"
