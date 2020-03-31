package store.application;

//import java.math.RoundingMode;
import java.math.BigDecimal;
import java.util.HashMap;

public class Product {
	String name;
	HashMap<String, String> characteristics = new HashMap<String, String>();
	String description;
	BigDecimal price = new BigDecimal(0);
	
	public Product() { }
	
	public Product(String _name, HashMap<String, String> _characteristics, String _description, BigDecimal _price)
	{
		name = _name;
		characteristics = _characteristics;
		description = _description;
		price = _price;
	}
	
	public String getName()
	{
		return name;
	}
	
	public void setPrice(BigDecimal _price)
	{
		price = _price;
	}
}
