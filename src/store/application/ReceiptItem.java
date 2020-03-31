package store.application;

import java.math.BigDecimal;

public class ReceiptItem {
	private String productName;
	private BigDecimal ppUnit = BigDecimal.ZERO;
	private int quantity;
	private BigDecimal total = BigDecimal.ZERO;
	
	public ReceiptItem(String _productName, BigDecimal _ppUnit, int _quantity)
	{
		productName = _productName;
		ppUnit = _ppUnit;
		quantity = _quantity;
	}
	
	public void setTotal(BigDecimal _total)
	{
		total = _total;
	}
	
	public int getQuantity()
	{
		return quantity;
	}
	
	public String getProductName()
	{
		return productName;
	}
	
	public BigDecimal getPPUnit()
	{
		return ppUnit;
	}
	
	public BigDecimal getTotal()
	{
		return total;
	}
}
