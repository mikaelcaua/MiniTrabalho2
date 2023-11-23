package Model.Solicitacao;

public class Solicitacao {
    private int ano;
    private int semestre;
    private String curso;
    private int vagas;
    private Horario horario;
    private String tipoSolicitcao;
    private String dado;

    public Solicitacao(int ano, int semestre, String curso, int vagas, Horario horario, String tipoSolicitcao, String dado) {
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
}
