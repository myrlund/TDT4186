/**
 * This class implements the barber's part of the
 * Barbershop thread synchronization example.
 */
public class Barber {
	private CustomerQueue queue;
	private Gui gui;
	private int position;
	
	Thread thread = null;
	
	/**
	 * Creates a new barber.
	 * @param queue		The customer queue.
	 * @param gui		The GUI.
	 * @param pos		The position of this barber's chair
	 */
	public Barber(CustomerQueue queue, Gui gui, int pos) { 
		this.queue = queue;
		this.gui = gui;
		this.position = pos;
		
		gui.println("dslkfdsflj");
	}

	/**
	 * Starts the barber running as a separate thread.
	 */
	public void startThread() {
		thread = new Thread(new Runnable() {
			public void run() {
				while (true) {
					try {
						Customer c = queue.next();
						
						if (c != null) {
							gui.fillBarberChair(position, c);
							
							Thread.sleep(Globals.barberWork);
							gui.emptyBarberChair(position);
							
							gui.barberIsSleeping(position);
							Thread.sleep(Globals.barberSleep);
							gui.barberIsAwake(position);
						}
					} catch (InterruptedException e) {
						System.out.println("INTERRUPTED AGAIIIIN!");
					}
				}
			}
		});
		thread.start();
	}

	/**
	 * Stops the barber thread.
	 */
	public void stopThread() {
		if (thread != null)
			thread.interrupt();
	}

}

