/**
 * 
 */
package org.jutility.javafx.control.validated;


import java.net.URI;
import java.net.URISyntaxException;
import java.util.Arrays;

import org.controlsfx.validation.Severity;
import org.controlsfx.validation.ValidationResult;
import org.controlsfx.validation.Validator;
import org.jutility.common.datatype.util.NumberComparator;
import org.jutility.common.datatype.util.NumberUtils;


/**
 * @author Peter J. Radics
 * @version 0.1
 * @since 0.1
 *
 */
public class ValidationUtils {


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
    @SuppressWarnings("unused")
    public static <T> Validator<T> createURIFormatValidator(
            final String message, final Severity severity) {

        return (control, value) -> {

            boolean condition = value == null;

            if (!condition) {

                try {

                    new URI(value.toString());
                }
                catch (URISyntaxException e) {

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
