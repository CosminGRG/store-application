package store.application;

import java.math.BigDecimal;
import java.util.*;

public class Store {
	String description;
	String name;
	Stock stock;
	ArrayList<CashRegister> cashRegisters = new ArrayList<CashRegister>();
	ArrayList<Seller> sellers = new ArrayList<Seller>();
	ArrayList<Administrator> administrators = new ArrayList<Administrator>();
	
	public Store(String _name, String _description)
	{
		name = _name;
		description = _description;
		stock = new Stock();
	}
	
	public void addNewCashRegister(String _storeName, String _fiscalIdentifier)
	{
		CashRegister newCashRegister = new CashRegister(_storeName, _fiscalIdentifier);
		cashRegisters.add(newCashRegister);
		newCashRegister = null;
	}
	
	public void addNewSeller(EmployeeInfo _employeeInfo, Stock _stock, CashRegister _cashRegister)
	{
		Seller newSeller = new Seller(_employeeInfo, _stock, _cashRegister);
		sellers.add(newSeller);
		newSeller = null;
	}
	
	public void addNewAdministrator(EmployeeInfo _employeeInfo, Stock _stock)
	{
		Administrator newAdministrator = new Administrator(_employeeInfo, _stock);
		administrators.add(newAdministrator);
		newAdministrator = null;
	}
	
	public ArrayList<CashRegister> getCashRegisters()
	{
		return cashRegisters;
	}
	
	public ArrayList<Administrator> getAdministators()
	{
		return administrators;
	}
	
	public ArrayList<Seller> getSellers()
	{
		return sellers;
	}
	
	public Stock getStock()
	{
		return stock;
	}
	
	public String getStoreName()
	{
		return name;
	}
	
	public Product ConstructProduct(Scanner input)
	{
		input.nextLine();
		System.out.println("Product Name:");
		String productName = input.nextLine();
		System.out.println("Product Description:");
		String productDescription = input.nextLine();
		System.out.println("Product Price:");
		BigDecimal productPrice = new BigDecimal(input.nextInt());
		HashMap<String, String> characteristics = new HashMap<String, String>();

		System.out.println("Any characteristics? 1 - Yes, 0 - No");
		int option = input.nextInt();
		if (option == 1)
		{
			System.out.println("How many?");
			int noChar = input.nextInt();
			for (int i = 0; i < noChar; i++)
			{
				input.nextLine();
				System.out.println("Characteristic:");
				String charKey = input.nextLine();
				System.out.println("Characterisitc value:");
				String charValue = input.nextLine();
				characteristics.put(charKey, charValue);
			}
		}
		
		Product newProduct = new Product(productName, characteristics, productDescription, productPrice);
		
		return newProduct;
	}
}
