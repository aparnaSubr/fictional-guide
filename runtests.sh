# !bin/bash
python formatFileName.py $1
./createGradingDirectory.sh $1 $2
python tests.py $1 $2
