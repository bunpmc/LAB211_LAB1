package dto;

import java.io.Serializable;
import java.util.ArrayList;
import utils.Utils;
import view.ProductManagement;

public class Product implements Serializable {
    private String ID;
    private String name;
    private String brandID;
    private String categoryID;
    private int modelYear;
    private double listPrice;
    
    
    public Product(String ID, String name, String brandID, String categoryID, int modelYear, double listPrice) {
        this.ID = ID;
        this.name = name;
        this.brandID = brandID;
        this.categoryID = categoryID;
        this.modelYear = modelYear;
        this.listPrice = listPrice;
    }

    public String getID() {
        return ID;
    }

    public void setID(String ID) {
        this.ID = ID;
    }

    public String getName() {
        return name.toUpperCase();
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getBrandID() {
        return brandID;
    }

    public void setBrandID(String brandID) {
        this.brandID = brandID;
    }

    public String getCategoryID() {
        return categoryID;
    }

    public void setCategoryID(String categoryID) {
        this.categoryID = categoryID;
    }

    public int getModelYear() {
        return modelYear;
    }

    public void setModelYear(int modelYear) {
        this.modelYear = modelYear;
    }

    public double getListPrice() {
        return listPrice;
    }

    public void setListPrice(double listPrice) {
        this.listPrice = listPrice;
    }

    @Override
    public int hashCode() {
        int hash = 3;
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        
        if (obj == null || getClass() != obj.getClass()) {
            return false;
        }
        
        Product product = (Product) obj;
     
        return (this.name != null && this.name.equalsIgnoreCase(product.name)) &&
               (this.brandID != null && this.brandID.equalsIgnoreCase(product.getBrandID())) &&
               (this.categoryID != null && this.categoryID.equalsIgnoreCase(product.categoryID)) &&
                this.listPrice == product.listPrice &&
                this.modelYear == product.modelYear;
    }

    @Override
    public String toString() {
        ArrayList<Brand> brand = ProductManagement.brandList;
        ArrayList<Category> category = ProductManagement.categoryList;
              
        return ID.toUpperCase() + ", " + name.toUpperCase() + ", " 
                + brand.get(Utils.getIndexForBrand(brand, this.brandID)).getBrandName() + ", " 
                + category.get(Utils.getIndexForCategory(category, this.categoryID)).getCategoryName() + ", " 
                + modelYear + ", " + listPrice;
    }
    
    public String toSaveString(){
        return ID.toUpperCase() + ", " + name.toUpperCase() + ", " 
                + brandID.toUpperCase() + ", " 
                + categoryID.toUpperCase() + ", " 
                + modelYear + ", " + listPrice; 
    }
}
