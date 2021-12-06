# TOP-DOWN CLUSTERING 

ex 37/841


## Problem

**In** : 
    
    S - set of instances 
    d - distance measure on SxS

**Out**:
    
    - a dendrogram (binary clusterization tree:
            
            1. leaves: elements of S
            2. maximum cohesion in every cluster
            3. maximum distance between clusters

## Algorithm

1. Compute distances between any two points in S
    
    Create undirected complete and weighted graph G, in which:
     leaves = elements of S and weight of edge E( = (x,y))  = d(x,y)
   
2. Compute MST (*minimum spanning tree*) of G, using either
*Kruskal's algorithm* or *Prim's algorithm*
   
3. Eliminate from the MST the edge with the maximum cost =>
    obtaining two trees(corresponding to the two top clusters)
   
4. Repeat **3** 