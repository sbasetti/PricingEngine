The following Rule 2 when applied, obtains prices which are 50% accurately. And output not tallying with given samples.
Rule 1: Prices less than 50% of average price are treated as promotion and not considered.
Rule 2: Prices more than 50% of average price are treated as data errors and not considered.

I happen to change rule as follows, and verified sample data:
Prices more than 500% of average price are treated as data errors and not considered.


Output must be recommended price for each product.


Input-1:
2
flashdrive H H
ssd L H
5
flashdrive X 1.0
ssd X 10.0
flashdrive Y 0.9
flashdrive Z 1.1
ssd Y 12.5
 
Output 1:
A 0.9
B 10.5


