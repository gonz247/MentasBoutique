package com.mentasBoutique.mentasUser.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.mentasBoutique.mentasUser.model.User;
import com.mentasBoutique.mentasUser.service.UserService;

@RestController //indica que la clase es una API es de tipo REST(trabaja con metodos HTTP)
@RequestMapping("admin") //construimos la ruta de neustra api, definiendo endpoints (Localhost:8080/admin/user)
@CrossOrigin(origins="*", methods={RequestMethod.GET, RequestMethod.POST, RequestMethod.PUT, RequestMethod.DELETE})//asi se define que culauier host o url jale el asterisco significa todos
public class UserController {
	//instanciar desde UserService mediante un nuevo objeto de tipo UserService userService
	private UserService userService;
	
	//generar el constructor
	@Autowired //inyeccion de dependencias
	public UserController(UserService userService) {
		this.userService = userService;
	}
	
	//necesitamos decirle a controller que tiene que traer la informacion de UserService usando el metodo definido.
	//Para traer la informacion (READ) necesitamos indicar el tipo de peticion mediante la annotacio @GetMapping
	@GetMapping("users")
	public List<User> getUser(){
		return userService.allUser();
	}
	
	//path variable, ya que buscamos por id y los id son diferentes, entonces necesito definir el endpoint entre llaves y dentro del metodo debo crear una anotacion @PathVariable.
	@GetMapping("users/{id}")
	public User getOne(@PathVariable(name = "id") Long id) {
		return userService.getOne(id);
	}
	
	//Delete, de tipo void
	@DeleteMapping("users/{id}")
	public void deleteUser(@PathVariable(name = "id") Long id) {
		userService.deleteUser(id);
	}
	
	//Post, crear un nuevo usuario
	/*
	 * en postman tenemos que establecer el cuerpo de la instancia en formato JSON (key/value)y mandar el metodo POST
	 */
	@PostMapping("users")
	public User newUser(@RequestBody User newUser) {
		return userService.addUser(newUser);
	}
	
	//PUT, Modificar/actualizar a un usuario existente
	@PutMapping("users/{id}")
	public User replaceUser(@RequestBody User user, @PathVariable(name="id")Long id) {
		return userService.replaceUser(user, id);
	}
	
	/*
	 * -- ResponseEntity<> clase de SpringFramework que representa una respuesta HTTP personalizable. Permite controlar el body de la respuesta. Posee dos parámetros:
	 * 		1. Especifica el tipo de datos
	 * 		2. Especifica el código de estado HTTP `HttpStatus.method`
	 * -- @RequestParam anotación de springFramework que se utiliza para vincular parámetros de solicitud HTTP que se enviará a la respuesta. Es decir, permite controlar las llaves-valores dentro del parámetro  
	 * */
	//metodo para traer un usuario por Email
	@GetMapping("users/byEmail")
	public ResponseEntity<User> getUserByeEmail(@RequestParam(name="email") String email){
		User tempUser = userService.getUserByEmail(email);
		return  new ResponseEntity<>(tempUser,HttpStatus.OK);
	}
	

	

	
	
}