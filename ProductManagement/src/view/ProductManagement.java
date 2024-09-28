/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package view;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import controllers.*;
import controllers.Menu;
import dto.*;
import utils.Utils;

/**
 *
 * @author Minh Châu
 */

public class ProductManagement {
    
    public static ArrayList<Brand> brandList = new ArrayList<>();
    public static ArrayList<Category> categoryList = new ArrayList<>();

    public static Menu initializeMenu() {
        //tao danh sach menu
        Menu menu = new Menu();
        menu.addItem("1. Add new product");
        menu.addItem("2. Search product(s)");
        menu.addItem("3. Update a product");
        menu.addItem("4. Delete a product");
        menu.addItem("5. Save products to file");
        menu.addItem("6. Print list products from the file.");
        menu.addItem("Others: Exit");

        return menu;
    }

    private static void AddProduct(ProductList list, ArrayList<Brand> brandList, ArrayList<Category> categoryList, Menu menu) {
        boolean functioning = true;
        while (functioning) {
            list.add(brandList, categoryList);
            boolean confirm = menu.confirmYesNo("Do you want to add another product? (Y/N)");
            if (!confirm) {
                functioning = false;
            }
        }
    }

    private static void FindProduct(ProductList list, Menu menu) {
        boolean functioning = true;
        while (functioning) {
            list.find();
            boolean confirm = menu.confirmYesNo("Do you want to go back to the Main Menu? (Y/N)");
            if (confirm) {
                functioning = false;
            }
        }
    }

    private static void UpdateProduct(ProductList list, ArrayList<Brand> brandList, ArrayList<Category> categoryList, Menu menu) {
        boolean functioning = true;
        while (functioning) {
            list.update(brandList, categoryList);
            boolean confirm = menu.confirmYesNo("Do you want to update another product? (Y/N)");
            if (!confirm) {
                functioning = false;
            }
        }
    }

    private static void RemoveProduct(ProductList list, Menu menu) throws IOException {
        boolean functioning = true;
        while (functioning) {
            System.out.println("------Before Remove-----");
            Utils.showList(list);
            System.out.println("------------");
            list.remove();
            System.out.println("------After Remove------");
            Utils.showList(list);
            boolean confirm = menu.confirmYesNo("Do you want to remove another product? (Y/N)");
            if (!confirm) {
                functioning = false;
            }
        }
    }

    private static void SaveToFile(ProductList list, ArrayList<Brand> brandList, ArrayList<Category> categoryList, Menu menu) throws IOException {
        while (true) {
            boolean saveSuccess = Utils.saveToFile(list) && Utils.saveToFileBrand(brandList) && Utils.saveToFileCategory(categoryList);

            if (saveSuccess) {
                System.out.println("List files saved successfully.");
            } else {
                System.out.println("List files saved unsuccessfully.");
            }

            boolean confirm = menu.confirmYesNo("Do you want to go back to the Main Menu? (Y/N)");

            if (confirm) {
                break;  
            }
        }
    }

    private static void Output(ProductList list, Menu menu) {
        boolean functioning = true;
        while (functioning) {
            list.output();
            boolean confirm = menu.confirmYesNo("Do you want to go back to the Main Menu? (Y/N)");
            if (confirm) {
                functioning = false;
            }
        }
    }
    
    private static void loadFromFile(ProductList list, ArrayList<Brand> brandList, ArrayList<Category> categoryList) 
            throws IOException, FileNotFoundException, ClassNotFoundException{
        Utils.readFromFileBrand(brandList); 
        Utils.readFromFileCategory(categoryList);
        if(Utils.readFromFile(list, brandList, categoryList)){
            System.out.println("List files loaded succesfully");
        }else{
            System.out.println("List files loaded unsuccesfully");
        }
    }

    public static void main(String args[]) throws IOException, FileNotFoundException, ClassNotFoundException {
        ProductList list = new ProductList();
        
        loadFromFile(list, brandList, categoryList);
        Menu menu = initializeMenu();
                
        int choice;
        boolean running = false;
        do {
            System.out.println("______Main Menu_____");
            menu.showMenu();
            choice = menu.getChoice();
            switch (choice) {
                case 1:
                    AddProduct(list, brandList, categoryList, menu);
                    break;
                case 2:
                    FindProduct(list, menu);
                    break;
                case 3:
                    UpdateProduct(list, brandList, categoryList, menu);
                    break;
                case 4:
                    RemoveProduct(list, menu);
                    break;
                case 5:
                    SaveToFile(list, brandList, categoryList, menu);
                    break;
                case 6:
                    Output(list, menu);
                    break;
                default:
                    if(menu.confirmYesNo("Do you want to quit? (Y/N)")){
                        if (menu.confirmYesNo("Do you want to Save All the changes before leaving? (Y/N)")) {
                            Utils.saveToFile(list);
                        }
                        running = true;
                    }
                    break;
            }
        } while (!running);
    }
}
