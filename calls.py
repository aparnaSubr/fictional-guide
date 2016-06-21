import os
import sys
from subprocess import call
import shutil

status = call('pwd', shell=True)
files = [f for f in os.listdir('/u/a/p/aparnasubr/Downloads/summer-grading/trial/') if os.path.isfile(os.path.join('/u/a/p/aparnasubr/Downloads/summer-grading/trial/',f))]
print files
for i in files:
	shutil.copyfile(os.path.join('/u/a/p/aparnasubr/Downloads/summer-grading/trial/',i), os.path.join('/u/a/p/aparnasubr/Downloads/summer-grading/trial/Grades','DigitsInANumber.java'))
	print "\n\nGrading {0}".format(i)
	try:
		#command = 'java DigitsInANumber < <(printf \"12\\r\")'.decode('utf-8')
		#print command
		#status = call('javac DigitsInANumber.java', shell=True)
		#status = call('java DigitsInANumber < <(printf \"12\\r\")', shell=True)
		#status = call(command, shell=True)
		command = "bash " + os.path.join('/u/a/p/aparnasubr/Downloads/summer-grading/trial/Grades', 'script.sh')
		#command = command + " >> " + os.path.join('/u/a/p/aparnasubr/Downloads/summer-grading/trial/Grades', (i+'.txt'))
		destination = os.path.join('/u/a/p/aparnasubr/Downloads/summer-grading/trial/Grades', i)
		destination = destination + ".txt"
		with open(destination, 'w') as dst:
			status = call(command, shell=True, stdout=dst)
	except:
		e = sys.exc_info()
		print e
	else:
		dst.close()
		print "Done grading {0}".format(i)
		
