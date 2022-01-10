package test.mirante.johne.TesteMirante.enums;

import java.util.Arrays;

public enum EstadosEnum {

	AC,
	AL,
	AP,
	AM,
	BA,
	CE,
	ES,
	GO,
	MA,
	MT,
	MS,
	MG,
	PA,
	PB,
	PR,
	PE,
	PI,
	RJ,
	RN,
	RS,
	RO,
	RR,
	SC,
	SP,
	SE,
	TO,
	DF;


	public static boolean isUFvalido(String sigla) {
		return Arrays.stream(EstadosEnum.values()).filter((uf) -> uf.toString().equals(sigla)).count() > 0;
	}
}
