package test.mirante.johne.TesteMirante.entities;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.Email;

import lombok.Data;

@Entity(name = "Email")
@Table(name = "emails")
@Data
public class EmailEntity {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;

	@Column
	@Email
	String email;

}
