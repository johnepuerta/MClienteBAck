package test.mirante.johne.TesteMirante.services;

import java.util.List;
import java.util.Optional;

import javax.persistence.OptimisticLockException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.orm.ObjectOptimisticLockingFailureException;
import org.springframework.stereotype.Service;

import test.mirante.johne.TesteMirante.entities.ClienteEntity;
import test.mirante.johne.TesteMirante.exception.GenericException;
import test.mirante.johne.TesteMirante.repositories.ClienteRepository;

@Service
public class ClienteService {

	@Autowired
	ClienteRepository clienteRepository;

	public List findAll() {
		return clienteRepository.findAll();
	}

	public ResponseEntity<ClienteEntity> findById(Long id) {

		Optional<ClienteEntity> optionalIP = clienteRepository.findById(id);

		if(optionalIP.isPresent()) {
			return ResponseEntity.ok(optionalIP.get());
		}
		return ResponseEntity.notFound().build();
    }

	public ResponseEntity<ClienteEntity> create(ClienteEntity cliente){
		return ResponseEntity.ok(clienteRepository.save(cliente));
	}

	public ResponseEntity<Void> atualizar(ClienteEntity cliente){
		try {
			clienteRepository.save(cliente);
			return ResponseEntity.ok().build();
		}catch (OptimisticLockException | ObjectOptimisticLockingFailureException e) {
			throw new GenericException("Registro manipulado por outro usuaário.");
		}catch (Exception e) {
			throw new GenericException("Registro manipulado por outro usuaário.");
		}
	}

	public ResponseEntity<Void> excluir(Long id) {
		clienteRepository.deleteById(id);
		return ResponseEntity.ok().build();
	}

	static ClienteService serviceSingleton = null;
	public static ClienteService getInstance() {
		if(serviceSingleton == null) {
			serviceSingleton = new ClienteService();
		}
		return serviceSingleton;
	}

	public String getClienteById(Long id) {

		Optional<ClienteEntity> optCliente = clienteRepository.findById(id);
		if(optCliente.isPresent()) {
			return optCliente.get().toString();
		}
		return "";
	}

}
