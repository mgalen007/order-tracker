package com.galen.ordertracker.common.validation;

/** Shared validation limits. Lengths mirror the column sizes in the database schema. */
public final class ValidationRules {

    private ValidationRules() {}

    public static final String PHONE_PATTERN = "^\\+?[0-9]{9,15}$";

    public static final int NAME_MAX = 100;
    public static final int EMAIL_MAX = 100;
    public static final int PHONE_MAX = 16;
    public static final int STREET_MAX = 100;
    public static final int CITY_MAX = 100;
    public static final int DESCRIPTION_MAX = 500;
    public static final int POSTAL_CODE_MAX = 10;
}
