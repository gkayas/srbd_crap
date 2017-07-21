
public class Suite implements Comparable<Suite>{
	String name;
	private int passTc;
	private int failTc;
	private double timeRun;
	private double timeRerun;
	
	public double getTimeTotal() {
		return timeRerun+timeRun;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getPassTc() {
		return passTc;
	}

	public void setPassTc(int passTc) {
		this.passTc = passTc;
	}

	public int getFailTc() {
		return failTc;
	}

	public void setFailTc(int failTc) {
		this.failTc = failTc;
	}

	public int getTotalTc() {
		return passTc+failTc;
	}

	public Suite(String name, int passTc, int failTc,double time) {
		this.name = name;
		this.passTc = passTc;
		this.failTc = failTc;
		this.timeRun = time;
	}
	
	public Suite(String name) {
		this.name = name;
	}
	
	public double getTimeRun() {
		return timeRun;
	}

	public void setTimeRun(double timeRun) {
		this.timeRun = timeRun;
	}

	public double getTimeRerun() {
		return timeRerun;
	}

	public void setTimeRerun(double timeRerun) {
		this.timeRerun = timeRerun;
	}

	public Suite() {
		
	}
	
	@Override
	public boolean equals(Object obj) {
		if(!(obj instanceof Suite)) {
			return false;
		}
		return this.name.equals(((Suite)obj).getName());
	}

	@Override
	public int compareTo(Suite o) {
		
		return this.name.compareTo(o.getName());
	}
}
