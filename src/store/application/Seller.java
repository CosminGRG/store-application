package store.application;

import java.util.HashMap;

public class Seller {
	private EmployeeInfo employeeInfo = null;
	private Stock stock = null;
	private CashRegister cashRegister = null;
	
	public CashRegister getCashRegister()
	{
		return cashRegister;
	}
	
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

	public boolean returnProduct(Product _product, int _quantity, int _receiptNumber)
	{
		Receipt returnReceipt = cashRegister.getReceipts().get(_receiptNumber);
		for (ReceiptItem key : returnReceipt.getReceiptItems())
		{
			if (key.getProductName().equalsIgnoreCase(_product.getName()))
			{
				if (key.getQuantity() >= _quantity)
				{
					StockItem stockItem = new StockItem(_product, _quantity);
					stock.add(stockItem);
					return true;
				}
				break;
			}
		}
		return false;
	}
	
	public void sellProduct(Product _product, int _quantity)
	{
		StockItem _stockItem = new StockItem(_product, _quantity);
		stock.remove(_stockItem, _quantity);
		cashRegister.startNewSell(_product, _quantity);
	}

	public void sellMultipleProducts(HashMap<Product, Integer> _productsToSell)
	{
		for (Product _productKey : _productsToSell.keySet())
		{
			StockItem _stockItem = new StockItem(_productKey, _productsToSell.get(_productKey));
			stock.remove(_stockItem, _productsToSell.get(_productKey));
			cashRegister.startNewSell(_productKey, _productsToSell.get(_productKey));
		}
	}
}
