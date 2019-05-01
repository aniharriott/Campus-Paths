### Total Score: 25/36

### Answers Score: 19/26
- Pseudocode (Problem 0): 7/8

Missing case for division loop condition

- Altered Rep Invs (Problems 1a, 2b, and 2c): 4/6

The constructor does not have to be changed for 1a and 2b.
In general, checkRep must be changed alongside the rep invariant.

- Mutability (Problem 1b): 2/2
- checkRep Usage (Problems 1c, 2a, and 3a): 4/6

Being immutable by specification does not guarantee actual immutability.
RatNum is guaranteed to be immutable because of the `final` keyword (so it
is immutable through the Java compiler).

- RatPoly Design (Problem 3b): 2/4

Missing advantage of using 2 lists

### Code Style Score: 6/10

#### Specific Feedback

Declaring a new variable and setting it equal to a private field does not
necessarily make a copy of that field. Rep exposure can still happen!

#### General Feedback

Did not write loop invariants for non trivial loops.

Needs more comments in method bodies, especially for longer methods.
