package intranet;
import java.io.Serializable;
import java.util.Objects;
import java.util.Vector;

public class Employee extends User implements Serializable {
	private static final long serialVersionUID = 8544409722007964965L;
	private double salary;
	private Vector<Message> messages;
	{
		messages = new Vector<Message>();
	}

	public Employee(String firstName, String lastName, String id, String username, String password, Sex sex, int age,
			String email, double salary) {
		super(firstName, lastName, id, username, password, sex, age, email);
		this.salary = salary;
	}

	/**
	 * performs the function of sending messages to Employee class
	 */

	public void sendMessage(Employee employee, Message message) {
		employee.messages.add(message);
	}

	/**
	 * Implements getter of message for Employee class
	 */

	public Vector<Message> getMessage() {
		return messages;
	}

	/**
	 * Implements getter of salary for Employee class
	 */

	public double getSalary() {
		return salary;
	}

	/**
	 * Implements setter of salary for Employee class
	 */

	public void setSalary(double salary) {
		this.salary = salary;
	}

	public void sendReport() {
	}

	/**
	 * hashCode() implementation by including salary of the Employee class
	 */

	public int hashCode() {
		final int prime = 31;
		int result = super.hashCode();
		result = prime * result + Objects.hash(salary);
		return result;
	}

	/**
	 * Implementing equals() for Employee class
	 */

	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (!super.equals(obj))
			return false;
		if (getClass() != obj.getClass())
			return false;
		Employee other = (Employee) obj;
		return Double.doubleToLongBits(salary) == Double.doubleToLongBits(other.salary);

	}

	/**
	 * Returns the primitive value that we gave to the class of Employee class
	 */

	public String toString() {
		return super.toString() + ", salary= " + salary;
	}

}