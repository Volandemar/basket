import java.io.*;
import java.util.Arrays;
import java.util.Scanner;
import java.util.prefs.BackingStoreException;

public class Basket implements Serializable{

    private String[] nameProduct;
    private int[] priceProduct;
    private int[] countProduct;

    public Basket(String[] nameProduct, int[] priceProduct, int[] countProduct) {
        this.nameProduct = nameProduct;
        this.priceProduct = priceProduct;
        this.countProduct = countProduct;
    }

    public Basket(String[] productsList, int[] productsPrices) {
        this.priceProduct = productsPrices;
        this.nameProduct = productsList;
        this.countProduct = new int[nameProduct.length];
    }


    public void addToCart(int productNum, int count) {
        countProduct[productNum] += count;
    }

    public void printCart() {
        for (int i = 0; i < nameProduct.length; i++) {
            if (nameProduct[i].length() != 0) {
                System.out.println(nameProduct[i] + " по цене: " + priceProduct[i] + "руб.");
            }
        }
    }
    public void  saveBin (File binFile) throws IOException{
        ObjectOutputStream save = new ObjectOutputStream(new FileOutputStream(binFile));
        for (String name : nameProduct) {
            save.writeObject(this);
        }
    }

    static Basket loadFromBinFile(File binFile) throws IOException, ClassNotFoundException{
        ObjectInputStream load = new ObjectInputStream(new FileInputStream(binFile));
        Basket basket = (Basket) load.readObject();
        return basket;
        }
}

