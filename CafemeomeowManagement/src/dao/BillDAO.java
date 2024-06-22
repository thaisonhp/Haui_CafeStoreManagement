package dao;

import java.io.IOException;

import java.util.List;
import java.util.function.Predicate;

import model.Bill;
import	util.*;;

public class BillDAO implements DAO<Bill> {
	private final String FILE_PATH = "/src/db/bills.bin";
	private final FileConnector<Bill> fileConnector = new FileConnector<Bill>();

	@Override
	public Bill get(Predicate<Bill> predicate) throws ClassNotFoundException, IOException {
		List<Bill> bills = fileConnector.readFromFile(FILE_PATH);
		for (Bill bill : bills) {
			if (predicate.test(bill)) {
				return bill;
			}
		}
		return null;
	}

	@Override
	public List<Bill> getAll() throws ClassNotFoundException, IOException {
		return fileConnector.readFromFile(FILE_PATH);
	}

	@Override
	public boolean add(Bill bill) throws ClassNotFoundException, IOException {
		fileConnector.appendObject(FILE_PATH, bill);
		return true;
	}

	@Override
	public boolean update(Bill updatedBill) throws ClassNotFoundException, IOException {
		List<Bill> bills = fileConnector.readFromFile(FILE_PATH);
		for (int i = 0; i < bills.size(); i++) {
			if (bills.get(i).getId() == updatedBill.getId()) {
				bills.set(i, updatedBill);
				break;
			}
		}
		fileConnector.writeToFile(FILE_PATH, bills);
		return true;
	}

	@Override
	public boolean delete(Bill deletedBill) throws ClassNotFoundException, IOException {
		List<Bill> bills = fileConnector.readFromFile(FILE_PATH);
		bills.removeIf(user -> user.equals(deletedBill));
		fileConnector.writeToFile(FILE_PATH, bills);
		return true;
	}

}
