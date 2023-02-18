package intranet;


public class DeskWorker extends Employee {
	private static final long serialVersionUID = -6125636279127791433L;
	private State workingState;

	public DeskWorker(String firstName, String lastName, String id, String username, String password, Sex sex, int age,
			String email, double salary, State workingState) {
		super(firstName, lastName, id, username, password, sex, age, email, salary);
		this.setWorkingState(workingState);
	}

	public boolean reviewCase(State workingState) {
		if (workingState == State.FREE) {
			return true;
		}
		return false;
	}

	public State getWorkingState() {
		return workingState;
	}

	public void setWorkingState(State workingState) {
		this.workingState = workingState;
	}
}
 
