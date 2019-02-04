Sort Unix Command
=================
An implementation of the UNIX-command sort [options] <files> | uniq for the identifiers in the given files. This program finds all identifiers in the supplied files that occur an uneven number of times, sort those, and then print them on standard output (the screen).
By default the program should sort identifiers monotonically non-decreasing, differentiating between upper and lower case letters. In other words, the programis case sensitive by default.

Implementation details
======================
The identifiers are sorted by inserting them to a binary search tree and returning an iterator(descending or ascending) which the user could use to print the sorted tree. This implementation has its own Binary search tree implementation and own Iterator class implementation.

Options
========
Options of the program 
- "-i" (case insensitive) is passed, the program should not differentiate between upper and lower case letters. Output should be given in lowercase.
- "-d" (descending) is passed, the program should sort the identifiers contained in the files in monotonically non-increasing order.





