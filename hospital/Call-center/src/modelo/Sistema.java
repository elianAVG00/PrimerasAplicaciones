package modelo;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class Sistema {
	private List<Llamada> lstLlamadas;
	private List<Persona> lstPersonas;


	public Sistema() {
		super();
		this.lstLlamadas = new ArrayList<>();
		this.lstPersonas = new ArrayList<>();
	}

	public List<Llamada> getLstLlamadas() {
		return lstLlamadas;
	}

	public void setLstLlamadas(List<Llamada> lstLlamadas) {
		this.lstLlamadas = lstLlamadas;
	}

	public List<Persona> getLstPersonas() {
		return lstPersonas;
	}

	public void setLstPersonas(List<Persona> lstPersonas) {
		this.lstPersonas = lstPersonas;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((lstLlamadas == null) ? 0 : lstLlamadas.hashCode());
		result = prime * result + ((lstPersonas == null) ? 0 : lstPersonas.hashCode());
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
		Sistema other = (Sistema) obj;
		if (lstLlamadas == null) {
			if (other.lstLlamadas != null)
				return false;
		} else if (!lstLlamadas.equals(other.lstLlamadas))
			return false;
		if (lstPersonas == null) {
			if (other.lstPersonas != null)
				return false;
		} else if (!lstPersonas.equals(other.lstPersonas))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "SistemaCallCenter [lstLlamadas=" + lstLlamadas + ", lstPersonas=" + lstPersonas + "]";
	}
	
	//1-traerPersona
	public Persona traerPersona(long dni) {
		Persona p = null;
		int i = 0;
		
		while(i < lstPersonas.size() && p == null) {
			
			if(lstPersonas.get(i).getDni() == dni) {
				
				p = lstPersonas.get(i);
			}
			i++;
		}
		
		return p;
	}
	public int generarIdPersona() {
		
		int id = 0;
		
		if(!lstPersonas.isEmpty()) {
			id = lstPersonas.get(lstPersonas.size()-1).getIdPersona()+1;
		}
		
		return id;
	}
	
	//2-agregarCliente
	public boolean agregarCliente(String nombre, String apellido, long dni, boolean activo) throws Exception  {
		
		if(traerPersona(dni) != null)throw new Exception("Cliente ya existente");
		
		return lstPersonas.add(new Cliente(generarIdPersona(), nombre, apellido, dni, activo));
	}
	//3-agregarEmpleado
	public boolean agregarEmpleado(String nombre, String apellido, long dni, int sueldoBase) throws Exception  {
		
		if(traerPersona(dni) != null)throw new Exception("Empleado ya existente");
		
		return lstPersonas.add(new Empleado(generarIdPersona(), nombre, apellido, dni, sueldoBase));
	}
	//5-agregarLlamada
	public int generarIdLlamada() {
		
		int id = 0;
		
		if(!lstLlamadas.isEmpty()) {
			id = lstLlamadas.get(lstLlamadas.size()-1).getIdLlamada()+1;
		}
		
		return id;
	}
	
	public boolean agregarLlamada(LocalDate fecha, Cliente cliente, Empleado empleado, int nivelDeSatisfaccion) throws Exception {
		
		return lstLlamadas.add(new Llamada(generarIdLlamada(), cliente, empleado, fecha, nivelDeSatisfaccion));
	}
	//6-traerClientes
	public List<Cliente> traerClientes(){
		
		List<Cliente> l = new ArrayList<Cliente>();
		
		for(Persona p : lstPersonas) {
			
			if(p instanceof Cliente) {
				l.add((Cliente) p);
			}
		}
		return l;
	}
	//7-traerEmpleados
	public List<Empleado> traerEmpleados(){
		
		List<Empleado> l = new ArrayList<Empleado>();
		
		for(Persona p : lstPersonas) {
			
			if(p instanceof Empleado) {
				l.add((Empleado) p);
			}
		}
		return l;
	}
	//8-traerLlamada
	public List<Llamada> traerLlamada(LocalDate desde, LocalDate hasta){
		
		List<Llamada> l = new ArrayList<Llamada>();
		
		for(Llamada a : lstLlamadas) {
			
			if(a.getFecha().compareTo(desde)>=0 && a.getFecha().compareTo(hasta)<=0) {
				
				l.add(a);
			}
		}
		return l;
	}
	//9-traerLlamada
	public List<Llamada> traerLlamada(LocalDate desde, LocalDate after, int nivelDeSatisfaccion){
		
		List<Llamada> l = new ArrayList<Llamada>();
		
		for(Llamada a : traerLlamada(desde, after)) {
			
			if(a.getNivelDeSatisfaccion() == nivelDeSatisfaccion) {
				
				l.add(a);
			}
		}
		return l;
	}
	//10-calcularPorcentajeNivelSatisfaccionLlamada
	public double calcularPorcentajeNivelSatisfaccionLlamada(LocalDate desde,LocalDate hasta,int nivelDeSatisfaccion){
		
		List<Llamada> l= traerLlamada(desde,hasta);
		List<Llamada> l1= traerLlamada(desde,hasta,nivelDeSatisfaccion);
		
		double res = 0;
		if(0 < l.size()) {
			res	= l1.size()*100/l.size();
		}
		return res;
	}
	
	//11-traerLlamada
	public List<Llamada> traerLlamada(LocalDate desde,LocalDate hasta,Empleado empleado){
		
		List<Llamada> l= new ArrayList<Llamada>();
		
		for(Llamada a : traerLlamada(desde,hasta)) {
			if(a.getEmpleado().equals(empleado)) {
				l.add(a);
			}
		}
		
		return l;
	}
	//12-traerLlamada
	public List<Llamada> traerLlamada(LocalDate desde,LocalDate hasta,Empleado empleado, int nivelSatisfaccion){
		
		List<Llamada> l= new ArrayList<Llamada>();
			
		for(Llamada a : traerLlamada(desde,hasta,empleado)) {
			if(a.getNivelDeSatisfaccion() == nivelSatisfaccion) {
				l.add(a);
			}
		}
		
		return l;
	}
	//13-calcularPorcentajeNivelDeSatisfaccion
	public double calcularPorcentajeNivelSatisfaccionLlamada(LocalDate desde,LocalDate hasta,Empleado empleado, int nivelDeSatisfaccion){
		
		List<Llamada> l= traerLlamada(desde,hasta,empleado);
		List<Llamada> l1= traerLlamada(desde,hasta,empleado,nivelDeSatisfaccion);
		
		double res = 0;
		if(0 < l.size()) {
			res= l1.size()*100/l.size();
		}
		
		return res;
	}
}
