package store.application;

public class StockItem {
	private Product product = new Product();
	private int quantity;
	
	public StockItem(Product _product, int _quantity)
	{
		product = _product;
		quantity = _quantity;
	}
	
	public Product getProduct()
	{
		return product;
	}
	
	public int getQuantity()
	{
		return quantity;
	}
	
	public void setQuantity(int _quantity)
	{
		quantity = _quantity;
	}
}
