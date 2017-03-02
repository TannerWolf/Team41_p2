/////////////////////////////////////////////////////////////////////////////
// Semester:         CS367 Spring 2017
// PROJECT:          team41_p2
// FILE:             JobListIterator
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

import java.util.*;

public class JobListIterator<E> implements Iterator<Job> {
	
	Listnode<Job> curr;	//node reference we will traverse with

	public JobListIterator(Listnode<Job> head) {
		curr = head.getNext();	//start curr at first node in the list
	}

	@Override
			//checks if there is a next node
	public boolean hasNext() {	
		return curr != null;	//returns false if the next reference is null
	}

	@Override
			//get the next node's data if it exists
	public Job next() throws NoSuchElementException {
		
			//throw an error if there isn't a next node
		if (curr == null) {
			throw new NoSuchElementException();
		}
		
			//traverse curr and return the job data
		Job temp = curr.getData();
		curr = curr.getNext();
		return temp;
	}

}
