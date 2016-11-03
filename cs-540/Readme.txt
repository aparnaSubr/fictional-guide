Initializing the grading directory:

1. Download the latest student submissions from Learn@UW and extract/save the files into a directory (eg. grading/hw3/<student-submissions.java> ). Delete the /grading/hw3/index.html file.
2. Download and save formatFileName.py and test-search.py in the same directory. (eg. grading/formatFileName.py, grading/test-search.py)
3. Run "python formatFileName.py <path-to-grading-directory>".

Note: 
/grading/hw3/ will contain all the Search.java files (or zip submissions, for the few students who submitted zip files). 
Running formatFileName.py will create a separate directory for each student and place all their submitted files into the relevant directory. 
Zip files will be extracted into the student's directory. 
test-search.py will be copied into each student's directory for ease of use.





Grading:

1. For each student, make sure test-search.py is in the same directory as their .java/.class files. 

2. Run "python test-search.py Search" (optionally python test-search.py Search > feedback.txt)
Note: In case the submission is not named Search, use python test-search.py <filename>. If the submission compiles successfully, this should allow the test script to grade the program.
We might need to manually check compilation issues with incorrect filenames if this fix does not work.

3. The script is designed to check output in the following manner:

	-	Execute student's submission and capture output
	-	Compare ONLY the CLOSED and OPEN data structures (the test-script handles cases where out-of-order printing occurs, since the assignment did not enforce printing in order)
	-	Checks BFS and DFS output separately (sample runs for each are scored at +10 points. The additional test cases are scored at 5 points BFS, 5 points DFS each)
	-	The script does not check other printed lines (eg. BFS/DFS, Iteration :, etc.)

4. Feedback for each test case will be printed onto the console (or file, if output is redirected). This should (hopefully) make it easier for us to give detailed feedback.

5. Checking if the submission uses command line arguments will need to be done manually (only in cases where the test cases fail, since the tests assume that command line arguments are correctly used). 
Please do not forget to add 5 points to the total score if the command line arguments are used correctly! The tests currently add up to 95 points.

