package Controler;

import Model.EspacoFisico.EspacoFisico;
import Model.Solicitacao.Horario;
import Model.Solicitacao.Solicitacao;

import java.util.ArrayList;
import java.util.Hashtable;

public class Departamento {
    private String nome;
    ArrayList <EspacoFisico> espacoFisicos;
    Hashtable <String, ArrayList<Solicitacao>> solicitacaosConcluidas;

    public Departamento(String nome) {
        this.nome = nome;
        espacoFisicos = new ArrayList<>();
        solicitacaosConcluidas = new Hashtable<>();
    }

    public static boolean temLetraComum(String str1, String str2) {
        for (char letra : str1.toCharArray()) {
            if (str2.contains(String.valueOf(letra))) {
                return true;
            }
        }
        return false;
    }

    private boolean conflitoHorario(Horario novoHorario, Horario horarioExistente) {
        return temLetraComum(novoHorario.getDias(),horarioExistente.getDias())
                && temLetraComum(novoHorario.getTurno(),horarioExistente.getTurno())
                && temLetraComum(novoHorario.getNum(),horarioExistente.getNum());
    }

    public boolean reservarEspacoFisico(Solicitacao solicitacao) {
        for (EspacoFisico e : espacoFisicos) {
            for (Horario horarioExistente : e.getHorarios().values()) {
                if (conflitoHorario(solicitacao.getHorario(), horarioExistente)) {
                    // Conflito de horário
                    return false;
                }
            }

            if (e.getCapacidade() == solicitacao.getVagas()) {
                if ((e.getTipoEspacoFisico().equals("Auditorio") && solicitacao.getTipoSolicitcao().equals("Eventual")) ||
                    (e.getTipoEspacoFisico().equals("Sala"))) {
                    e.getHorarios().put(solicitacao.getHorario().toString(), solicitacao.getHorario());
                    if (!solicitacaosConcluidas.containsKey(solicitacao.getCurso())) {
                        solicitacaosConcluidas.put(solicitacao.getCurso(), new ArrayList<>());
                    }
                    solicitacaosConcluidas.get(solicitacao.getCurso()).add(solicitacao);

                    return true;
                }
            }

        }

        return false;
    }

    public String getNome() {
        return nome;
    }

    public ArrayList<EspacoFisico> getEspacoFisicos() {
        return espacoFisicos;
    }

    public void gerarRelatorioSala(String nomeCurso){
        //varre o hash solicitacoesconcluidas, printa a chave e varre o array vinculado a chave e printa todas as solicitações
    }

    public void gerarRelatorioEspaco(String localizacaoSala){
        //varre o  array de espaco fisico, varre a hash de horarios e vai printando todos os horarios
    }
}
