package com.techelevator;

import com.techelevator.view.Inventory;
import com.techelevator.view.Menu;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import static com.techelevator.view.Inventory.inventoryStocker;

public class VendingMachineCLI {

	private static final String MAIN_MENU_OPTION_DISPLAY_ITEMS = "Display Vending Machine Items";
	private static final String MAIN_MENU_OPTION_PURCHASE = "Purchase";
	private static final String MAIN_MENU_EXIT = "Exit";
	private static final String[] MAIN_MENU_OPTIONS = { MAIN_MENU_OPTION_DISPLAY_ITEMS, MAIN_MENU_OPTION_PURCHASE, MAIN_MENU_EXIT};


	private Menu menu;

	public VendingMachineCLI(Menu menu) {
		this.menu = menu;
	}

	public void run() {

//		System.out.println();
		inventoryStocker();
		while (true) {
			String choice = (String) menu.getChoiceFromOptions(MAIN_MENU_OPTIONS);

			if (choice.equals(MAIN_MENU_OPTION_DISPLAY_ITEMS)) {
				// display vending machine items

			} else if (choice.equals(MAIN_MENU_OPTION_PURCHASE)) {
				// do purchase
			} else if(choice.equals(MAIN_MENU_EXIT)){
				System.exit(0);
			}
		}
	}

//	public void CreateInventoryObjects(){
//
//		inventoryObjects.add(new Inventory("A1", "Potato Crisps", "Chip", 3.05));
//		inventoryObjects.add(new Inventory("A2", "Stackers", "Chip", 1.45));
//		inventoryObjects.add(new Inventory("A3", "Grain Waves", "Chip", 2.75));
//		inventoryObjects.add(new Inventory("A4", "Cloud Popcorn", "Chip", 3.65));
//
//		inventoryObjects.add(new Inventory("B1", "Moonpie", "Candy", 1.80));
//		inventoryObjects.add(new Inventory("B2", "Cowtales", "Candy", 1.50));
//		inventoryObjects.add(new Inventory("B3", "Wonka Bar", "Candy", 1.50));
//		inventoryObjects.add(new Inventory("B4", "Crunchie", "Candy", 1.75));
//
//		inventoryObjects.add(new Inventory("C1", "Cola", "Drink", 1.25));
//		inventoryObjects.add(new Inventory("C2", "Dr. Salt", "Drink", 1.50));
//		inventoryObjects.add(new Inventory("C3", "Mountain Melter", "Drink", 1.50));
//		inventoryObjects.add(new Inventory("C4", "Heavy", "Drink", 1.50));
//
//		inventoryObjects.add(new Inventory("D1", "U-Chews", "Gum", 0.85));
//		inventoryObjects.add(new Inventory("D2", "Little League Chew", "Gum", 0.95));
//		inventoryObjects.add(new Inventory("D3", "Chiclets", "Gum", 0.75));
//		inventoryObjects.add(new Inventory("D4", "Triplemint", "Gum", 0.75));
//	}

	public static void main(String[] args) {
		Menu menu = new Menu(System.in, System.out);
		VendingMachineCLI cli = new VendingMachineCLI(menu);
		cli.run();
	}
}
