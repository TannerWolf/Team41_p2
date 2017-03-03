/////////////////////////////////////////////////////////////////////////////
// Semester:         CS367 Spring 2017
// PROJECT:          team41_p2
// FILE:             JobList
//
// TEAM:    Team 41, IDGAF
// Authors: 
// Author1: (Jarrett Benson, jbenson6@wisc.edu, jbenson6, Lec 002)
// Author2: (Cameron Carlson, ccarlson24@wisc.edu, ccarlson, Lec 002) 
// Author3: (Isaac Heinrich, iheinrich@wisc.edu, iheinrich, Lec 002) 
// Author4: (Jiayue Lai, jlai28@wisc.edu, jlai, Lec 002)
// Author5: (William Mustari, willmustari@gmail.com, mustari, Lec 002) 
// Author6: (Tanner Wolf, tmwolf2@wisc.edu, tmwolf2, Lec 002) 
///////////////////////////////////////////////////////////////////////////////

import java.util.Iterator;

public class JobList implements ListADT<Job> {

	// Global parameters for list
	private int numItems;	    // number of items in the list
	private Listnode<Job> head; // header node of list
	
	//Constructor
	public JobList() {
		numItems = 0;
		head = new Listnode<Job>(null);
	}
	
	@Override
	public Iterator<Job> iterator() {
		// return a new list iterator
		return new JobListIterator<Job>(head);
	}

	@Override
	public void add(Job item) {
		// Check if the given item is null
		if (item == null) {
			throw new IllegalArgumentException();
		}
		// Create a new node and look for the last in the list
		Listnode<Job> newnode = new Listnode<Job>(item);
		Listnode<Job> curr = head;
		while (curr.getNext() != null) {
			curr = curr.getNext();
		}
		curr.setNext(newnode);
		// increase number of items in list
		numItems++;
	}

	@Override
	public void add(int pos, Job item) {
		// first check to make sure the position is valid
		if (pos < 0 || pos > numItems) {
			throw new IndexOutOfBoundsException();
		}
		if (item == null ) {
			throw new IllegalArgumentException();
		}
		if (pos == numItems) {
			add(item);
		}
		// get to one before position in list
		Listnode<Job> curr = head;
		for (int  i = 0; i < pos; i++) {
			curr = curr.getNext();
		}
		// Add the item into the list
		Listnode<Job> temp = curr.getNext();
		Listnode<Job> newnode = new Listnode<Job>(item);
		// redirect nodes
		newnode.setNext(temp);
		curr.setNext(newnode);
		numItems++;
	}

	@Override
	public boolean contains(Job item) {
		// go through the list to check if it is there
		Listnode<Job> curr = head;
		// go through the list to check all items
		while ( curr.getNext() != null ) {
			// check each nodes' data against input item
			if ( curr.getData().equals(item) ) {
				return true;
			}
		}
		// If no nodes match, return false
		return false;
	}

	@Override
	public Job get(int pos) {
		// first check to make sure the position is valid
		if (pos < 0 || pos >= numItems) {
			throw new IndexOutOfBoundsException();
		}
		// go to position
		Listnode<Job> curr = head.getNext();
		for (int i = 0; i < pos; i++) {
			curr = curr.getNext();
		}
		// Return the found job
		return curr.getData();
	}

	@Override
	public boolean isEmpty() {
		// check if the number of items is nonzero
		return numItems > 0;
	}

	@Override
	public Job remove(int pos) {
		// first check to make sure the position is valid
		if (pos < 0 || pos >= numItems) {
			throw new IndexOutOfBoundsException();
		}
		// go to position
		Listnode<Job> curr = head;
		for (int i = 0; i < pos; i++) {
			curr = curr.getNext();
		}
		// Take the item out of the list
		Listnode<Job> temp = curr.getNext();
		curr.setNext(temp.getNext());
		// decrement size of list
		numItems--;
		// Return the found job
		return temp.getData();
	}

	@Override
	public int size() {
		return numItems;
	}

}
