package modelo;

public class Cliente extends Persona {
	private boolean activo;

	public Cliente(int idPersona, String nombre, String apellido, long dni, boolean activo) {
		super(idPersona, nombre, apellido, dni);
		this.activo = activo;
	}

	public boolean isActivo() {
		return activo;
	}

	public void setActivo(boolean activo) {
		this.activo = activo;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = super.hashCode();
		result = prime * result + (activo ? 1231 : 1237);
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
		Cliente other = (Cliente) obj;
		if (activo != other.activo)
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "Cliente [activo=" + activo + ", IdPersona=" + getIdPersona() + ", Nombre=" + getNombre()
				+ ", Apellido=" + getApellido() + ", Dni=" + getDni() + "]";
	}

	
	
}
