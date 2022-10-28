import java.io.*;
import java.util.Arrays;
import java.util.Scanner;

public class Basket {

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

    public void saveTxt(File textFile) throws IOException{
        FileWriter write = new FileWriter(textFile);
        for (String name : nameProduct) {
            write.write(name + " ");
        }
        write.write("\n");
        for (int price : priceProduct) {
            write.write(price + " ");
        }
        write.write("\n");
        for (int count : countProduct) {
            write.write(count + " ");
        }
        write.flush();
        write.close();
    }

    static Basket loadFromTxtFile(File textFile) throws IOException {
        FileReader reader = new FileReader(textFile);
        Scanner scanner = new Scanner(reader);
        String[] nameProduct = scanner.nextLine().split(" ");
        int[] priceProduct = Arrays.stream(
                        scanner.nextLine().split(" "))
                .mapToInt(value -> Integer.parseInt(value))
                .toArray();
        int[] count = Arrays.stream(
                        scanner.nextLine().split(" "))
                .mapToInt(value -> Integer.parseInt(value))
                .toArray();

        return new Basket(nameProduct, priceProduct, count);
    }
}

