import com.opencsv.CSVWriter;
import org.xml.sax.SAXException;

import javax.xml.parsers.ParserConfigurationException;
import javax.xml.xpath.XPathExpressionException;
import java.io.*;
import java.util.Objects;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) throws IOException, ParserConfigurationException, SAXException, XPathExpressionException {

        Setting setting = new Setting();
        setting.loadSetting();
        Basket basket = null;
        ClientLog clientLog = new ClientLog();
        Scanner scanner = new Scanner(System.in);

        if (setting.load.equals("true")) {
            if (setting.loadFileFormat.equals("json")) {
                basket = Basket.loadJSON(new File(setting.loadFileName));
            }
            if (setting.loadFileFormat.equals("txt")) {
                basket = Basket.loadFromTxtFile(new File(setting.loadFileName));
            }
        } else {
            String[] productsList = {"Хлеб", "Молоко", "Яблоко", "Дыня", "Кефир"};
            long[] productsPrices = {50, 65, 40, 100, 50};
            basket = new Basket(productsList, productsPrices);
        }

        basket.printCart();
        while (true) {
            System.out.println("Выберите и введине номер товара, а затем необходимое количество.\n End - завершение работы программы");
            String enterUser = scanner.nextLine();
            if ("end".equals(enterUser)) {
                if (setting.log.equals("true")) {
                    clientLog.exportAsCSV(new File(setting.logFileName));
                }
                break;
            }
            String[] selectUser = enterUser.split(" ");
            int numberProduct = (Integer.parseInt(selectUser[0]) - 1);
            int countProduct = Integer.parseInt(selectUser[1]);
            basket.addToCart(numberProduct, countProduct);

            if (setting.save.equals("true")) {
                if (setting.saveFileFormat.equals("json")) {
                    basket.saveJSON(new File(setting.saveFileName));
                }
                if (setting.saveFileFormat.equals("txt")) {
                    basket.saveTxt(new File(setting.saveFileName));
                }
            }
            basket.printCart();
            clientLog.log(numberProduct, countProduct);
        }


    }
}

