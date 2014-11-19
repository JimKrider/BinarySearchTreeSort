BinarySearchTreeSort
====================

Binary Search Tree - Sort

The two methods that are used to sort the Binary Tree to become a Binary Search Tree.

`btSort` Takes a binary tree which is the Intcoll6 obj, and puts that into an array.
```java
public void btSort(Intcoll6 obj)
    {
        int[] sort = new int[obj.howmany];   
        toarray(obj.c,sort,0);
        
        c = sortedArrayToBST(sort,0,sort.length-1);//sets this.c to the sorted tree
      
    }
```
`sortedArrayToBST` takes the array and puts the elemenets into a Nodes of a binary tree.

`root.left` is set to all the numbers to the left of the mid point from `start ` to  `mid-1`.

`root.right` is set to all the numbers to the right of the mid point from `mid+1` to  `end`. 


```java
private btNode sortedArrayToBST(int[] a, int start, int end)
    {
       if(start > end)
            return null;
       int mid       = (start + end)/2;
       btNode root   = new btNode(a[mid], null, null);howmany++;
       root.left     = sortedArrayToBST(a,start,mid-1);
       root.right    = sortedArrayToBST(a,mid+1,end);
       return root;
    }
```
