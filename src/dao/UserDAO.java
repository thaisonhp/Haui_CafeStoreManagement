package dao;

import java.io.IOException;
import java.util.List;
import java.util.function.Predicate;

import model.User;
import common.FileConnector;
import common.HashPassword;

public class UserDAO implements DAO<User> {
	private final String FILE_NAME = "users.bin";
	private final FileConnector<User> fileConnector = new FileConnector<User>();

	public boolean isUserExist(String username, String email) throws ClassNotFoundException, IOException {
		List<User> users = fileConnector.readFromFile(FILE_NAME);
		return users.stream().anyMatch(user -> user.getName().equals(username) || user.getEmail().equals(email));
	}

	@Override
	public User get(Predicate<User> predicate) throws ClassNotFoundException, IOException {
		List<User> users = fileConnector.readFromFile(FILE_NAME);
		for (User user : users) {
			if (predicate.test(user)) {
				return user;
			}
		}
		return null;
	}

	@Override
	public List<User> getAll() throws ClassNotFoundException, IOException {
		return fileConnector.readFromFile(FILE_NAME);
	}

	@Override
	public boolean add(User user) throws ClassNotFoundException, IOException {
		if (isUserExist(user.getName(), user.getEmail())) {
			return false;
		}
		user.setPassword(HashPassword.hashPassword(user.getPassword()));
		fileConnector.appendObject(FILE_NAME, user);
		return true;

	}

	@Override
	public boolean update(User updatedUser) throws ClassNotFoundException, IOException {
		List<User> users = fileConnector.readFromFile(FILE_NAME);
		for (int i = 0; i < users.size(); i++) {
			if (users.get(i).getEmail().equals(updatedUser.getEmail())) {
				users.set(i, updatedUser);
				break;
			}
		}
		fileConnector.writeToFile(FILE_NAME, users);
		return true;
	}

	@Override
	public boolean delete(User deletedUser) throws ClassNotFoundException, IOException {
		List<User> users = fileConnector.readFromFile(FILE_NAME);
		users.removeIf(user -> user.equals(deletedUser));
		fileConnector.writeToFile(FILE_NAME, users);
		return true;
	}
}
