package store.application;

import java.math.BigDecimal;
import java.util.ArrayList;

public class Receipt {
	private ArrayList<ReceiptItem> receiptItems = new ArrayList<ReceiptItem>();
	private BigDecimal total = BigDecimal.ZERO;
	private int receiptNumber;
	
	public void setReceiptNumber(int _receiptNumber)
	{
		receiptNumber = _receiptNumber;
	}
	
	public ArrayList<ReceiptItem> getReceiptItems()
	{
		return receiptItems;
	}
	
	public BigDecimal calculateProductTotal(ReceiptItem _receiptItem)
	{
		_receiptItem.setTotal(_receiptItem.getPPUnit().multiply(new BigDecimal(_receiptItem.getQuantity())));
		return _receiptItem.getTotal();
	}
	
	public void calculateTotal()
	{
		for (ReceiptItem key : receiptItems)
		{
			total = total.add(calculateProductTotal(key));
		}
	}
	
	public BigDecimal getTotal()
	{
		return total;
	}
	
	public void print()
	{
		for (int i = 0; i < receiptItems.size(); i++) {
		      System.out.println(receiptItems.get(i).getProductName() + "------" + receiptItems.get(i).getQuantity());
		    }
	}
}
