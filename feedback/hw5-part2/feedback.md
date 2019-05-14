### Total Score: 9/10
- Problem 1: 6/7
How would an adjacency matrix make finding a path more straightforward? It does have a constant time
edge lookup but you would still need to implement some algorithm to find a path between nodes.

Also, it is unclear what you mean by unnecessary information (aka every edge they do not connect to).

- Problem 4: 3/3

The below scores are separate:

### Implementation Score: 1/3
Representation invariant is missing elements: can list of nodes contains duplicate? can an edge in the
graph connects nodes that don't exist in the list of nodes?

It might be better if your implementation takes care of more things for the client. For example, why does
your add edge method just create and return a new edge? Do you expect your client to keep track of the
edges on their own?

### Design Score: 1/3
Graph does not need to be able to sort things as it is not a common specification or responsibility of a
graph

There might be methods that are better be private (for example addOutGoing and addInComing) to avoid
exposure. Notice that your listNodes method actually returning the same graphNode objects in your field 
and they are mutable by these methods). You might want to think again about the methods that your clients
actually need to have access to.

### Testing Score: 3/3

#### Code Review Feedback

None.

#### Graded By: Sherry Prawiro