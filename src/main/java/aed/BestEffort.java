package aed;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import aed.Heap.Nodo;

public class BestEffort {
    private int promedio;
    private ArrayList<Ciudad> ciudades;
    private Heap heapPorTimestamp;
    private Heap heapPorGanancia;
    private Map<Traslado, Integer> mapaGanancia;
    private Map<Traslado, Integer> mapaTimestamp;

    public BestEffort(int cantCiudades, Traslado[] traslados){
        this.ciudades = new ArrayList<>();
        for (int i = 0; i < cantCiudades; i++) {
            ciudades.add(new Ciudad(i));
        }
        this.heapPorTimestamp = new Heap(true);
        this.heapPorGanancia = new Heap(false);
        for (Traslado traslado : traslados) {
            this.heapPorTimestamp.encolar(traslado);
            this.heapPorGanancia.encolar(traslado);
        }
    }

    public void registrarTraslados(Traslado[] traslados){
        for (Traslado traslado : traslados) {
            heapPorTimestamp.encolar(traslado);
            heapPorGanancia.encolar(traslado);
        }
        actualizarPromedio();
    }

    private void actualizarPromedio() {
        int totalGanancia = 0;
        int numTraslados = 0;
        for (Nodo nodo : heapPorGanancia.elems) {
            totalGanancia += nodo.valor.gananciaNeta;
            numTraslados++;
        }
        if (numTraslados != 0) {
            promedio = totalGanancia / numTraslados;
        }
    }

    public int[] despacharMasRedituables(int n){
        List<Integer> idsDespachados = new ArrayList<>();
        int numTrasladosDisponibles = Math.min(n, heapPorGanancia.elems.size());
        for (int i = 0; i < numTrasladosDisponibles; i++) {
            Traslado traslado = heapPorGanancia.desencolar();
            idsDespachados.add(traslado.id);
            actualizarPromedio();
        }
        int[] idsSeq = new int[idsDespachados.size()];
        for (int i = 0; i < idsDespachados.size(); i++) {
            idsSeq[i] = idsDespachados.get(i);
        }
        return idsSeq;
    }

    public int[] despacharMasAntiguos(int n){
        List<Integer> idsDespachados = new ArrayList<>();
        int numTrasladosDisponibles = Math.min(n, heapPorTimestamp.elems.size());
        for (int i = 0; i < numTrasladosDisponibles; i++) {
            Traslado traslado = heapPorTimestamp.desencolar();
            idsDespachados.add(traslado.id);
            actualizarPromedio();
        }
        int[] idsSeq = new int[idsDespachados.size()];
        for (int i = 0; i < idsDespachados.size(); i++) {
            idsSeq[i] = idsDespachados.get(i);
        }
        return idsSeq;
    }

    public int ciudadConMayorSuperavit(){
        // Implementar
        return 0;
    }

    public ArrayList<Integer> ciudadesConMayorGanancia(){
        // Implementar
        return null;
    }

    public ArrayList<Integer> ciudadesConMayorPerdida(){
        // Implementar
        return null;
    }

    public int gananciaPromedioPorTraslado(){
        // Implementar
        return 0;
    }
    
}
