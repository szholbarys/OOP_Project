package intranet;

import java.io.Serializable;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Objects;
import java.util.Vector;

public class User implements Comparable<User>, Serializable {
	private static final long serialVersionUID = 522695291421763322L;
	private String firstName;
	private String lastName;
	private String id;
	private String username;
	private Sex sex;
	private int age;
	private String email;
	private boolean loginStatus;
	private int hashPassword;

	public User(String firstName, String lastName, String id, String username, String password, Sex sex, int age,
			String email) {
		this.firstName = firstName;
		this.lastName = lastName;
		this.id = id;
		this.username = username;
		this.sex = sex;
		this.age = age;
		this.email = email;
		this.loginStatus = false;
		this.hashPassword = Objects.hash(password);
	}

	/**
	 ** Implements setter of password for User class
	 */

	public boolean setPassword(String newPassword) {
		this.hashPassword = Objects.hash(newPassword);
		return true;
	}

	/**
	 * Implements getter of password for User class
	 */

	public int getPassword() {
		return hashPassword;
	}

	/**
	 * logs in with a password and username in University System
	 */

	public boolean login(String username, String password) {
		if (username.equals(this.username) && Objects.hash(password) == hashPassword) {
			loginStatus = true;
			return true;
		}
		return false;
	}

	/**
	 * Implements setter of loginStatus for User class
	 */

	public void setLoginStatus(boolean choice) {
		if (choice)
			loginStatus = true;
		else
			loginStatus = false;
	}

	/**
	 * Logs out of the University system
	 */

	public boolean logout() {
		loginStatus = false;
		return true;
	}

	/**
	 * If his login status is ok, then he can see the news
	 */

	public void viewNews() {
		if (!loginStatus) {
			return;
		}
	}

	/**
	 * If his login status is ok, then he can see the schedule
	 */

	public void viewSchedule() {
		if (!loginStatus) {
			return;
		}
	}

	/**
	 * Implements getter of First Name for User class
	 */

	public String getFirstName() {
		return firstName;
	}

	/**
	 * Implements setter of First Name for User class
	 */

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	/**
	 * Implements getter of Last Name for User class
	 */

	public String getLastName() {
		return lastName;
	}

	/**
	 * Implements setter of Last Name for User class
	 */

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	/**
	 * Implements getter of Id for User class
	 */

	public String getId() {
		return id;
	}

	/**
	 * Implements setter of Id for User class
	 */

	public void setId(String id) {
		this.id = id;
	}

	/**
	 * Implements getter of UserName for User class
	 */

	public String getUsername() {
		return username;
	}

	/**
	 * Implements getter of sex for User class
	 */

	public Sex getSex() {
		return sex;
	}

	/**
	 * Implements getter of age for User class
	 */

	public int getAge() {
		return age;
	}

	/**
	 * Implements getter of email for User class
	 */

	public String getEmail() {
		return email;
	}

	/**
	 * Implements setter of email for User class
	 */

	public void setEmail(String email) {
		this.email = email;
	}

	/**
	 * Checking the login status
	 */

	public boolean isLoginStatus() {
		return loginStatus;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public void setSex(Sex sex) {
		this.sex = sex;
	}

	public void setAge(int age) {
		this.age = age;
	}

	/**
	 * Implementing compareTo() for using comparator
	 */

	public int compareTo(User other) {
		int usernameComparison = username.compareTo(other.username);
		return usernameComparison;
	}

	/**
	 * Returns the primitive value that we gave to the class of User class
	 */

	public String toString() {
		return "User [firstName=" + firstName + ", lastName=" + lastName + ", id=" + id + ", username=" + username
				+ ", sex=" + sex + ", age=" + age + ", email=" + email + ", loginStatus=" + loginStatus + "]";
	}

	/**
	 * hashCode() implementation by including salary of the User class
	 */

	public int hashCode() {
		return Objects.hash(age, email, firstName, id, lastName, loginStatus, hashPassword, sex, username);
	}

	/**
	 * Implementing equals() for User class
	 */

	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		User other = (User) obj;
		return age == other.age && Objects.equals(email, other.email) && Objects.equals(firstName, other.firstName)
				&& Objects.equals(id, other.id) && Objects.equals(lastName, other.lastName)
				&& loginStatus == other.loginStatus && sex == other.sex && Objects.equals(username, other.username);
	}

}