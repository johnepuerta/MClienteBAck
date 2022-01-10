package test.mirante.johne.TesteMirante.rest;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import javax.validation.Valid;

import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import test.mirante.johne.TesteMirante.TesteMiranteApplication;
import test.mirante.johne.TesteMirante.entities.ClienteEntity;
import test.mirante.johne.TesteMirante.services.ClienteService;

@RestController
@CrossOrigin(origins = "http://localhost:4200")
@RequestMapping(path = "/clientes")
public class ClientesController {

	@Autowired
	private ClienteService clienteService;

	@GetMapping
    public List getEmployees(){
		List findAll = clienteService.findAll();
		LoggerFactory.getLogger(TesteMiranteApplication.class).warn(getUser() +" solicitou listagem de clientes");
		return findAll;
    }

	@GetMapping("{id}")
	public ResponseEntity<ClienteEntity> findById(@PathVariable Long id) {
		ResponseEntity<ClienteEntity> cliente = clienteService.findById(id);
		LoggerFactory.getLogger(TesteMiranteApplication.class).warn(getUser() +" solicitou alteração do cliente" + cliente.toString());
		return cliente;
    }

	@PostMapping
	@ResponseStatus(HttpStatus.CREATED)
	public ResponseEntity<ClienteEntity> create(@Valid @RequestBody ClienteEntity cliente){
		return clienteService.create(cliente);
	}


	@PutMapping
	public ResponseEntity<String> atualizar(@RequestBody ClienteEntity cliente){
        return new ResponseEntity<>("teste johne", HttpStatus.BAD_REQUEST);

		//return clienteService.atualizar(cliente);
	}

	@DeleteMapping("{id}")
	public ResponseEntity<Void> excluir(@PathVariable Long id) {
		return clienteService.excluir(id);
	}

	String getUser() {
		return "(Usuário:" + SecurityContextHolder.getContext().getAuthentication().getName() + ")";
	}

}
