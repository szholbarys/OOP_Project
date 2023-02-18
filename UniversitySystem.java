package intranet;


import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Map;
import java.util.Vector;

public class UniversitySystem {
	private static BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
	private static User sessionUser;

	public static void main(String[] args) throws IOException, ClassNotFoundException {
		sessionUser = null;
		Database.deserialize();
		Database.users.add(new Admin("Gordon", "Freeman", "2X120SD", "superadmin", "easypass", Sex.UNDEFINED, 99,
				"superadmin@gmail.com", 0));
		System.out.println(Database.users);
		runSystem();
		System.out.print(Database.getUsers());
		System.out.print(Database.getCourses());
	}

	private static void runSystem() throws IOException {
		while (true) {
			if (sessionUser == null) {
				displayCredentialsMenu();
			} else if (sessionUser instanceof Student) {
				displayStudentView();
			} else if (sessionUser instanceof Teacher) {
				displayTeacherView();
			} else if (sessionUser instanceof Admin) {
				displayAdminView();
			} else {
				System.out.println("Oops, fiddlesticks! What now?");
			}
		}
	}

	private static void displayCredentialsMenu() throws IOException {
		System.out.println("Welcome to Saint Martin University System");
		System.out.println("1. Log In");
		System.out.println("2. Exit");
		System.out.print("Enter prompt: ");
		String choice = reader.readLine();
		switch (choice) {
		case "1":
			System.out.print("Enter your username: ");
			String username = reader.readLine();
			System.out.print("Enter your password: ");
			String password = reader.readLine();
			boolean loginSuccess = false;
			for (User user : Database.users) {
				if (user.login(username, password)) {
					if (user instanceof Student) {
						sessionUser = (Student) user;
					} else if (user instanceof Teacher) {
						sessionUser = (Teacher) user;
					} else if (user instanceof Admin) {
						sessionUser = (Admin) user;
					} else {
						sessionUser = user;
					}
					loginSuccess = true;
					break;
				}
			}
			if (loginSuccess) {
				System.out.println("Login successful!");
			} else {
				System.out.println("Invalid username or password.");
				displayCredentialsMenu();
			}
			break;
		case "2":
			System.out.println("Goodbye!");
			sessionUser = null;
			Database.serialize();
			System.exit(0);
			break;
		default:
			System.out.println("Invalid choice. Try again.");
			break;
		}
	}

	private static void displayStudentView() throws IOException {
		if (sessionUser instanceof Student) {
			System.out.println("Welcome, " + sessionUser.getFirstName() + "!");
			while (true) {
				System.out.println("1. View news");
				System.out.println("2. View schedule");
				System.out.println("3. Register for courses");
				System.out.println("4. View transcript");
				System.out.println("5. Logout");
				System.out.print("Enter your choice: ");
				String choice = reader.readLine();
				switch (choice) {
				case "1":
					sessionUser.viewNews();
					break;
				case "2":
					sessionUser.viewSchedule();
					break;
				case "3":
					Vector<Course> courses = Database.getCourses();
					Vector<Course> availableCourses = new Vector<Course>();
					for (Course course : courses) {
						if (!((Student) sessionUser).getCourses().contains(course)) {
							availableCourses.add(course);
						}
					}
					if (availableCourses.isEmpty()) {
						System.out.println("There are no available courses for you to enroll in.");
						break;
					}
					System.out.println("Available courses:");
					for (int i = 0; i < availableCourses.size(); i++) {
						System.out.println((i + 1) + ". " + availableCourses.get(i).getName());
					}
					System.out.println("Choose course, 0 to return back: ");
					int courseIndex;
					try {
						courseIndex = Integer.parseInt(reader.readLine()) - 1;
					} catch (NumberFormatException e) {
						System.out.println("Error: Invalid input.");
						break;
					}
					if (courseIndex < 0) {
						break;
					}
					if (courseIndex >= availableCourses.size()) {
						System.out.println("Error: Invalid course number.");
						break;
					}
					Course selectedCourse = availableCourses.get(courseIndex);
					if (selectedCourse.getLimit() <= 0) {
						System.out.println("Sorry, this course is full.");
						break;
					}
					((Student) sessionUser).addCourse(selectedCourse);
					break;
				case "4":
					System.out.println(((Student) sessionUser).viewTranscript());
					break;
				case "5":
					sessionUser.logout();
					sessionUser = null;
					displayCredentialsMenu();
					break;
				default:
					System.out.println("Invalid choice. Try again.");
					break;
				}
			}
		}
	}

	public static void displayTeacherView() throws IOException {
		if (sessionUser instanceof Teacher) {
			System.out.println("Welcome, " + sessionUser.getFirstName() + "!");
			while (true) {
				System.out.println("1. View news");
				System.out.println("2. View schedule");
				System.out.println("3. View students");
				System.out.println("4. Put grade");
				System.out.println("5. Show marks");
				System.out.println("6. Logout");
				System.out.print("Enter your choice: ");
				String choice = reader.readLine();
				switch (choice) {
				case "1":
					sessionUser.viewNews();
					break;
				case "2":
					sessionUser.viewSchedule();
					break;
				case "3":
					Vector<Course> courses = ((Teacher) sessionUser).getCourses();
					if (courses.isEmpty()) {
						System.out.println("You don't teach any courses.");
						break;
					}
					System.out.println("Choose a course to view students for:");
					for (int i = 0; i < courses.size(); i++) {
						System.out.println((i + 1) + ". " + courses.get(i).getName());
					}
					System.out.println("Choose course, 0 to return back: ");
					int courseIndex;
					try {
						courseIndex = Integer.parseInt(reader.readLine()) - 1;
					} catch (NumberFormatException e) {
						System.out.println("Error: Invalid input.");
						break;
					}
					if (courseIndex < 0) {
						break;
					}
					if (courseIndex >= courses.size()) {
						System.out.println("Error: Invalid course number.");
						break;
					}
					Course selectedCourse = courses.get(courseIndex);
					Vector<Student> students = selectedCourse.getEnrolled();
					System.out.println("Students for " + selectedCourse.getName() + ":\n");
					for (int i = 0; i < students.size(); i++) {
						System.out.println(
								(i + 1) + ". " + students.get(i).getFirstName() + " " + students.get(i).getLastName());
					}
					break;

				case "4":
					System.out.println("Choose a student to add a grade for:");
					Vector<User> users = Database.getUsers();
					students = new Vector<>();
					for (User user : users) {
						if (user instanceof Student) {
							students.add((Student) user);
						}
					}
					int i = 1;
					for (Student student : students) {
						System.out.println((i++) + ". " + student.getFirstName() + " " + student.getLastName());
					}
					System.out.print("Enter the number of the student: ");
					int studentIndex;
					try {
						studentIndex = Integer.parseInt(reader.readLine()) - 1;
					} catch (NumberFormatException e) {
						System.out.println("Error: Invalid input.");
						break;
					}
					if (studentIndex < 0 || studentIndex >= students.size()) {
						System.out.println("Error: Invalid student number.");
						break;
					}
					Student selectedStudent = students.get(studentIndex);
					System.out.println("Choose a course to add a grade for:");
					courses = ((Teacher) sessionUser).getCourses();
					i = 1;
					for (Course course : courses) {
						System.out.println((i++) + ". " + course.getName());
					}
					System.out.print("Enter the number of the course: ");
					try {
						courseIndex = Integer.parseInt(reader.readLine()) - 1;
					} catch (NumberFormatException e) {
						System.out.println("Error: Invalid input.");
						break;
					}
					if (courseIndex < 0 || courseIndex >= courses.size()) {
						System.out.println("Error: Invalid course number.");
						break;
					}
					selectedCourse = courses.get(courseIndex);
					System.out.print("Enter the grade to add: ");
					double grade;
					try {
						grade = Double.parseDouble(reader.readLine());
					} catch (NumberFormatException e) {
						System.out.println("Error: Invalid input.");
						break;
					}
					if (!((Teacher) sessionUser).putGrade(selectedStudent, selectedCourse, grade)) {
						System.out.println("Error: Could not add grade.");
					} else {
						System.out.println("Grade added successfully.");
					}
					break;

				case "5":
					courses = ((Teacher) sessionUser).getCourses();
					if (courses.isEmpty()) {
						System.out.println("You do not currently teach any courses.");
						break;
					}

					System.out.println("Choose a course to view student marks:");
					for (i = 0; i < courses.size(); i++) {
						System.out.println((i + 1) + ". " + courses.get(i).getName());
					}
					try {
						courseIndex = Integer.parseInt(reader.readLine()) - 1;
					} catch (NumberFormatException e) {
						System.out.println("Error: Invalid input.");
						break;
					}
					if (courseIndex < 0 || courseIndex >= courses.size()) {
						System.out.println("Error: Invalid course number.");
						break;
					}
					selectedCourse = courses.get(courseIndex);

					users = Database.getUsers();
					Vector<Student> studentsInCourse = new Vector<>();
					for (User user : users) {
						if (user instanceof Student && ((Student) user).getCourses().contains(selectedCourse)) {
							studentsInCourse.add((Student) user);
						}
					}

					if (studentsInCourse.isEmpty()) {
						System.out.println("There are no students enrolled in this course.");
						break;
					}

					System.out.println("Choose a student to view marks:");
					for (i = 0; i < studentsInCourse.size(); i++) {
						System.out.println((i + 1) + ". " + studentsInCourse.get(i).getFirstName() + " "
								+ studentsInCourse.get(i).getLastName());
					}
					try {
						studentIndex = Integer.parseInt(reader.readLine()) - 1;
					} catch (NumberFormatException e) {
						System.out.println("Error: Invalid input.");
						break;
					}
					if (studentIndex < 0 || studentIndex >= studentsInCourse.size()) {
						System.out.println("Error: Invalid student number.");
						break;
					}
					selectedStudent = studentsInCourse.get(studentIndex);
					Map<Course, Mark> marks = selectedStudent.getMarks();
					if (!marks.containsKey(selectedCourse)) {
						System.out.println("This student does not have a mark for this course.");
						break;
					}
					System.out.println("Marks for " + selectedStudent.getFirstName() + " "
							+ selectedStudent.getLastName() + " in " + selectedCourse.getName() + ":");
					marks = selectedStudent.getMarks();
					if (marks.containsKey(selectedCourse)) {
						Mark mark = marks.get(selectedCourse);
						System.out.println("First attestation: " + mark.getFirstAttestation());
						System.out.println("Second attestation: " + mark.getSecondAttestation());
						System.out.println("Final exam: " + mark.getFinalExam());
						System.out.println("Total: " + mark.getTotal());
					} else {
						System.out.println("This student does not have a mark for this course.");
					}
					break;

				case "6":
					sessionUser.logout();
					sessionUser = null;
					displayCredentialsMenu();
					break;
				default:
					System.out.println("Invalid choice. Try again.");
					break;
				}

			}
		}
	}

	private static void displayAdminView() throws IOException {
		System.out.println("Welcome, " + sessionUser.getFirstName() + "!");
		while (true) {
			System.out.println("1. View logs");
			System.out.println("2. Create user");
			System.out.println("3. Update user");
			System.out.println("4. Remove user");
			System.out.println("5. Send message");
			System.out.println("6. Logout");
			System.out.print("Enter your choice: ");
			String choice = reader.readLine();
			switch (choice) {
			case "1":
				((Admin) sessionUser).viewLogs();
				break;
			case "2":
				System.out.println("Which type of user do you want to create?");
				System.out.println("1. User");
				System.out.println("2. Student");
				System.out.println("3. Teacher");
				System.out.print("Enter choice: ");
				String userType = reader.readLine();
				System.out.print("Enter first name: ");
				String firstName = reader.readLine();
				System.out.print("Enter last name: ");
				String lastName = reader.readLine();
				System.out.print("Enter ID: ");
				String id = reader.readLine();
				System.out.print("Enter username: ");
				String username = reader.readLine();
				System.out.print("Enter password: ");
				String password = reader.readLine();
				System.out.print("Enter sex (M/F): ");
				String sexString = reader.readLine();
				System.out.print("Enter email: ");
				String email = reader.readLine();
				Sex sex;
				if (sexString.equalsIgnoreCase("M")) {
					sex = Sex.MALE;
				} else if (sexString.equalsIgnoreCase("F")) {
					sex = Sex.FEMALE;
				} else {
					System.out.println("Invalid sex. Try again.");
					break;
				}
				System.out.print("Enter age: ");
				int age = Integer.parseInt(reader.readLine());
				if (userType.equals("1")) {
					User newUser = new User(firstName, lastName, id, username, password, sex, age, email);
					Database.users.add(newUser);
					System.out.println("User created successfully!");
				} else if (userType.equals("2")) {
					System.out.print("Enter enrollment date (dd-MM-yyyy): ");
					String enrollmentDateString = reader.readLine();
					try {
						Date enrollmentDate = new SimpleDateFormat("dd-MM-yyyy").parse(enrollmentDateString);
					} catch (ParseException e) {
						System.out.println("Invalid date format. Try again.");
						break;
					}
					Student newStudent = new Student(firstName, lastName, id, username, password, sex, age, email, 0,
							null, new Date(), false, 2022, null, null);
					Database.users.add(newStudent);
					System.out.println("Student created successfully!");
				} else if (userType.equals("3")) {
					System.out.print("Enter hire date (dd-MM-yyyy): ");
					String hireDateString = reader.readLine();
					try {
						Date hireDate = new SimpleDateFormat("dd-MM-yyyy").parse(hireDateString);
					} catch (ParseException e) {
						System.out.println("Invalid date format. Try again.");
						break;
					}
					Teacher newTeacher = new Teacher(firstName, lastName, id, username, password, sex, age, email, 0,
							null, null, null, null, null);
					Database.users.add(newTeacher);
				} else {
					System.out.println("Invalid user type. Try again.");
					break;
				}
				break;
			case "3":
				break;
			case "4":
				break;
			case "5":
				break;
			case "6":
				sessionUser.logout();
				sessionUser = null;
				displayCredentialsMenu();
				break;
			default:
				System.out.println("Invalid choice. Try again.");
				break;
			}
		}

	}
}
