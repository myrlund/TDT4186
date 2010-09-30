/**
 * This class implements the doorman's part of the
 * Barbershop thread synchronization example.
 */
public class Doorman {
	CustomerQueue queue;
	Gui gui;
	
	Thread thread = null;
	
	/**
	 * Creates a new doorman.
	 * @param queue		The customer queue.
	 * @param gui		A reference to the GUI interface.
	 */
	public Doorman(CustomerQueue queue, Gui gui) { 
		this.queue = queue;
		this.gui = gui;
	}

	/**
	 * Starts the doorman running as a separate thread.
	 */
	public void startThread() {
		thread = new Thread(new Runnable() {
			public void run() {
				while (true) {
					try {	
						Customer c = new Customer();
						
						gui.println("Pushing customer " + c + " to queue.");
						queue.enqueue(c);
					
						Thread.sleep(Globals.doormanSleep);
					} catch (InterruptedException e) {}
				}
			}
		});
		thread.start();
	}

	/**
	 * Stops the doorman thread.
	 */
	public void stopThread() {
		if (thread != null)
			thread.interrupt();
	}

	// Add more methods as needed
}
