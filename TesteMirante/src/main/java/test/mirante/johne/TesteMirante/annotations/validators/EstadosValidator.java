package test.mirante.johne.TesteMirante.annotations.validators;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

import test.mirante.johne.TesteMirante.annotations.UF;
import test.mirante.johne.TesteMirante.enums.EstadosEnum;

public class EstadosValidator implements ConstraintValidator<UF, String> {

	@Override
	public boolean isValid(String value, ConstraintValidatorContext context) {

		return EstadosEnum.isUFvalido(value);
	}

}
