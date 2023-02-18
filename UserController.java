package intranet;


import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.Objects;

public class UserController implements Serializable {
	private static final long serialVersionUID = -3330920723864772251L;
	User n;
	public UserController(User u) {
		this.n = u;
	}

	public boolean verifyLogin(String login, String password) {
		return n.getUsername().equals(login) && n.getPassword() == Objects.hash(password);
	}

	public void serialize() {
		try (FileOutputStream fs = new FileOutputStream(Database.getInstance().getPath() + "Users.txt")) {
			ObjectOutputStream oos = new ObjectOutputStream(fs);
			oos.writeObject(n);
			oos.flush();
			oos.close();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e1) {
			e1.printStackTrace();
		}
	}

	public User deserialize() {
		try (FileInputStream fs = new FileInputStream(Database.getInstance().getPath() + "Users.txt")) {
			ObjectInputStream ois = new ObjectInputStream(fs);
			User u = (User) ois.readObject();
			return u;
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e1) {
			e1.printStackTrace();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
		return new User("", "", "", "", "", Sex.UNDEFINED, 0, "");
	}
}