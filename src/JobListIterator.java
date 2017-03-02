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
