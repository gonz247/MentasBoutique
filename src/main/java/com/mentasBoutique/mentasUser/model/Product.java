package com.mentasBoutique.mentasUser.model;

import java.util.Objects;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;


@Entity
@Table(name="products")
public class Product {
		@Id
		@GeneratedValue(strategy = GenerationType.IDENTITY)
		@Column(name="id_product")
		private Long id;
		@Column(name="product_name")
		private String nombre;
		@Column(name="price")
		private Long precio;
		@Column(name="description")
		private String descripcion;
		@Column(name="material")
		private String material;
		@Column(name="type")
		private String tipo;
		@Column(name="category")
		private String categoria;
		@Column(name="url_img")
		private String imagen;
		@Column(name="stock")
		private Long stock;
		
		
		
		
		
		public Product() {
			super();
		}




		public Product(Long id, String nombre, Long precio, String descripcion, String material, String tipo,
				String categoria, String imagen, Long stock) {
			super();
			this.id = id;
			this.nombre = nombre;
			this.precio = precio;
			this.descripcion = descripcion;
			this.material = material;
			this.tipo = tipo;
			this.categoria = categoria;
			this.imagen = imagen;
			this.stock = stock;
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




		public Long getPrecio() {
			return precio;
		}




		public void setPrecio(Long precio) {
			this.precio = precio;
		}




		public String getDescripcion() {
			return descripcion;
		}




		public void setDescripcion(String descripcion) {
			this.descripcion = descripcion;
		}




		public String getMaterial() {
			return material;
		}




		public void setMaterial(String material) {
			this.material = material;
		}




		public String getTipo() {
			return tipo;
		}




		public void setTipo(String tipo) {
			this.tipo = tipo;
		}




		public String getCategoria() {
			return categoria;
		}




		public void setCategoria(String categoria) {
			this.categoria = categoria;
		}




		public String getImagen() {
			return imagen;
		}




		public void setImagen(String imagen) {
			this.imagen = imagen;
		}




		public Long getStock() {
			return stock;
		}




		public void setStock(Long stock) {
			this.stock = stock;
		}




		@Override
		public String toString() {
			return "Product [id=" + id + ", nombre=" + nombre + ", precio=" + precio + ", descripcion=" + descripcion
					+ ", material=" + material + ", tipo=" + tipo + ", categoria=" + categoria + ", imagen=" + imagen
					+ ", stock=" + stock + "]";
		}




		@Override
		public int hashCode() {
			return Objects.hash(categoria, descripcion, id, imagen, material, nombre, precio, stock, tipo);
		}




		@Override
		public boolean equals(Object obj) {
			if (this == obj)
				return true;
			if (obj == null)
				return false;
			if (getClass() != obj.getClass())
				return false;
			Product other = (Product) obj;
			return Objects.equals(categoria, other.categoria) && Objects.equals(descripcion, other.descripcion)
					&& Objects.equals(id, other.id) && Objects.equals(imagen, other.imagen)
					&& Objects.equals(material, other.material) && Objects.equals(nombre, other.nombre)
					&& Objects.equals(precio, other.precio) && Objects.equals(stock, other.stock)
					&& Objects.equals(tipo, other.tipo);
		}

	
	
	
	
}//class product
