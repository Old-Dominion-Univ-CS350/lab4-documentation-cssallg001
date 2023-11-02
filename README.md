# WordCounter

There are a number of applications in which it is useful to know what the most
common words in a language might be.  The most reliable way to determine this
is to collect a large sample of written works in that language, make a list of
all the words contained in those works, and count how often each word is seen.

In this project, we present a program intended to count the occurrences of
English words in a collection of plain-text documents.

## Input

The program is intended to be run from the command line like this:

`java -cp` _path-to-wordCounter.jar_ MostCommonWords _N_ _document1_ _document2_ ...

where

* _path-to-wordCounter.jar_ is the path to the jar file containing the compiled program.
* _N_ is the number of words we would like to see reported upon, e.g., if _N_=100, we are seeking to learn the 100 most common words.
* _document1_ _document2_ ... are paths to documents in plain text (ASCII) that will be read as the basis for counting the number of times various words occur.

Multiple text files have been provided in the directory `testData`.  Most of
these are entire books, courtesy of [Project Gutenberg](https://www.gutenberg.org/).

## Output

The program should read the provided input documents, count up the words contained in each, and then print a report to standard out consisting of the _N_ most frequently occurring words. Each line of the report will contain one such word followed b7y the number of times that the word has occurred.

The words are to be printed in order starting with the most frequently occurring, then the second most frequently occurring, and so on.  Ties may be handled in any order.

If _N_ exceeds the number of distinct words encountered in the input documents, then only as many lines are printed as the number of discrete words appearing in the inputs.

## Example

The file `testData/woodchuck.txt` contains:

```
How much wood could a woodchuck chuck if a woodchuck could chuck wood?

As much wood as a woodchuck could chuck, If a woodchuck could chuck wood.
```

If the program is built with the provided makefile and run as

    java -cp wordCounter.jar MostCommonWords 8 testData/woodchuck.txt

the output could be:

```
The 8 most common words are:
  a     4
  woodchuck     4
  could 4
  wood  4
  chuck 4
  as    2
  if    2
  much  2
```

(Because of the multiple ties, the order of output could vary somewhat.)


On the other hand, if the program is run as 

    java -cp wordCounter.jar MostCommonWords 20 testData/*.txt

the output should be:

```
The 20 most common words are:
  the   60247
  and   38935
  to    30509
  of    28703
  a     20924
  I     19703
  in    17063
  he    15620
  was   14024
  that  13968
  his   12401
  it    11695
  you   10880
  with  9976
  had   9523
  her   8421
  not   8351
  as    7874
  at    7855
  but   7213
  ```

  which, overall, seems like a pretty reasonable list.
  