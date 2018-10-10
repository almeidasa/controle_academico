package JogoDaVelha;

import static JogoDaVelha.Jogada.Tabela;
/**
 * @Autor: Winder Rezende <windergt@gmail.com>
 * @Data: 23/09/2017
 */
public class Vencedor {
    static int PlacarX, PlacarO, QuemVenceu=0, Empates=0;

    public static void VerificarVencedor(){
        int c=0, d=0;
        for (int i = 0; i < 3; i++) {
            if (Tabela[1+c] == 1 && Tabela[2+c] == 1 && Tabela[3+c] == 1) {
                Jogada.MsgFinal = "((<<<<<<<Fim de Jogo>>>>>>>))";
                Jogada.InfoJogada = "X Venceu!";
                PlacarX++;
                Jogada.Jogador=1;

                switch (c) {
                    case 0:
                        QuemVenceu=1;
                        break;
                    case 3:
                        QuemVenceu=2;
                        break;
                    case 6:
                        QuemVenceu=3;
                        break;
                    default:
                        break;
                }
            }
            else if (Tabela[1+c] == 2 && Tabela[2+c] == 2 && Tabela[3+c] == 2) {
                Jogada.MsgFinal = "((<<<<<<<Fim de Jogo>>>>>>>))";
                Jogada.InfoJogada = "O Venceu!";
                PlacarO++;
                Jogada.Jogador=2;

                switch (c) {
                    case 0:
                        QuemVenceu=1;
                        break;
                    case 3:
                        QuemVenceu=2;
                        break;
                    case 6:
                        QuemVenceu=3;
                        break;
                    default:
                        break;
                }
            }

            else if (Tabela[1+i] == 1 && Tabela[4+i] == 1 && Tabela[7+i] == 1) {
                Jogada.MsgFinal = "((<<<<<<<Fim de Jogo>>>>>>>))";
                Jogada.InfoJogada = "X Venceu!";
                PlacarX++;
                Jogada.Jogador=1;

                if(Tabela[1] == 1 && Tabela[4] == 1 && Tabela[7] == 1){
                    QuemVenceu=4;
                }
                else if(Tabela[1+1] == 1 && Tabela[4+1] == 1 && Tabela[7+1] == 1){
                    QuemVenceu=5;
                }
                else if(Tabela[1+2] == 1 && Tabela[4+2] == 1 && Tabela[7+2] == 1){
                    QuemVenceu=6;
                }
            }
            else if (Tabela[1+i] == 2 && Tabela[4+i] == 2 && Tabela[7+i] == 2) {
                Jogada.MsgFinal = "((<<<<<<<Fim de Jogo>>>>>>>))";
                Jogada.InfoJogada = "O Venceu!";
                PlacarO++;
                Jogada.Jogador=2;

                if(Tabela[1] == 2 && Tabela[4] == 2 && Tabela[7] == 2){
                    QuemVenceu=4;
                }
                else if(Tabela[1+1] == 2 && Tabela[4+1] == 2 && Tabela[7+1] == 2){
                    QuemVenceu=5;
                }
                else if(Tabela[1+2] == 2 && Tabela[4+2] == 2 && Tabela[7+2] == 2){
                    QuemVenceu=6;
                }
            }
            c=c+3;
        }

        if (Tabela[1] == 1 && Tabela[5] == 1 && Tabela[9] == 1) {
            Jogada.MsgFinal = "((<<<<<<<Fim de Jogo>>>>>>>))";
            Jogada.InfoJogada = "X Venceu!";
            PlacarX++;
            QuemVenceu=7;
            Jogada.Jogador=1;
        }
        else if (Tabela[1] == 2 && Tabela[5] == 2 && Tabela[9] == 2) {
            Jogada.MsgFinal = "((<<<<<<<Fim de Jogo>>>>>>>))";
            Jogada.InfoJogada = "O Venceu!";
            PlacarO++;
            QuemVenceu=7;
            Jogada.Jogador=2;
        }
        else if (Tabela[3] == 1 && Tabela[5] == 1 && Tabela[7] == 1) {
            Jogada.MsgFinal = "((<<<<<<<Fim de Jogo>>>>>>>))";
            Jogada.InfoJogada = "X Venceu!";
            PlacarX++;
            QuemVenceu=8;
            Jogada.Jogador=1;
        }
        else if (Tabela[3] == 2 && Tabela[5] == 2 && Tabela[7] == 2) {
            Jogada.MsgFinal = "((<<<<<<<Fim de Jogo>>>>>>>))";
            Jogada.InfoJogada = "O Venceu!";
            PlacarO++;
            QuemVenceu=8;
            Jogada.Jogador=2;
        }

        if(QuemVenceu > 0){
            //MainActivity.IniciaTempo = false;
        }

        if(QuemVenceu==0){
            for (int i = 1; i < 10; i++) {
                if (Tabela[i]>0) {
                    d++;
                    if (d>8) {
                        Jogada.MsgFinal = "((<<<<<<<Fim de Jogo>>>>>>>))";
                        Jogada.InfoJogada = "Empate! XO";
                        Empates++;
                        //MainActivity.IniciaTempo = false;
                    }
                }
            }
        }
    }
}