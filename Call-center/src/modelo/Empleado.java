package modelo;

public class Empleado extends Persona{
	private int sueldoBase;

	public Empleado(int idPersona, String nombre, String apellido, long dni, int sueldoBase) {
		super(idPersona, nombre, apellido, dni);
		this.sueldoBase = sueldoBase;
	}

	public int getSueldoBase() {
		return sueldoBase;
	}

	public void setSueldoBase(int sueldoBase) {
		this.sueldoBase = sueldoBase;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = super.hashCode();
		result = prime * result + sueldoBase;
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (!super.equals(obj))
			return false;
		if (getClass() != obj.getClass())
			return false;
		Empleado other = (Empleado) obj;
		if (sueldoBase != other.sueldoBase)
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "Empleado [sueldoBase=" + sueldoBase + ", IdPersona=" + getIdPersona() + ", Nombre=" + getNombre()
		+ ", Apellido=" + getApellido() + ", Dni=" + getDni() + "]";
	}

	
	
}
