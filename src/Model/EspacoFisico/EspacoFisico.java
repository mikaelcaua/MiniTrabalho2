package Model.EspacoFisico;

import Model.Solicitacao.Horario;
import Model.Solicitacao.Solicitacao;

import java.util.ArrayList;
import java.util.Hashtable;

public class EspacoFisico {
    private String tipo;
    private int capacidade;
    private String localizacao;
    Hashtable <String, Horario> horarios;


    public EspacoFisico( String localizacao,String tipo, int capacidade) {
        this.tipo = tipo;
        this.capacidade = capacidade;
        this.localizacao = localizacao;
        horarios = new Hashtable<>();

    }

    public String getTipoEspacoFisico() {
        return tipo;
    }

    public int getCapacidade() {
        return capacidade;
    }

    public String getLocalizacao() {
        return localizacao;
    }

    public Hashtable<String, Horario> getHorarios() {
        return horarios;
    }
}
