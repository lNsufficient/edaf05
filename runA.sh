#!/bin/sh
for FILE in data/*-in.txt

do
    base=${FILE%-in.txt}
    fileName=${FILE%.txt}
    fileName=${fileName#data/}
    echo $fileName
    java -cp 'bin/' Matching.GS $fileName
    echo "DIFFS:"
    diff $base-outNEW.txt $base-out.txt
done
