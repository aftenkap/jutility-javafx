package org.jutility.javafx.control.validation;


// @formatter:off
/*
 * #%L 
 * jutility-javafx 
 * %% 
 * Copyright (C) 2013 - 2014 jutility.org 
 * %% 
 * Licensed under the Apache License, Version 2.0 (the "License"); you may not 
 * use this file except in compliance with the License. You may obtain a copy 
 * of the License at
 * 
 * http://www.apache.org/licenses/LICENSE-2.0
 * 
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS, WITHOUT
 * WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied. See the
 * License for the specific language governing permissions and limitations under
 * the License. 
 * #L%
 */

//@formatter:on


import java.net.URI;
import java.util.Arrays;

import org.controlsfx.validation.Severity;
import org.controlsfx.validation.ValidationResult;
import org.controlsfx.validation.Validator;
import org.jutility.common.datatype.util.NumberComparator;
import org.jutility.common.datatype.util.NumberUtils;


/**
 * @author Peter J. Radics
 * @version 0.1.2
 * @since 0.1.0
 *
 */
public class ValidationUtils {


    /**
     * Factory method to create a validator, which checks whether the string
     * representation of a value represents a valid boolean.
     * 
     * @param message
     *            text of a message to be created if value is invalid
     * @param severity
     *            severity of a message to be created if value is invalid
     * @return new validator
     */
    public static <T> Validator<T> createBooleanFormatValidator(
            final String message, final Severity severity) {

        return (control, value) -> {

            boolean invalidValue = value == null;

            if (!invalidValue) {

                invalidValue = !"true".equalsIgnoreCase(value.toString())
                        && !"false".equalsIgnoreCase(value.toString());
            }
            return ValidationResult.fromMessageIf(control, message, severity,
                    invalidValue);
        };
    }

    /**
     * Factory method to create a validator, which checks whether the string
     * representation of a value represents a valid boolean.
     * 
     * @param message
     *            text of a message to be created if value is invalid.
     * @return new validator.
     */
    public static <T> Validator<T> createBooleanFormatValidator(
            final String message) {

        return ValidationUtils.createBooleanFormatValidator(message,
                Severity.ERROR);
    }


    /**
     * Factory method to create a validator, which checks whether the string
     * representation of a value represents a valid instance of the provided
     * number type.
     * 
     * @param type
     *            the number type to check for.
     * @param message
     *            text of a message to be created if value is invalid
     * @param severity
     *            severity of a message to be created if value is invalid
     * @return new validator
     */
    public static <T> Validator<T> createNumberFormatValidator(
            final Class<? extends Number> type, final String message,
            final Severity severity) {

        return (control, value) -> {

            boolean condition = value == null;

            if (!condition) {

                try {
                    if (type == Byte.class) {

                        Byte.parseByte(value.toString());
                    }
                    else if (type == Double.class) {

                        Double.parseDouble(value.toString());
                    }
                    else if (type == Float.class) {

                        Float.parseFloat(value.toString());
                    }
                    else if (type == Integer.class) {

                        Integer.parseInt(value.toString());
                    }
                    else if (type == Long.class) {

                        Long.parseLong(value.toString());
                    }
                    else if (type == Short.class) {

                        Short.parseShort(value.toString());
                    }
                }
                catch (NumberFormatException e) {

                    condition = true;
                }
            }

            return ValidationResult.fromMessageIf(control, message, severity,
                    condition);
        };
    }


    /**
     * Factory method to create a validator, which checks whether the string
     * representation of a value represents a valid instance of the provided
     * number type.
     * 
     * @param type
     *            the number type to check for.
     * @param message
     *            text of a message to be created if value is invalid
     * @return new validator
     */
    public static <T> Validator<T> createNumberFormatValidator(
            final Class<? extends Number> type, final String message) {

        return ValidationUtils.createNumberFormatValidator(type, message,
                Severity.ERROR);
    }

    /**
     * Factory method to create a validator, which checks whether the string
     * representation of a value represents a valid instance of the provided
     * number type.
     * <p/>
     * Note: if the value does not evaluate to a valid number type, the
     * validator evaluates to {@code false}.
     * 
     * @param min
     *            the lower boundary of the range.
     * @param max
     *            the upper boundary of the range.
     * @param includeMin
     *            whether to include the lower boundary of the range (i.e.,
     *            smaller or equal).
     * @param includeMax
     *            whether to include the upper boundary of the range (i.e.,
     *            greater or equal).
     * @param message
     *            text of a message to be created if value is invalid
     * @return new validator
     */
    public static <T, S extends Number> Validator<T> createNumberRangeValidator(
            final S min, final S max, boolean includeMin, boolean includeMax,
            final String message) {

        return ValidationUtils.createNumberRangeValidator(min, max, includeMin,
                includeMax, message, Severity.ERROR);
    }


    /**
     * Factory method to create a validator, which checks whether the string
     * representation of a value represents a valid instance of the provided
     * number type.
     * <p/>
     * Note: if the value does not evaluate to a valid number type, the
     * validator evaluates to {@code false}.
     * 
     * @param min
     *            the lower boundary of the range.
     * @param max
     *            the upper boundary of the range.
     * @param includeMin
     *            whether to include the lower boundary of the range (i.e.,
     *            smaller or equal).
     * @param includeMax
     *            whether to include the upper boundary of the range (i.e.,
     *            greater or equal).
     * @param message
     *            text of a message to be created if value is invalid
     * @param severity
     *            severity of a message to be created if value is invalid
     * @return new validator
     */
    public static <T, S extends Number> Validator<T> createNumberRangeValidator(
            final S min, final S max, boolean includeMin, boolean includeMax,
            final String message, final Severity severity) {

        return (control, value) -> {

            boolean condition = value == null;

            if (!condition) {


                Class<? extends Number> type = NumberComparator
                        .greatestPrecisionType(Arrays.asList(min.getClass(),
                                max.getClass()));

                try {

                    Number numberValue = NumberUtils.parse(value.toString(),
                            type);

                    if (includeMin && includeMax) {

                        condition = !(NumberComparator.smallerOrEqual(min,
                                numberValue) && NumberComparator
                                .greaterOrEqual(max, numberValue));
                    }
                    else if (includeMin) {

                        condition = !(NumberComparator.smallerOrEqual(min,
                                numberValue) && NumberComparator.greaterThan(
                                max, numberValue));
                    }
                    else if (includeMax) {

                        condition = !(NumberComparator.smallerThan(min,
                                numberValue) && NumberComparator
                                .greaterOrEqual(max, numberValue));
                    }
                    else {

                        condition = !(NumberComparator.smallerThan(min,
                                numberValue) && NumberComparator.greaterThan(
                                max, numberValue));
                    }

                }
                catch (NumberFormatException e) {

                    condition = true;
                }
            }

            return ValidationResult.fromMessageIf(control, message, severity,
                    condition);
        };
    }

    /**
     * Factory method to create a validator, which checks if the string
     * representation of a value represents a valid URI.
     * 
     * @param message
     *            text of a message to be created if value is invalid.
     * @param severity
     *            severity of a message to be created if value is invalid.
     * @return new validator
     */
    public static <T> Validator<T> createURIFormatValidator(
            final String message, final Severity severity) {

        return (control, value) -> {

            boolean condition = value == null;

            if (!condition) {

                try {

                    URI.create(value.toString());
                }
                catch (Exception e) {

                    condition = true;
                }
            }

            return ValidationResult.fromMessageIf(control, message, severity,
                    condition);
        };
    }

    /**
     * Factory method to create a validator, which checks if the string
     * representation of a value represents a valid URI.
     * 
     * @param message
     *            text of a message to be created if value is invalid.
     * @return new validator.
     */
    public static <T> Validator<T> createURIFormatValidator(final String message) {

        return ValidationUtils
                .createURIFormatValidator(message, Severity.ERROR);
    }

}
