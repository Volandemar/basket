public class Basket {
    private String[] nameProduct;
    private int[] priceProduct;
    private long[] countProduct;

    public Basket(String[] nameProduct, int[] priceProduct) {
        this.nameProduct = nameProduct;
        this.priceProduct = priceProduct;
    }

    private void addToCart(int productNum, int amount){
        countProduct[productNum-1]+=amount;
    }
    private void printCart(){
        for (int i = 0; i < nameProduct.length; i++){
            if (nameProduct[i].length() != 0){
                System.out.println(nameProduct[i] + " цена " + priceProduct[i]);
            }
        }
    }

}
