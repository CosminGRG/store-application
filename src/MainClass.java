
import java.util.Scanner;

import store.application.*;

public class MainClass {

	public static void main(String[] args) {
		
		Scanner input = new Scanner(System.in);
		Store store = new Store("Magazinul", "Magazin");
		Menu menu = new Menu();
		
		EmployeeInfo infoAdmin = new EmployeeInfo("Administrator", "0711111111", "Idk");
		EmployeeInfo infoSeller = new EmployeeInfo("Seller", "0722222222", "Idk");
		store.addNewCashRegister(store.getStoreName(), "33f4asdas");
		store.addNewAdministrator(infoAdmin, store.getStock());
		store.addNewSeller(infoSeller, store.getStock(), store.getCashRegisters().get(0));
		
		menu.InitMenu(input, store);
		
		input.close();
	}
}
