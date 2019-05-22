### Total Score: 3/5
- Documenting Changes: 1/1
- Command Line Application: 2/4

- Does not work in simple cases.
- Application is really slow.

The below scores are separate:

### Specification Score: 3/3

### Implementation Score: 3/3

- Don't use `System.exit` in non-main methods, because this makes clients not able
to recover from something like `FileNotFoundException`.

### Design Score: 3/3

### Testing Score: 1/3

- You need to have more implementation tests that test various cases.

#### Code Review Feedback

None.

#### Graded By: Aditya Jhamb