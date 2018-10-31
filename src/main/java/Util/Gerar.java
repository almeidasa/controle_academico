package Util;

import java.util.Random;

/**
 * @Autor Winder Rezende
 * @Data  28/10/2018
 */
public class Gerar {
    
    public static String Senha(){
    String caracteres = "ABCDEFGHIJKLMNOPQRSTUVYWXZabcdefghijklmnopqrstuvywxz1234567890!@#$%";

        Random random = new Random();

        String armazenaChaves = "";
        int index = -1;
        for (int i = 0; i < 9; i++) {
            index = random.nextInt(caracteres.length());
            armazenaChaves += caracteres.substring(index, index + 1);
        }
        System.out.println(armazenaChaves);
        return armazenaChaves;
    }
}