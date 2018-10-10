package JogoDaVelha;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

/**
 * @Autor Winder Rezende
 * @Data 23/09/2018, 22:45:47
 */
@ManagedBean
@ViewScoped
public class JogoDaVelhaBean {

    private String tempo;
    private String botao1 = "";
    private String botao2 = "";
    private String botao3 = "";
    private String botao4 = "";
    private String botao5 = "";
    private String botao6 = "";
    private String botao7 = "";
    private String botao8 = "";
    private String botao9 = "";
    private String botaoModo = "Médio";
    private String botaoSelJogador = "Selecionar Jogador";
    private boolean iniciaTempo;
    private String campoInfoJogada = "-";
    private String campoMsgFinal = "";
    private String campoPlacarO = "0";
    private String campoPlacarX = "0";
    private String campoEmpates = "0";
    private boolean btn1;
    private boolean btn2;
    private boolean btn3;
    private boolean btn4;
    private boolean btn5;
    private boolean btn6;
    private boolean btn7;
    private boolean btn8;
    private boolean btn9;
    private boolean btnModo;
    Jogada botao = new Jogada();

    /**
     * Creates a new instance of JogoDaVelhaBean
     */
    public JogoDaVelhaBean() {
    }

    public void iniciaTempo() {
        iniciaTempo = true;
        setTempo();
    }

    public void paraTempo() {
        iniciaTempo = false;
    }

    public void setTempo() {

        new Thread() {

            @Override
            public void run() {
                int ms = 0;
                while (iniciaTempo) {

                    try {
                        //long centesimos = (ms / 100) % 60;
                        long segundos = (ms / 1000) % 60;
                        long minutos = (ms / 60000) % 60; // 60000   = 60 * 1000
                        long horas = ms / 3600000; // 3600000 = 60 * 60 * 1000
                        tempo = String.format("%02d:%02d:%02d", horas, minutos, segundos);
                        Thread.sleep(10);
                        ms = ms + 1000;
                    } catch (InterruptedException ex) {
                        System.out.println(ex);
                    }
                }
            }
        }.start();
    }

    public void actBotao1() {
        System.out.println("Btn1");
        if (botao1.equals("") && Vencedor.QuemVenceu == 0) {
            botao.setBotao(1);
            Jogada.Botoes();
            Vencedor.VerificarVencedor();
            botao1 = Jogada.MarcaJogador;
            campoInfoJogada = Jogada.InfoJogada;
            campoMsgFinal = Jogada.MsgFinal;
            campoPlacarX = Integer.toString(Vencedor.PlacarX);
            campoPlacarO = Integer.toString(Vencedor.PlacarO);
            campoEmpates = Integer.toString(Vencedor.Empates);
            if (Jogada.JogadorSelecionado() == 1) {
                JogadaComputador();
            }
            MarcarVencedor();
        }
    }

    public void actBotao2() {
        System.out.println("Btn2");
        if (botao2.equals("") && Vencedor.QuemVenceu == 0) {
            botao.setBotao(2);
            Jogada.Botoes();
            Vencedor.VerificarVencedor();
            botao2 = Jogada.MarcaJogador;
            campoInfoJogada = Jogada.InfoJogada;
            campoMsgFinal = Jogada.MsgFinal;
            campoPlacarX = Integer.toString(Vencedor.PlacarX);
            campoPlacarO = Integer.toString(Vencedor.PlacarO);
            campoEmpates = Integer.toString(Vencedor.Empates);
            if (Jogada.JogadorSelecionado() == 1) {
                JogadaComputador();
            }
            MarcarVencedor();
        }
    }

    public void actBotao3() {
        System.out.println("Btn3");
        if (botao3.equals("") && Vencedor.QuemVenceu == 0) {
            botao.setBotao(3);
            Jogada.Botoes();
            Vencedor.VerificarVencedor();
            botao3 = Jogada.MarcaJogador;
            campoInfoJogada = Jogada.InfoJogada;
            campoMsgFinal = Jogada.MsgFinal;
            campoPlacarX = Integer.toString(Vencedor.PlacarX);
            campoPlacarO = Integer.toString(Vencedor.PlacarO);
            campoEmpates = Integer.toString(Vencedor.Empates);
            if (Jogada.JogadorSelecionado() == 1) {
                JogadaComputador();
            }
            MarcarVencedor();
        }
    }

    public void actBotao4() {
        System.out.println("Btn4");
        if (botao4.equals("") && Vencedor.QuemVenceu == 0) {
            botao.setBotao(4);
            Jogada.Botoes();
            Vencedor.VerificarVencedor();
            botao4 = Jogada.MarcaJogador;
            campoInfoJogada = Jogada.InfoJogada;
            campoMsgFinal = Jogada.MsgFinal;
            campoPlacarX = Integer.toString(Vencedor.PlacarX);
            campoPlacarO = Integer.toString(Vencedor.PlacarO);
            campoEmpates = Integer.toString(Vencedor.Empates);
            if (Jogada.JogadorSelecionado() == 1) {
                JogadaComputador();
            }
            MarcarVencedor();
        }
    }

    public void actBotao5() {
        System.out.println("Btn5");
        if (botao5.equals("") && Vencedor.QuemVenceu == 0) {
            botao.setBotao(5);
            Jogada.Botoes();
            Vencedor.VerificarVencedor();
            botao5 = Jogada.MarcaJogador;
            campoInfoJogada = Jogada.InfoJogada;
            campoMsgFinal = Jogada.MsgFinal;
            campoPlacarX = Integer.toString(Vencedor.PlacarX);
            campoPlacarO = Integer.toString(Vencedor.PlacarO);
            campoEmpates = Integer.toString(Vencedor.Empates);
            if (Jogada.JogadorSelecionado() == 1) {
                JogadaComputador();
            }
            MarcarVencedor();
        }
    }

    public void actBotao6() {
        System.out.println("Btn6");
        if (botao6.equals("") && Vencedor.QuemVenceu == 0) {
            botao.setBotao(6);
            Jogada.Botoes();
            Vencedor.VerificarVencedor();
            botao6 = Jogada.MarcaJogador;
            campoInfoJogada = Jogada.InfoJogada;
            campoMsgFinal = Jogada.MsgFinal;
            campoPlacarX = Integer.toString(Vencedor.PlacarX);
            campoPlacarO = Integer.toString(Vencedor.PlacarO);
            campoEmpates = Integer.toString(Vencedor.Empates);
            if (Jogada.JogadorSelecionado() == 1) {
                JogadaComputador();
            }
            MarcarVencedor();
        }
    }

    public void actBotao7() {
        System.out.println("Btn7");
        if (botao7.equals("") && Vencedor.QuemVenceu == 0) {
            botao.setBotao(7);
            Jogada.Botoes();
            Vencedor.VerificarVencedor();
            botao7 = Jogada.MarcaJogador;
            campoInfoJogada = Jogada.InfoJogada;
            campoMsgFinal = Jogada.MsgFinal;
            campoPlacarX = Integer.toString(Vencedor.PlacarX);
            campoPlacarO = Integer.toString(Vencedor.PlacarO);
            campoEmpates = Integer.toString(Vencedor.Empates);
            if (Jogada.JogadorSelecionado() == 1) {
                JogadaComputador();
            }
            MarcarVencedor();
        }
    }

    public void actBotao8() {
        System.out.println("Btn8");
        if (botao8.equals("") && Vencedor.QuemVenceu == 0) {
            botao.setBotao(8);
            Jogada.Botoes();
            Vencedor.VerificarVencedor();
            botao8 = Jogada.MarcaJogador;
            campoInfoJogada = Jogada.InfoJogada;
            campoMsgFinal = Jogada.MsgFinal;
            campoPlacarX = Integer.toString(Vencedor.PlacarX);
            campoPlacarO = Integer.toString(Vencedor.PlacarO);
            campoEmpates = Integer.toString(Vencedor.Empates);
            if (Jogada.JogadorSelecionado() == 1) {
                JogadaComputador();
            }
            MarcarVencedor();
        }
    }

    public void actBotao9() {
        System.out.println("Btn9");
        if (botao9.equals("") && Vencedor.QuemVenceu == 0) {
            botao.setBotao(9);
            Jogada.Botoes();
            Vencedor.VerificarVencedor();
            botao9 = Jogada.MarcaJogador;
            campoInfoJogada = Jogada.InfoJogada;
            campoMsgFinal = Jogada.MsgFinal;
            campoPlacarX = Integer.toString(Vencedor.PlacarX);
            campoPlacarO = Integer.toString(Vencedor.PlacarO);
            campoEmpates = Integer.toString(Vencedor.Empates);
            if (Jogada.JogadorSelecionado() == 1) {
                JogadaComputador();
            }
            MarcarVencedor();
        }
    }

    public void actBotaoReiniciar() {
        botao1 = "";
        botao2 = "";
        botao3 = "";
        botao4 = "";
        botao5 = "";
        botao6 = "";
        botao7 = "";
        botao8 = "";
        botao9 = "";
        campoInfoJogada = "-";
        campoMsgFinal = "";
        btn1 = true;
        btn2 = true;
        btn3 = true;
        btn4 = true;
        btn5 = true;
        btn6 = true;
        btn7 = true;
        btn8 = true;
        btn9 = true;
        btnModo = true;
        Jogada.Reiniciar();
    }

    public void actBotaoSelJogador() {
        Jogada.SelJogador();
        botaoSelJogador = Jogada.SelJogador();
        actBotaoReiniciar();
        if (Jogada.JogadorSelecionado() != 1) {
            btnModo = false;
        } else {
            btnModo = true;
        }
    }

    public void actBotaoModo() {
        Computador.ModoDeJogo();
        botaoModo = Computador.InfoModo;
    }

    public void actBotaoZerarPlacar() {
        Vencedor.PlacarX = 0;
        Vencedor.PlacarO = 0;
        Vencedor.Empates = 0;
        campoPlacarX = "0";
        campoPlacarO = "0";
        campoEmpates = "0";
    }

    public void JogadaComputador() {
        //System.out.println("Computador:" + Computador.JogadaPC());
        if (Vencedor.QuemVenceu == 0) {
            switch (Computador.JogadaPC()) {
                case 1:
                    botao.setBotao(1);
                    Jogada.Botoes();
                    if (botao1.equals("")) {
                        botao1 = Jogada.MarcaJogador;
                    }
                    Vencedor.VerificarVencedor();
                    campoInfoJogada = Jogada.InfoJogada;
                    campoMsgFinal = Jogada.MsgFinal;
                    campoPlacarO = Integer.toString(Vencedor.PlacarO);
                    campoEmpates = Integer.toString(Vencedor.Empates);
                    break;
                case 2:
                    botao.setBotao(2);
                    Jogada.Botoes();
                    if (botao2.equals("")) {
                        botao2 = Jogada.MarcaJogador;
                    }
                    Vencedor.VerificarVencedor();
                    campoInfoJogada = Jogada.InfoJogada;
                    campoMsgFinal = Jogada.MsgFinal;
                    campoPlacarO = Integer.toString(Vencedor.PlacarO);
                    campoEmpates = Integer.toString(Vencedor.Empates);
                case 3:
                    botao.setBotao(3);
                    Jogada.Botoes();
                    if (botao3.equals("")) {
                        botao3 = Jogada.MarcaJogador;
                    }
                    Vencedor.VerificarVencedor();
                    campoInfoJogada = Jogada.InfoJogada;
                    campoMsgFinal = Jogada.MsgFinal;
                    campoPlacarO = Integer.toString(Vencedor.PlacarO);
                    campoEmpates = Integer.toString(Vencedor.Empates);
                    break;
                case 4:
                    botao.setBotao(4);
                    Jogada.Botoes();
                    if (botao4.equals("")) {
                        botao4 = Jogada.MarcaJogador;
                    }
                    Vencedor.VerificarVencedor();
                    campoInfoJogada = Jogada.InfoJogada;
                    campoMsgFinal = Jogada.MsgFinal;
                    campoPlacarO = Integer.toString(Vencedor.PlacarO);
                    campoEmpates = Integer.toString(Vencedor.Empates);
                    break;
                case 5:
                    botao.setBotao(5);
                    Jogada.Botoes();
                    if (botao5.equals("")) {
                        botao5 = Jogada.MarcaJogador;
                    }
                    Vencedor.VerificarVencedor();
                    campoInfoJogada = Jogada.InfoJogada;
                    campoMsgFinal = Jogada.MsgFinal;
                    campoPlacarO = Integer.toString(Vencedor.PlacarO);
                    campoEmpates = Integer.toString(Vencedor.Empates);
                    break;
                case 6:
                    botao.setBotao(6);
                    Jogada.Botoes();
                    if (botao6.equals("")) {
                        botao6 = Jogada.MarcaJogador;
                    }
                    Vencedor.VerificarVencedor();
                    campoInfoJogada = Jogada.InfoJogada;
                    campoMsgFinal = Jogada.MsgFinal;
                    campoPlacarO = Integer.toString(Vencedor.PlacarO);
                    campoEmpates = Integer.toString(Vencedor.Empates);
                    break;
                case 7:
                    botao.setBotao(7);
                    Jogada.Botoes();
                    if (botao7.equals("")) {
                        botao7 = Jogada.MarcaJogador;
                    }
                    Vencedor.VerificarVencedor();
                    campoInfoJogada = Jogada.InfoJogada;
                    campoMsgFinal = Jogada.MsgFinal;
                    campoPlacarO = Integer.toString(Vencedor.PlacarO);
                    campoEmpates = Integer.toString(Vencedor.Empates);
                    break;
                case 8:
                    botao.setBotao(8);
                    Jogada.Botoes();
                    if (botao8.equals("")) {
                        botao8 = Jogada.MarcaJogador;
                    }
                    Vencedor.VerificarVencedor();
                    campoInfoJogada = Jogada.InfoJogada;
                    campoMsgFinal = Jogada.MsgFinal;
                    campoPlacarO = Integer.toString(Vencedor.PlacarO);
                    campoEmpates = Integer.toString(Vencedor.Empates);
                    break;
                case 9:
                    botao.setBotao(9);
                    Jogada.Botoes();
                    if (botao9.equals("")) {
                        botao9 = Jogada.MarcaJogador;
                    }
                    Vencedor.VerificarVencedor();
                    campoInfoJogada = Jogada.InfoJogada;
                    campoMsgFinal = Jogada.MsgFinal;
                    campoPlacarO = Integer.toString(Vencedor.PlacarO);
                    campoEmpates = Integer.toString(Vencedor.Empates);
                    break;
                default:
                    break;
            }
        }
    }

    public void MarcarVencedor() {
        switch (Vencedor.QuemVenceu) {
            case 1:
                btn4 = false;
                btn5 = false;
                btn6 = false;
                btn7 = false;
                btn8 = false;
                btn9 = false;
                break;
            case 2:
                btn1 = false;
                btn2 = false;
                btn3 = false;
                btn7 = false;
                btn8 = false;
                btn9 = false;
                break;
            case 3:
                btn1 = false;
                btn2 = false;
                btn3 = false;
                btn4 = false;
                btn5 = false;
                btn6 = false;
                break;
            case 4:
                btn2 = false;
                btn3 = false;
                btn5 = false;
                btn6 = false;
                btn8 = false;
                btn9 = false;
                break;
            case 5:
                btn1 = false;
                btn3 = false;
                btn4 = false;
                btn6 = false;
                btn7 = false;
                btn9 = false;
                break;
            case 6:
                btn1 = false;
                btn2 = false;
                btn4 = false;
                btn5 = false;
                btn7 = false;
                btn8 = false;
                break;
            case 7:
                btn2 = false;
                btn3 = false;
                btn4 = false;
                btn6 = false;
                btn7 = false;
                btn8 = false;
                break;
            case 8:
                btn1 = false;
                btn2 = false;
                btn4 = false;
                btn6 = false;
                btn8 = false;
                btn9 = false;
                break;
            default:
                break;
        }
    }

    public String getTempo() {
        return tempo;
    }

    public void setTempo(String tempo) {
        this.tempo = tempo;
    }

    public String getBotao1() {
        return botao1;
    }

    public void setBotao1(String botao1) {
        this.botao1 = botao1;
    }

    public String getBotao2() {
        return botao2;
    }

    public void setBotao2(String botao2) {
        this.botao2 = botao2;
    }

    public String getBotao3() {
        return botao3;
    }

    public void setBotao3(String botao3) {
        this.botao3 = botao3;
    }

    public String getBotao4() {
        return botao4;
    }

    public void setBotao4(String botao4) {
        this.botao4 = botao4;
    }

    public String getBotao5() {
        return botao5;
    }

    public void setBotao5(String botao5) {
        this.botao5 = botao5;
    }

    public String getBotao6() {
        return botao6;
    }

    public void setBotao6(String botao6) {
        this.botao6 = botao6;
    }

    public String getBotao7() {
        return botao7;
    }

    public void setBotao7(String botao7) {
        this.botao7 = botao7;
    }

    public String getBotao8() {
        return botao8;
    }

    public void setBotao8(String botao8) {
        this.botao8 = botao8;
    }

    public String getBotao9() {
        return botao9;
    }

    public void setBotao9(String botao9) {
        this.botao9 = botao9;
    }

    public String getBotaoModo() {
        return botaoModo;
    }

    public void setBotaoModo(String botaoModo) {
        this.botaoModo = botaoModo;
    }

    public String getBotaoSelJogador() {
        return botaoSelJogador;
    }

    public void setBotaoSelJogador(String botaoSelJogador) {
        this.botaoSelJogador = botaoSelJogador;
    }

    public boolean isIniciaTempo() {
        return iniciaTempo;
    }

    public void setIniciaTempo(boolean iniciaTempo) {
        this.iniciaTempo = iniciaTempo;
    }

    public String getCampoInfoJogada() {
        return campoInfoJogada;
    }

    public void setCampoInfoJogada(String campoInfoJogada) {
        this.campoInfoJogada = campoInfoJogada;
    }

    public String getCampoMsgFinal() {
        return campoMsgFinal;
    }

    public void setCampoMsgFinal(String campoMsgFinal) {
        this.campoMsgFinal = campoMsgFinal;
    }

    public String getCampoPlacarO() {
        return campoPlacarO;
    }

    public void setCampoPlacarO(String campoPlacarO) {
        this.campoPlacarO = campoPlacarO;
    }

    public String getCampoPlacarX() {
        return campoPlacarX;
    }

    public void setCampoPlacarX(String campoPlacarX) {
        this.campoPlacarX = campoPlacarX;
    }

    public String getCampoEmpates() {
        return campoEmpates;
    }

    public void setCampoEmpates(String campoEmpates) {
        this.campoEmpates = campoEmpates;
    }

    public boolean isBtn1() {
        return btn1;
    }

    public void setBtn1(boolean btn1) {
        this.btn1 = btn1;
    }

    public boolean isBtn2() {
        return btn2;
    }

    public void setBtn2(boolean btn2) {
        this.btn2 = btn2;
    }

    public boolean isBtn3() {
        return btn3;
    }

    public void setBtn3(boolean btn3) {
        this.btn3 = btn3;
    }

    public boolean isBtn4() {
        return btn4;
    }

    public void setBtn4(boolean btn4) {
        this.btn4 = btn4;
    }

    public boolean isBtn5() {
        return btn5;
    }

    public void setBtn5(boolean btn5) {
        this.btn5 = btn5;
    }

    public boolean isBtn6() {
        return btn6;
    }

    public void setBtn6(boolean btn6) {
        this.btn6 = btn6;
    }

    public boolean isBtn7() {
        return btn7;
    }

    public void setBtn7(boolean btn7) {
        this.btn7 = btn7;
    }

    public boolean isBtn8() {
        return btn8;
    }

    public void setBtn8(boolean btn8) {
        this.btn8 = btn8;
    }

    public boolean isBtn9() {
        return btn9;
    }

    public void setBtn9(boolean btn9) {
        this.btn9 = btn9;
    }

    public boolean isBtnModo() {
        return btnModo;
    }

    public void setBtnModo(boolean btnModo) {
        this.btnModo = btnModo;
    }

    public Jogada getBotao() {
        return botao;
    }

    public void setBotao(Jogada botao) {
        this.botao = botao;
    }
}
