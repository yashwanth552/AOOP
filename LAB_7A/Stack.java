// Define a Generic Stack Interface
interface Stack<T> {
    void push(T element);  // Add an element to the top of the stack
    T pop();               // Remove and return the top element of the stack
    T peek();              // Return the top element without removing it
    boolean isEmpty();     // Check if the stack is empty
}
