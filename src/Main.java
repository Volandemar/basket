import com.sun.source.tree.WhileLoopTree;

import java.io.*;
import java.nio.charset.StandardCharsets;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) throws IOException {
        File basketFile = new File("basket.txt");
        try {
            if (basketFile.createNewFile()){
                System.out.println("Корзина пуста!");
            }
            else{
                InputStreamReader in = new InputStreamReader(new FileInputStream(basketFile), StandardCharsets.UTF_8);
                while (in.ready()){
                    System.out.println((char) in.read());
                }
                in.close();
            }

        }catch (IOException e){
            System.out.println(e.getMessage());
        }
        Scanner scanner = new Scanner(System.in);
        System.out.println("Введите название продукта и его сумму");
        OutputStreamWriter out = new OutputStreamWriter(new FileOutputStream(basketFile));
        String enterUser = scanner.nextLine();
        out.write(enterUser);
        out.close();
    }
}