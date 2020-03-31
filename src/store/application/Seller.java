package store.application;

import java.util.HashMap;

public class Seller {
	@SuppressWarnings("unused")
	private EmployeeInfo employeeInfo = null;
	private Stock stock = null;
	private CashRegister cashRegister = null;
	
	public Seller(EmployeeInfo _employeeInfo, Stock _stock, CashRegister _cashRegister)
	{
		employeeInfo = _employeeInfo;
		stock = _stock;
		cashRegister = _cashRegister;
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

	public void returnProduct(Product _product, int _quantity)
	{
		/*
		 * TODO: Check receipt for product and quantity;
		 */
		StockItem _stockItem = new StockItem(_product, _quantity);
		stock.add(_stockItem);
	}
	
	//A hashmap containing Products and quantities?
	public void returnMultipleProducts(HashMap<Product, Integer> _productsToReturn)
	{
		for (Product key : _productsToReturn.keySet())
		{
			StockItem _stockItem = new StockItem(key, _productsToReturn.get(key));
			stock.add(_stockItem);
		}
	}
	
	/*
	 * TODO: Implement receipt and cash register;
	 * When you sell something you make a new receipt and add the products to it same happens for multiple sales;
	 */
	public void sellProduct(Product _product, int _quantity)
	{
		StockItem _stockItem = new StockItem(_product, _quantity);
		stock.remove(_stockItem, _quantity);
		cashRegister.startNewSell(_product, _quantity);
		/*
		 for (StockItem key : stock.getStockItems())
		{
			if (_product.getName().equalsIgnoreCase(key.getProduct().getName()))
			{
				cashRegister.startNewSell(_product, _quantity);
				if (key.getQuantity() > _quantity)
				{
					key.setQuantity(key.getQuantity() - _quantity);
				}
				else
				{
					stock.getStockItems().remove(key);
				}
				break;
			}
		}
		*/
	}

	public void sellMultipleProducts(HashMap<Product, Integer> _productsToSell)
	{
		for (Product _productKey : _productsToSell.keySet())
		{
			StockItem _stockItem = new StockItem(_productKey, _productsToSell.get(_productKey));
			stock.remove(_stockItem, _productsToSell.get(_productKey));
			/*
			for(StockItem _stockKey : stock.getStockItems())
			{
				if (_productKey.getName().equalsIgnoreCase(_stockKey.getProduct().getName()))
				{
					
					
					if (_stockKey.getQuantity() > _productsToSell.get(_productKey))
					{
						_stockKey.setQuantity(_stockKey.getQuantity() - _productsToSell.get(_productKey));
					}
					else
					{
						stock.getStockItems().remove(_stockKey);
					}
					break;
				}
			}
			*/
		}
	}
}
