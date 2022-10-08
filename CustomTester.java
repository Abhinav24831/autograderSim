/**
 * TODO: Add your file header
 * Name: Abhinav Chandra
 * ID: A16517764
 * Email: achandra@ucsd.edu
 * Sources used: None
 * 
 * Custom tests written for the MyMinHeap class
 */

import org.junit.*;
import static org.junit.Assert.*;
import java.util.ArrayList;
import java.util.Arrays;



/**
 * Contains public test cases for the MyMinHeap class, mostly focusing on edge
 * cases.
 */
public class CustomTester {

    static void initMinHeap(MyMinHeap<Integer> heap, ArrayList<Integer> data) {
        heap.data = new ArrayList<>(data);
    }
    
    /**
     * Test the constructor when a null argument is passed throug
     */
    @Test
    public void testMyMinHeapConstructor() {
        try{
            MyMinHeap<Integer> heap = new MyMinHeap<>(null);
        } catch(NullPointerException e){
            // Exception caught
        }
    }

    /**
     * Test the getMinChildIdx method when index element has no children
     */
    @Test
    public void testGetMinChildIdx() {
        MyMinHeap<Integer> heap = new MyMinHeap<>();
        ArrayList<Integer> startingList = new ArrayList<Integer>(
            Arrays.asList(
                new Integer[] {1, 2, 3}
            )
        );
        initMinHeap(heap, startingList);
        Integer[] expected = {1, 2, 3};
        int minChildIdx = heap.getMinChildIdx(1);

        assertEquals("Check for correct behavior on leaf nodes",
                    -1, minChildIdx);
        for(int i = 0; i < 3; i++){
            assertEquals("Check if data is unchanged", 
                    expected[i], heap.data.get(i));
        }
    }

    /**
     * Test the percolateUp method when no heap properties are violated
     */
    @Test
    public void testPercolateUp() {
        MyMinHeap<Integer> heap = new MyMinHeap<>();
        ArrayList<Integer> startingList = new ArrayList<Integer>(
            Arrays.asList(
                new Integer[] { 1, 3, 5, 7, 9, 8 }
            )
        );
        initMinHeap(heap, startingList);
        Integer[] expected = { 1, 3, 5, 7, 9, 8 };
        heap.percolateUp(1);
        for(int i = 0; i < 6; i++){
            assertEquals("Check that data is unchanged",
                        expected[i], heap.data.get(i));
        }
    }

    /**
     * Test the percolateDown method when no heap properties are violated
     */
    @Test
    public void testPercolateDown() {
        MyMinHeap<Integer> heap = new MyMinHeap<>();
        ArrayList<Integer> startingList = new ArrayList<Integer>(
            Arrays.asList(
                new Integer[] { 1, 3, 5, 7, 9, 8 }
            )
        );
        initMinHeap(heap, startingList);
        Integer[] expected = { 1, 3, 5, 7, 9, 8 };
        heap.percolateDown(1);
        for(int i = 0; i < 6; i++){
            assertEquals("Check that data is unchanged",
                        expected[i], heap.data.get(i));
        }
    }

    /**
     * Test the deleteIndex method when a leaf node is passed as argument
     */
    @Test
    public void testDeleteIndex() {
        MyMinHeap<Integer> heap = new MyMinHeap<>();
        ArrayList<Integer> startingList = new ArrayList<Integer>(
            Arrays.asList(
                new Integer[] { 1, 2, 3 }
            )
        );
        initMinHeap(heap, startingList);
        Integer[] expected = { 1, 2 };
        heap.deleteIndex(2);
        
        assertEquals("Check if size is decreased", 2, heap.size());
        for(int i = 0; i < 2; i++){
        assertEquals("Check if necessary element is deleted", 
                        expected[i], heap.data.get(i));
        }
    }

    /**
     * Test the deleteIndex method when an element from the middle of 
     * the heap is passed as an input
     */
    @Test
    public void testDeleteIndex2() {
        MyMinHeap<Integer> heap = new MyMinHeap<>();
        ArrayList<Integer> startingList = new ArrayList<Integer>(
            Arrays.asList(
                new Integer[] { 1, 2, 3, 4, 5, 6 }
            )
        );
        initMinHeap(heap, startingList);
        Integer[] expected = { 1, 4, 3, 6, 5 };
        heap.deleteIndex(1);
        
        assertEquals("Check that size is decreased", 5, heap.size());
        for(int i = 0; i < 5; i++){
            assertEquals("Check if necessary element is deleted", 
                            expected[i], heap.data.get(i));
            }
    }

    /**
     * Test the insert method when null element is inserted
     */
    @Test
    public void testInsert(){
        try{
            MyMinHeap<Integer> heap = new MyMinHeap<>();
            ArrayList<Integer> startingList = new ArrayList<Integer>(
            Arrays.asList(
                new Integer[] { 1, 2, 3, 4, 5, 6 }
                )
            );
            initMinHeap(heap, startingList);
            heap.insert(null);
        } catch(NullPointerException e){
            // Exception is caught
        }
    }

    /**
     * Test the insert method when element does not need to be percolated
     */
    @Test
    public void testInsert2(){
        MyMinHeap<Integer> heap = new MyMinHeap<>();
        ArrayList<Integer> startingList = new ArrayList<Integer>(
        Arrays.asList(
            new Integer[] { 1, 2, 3, 4, 5, 6 }
            )
        );
        initMinHeap(heap, startingList);
        Integer[] expected = { 1, 2, 3, 4, 5, 6, 9 };
        heap.insert(9);
        
        assertEquals("Check if size is increased", 7, heap.size());
        for(int i = 0; i < 5; i++){
            assertEquals("Check if necessary element is inserted", 
                            expected[i], heap.data.get(i));
        }

    }

   
    /**
     * Test remove when heap is empty
     */
    @Test
    public void testRemove(){
        MyMinHeap<Integer> heap = new MyMinHeap<>();
        ArrayList<Integer> startingList = new ArrayList<Integer>(
        Arrays.asList(
            new Integer[] { }
            )
        );
        initMinHeap(heap, startingList);
        Integer removeReturn = heap.remove();
        
        assertEquals("Check size is unchanged", 0, heap.size());
        assertEquals("Check for correct return value", null, removeReturn);
    }

  
    /**
     * Test getMin when heap is empty
     */
    @Test
    public void testGetMin(){
        MyMinHeap<Integer> heap = new MyMinHeap<>();
        ArrayList<Integer> startingList = new ArrayList<Integer>(
        Arrays.asList(
            new Integer[] { }
            )
        );
        initMinHeap(heap, startingList);
        Integer getMinReturn = heap.getMin();
        
        assertEquals("Check size is unchanged", 0, heap.size());
        assertEquals("Check for correct return value", null, getMinReturn);
    }
}