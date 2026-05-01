package com.krakedev.artesanal;

import java.util.ArrayList;

public class NegocioMejorado {
	private ArrayList<Maquina> maquinas;
	private ArrayList<Cliente> clientes;
	private int ultimoCodigo=100;

	public NegocioMejorado() {
        maquinas = new ArrayList<Maquina>();
        
    }
	
	public ArrayList<Maquina> getMaquinas() {
		return maquinas;
	}

	public void setMaquinas(ArrayList<Maquina> maquinas) {
		this.maquinas = maquinas;
	}
	
	public String generarCodigo() {
        int numeroAleatorio = (int) (Math.random() * 100) + 1;
        return "M-" + numeroAleatorio;
    }
	
	public boolean agregarMaquina(String nombreCerveza, String descripcion, double precioPorMl) {
        String codigo = generarCodigo();
                   
            Maquina existente = recuperarMaquina(codigo);
            if (existente != null) {
                return false; 
            }
            
            Maquina nuevaMaquina = new Maquina(nombreCerveza, descripcion, precioPorMl);
            nuevaMaquina.setCodigo(codigo);
            maquinas.add(nuevaMaquina);
            return true;
        }
    
	
	public void cargarMaquinas() {
        for (int i = 0; i < maquinas.size(); i++) {
            maquinas.get(i).llenarMaquina();
        }
    }
	
	public Maquina recuperarMaquina(String codigo) {
        for (int i = 0; i < maquinas.size(); i++) {
            if (maquinas.get(i).getCodigo().equals(codigo)) {
                return maquinas.get(i);
            }
        }
        return null;
    }
	
	public void registrarCliente(String nombre, String cedula) {
        Cliente nuevoCliente = new Cliente(nombre, cedula);
        nuevoCliente.setCodigo(ultimoCodigo);
        ultimoCodigo++;
        clientes.add(nuevoCliente);
    }

}
