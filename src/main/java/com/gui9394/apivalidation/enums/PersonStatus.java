package com.gui9394.apivalidation.enums;

import java.util.stream.Stream;

public enum PersonStatus {
    ENABLED(666), DISABLED(667);

    private Integer value;

    PersonStatus(Integer value) {
        this.value = value;
    }

    // @JsonValue
    public Integer getValue() {
        return this.value;
    }

    // @JsonCreator
    public static PersonStatus valueOf(int value) {
        return Stream.of(PersonStatus.values())
          .filter(c -> c.getValue().equals(value))
          .findFirst()
          .orElseThrow(IllegalArgumentException::new);


        // for (PersonStatus status : PersonStatus.values()) {
        //     if (status.getValue() == value) {
        //         return status;
        //     }
        // }

        // throw new IllegalArgumentException("No enum constant " + PersonStatus.class.getCanonicalName() + " by value " + value);
    }
}
