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
        departamento.gerarRelatorioCurso("Ciência da Computação");
//        departamento.gerarRelatorioEspaco("301-NORTE"); n ta funcionando por algum bug


    }
}