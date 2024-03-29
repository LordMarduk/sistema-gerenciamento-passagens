package util;

public class Auxiliares {

    public static final int CADASTRAR = 0;

    public static final int ALTERAR = 1;

    public static final String[] DIAS_SEMANA = {"segunda", "terca", "quarta",
    "quinta", "sexta", "sabado", "domingo"};

    public static final String[] DIAS = {"01", "02", "03", "04", "05", "06", "07", "08", "09", "10",
        "11", "12", "13", "14", "15", "16", "17", "18", "19", "20",
        "21", "22", "23", "24", "25", "26", "27", "28", "29", "30", "31"};

    public static final String[] UFS = {"", "AC", "AL", "AP", "AM", "BA", "CE", "DF",
        "ES", "GO", "MA", "MT", "MS", "MG", "PA", "PB", "PR", "PE", "PI", "RJ",
        "RN", "RS", "RO", "RR", "SC", "SP", "SE", "TO"};

    public static final String[] MESES = {"janeiro", "fevereiro", "marÃ§o", "abril", "maio", "junho",
       "julho", "agosto", "setembro", "outubro", "novembro", "dezembro"};

    public static final String[] ANOS = {"2000","2001","2002","2003","2004","2005","2006","2007","2008",
    "2009","2010","2011","2012","2013","2014","2015","2016","2017",
            "2018","2019","2020"};

    public static final String[] TIPO_FUNCIONARIO = {"Funcionarios","Agente","Motorista"};

    public static final String[] NUMERO_POLTRONA = {"1", "2", "3", "4", "5",
    	"6", "7", "8", "9", "10", "11", "12", "13", "14", "15", "16", "17",
    	"18", "19", "20", "21", "22", "23", "24", "25", "26", "27", "28",
    	"29", "30", "31", "32", "33", "34", "35", "36", "37", "38","39",
    	"40","41","42","43","44","45","46","47","48","49","50"};

    public static final String[] FLAG_VIAGENS = {"Instancia De Viagem","Tipo De Viagem"};

    public static int gerarId(int ant){
        ant++;
        String ats = ant + "";
        ats.replace('3', '4');
        return Integer.parseInt(ats);
    }

    public static String gerarCodigoPassagem(int np, int idtdv, String data){
        Date dat = new Date( data );
        String codigoPassagem = np < 10 ? "0" + np : "" + np;
        codigoPassagem += idtdv < 10 ? "0" + idtdv : "" + idtdv;
        codigoPassagem += dat.a + "";
        codigoPassagem += dat.m < 10 ? "0" + dat.m : "" + dat.m;
        codigoPassagem += dat.d < 10 ? "0" + dat.d : "" + dat.d;
        return codigoPassagem;
    }

    public static int getNumPoltrona(String codigoPassagem){
        return Integer.parseInt( codigoPassagem.substring(0, 2) );
    }

    public static int getIdTdv(String codigoPassagem){
        return Integer.parseInt( codigoPassagem.substring(2, 4) );
    }

    public static String getDataString(String codigoPassagem){
        return codigoPassagem.substring(4, 8) + "-" +
                codigoPassagem.substring(8, 10) + "-" +
                codigoPassagem.substring(10, 12);
    }

    public static Date getData(String codigoPassagem){
        return new Date( getDataString(codigoPassagem) );
    }

}
