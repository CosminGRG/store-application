package store.application;

//import java.math.RoundingMode;
import java.math.BigDecimal;
import java.util.HashMap;

public class Product {
	private String name;
	private HashMap<String, String> characteristics = new HashMap<String, String>();
	private String description;
	private BigDecimal price = new BigDecimal(0);
	
	public Product() { }
	
	public Product(String _name, HashMap<String, String> _characteristics, String _description, BigDecimal _price)
	{
		name = _name;
		characteristics = _characteristics;
		description = _description;
		price = _price;
	}
	
	public Product(String _name, String _description, BigDecimal _price)
	{
		name = _name;
		description = _description;
		price = _price;
	}
	
	public String getName()
	{
		return name;
	}
	
	public BigDecimal getPrice()
	{
		return price;
	}
	
	public String getDescription()
	{
		return description;
	}
	
	public HashMap<String, String> getCharacteristics()
	{
		return characteristics;
	}
	
	public void setPrice(BigDecimal _price)
	{
		price = _price;
	}
}
