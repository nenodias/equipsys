package br.com.glabs.equipsys.despesa.entidade.enums;

import java.util.Arrays;
import java.util.function.Predicate;

public enum SituacaoDespesaEnum {
    PAGO,
    PARCIALMENTE_PAGO,
    NAO_PAGO;

    public static SituacaoDespesaEnum findByName(final String name){
        final Predicate<SituacaoDespesaEnum> predicate = (SituacaoDespesaEnum i) -> name.equalsIgnoreCase(i.name());
        return Arrays.stream(SituacaoDespesaEnum.values()).filter(predicate).findAny()
                .orElse(NAO_PAGO);
    }
}
