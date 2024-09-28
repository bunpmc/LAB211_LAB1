package controllers;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;
import dto.Brand;
import dto.Category;
import dto.I_List;
import dto.Product;
import utils.Utils;

public class ProductList extends ArrayList<Product> implements I_List {
    
    public static final String RED = "\u001B[31m";
    public static final String GREEN = "\u001B[32m";
    public static final String RESET = "\u001B[0m";

    public ProductList() {
        super();
    }
    
    @Override
    public void find() {
        String inputName = Utils.getInputNameForProduct("Enter a product name: ");
        ArrayList<Product> productList = Utils.getMatchNameForProduct(this, inputName);

        if (productList.isEmpty()) {
            System.out.println(RED + "Have no any Product" + RESET);
        } else {
            productList.stream().sorted((product1, product2) -> (product1.getModelYear() - product2.getModelYear()))
                    .forEach(product -> System.out.println(product));
        }
    }

    @Override
    public void add(ArrayList<Brand> brandList, ArrayList<Category> categoryList) {
        System.out.println(RED + "There are already " + Utils.COUNTER + " Products in Products List" + RESET);
        String id = Utils.getNextProductID(this);
        System.out.println("Product is id: " + id);
        String name = Utils.getInputNameForProduct("Enter a product name: ");
        String brandID = Utils.getIDForBrand(brandList, "Enter a Brand ID (Hint: Bxxx): ");
        String categoryID = Utils.getIDForCategory(categoryList, "Enter a Category ID (Hint: Cxxx): ");
        int modelYear = Utils.getInputForYear("Enter Model Year");
        double listPrice = Utils.getInputForDouble("Enter list Price");

        Product product = new Product(id, name, brandID, categoryID, modelYear, listPrice);
        if (!this.stream().anyMatch(p -> p.equals(product))) {
            this.add(product);
            System.out.println(GREEN + "Product added successfully" + RESET);
        } else {
            System.out.println(RED + "Product has already exist" + RESET);
        }
    }

    @Override
    public void remove() {
        String id = Utils.getInput("Enter a product ID: ").toUpperCase();
        if (Utils.isIDForProduct(this, id)) {
            boolean confirmYN = Utils.confirmYesNo("You are going to delete "
                    + "|" + this.get(Utils.getIndexAtPosition(this, id, "product")) + "|" + " (Y/N)");
            if (confirmYN) {
                this.remove(this.get(Utils.getIndexAtPosition(this, id, "product")));
                System.out.println(GREEN + "Product removed successfully" + RESET);
            } else {
                System.out.println(RED + "Product removed unsuccessfully" + RESET);
            }
        } else {
            System.out.println(RED + "Product does not exist" + RESET);
        }
    }
    
    @Override
    public void update(ArrayList<Brand> brandList, ArrayList<Category> categoryList){
        String id = Utils.getInput("Enter a product ID: ");
        if (Utils.isIDForProduct(this, id)) {
            int index = Utils.getIndexAtPosition(this, id, "product");
            System.out.println(this.get(index));
            Utils.getUpdateName(this.get(index), "Enter a product name (or Enter to stay unchange): ");
            Utils.getUpdateIDBrand(brandList, this.get(index), "Enter a Brand ID (Hint: Bxxx) (or Enter to stay unchange): ");
            Utils.getUpdateIDCategory(categoryList, this.get(index), "Enter a Category ID (Hint: Cxxx) (or Enter to stay unchange): ");
            Utils.getUpdateYear(this.get(index), "Enter Model Year (or Enter to stay unchange)");
            Utils.getUpdatePrice(this.get(index), "Enter list Price(or Enter to stay unchange)");
            System.out.println(GREEN + "All fields have been updated successfully" + RESET);
        }else{
            System.out.println(RED + "Product does not exist" + RESET);
        }
    }

    @Override
    public ArrayList<Product> sort(){
        List<Product> sortedList = this.stream()
                .sorted(Comparator.comparing(Product::getListPrice).reversed()
                        .thenComparing(Product::getName))
                .collect(Collectors.toList());

        return (ArrayList<Product>) sortedList;
    }

    @Override
    public void output() {
        sort().stream().forEach(p -> System.out.println(p));
    }     
}
