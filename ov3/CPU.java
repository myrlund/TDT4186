
public class CPU {

	private Queue cpuQueue;
	private Statistics statistics;
	private long maxCpuTime;
	
	private long currentQueueSize = 0L;
	
	public CPU(Queue cpuQueue, long maxCpuTime, Statistics statistics) {
		this.cpuQueue = cpuQueue;
		this.statistics = statistics;
		this.maxCpuTime = maxCpuTime;
	}
	
	public boolean hasRoomFor(Process p) {
		return currentQueueSize + p.getMemoryNeeded() <= maxCpuTime;
	}
	
	public void addProcess(Process p) {
		if (hasRoomFor(p)) {
			cpuQueue.insert(p);
			currentQueueSize += p.getMemoryNeeded();
		}
	}
	
	public Process removeProcess() {
		if (cpuQueue.isEmpty())
			return null;
		
		Process p = cpuQueue.removeNext();
		currentQueueSize -= p.getMemoryNeeded();
		
		return p;
	}
	
	public Process getNextProcess() {
		if (cpuQueue.isEmpty())
			return null;
		
		return cpuQueue.getNext();
	}
	
	public long getMaxCpuTime () {
		return this.maxCpuTime;
	}

}
