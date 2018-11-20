package Teste;

/**
 * @Autor Winder Rezende
 * @Data  19/11/2018
 */
public class StringNum {
    
    public static void main(String[] args) {
        System.out.println("1235541517".matches("[0-9]+"));
        System.out.println("12312a".matches("[0-9]+"));
        System.out.println("12312+".matches("[0-9]+"));
    }

}
