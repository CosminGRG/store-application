package store.application;

import java.util.HashMap;

public class Seller {
	@SuppressWarnings("unused")
	private EmployeeInfo employeeInfo = null;
	private Stock stock = null;
	private CashRegister cashRegister = null;
	
	public CashRegister getCashRegister()
	{
		return cashRegister;
	}
	
	public void setCashRegister(CashRegister _cashRegister)
	{
		cashRegister = _cashRegister;
	}
	
	public Seller(EmployeeInfo _employeeInfo, Stock _stock, CashRegister _cashRegister)
	{
		employeeInfo = _employeeInfo;
		stock = _stock;
		cashRegister = _cashRegister;
	}
	
	public StockItem checkProductStock(String _productName)
	{
		for (StockItem key : stock.getStockItems())
		{
			if (_productName.equalsIgnoreCase(key.getProduct().getName()))
			{
				return key;
			}
		}
		return null;
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
		cashRegister.startNewSell();
		StockItem _stockItem = new StockItem(_product, _quantity);
		for (StockItem key : stock.getStockItems())
		{
			if (key.getProduct().getName().equalsIgnoreCase(_product.getName()))
			{
				if (key.getQuantity() > _quantity)
				{
					stock.remove(_stockItem, _quantity);
					cashRegister.registerNewProduct(_product, _quantity);
				}
			}
		}
	}

	public boolean removeProductFromSell(Product _product, int _quantity)
	{
		boolean ok = cashRegister.removeProductFromSell(_product, _quantity);
		if (ok)
		{
			return true;
		}
		else
		{
			return false;
		}
	}
	
	@SuppressWarnings("unlikely-arg-type")
	public void sellMultipleProducts(HashMap<Product, Integer> _productsToSell)
	{
		cashRegister.startNewSell();
		for (Product _productKey : _productsToSell.keySet())
		{
			for (StockItem key : stock.getStockItems())
			{
				if (key.getProduct().getName().equalsIgnoreCase(_productKey.getName()))
				{
					if (key.getQuantity() > _productsToSell.get(key))
					{
						StockItem _stockItem = new StockItem(_productKey, _productsToSell.get(_productKey));
						stock.remove(_stockItem, _productsToSell.get(_productKey));
						cashRegister.registerNewProduct(_productKey, _productsToSell.get(_productKey));
					}
				}
			}
		}
	}
}
