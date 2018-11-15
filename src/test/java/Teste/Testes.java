package Teste;

import DAO.MatriculaDisciplinaDAO;

/**
 *
 * @author Alexandre Almeida
 */
public class Testes {

    public static void main(String[] args) {
        System.out.println("Pode cursar? " + new MatriculaDisciplinaDAO().alunoMatriculado("030.114.981-05", "BDI"));
    }
}
