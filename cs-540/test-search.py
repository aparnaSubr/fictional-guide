import sys
import os
from itertools import izip
import subprocess
from subprocess import check_output, check_call, CalledProcessError

#-----------------------------------------------------------------------------------------------------------------------------------------------------------------------------
def sample_bfs(bfs_output):
    expected = ['BFS', 'Iteration:', '0', '(0,0)', '(3,0)(0,2)', '1', '(0,0)(3,0)', '(3,2)(0,0)(1,2)',\
                '2', '(0,0)(3,0)(0,2)', '(3,2)(0,0)(2,0)', '3', '(0,0)(3,0)(0,2)(3,2)', '(0,2)(3,0)',\
                '4', '(0,0)(3,0)(0,2)(3,2)(1,2)', '(3,2)(0,2)(1,0)(3,0)', 'Result:', '(0,0)(0,2)(2,0)']
    got = [line.strip().replace(' ', '') for line in bfs_output.split('\n')]
    for expect, get in izip(expected, got):
        if '(' in expect and ')' in expect and '(' in get and ')' in get:
            expect_list = expect.split(')')
            get_list = get.split(')')
            for i in expect_list:
                if not i in get_list:
                    print('Expected output :')
                    print(os.linesep.join(expected))
                    print(os.linesep)
                    print('Got :' + os.linesep)
                    print(os.linesep.join(got))
                    return False
    return True

#-----------------------------------------------------------------------------------------------------------------------------------------------------------------------------
def sample_dfs(dfs_output):
    expected = ['DFS', 'Iteration:', '0', '(0,0)', '(3,0)(0,2)', '1', '(0,0)(0,2)', '(3,2)(0,0)(2,0)', 'Result:', '(0,0)(0,2)(2,0)']
    got = [line.strip().replace(' ', '') for line in dfs_output.split('\n')]

    for expect, get in izip(expected, got):
        if '(' in expect and ')' in expect and '(' in get and ')' in get:
            expect_list = expect.split(')')
            get_list = get.split(')')
            for i in expect_list:
                if not i in get_list:
                    print('Expected output :')
                    print(os.linesep.join(expected))
                    print(os.linesep)
                    print('Got :' + os.linesep)
                    print(os.linesep.join(got))
                    return False
    return True

#-----------------------------------------------------------------------------------------------------------------------------------------------------------------------------
def test_bfs(bfs_output, test_case_num):
    expected = [ ['BFS', 'Iteration:', '0', '(0,0)', '(1,0)', 'Result:', '(0,0)(1,0)'], \
                ['BFS', 'Iteration:', '0', '(0,0)', '(2,0)(0,2)', 'Result:', '(0,0)(2,0)'], \
                ['BFS', 'Iteration:', '0', '(0,0)', '(2,0)(0,1)', '1', '(0,0)(2,0)', '(2,1)(0,0)(1,1)', '2', '(0,0)(2,0)(0,1)', \
                '(2,1)(0,0)(1,0)', '3', '(0,0)(2,0)(0,1)(2,1)', '(0,1)(2,0)', '4', '(0,0)(2,0)(0,1)(2,1)(1,1)', \
                '(2,1)(0,1)(1,0)(2,0)', 'Result:', '(0,0)(0,1)(1,0)'], \
                ['BFS', 'Iteration:', '0', '(0,0)', '(4,0)(0,2)', '1', '(0,0)(4,0)', '(4,2)(0,0)(2,2)', '2', '(0,0)(4,0)(0,2)', \
                '(4,2)(0,0)(2,0)', '3', '(0,0)(4,0)(0,2)(4,2)', '(0,2)(4,0)', '4', '(0,0)(4,0)(0,2)(4,2)(2,2)', \
                '(4,2)(0,2)(2,0)(4,0)', '5', '(0,0)(4,0)(0,2)(4,2)(2,2)(2,0)', '(4,0)(2,2)(0,0)(0,2)', 'Result:', 'Unsolvable'], \
                ['BFS', 'Iteration:', '0', '(0,0)', '(6,0)(0,2)', '1', '(0,0)(6,0)', '(6,2)(0,0)(4,2)', '2', \
                '(0,0)(6,0)(0,2)', '(6,2)(0,0)(2,0)', '3', '(0,0)(6,0)(0,2)(6,2)', '(0,2)(6,0)', '4', \
                '(0,0)(6,0)(0,2)(6,2)(4,2)', '(6,2)(0,2)(4,0)(6,0)', '5', '(0,0)(6,0)(0,2)(6,2)(4,2)(2,0)', \
                '(6,0)(2,2)(0,0)(0,2)', '6', '(0,0)(6,0)(0,2)(6,2)(4,2)(2,0)(4,0)', '(6,0)(4,2)(0,0)(2,2)', \
                '7', '(0,0)(6,0)(0,2)(6,2)(4,2)(2,0)(4,0)(2,2)', '(6,2)(0,2)(2,0)(4,0)', 'Result:', 'Unsolvable'], \
                ['BFS', 'Iteration:', '0', '(0,0)', '(7,0)(0,9)', '1', '(0,0)(7,0)', '(7,9)(0,0)(0,7)', \
                '2', '(0,0)(7,0)(0,9)', '(7,9)(0,0)(7,2)', '3', '(0,0)(7,0)(0,9)(7,9)', '(0,9)(7,0)', '4', \
                '(0,0)(7,0)(0,9)(7,9)(0,7)', '(7,7)(0,9)(0,0)(7,0)', '5', '(0,0)(7,0)(0,9)(7,9)(0,7)(7,2)', \
                '(7,9)(0,2)(7,0)(0,9)', '6', '(0,0)(7,0)(0,9)(7,9)(0,7)(7,2)(7,7)', '(7,9)(0,7)(7,0)(5,9)', \
                '7', '(0,0)(7,0)(0,9)(7,9)(0,7)(7,2)(7,7)(0,2)', '(7,2)(0,9)(0,0)(2,0)', '8', '(0,0)(7,0)(0,9)(7,9)(0,7)(7,2)(7,7)(0,2)(5,9)', \
                '(7,9)(0,9)(5,0)(7,7)', '9', '(0,0)(7,0)(0,9)(7,9)(0,7)(7,2)(7,7)(0,2)(5,9)(2,0)', '(7,0)(2,9)(0,0)(0,2)', '10', \
                '(0,0)(7,0)(0,9)(7,9)(0,7)(7,2)(7,7)(0,2)(5,9)(2,0)(5,0)', '(7,0)(5,9)(0,0)(0,5)', '11', \
                '(0,0)(7,0)(0,9)(7,9)(0,7)(7,2)(7,7)(0,2)(5,9)(2,0)(5,0)(2,9)', '(7,9)(0,9)(2,0)(7,4)', '12', \
                '(0,0)(7,0)(0,9)(7,9)(0,7)(7,2)(7,7)(0,2)(5,9)(2,0)(5,0)(2,9)(0,5)', '(7,5)(0,9)(0,0)(5,0)', '13', \
                '(0,0)(7,0)(0,9)(7,9)(0,7)(7,2)(7,7)(0,2)(5,9)(2,0)(5,0)(2,9)(0,5)(7,4)', '(7,9)(0,4)(7,0)(2,9)', '14', \
                '(0,0)(7,0)(0,9)(7,9)(0,7)(7,2)(7,7)(0,2)(5,9)(2,0)(5,0)(2,9)(0,5)(7,4)(7,5)', '(7,9)(0,5)(7,0)(3,9)', '15', \
                '(0,0)(7,0)(0,9)(7,9)(0,7)(7,2)(7,7)(0,2)(5,9)(2,0)(5,0)(2,9)(0,5)(7,4)(7,5)(0,4)', '(7,4)(0,9)(0,0)(4,0)', '16', \
                '(0,0)(7,0)(0,9)(7,9)(0,7)(7,2)(7,7)(0,2)(5,9)(2,0)(5,0)(2,9)(0,5)(7,4)(7,5)(0,4)(3,9)', '(7,9)(0,9)(3,0)(7,5)', '17', \
                '(0,0)(7,0)(0,9)(7,9)(0,7)(7,2)(7,7)(0,2)(5,9)(2,0)(5,0)(2,9)(0,5)(7,4)(7,5)(0,4)(3,9)(4,0)', '(7,0)(4,9)(0,0)(0,4)', \
                'Result:', '(0,0)(7,0)(0,7)(7,7)(5,9)(5,0)(0,5)(7,5)(3,9)(3,0)'] ]
    got = [line.strip().replace(' ', '') for line in bfs_output.split('\n')]
    for expect, get in izip(expected[test_case_num], got):
        if '(' in expect and ')' in expect and '(' in get and ')' in get:
            expect_list = expect.split(')')
            get_list = get.split(')')
            for i in expect_list:
                if not i in get_list:
                    print('Expected output :')
                    print(os.linesep.join(expected[test_case_num]))
                    print(os.linesep)
                    print('Got :' + os.linesep)
                    print(os.linesep.join(got))
                    return False
            return True

#-----------------------------------------------------------------------------------------------------------------------------------------------------------------------------
def test_dfs(dfs_output, test_case_num):
    expected = [ ['DFS', 'Iteration:', '0', '(0,0)', '(1,0)', 'Result:', '(0,0)(1,0)'], \
                 ['DFS', 'Iteration:', '0', '(0,0)', '(2,0)(0,2)', '1', '(0,0)(0,2)', '(2,2)(0,0)(2,0)', 'Result:', '(0,0)(0,2)(2,0)'], \
                 ['DFS', 'Iteration:', '0', '(0,0)', '(2,0)(0,1)', '1', '(0,0)(0,1)', '(2,1)(0,0)(1,0)', 'Result:', '(0,0)(0,1)(1,0)'], \
                 ['DFS', 'Iteration:', '0', '(0,0)', '(4,0)(0,2)', '1', '(0,0)(0,2)', '(4,2)(0,0)(2,0)', '2', '(0,0)(0,2)(2,0)', \
                 '(4,0)(2,2)(0,0)(0,2)', '3', '(0,0)(0,2)(2,0)(2,2)', '(4,2)(0,2)(2,0)(4,0)', '4', '(0,0)(0,2)(2,0)(2,2)(4,0)', \
                 '(4,2)(0,0)(2,2)', '5', '(0,0)(0,2)(2,0)(2,2)(4,0)(4,2)', '(0,2)(4,0)', 'Result:', 'Unsolvable'], \
                 ['DFS', 'Iteration:', '0', '(0,0)', '(6,0)(0,2)', '1', '(0,0)(0,2)', '(6,2)(0,0)(2,0)', '2', '(0,0)(0,2)(2,0)', \
                 '(6,0)(2,2)(0,0)(0,2)', '3', '(0,0)(0,2)(2,0)(2,2)', '(6,2)(0,2)(2,0)(4,0)', '4', '(0,0)(0,2)(2,0)(2,2)(4,0)', \
                 '(6,0)(4,2)(0,0)(2,2)', '5', '(0,0)(0,2)(2,0)(2,2)(4,0)(4,2)', '(6,2)(0,2)(4,0)(6,0)', '6', \
                 '(0,0)(0,2)(2,0)(2,2)(4,0)(4,2)(6,0)', '(6,2)(0,0)(4,2)', '7', '(0,0)(0,2)(2,0)(2,2)(4,0)(4,2)(6,0)(6,2)', \
                 '(0,2)(6,0)', 'Result:', 'Unsolvable'], \
                 ['DFS', 'Iteration:', '0', '(0,0)', '(7,0)(0,9)', '1', '(0,0)(0,9)', '(7,9)(0,0)(7,2)', '2', '(0,0)(0,9)(7,2)', \
                 '(7,9)(0,2)(7,0)(0,9)', '3', '(0,0)(0,9)(7,2)(7,0)', '(7,9)(0,0)(0,7)', '4', '(0,0)(0,9)(7,2)(7,0)(0,7)', \
                 '(7,7)(0,9)(0,0)(7,0)', '5', '(0,0)(0,9)(7,2)(7,0)(0,7)(7,7)', '(7,9)(0,7)(7,0)(5,9)', '6', \
                 '(0,0)(0,9)(7,2)(7,0)(0,7)(7,7)(5,9)', '(7,9)(0,9)(5,0)(7,7)', '7', '(0,0)(0,9)(7,2)(7,0)(0,7)(7,7)(5,9)(5,0)', \
                 '(7,0)(5,9)(0,0)(0,5)', '8', '(0,0)(0,9)(7,2)(7,0)(0,7)(7,7)(5,9)(5,0)(0,5)', '(7,5)(0,9)(0,0)(5,0)', '9', \
                 '(0,0)(0,9)(7,2)(7,0)(0,7)(7,7)(5,9)(5,0)(0,5)(7,5)', '(7,9)(0,5)(7,0)(3,9)', '10', '(0,0)(0,9)(7,2)(7,0)(0,7)(7,7)(5,9)(5,0)(0,5)(7,5)(3,9)', \
                 '(7,9)(0,9)(3,0)(7,5)', 'Result:', '(0,0)(0,9)(7,2)(7,0)(0,7)(7,7)(5,9)(5,0)(0,5)(7,5)(3,9)(3,0)'] ]
    got = [line.strip().replace(' ', '') for line in dfs_output.split('\n')]

    for expect, get in izip(expected[test_case_num], got):
        if '(' in expect and ')' in expect and '(' in get and ')' in get:
            expect_list = expect.split(')')
            get_list = get.split(')')
            for i in expect_list:
                if not i in get_list:
                    print('Expected output :')
                    print(os.linesep.join(expected[test_case_num]))
                    print(os.linesep)
                    print('Got :' + os.linesep)
                    print(os.linesep.join(got))
                    return False
    return True

#-----------------------------------------------------------------------------------------------------------------------------------------------------------------------------
def main():
    total_points = 95
    
    cwd = os.getcwd()
    list_of_files = os.listdir(cwd)
    
    compiled_success = False
    submitted_filename = sys.argv[1]
    
    # 5 points for submitting code
    # Do not deduct 

    # 5 points for naming file Search.java
    if not 'Search.java' in list_of_files and not 'Search.class' in list_of_files:
        print('Could not find Search.java : -5 points' + os.linesep)
        total_points -= 5
    
    # 5 points for compiling without errors
    try:
        command = 'javac *.java'
        output = check_call(command, shell=True)
        print('Compiled files successfully.')
        compiled_success = True
    except CalledProcessError, e:
        total_points -= 5
        print('Compilation failed : -5 points')
        
    # 10 points for sample BFS
    if compiled_success is True:
        command = 'java ' + submitted_filename + ' 3 2 2'
        output = check_output(command, shell=True)
        bfs_output = output.split('DFS')[0]
        if sample_bfs(bfs_output) is True:
            print('Sample BFS run passed.')
        else:
            total_points -= 10
            print('Sample BFS run failed : -10 points')

    else:
        total_points -= 10
        print('Could not test sample BFS run.')

    # 10 points for sample DFS
    if compiled_success is True:
        command = 'java ' + submitted_filename + ' 3 2 2'
        output = check_output(command, shell=True)
        dfs_output = 'DFS' + output.split('DFS')[1]
        if sample_dfs(dfs_output) is True:
            print('Sample DFS run passed.')
        else:
            total_points -= 10
            print('Sample DFS run failed : -10 points' + os.linesep)
    else:
        total_points -= 10
        print('Could not test sample DFS run.' + os.linesep)

    # 6 test cases, 10 points each
    commands = ['1 0 1', '2 2 2', '2 1 1', '4 2 3', '6 2 5', '7 9 3']
    if compiled_success is True:
        for i in range(6):
            command = 'java ' + submitted_filename + ' ' + commands[i]
            output = check_output(command, shell=True)
            bfs_output = output.split('DFS')[0]
            if test_bfs(bfs_output, i) is True:
                print('BFS Test case {0} passed.'.format(i))
            else:
                total_points -= 5
                print('BFS run failed for Test case {0} : -5 points'.format(i))

            dfs_output = 'DFS' + output.split('DFS')[1]
            if test_dfs(dfs_output, i) is True:
                print('DFS Test case {0} passed.'.format(i))
            else:
                total_points -=5
                print('DFS run failed for Test case {0} : -5 points'.format(i))
    else:
        total_points -= 60
        print('Could not run submitted program.' + os.linesep)

    print('TOTAL POINTS : {0}'.format(total_points))
    # 5 points for reading command line arguments
    print('Checking command line arguments...')

if __name__ == '__main__':
    main()
