package controllers;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
/**
 *
 * @author USER
 */
import java.util.ArrayList;
import java.util.Scanner;
import dto.I_Menu;
import utils.Utils;

public class Menu extends ArrayList<String> implements I_Menu {
    
    Scanner sc = new Scanner(System.in);
    
    public Menu() {
        super();
    }

    @Override
    public void addItem(String s) {
        this.add(s);
    }

    @Override
    public void showMenu() {
        this.stream().forEach(product -> System.out.println(product));
    }

    @Override
    public boolean confirmYesNo(String message) {
        System.out.println(message);
        String answer = sc.nextLine();

        while (true) {
            if ("Yes".equalsIgnoreCase(answer) || "Y".equalsIgnoreCase(answer)) {
                return true;
            } else if ("No".equalsIgnoreCase(answer) || "N".equalsIgnoreCase(answer)) {
                return false;
            } 
            System.out.println("Please enter a valid answer (Yes/Y or No/N)");
            answer = sc.nextLine();
            
        }
    }

    @Override
    public int getChoice() {
        return Utils.getUserChoice("Choose: ", 1);
    }

}
