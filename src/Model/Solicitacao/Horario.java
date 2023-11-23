package Model.Solicitacao;

public class Horario {
    private String dias;
    private String turno;
    private String num;

    public Horario(String dias, String turno, String num) {
        this.dias = dias;
        this.turno = turno;
        this.num = num;
    }

    public String getDias() {
        return dias;
    }

    public String getTurno() {
        return turno;
    }

    public String getNum() {
        return num;
    }

    @Override
    public String toString() {
        return "Horario{" +
                "dias='" + dias + '\'' +
                ", turno='" + turno + '\'' +
                ", num='" + num + '\'' +
                '}';
    }
}
