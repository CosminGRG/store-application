package store.application;

import java.util.*;

public class Store {
	String description;
	String name;
	Stock stock;
	ArrayList<CashRegister> cashRegisters = new ArrayList<CashRegister>();
	ArrayList<Seller> sellers = new ArrayList<Seller>();
	ArrayList<Administrator> administrators = new ArrayList<Administrator>();
	
	public Store(String _name, String _description, Stock _stock)
	{
		name = _name;
		description = _description;
		stock = _stock;
	}
	
	public void addNewCashRegister(String storeName, String fiscalIdentifier)
	{
		CashRegister newCashRegister = new CashRegister(storeName, fiscalIdentifier);
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
}
