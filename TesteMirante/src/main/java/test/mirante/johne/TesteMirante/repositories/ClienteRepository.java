package test.mirante.johne.TesteMirante.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import test.mirante.johne.TesteMirante.entities.ClienteEntity;

@Repository
public interface ClienteRepository extends JpaRepository<ClienteEntity, Long>  {

}
