package com.krakedev.artesanal;

public class Negocio {
	private String nombre;
	private Maquina maquinaA;

	
	
	public Negocio() {
		
	}

	public Negocio(String nombre, Maquina maquinaA) {
	    this.nombre = nombre;
	    this.maquinaA = maquinaA;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public Maquina getMaquinaA() {
		return maquinaA;
	}

	public void setMaquinaA(Maquina maquinaA) {
		this.maquinaA = maquinaA;
	}
	
	
}
