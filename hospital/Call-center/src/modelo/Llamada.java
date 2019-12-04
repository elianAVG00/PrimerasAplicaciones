package modelo;

import java.time.LocalDate;

public class Llamada {
	private int idLlamada;
	private Cliente cliente;
	private Empleado empleado;
	private LocalDate fecha;
	private int nivelDeSatisfaccion;
	public Llamada(int idLlamada, Cliente cliente, Empleado empleado, LocalDate fecha, int nivelDeSatisfaccion) throws Exception {
		super();
		this.idLlamada = idLlamada;
		this.cliente = cliente;
		this.empleado = empleado;
		this.fecha = fecha;
		
		if(!validarNivelDeSatisfaccion(nivelDeSatisfaccion) )throw new Exception("Nivel de satisfaccion invalido");
		else {
			this.nivelDeSatisfaccion = nivelDeSatisfaccion;
		}
	}
	public int getIdLlamada() {
		return idLlamada;
	}
	public void setIdLlamada(int idLlamada) {
		this.idLlamada = idLlamada;
	}
	public Cliente getCliente() {
		return cliente;
	}
	public void setCliente(Cliente cliente) {
		this.cliente = cliente;
	}
	public Empleado getEmpleado() {
		return empleado;
	}
	public void setEmpleado(Empleado empleado) {
		this.empleado = empleado;
	}
	public LocalDate getFecha() {
		return fecha;
	}
	public void setFecha(LocalDate fecha) {
		this.fecha = fecha;
	}
	public int getNivelDeSatisfaccion() {
		return nivelDeSatisfaccion;
	}
	public void setNivelDeSatisfaccion(int nivelDeSatisfaccion) throws Exception {
		
	}
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((cliente == null) ? 0 : cliente.hashCode());
		result = prime * result + ((empleado == null) ? 0 : empleado.hashCode());
		result = prime * result + ((fecha == null) ? 0 : fecha.hashCode());
		result = prime * result + idLlamada;
		result = prime * result + nivelDeSatisfaccion;
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
		Llamada other = (Llamada) obj;
		if (cliente == null) {
			if (other.cliente != null)
				return false;
		} else if (!cliente.equals(other.cliente))
			return false;
		if (empleado == null) {
			if (other.empleado != null)
				return false;
		} else if (!empleado.equals(other.empleado))
			return false;
		if (fecha == null) {
			if (other.fecha != null)
				return false;
		} else if (!fecha.equals(other.fecha))
			return false;
		if (idLlamada != other.idLlamada)
			return false;
		if (nivelDeSatisfaccion != other.nivelDeSatisfaccion)
			return false;
		return true;
	}
	@Override
	public String toString() {
		return "Llamada [idLlamada=" + idLlamada + ", cliente=" + cliente + ", empleado=" + empleado + ", fecha="
				+ fecha + ", nivelDeSatisfaccion=" + nivelDeSatisfaccion +  "]"+ "\n";
	}
	
	//4-validarNivelDeSatisfaccion
		public boolean validarNivelDeSatisfaccion(int nivelDeSatisfaccion) {
			if(nivelDeSatisfaccion > 0 && nivelDeSatisfaccion < 6) {
				return true;
			}
			return false;
		}
	
	
	
}
