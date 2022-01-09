package br.com.glabs.equipsys.despesa.entidade.enums;

import java.util.Arrays;
import java.util.function.Predicate;

public enum TipoDespesaEnum {
    PARCELA,
    DESPESA_FIXA;

    public static TipoDespesaEnum findByName(final String name){
        final Predicate<TipoDespesaEnum> predicate = (TipoDespesaEnum i) -> name.equalsIgnoreCase(i.name());
        return Arrays.stream(TipoDespesaEnum.values()).filter(predicate).findAny()
                .orElse(DESPESA_FIXA);
    }
}
