package com.techelevator;

import com.techelevator.view.Menu;

import java.math.BigDecimal;
import java.util.Locale;
import java.util.Scanner;

public class VendingMachineCLI {
	Scanner userInput = new Scanner(System.in);

	private final Menu menu;
	private static final CashBank cashBank = new CashBank();
	private static final Ledger ledger = new Ledger();
	private static final Inventory inventory = new Inventory();
	private static final int PRODUCT_NAME = 1;
	private static final int PRICE = 2;
	private static final int PRODUCT_TYPE = 3;
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

			switch (choice) {
				case MAIN_MENU_OPTION_DISPLAY_ITEMS:

					inventory.displayContents();

					break;
				case MAIN_MENU_OPTION_PURCHASE:

					label:
					while (true) {

						String purchasingDecision = (String) menu.getChoiceFromOptions(PURCHASE_MENU_OPTIONS, CashBank.getMoneyProvided());
						switch (purchasingDecision) {
							case PURCHASE_MENU_FEED_MONEY:

								label1:
								while (true) {

									String billEntered = (String) menu.getChoiceFromOptions(BILL_DENOMINATIONS, CashBank.getMoneyProvided());
									String start = CashBank.getMoneyProvided().toString();
									switch (billEntered) {
										case ONE_DOLLAR:

											CashBank.addMoney(BigDecimal.valueOf(1.00));
											ledger.printsToLog("FEED MONEY: " + "$" + start + " $" + CashBank.getMoneyProvided());
											break;
										case TWO_DOLLAR:
											CashBank.addMoney(BigDecimal.valueOf(2.00));
											ledger.printsToLog("FEED MONEY: " + "$" + start + " $" + CashBank.getMoneyProvided());
											break;
										case FIVE_DOLLAR:
											CashBank.addMoney(BigDecimal.valueOf(5.00));
											ledger.printsToLog("FEED MONEY: " + "$" + start + " $" + CashBank.getMoneyProvided());
											break;
										case TEN_DOLLAR:
											CashBank.addMoney(BigDecimal.valueOf(10.00));
											ledger.printsToLog("FEED MONEY: " + "$" + start + " $" + CashBank.getMoneyProvided());
											break;
										case I_HAVE_NO_MONEY:
											break label1;
									}
									System.out.println(" ");


								}
								break;
							case PURCHASE_MENU_SELECT_PRODUCT: {
								inventory.displayContents();
								System.out.println("Please select item. (Example: select A1 for Potato Crisps) ");
								String input = userInput.nextLine().trim().toUpperCase(Locale.ROOT);
								String start = CashBank.getMoneyProvided().toString();
								if (Inventory.getProductInfoBySlotMap().containsKey(input) &&
										Inventory.isInStock(input)) {
									BigDecimal cost = BigDecimal.valueOf(Double.parseDouble(Inventory.getProductCost(input, PRICE)));
									cashBank.setCost(cost);
									if (Double.parseDouble(CashBank.getMoneyProvided().toString()) >= cashBank.getCost().doubleValue()) {
										Inventory.decrementStock(input);
										System.out.println(Inventory.getProductDetail(input, PRODUCT_NAME) + " $" + cashBank.getCost() + " $" + cashBank.getReturnAmount());
										System.out.println(inventory.getSound(Inventory.getProductDetail(input, PRODUCT_TYPE)));
										ledger.printsToLog(Inventory.getProductDetail(input, PRODUCT_NAME) + " " + input + " $" + start + " $" + CashBank.getMoneyProvided());
										ledger.updateSalesReport(Inventory.getProductDetail(input, PRODUCT_NAME));
									} else {
										System.out.println("Please add more money before attempting to purchase this product. \n");
									}
								} else
									System.out.println("This item is OUT OF STOCK. Please try again.");

								break;
							}
							case PURCHASE_MENU_FINISH_TRANSACTION: {
								String start = CashBank.getMoneyProvided().toString();
								CashBank.makeChange();
								ledger.printsToLog("GIVE CHANGE: $" + start + " $" + CashBank.getMoneyProvided());
								break label; // returns to first menu
							}
						}
					}
					break;
				case MAIN_MENU_OPTION_EXIT:
					System.out.println("Thank you, come again!");
					System.exit(0);
				case "4":
					ledger.printSalesReport();
					break;
			}
		}
	}

	public static void main(String[] args) {
		Menu menu = new Menu(System.in, System.out);
		VendingMachineCLI cli = new VendingMachineCLI(menu);
		cli.run();
	}
}
