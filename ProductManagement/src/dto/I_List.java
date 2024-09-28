package dto;

import java.util.ArrayList;

public interface I_List {
  // Find the position of element which has code equal parameter coe
  void  find();
  // add new element( input from scanner) to I_List
  void add(ArrayList<Brand> brandList, ArrayList<Category> categoryList); 
  // Input the code wanna remove
  void remove();
  // input the code want to update, after that update other information--> use set method
  void update(ArrayList<Brand> brandList, ArrayList<Category> categoryList);
  // sort list use Collections.sort(this, new Comparator<Clock>()..., sort based price or make
  ArrayList<?> sort();
  // show detail of each element of List
  void output();
}
