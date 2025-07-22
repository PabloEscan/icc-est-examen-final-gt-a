package models;

import java.util.List;

public class Maquina implements Comparable{
    private String nombre;
    private String ip;
    private List<Integer> codigos;
    private int subred;
    private int riesgo;

    public Maquina(String nombre, String ip, List<Integer> codigos) {
        this.nombre = nombre;
        this.ip = ip;
        this.codigos = codigos;
    }
    
    private int calcularSubred() {
        String[] numeros = ip.split("\\.");
        int cuartoOcteto = Integer.parseInt(numeros[2]);
        return cuartoOcteto;
    }

    private int calcularRiesgo() {
        int total =0;
        String[] numeros = ip.split("\\.");
        for(int i=0; i<numeros.length; i++){
            int valor = Integer.parseInt(numeros[0]);
            if(valor%3 ==0){
                total += valor;
            }
        }
        return total;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getIp() {
        return ip;
    }

    public void setIp(String ip) {
        this.ip = ip;
    }

    public List<Integer> getCodigos() {
        return codigos;
    }

    public void setCodigos(List<Integer> codigos) {
        this.codigos = codigos;
    }

    public int getSubred() {
        return calcularSubred();
    }

    public void setSubred(int subred) {
        this.subred = subred;
    }

    public int getRiesgo() {
        return calcularRiesgo();
    }

    public void setRiesgo(int riesgo) {
        this.riesgo = riesgo;
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((nombre == null) ? 0 : nombre.hashCode());
        result = prime * result + subred;
        return result;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        Maquina other = (Maquina) obj;
        if (nombre == null) {
            if (other.nombre != null)
                return false;
        } else if (!nombre.equals(other.nombre))
            return false;
        if (subred != other.subred)
            return false;
        return true;
    }

    @Override
    public int compareTo(Object o) {
        Maquina compM = (Maquina) o;
        if(this == o)return 0;
        
        if(this.subred != compM.getSubred()){
            return Math.max(this.subred, compM.subred);
        } 

        int intN = this.nombre.compareTo(compM.getNombre());
        return intN;
    }
}
