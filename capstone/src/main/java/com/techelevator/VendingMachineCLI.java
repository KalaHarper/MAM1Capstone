package com.techelevator;

import com.techelevator.view.Menu;

import java.math.BigDecimal;
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
		ledger.startSalesReport();

		while (true) {

			String choice = (String) menu.getChoiceFromOptions(MAIN_MENU_OPTIONS);

			if (choice.equals(MAIN_MENU_OPTION_DISPLAY_ITEMS)) {

				inventory.displayContents();

			} else if (choice.equals(MAIN_MENU_OPTION_PURCHASE)) {

				while (true) {

					String purchasingDecision = (String) menu.getChoiceFromOptions(PURCHASE_MENU_OPTIONS);
					if (purchasingDecision.equals(PURCHASE_MENU_FEED_MONEY)) {

						while (true) {

							String billEntered = (String) menu.getChoiceFromOptions(BILL_DENOMINATIONS);
							String start = cashBank.getMoneyProvided();
							if (billEntered.equals(ONE_DOLLAR)) {

								cashBank.addMoney(BigDecimal.valueOf(1.00));
								ledger.printsToLog("FEED MONEY: " + "$" + start + " $" + cashBank.getMoneyProvided());
							} else if (billEntered.equals(TWO_DOLLAR)) {
								cashBank.addMoney(BigDecimal.valueOf(2.00));
								ledger.printsToLog("FEED MONEY: " + "$" + start + " $" + cashBank.getMoneyProvided());
							} else if (billEntered.equals(FIVE_DOLLAR)) {
								cashBank.addMoney(BigDecimal.valueOf(5.00));
								ledger.printsToLog("FEED MONEY: " + "$" + start + " $" + cashBank.getMoneyProvided());
							} else if (billEntered.equals(TEN_DOLLAR)) {
								cashBank.addMoney(BigDecimal.valueOf(10.00));
								ledger.printsToLog("FEED MONEY: " + "$" + start + " $" + cashBank.getMoneyProvided());
							} else if (billEntered.equals(I_HAVE_NO_MONEY)) {
								System.out.println("Goodbye");
								break;
							}
							System.out.println(" ");
							System.out.println("Current Money Provided: \n" + "$" + cashBank.getMoneyProvided());

						}
					} else if (purchasingDecision.equals(PURCHASE_MENU_SELECT_PRODUCT)) {
						inventory.displayContents();
						System.out.println("Please select item. (Example: select A1 for Potato Crisps) ");
						String input = userInput.nextLine().trim().toUpperCase(Locale.ROOT);
						String start = cashBank.getMoneyProvided();
						if (inventory.getProductInfoBySlotMap().containsKey(input) &&
								inventory.isInStock(input)) {
							BigDecimal cost = BigDecimal.valueOf(Double.parseDouble(inventory.getProductCost(input, PRICE)));
							cashBank.setCost(cost);
							if (Double.parseDouble(cashBank.getMoneyProvided()) >= cashBank.getCost().doubleValue()) {
								inventory.decrementStock(input);
								System.out.println(inventory.getProductDetail(input, PRODUCT_NAME) + " $" + cashBank.getCost() + " $" + cashBank.getReturnAmount());
								System.out.println(inventory.getSound(inventory.getProductDetail(input, PRODUCT_TYPE)));
								ledger.printsToLog(inventory.getProductDetail(input, PRODUCT_NAME) + " " + input + " $" + start + " $" + cashBank.getMoneyProvided());
								ledger.updateSalesReport(inventory.getProductDetail(input, PRODUCT_NAME));
							}
						} else
							System.out.println("This item is OUT OF STOCK. Please try again.");

					} else if (purchasingDecision.equals(PURCHASE_MENU_FINISH_TRANSACTION)) {
						String start = cashBank.getMoneyProvided();
						cashBank.makeChange();
						ledger.printsToLog("GIVE CHANGE: $" + start + " $" + cashBank.getMoneyProvided());
						break; // returns to first menu
					}
					System.out.println("Current Money Provided: \n" + "$" + cashBank.getMoneyProvided());
				}
			} else if (choice.equals(MAIN_MENU_OPTION_EXIT)) {
				System.out.println("Thank you, come again!");
				System.exit(0);
			} else if (choice.equals("4")){
				ledger.printSalesReport();
			}
		}
	}

	public static void main(String[] args) {
		Menu menu = new Menu(System.in, System.out);
		VendingMachineCLI cli = new VendingMachineCLI(menu);
		cli.run();
	}
}
