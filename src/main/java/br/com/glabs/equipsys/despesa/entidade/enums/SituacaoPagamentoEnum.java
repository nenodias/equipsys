package br.com.glabs.equipsys.despesa.entidade.enums;

import java.util.Arrays;
import java.util.function.Predicate;

public enum SituacaoPagamentoEnum {
    PAGO,
    NAO_PAGO;

    public static SituacaoPagamentoEnum findByName(final String name){
        final Predicate<SituacaoPagamentoEnum> predicate = (SituacaoPagamentoEnum i) -> name.equalsIgnoreCase(i.name());
        return Arrays.stream(SituacaoPagamentoEnum.values()).filter(predicate).findAny()
                .orElse(NAO_PAGO);
    }
}
