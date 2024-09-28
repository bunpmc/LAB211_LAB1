package utils;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.time.Year;
import java.util.ArrayList;
import java.util.Scanner;
import controllers.ProductList;
import dto.Brand;
import dto.Category;
import dto.Product;

public class Utils {
    public static final String RED = "\u001B[31m";
    public static final String RESET = "\u001B[0m";
    static Scanner sc = new Scanner(System.in);
    public static int COUNTER = 0;

    public static int getUserChoice(String message, int min, int max) {
        int choice = 0;
        boolean validInput = false;
        while (!validInput) {
            try {
                System.out.print(message);
                choice = Integer.parseInt(sc.nextLine());

                if (choice >= min && choice <= max) {
                    validInput = true;
                }
            } catch (NumberFormatException e) {
                System.out.println(RED + "Please Enter a Number " + min + " | " + max + RESET);
            }
        }
        return choice;
    }
    
    public static int getUserChoice(String message, int min) {
        int choice = 0;
        boolean validInput = false;
        while (!validInput) {
            try {
                System.out.print(message);
                choice = Integer.parseInt(sc.nextLine());

                if (choice >= min) {
                    validInput = true;
                }
            } catch (NumberFormatException e) {
                System.out.println(RED + "Please Enter a Number from " + min + RESET);
            }
        }
        return choice;
    }

    public static boolean isIDForProduct(ArrayList<Product> list, String code) {
        if (list.isEmpty() || code.trim().isEmpty()) {
            return false;
        }

        return list.stream().
                anyMatch(product -> product.getID().equalsIgnoreCase(code));
    }

    public static String getIDForBrand(ArrayList<Brand> brandList, String message) {
        if (brandList.isEmpty()) {
            System.out.println(RED + "This list is empty" + RESET);
            return "List is Empty";
        }

        boolean flag = false;
        String id = getInput(message);
        while (!flag) {
            if (isIDForBrand(brandList, id)) {
                flag = true;
            }else{
                id = getInput(message);
            }
        }

        return id;
    }

    public static boolean isIDForBrand(ArrayList<Brand> list, String code) {
        if(!code.matches("[B,b]\\d{3}")){
            System.out.println(RED + "Please try to enter as the following format Bxxx (B001-B005)" + RESET);
            return false;
        }
        
        if(!list.stream()
                    .anyMatch(b -> b.getBrandID().equalsIgnoreCase(code))){
            System.err.println(RED + "Brand ID does not exit (B001-B005)" + RESET);
            return false;
        }
        
        return true;
    }
  

    public static String getIDForCategory(ArrayList<Category> categoryList ,String message) {
        if (categoryList.isEmpty()) {
            System.out.println(RED + "This list is empty" + RESET);
            return "List is Empty";
        }

        boolean flag = false;
        String id = getInput(message);
        while (!flag) {
            if (isIDForCategory(categoryList, id)) {
                flag = true;
            }else{
                id = getInput(message);
            }
        }

        return id;
    }

    public static boolean isIDForCategory(ArrayList<Category> list, String code) {      
        if(!code.matches("[C,c]\\d{3}")){
            System.out.println(RED + "Please try to enter as the following format Cxxx (C001-C005)" + RESET);
            return false;
        }
        
        if(!list.stream()
                .anyMatch(c -> c.getCategoryID().equalsIgnoreCase(code))){
            System.out.println(RED + "Brand ID does not exit (C001-C005)" + RESET);
            return false;
        }
        
        return true;
    }

    public static ArrayList<Product> getMatchNameForProduct(ArrayList<Product> list, String name) {
        ArrayList<Product> result = new ArrayList<>();
        list.stream().filter((product) -> (product.getName().toLowerCase().trim()
                .contains(name.trim().toLowerCase()))).forEachOrdered((product) -> {
            result.add(product);
        });
        return result;
    }

    public static String getMatchIDForBrand(ArrayList<Brand> list, String name) {
        for (Brand b : list) {
            if (b.getBrandName().equalsIgnoreCase(name)) {
                return b.getBrandID();
            }
        }
        return "Unknown";
    }

    public static String getMatchIDForCategory(ArrayList<Category> list, String name) {
        for (Category c : list) {
            if (c.getCategoryName().equalsIgnoreCase(name)) {
                return c.getCategoryID();
            }
        }
        return "Unknown";
    }

    public static int getIndexForBrand(ArrayList<Brand> list, String input) {
        for (int i = 0; i < list.size(); i++) {
            if (list.get(i).getBrandID().equalsIgnoreCase(input)) {
                return i;
            }
        }
        return -1;
    }

    public static int getIndexForCategory(ArrayList<Category> list, String input) {
        for (int i = 0; i < list.size(); i++) {
            if (list.get(i).getCategoryID().equalsIgnoreCase(input)) {
                return i;
            }
        }
        return -1;
    }

    public static int getIndexAtPosition(ArrayList<Product> list, String input, String type) {
        for (int i = 0; i < list.size(); i++) {
            switch (type) {
                case "product":
                    if (list.get(i).getID().equalsIgnoreCase(input)) {
                        return i;
                    }
                    break;
                case "brand":
                    if (list.get(i).getBrandID().equalsIgnoreCase(input)) {
                        return i;
                    }
                    break;
                case "category":
                    if (list.get(i).getCategoryID().equalsIgnoreCase(input)) {
                        return i;
                    }
                    break;
            }
        }
        return -1;
    }
    
    public static String getNextProductID(ArrayList<Product> productList) {
        int maxID = 0;

        for (Product product : productList) {
            String productID = product.getID().substring(1);
            int currentID = Integer.parseInt(productID);

            if (currentID > maxID) {
                maxID = currentID;
            }
        }

        return "P" + String.format("%03d", maxID + 1);
    }

    public static String getInputNameForProduct(String message){
        String input = "";
        boolean flag = false;
        do{
            System.out.print(message);
            input = sc.nextLine();
            if(!input.trim().isEmpty()){
                flag = true;
            }else{
                System.out.println(RED + "Do not leave this field empty\033[0m" + RESET);
            }
        }while(!flag);
            
        return input.replace(" ", "_").trim();
    }

    public static String getInput(String message) {
        String input = "";
        boolean flag = false;
        do{
            System.out.print(message);
            input = sc.nextLine();
            if(!input.trim().isEmpty()){
                flag = true;
            }else{
                System.out.println(RED + "Do not leave this field empty" + RESET);
            }
        }while(!flag);

        return input;
    }

    public static boolean isInput(String input) {
        return !input.trim().isEmpty();
    }

    public static int getInputForYear(String message) {
        int currentYear = Year.now().getValue();
        int year;
        boolean flag = false;
        do {

            System.out.print(message + ": ");
            year = Integer.parseInt(sc.nextLine());
            if (year >= 1900 && year <= currentYear) {
                flag = true;
            }else {
                System.out.println(RED + "Invalid for " + message + "Please enter again (1900 <= year <= " + currentYear + ")" + RESET);
            }

        } while (!flag);

        return year;
    }
    
    public static double getInputForDouble(String message) {
        boolean flag = false;
        double doubleNumber = 0.0;
        do {
            try {
                System.out.print(message + ": ");
                doubleNumber = Double.parseDouble(sc.nextLine());
                if (doubleNumber > 0) {
                    flag = true;
                } else {
                    System.out.println(RED + "Invalid for " + message + "Please enter again" + RESET);
                }

            } catch (NumberFormatException e) {
                System.out.println(RED + "Invalid for " + message + "Please enter a number" + RESET);
            }
        } while (!flag);
        return doubleNumber;
    }

    public static boolean confirmYesNo(String message) { 
        boolean flag = false;
        while (!flag) {
            System.out.println(message);
            String answer = sc.nextLine();
            if ("Yes".equalsIgnoreCase(answer)
                    || "Y".equalsIgnoreCase(answer)) {
                return true;
            } else if ("No".equalsIgnoreCase(answer)
                    || "N".equalsIgnoreCase(answer)) {
                return false;
            }else if(answer.trim().isEmpty()){
                System.out.println(RED + "Do not leave this field empty" + RESET);
            } else {
                System.out.println(RED + "Please Enter a valid answer" + RESET);
            }
        }
        return true;
    }
    
    public static void getUpdateName(Product product, String message){
        System.out.print(message);
        String newName = sc.nextLine();
        if(!newName.trim().isEmpty()){
            product.setName(newName.replace(" ", "_").trim());
        }
    }
    
    public static void getUpdateIDBrand(ArrayList<Brand> list, Product product, String message) {
        System.out.print(message);
        String newBrand = sc.nextLine();
        boolean flag = false;
        while (!flag) {
            if (!newBrand.trim().isEmpty()) {
                if (isIDForBrand(list, newBrand)) {
                    product.setBrandID(newBrand);
                    flag = true;
                } else {
                    System.out.print(message);
                    newBrand = sc.nextLine();
                }
            }else{
                flag = true;
            }
        }
    }
    
    public static void getUpdateIDCategory(ArrayList<Category> list, Product product, String message){
        System.out.print(message);
        String newCategory = sc.nextLine();
        boolean flag = false;
        while (!flag) {
            if (!newCategory.trim().isEmpty()) {
                if (isIDForCategory(list, newCategory)) {
                    product.setBrandID(newCategory);
                    flag = true;
                } else {
                    System.out.print(message);
                    newCategory = sc.nextLine();
                }
            }else{
                flag = true;
            }
        }
    }
    
    public static void getUpdateYear(Product product, String message){
        boolean flag = false;
        do {
            System.out.print(message + ":");
            String year = sc.nextLine();
            if (!year.trim().isEmpty()) {
                if (year.matches("^[0-9]{4}$")) {
                    int newYear = Integer.parseInt(year);
                    int currentYear = java.time.Year.now().getValue();
                    if (newYear >= 1900 && newYear <= currentYear) {
                        product.setModelYear(newYear);
                        flag = true;
                    }
                }else{
                    System.out.println(RED + "Invalid year please enter again (1900 <= year <= " + java.time.Year.now().getValue() + ")" + RESET);
                }
            } else {
                flag = true;
            }
        }while(!flag);
    }
    
    public static void getUpdatePrice(Product product, String message){
        boolean flag = false;
        do {
            System.out.print(message + ":");
            String doubleNum = sc.nextLine();
            if (!doubleNum.trim().isEmpty()) {
                try {
                    double doubleNumber = Double.parseDouble(doubleNum);
                    if (doubleNumber > 0) {
                        product.setListPrice(doubleNumber);
                        flag = true;
                    } else {
                        System.out.println(RED + "Invalid for " + message + " Please enter again" + RESET);
                    }
                } catch (NumberFormatException e) {
                    System.out.println(RED + "Invalid for " + message + " Please enter a number" + RESET);
                }
            } else {
                flag = true;
            }
        } while (!flag);
    }
    
    public static void showList(ProductList list) {
        list.stream().forEach(product -> System.out.println(product));
    }
    
    public static boolean readFromFile(ProductList list, ArrayList<Brand> brandList, ArrayList<Category> categoryList) {
        String fileName = "Product.txt";
        boolean flag = false;

        try (BufferedReader reader = new BufferedReader(new FileReader(fileName))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] part = line.split(", ");
                if (part.length >= 6) {
                    String id = part[0].trim();
                    String name = part[1].trim();
                    String brandID = part[2].trim();
                    String categoryID = part[3].trim();
                    int modelYear  = Integer.parseInt(part[4].trim());
                    double price = Double.parseDouble(part[5].trim().replace(",", "."));
                    list.add(new Product(id, name, brandID, categoryID, modelYear, price));
                    COUNTER ++;
                }
            }
            flag = true;
        }catch (IOException e){
            System.out.println(RED + "File not found!" + RESET);
        }
        return flag;
    }

    public static boolean saveToFile(ProductList list) {
        String fileName = "Product.txt";
        boolean flag = false;
        try {
            File file = new File(fileName);
            if (!file.exists()) {
                System.out.println("File created: " + file.getName());
                file.createNewFile();
            }

            try (BufferedWriter writer = new BufferedWriter(new FileWriter(file))) {
                for (Product product : list) {
                    writer.write(product.toSaveString());
                    writer.newLine();
                }
                flag = true;
            } catch (Exception e) {
                System.out.println(RED + "File not found!" + RESET);
            }
        } catch (IOException e) {
            System.out.println(RED + "File saved unsuccesfully!" + RESET);
        }
        return flag;
    }
    
    public static boolean readFromFileCategory(ArrayList<Category> categoryList) {
        String fileName = "Category.txt";
        boolean flag = false;

        try (BufferedReader reader = new BufferedReader(new FileReader(fileName))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] part = line.split(", ");
                if (part.length >= 2) {
                    String id = part[0].trim();
                    String name = part[1].trim();
                    categoryList.add(new Category(id, name));
                }
            }
            flag = true;
        }catch (IOException e){
            System.out.println(RED + "File not found!" + RESET);
        }
        return flag;
    }

    public static boolean saveToFileCategory(ArrayList<Category> categoryList) {
        String fileName = "Category.txt";
        boolean flag = false;
        try {
            File file = new File(fileName);
            if (!file.exists()) {
                System.out.println("File created: " + file.getName());
                file.createNewFile();
            }

            try (BufferedWriter writer = new BufferedWriter(new FileWriter(file))) {
                for (Category category : categoryList) {
                    writer.write(category.toString());
                    writer.newLine();
                }
                flag = true;
            } catch (Exception e) {
                System.out.println(RED + "File not found!" + RESET);
            }
        } catch (IOException e) {
            System.out.println(RED + "File saved unsuccesfully!" + RESET);
        }
        return flag;
    }
    
    public static boolean readFromFileBrand(ArrayList<Brand> brandList) {
        String fileName = "Brand.txt";
        boolean flag = false;

        try (BufferedReader reader = new BufferedReader(new FileReader(fileName))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] part = line.split(", ");
                if (part.length >= 2) {
                    String id = part[0].trim();
                    String name = part[1].trim();
                    brandList.add(new Brand(id, name));
                }
            }
            flag = true;
        }catch (IOException e){
            System.out.println(RED + "File not found!" + RESET);
        }
        return flag;
    }

    public static boolean saveToFileBrand(ArrayList<Brand> brandList) {
        String fileName = "Brand.txt";
        boolean flag = false;
        try {
            File file = new File(fileName);
            if (!file.exists()) {
                System.out.println("File created: " + file.getName());
                file.createNewFile();
            }

            try (BufferedWriter writer = new BufferedWriter(new FileWriter(file))) {
                for (Brand brand : brandList) {
                    writer.write(brand.toString());
                    writer.newLine();
                }
                flag = true;
            } catch (Exception e) {
                System.out.println(RED + "File not found!" + RESET);
            }
        } catch (IOException e) {
            System.out.println(RED + "File saved unsuccesfully!" + RESET);
        }
        return flag;
    }
    
}
