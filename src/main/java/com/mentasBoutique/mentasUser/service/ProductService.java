package com.mentasBoutique.mentasUser.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mentasBoutique.mentasUser.model.Product;

import com.mentasBoutique.mentasUser.repository.ProductRepository;


@Service
public class ProductService {

	//Vamos a traer a JPA para que me brinde los metodos para recuperar los datos
	private final ProductRepository repository;
	
	//constructor permite la inyeccion de dependencias
	@Autowired
	public ProductService(ProductRepository repository) {
		this.repository = repository;
	}
	
	//metodo de tipo GET para traer a todos los usuarios
	public List<Product> allProduct(){
		return repository.findAll();
	}
	
	
	//DELETE
		//Crear metodo para eliminar un usuario por id. Primero debemos comprobar que el usuario con el id realmente existe, en caso de existir se elimina, si no, se lanza una exception por default (illegalSatateException) o perzonalizada
		public void deleteProduct(Long id) {
			if (repository.existsById(id)) {
				repository.deleteById(id);
			}
			else {
				throw new IllegalStateException("Id de producto no existente");
			}
		}
		
		/*POST, crear un nuevo usuario. 
		 * Primero tengo que consultar la DB para saber si el usuario existe. Por ello, debo definir de qué manera buscaré a mi usuario. En este caso será mediante email.
		 * Si el usuario con un email asociado existe, no puede crear un nuevo usuario con el mismo email.
		 * Si no hay un usuario con el email asociado, se crea un nuevo usuario.
		 * Tenemos que definir la búsqueda específica por email (findByEmail) utilizando una consulta de tipo JPQL, la cual se realiza dentro la interface UserRepository ya que ahí se implementan todas los métodos de JPA
		 */
		
		public Product addProduct(Product newProduct) {
		return repository.save(newProduct);
		}
		
		//metodo PUT
		/*
		 * Debemos iterar cada key/value del objeto para observar las modificaciones y aplicarlas en el atributo
		 * especifico. Para ello usaremos el metodo .map
		 */
		public Product updateProduct(Product product, Long id) {
			return repository.findById(id)
					.map(userMap ->{
						userMap.setNombre(product.getNombre());
						userMap.setPrecio(product.getPrecio());
						userMap.setDescripcion(product.getDescripcion());
						userMap.setMaterial(product.getMaterial());
						userMap.setTipo(product.getTipo());
						userMap.setCategoria(product.getCategoria());
						userMap.setImagen(product.getImagen());
						userMap.setStock(product.getStock());
						
						return repository.save(userMap);
					})
					.orElseGet(()-> {
						product.setId(id);
						return repository.save(product);
					});
		}
		
		
	
}//ProductService
