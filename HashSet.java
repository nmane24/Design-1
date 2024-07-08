// Design HashSet

// Time Complexity : O{1) avg
// Space Complexity : O(1) avg
// Did this code successfully run on Leetcode : Yes

/*
Problem 1:(https://leetcode.com/problems/design-hashset/)

Design a HashSet without using any built-in hash table libraries.

Implement MyHashSet class:

void add(key) Inserts the value key into the HashSet.
bool contains(key) Returns whether the value key exists in the HashSet or not.
void remove(key) Removes the value key in the HashSet. If key does not exist in the HashSet, do nothing.
*/


/*
 * Hashing based data structure 
 * Advantage :serach any key in O(1), it is very very fast. 
 * 
 * Hashset
 * Underlying data structure is  array, choosing a proper hash function is important to avoid collision problem.
 * To avoid wastage of space and collision occuring at the index if only 1d array used we maintain 
 * an array as the secondary data structure. This 2ndary data structure stores boolean values. 
 * so an Array is used to store poniters to boolean arrays. (kind of 2-d array but we are not allocating 
memory to all memory locations in this example in class constructor. memory will be allocated as requirement
in add function).

This problem be solved using single 1-D array but it will not require any 
hash function. 2D array does not store memory in contiguous manner which gives flexibility to Memory management unit.

Data set is given 0 to 1,000,000
2D array is taken of size [1000][1000]; which is roughly square root of 1,000,000.
first hash function is key%1000 and second hash function is key/1000


null | null | null | null | .......
   0     1     2     3
         |
         |
         V
         
   0   false
         ---
   1    false
         ---
   2    false
         -----
   3     16
         ------
   4     21
         ----


We are not storing actual interger values in hashset here, index itself can be treated as data.
If value at specific index is TRUE that means data is presnet in hashmap.
Using bool array instead of interger array reduces space requirement by four times.
1 interger needs 4 bytes, 1 boolean need 1 bytes.

Example search of key = 20, it will do 20 % 5 = 0. At 0 location it see value as null, it will return false.
Lets say we search key = 21, First hash function 21 % 5 = 1 . At 1st index we found a value  
then 2nd hash function is performed 21 / 5 = 4, If we check 21 is present at that index. Same Remove for key = 21 O(1)

***************************
How is space optimization is achieved in Double Hashing Technique. ?

The space optimization in above DOUBLE HASHING TECHNIQUE is achieved because unless the indexes of primary array are been 
accessed, they are not initialized and thus keep them pointing to null as default and not wasting any memory.
 IF and only if data is coming up we will point to 2nd array otherwise not required


 ************************************
 */


 class MyHashSet{

      private boolean[][] storage;
      private int bucket; // size of primary array
      private int bucketItems; // size of secondary array
     
      public MyHashSet(){
        this.bucket = 1000; // primary array 
        this.bucketItems = 1000; 
        this.storage = new boolean[bucket][];
      }
     
      private int hash1(int key){
         return key % this.bucket;
      }
     
      private int hash2(int key){
         return key / this.bucketItems;
      }
     
      public void add (int key){
         int bucket = hash1(key);
         int bucketItem = hash2(key);
         if (storage[bucket] == null){ //check if primary array contains that key at index or not null
             if (bucket == 0){
                 storage[bucket] == new boolean[bucketItems + 1];
             }
             else{
                 storage[bucket] = new boolean [bucketItems]; // initialize the secondary array here
             }
         }
         storage[bucket][bucketItem] = true; // update the value in secondary array to true
                 
      }
     
      public void remove(int key){
         int bucket = hash1(key);
         int bucketItem = hash2(key);
     
         if(storage[bucket] == null) return;
         storage[bucket][bucketItem]= false;
      }
     
      public boolean contains(int key){
         int bucket = hash1(key);
         int bucketItem = hash2(key);
     
         if (storage[bucket] == null) return false;
         return storage[bucket][bucketItem];
      }
     
     }