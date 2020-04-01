package store.application;

import java.util.ArrayList;

public class CashRegister {
	private int currentReceiptNumber = -1;
	private static int receiptNumber = -1;
	private ArrayList<Receipt> receipts = new ArrayList<Receipt>();
	@SuppressWarnings("unused")
	private String storeName;
	@SuppressWarnings("unused")
	private String fiscalIdentifier;
	
	private boolean saleIsOngoing = false;
	private Receipt newReceipt = new Receipt();
	
	public CashRegister(String _storeName, String _fiscalIdentifier)
	{
		storeName = _storeName;
		fiscalIdentifier = _fiscalIdentifier;
	}
	
	public ArrayList<Receipt> getReceipts()
	{
		return receipts;
	}
	
	public void startNewSell(Product _product, int _quantity)
	{
		if (saleIsOngoing == false)
		{
			receiptNumber++;
			currentReceiptNumber = receiptNumber;
			newReceipt.setReceiptNumber(currentReceiptNumber);
			saleIsOngoing = true;
		}
		ReceiptItem newReceiptItem = new ReceiptItem(_product.getName(), _product.getPrice(), _quantity);
		newReceipt.getReceiptItems().add(newReceiptItem);
	}
	
	public void removeProductFromSell(Product _product, int _quantity)
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
					break;
				}
			}
		}
	}
	
	public void finalizeSell()
	{
		newReceipt.calculateTotal();
		receipts.add(newReceipt);
		//receipts.get(currentReceiptNumber).print();
		//System.out.println(receipts.get(currentReceiptNumber).getTotal());
		//newReceipt.getReceiptItems().clear();
		saleIsOngoing = false;
	}
}
