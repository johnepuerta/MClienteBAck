package test.mirante.johne.TesteMirante.entities;

import java.util.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EntityListeners;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.PostPersist;
import javax.persistence.PostRemove;
import javax.persistence.PostUpdate;
import javax.persistence.PrePersist;
import javax.persistence.PreUpdate;
import javax.persistence.Table;
import javax.persistence.Version;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;

import org.hibernate.validator.constraints.Length;
import org.slf4j.LoggerFactory;
import org.springframework.data.annotation.CreatedBy;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedBy;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;
import org.springframework.security.core.context.SecurityContextHolder;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import test.mirante.johne.TesteMirante.TesteMiranteApplication;
import test.mirante.johne.TesteMirante.annotations.UF;

@Entity(name = "Cliente")
@Table(name = "clientes")
@EntityListeners({AuditingEntityListener.class })
@Data
@NoArgsConstructor
@AllArgsConstructor
public class ClienteEntity {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;

	@Column(nullable = false)
	@NotNull(message = "Nome deve ser infromado")
	@Length(min=3, max=100, message = "Nomde deve conter no mínimo 3 caractesres e no máximo 100")
	@Pattern(regexp = "^[a-zA-Z0-9.\\-\\/ ]*$")
	private String nome;

	@Column(nullable = false)
	@NotNull(message = "CPF deve ser infromado")
	@Min(value = 1, message = "CPF inválido")
	private Long cpf;

	@Column(nullable = false)
	@NotNull(message = "CEP deve ser infromado")
	@Min(value = 1, message = "CEP inválido")
	@Max(value = 99999999, message = "CEP inválido")
	private int cep;

	@Column(nullable = false)
	@NotEmpty(message = "Logradouro deve ser infromado")
	private String logradouro;

	@Column(nullable = false)
	@NotEmpty(message = "Logradouro deve ser infromado")
	private String bairro;

	@Column(nullable = false)
	@NotEmpty(message = "Cidade deve ser infromado")
	private String cidade;

	@Column(nullable = false, length = 2)
	@UF
	private String uf;

	@Column
	private String complemento;

	@OneToMany(cascade = CascadeType.ALL)
	@NotEmpty(message = "Deve ser informado ao menos um telefone")
	private List<TelefoneEntity> telefones;

	@OneToMany(cascade = CascadeType.ALL)
	@NotEmpty(message = "Deve ser informado ao menos um e-mail")
	private List<EmailEntity> emails;

	@Version
	private Integer version;


	@Column(name = "created_date", nullable = false, updatable = false)
	@CreatedDate
	private Date createdDate;

	@Column(name = "modified_date")
	@LastModifiedDate
	private Date modifiedDate;

	@Column(name = "created_by")
	@CreatedBy
	private String createdBy;

	@Column(name = "modified_by")
	@LastModifiedBy
	private String modifiedBy;

	@PostRemove
	void logRemove() {
		LoggerFactory.getLogger(TesteMiranteApplication.class).warn(getUser() +" excluiu cliente" + this.toString());
	}

	@PostPersist
	@PostUpdate
	void logPost() {
		LoggerFactory.getLogger(TesteMiranteApplication.class).warn(getUser() +" Cliente após salvar: " + this.toString());
	}

	String getUser() {
		return "(Usuário:" + SecurityContextHolder.getContext().getAuthentication().getName() + ")";
	}

}
