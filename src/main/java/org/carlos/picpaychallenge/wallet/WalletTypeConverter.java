package org.carlos.picpaychallenge.wallet;

import jakarta.persistence.AttributeConverter;
import jakarta.persistence.Converter;

@Converter(autoApply = true)
public class WalletTypeConverter implements AttributeConverter<WalletType, Integer> {

    @Override
    public Integer convertToDatabaseColumn(WalletType walletType) {
        if (walletType == null) {
            return null;
        }
        return walletType.getType();
    }

    @Override
    public WalletType convertToEntityAttribute(Integer dbData) {
        if (dbData == null) {
            return null;
        }
        for (WalletType walletType : WalletType.values()) {
            if (walletType.getType().equals(dbData)) {
                return walletType;
            }
        }
        throw new IllegalArgumentException("Unknown database value: " + dbData);
    }
}
