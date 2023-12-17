package View;

import Controler.Departamento;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;


public class Screen extends JFrame implements ActionListener {
    JLabel textFeedback;

    JLabel textCurso;


    JTextField inputArquivoRelatorioCurso;
    JButton fazerRelatorioCurso;
    JLabel textEspaco;
    JTextField inputArquivoRelatorioEspaco;
    JButton fazerRelatorioEspaco;
    Departamento departamento;


    public Screen(){
        textFeedback= new JLabel();
        textCurso = new JLabel("Digite o curso:");
        inputArquivoRelatorioCurso = new JTextField();
        fazerRelatorioCurso = new JButton("Relatorio Curso");
        textEspaco = new JLabel("Digite o espaço físico:");
        inputArquivoRelatorioEspaco = new JTextField();
        fazerRelatorioEspaco  = new JButton("Relatorio Espaço");
        departamento= new Departamento("UFMA");

        setResizable(false);
        setTitle("MiniTrabalho2");
        setSize(800,600);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setLayout(null);

        Font fonteBotoes = new Font("Arial", Font.BOLD, 20);
        Font fonteCaixaTexto = new Font("Arial", Font.ITALIC, 14);
        Font fonteTexto = new Font("Arial", Font.BOLD, 20);


        textFeedback.setFont(new Font("Arial", Font.ITALIC, 20));


        textCurso.setBounds(250,100, 300,25);
        textCurso.setFont(fonteTexto);


        inputArquivoRelatorioCurso.setFont(fonteCaixaTexto);
        inputArquivoRelatorioCurso.setBounds(250, 130, 300, 25);


        fazerRelatorioCurso.setFont(fonteBotoes);
        fazerRelatorioCurso.setBounds(250,154, 300, 50);


        textEspaco.setBounds(250,285, 300,25);
        textEspaco.setFont(fonteTexto);


        inputArquivoRelatorioEspaco.setFont(fonteCaixaTexto);
        inputArquivoRelatorioEspaco.setBounds(250,316, 300,25);


        fazerRelatorioEspaco.setFont(fonteBotoes);
        fazerRelatorioEspaco.setBounds(250, 340, 300, 50);

        add(textFeedback);
        add(textCurso);
        add(inputArquivoRelatorioCurso);
        add(fazerRelatorioCurso);
        add(textEspaco);
        add(inputArquivoRelatorioEspaco);
        add(fazerRelatorioEspaco);

        textCurso.setVisible(true);
        inputArquivoRelatorioCurso.setVisible(true);
        fazerRelatorioCurso.setVisible(true);
        textEspaco.setVisible(true);
        fazerRelatorioEspaco.setVisible(true);
        setVisible(true);


        fazerRelatorioCurso.addActionListener(this::fazerRelatorioCurso);
        fazerRelatorioEspaco.addActionListener(this::fazerRelatorioEspaco);

    }


    private void fazerRelatorioCurso(ActionEvent actionEvent) {
        boolean teste;
        teste = departamento.gerarRelatorioCurso(inputArquivoRelatorioCurso.getText());
        if(teste){
            textFeedback.setBounds(200,194, 500, 50);
            textFeedback.setText("Relatório feito com Sucesso, feche o programa!");
            textFeedback.setVisible(true);
        }
        else{
            textFeedback.setText("Não foi possível fazer o relatório");
            textFeedback.setBounds(250,194, 500, 50);
            textFeedback.setVisible(true);

        }
    }


    private void fazerRelatorioEspaco(ActionEvent actionEvent){
        boolean teste;
        teste = departamento.gerarRelatorioEspaco(inputArquivoRelatorioEspaco.getText());
        if(teste){
            textFeedback.setBounds(200,390, 500, 50);
            textFeedback.setText("Relatório feito com Sucesso, feche o programa!");
            textFeedback.setVisible(true);
        }
        else{
            textFeedback.setText("Não foi possível fazer o relatório");
            textFeedback.setBounds(250,390, 500, 50);
            textFeedback.setVisible(true);

        }
    }


    @Override
    public void actionPerformed(ActionEvent e) {
    }

}
