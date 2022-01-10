package test.mirante.johne.TesteMirante.annotations;

import static java.lang.annotation.ElementType.FIELD;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

import java.lang.annotation.Retention;
import java.lang.annotation.Target;

import javax.validation.Constraint;
import javax.validation.Payload;

import test.mirante.johne.TesteMirante.annotations.validators.TipoTelefoneValidator;

@Retention(RUNTIME)
@Target(FIELD)
@Constraint(validatedBy = TipoTelefoneValidator.class)
public @interface TipoFone {

	String message() default "Tipo de telefone inválido";

	public Class<?>[] groups() default {};

    public Class<? extends Payload>[] payload() default {};

}
