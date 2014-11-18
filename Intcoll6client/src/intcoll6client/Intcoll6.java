/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package intcoll6client;
import java.io.*;

/**
 *
 * @author Jimbo
 */
public class Intcoll6 {
    private int howmany;
    private btNode c;


    public Intcoll6()
    {
      c = null;
      howmany = 0;
    } 
    
    public Intcoll6(int i)
    {
      c = null;
      howmany = 0;
    }   
    
    private static btNode copytree(btNode t)
    {
      btNode root = null;
      if (t != null)
      {
         root       = new btNode();
         root.info  = t.info; 
         root.left  = copytree(t.left);
         root.right = copytree(t.right);
      }
      return root;
    }   
    
    public void copy(Intcoll6 obj)
    {
      if (this != obj)
      {
          howmany = obj.howmany;
          c = copytree(obj.c);  
      }
    }   
    
    public void insert(int i)
    {
      btNode pred = null, p = c;
      while ((p != null)&&(p.info != i))
      {
            pred = p;
            if (p.info > i) p = p.left; //If i is less then Current node go LEFT
            else p = p.right;           //Else go right
      }
      if (p == null)                    //If the node is null
      {
         howmany++; p = new btNode(i, null, null);//create a new one
        if (pred != null)
        {
            if (pred.info > i) pred.left = p;
            else pred.right = p;
        }
        else c = p;
      }
    }   
    
    public void omit(int i)
    {
        btNode p = c, predp = null;
       while (( p != null )&&( p.info != i ))
        {
            predp = p;
            if (p.info > i) {p = p.left; System.out.println("L");}//Prints L for Left I used it to check 
            else{ p = p.right; System.out.println("R");}//Prints R for Right I use it to check not really needed
        }
       
        if((p.left == null)&&(p.right == null)){ //Leaf Node
            if(predp == null){
               c = null;           
            }else if(predp.info > p.info){        //Go Left if the Previous or Parent is greater then P
               predp.left  = null;
            }else if(predp.info < p.info){           //Else go right
               predp.right = null;
            }
        }else if(p.left == null){
            if(predp == null){
               c = p.right;
            }else if(predp.info > p.info){
               predp.left  = p.right;
            }else{
               predp.right = p.right;
            }
        }else if(p.right == null){
            if(predp == null){
               c = p.left;
            }else if(predp.info > p.info){
               predp.left  = p.left;
            }else{
               predp.right = p.left;
            }
        }else if((p.left != null)&&(p.right != null)){ 
            if(predp == null){
                btNode max = p.left,maxpred = null;
                while(max.right != null){
                    maxpred = max;
                    max = max.right;
                }           
            c.info/*<-----*/= max.info;
            maxpred.right   = max.left;
            c.right/*<----*/= p.right;
            c.left/*<-----*/= p.left;
            }else if(predp.info > p.info){
                btNode min = p;                
                while(min.left != null){
                   min = min.left;
                }
               predp.left = min;
               min.right  = p.right;
            }else{
               btNode min = p;
               while(min.left != null){
                   min = min.left;
                }
              predp.right = min; 
              min.right = p.right;
            }
        }//End of Inner If statements 
    }//End of Omit Method   
    
    public boolean belongs(int i)
    {
      btNode p = c;
      while ((p != null)&&(p.info != i)) 
      {
         if (p.info > i) p = p.left;
         else p = p.right;
      }
      return (p != null); 
    }//End of Belongs Method   
    
    public int get_howmany() {return howmany;}//End of get_howmany   
    
    public void print(String outname)
    {
      try
      {
	 PrintWriter outs =new PrintWriter(new FileOutputStream(outname));
         outs.println("The number of integers is "+howmany);
         outs.println();
         printtree(c, outs);
         outs.close();
      }catch (IOException ex)
      {
      }
    }//End of print that works with outs
    
    public void btSort(Intcoll6 obj)
    {
        int[] sort = new int[obj.howmany];   
        toarray(obj.c,sort,0);
        //quicksort(sort,0,sort.length-1);      //Sorts the tree using quick
        c = sortedArrayToBST(sort,0,sort.length-1);//sets this.c to the sorted tree
      
    }
    
    private btNode sortedArrayToBST(int[] a, int start, int end)
    {
       if(start > end)
            return null;
        int mid       = (start + end)/2;
        btNode root   = new btNode(a[mid], null, null);howmany++;
       root.left     = sortedArrayToBST(a,start,mid-1);
       root.right    = sortedArrayToBST(a,mid+1,end);
       
       return root;
    }/* Cuts the array in half then sorts. */
    
    public static void quicksort(int a[], int l, int h)
    {
        int p;
        if((h - l) > 0){
            p = partition(a,l,h);
            quicksort(a,   l, p-1);
            quicksort(a, p+1, h);
        }
    }/* Not Being used Currently Fix quickSortBst first */
    
    public static int partition( int data[], int l, int h)
    {
        int upper;
        int lower;
        int firsthigh;
        upper = l; lower = h; firsthigh = data[l];
        while(upper != lower){
            while((upper < lower)&&(firsthigh >= data[lower]))
                   lower--;
                if(upper != lower){
                   data[upper] = data[lower];
                }             
            while((upper < lower)&&(firsthigh <= data[upper]))
                   upper++;
                if(upper != lower){
                   data[lower] = data[upper];
                }
          
        }       
        data[upper] = firsthigh;
        return(upper);
    }/* Not Being used Currently Fix quickSortBst first */
    
    private btNode quickSortBST(int a[], int start, int end)
    {
       int p; btNode root =null;
       
       if((end - start) > 0){
       p = partition(a,start,end);
       root = new btNode(a[p], null, null);howmany++;
       root.left     =  quickSortBST(a,   start, p-1);
       root.right    =  quickSortBST(a,     p+1, end);
           
        }
       
       return root;
    }/* Does not Print correctly, it is weird. Will Fix Later Use sortedArrayToBST for now */
    
    public void print()
    {
      printtree(c);
      if(c == null){System.out.println(c);}
    }//End of print    
    
    public boolean equals(Intcoll6 obj)
    {
      int j; boolean result  = (howmany==obj.howmany);
      if (result)
      { 
         int[] a = new int[howmany]; 
         int[] b = new int[howmany];
         toarray(c, a, 0); toarray(obj.c, b, 0);

         j=0;
         while ((result) && (j<howmany))
         {
            result=(a[j]==b[j]); j++;
         }
      }
      return result;
    }//End of Equals Method   
    
    private static void printtree(btNode t, PrintWriter outs)
    {   
	if (t!=null)
	{
            printtree(t.left, outs);
            outs.println(t.info );
            printtree(t.right, outs);
	}
    }//End of Printtree method for PrintWriter OUTS   
    
    private static void printtree(btNode t)
    {    
      if(t != null)
      {  
        printtree(t.left);
        System.out.println(t.info);
        if(t.right != null)
            System.out.println("Right " + t.right.info);
        else
            System.out.println("Right is null" );
        if(t.left != null)
            System.out.println("Left " + t.left.info);
        else
            System.out.println("Left is null" );
        printtree(t.right);
      } 
    }//End of Printtree method   
    
    private static int toarray(btNode t, int[] a, int i)
    {
      int num_nodes = 0;int r=0,l=0;
      if (t != null)
      { 
        num_nodes      = toarray(t.left, a, i);l=i;
        a[num_nodes+i] = t.info; r=i;
        num_nodes      = num_nodes + 1 + toarray(t.right, a, num_nodes+i+1);
       }
      return num_nodes;
    }//End of toarray method
    
    private static class btNode
    {
       int info; btNode left; btNode right;

       private btNode(int s, btNode lt, btNode rt)
       {
        info = s; left = lt;   right = rt;  
        }

       private btNode()
       {
        info = 0; left = null; right = null;
        }
    }//End of Class btNode
}
