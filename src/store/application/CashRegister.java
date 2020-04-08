package store.application;

import java.util.ArrayList;

public class CashRegister {
	private int currentReceiptNumber = -1;
	private static int receiptNumber = -1;
	private ArrayList<Receipt> receipts = new ArrayList<Receipt>();
	@SuppressWarnings("unused")
	private String storeName;
	private String fiscalIdentifier;
	
	private boolean saleIsOngoing = false;
	private Receipt newReceipt = null;
	
	public CashRegister(String _storeName, String _fiscalIdentifier)
	{
		storeName = _storeName;
		fiscalIdentifier = _fiscalIdentifier;
	}
	
	public ArrayList<Receipt> getReceipts()
	{
		return receipts;
	}
	
	public String getFiscalIdentifier()
	{
		return fiscalIdentifier;
	}
	
	public void startNewSell()
	{
		if (saleIsOngoing == false)
		{
			receiptNumber++;
			currentReceiptNumber = receiptNumber;
			newReceipt = new Receipt();
			newReceipt.setReceiptNumber(currentReceiptNumber);
			saleIsOngoing = true;
		}
	}
	
	public void registerNewProduct(Product _product, int _quantity)
	{
		ReceiptItem newReceiptItem = new ReceiptItem(_product.getName(), _product.getPrice(), _quantity);
		newReceipt.getReceiptItems().add(newReceiptItem);
	}
	
	public boolean removeProductFromSell(Product _product, int _quantity)
	{
		if (saleIsOngoing)
		{
			for (ReceiptItem key : newReceipt.getReceiptItems())
			{
				if (key.getProductName().equalsIgnoreCase(_product.getName()))
				{
					if (_quantity != 0)
					{
						if (_quantity >= key.getQuantity())
						{
							newReceipt.getReceiptItems().remove(key);
						}
						else
						{
							key.setQuantity(key.getQuantity() - _quantity);
						}
					}
					else
					{
						newReceipt.getReceiptItems().remove(key);
					}
					return true;
				}
			}
		}
		return false;
	}
	
	public Receipt getCurrentReceipt()
	{
		if (newReceipt != null)
		{
			return newReceipt;
		}
		return null;
	}
	
	public int getCurrentReceiptNumber()
	{
		return receiptNumber;
	}
	
	public boolean getSellState()
	{
		return saleIsOngoing;
	}
	
	public void finalizeSell()
	{
		newReceipt.calculateTotal();
		receipts.add(newReceipt);

		newReceipt = null;

		saleIsOngoing = false;
	}
}
