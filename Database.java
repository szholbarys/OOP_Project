package intranet;


import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.Vector;

public final class Database implements Serializable {
	private static final long serialVersionUID = 1L;
	private final static String BASE_PATH = "C:\\Users\\rblxx\\git\\finalIntranet\\Intranet";
	@SuppressWarnings("unused")
	private static Database instance = new Database(BASE_PATH);
	private final String path;
	private static Database obj;
	public static Vector<User> users = new Vector<User>();
	public static Vector<Course> courses = new Vector<Course>();
	public static Vector<Message> messages = new Vector<Message>();
	public static Vector<Journal> journals = new Vector<Journal>();
	public static Vector<Mark> marks = new Vector<Mark>();
	public static Vector<News> newss = new Vector<News>();
	public static Vector<Organization> organizations = new Vector<Organization>();
	public static Vector<Transcript> transcripts = new Vector<Transcript>();

	public static boolean updateUser(User user) {
		if (user == null) {
			System.out.println("Error: cannot be null");
			return false;
		}
		int index = users.indexOf(user);
		if (index >= 0) {
			users.set(index, user);
			return true;
		} else {
			users.add(user);
			return false;
		}
	}

	public static boolean updateCourse(Course course) {
		if (course == null) {
			System.out.println("Error: cannot be null");
			return false;
		}
		int index = courses.indexOf(course);
		if (index >= 0) {
			courses.set(index, course);
			return true;
		} else {
			courses.add(course);
			return false;
		}
	}

	public static boolean updateMessage(Message message) {
		if (message == null) {
			System.out.println("Error: cannot be null");
			return false;
		}
		int index = messages.indexOf(message);
		if (index >= 0) {
			messages.set(index, message);
			return true;
		} else {
			messages.add(message);
			return false;
		}
	}

	public static boolean updateJournal(Journal journal) {
		if (journal == null) {
			System.out.println("Error: cannot be null");
			return false;
		}
		int index = journals.indexOf(journal);
		if (index >= 0) {
			journals.set(index, journal);
			return true;
		} else {
			journals.add(journal);
			return false;
		}
	}

	public static boolean updateMark(Mark mark) {
		if (mark == null) {
			System.out.println("Error: cannot be null");
			return false;
		}
		int index = marks.indexOf(mark);
		if (index >= 0) {
			marks.set(index, mark);
			return true;
		} else {
			marks.add(mark);
			return false;
		}
	}

	public static boolean updateNews(News news) {
		if (news == null) {
			System.out.println("Error: cannot be null");
			return false;
		}
		int index = newss.indexOf(news);
		if (index >= 0) {
			newss.set(index, news);
			return true;
		} else {
			newss.add(news);
			return false;
		}
	}

	public static boolean updateOrganization(Organization organization) {
		if (organization == null) {
			System.out.println("Error: cannot be null");
			return false;
		}
		int index = organizations.indexOf(organization);
		if (index >= 0) {
			organizations.set(index, organization);
			return true;
		} else {
			organizations.add(organization);
			return false;
		}
	}

	public static boolean updateTranscript(Transcript transcript) {
		if (transcript == null) {
			System.out.println("Error: cannot be null");
			return false;
		}
		int index = transcripts.indexOf(transcript);
		if (index >= 0) {
			transcripts.set(index, transcript);
			return true;
		} else {
			transcripts.add(transcript);
			return false;
		}
	}

	public static Vector<User> getUsers() {
		return users;
	}

	public static Vector<Course> getCourses() {
		return courses;
	}

	public static Vector<Message> getMessages() {
		return messages;
	}

	public static Vector<Journal> getJournals() {
		return journals;
	}

	public static Vector<Mark> getMarks() {
		return marks;
	}

	public static Vector<News> getNewss() {
		return newss;
	}

	public static Vector<Organization> getOrganizations() {
		return organizations;
	}

	public static Vector<Transcript> getTranscripts() {
		return transcripts;
	}

	public static void serialize() throws IOException {
		File file = new File("database.ser");
		if (!file.exists()) {
			file.createNewFile();
		}
		if (!file.canWrite()) {
			throw new IOException("Cannot write to file");
		}
		try (FileOutputStream fos = new FileOutputStream(file); ObjectOutputStream oos = new ObjectOutputStream(fos)) {
			oos.writeObject(obj);
			oos.writeObject(users);
			oos.writeObject(courses);
			oos.writeObject(messages);
			oos.writeObject(journals);
			oos.writeObject(marks);
			oos.writeObject(newss);
			oos.writeObject(organizations);
			oos.writeObject(transcripts);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	@SuppressWarnings("unchecked")
	public static Database deserialize() throws IOException, ClassNotFoundException {
		File file = new File("database.ser");
		if (!file.exists()) {
			throw new FileNotFoundException("File does not exist");
		}
		if (!file.canRead()) {
			throw new IOException("Cannot read file");
		}
		Database obj = null;
		try (FileInputStream fis = new FileInputStream(file); ObjectInputStream ois = new ObjectInputStream(fis)) {
			obj = (Database) ois.readObject();
			users = (Vector<User>) ois.readObject();
			courses = (Vector<Course>) ois.readObject();
			messages = (Vector<Message>) ois.readObject();
			journals = (Vector<Journal>) ois.readObject();
			marks = (Vector<Mark>) ois.readObject();
			newss = (Vector<News>) ois.readObject();
			organizations = (Vector<Organization>) ois.readObject();
			transcripts = (Vector<Transcript>) ois.readObject();
		} catch (IOException | ClassNotFoundException e) {
			e.printStackTrace();
		}
		return obj;
	}

	private Database(String path) {
		this.path = path;
	}

	public String getPath() {
		return this.path;
	}

	public static Database getInstance() {

		if (obj == null) {
			obj = new Database(BASE_PATH);
		}
		return obj;
	}
}