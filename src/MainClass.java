import java.math.BigDecimal;
import java.util.ArrayList;

import store.application.*;

public class MainClass {

	public static void main(String[] args) {
		
		Stock stock = new Stock();
		Store magazin = new Store("Magazinul", "Magazin cu chestii", stock);
		
		
		EmployeeInfo infoAdmin = new EmployeeInfo("Administrator", "0711111111", "Idk");
		EmployeeInfo infoSeller1 = new EmployeeInfo("Vanzator1", "0722222222", "Idk");
		EmployeeInfo infoSeller2 = new EmployeeInfo("Vanzator2", "0733333333", "Idk");
		
		magazin.addNewCashRegister("Magazinul", "123");
		magazin.addNewAdministrator(infoAdmin, stock);
		magazin.addNewSeller(infoSeller1, stock, magazin.getCashRegisters().get(0));
		magazin.addNewSeller(infoSeller2, stock, magazin.getCashRegisters().get(0));
		
		Product coffee = new Product("Coffee", "Coffee beans", new BigDecimal(20));
		Product milk = new Product("Milk", "It's white", new BigDecimal(6.5));
		Product cup = new Product("Cup", "Coffee goes here", new BigDecimal(15));
		Product sugar = new Product("Sugar", "For my coffee", new BigDecimal(10));
		Product spoon = new Product("Spoon", "To mix the coffee", new BigDecimal(5));
		
		Administrator admin = magazin.getAdministators().get(0);
		admin.addProductToStock(coffee, 15);
		admin.addProductToStock(milk, 25);
		admin.addProductToStock(cup, 5);
		admin.addProductToStock(sugar, 10);
		admin.addProductToStock(spoon, 7);
		
		stock.print();
		
		Seller Vanzator1 = magazin.getSellers().get(0);
		Seller Vanzator2 = magazin.getSellers().get(1);
		
		Vanzator1.sellProduct(coffee, 2);
		Vanzator2.sellProduct(sugar, 5);
		
		StockItem itemCoffee = new StockItem(coffee, 10);
		StockItem itemMilk = new StockItem(milk, 10);
		ArrayList<StockItem> productsToRemove = new ArrayList<StockItem>();
		productsToRemove.add(itemCoffee);
		productsToRemove.add(itemMilk);
		
		admin.removeSingleProductFromStock(spoon, 0);
		admin.removeMultipleProductFromStock(productsToRemove);
		
		System.out.println("STOCK DUPA ADMIN.REMOVE SI SELL");
		stock.print();
		
		System.out.println("FINALIZE SELL AKA. BON:");
		
		Vanzator1.getCashRegister().finalizeSell();
		
		System.out.println("-=========");
		
		Vanzator1.returnProduct(coffee, 1, 0);
		stock.print();
		
		System.out.println("-==================");
		
		Vanzator1.sellProduct(cup, 1);
		Vanzator2.sellProduct(milk, 1);
		
		Vanzator1.getCashRegister().finalizeSell();
		
		stock.print();
	}

}
