package util;

public class Date {

        public int d;
        public int m;
        public int a;

        public Date(int d, int m, int a){
            this.d = d;
            this.m = m;
            this.a = a;
        }

        public String toString(){
            String dia = d < 10 ? "0" + d : d + "";
            String mes = m < 10 ? "0" + m : m + "";
            return dia + "/" + mes + "/" + a;
        }

}
