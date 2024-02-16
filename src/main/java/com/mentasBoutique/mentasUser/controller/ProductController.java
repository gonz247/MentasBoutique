package com.mentasBoutique.mentasUser.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.mentasBoutique.mentasUser.model.Product;
import com.mentasBoutique.mentasUser.service.ProductService;



@RestController //indica que la clase es una API es de tipo REST(trabaja con metodos HTTP)
@RequestMapping("admin") //construimos la ruta de neustra api, definiendo endpoints (Localhost:8080/admin/user)
@CrossOrigin(origins="*", methods={RequestMethod.GET, RequestMethod.POST, RequestMethod.PUT, RequestMethod.DELETE})//asi se define que culauier host o url jale el asterisco significa todos
public class ProductController {

private ProductService productService;
	
	//generar el constructor
	@Autowired //inyeccion de dependencias
	public ProductController(ProductService productService) {
		this.productService = productService;
	}
	
	//necesitamos decirle a controller que tiene que traer la informacion de UserService usando el metodo definido.
	//Para traer la informacion (READ) necesitamos indicar el tipo de peticion mediante la annotacio @GetMapping
	@GetMapping("products")
	public List<Product> getProduct(){
		return productService.allProduct();
	}
	
	
	//Delete, de tipo void
	@DeleteMapping("products/{id}")
	public void deleteProdcut(@PathVariable(name = "id") Long id) {
		productService.deleteProduct(id);
	}
	
	//Post, crear un nuevo usuario
	/*
	 * en postman tenemos que establecer el cuerpo de la instancia en formato JSON (key/value)y mandar el metodo POST
	 */
	@PostMapping("products")
	public Product newProduct(@RequestBody Product newProduct) {
		return productService.addProduct(newProduct);
	}
	
	//PUT, Modificar/actualizar a un usuario existente
	@PutMapping("products/{id}")
	public Product updateProduct(@RequestBody Product product, @PathVariable(name="id")Long id) {
		return productService.updateProduct(product, id);
	}
	
	/*
	 * -- ResponseEntity<> clase de SpringFramework que representa una respuesta HTTP personalizable. Permite controlar el body de la respuesta. Posee dos parámetros:
	 * 		1. Especifica el tipo de datos
	 * 		2. Especifica el código de estado HTTP `HttpStatus.method`
	 * -- @RequestParam anotación de springFramework que se utiliza para vincular parámetros de solicitud HTTP que se enviará a la respuesta. Es decir, permite controlar las llaves-valores dentro del parámetro  
	 * */

	
	
	
}//ProductController
