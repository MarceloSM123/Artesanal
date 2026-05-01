package com.krakedev.artesanal;

public class Maquina {
	private String nombreCerveza;
	private String descripcion;
	private double precioPorMl;
	private double capacidadMaxima;
	private double cantidadActual;
	private String codigo;
	private double cantidadDesperdicio;
	
	

	public double getCantidadDesperdicio() {
		return cantidadDesperdicio;
	}

	public void setCantidadDesperdicio(double cantidadDesperdicio) {
		this.cantidadDesperdicio = cantidadDesperdicio;
	}

	public String getCodigo() {
		return codigo;
	}

	public Maquina(String nombreCerveza, String descripcion, double precioPorMl, double capacidadMaxima) {
		this.nombreCerveza = nombreCerveza;
		this.descripcion = descripcion;
		this.precioPorMl = precioPorMl;
		this.capacidadMaxima = capacidadMaxima;
		this.cantidadActual = 0;
	}

	public Maquina(String nombreCerveza, String descripcion, double precioPorMl) {
		this.nombreCerveza = nombreCerveza;
		this.descripcion = descripcion;
		this.precioPorMl = precioPorMl;
		this.capacidadMaxima = 10000;
		this.cantidadActual = 0;
	}

	public String getNombreCerveza() {
		return nombreCerveza;
	}

	public void setNombreCerveza(String nombreCerveza) {
		this.nombreCerveza = nombreCerveza;
	}

	public String getDescripcion() {
		return descripcion;
	}

	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}

	public double getPrecioPorMl() {
		return precioPorMl;
	}

	public void setPrecioPorMl(double precioPorMl) {
		this.precioPorMl = precioPorMl;
	}

	public double getCapacidadMaxima() {
		return capacidadMaxima;
	}

	public void setCapacidadMaxima(double capacidadMaxima) {
		this.capacidadMaxima = capacidadMaxima;
	}

	public double getCantidadActual() {
		return cantidadActual;
	}

	public void imprimir() {
		String mensaje;
		mensaje = "Nombre cerveza: " + nombreCerveza + ", Descripción: " + descripcion + ", Precio por Ml: "
				+ precioPorMl + ", Capacidad Máxima: " + capacidadMaxima + ", Cantidad Actual: " + cantidadActual+", Codigo " + codigo;

		System.out.println(mensaje);
	}

	public void llenarMaquina() {
		this.cantidadActual = this.capacidadMaxima - 200;
	}

	public boolean recargarCerveza(double cantidad) {
		double limitePermitido;
		limitePermitido = capacidadMaxima - 200;
		if (cantidadActual + cantidad <= limitePermitido) {
			cantidadActual = cantidadActual + cantidad;
			return true;
		} else {
			return false;
		}
	}

	public double servirCerveza(double cantidad) {
		if (cantidadActual >= cantidad) {
			cantidadActual = cantidadActual - cantidad;

			double valor;
			valor = cantidad * precioPorMl;
			return valor;
		} else {
			return 0;
		}
	}
	
	public void vaciarMaquina() {
		cantidadDesperdicio=cantidadActual;
		cantidadActual=0;
	}

}
