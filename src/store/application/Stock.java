package store.application;

import java.math.BigDecimal;
import java.util.ArrayList;

public class Stock {
	
	/*
	 * StockItem already has a quantity parameter. HashMap is a bad choice.
	 * TODO: Delete comments?
	 */
	//HashMap<StockItem, Integer> stockItems = new HashMap<StockItem, Integer>();
	private ArrayList<StockItem> stockItems = new ArrayList<StockItem>();
	
	private int defaultOptionalRemovableQuantity = 0;
	
	public ArrayList<StockItem> getStockItems()
	{
		return stockItems;
	}
	
	/*
	public void add(StockItem _stockItem, int _quantity)
	{
		boolean ok = false;
		for (StockItem key : stockItems.)
		{
			if (_stockItem.getProduct().getName().equalsIgnoreCase(key.getProduct().getName()))
			{
				stockItems.replace(key, stockItems.get(key) + _quantity);
				ok = true;
				break;
			}
		}
		if (!ok)
		{
			stockItems.put(_stockItem, _quantity);
		}
	}
	
	public void remove(StockItem _stockItem, int _quantity)
	{
		for (StockItem key : stockItems.keySet())
		{
			if (_stockItem.getProduct().getName().equalsIgnoreCase(key.getProduct().getName()))
			{
				if (_quantity != 0)
				{
					stockItems.replace(key, stockItems.get(key), _quantity);
				}
				else
				{
					stockItems.remove(key);
				}
				break;
			}
		}
	}
	
	public void remove(StockItem _stockItem)
	{
		remove(_stockItem, defaultOptionalRemovableQuantity);
	}
	
	public void updateProductPrice(StockItem _stockItem, BigDecimal newPrice)
	{
		for (StockItem key : stockItems.keySet())
		{
			if (_stockItem.getProduct().getName().equalsIgnoreCase(key.getProduct().getName()))
			{
				key.getProduct().setPrice(newPrice);
			}
			break;
		}
	}
	*/
	public void add(StockItem _stockItem)
	{
		boolean ok = false;
		for (StockItem key : stockItems)
		{
			if (key.getProduct().getName().equalsIgnoreCase(_stockItem.getProduct().getName()))
			{
				key.setQuantity(key.getQuantity() + _stockItem.getQuantity());
				ok = true;
				break;
			}
		}
		if(!ok)
		{
			stockItems.add(_stockItem);
		}
	}
	
	public void remove(StockItem _stockItem, int _quantity)
	{
		for (StockItem key : stockItems)
		{
			if (key.getProduct().getName().equalsIgnoreCase(_stockItem.getProduct().getName()))
			{
				if (_quantity != 0)
				{
					if (_quantity >= key.getQuantity())
					{
						stockItems.remove(key);
					}
					else
					{
						key.setQuantity(key.getQuantity() - _quantity);
					}
				}
				else
				{
					stockItems.remove(key);
				}
				break;
			}
		}
	}
	
	public void remove(StockItem _stockItem)
	{
		remove(_stockItem, defaultOptionalRemovableQuantity);
	}
	
	public void updateProductPrice(StockItem _stockItem, BigDecimal _price)
	{
		for (StockItem key : stockItems)
		{
			if (key.getProduct().getName().equalsIgnoreCase(_stockItem.getProduct().getName()))
			{
				key.getProduct().setPrice(_price);
			}
			break;
		}
	}
}