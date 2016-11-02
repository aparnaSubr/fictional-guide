import sys
import os
import string
import itertools

if len(sys.argv) != 4:
	print "Usage: python compareFiles.py <path to source directory> <name of grading directory> <name of student>"


#listOfFiles = [f for f in os.listdir(os.path.join(sys.argv[1], sys.argv[2])) if os.path.isfile(os.path.join(sys.argv[1], sys.argv[2], f)) and f.endswith(".txt")]
#print listOfFiles 

#####################################################################################################################
# Read expected output file
#####################################################################################################################
with open('sample_output', 'r') as fd:
	try:
		expected = (line.rstrip() for line in fd)
		expected = list(line for line in expected if line)
	finally:
		fd.close()

print len(expected)

'''
#####################################################################################################################
# Get expected output in comparable form
#####################################################################################################################
for i in range(0,len(expected)):
	line = expected[i]
	line = line.translate(string.maketrans("",""), string.punctuation)
	line = line.lower().replace(" ","")
	expected[i] = line

#print expected
'''

#####################################################################################################################
# Get submitted output in comparable form
#####################################################################################################################
filename = os.path.join(sys.argv[1], sys.argv[2], sys.argv[3])# + ".txt"
with open(filename, 'r') as fd:
	try:
		output = (line.rstrip() for line in fd)
		output = list(line for line in output if line)
	finally:
		fd.close()

print len(output)

'''
#####################################################################################################################
# Get student output in comparable form
#####################################################################################################################
for i in range(0,len(output)):
	line = output[i]
	line = line.translate(string.maketrans("",""), string.punctuation)
	line = line.lower().replace(" ","")
	output[i] = line
#print output
'''

#####################################################################################################################
# Compare outputs
#####################################################################################################################
print "\nEvaluating submission for {0}...\n".format(sys.argv[3])
if len(expected) != len(output):
    print "Number of lines inconsistent."
    sys.exit(0)

lineNum = 0
for exp,got in itertools.izip(expected, output):
	lineNum += 1
	i = exp.lower().replace(" ","").translate(string.maketrans("",""), string.punctuation)
	j = got.lower().replace(" ","").translate(string.maketrans("",""), string.punctuation)
	if i != j:
		print "Line {0}".format(lineNum)
		print "Expected: {0}".format(exp)
		print "Got: {0}".format(got)
		print "\n"

print "Complete.\n"
