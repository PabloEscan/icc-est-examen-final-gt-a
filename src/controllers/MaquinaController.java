package controllers;

import java.util.*;
import models.Maquina;

public class MaquinaController {
    public Stack<Maquina> filtrarPorSubred(List<Maquina> maquinas, int umbral){
        Stack<Maquina> maquinasUmbral = new Stack<>();
        for(Maquina maq : maquinas){
            int subred = maq.getSubred();
            if(subred <= umbral){
                maquinasUmbral.add(maq);
            }
        }
        return maquinasUmbral;
    }

    public Set<Maquina> ordenarPorSubred(Stack<Maquina> pila){
        Set<Maquina> ordenados = new TreeSet<>();
        for(Maquina machine : pila){
            ordenados.add(machine);
        }
        return ordenados;
    }

    public TreeMap<Integer, Queue<Maquina>> agruparPorRiesgo(List<Maquina> maquinas) {
        TreeMap<Integer, Queue<Maquina>> mapa = new TreeMap<>();
        for (Maquina machine : maquinas) {
            int riesgo = machine.getRiesgo();
            mapa.putIfAbsent(riesgo, new LinkedList<>());
            mapa.get(riesgo).add(machine);
        }
        return mapa;
    }

    public Stack<Maquina> explotarGrupo(Map<Integer, Queue<Maquina>> mapa) {
        int maxCantidad = -1;
        int riesgoSeleccionado = -1;

        for (Map.Entry<Integer, Queue<Maquina>> entry : mapa.entrySet()) {
            int riesgo = entry.getKey();
            int cantidad = entry.getValue().size();

            if (cantidad > maxCantidad || (cantidad == maxCantidad && riesgo > riesgoSeleccionado)) {
                maxCantidad = cantidad;
                riesgoSeleccionado = riesgo;
            }
        }

        Queue<Maquina> grupo = mapa.get(riesgoSeleccionado);
        Stack<Maquina> resultado = new Stack<>();
        for (Maquina m : grupo) {
            resultado.insertElementAt(m, 0);
        }
        return resultado;
    }
}
