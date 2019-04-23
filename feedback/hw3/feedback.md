### Total Score: 22/25

### Answers Score: 12/15
- Fibonacci: 5/5
- Ball: 1/1
- BallContainer: 2/4
The advantage of computing the volume only when necessary is that other methods
that change the conceptual total volume in the BallContainer don't have to worry
about updating a field that tracks the total volume.  This advantage is
particularly noticable when you begin to extend the BallContainer class to
include more such methods.  You are more prone to error when you have to keep
the unrelated concept of total volume in your head at the same time as
implementing new methods.

Another minor advantage is that performance is better in the case that the
volume is seldom accessed.  In this case, the second implementation is keeping
track of the total volume for little reason.

- Box: 4/5
The advantage of the implementation with the field is that repeated calls to
getBallsFromSmallest will be more efficient.

### Style Score: 10/10
When selecting a greeting in `RandomHello`, the best style would use the length
of the array to specify the maximum value for the random integer generation:
```
String nextGreeting = greetings[rand.nextInt(greetings.length)];
```
Notice how this benefits us later on if we wanted to change the number of
possible greetings in the array.