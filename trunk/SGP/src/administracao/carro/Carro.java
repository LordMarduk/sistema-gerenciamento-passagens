package administracao.carro;

public class Carro {

    protected int id_seq_carro;

    protected String placa;

    protected String chassis;

    protected String arCondicionado;

    protected int Capacidade;

    public Carro(){}

    public int getId_seq_carro() {
        return id_seq_carro;
    }

    public String getPlaca() {
        return placa;
    }

    public String getChassis() {
        return chassis;
    }

    public String getArCondicionado() {
        return arCondicionado;
    }

    public int getCapacidade() {
        return Capacidade;
    }


    public void setId_seq_carro(int id_seq_carro) {
        this.id_seq_carro = id_seq_carro;
    }

    public void setPlaca(String placa) {
        this.placa = placa;
    }

    public void setChassis(String chassis) {
        this.chassis = chassis;
    }

    public void setArCondicionado(String arCondicionado) {
        this.arCondicionado = arCondicionado;
    }

    public void setCapacidade(int getCapacidade) {
        this.Capacidade = getCapacidade;
    }

    

    @Override
    public String toString() {
        StringBuffer ret = new StringBuffer();
            ret.append( "com.mycompany.myapp.dto.Carro: " );
            ret.append( "id_seq_carro=" + id_seq_carro );
            ret.append( ", placa=" + placa );
            ret.append( ", chassis=" + chassis );
            ret.append( ", arCondicionado=" + arCondicionado );
            return ret.toString();
    }



}