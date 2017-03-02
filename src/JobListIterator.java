import java.util.*;

public class JobListIterator<E> implements Iterator<Job> {

	Listnode<Job> head;	//header reference passed to us
	Listnode<Job> curr;	//node reference we will traverse with

	public JobListIterator(Listnode<Job> header) {
		head = header;
		curr = head;	//start curr at head
	}

	@Override
			//checks if there is a next node
	public boolean hasNext() {	
		if (curr.getNext() == null) {
			
			//returns false if the next reference is null
		return false;	
		}

			//if there is a next node, return true
		return true;
	}

	@Override
			//get the next node's data if it exists
	public Job next() throws NoSuchElementException {
		
			//throw an error if there isn't a next node
		if (curr.getNext() == null) {
			throw new NoSuchElementException();
		}
		
			//traverse curr and return the job data
		Job temp = curr.getData();
		curr = curr.getNext();
		return temp;
	}

}
