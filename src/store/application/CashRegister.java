package store.application;

import java.util.ArrayList;

public class CashRegister {
	private int currentReceiptNumber = -1;
	private static int receiptNumber = -1;
	private ArrayList<Receipt> receipts = new ArrayList<Receipt>();
	String storeName;
	String fiscalIdentifier;
	
	public CashRegister(String _storeName, String _fiscalIdentifier)
	{
		storeName = _storeName;
		fiscalIdentifier = _fiscalIdentifier;
		receiptNumber++;
		currentReceiptNumber = receiptNumber;
	}
	
	public void startNewSell(Product _product, int _quantity)
	{
		Receipt newReceipt = new Receipt();
		ReceiptItem newReceiptItem = new ReceiptItem(_product.getName(), _product.getPrice(), _quantity);
		newReceipt.getReceiptItems().add(newReceiptItem);
		receipts.add(newReceipt);
	}
	
	public void removeProductFromSell(Product _product, int _quantity)
	{
		
	}
	
	public void finalizeSell()
	{
		
	}
}
