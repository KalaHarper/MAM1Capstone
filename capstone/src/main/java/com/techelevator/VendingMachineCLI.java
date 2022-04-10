package com.techelevator;

import com.techelevator.view.Menu;

public class VendingMachineCLI {

	private static final String MAIN_MENU_OPTION_DISPLAY_ITEMS = "Display Vending Machine Items";
	private static final String MAIN_MENU_OPTION_PURCHASE = "Purchase";
	private static final String MAIN_MENU_OPTION_EXIT = "Exit";
	private static final String[] MAIN_MENU_OPTIONS = { MAIN_MENU_OPTION_DISPLAY_ITEMS, MAIN_MENU_OPTION_PURCHASE, MAIN_MENU_OPTION_EXIT };
	private static final String PURCHASE_MENU_FEED_MONEY = "Feed Money";
	private static final String PURCHASE_MENU_SELECT_PRODUCT = "Purchase Product";
	private static final String PURCHASE_MENU_FINISH_TRANSACTION = "Finish Transaction";
	private static final String[] PURCHASE_MENU_OPTIONS = {PURCHASE_MENU_FEED_MONEY, PURCHASE_MENU_SELECT_PRODUCT, PURCHASE_MENU_FINISH_TRANSACTION};

	private Menu menu;

	public VendingMachineCLI(Menu menu) {
		this.menu = menu;
	}

	public void run() {
		CashBank cashBank = new CashBank();
		Inventory inventory = new Inventory();
		inventory.loadInventory();
		while (true) {
			String choice = (String) menu.getChoiceFromOptions(MAIN_MENU_OPTIONS);

			if (choice.equals(MAIN_MENU_OPTION_DISPLAY_ITEMS)) {
				// display vending machine items
				for (String[] item : inventory.getInventoryList()){
					for(String detail : item){
						System.out.print(detail+" ");
					}
					System.out.println();
				}
			} else if (choice.equals(MAIN_MENU_OPTION_PURCHASE)) {
				// do purchase
				while(true){
					String purchasingDecision = (String) menu.getChoiceFromOptions(PURCHASE_MENU_OPTIONS);

					if (purchasingDecision.equals(PURCHASE_MENU_FEED_MONEY)){
						
					}else if (purchasingDecision.equals(PURCHASE_MENU_SELECT_PRODUCT)){

					}else if (purchasingDecision.equals(PURCHASE_MENU_FINISH_TRANSACTION)){

					}
				}
			} else if (choice.equals(MAIN_MENU_OPTION_EXIT)){
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
}
