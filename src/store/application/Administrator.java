package store.application;

import java.util.ArrayList;

public class Administrator {
	
	@SuppressWarnings("unused")
	private EmployeeInfo employeeInfo = null;
	private Stock stock = null;
	
	public Administrator(EmployeeInfo _employeeInfo, Stock _stock)
	{
		employeeInfo = _employeeInfo;
		stock = _stock;
	}
	
	public Stock getStock()
	{
		return stock;
	}
	
	public void addProductToStock(Product _product, int _quantity)
	{
		StockItem _stockItem = new StockItem(_product, _quantity);
		stock.add(_stockItem);
	}
	
	public boolean checkProductStock(Product _product)
	{
		for (StockItem key : stock.getStockItems())
		{
			if (_product.getName().equalsIgnoreCase(key.getProduct().getName()))
			{
				return true;
			}
		}
		return false;
	}
	
	public void removeSingleProductFromStock(Product _product, int _quantity)
	{
		StockItem _stockItem = new StockItem(_product, _quantity);
		stock.remove(_stockItem, _quantity);
	}
	
	public void removeMultipleProductFromStock(ArrayList<StockItem> _productsToRemove)
	{
		for (StockItem key : _productsToRemove)
		{
			stock.remove(key, key.getQuantity());
		}
	}
}
