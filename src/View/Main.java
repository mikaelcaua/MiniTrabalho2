package View;

import Controler.Departamento;
import Model.EspacoFisico.EspacoFisico;
import Model.Solicitacao.Horario;
import Model.Solicitacao.Solicitacao;

public class Main {
    public static void main(String[] args) {
        boolean teste;
        EspacoFisico espacoFisico = new EspacoFisico("Sala",60,"303");

        Departamento departamento = new Departamento("Teste");
        departamento.getEspacoFisicos().add(espacoFisico);
        Horario horario1 = new Horario("25","T","24");
        Horario horario2 = new Horario("24","T","24");
        Solicitacao solicitacao1 = new Solicitacao(2023,1,"CP", 60,horario1,"Fixa", "Palestra");
        Solicitacao solicitacao2 = new Solicitacao(2023,1,"CP", 60,horario2,"Fixa", "Palestra");
        teste = departamento.reservarEspacoFisico(solicitacao1);
        System.out.println(teste);
        teste = departamento.reservarEspacoFisico(solicitacao2);
        System.out.println(teste);
    }
}