package com.mentasBoutique.mentasUser.model;

import java.util.Objects;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name="clients")
public class User {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="id_client")
	private Long id;
	@Column(name="first_name")
	private String nombre;
	@Column(name="last_name")
	private String apellido;
	@Column(name="phone_number")
	private String telefono;
	@Column(name="email")
	private String email;
	@Column(name="password")
	private String password;
	@Column(name="roles_id_roles")
	private Long rol;
	
	
	public User() {
	
	}


	public User(Long id, String nombre, String apellido, String telefono, String email, String password, Long rol) {
		super();
		this.id = id;
		this.nombre = nombre;
		this.apellido = apellido;
		this.telefono = telefono;
		this.email = email;
		this.password = password;
		this.rol = rol;
	}


	public Long getId() {
		return id;
	}


	public void setId(Long id) {
		this.id = id;
	}


	public String getNombre() {
		return nombre;
	}


	public void setNombre(String nombre) {
		this.nombre = nombre;
	}


	public String getApellido() {
		return apellido;
	}


	public void setApellido(String apellido) {
		this.apellido = apellido;
	}


	public String getTelefono() {
		return telefono;
	}


	public void setTelefono(String telefono) {
		this.telefono = telefono;
	}


	public String getEmail() {
		return email;
	}


	public void setEmail(String email) {
		this.email = email;
	}


	public String getPassword() {
		return password;
	}


	public void setPassword(String password) {
		this.password = password;
	}


	public Long getRol() {
		return rol;
	}


	public void setRol(Long rol) {
		this.rol = rol;
	}


	@Override
	public String toString() {
		return "User [id=" + id + ", nombre=" + nombre + ", apellido=" + apellido + ", telefono=" + telefono
				+ ", email=" + email + ", password=" + password + ", rol=" + rol + "]";
	}


	@Override
	public int hashCode() {
		return Objects.hash(apellido, email, id, nombre, password, rol, telefono);
	}


	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		User other = (User) obj;
		return Objects.equals(apellido, other.apellido) && Objects.equals(email, other.email)
				&& Objects.equals(id, other.id) && Objects.equals(nombre, other.nombre)
				&& Objects.equals(password, other.password) && Objects.equals(rol, other.rol)
				&& Objects.equals(telefono, other.telefono);
	}
	
	

	
}//class user
