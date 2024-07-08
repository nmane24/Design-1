// Design minstack using the SINGLE stacks and store the value and its prev minimum below it. 
//The min value is pushed only if it is changing and equal to the stack element


class MinStack{
Stack<Integer> st;
int min;


public MinStack(){
    this.min = Integer.MAX_VALUE;
    this.st = new Stack<>();
}

public void push(int val){
    if(val <= min){
        st.push(min);
        min = val;
    }
    this.st.push(val);
}

public void pop(){
    if(st.pop() == min){
        min = st.pop();
    }
}

public int top(){
 return this.st.peek()
}

public int geMin(){
    return this.min;
}

}