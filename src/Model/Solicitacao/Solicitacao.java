package Model.Solicitacao;

import Model.EspacoFisico.EspacoFisico;

public class Solicitacao {
    private int ano;
    private int semestre;
    private String curso;
    private int vagas;
    private Horario horario;
    private String tipoSolicitcao;
    private String dado;
    private EspacoFisico espacoFisico;

    public Solicitacao(String tipoSolicitcao,int ano, int semestre, String curso,String dado, int vagas, Horario horario ) {
        this.ano = ano;
        this.semestre = semestre;
        this.curso = curso;
        this.vagas = vagas;
        this.horario = horario;
        this.tipoSolicitcao = tipoSolicitcao;
        this.dado = dado;
    }

    public int getAno() {
        return ano;
    }

    public int getSemestre() {
        return semestre;
    }

    public String getCurso() {
        return curso;
    }

    public int getVagas() {
        return vagas;
    }

    public Horario getHorario() {
        return horario;
    }

    public String getTipoSolicitcao() {
        return tipoSolicitcao;
    }

    public String getDado() {
        return dado;
    }

    public EspacoFisico getEspacoFisico() {
        return espacoFisico;
    }

    public void setEspacoFisico(EspacoFisico espacoFisico) {
        this.espacoFisico = espacoFisico;
    }

    @Override
    public String toString() {
        return  "ano=" + ano +
                ";semestre= " + semestre +
                ";curso= " + curso +
                ";vagas= " + vagas +
                ";" + horario.toString() +
                ";" + tipoSolicitcao +
                ";" + dado +
                ";" + espacoFisico.getLocalizacao();
    }

    public String localizacaoDebug(){
        return espacoFisico.getLocalizacao();
    }
}
