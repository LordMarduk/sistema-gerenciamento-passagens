package util;

import javax.swing.JComponent;
import javax.swing.JTextField;

public class Auxiliares {

    public static final String[] DIAS_SEMANA = {"segunda", "terca", "quarta",
    "quinta", "sexta", "sabado", "domingo"};

    public static final String[] DIAS = {"1", "2", "3", "4", "5", "6", "7", "8", "9", "10",
        "11", "12", "13", "14", "15", "16", "17", "18", "19", "20",
        "21", "22", "23", "24", "25", "26", "27", "28", "29", "30", "31"};

    public static final String[] UFS = {"", "AC", "AL", "AP", "AM", "BA", "CE", "DF",
        "ES", "GO", "MA", "MT", "MS", "MG", "PA", "PB", "PR", "PE", "PI", "RJ",
        "RN", "RS", "RO", "RR", "SC", "SP", "SE", "TO"};

    public static final String[] MESES = {"janeiro", "fevereiro", "março", "abril", "maio", "junho",
       "julho", "agosto", "setembro", "outubro", "novembro", "dezembro"};

    public static final String[] ANOS = {"2000","2001","2002","2003","2004","2005","2006","2007","2008",
    "2009","2010","2011","2012","2013","2014","2015","2016","2017",
            "2018","2019","2020"};

    public static final String[] TIPO_FUNCIONARIO = {"","motorista","agente"};

    public static final String[] NUMERO_POLTRONA = {"1", "2", "3", "4", "5",
    	"6", "7", "8", "9", "10", "11", "12", "13", "14", "15", "16", "17",
    	"18", "19", "20", "21", "22", "23", "24", "25", "26", "27", "28",
    	"29", "30", "31", "32", "33", "34", "35", "36", "37", "38","39",
    	"40","41","42","43","44","45","46","47","48","49","50"};

    public static final String[] FLAG_VIAGENS = {"","tipo de viagem","instancia de viagem"};

    public static int gerarId(int ant){
        ant++;
        String ats = ant + "";
        ats.replace('3', '4');
        return Integer.parseInt(ats);
    }

}
