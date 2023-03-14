package com.kuliah;

import java.util.InputMismatchException;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {

        Scanner in = new Scanner(System.in);
        int maxSize, menuChoice;
        maxSize = getNumProducts(in);
        if(maxSize==0) {

            System.out.println("Tidak diperlukan produk!");
        }else {
            Product[] products = new Product[maxSize];
            addToInventory(products, in);
            do {
                menuChoice = getMenuOption(in);
                executeMenuChoice(menuChoice, products, in);
            }while(menuChoice!=0);
        }
    }
    static void executeMenuChoice(int choice,Product[] products, Scanner in){
        switch (choice){
            case 1:System.out.println("Lihat Daftar Produk");
                displayInventory(products);
                break;
            case 2: System.out.println("Tambah Persediaan");
                addInventory (products, in);
                break;
            case 3 : System.out.println("Kurangi Stock");
                deductInventory(products,in);
                break;
            case 4 : System.out.println("Hentikan Stock");
                discontinueInventory(products,in);
                break;
        }
    }
    static void discontinueInventory(Product[] products, Scanner in){
        int productChoice;
        productChoice=getProductNumber(products, in);
        products[productChoice].setActive(false);
    }

    static void deductInventory(Product[] products, Scanner in) {
        int productChoice;
        int updateValue=1;
        productChoice=getProductNumber(products, in);
        do {
            try {
                System.out.print("Berapa Banyak Produk yang ingin Anda kurangi?");
                updateValue = in.nextInt();
                if (updateValue < 0)
                    System.out.println("Hanya masukan nilai positif untuk mengurangi persediaan");

                if (updateValue > products[productChoice].getQtyInStock())
                    System.out.println("Tidak cukup persediaan untuk menghapus jumlah itu, hanya" + products[productChoice].getQtyInStock() + "tersisa!");
            } catch (InputMismatchException e) {
                System.out.println("Tipe Data Salah dimasukan!");
                in.nextLine();
            } catch (Exception e) {
                System.out.println(e);
                in.nextLine();
            }
        } while (updateValue < 0 ||updateValue > products[productChoice].getQtyInStock());
        products[productChoice].deductFromInventory(updateValue);
    }
    static void addInventory(Product[]products, Scanner in){
        int productChoice;
        int updateValue=-1;
        productChoice=getProductNumber(products,in);
        do {
            try {
                System.out.print("Berapa banyak produk yang ingin Anda Tambahkan?");
                updateValue = in.nextInt();
                if (updateValue < 0)
                    System.out.println("Hanya masukkan nilai positif untuk menambah persediaan");
            } catch (InputMismatchException e) {
                System.out.println("Tipe data salah dimasukan!");
                in.nextLine();
            } catch (Exception e) {
                System.out.println(e);
                in.nextLine();
            }
        }while (updateValue<0);
        products[productChoice].addToInventory(updateValue);
    }
    static int getProductNumber(Product[] products, Scanner in){
        int productChoice=-1;

        for (int i = 0; i < products.length; i++)
            System.out.println(i + ":" + products[i].getName());

        do{
            try{
                System.out.print("Masukan nomor item produk yang ingin anda perbarui:");
                productChoice=in.nextInt();
                if (productChoice <0 ||productChoice > products.length-1)
                    System.out.println(("Hanya masukan nilai antara 0 dan"+(products.length-1)));

            }catch (InputMismatchException e) {
                System.out.println("Tipe Data salah dimasukan!");
                in.nextLine();
            }catch (Exception e){
                System.out.println(e);
                in.nextLine();
            }
        }while (productChoice<0||productChoice>products.length-1);
        return productChoice;
    }
    static int getMenuOption(Scanner in){
        int menuOption=-1;

        do{
            try {
                System.out.println("\n\n1.Lihat Inventaris\n2. Tambah Persediaan\n3. Kurangi Persediaan\n" + "4.Hentikan Produk\n0. Keluar");
                System.out.print("Masukan opsi menu:");
                menuOption = in.nextInt();
            }catch (InputMismatchException e) {
                System.out.println("Tipe data salah dimasukan!");
                in.nextLine();
            }catch (Exception e){
                System.out.println(e);
                in.nextLine();
            }
        }while (menuOption<0|| menuOption>4);

        return menuOption;
    }
    static int getNumProducts(Scanner in){
        int maxSize=-1;

        do {
            try {

                System.out.println("Masukan jumlah produk yang ingin anda tambahkan.");
                System.out.print("Masukan 0(nol) jika anda tidak ingin menambahkan produk:");


                maxSize = in.nextInt();
                if(maxSize<0)
                    System.out.println("Nilai salah dimasukan");
            }
            catch (InputMismatchException e) {
                System.out.println("Tipe data salah dimasukan!");
                in.nextLine();
            }catch (Exception e) {
                System.out.println(e);
                in.nextLine();
            }
        }while (maxSize<0);

        return maxSize;
    }
    static void addToInventory(Product[] products, Scanner in){
        int tempNumber;
        String tempName;
        int tempQty;
        double tempPrice;
        for(int i=0;i<products.length;i++){
            in.nextLine();

            System.out.print("\n\nMasukan nama produk:");
            tempName=in.nextLine();
            System.out.print("Masukan jumlah persediaan untuk produk ini:");
            tempQty=in.nextInt();
            System.out.print("Masukan harga untuk produk ini");
            tempPrice=in.nextDouble();
            System.out.print("Masukan nomor item:");
            tempNumber=in.nextInt();

            products[i]=new Product(tempNumber,tempName,tempQty,tempPrice);
        }
    }
    static void displayInventory(Product[]products){

        for (Product product: products){
            System.out.println(product);
        }
    }
}