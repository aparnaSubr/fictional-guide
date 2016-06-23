#!/usr/bin/python
from os import listdir
from os.path import isfile, join, realpath
import re
import sys
from datetime import datetime
import traceback
import argparse

class SubmissionType(object):
    '''
    Holds student details based on submission filename
    '''
    uid = None
    student = None
    fname = None
    date = None
    assignment = None


def get_latest_submissions(dirname, fileext='.java'):
    '''
    Gets the latest file submissions for each student
    If there are multiple submissions, it picks the
        latest one based on the time mentioned in the
        filename

    Arguments:
        1. dirname : directory name with all submission
                     files (.java extension)
    Returns:
        1. list_of_submissions : list of type SubmissionType() having all
                                 submission info
    '''

    files = [ f for f in listdir(dirname) if isfile(join(dirname, f)) and f.endswith(fileext) ]
    
    # submission record list
    list_of_submissions = []
    # keep uid_list separately to loop for duplicates
    uid_list = []

    for fname in files:
        # isolate the major pieces in the filename
        fname_syntax = re.compile('(\d+)\-\d+ \- ([\w\s\-]+) \- (\w+ \d+, \d+ \d+ \w+) \- (\w+.java)')
        fname_parts = fname_syntax.findall(fname)

        # format the datestring to play nice with Python's datetime
        try:
            datestring_badformat = fname_parts[0][2];
            datestring_syntax = re.compile('(\w+) (\d+), (\d+) (\d+) (\w+)')
            datestring_parts = datestring_syntax.findall(datestring_badformat)
            datestring_parts = list(datestring_parts[0])
            datestring_parts[1] = int(datestring_parts[1])
            datestring_parts[2] = int(datestring_parts[2])
            datestring_parts[3] = int(datestring_parts[3])
            datestring = "%s %02d %04d %04d %s" % tuple(datestring_parts)
        except Exception as e:
            print("filename = " + fname)
            traceback.print_exc()
            raise e

        # create a submission record
        submission = SubmissionType()
        submission.uid = int(fname_parts[0][0])
        submission.name = str(fname_parts[0][1])
        submission.date = datetime.strptime(datestring, "%b %d %Y %I%M %p")
        submission.assignment = str(fname_parts[0][3])
        submission.fname = str(fname)

        # only append to list if first submission
        #     if not, pick latest submission
        if submission.uid in uid_list:
            index = uid_list.index(submission.uid)
            if list_of_submissions[index].date < submission.date:
                list_of_submissions[index] = submission
        else:
            list_of_submissions.append(submission)
            uid_list.append(submission.uid)

    return list_of_submissions

if __name__=='__main__':
    parser = argparse.ArgumentParser(description='Get the unique latest submission for each student from files in directory, based on filename')
    parser.add_argument('--dir', type=str, required=True, help='directory with all the file submissions. filenames are of the format unique_student_id-unique_assignment_id - student_name - submission_datetime - assignment.extension')
    parser.add_argument('--outfile', type=str, required=True, help='output file to write the list of unique file submissions')
    args = parser.parse_args()

    list_of_submissions = get_latest_submissions(args.dir)
    print( "{0} submissions processed".format(len(list_of_submissions)) )

    with open(args.outfile, 'w') as outf:
        outf.write( "\n".join([ realpath("/".join([args.dir,s.fname])) for s in list_of_submissions ]) )
