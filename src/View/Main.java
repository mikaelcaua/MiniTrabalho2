package View;

import Controler.Departamento;
import Model.EspacoFisico.EspacoFisico;
import Model.Solicitacao.Horario;
import Model.Solicitacao.Solicitacao;

public class Main {
    public static void main(String[] args) {
        boolean teste;
        Departamento departamento = new Departamento("Teste");
        departamento.lerEspacosFisicosArquivo();
        departamento.lerSolicitacaoArquivo();
//        departamento.gerarRelatorioCurso("BICT");
        departamento.gerarRelatorioEspaco("301-NORTE");


    }
}