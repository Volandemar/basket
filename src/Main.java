import java.io.*;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) throws IOException {
        Basket basket = null;
        File basketFile = new File("src/basket.txt");
        Scanner scanner = new Scanner(System.in);
        try {
            if (basketFile.createNewFile()) {
                String[] productsList = {"Хлеб", "Молоко", "Яблоко", "Дыня", "Кефир"};
                int[] productsPrices = {50, 65, 40, 100, 50};
                basket = new Basket(productsList, productsPrices);
            } else {
                basket = Basket.loadFromTxtFile(basketFile);
            }
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
        basket.printCart();
        while (true) {
            System.out.println("Выберите и введине номер товара, а затем необходимое количество.\n End - завершение работы программы");
            String enterUser = scanner.nextLine();
            if ("end".equals(enterUser)) {
                break;
            }
            try {
                String[] selectUser = enterUser.split(" ");
                int numberProduct = (Integer.parseInt(selectUser[0]) - 1);
                int countProduct = Integer.parseInt(selectUser[1]);
                basket.addToCart(numberProduct, countProduct);
                basket.saveTxt(basketFile);
                basket.printCart();
            } catch (IOException e) {
                e.getMessage();
            }
        }
    }
}

