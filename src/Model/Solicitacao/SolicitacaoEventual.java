package Model.Solicitacao;

public class SolicitacaoEventual extends Solicitacao {

    private String dataInicio;
    private String dataFim;

    public SolicitacaoEventual(String tipoSolicitcao, int ano, int semestre, String curso, String dado, int vagas, Horario horario, String dataInicio, String dataFim) {
        super(tipoSolicitcao, ano, semestre, curso, dado, vagas, horario);
        this.dataInicio = dataInicio;
        this.dataFim = dataFim;
    }

    public String getDataInicio() {
        return dataInicio;
    }


    public String getDataFim() {
        return dataFim;
    }


}
