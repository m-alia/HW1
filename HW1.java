
/*
 * *** Malia Kuykendall / COMP 272/400C-001 ***
 *
 * Homework # 1 (Programming Assignment). This Java class defines some basic
 * manipulation operations on Linked-Lists and Stacks.
 *
 * Additionally, there are two algorithm analysis methods where you need
 * to return a specified number as provided in comments of each method indicating
 * the complexity of the code shown. The testing routine will be looking for a
 * specific number returned.
 */

import java.util.Stack;

public class HW1 {

    /*
     * Class LinkedList
     *
     * This class builds a singly linked list. Each node of the linked-list
     * contains a single integer values.
     *
     * Methods:
     *  - void   sortInserted(val)     - Inserts value 'val' into the linked-list in
     *                                   sorted fashion
     *  - void   removeElementsLT(val) - Removed values in the linked-list that are less
     *                                   than 'val'
     *  - void   removeElement(val)    - Removes all values in the linked list of
     *                                   value 'val'
     *  - String toString()            - coverts and returns the linked-lists as a string
     *                                   delimited by brackets []
     *
     */

    static class LinkedList {
        static class Node {
            int data;
            Node next;

            Node(int d)  {        // Constructor
                data = d;
                next = null;
            }
        }
        Node head;                // head of Linked-list


        /*
         * Method sortedInsert() - this method will insert a new node to the
         * linked list containing the value specific in the parameter 'data'.
         * The newly inserted node will be inserted in sorted order within
         * the linked-list.
         *
         */
        public void sortedInsert ( int data ) {
            Node new_node = new Node(data);

            new_node.next = null;

            // Special case for head node.
            if (this.head == null || head.data >= new_node.data ) {
                new_node.next = head;
                head = new_node;
            } else {
                // locate the node before the point of insertion
                Node current = this.head;

                // Identify where to place the item to insert
                while (current.next != null && current.next.data < data) {
                    current = current.next;
                }
                new_node.next = current.next;
                current.next = new_node;
            }
            return;
        }


        /*
         * Method removeElementsLT() - this method removes all nodes that contain a
         * value that is less than the provided parameter 'ltValue'.
         *
         * The method will invoke the method removeElements for each element
         * found in the linked-list that is less than the parameter value passed.
         */
        public void removeElementsLT ( int ltValue ) {
            // case for head item
            while (this.head != null && this.head.data < ltValue) {
                removeElement(this.head.data);
            }
            Node cursor = this.head;
            Node storeNode = this.head;
            while (cursor != null && cursor.next != null) {
                if (cursor.next.data >= ltValue) {
                    cursor = cursor.next;
                } else {
                    storeNode = cursor.next.next;
                    removeElement(cursor.next.data);
                    cursor.next = storeNode;
                }
            }
            return;
        }


        /*
         * Method removeElement() - this method removes all nodes that contain a
         * value equal to the value the provided parameter 'value'.
         */

        public void removeElement ( int value ) {
            // case for head item
            while (this.head != null && this.head.data == value) {
                this.head = this.head.next;
            }
            Node cursor = this.head;
            while (cursor != null && cursor.next != null) {
                if (cursor.next.data != value) {
                    cursor = cursor.next;
                } else {
                    cursor.next = cursor.next.next;
                }
            }
            /*
             * cursor traverses through linked list, skipping any nodes with data matching the parameter 'value'
             * code looks at next node, determines if it matches the parameter
             * if so, updates current node's 'next' value to be that of the skipped node's 'next'
             * continues process until entire linked list has been traversed
            */ 
            return;
        }


        /*
         * Method toString() - this is a helper method for printing / constructing
         * a string object from the linked-list.
         */
        public String toString () // Method to output the LinkedList as a String
        {
            String output = "[";
            Node currNode = this.head;
            while (currNode != null) {
                output += currNode.data + " ";
                currNode = currNode.next;
            }
            return output.trim() + "]";
        }

    } // End class LinkedList




    /*
     * Class Stacks
     *
     * This class utilizes the Java Common Framework Library Stack class.
     *
     * The intent of this class is to write methods which utilize the Java
     * library's Stack class. You need to utilize this library class in
     * your solution.
     *
     * Methods:
     *  - boolean isPalindrome(string)   - method returns true or false if 'string'
     *                                     is a palindrome
     *  - int     findlargestK(stack, k) - method accepts a stack and returns the
     *                                     the largest index in the stack containing
     *                                     value 'k' (stack index starts at 0)
     *
     */

    static class Stacks {

        /*
         * Method isPalindrome() - This method will return the boolean value 'true'
         * or 'false' on if the passed in parameter string is a palindrome or not.
         *
         * The routine should be case insensitive! Meaning 'RaCe cAr' is a palindrome.
         * Moreover, spaces are ignore, so both 'race car' and 'racecar' are palindromes.
         *
         * The method should utilize the provided Stack class.
         */
        public static boolean isPalindrome(String input) {

            Stack<Character> stack = new Stack<>();
            input = input.toLowerCase().replaceAll("\\s+", "");

            boolean isPalindrome = true; // initially assume input is palindrome
            if (input != null) {
                for (int i = 0; i < input.length(); i++) {
                    stack.push(input.charAt(i));
                } // push string character by character onto stack
                for (int i = 0; i < input.length(); i++) {
                    isPalindrome = (stack.pop() == input.charAt(i));
                } // pop each character back off the stack, and compare to input string
                  // set boolean to false if the character does NOT match
            }
            return isPalindrome;
        }


        /*
         * Method findLargestk() - This method will return the largest index
         * position in the stack for the value specified by the parameter 'k'.
         *
         * Note that the bottom of the stack is index location 0. So it you push
         * on to the stack the values 3 4 9 4 4 7 4, in that order. And you pass the
         * value '4' for the parameter k, then the largest index position is index
         * location 6.
         *
         * The method should utilize the provided Stack class. This method should NOT
         * destroy the passed in stack, meaning when the method returns, the passed in
         * stack should be identical to when this method is passed. One trick as you
         * pop elements off the passed in stack, place them in a temp stack. Then when
         * completed, place them all back in the original stack.
         */
        public static int findLargestK(Stack<Integer> stack, int k) {
            // YOUR CODE GOES HERE
            int count = 0;
            int largestK = -1;
            Stack<Integer> tempStack = new Stack<>();

            if (!stack.empty()) { // guard statement for case of empty stack
                while (!stack.empty()) {
                    tempStack.push(stack.pop());
                } // temporarily pushes all elements from original stack onto new stack, effectively reversing order
                while (!tempStack.empty()) {
                    if (tempStack.peek() == k) {
                        largestK = count;
                    }
                    stack.push(tempStack.pop());
                    count++; 
                }
            }
            /*
             * Code pops elements off of original stack and pushes them onto a temporary stack
                    * This effectively reverses the order
                    * This makes it easier to count index, since stack index 0 is at the bottom of the stack
             * Code then pops each element back off of the temporary stack, and also compares each element
             * to the given parameter
             * 'count' keeps track of the index of each element, increasing by 1 with each element popped off of
             * the temporary stack and back onto the original stack
             * If element matches specified parameter, 'largestK' is updated to be equal to the current count
                     * This works because items on the temporary stack are reversed, meaning the bottom element
                     * of the original stack is now on top of the temporary stack
                     * 
                     * Or in other words, elements are popped off of the temporary stack
                     * Starting with the element originally at index 0
                     * making 'count' match up with the original indexes of each element
             */
            return largestK;
        }

    }  // End class Stacks



    /*******************************
     *
     * Algorithm Analysis
     *
     * Below are two methods, algorithmAnalysis1 and algorithmAnalysis2.
     * Modify the return statement to return the correct option number.
     *
     *********************************/

    public static int algorithmAnalysis1(int n, int m) {
        int a = 0, b = 0;

        for (int i=0; i < n; i++)
            a+= Math.random();

        for (int j=0; j < m; j++)
            b+= Math.random();

        /*
         * Select the correct option listed below:
         *   1. O(N * M) time, O(1) space
         *   2. O(N + M) time, O(N + M) space
         *   3. O(N + M) time, O(1) space
         *   4. O(N * M) time, O(N + M) space
         *
         * TODO: return the answer (which option is correct), in the return statement
        */

        // RETURN THE CORRECT OPTION NUMBER LISTED ABOVE
        return 3;
    }


    public static int algorithmAnalysis2(int n) {
        int i, j, k = 0;
        for (i = n/2; i <= n; i++)
            for ( j = 2; j <= n; j = j*2 )
                k+= n/2;

        /*
         * Select the correct option listed below:
         *   1. O(N) time
         *   2. O(N log N) time
         *   3. O(N^2) time
         *   4. O(N^2Log n) time
         *
         * TODO: return the answer (which option is correct), in the return statement
         */

        // RETURN THE CORRECT OPTION LISTED ABOVE
        return 2;
    }

}

