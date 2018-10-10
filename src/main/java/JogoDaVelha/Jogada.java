package JogoDaVelha;

import java.util.Arrays;
/**
 * @Autor: Winder Rezende <windergt@gmail.com>
 * @Data: 23/09/2017
 */
public class Jogada {
    private static int Botao;
    static int[] Tabela = new int[10];
    static int EscolherJogador=1, Jogador=1, Rodada=0, IniciaTempo=0;
    static String MarcaJogador, MsgFinal, InfoJogada= "";

    public Jogada(){
    }

    public int getBotao() {
        return Botao;
    }

    public void setBotao(int Botao) {
        this.Botao = Botao;
    }

    public static String SelJogador(){
        System.out.println("Ecolher:"+EscolherJogador);
        String Selecionado = "";
        switch (EscolherJogador) {
            case 1:
            case 0:
                Selecionado = "Jogador X";
                Jogador = 1;
                EscolherJogador = 2;
                break;
            case 2:
                Selecionado = "Jogador O";
                Jogador = 2;
                EscolherJogador = 3;
                break;
            case 3:
                Selecionado = "Contra o Computador O";
                Jogador = 1;
                EscolherJogador = 1;
                break;
            default:
                break;
        }
        return Selecionado;
    }

    public static int JogadorSelecionado() {
        return EscolherJogador;
    }

    public static void Botoes(){
        if (IniciaTempo == 0){
            //MainActivity.IniciaTempo = true;
            IniciaTempo = 1;
        }

        if (Jogador == 1) {
            Jogador = 2;
            Computador.SequenJogHumano += Botao;
            switch (Botao) {
                case 1:
                    Tabela[1] = 1;
                    break;
                case 2:
                    Tabela[2] = 1;
                    break;
                case 3:
                    Tabela[3] = 1;
                    break;
                case 4:
                    Tabela[4] = 1;
                    break;
                case 5:
                    Tabela[5] = 1;
                    break;
                case 6:
                    Tabela[6] = 1;
                    break;
                case 7:
                    Tabela[7] = 1;
                    break;
                case 8:
                    Tabela[8] = 1;
                    break;
                case 9:
                    Tabela[9] = 1;
                    break;
                default:
                    break;
            }
            MarcaJogador = "X";
            InfoJogada = "Vez de O";
        }
        else if(Jogador == 2){
            Jogador = 1;
            switch (Botao) {
                case 1:
                    Tabela[1] = 2;
                    break;
                case 2:
                    Tabela[2] = 2;
                    break;
                case 3:
                    Tabela[3] = 2;
                    break;
                case 4:
                    Tabela[4] = 2;
                    break;
                case 5:
                    Tabela[5] = 2;
                    break;
                case 6:
                    Tabela[6] = 2;
                    break;
                case 7:
                    Tabela[7] = 2;
                    break;
                case 8:
                    Tabela[8] = 2;
                    break;
                case 9:
                    Tabela[9] = 2;
                    break;
                default:
                    break;
            }
            MarcaJogador = "O";
            InfoJogada = "Vez de X";
        }
        //System.out.println("Tabela: "+Arrays.toString(Tabela));
        System.out.println("Rodada++ :" + Rodada);
        Rodada++;
    }

    public static void Reiniciar(){
        if(EscolherJogador == 3){
            Jogador = 2;
        }
        else{
            Jogador = 1;
        }
        MarcaJogador = "";

        for (int i = 0; i < 10; i++) {
            Tabela[i]=0;
        }

        MsgFinal = "";
        InfoJogada = "";
        Vencedor.QuemVenceu = 0;
        Computador.SequenJogHumano = "";
        IniciaTempo = 0;

        if (Computador.Modo == 2) {
            Rodada = 8;
        }
        else{
            Rodada = 0;
        }
        
        System.out.println("Rodada :" + Rodada);
    }
}
