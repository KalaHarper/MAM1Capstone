package com.techelevator;

import com.techelevator.view.Menu;

import java.util.Locale;
import java.util.Scanner;

public class VendingMachineCLI {
	Scanner userInput = new Scanner(System.in);

	private Menu menu;
	private static CashBank cashBank = new CashBank();
	private static Ledger ledger = new Ledger();
	private static Inventory inventory = new Inventory();
	private static final int SLOT_INDICATOR = 0;
	private static final int PRODUCT_NAME = 1;
	private static final int PRICE = 2;
	private static final int PRODUCT_TYPE = 3;
	private static final int AMOUNT_IN_STOCK = 4;
	private static final String MAIN_MENU_OPTION_DISPLAY_ITEMS = "Display Vending Machine Items";
	private static final String MAIN_MENU_OPTION_PURCHASE = "Purchase";
	private static final String MAIN_MENU_OPTION_EXIT = "Exit";
	private static final String[] MAIN_MENU_OPTIONS = {MAIN_MENU_OPTION_DISPLAY_ITEMS, MAIN_MENU_OPTION_PURCHASE, MAIN_MENU_OPTION_EXIT};
	private static final String PURCHASE_MENU_FEED_MONEY = "Feed Money";
	private static final String PURCHASE_MENU_SELECT_PRODUCT = "Select Product";
	private static final String PURCHASE_MENU_FINISH_TRANSACTION = "Finish Transaction";
	private static final String[] PURCHASE_MENU_OPTIONS = {PURCHASE_MENU_FEED_MONEY, PURCHASE_MENU_SELECT_PRODUCT, PURCHASE_MENU_FINISH_TRANSACTION};
	private static final String ONE_DOLLAR = "$1";
	private static final String TWO_DOLLAR = "$2";
	private static final String FIVE_DOLLAR = "$5";
	private static final String TEN_DOLLAR = "$10";
	private static final String I_HAVE_NO_MONEY = "Return to Purchase Menu";
	private static final String[] BILL_DENOMINATIONS = {ONE_DOLLAR, TWO_DOLLAR, FIVE_DOLLAR, TEN_DOLLAR, I_HAVE_NO_MONEY};


	public VendingMachineCLI(Menu menu) {
		this.menu = menu;
	}

	public void run() {

		inventory.loadInventory();

		while (true) {

			String choice = (String) menu.getChoiceFromOptions(MAIN_MENU_OPTIONS);

			if (choice.equals(MAIN_MENU_OPTION_DISPLAY_ITEMS)) {

				inventory.displayContents();//implemented new inventory method

			} else if (choice.equals(MAIN_MENU_OPTION_PURCHASE)) {

				while (true) {

					String purchasingDecision = (String) menu.getChoiceFromOptions(PURCHASE_MENU_OPTIONS);
					if (purchasingDecision.equals(PURCHASE_MENU_FEED_MONEY)) {

						while (true) {

							String billEntered = (String) menu.getChoiceFromOptions(BILL_DENOMINATIONS);

							if (billEntered.equals(ONE_DOLLAR)) {
								cashBank.addMoney(1.00);
							} else if (billEntered.equals(TWO_DOLLAR)) {
								cashBank.addMoney(2.00);
							} else if (billEntered.equals(FIVE_DOLLAR)) {
								cashBank.addMoney(5.00);
							} else if (billEntered.equals(TEN_DOLLAR)) {
								cashBank.addMoney(10.00);
							} else if (billEntered.equals(I_HAVE_NO_MONEY)) {
								System.out.println("Goodbye");
								break;
							}
							System.out.println(" ");
							System.out.println("Current Money Provided: \n" + "$" + cashBank.getMoneyProvided());
							ledger.printsToLog("FEED MONEY: " + cashBank.getMoneyProvided());
						}
					} else if (purchasingDecision.equals(PURCHASE_MENU_SELECT_PRODUCT)) {
						inventory.displayContents();//changed method from vendingmachine display method to inventory method
						System.out.println("Please select item. (Example: select A1 for Potato Crisps) ");
						String input = userInput.nextLine().toUpperCase(Locale.ROOT);
						//if(inventory.getSlotIndicatorHashMap().containsKey(input.toUpperCase())){
						if (inventory.getProductInfoBySlotMap().containsKey(input) &&
								inventory.isInStock(input, AMOUNT_IN_STOCK)) {
							double cost = Double.parseDouble(inventory.getProductDetail(input, PRICE));
							cashBank.setCost(cost);
						if(cashBank.getMoneyProvided() >= cashBank.getCost()){
							//displayDispenseMessage(input);
							System.out.println(inventory.getProductDetail(input, PRODUCT_NAME) + " " + cashBank.getCost() + " " + cashBank.getReturnAmount());
						}
						} else
							System.out.println("This item is OUT OF STOCK. Please try again.");

					} else if (purchasingDecision.equals(PURCHASE_MENU_FINISH_TRANSACTION)) {
						break; // returns to first menu
					}
					System.out.println("Current Money Provided: \n" + "$" + cashBank.getMoneyProvided());
				}
			} else if (choice.equals(MAIN_MENU_OPTION_EXIT)) {
				System.out.println("Thank you, come again!");
				System.exit(0);
			}
		}
	}

	public static void main(String[] args) {
		Menu menu = new Menu(System.in, System.out);
		VendingMachineCLI cli = new VendingMachineCLI(menu);
		cli.run();
	}

	//This was mostly so we don't have to type it all out later
//	public static void displayDispenseMessage(String input){
//		if(inventory.getProductDetail(input, PRODUCT_TYPE).equals( "Chip")){
//			System.out.println("Crunch Crunch, Yum!");
//		}else if(inventory.getProductDetail(input, PRODUCT_TYPE).equals("Candy")){
//			System.out.println("Munch Munch, Yum!");
//		}else if(inventory.getProductDetail(input, PRODUCT_TYPE).equals("Drink")){
//			System.out.println("Glug Glug, Yum!");
//		}else if(inventory.getProductDetail(input, PRODUCT_TYPE).equals("Gum")){
//			System.out.println("Chew Chew, Yum!");
//		}else {
//			System.out.println("Dispense message failure");
//		}
//	}
}

