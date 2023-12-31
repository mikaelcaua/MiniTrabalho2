package Controler;

import Model.EspacoFisico.EspacoFisico;
import Model.Solicitacao.Horario;
import Model.Solicitacao.Solicitacao;
import Model.Solicitacao.SolicitacaoEventual;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import java.io.*;
import java.util.ArrayList;
import java.util.Hashtable;

public class Departamento {
    private String nome;

    private ArrayList <EspacoFisico> espacoFisicos;
    private Hashtable <String, ArrayList<Solicitacao>> solicitacaosConcluidas;

    public Departamento(String nome) {
        this.nome = nome;
        espacoFisicos = new ArrayList<>();
        solicitacaosConcluidas = new Hashtable<>();
        lerEspacosFisicosArquivo();
        lerSolicitacaoArquivo();

    }

    public String getNome() {
        return nome;
    }
    public ArrayList<EspacoFisico> getEspacoFisicos() {
        return espacoFisicos;
    }

    public Hashtable<String, ArrayList<Solicitacao>> getSolicitacaosConcluidas() {
        return solicitacaosConcluidas;
    }

    public void lerEspacosFisicosArquivo(){
        try{
            BufferedReader arquivo = new BufferedReader(new FileReader("Banco/espacosfisicos.csv"));
            String linha;
            while((linha=arquivo.readLine())!=null){
                String[] partes = linha.split(";");
                EspacoFisico novo = new EspacoFisico(partes[0],partes[1], Integer.parseInt(partes[2]));
                espacoFisicos.add(novo);
            }
            arquivo.close();
        }
        catch(IOException e){
            e.printStackTrace();
        }
    }

    public Horario quebrarHorarioArquivo(String horario) {
        String[] vetor1 = horario.split(" ");
        char[] vetoraux;
        String dias = "";
        String turno = "";
        String num = "";

        for (int i = 0; i < vetor1.length; i++) {
            vetoraux = vetor1[i].toCharArray();
            int j = 0;

            // Loop para obter os dias
            while (vetoraux[j] != 'M' && vetoraux[j] != 'T' && vetoraux[j] != 'N') {
                dias += vetoraux[j];
                j++;
            }

            turno+=vetoraux[j];
            j++;

            // Loop para obter o número
            while (j < vetoraux.length) {
                num += vetoraux[j];
                j++;
            }
        }

        return new Horario(dias, turno, num);
    }

    public boolean validarDatas(String dataInicio, String dataFim) {
        // Define o formato esperado para as datas
        SimpleDateFormat formatoData = new SimpleDateFormat("dd/MM/yyyy");
        formatoData.setLenient(false); // Impede a interpretação flexível das datas

        try {
            // Tenta fazer o parse das datas
            Date dataInicioParse = formatoData.parse(dataInicio);
            Date dataFimParse = formatoData.parse(dataFim);

            // Verifica se as datas são válidas
            if (dataInicioParse.after(dataFimParse)) {
                return false;
            }

        } catch (ParseException e) {
            // Se ocorrer uma exceção, a data é inválida
            return false;
        }

        // Se não houver exceções, as datas são válidas
        return true;
    }

    public void lerSolicitacaoArquivo(){
        try{
            BufferedReader arquivo = new BufferedReader(new FileReader("Banco/solicitacoes.csv"));
            String linha;
            while((linha = arquivo.readLine())!=null){
                String[] partes = linha.split(";");
                Solicitacao novo = new Solicitacao(partes[0], Integer.parseInt(partes[1]), Integer.parseInt(partes[2]), partes[3], partes[4], Integer.parseInt(partes[5]), quebrarHorarioArquivo(partes[6]));
                if(novo.getTipoSolicitcao().equals("Eventual")){
                    novo = new SolicitacaoEventual(partes[0], Integer.parseInt(partes[1]), Integer.parseInt(partes[2]), partes[3], partes[4], Integer.parseInt(partes[5]), quebrarHorarioArquivo(partes[6]), partes[7], partes[8]);
                    if(validarDatas(((SolicitacaoEventual) novo).getDataInicio(),((SolicitacaoEventual) novo).getDataFim())){
                        reservarEspacoFisico(novo);
                    }
                }
                else{
                    reservarEspacoFisico(novo);
                }

            }
        }
        catch(IOException e) {
            e.printStackTrace();
        }
    }

    public boolean temLetraComum(String str1, String str2) {
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
        boolean pular;
        for (EspacoFisico e : espacoFisicos) {
            pular = false;
            for (Horario horarioExistente : e.getHorarios().values()) {
                if (conflitoHorario(solicitacao.getHorario(), horarioExistente)) {
                    pular = true;
                }
            }
            if(pular){continue;}

            if (e.getCapacidade() == solicitacao.getVagas()) {
                if ((e.getTipoEspacoFisico().equals("Auditório") && solicitacao.getTipoSolicitcao().equals("Eventual")) ||
                    (e.getTipoEspacoFisico().equals("Sala"))) {
                    e.getHorarios().put(solicitacao.getHorario().toString(), solicitacao.getHorario());
                    if (!solicitacaosConcluidas.containsKey(solicitacao.getCurso())) {
                        solicitacaosConcluidas.put(solicitacao.getCurso(), new ArrayList<>());
                    }
                    solicitacao.setEspacoFisico(e);
                    solicitacaosConcluidas.get(solicitacao.getCurso()).add(solicitacao);


                    return true;
                }
            }

        }

        return false;
    }

    public boolean gerarRelatorioCurso(String key){
        try{
            BufferedWriter escritor = new BufferedWriter(new FileWriter("RelatoriosFeitos/relatoriocurso.csv"));
            if(solicitacaosConcluidas.containsKey(key)){
                escritor.write(key+"\n");
                for(Solicitacao s: solicitacaosConcluidas.get(key)){
                    escritor.write(s.toString()+"\n");
                }
                escritor.close();
                return  true;
            }
            escritor.close();
        }catch (IOException e){
            e.printStackTrace();
        }
        return false;

    }

    public boolean gerarRelatorioEspaco(String localizacaoSala){
        boolean retorno = false;
        try{
            BufferedWriter escritor = new BufferedWriter(new FileWriter("RelatoriosFeitos/relatorioespaco.csv"));

            escritor.write(localizacaoSala+"\n");

            for(String key: solicitacaosConcluidas.keySet()){
                for(Solicitacao s: solicitacaosConcluidas.get(key)){
                    if(s.localizacaoDebug().contains(localizacaoSala)){
                        escritor.write(s.toString()+"\n");
                        retorno = true;
                    }
                }
            }

            escritor.close();
            return retorno;

        }catch(IOException e){
            e.printStackTrace();
        }

        return retorno;
    }


}
