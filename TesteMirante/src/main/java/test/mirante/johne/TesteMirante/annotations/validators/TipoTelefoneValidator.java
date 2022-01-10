package test.mirante.johne.TesteMirante.annotations.validators;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

import test.mirante.johne.TesteMirante.annotations.TipoFone;
import test.mirante.johne.TesteMirante.enums.TiposTelefoneEnum;

public class TipoTelefoneValidator implements ConstraintValidator<TipoFone, String> {

	@Override
	public boolean isValid(String value, ConstraintValidatorContext context) {
		return TiposTelefoneEnum.isTipoValido(value);
	}

}
