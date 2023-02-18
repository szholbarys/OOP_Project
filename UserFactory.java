package intranet;

public class UserFactory {

	public static User getUser(String firstName, String lastName, String id, String username, String password, Sex sex,
			int age, String email, String userType) {
		if (userType == "ADMIN") {
			return new Admin(firstName, userType, userType, userType, userType, null, 0, userType, 0);
		}
		if (userType == "STUDENT") {
			return new Student(userType, userType, userType, userType, userType, sex, age, userType, age, null, null,
					false, age, null, null);
		}
		if (userType == "MANAGER") {
			return new Manager(userType, userType, userType, userType, userType, sex, age, userType, age);
		}
		if (userType == "TEACHER") {
			return new Teacher(userType, userType, userType, userType, userType, sex, age, userType, age, userType,
					null, null, null, null);
		}
		return null;
	}
}