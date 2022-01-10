package test.mirante.johne.TesteMirante.entities;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.Data;
import test.mirante.johne.TesteMirante.annotations.TipoFone;

@Entity(name = "Telefone")
@Table(name = "telefones")
@Data
public class TelefoneEntity {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;

	@Column(nullable = false)
	@TipoFone
	String tipo;

	@Column(nullable = false)
	int ddd;

	@Column(nullable = false)
	int numero;

}
