### Total Score: 18/25

### Answers Score: 18/20
- Problem 1a: 5/7

INTQUEUE2_AF
The abstraction function for `IntQueue2` ought to be:
```
AF(this) = [..., entries[(i + front) % entries.length], ...]
           for front <= i < size
```
INTQUEUE2_AF

INTQUEUE2_RI
The representation invariant for `IntQueue2` ought to be:
```
entries != null && 0 <= front < entries.length && 0 <= size <= entries.length
```
INTQUEUE2_RI


- Problem 1b: 1/1
- Problem 1c: 6/6
- Problem 2: 3/3
  Think carefully about if you need the delete operation or not.
- Problem 3: 3/3

### Following Directions Score: 0/5

IMPLEMENTATION
You included some implementation details.  These may include private methods,
private inner classes, private fields, abstraction functions, and representation
invariants.
IMPLEMENTATION



The below scores are separate:

### Documentation Score: 2/3

modifies and effects should not reveal private fields

### Design Score: 3/3

### Testing Score: 3/3

#### Code Review Feedback

None.
