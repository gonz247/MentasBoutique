package com.mentasBoutique.mentasUser.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mentasBoutique.mentasUser.exceptions.UserNotFoundException;
import com.mentasBoutique.mentasUser.model.User;
import com.mentasBoutique.mentasUser.repository.UserRepository;

@Service
public class UserService {

	//Vamos a traer a JPA para que me brinde los metodos para recuperar los datos
		private final UserRepository repository;
		
		//constructor permite la inyeccion de dependencias
		@Autowired
		public UserService(UserRepository repository) {
			this.repository = repository;
		}
		
		
		//metodo de tipo get para traer a todos los usuarios
		public List<User> allUser(){
			return repository.findAll();
		}
		
		//Crear método de tipo get para traer un usuario por id, si no encuentra al usuario arroja una exception (default o personalizada)
			public User getOne(Long id) {
				return repository.findById(id)
						.orElseThrow(() -> new UserNotFoundException(id)); //Estoy creando una exception personalizada que vivirá en otra clase con el mismo nombre de la exception
			}
		
			//Crear metodo para eliminar un usuario por id. Primero debemos comprobar que el usuario con el id realmente existe, en caso de existir se elimina, si no, se lanza una exception por default (illegalSatateException) o perzonalizada
			public void deleteUser(Long id) {
				if (repository.existsById(id)) {
					repository.deleteById(id);
				}
				else {
					throw new UserNotFoundException(id);
				}
			}
			
			/*Post, crear un nuevo usuario. 
			 * Primero tengo que consultar la DB para saber si el usuario existe. Por ello, debo definir de qué manera buscaré a mi usuario. En este caso será mediante email.
			 * Si el usuario con un email asociado existe, no puede crear un nuevo usuario con el mismo email.
			 * Si no hay un usuario con el email asociado, se crea un nuevo usuario.
			 * Tenemos que definir la búsqueda específica por email (findByEmail) utilizando una consulta de tipo JPQL, la cual se realiza dentro la interface UserRepository ya que ahí se implementan todas los métodos de JPA
			 */
			
			public User addUser(User user) {
				User existingUser = repository.findByEmail(user.getEmail());
				if(existingUser != null) {
					throw new IllegalStateException("Este correo ya esta asociado a una cuenta existente");
				}return repository.save(user);
			}
			
			//metodo PUT
			/*
			 * Debemos iterar cada key/value del objeto para observar las modificaciones y aplicarlas en el atributo
			 * especifico. Para ello usaremos el metodo .map
			 */
			public User replaceUser(User user, Long id) {
				return repository.findById(id)
						.map(userMap ->{
							userMap.setNombre(user.getNombre());
							userMap.setApellido(user.getApellido());
							userMap.setTelefono(user.getTelefono());
							userMap.setEmail(user.getEmail());
							userMap.setPassword(user.getPassword());
							userMap.setRol(user.getRol());
							return repository.save(userMap);
						})
						.orElseGet(()-> {
							user.setId(id);
							return repository.save(user);
						});
			}
			
			
			/*
			 * Podemos buscar un usuario por medio de su correo para recuperar informacion. Para ello dependemos de JPQL en el repository y de ResponseEntity que se ejecutara en el controler
			 * Vamos a crear un metodo para buscar a un usuario por email (getUserbyEmail)
			 * 
			 */
			public User getUserByEmail(String email) {
				return repository.findByEmail(email);
			}
			
			   

			public boolean validatePassword(String email, String password) {
		        User user = repository.findByEmail(email);
		        if (user != null) {
		            // Compare the raw password with the hashed password stored in the database
		            return password.equals(user.getPassword());
		        }
		        return false; // User not found or password doesn't match
		    }
			
			
	
	
	
}
