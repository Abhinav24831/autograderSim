/**
 * TODO: Add your file header
 * Name: Abhinav Chandra
 * ID: A16517764
 * Email: achandra@ucsd.edu
 * Sources used: Zybooks Chapter 6
 * Some example of sources used would be Tutors, Zybooks, and Lecture Slides
 * 
 * This file contains the MyMinHeap class. It is used in the MyPriorityQueue, 
 * Ticket, and Autograder classes.
 */

import java.util.Collection;
import java.util.ArrayList;

/**
 * This class is an implementation of the min-data structure. It is implemented
 * using a generic ArrayList instance variable called data
 */
public class MyMinHeap<E extends Comparable<E>> implements MinHeapInterface <E>{

    /**
     * TODO: Implement MinHeap
     */

    public ArrayList<E> data;

    /**
     * A constructor that creats an instance of a MyMinHeap object with an
     * empty data instance variable
     */
    public MyMinHeap(){
        this.data = new ArrayList<>();
    }

    /**
     * A constructor that creats an instance of a MyMinHeap object where the
     * data instance variable holds the elements from collection
     *  
     * @param collection - Collection that holds data to be copied over to 
     * data instance variable
     */
    public MyMinHeap(Collection<? extends E> collection){
        if(collection == null || collection.contains(null) == true){
            throw new NullPointerException("Input invalid");
        }
        this.data = new ArrayList<>(collection);
        // Rearranges data to match heap properties
        for(int i = size() - 1; i > -1; i--){
            percolateDown(i);
        }
    }
    /**
     * Helper method that swaps the elements at two given indexes
     * @param from - index from which one element is being swapped
     * @param to - index to which one element is being swapped
     */
    protected void swap(int from, int to){
        E fromElem = this.data.get(from);
        E toElem = this.data.get(to);
        this.data.set(from, toElem);
        this.data.set(to, fromElem);
    }

    /**
     * Helper method that returns parent index of a given index
     * @param index - index who's parent index is being returned
     * @return parent index of index
     */
    protected int getParentIdx(int index){
        return (index-1)/2;
    }

    /**
     * Helper method that returns index of left child of a given index
     * @param index - index who's left child's index is being returned
     * @return index of left child of given index
     */
    protected int getLeftChildIdx(int index){
        return 2 * index + 1;
    }

    /**
     * Helper method that returns index of right child of a given index
     * @param index - index who's right child's index is being returned
     * @return index of right child of given index
     */
    protected int getRightChildIdx(int index){
        return 2 * index + 2;
    }

    /**
     * Helper method that returns index smallest child of given index
     * @param index - index who's smallest child's index is being returned
     * @return index of smallest child of given index
     */
    protected int getMinChildIdx(int index){
        // Checks if index has any leaf nodes
        if(getLeftChildIdx(index) > size() - 1){
            return -1;
        }
        E rightChild = this.data.get(getRightChildIdx(index));
        E leftChild = this.data.get(getLeftChildIdx(index));

        // Second check for if index has any leaf nodes
        if(leftChild == null && rightChild == null){
                return -1;
        }

        // Checks if left child's index should be returned
        if((leftChild != null && rightChild == null) ||
            (leftChild.compareTo(rightChild) <= 0)){
                return getLeftChildIdx(index);
        }
        
        return getRightChildIdx(index);
    }

    /**
     * Helper method that percolates the element at a given index up the heap
     * until heap properties are no longer violated
     * @param index - index of element that should be percolated up
     */
    protected void percolateUp(int index){        
        while(index > 0){
            int parentIndex = getParentIdx(index);
            // Checks if parent is less than element at index
            if(this.data.get(index).compareTo(this.data.get(parentIndex))
                >= 0) {
                    break;
            } 
            // Sets course of action if element at parent > element at index
            else{
                swap(index, parentIndex);
                index = parentIndex;
            }
        }
    }

    /**
     * Helper method that percolates the element at a given index down the heap
     * until heap properties are no longer violated
     * @param index - index of element that should be percolated down
     */
    protected void percolateDown(int index){
        // Checks if element at index is a leaf node
        if(index > size() - 1){
            return;
        }
        int leftChildIdx = getLeftChildIdx(index);
        E value = this.data.get(index);

        while(leftChildIdx < size()){
            E minValue = value;
            int minIndex = -1;

            // Identifies smallest element between element at index and its 
            // children and assigns it to minValue
            for(int i = 0; i < 2 && i + leftChildIdx < size(); i++){
                if(this.data.get(i + leftChildIdx).compareTo(minValue) < 0){
                    minValue = this.data.get(i + leftChildIdx);
                    minIndex = i + leftChildIdx;
                }
            }

            // Action for when element at index is minValue
            if(minValue == value){
                break;
            } 
            // Action for when one of the children is minValue
            else{
                swap(index, minIndex);
                index = minIndex;
                leftChildIdx = getLeftChildIdx(index);
            }
        }
    }

    /**
     * Helper method that deletes element at a given index
     * @param index - index at which element should be deleted
     */
    protected E deleteIndex(int index){
        E returnVal = this.data.get(index);
        swap(index, size() - 1);
        this.data.remove(size() - 1);
        percolateDown(index);
        return returnVal;
    }

    /**
     * Inserts a given element at the end of the heap
     * @param element - element being inserted into heap
     */
    public void insert(E element){
        if(element == null){
            throw new NullPointerException("Input null");
        }

        this.data.add(element);
        percolateUp(size() - 1);
    }

    /**
     * Returns smallest value in heap
     * @return null if heap is empty or the root otherwise
     */
    public E getMin(){
        if(size() == 0){
            return null;
        }
        return this.data.get(0);
    }

    /**
     * Removes the root of the heap
     * @return null if heap is empty, element at root otherwise
     */
    public E remove(){
        if(size() == 0){
            return null;
        }
        E root = getMin();
        deleteIndex(0);
        return root;
    }

    /**
     * Returns number of elements in heap
     * @return - number of elements in the data instance variable
     */
    public int size(){
        return this.data.size();
    }

    /**
     * Removes all elements from the heap
     */
    public void clear(){
        this.data = new ArrayList<>();
    }

}