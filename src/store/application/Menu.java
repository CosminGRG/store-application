package store.application;

import java.util.Scanner;

public class Menu {

	public Menu() { }

	public void InitMenu(Scanner input, Store store)
	{
		ClearConsole();
		int option = 1;
		while (option != 0)
		{
			System.out.println("0. Exit");
			System.out.println("1. Use as administrator");
			System.out.println("2. Use as seller");
			option = input.nextInt();
			switch (option)
			{
			case 1:
				ClearConsole();
				AdminMenu(input, store);
				break;
			case 2:
				ClearConsole();
				SellerMenu(input, store);
				break;
			default:
				break;
			}
		}
	}
	
	private void AdminMenu(Scanner input, Store store)
	{
		int option = 1;
		while (option != 0)
		{
			System.out.println("0. Back");
			System.out.println("1. Stock");
			System.out.println("2. Cash Registers");
			option = input.nextInt();
			switch (option)
			{
			case 1:
				//CHOOSE ADMIN NUMBER?
				ClearConsole();
				AdminStockMenu(input, store);
				break;
			case 2:
				ClearConsole();
				AdminRegistersMenu(input, store);
				break;
			default:
				break;
			}
		}
		ClearConsole();
	}
	
	private void AdminStockMenu(Scanner input, Store store)
	{
		Product newProduct;
		int quantity;
		String productName;
		
		int option = 1;
		while (option != 0)
		{
			System.out.println("0. Back");
			System.out.println("1. Add Product");
			System.out.println("2. Remove Product");
			System.out.println("3. View Stock");
			option = input.nextInt();
			switch (option)
			{
			case 1:
				//Add product
				newProduct = store.ConstructProduct(input);
				
				System.out.println("Quantity:");
				quantity = input.nextInt();
				
				store.getAdministators().get(0).addProductToStock(newProduct, quantity);
				
				System.out.println("Added " + quantity + " units - " + newProduct.getName());
				break;
			case 2:
				//Remove product
				input.nextLine();
				System.out.println("Product Name: ");
				productName = input.nextLine();
				System.out.println("Quantity");
				quantity = input.nextInt();
				
				newProduct = new Product(productName);
				store.getAdministators().get(0).removeSingleProductFromStock(newProduct, quantity);
				
				System.out.println("Removed " + quantity + " units - " + productName);
				break;
			case 3:
				//View stock
				if (store.getStock().getStockItems().size() == 0)
				{
					System.out.println("Stock is empty.");
				}
				else
				{
					for (int i = 0; i < store.getStock().getStockItems().size(); i++)
					{
						StockItem product = store.getStock().getStockItems().get(i);
						System.out.println(product.getProduct().getName() + "  -  " + product.getQuantity() + " units");
					}
				}
				break;
			default:
				break;
			}
		}
		ClearConsole();
	}
	
	private void AdminRegistersMenu(Scanner input, Store store)
	{
		int index;
		String fiscalIdentifier;
		int option = 1;
		while (option != 0)
		{
			System.out.println("0. Back");
			System.out.println("1. Add Cash Register");
			System.out.println("2. Remove Cash Register");
			System.out.println("3. View Cash Registers");
			option = input.nextInt();
			switch (option)
			{
			case 1:
				//Add Cash Register		
				input.nextLine();
				System.out.println("Fiscal Identifier: ");
				fiscalIdentifier = input.nextLine();
				
				store.addNewCashRegister(store.getStoreName(), fiscalIdentifier);
				System.out.println("Added new cash register " + fiscalIdentifier);
				break;
			case 2:
				//Remove Cash Register
				System.out.println("Index: ");
				index = input.nextInt();
				
				System.out.println("Removed cash register " + store.getCashRegisters().get(index).getFiscalIdentifier());
				
				store.getCashRegisters().remove(index);
				store.getSellers().get(0).setCashRegister(null);
				
				break;
			case 3:
				//View Cash Registers
				System.out.println("There is/are currently " + store.getCashRegisters().size() + " cash register(s).");
				for (int i = 0; i < store.getCashRegisters().size(); i++)
				{
					System.out.println(i + " " + store.getCashRegisters().get(i).getFiscalIdentifier());
				}
				System.out.println();
				
				break;
			default:
				break;
			}
		}
		ClearConsole();
	}
	
	private void SellerMenu(Scanner input, Store store)
	{
		if (store.getSellers().get(0).getCashRegister() != null)
		{
			String productName;
			int option = 1;
			while (option != 0)
			{
				System.out.println("0. Back");
				System.out.println("1. Start Sell");
				System.out.println("2. Search Product");
				System.out.println("3. View Stock");
				option = input.nextInt();
				switch (option)
				{
				case 1:
					//Start sell
					ClearConsole();
					SellerSellMenu(input, store);
					break;
				case 2:
					//Search product
					input.nextLine();
					System.out.println("Product Name:");
					productName = input.nextLine();
					StockItem stockProduct = store.getSellers().get(0).checkProductStock(productName);
					if (stockProduct != null)
					{
						System.out.println("Product is in stock.");
						System.out.println(stockProduct.getProduct().getName() + " - " + stockProduct.getQuantity() + " units");
					}
					else
					{
						System.out.println("Product not in stock.");
					}
					break;
				case 3:
					//View stock
					for (int i = 0; i < store.getStock().getStockItems().size(); i++)
					{
						StockItem product = store.getStock().getStockItems().get(i);
						System.out.println(product.getProduct().getName() + "  -  " + product.getQuantity() + " units");
					}
					break;
				default:
					break;
				}
			}
			ClearConsole();
		}
		else
		{
			System.out.println("Seller does not have a cash register. Assign cash register.");
			
			if (store.getCashRegisters().size() == 0)
			{
				System.out.println("No cash register. A new one will be created:");
				input.nextLine();
				System.out.println("Fiscal Identifier: ");
				String fiscalIdentifier = input.nextLine();
				
				store.addNewCashRegister(store.getStoreName(), fiscalIdentifier);
				System.out.println("Added new cash register " + fiscalIdentifier);
				
				store.getSellers().get(0).setCashRegister(store.getCashRegisters().get(0));
			}
			else
			{
				System.out.println("There is/are currently " + store.getCashRegisters().size() + " cash register(s).");
				for (int i = 0; i < store.getCashRegisters().size(); i++)
				{
					System.out.println(i + " " + store.getCashRegisters().get(i).getFiscalIdentifier());
				}
				System.out.println();
				System.out.println("Cash register index:");
				int index = input.nextInt();
				
				store.getSellers().get(0).setCashRegister(store.getCashRegisters().get(index));
			}
			
		}
	}
	
	private void SellerSellMenu(Scanner input, Store store)
	{
		String productName;
		int quantity;
		StockItem stockProduct;
		Product newProduct;
		Receipt currentReceipt;
		int currentReceiptNumber;
		
		int option = 1;
		while (option != 0)
		{
			System.out.println("0. Back");
			System.out.println("1. Add Product To Sell");
			System.out.println("2. Remove Product");
			System.out.println("3. View Current Sell");
			System.out.println("4. Finalize Sell");
			option = input.nextInt();
			switch (option)
			{
			case 1:
				//Add product to sell
				input.nextLine();
				System.out.println("Product Name:");
				productName = input.nextLine();
				System.out.println("Quantity:");
				quantity = input.nextInt();

				stockProduct = store.getSellers().get(0).checkProductStock(productName);
				
				if (stockProduct != null)
				{
					store.getSellers().get(0).sellProduct(stockProduct.getProduct(), quantity);
					System.out.println(quantity + " units added to sell -" + stockProduct.getProduct().getName());
					System.out.println();
				}
				else
				{
					System.out.println("Invalid product");
				}
				
				stockProduct = null;
				
				break;
			case 2:
				//Remove product from sell
				input.nextLine();
				System.out.println("Product Name:");
				productName = input.nextLine();
				System.out.println("Quantity:");
				quantity = input.nextInt();
				
				newProduct = new Product(productName);
				
				if (store.getSellers().get(0).removeProductFromSell(newProduct, quantity))
				{
					System.out.println("Removed " + quantity + " units - " + newProduct.getName());
					System.out.println();
				}
				else
				{
					System.out.println("Product not found or bad quantity");
					System.out.println();
				}
				
				break;
			case 3:
				//View current sell
				currentReceipt = store.getCashRegisters().get(0).getCurrentReceipt();
				
				if (currentReceipt != null)
				{
					for (ReceiptItem key : currentReceipt.getReceiptItems())
					{
						System.out.println(key.getProductName() + " " + key.getQuantity() + " units " + key.getPPUnit() + " ppu");
					}
				}
				else
				{
					System.out.println("Add products first");
				}
				System.out.println();
				
				break;
			case 4:
				
				if(store.getCashRegisters().get(0).getSellState())
				{
					currentReceiptNumber = store.getCashRegisters().get(0).getCurrentReceiptNumber();
					store.getCashRegisters().get(0).finalizeSell();
					currentReceipt = store.getCashRegisters().get(0).getReceipts().get(currentReceiptNumber);
					for (ReceiptItem key : currentReceipt.getReceiptItems())
					{
						System.out.println(key.getProductName() + " " + key.getQuantity() + " " + key.getPPUnit());
					}
					System.out.println("Total: " + currentReceipt.getTotal());
					System.out.println();
				}
				else
				{
					System.out.println("Add products first");
					System.out.println();
				}
				break;
			default:
				break;
			}
		}
		ClearConsole();
	}
	
	private final static void ClearConsole()
	{
		try
	    {
	        final String os = System.getProperty("os.name");

	        if (os.contains("Windows"))
	        {
	        	new ProcessBuilder("cmd", "/c", "cls").inheritIO().start().waitFor();
	        }
	        else
	        {
	            Runtime.getRuntime().exec("clear");
	        }
	    }
	    catch (final Exception e)
	    {
	        //Handle exceptions.
	    }
	}

}
