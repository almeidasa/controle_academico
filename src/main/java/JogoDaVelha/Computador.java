package JogoDaVelha;

import static JogoDaVelha.Jogada.Rodada;
import static JogoDaVelha.Jogada.Tabela;
import java.util.Arrays;
import java.util.Random;

/**
 * @Autor: Winder Rezende <windergt@gmail.com>
 * @Data: 23/09/2017
 */
public class Computador {

    static String SequenJogHumano = "", InfoModo = "Médio";
    static int Modo = 2;

    public static void ModoDeJogo() {
        if (Modo == 1) {
            InfoModo = "Fácil";
            Modo = 2;
            Rodada = 8;
        } else if (Modo == 2) {
            InfoModo = "Médio";
            Modo = 3;
            Rodada = 0;
        } else if (Modo == 3) {
            InfoModo = "Difícil";
            Modo = 1;
            Rodada = 0;
        }
        System.out.println("Rod: " + Rodada + " Modo: " + Modo);
    }

    public static int JogadaPC() {
        Random radom = new Random();
        int AutoJoga = 0;
        int[] Tabela2 = new int[10];

        for (int i = 1; i < 10; i++) { //Verifica os dados preenchidos no vetor Tabela e marca as posições usadas no vetor Tabela2
            if (Tabela[i] > 0) {
                Tabela2[i] = i;
            }
        }

        if (Rodada == 1) {
            for (int i = 0; i < 10; i++) {
                int Pos;
                if (Tabela[i] == 1) {
                    Pos = i;
                    if (Pos == 5) {
                        AutoJoga = 3;
                    } else {
                        AutoJoga = 5;
                    }
                }
            }
            //System.out.println("Jogada 1 Computador: " + AutoJoga);
        } else if (Rodada == 3) {
            String[] JogHumanoPredf = Dados.JogHumanoPreR3();
            int[] JogCompPredf = Dados.JogCompPreR3();

            for (int i = 0; i < 63; i++) {
                if (SequenJogHumano.equals(JogHumanoPredf[i])) {
                    AutoJoga = JogCompPredf[i];
                }
            }

            if (AutoJoga == 0) {
                Rodada = 8;
            }
            //System.out.println("Jogada 2 Computador : " + AutoJoga);
            //System.out.println("Sequência de Jogadas Humano: " + SequenJogHumano);
        } else if (Rodada == 5) {
            String[] JogHumanoPredf = Dados.JogHumanoPreR5();
            int[] JogCompPredf = Dados.JogCompPreR5();

            for (int i = 0; i < Dados.JogHumanoPreR5().length; i++) {
                if (SequenJogHumano.equals(JogHumanoPredf[i])) {
                    AutoJoga = JogCompPredf[i];
                }
            }

            if (AutoJoga == 0) {
                Rodada = 8;
            }
            //System.out.println("Jogada 3 Computador: " + AutoJoga);
            //System.out.println("Sequência de Jogadas Humano: " + SequenJogHumano);
        } else if (Rodada == 7 && Modo != 3) {
            String[] JogHumanoPredf = Dados.JogHumanoPreR7();
            int[] JogCompPredf = Dados.JogCompPreR7();

            for (int i = 0; i < Dados.JogHumanoPreR7().length; i++) {
                if (SequenJogHumano.equals(JogHumanoPredf[i])) {
                    AutoJoga = JogCompPredf[i];
                }
            }

            if (AutoJoga == 0) {
                Rodada = 8;
            }
            //System.out.println("Jogada 4 Computador: " + AutoJoga);
            //System.out.println("Sequência de Jogadas Humano: " + SequenJogHumano);
        } else if (Rodada == 7 && Modo == 3) {
            Rodada = 8;
        } else if (Rodada > 7) {
            while (AutoJoga == Tabela2[1] || AutoJoga == Tabela2[2] || AutoJoga == Tabela2[3] || AutoJoga == Tabela2[4] || AutoJoga == Tabela2[5] || AutoJoga == Tabela2[6] || AutoJoga == Tabela2[7] || AutoJoga == Tabela2[8] || AutoJoga == Tabela2[9]) {
                AutoJoga = radom.nextInt(9);
            } // Verifica as possições usadas e libera um número aleatório que não foi usado

            //System.out.println("Número Aleatório: " + AutoJoga); // Log número gerado
            //System.out.println("Tabela2:" + Arrays.toString(Tabela2)); //Log do Vetor
        }
        System.out.println("Rodada: " + Rodada);

        return AutoJoga;
    }
}
