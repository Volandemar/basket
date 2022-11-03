import com.opencsv.CSVWriter;

import java.io.*;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) throws IOException {
        Basket basket = null;
        ClientLog clientLog = new ClientLog();
        File basketFile = new File("src/basket.json");
        File log = new File("src/log.csv");
        Scanner scanner = new Scanner(System.in);
        try (CSVWriter writer = new CSVWriter(new FileWriter(log))) {
            String[] values = "numberProduct,amount".split(",");
            writer.writeNext(values);
        }
        try {
            if (basketFile.createNewFile()) {
                String[] productsList = {"Хлеб", "Молоко", "Яблоко", "Дыня", "Кефир"};
                long[] productsPrices = {50, 65, 40, 100, 50};
                basket = new Basket(productsList, productsPrices);
            } else {
                basket = Basket.loadJSON(basketFile);
            }
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
        basket.printCart();
        while (true) {
            System.out.println("Выберите и введине номер товара, а затем необходимое количество.\n End - завершение работы программы");
            String enterUser = scanner.nextLine();
            if ("end".equals(enterUser)) {
                clientLog.exportAsCSV(log);
                break;
            }
            String[] selectUser = enterUser.split(" ");
            int numberProduct = (Integer.parseInt(selectUser[0]) - 1);
            int countProduct = Integer.parseInt(selectUser[1]);
            basket.addToCart(numberProduct, countProduct);
            basket.saveJSON(basketFile);
            basket.printCart();
            clientLog.log(numberProduct,countProduct);
        }
    }
}

