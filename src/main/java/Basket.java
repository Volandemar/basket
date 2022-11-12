
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import java.io.*;
import java.util.Arrays;
import java.util.Scanner;

public class Basket {

    private String[] nameProduct;
    private long[] priceProduct;
    private long[] countProduct;

    public Basket(String[] nameProduct, long[] priceProduct, long[] countProduct) {
        this.nameProduct = nameProduct;
        this.priceProduct = priceProduct;
        this.countProduct = countProduct;
    }

    public Basket(String[] productsList, long[] productsPrices) {
        this.priceProduct = productsPrices;
        this.nameProduct = productsList;
        this.countProduct = new long[nameProduct.length];
    }

    public Basket(String[] nameProduct, int[] priceProduct, int[] count) {
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

    public void saveJSON(File jsonFile) {
        try (Writer writer = new FileWriter(jsonFile)) {
            Gson gson = new GsonBuilder().create();
            gson.toJson(Basket.this, writer);
        } catch (IOException e) {
            e.getMessage();
        }
    }

    public static Basket loadJSON(File jsonFile) {
        try (Reader reader = new FileReader(jsonFile)) {
            Gson gson = new GsonBuilder().create();
            Basket basket = gson.fromJson(reader, Basket.class);
            return new Basket(basket.nameProduct, basket.priceProduct, basket.countProduct);
        } catch (IOException e) {
            e.getMessage();
        }
        return null;
    }

//    public void saveJSON(File jsonFile) {
//        JSONObject saveJSON = new JSONObject();
//        JSONArray arrayNameProduct = new JSONArray();
//        JSONArray arrayPrice = new JSONArray();
//        JSONArray arrayCountProduct = new JSONArray();
//
//        for (String name : nameProduct) {
//            arrayNameProduct.add(name);
//        }
//        for (long price : priceProduct) {
//            arrayPrice.add(price);
//        }
//        for (long count : countProduct) {
//            arrayCountProduct.add(count);
//        }
//        saveJSON.put("name", arrayNameProduct);
//        saveJSON.put("price", arrayPrice);
//        saveJSON.put("count", arrayCountProduct);
//        try (FileWriter file = new FileWriter(jsonFile)) {
//            file.write(saveJSON.toJSONString());
//            file.flush();
//        } catch (IOException e) {
//            e.getMessage();
//        }
//    }

//    public static Basket loadJSON(File jsonFile) {
//        JSONParser parser = new JSONParser();
//        try {
//            Object obj = parser.parse(new FileReader(jsonFile));
//            JSONObject loadJSON = (JSONObject) obj;
//            JSONArray arrayNameProduct = (JSONArray) loadJSON.get("name");
//            JSONArray arrayPrice = (JSONArray) loadJSON.get("price");
//            JSONArray arrayCountProduct = (JSONArray) loadJSON.get("count");
//            String[] nameProduct = new String[arrayNameProduct.size()];
//            for (int i = 0; i < arrayNameProduct.size(); i++) {
//                nameProduct[i] = (String) arrayNameProduct.get(i);
//            }
//
//            long[] priceProduct = new long[arrayPrice.size()];
//            for (int i = 0; i < arrayPrice.size(); i++) {
//                priceProduct[i] = (Long) arrayPrice.get(i);
//            }
//
//            long[] countProduct = new long[arrayCountProduct.size()];
//            for (int i = 0; i < arrayCountProduct.size(); i++) {
//                countProduct[i] = (Long) arrayCountProduct.get(i);
//            }
//            return new Basket(nameProduct, priceProduct, countProduct);
//
//        } catch (IOException | ParseException e) {
//            e.getMessage();
//        }
//        return null;
//    }

    public void saveTxt(File textFile) throws IOException {
        try (FileWriter write = new FileWriter(textFile)) {
            for (String name : nameProduct) {
                write.write(name + " ");
            }
            write.write("\n");
            for (long price : priceProduct) {
                write.write(price + " ");
            }
            write.write("\n");
            for (long count : countProduct) {
                write.write(count + " ");
            }
            write.flush();
        }
    }

    static Basket loadFromTxtFile(File textFile) throws IOException {
        try (FileReader reader = new FileReader(textFile);
             Scanner scanner = new Scanner(reader)) {
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
}

