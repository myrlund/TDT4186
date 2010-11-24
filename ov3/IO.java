
public class IO {

	private Queue ioQueue;
	private long avgIoTime;
	private Statistics statistics;

	public IO(Queue ioQueue, long avgIoTime, Statistics statistics) {
		this.ioQueue = ioQueue;
		this.avgIoTime = avgIoTime;
		this.statistics = statistics;
	}
	
	public void addProcess(Process p) {
		ioQueue.insert(p);
	}
	
	public Process removeProcess() {
		return ioQueue.removeNext();
	}
	
	public Process getNextProcess() {
		if (ioQueue.isEmpty())
			return null;
		
		return ioQueue.getNext();
	}
	
	public long getAvgIoTime () {
		return this.avgIoTime;
	}

}
