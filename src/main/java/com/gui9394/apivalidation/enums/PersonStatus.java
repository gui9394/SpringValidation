package com.gui9394.apivalidation.enums;

import java.util.stream.Stream;

public enum PersonStatus {
    ENABLED(100), DISABLED(101);

    private Integer value;

    PersonStatus(Integer value) {
        this.value = value;
    }

    public Integer getValue() {
        return this.value;
    }

    public static PersonStatus valueOf(int value) {
        return Stream.of(PersonStatus.values())
                .filter(c -> c.getValue().equals(value))
                .findFirst()
                .orElseThrow(IllegalArgumentException::new);
    }
}
