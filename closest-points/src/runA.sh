#!/bin/sh

for FILE in ../data/*-tsp.txt

do
    base=${FILE%-tsp.txt}
    fileName=${FILE%.txt}
    java CP $fileName >> out.txt
done
