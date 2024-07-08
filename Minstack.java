// Time Complexity : O{1)
//push ,pop, peek, pop : O(1) complexity
// Space Complexity : O(1)
// Did this code successfully run on Leetcode : Yes
// Any problem you faced while coding this : N/A

/*
Design MinStack (https://leetcode.com/problems/min-stack/)

Design a stack that supports push, pop, top, and retrieving the minimum element in constant time.

Implement the MinStack class:

MinStack() initializes the stack object.
void push(int val) pushes the element val onto the stack.
void pop() removes the element on the top of the stack.
int top() gets the top element of the stack.
int getMin() retrieves the minimum element in the stack.
You must implement a solution with O(1) time complexity for each function.


*****************************

Ask interviewer if we can use parent stack or not .?? 
If interviewer  says no parent stack then the datastructures we can use are LinkedList/ArrayList


*******************************
Why not use a variable  to maintain the min element ??? 

WE are maintaining a two stack here as we might call pop action and minimum element might get lost
hence we are maintaining using a minstack here. If we had only taken a variable to check the incoming element 
is min or not with previous element then we would not be able to maintain and loose it & thereby not able to 
keep track of what was the previous minimum.
*******************************


Code Implementation

** Using two stack
We are using two stack one for data elements and other to maintain the min element.
WE do 1:1 mapping. As we push any element to data stack we push the corresponding min element to min stack.
The pop happens we pop the data element and corresponding min from the min stack as well.
We get the minimum by whatever is top at the min stack . 

So by maintaing two stacks or 1:1 mapping we are able to handle the case that if the element which is 
being popped out is our current minimum then we will have the track of what was the previous minimum 
that is whatever is the value at the top of min stack.
We also initilize the min stack to infinity so that when the stack becomes empty and last pop happens, we will be
able to give out infinity as minimum.

More optimization to above is : Only when min is changing or it is equal to current element then only push to min stack otherwise dont push it. 
When pop is  called check if the top of minstack is equal to the current minimum than pop out from data and min stack. IF pop from data
stack is not equal to current minimum then do not pop anything from min stack.

From above , both the methods optimization or 1:1 mapping both are working same. 
So we implement 1:1 mapping using two stacks and optimization one in one stack 

*** Usign one stack
We can also implement using the SINGLE stacks and store the value and its prev minimum below it. 
The min value is pushed only if it is changing and equal to the stack element

*/

// Design minstack using the two stacks

class MinStack{

    Stack<Integer> st;
    Stack<Integer> minSt;
    int min;


    public MinStack(){
        this.min = Integer.MAX_VALUE; // minimum is at infinity
        this.st = new Stack<>();
        this.minSt = new Stack<>();
        this.minSt.push(min);   // initialize minstack to infinity
    }

    public void push(int val){
        min =  Math.min(min,val); // compare with the previous minimum and the value that is coming 
        this.minSt.push(min);
        this.st.push(min); // 1:1 mapping of push
    }

    public void pop(){
        this.st.pop();  // pop values from data stack and min stack
        this.minSt.pop(); 
        min = minSt.peek(); // as minumum has been popped out from min stack, update the min with the current min by getting peek of min stack
    }

    public int top(){
        return this.st.peek(); // whatever  is at the peek 
    }

    public int getMin(){
        return this.min; // return min
    }
}