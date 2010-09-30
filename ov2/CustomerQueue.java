import java.util.ArrayList;

/**
 * This class implements a queue of customers as a circular buffer.
 */
public class CustomerQueue {
	private Customer[] customers;
	private Gui gui;
	
	private int queueLength;
	private int queueStart = 0;
	private int queueStop = 0;
	
	/**
	 * Creates a new customer queue.
	 * @param queueLength	The maximum length of the queue.
	 * @param gui			A reference to the GUI interface.
	 */
    public CustomerQueue(int queueLength, Gui gui) {
		this.customers = new Customer[queueLength];
		this.gui = gui;
		
		this.queueLength = queueLength;
	}

    synchronized public Customer next() {
    	if (queueStart == queueStop) {
    		try {
				wait();
			} catch (InterruptedException e) {
				System.out.println("INTERRUPTED!");
			}
    	}
    	
		gui.println("Adding customer to barber chair.");
   		Customer c = customers[queueStart];
   		customers[queueStart] = null;
   		gui.emptyLoungeChair(queueStart);
   		queueStart = incrementIndex(queueStart);
   		return c;
	}
	
	synchronized public void enqueue(Customer c) {
		if (incrementIndex(queueStop) != queueStart) {
			gui.println("Adding customer to queue.");
			queueStop = incrementIndex(queueStop);
			customers[queueStop] = c;
			gui.fillLoungeChair(queueStop, c);
			
			notify();
		}
	}
	
	private int incrementIndex(int index) {
		index++;
		if (index == queueLength)
			index = 0;
		return index;
	}
	
}
