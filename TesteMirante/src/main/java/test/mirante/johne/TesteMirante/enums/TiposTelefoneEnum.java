package test.mirante.johne.TesteMirante.enums;

import java.util.Arrays;

public enum TiposTelefoneEnum {

	RESIDENCIAL,
	COMERCIAL,
	CELULAR;

	public static boolean isTipoValido(String tipo) {
		return Arrays.stream(TiposTelefoneEnum.values()).filter((tp) -> tp.toString().equalsIgnoreCase(tipo)).count() > 0;
	}
}
