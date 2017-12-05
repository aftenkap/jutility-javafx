package org.jutility.javafx.filter;



// @formatter:off
/*
 * #%L
 * jutility-javafx
 * %%
 * Copyright (C) 2013 - 2014 jutility.org
 * %%
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 * 
 *      http://www.apache.org/licenses/LICENSE-2.0
 * 
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 * #L%
 */
// @formatter:on


import java.util.function.Predicate;

import javafx.beans.property.BooleanProperty;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.util.StringConverter;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


/**
 * The {@code StringFilter} class provides a configurable filter
 * {@link Predicate} for the comparison of objects of a provided type with a
 * filter string.
 * <p>
 * The comparison of objects can be case sensitive (by default) or ignore case.
 * </p>
 * <p>
 * Furthermore, the filter string can be treated as a regular expression.
 * </p>
 *
 * @param <T>
 *            the type of the objects to be filtered.
 *
 * @author Peter J. Radics
 * @version 0.1.2
 * @since 0.1.2
 */
public class StringFilter<T>
        implements Predicate<T> {

    private static final Logger                      LOG = LoggerFactory
                                                                 .getLogger(StringFilter.class);

    private final ObjectProperty<StringConverter<T>> converterProperty;

    private final StringProperty                     filterStringProperty;

    private final BooleanProperty                    caseSensitiveProperty;

    private final BooleanProperty                    regexProperty;

    /**
     * Returns the converter property.
     * <p>
     * If a {@link StringConverter} is provided, it will be used to provide the
     * string value to be compared to the filter string. Otherwise, the
     * {@link #toString()} method is used.
     * </p>
     *
     * @see #getConverter()
     * @see #setConverter(StringConverter)
     *
     * @return the converter property.
     */
    public ObjectProperty<StringConverter<T>> converterProperty() {

        return this.converterProperty;
    }

    /**
     * Returns the value of the {@link #converterProperty()}.
     *
     * @return the value of the {@link #converterProperty()}
     */
    public StringConverter<T> getConverter() {

        return this.converterProperty.get();
    }

    /**
     * Sets the value of the {@link #converterProperty()}.
     *
     * @param value
     *            the value of the {@link #converterProperty()}.
     */
    public void setConverter(final StringConverter<T> value) {

        this.converterProperty.set(value);
    }

    /**
     * Returns the filter string property.
     * <p>
     * The value of the filter string property is used for the comparison in the
     * {@link #test(Object)} method.
     * </p>
     *
     * @see #getFilterString()
     * @see #setFilterString(String)
     *
     * @return the filter string property.
     */
    public StringProperty filterStringProperty() {

        return this.filterStringProperty;
    }

    /**
     * Returns the value of the {@link #filterStringProperty()}.
     *
     * @return the value of the {@link #filterStringProperty()}.
     */
    public String getFilterString() {

        return this.filterStringProperty.get();
    }

    /**
     * Sets the value of the {@link #filterStringProperty()}.
     *
     * @param value
     *            the value of the {@link #filterStringProperty()}.
     */
    public void setFilterString(final String value) {

        this.filterStringProperty.set(value);
    }

    /**
     * Returns the case sensitive property.
     * <p>
     * The value of this property determines whether or not the comparison
     * between the {@link #filterStringProperty() filter string} and an object's
     * string value is case sensitive.
     * </p>
     * <p>
     * Note that the value of this property is ignored if the filter string is
     * to be treated as a regular expression!
     * </p>
     *
     * @see #isCaseSensitive()
     * @see #setCaseSensitive(boolean)
     *
     * @return the case sensitive property.
     */
    public BooleanProperty caseSensitiveProperty() {

        return this.caseSensitiveProperty;
    }

    /**
     * Returns the value of the {@link #caseSensitiveProperty()}.
     *
     * @return the value of the {@link #caseSensitiveProperty()}.
     */
    public boolean isCaseSensitive() {

        return this.caseSensitiveProperty.get();
    }

    /**
     * Sets the value of the {@link #caseSensitiveProperty()}.
     *
     * @param value
     *            the value of the {@link #caseSensitiveProperty()}.
     */
    public void setCaseSensitive(final boolean value) {

        this.caseSensitiveProperty.set(value);
    }

    /**
     * Returns the regex property. The value of this property determines whether
     * or not the {@link #filterStringProperty() filter string} is treated as a
     * regular expression.
     * <p>
     * Note that the value of the {@link #caseSensitiveProperty()} is ignored if
     * the filter string is to be treated as a regular expression!
     * </p>
     *
     * @see #isRegexFilterString()
     * @see #setRegex(boolean)
     *
     * @return the regex property.
     */
    public BooleanProperty regexFilterStringProperty() {

        return this.regexProperty;
    }

    /**
     * Returns the value of the {@link #regexFilterStringProperty()}.
     *
     * @return the value of the {@link #regexFilterStringProperty()}.
     */
    public boolean isRegexFilterString() {

        return this.regexProperty.get();
    }

    /**
     * Sets the value of the {@link #regexFilterStringProperty()}.
     *
     * @param value
     *            the value of the {@link #regexFilterStringProperty()}.
     */
    public void setRegex(final boolean value) {

        this.regexProperty.set(value);
    }

    /**
     * Creates a new instance of the {@link StringFilter} class.
     */
    public StringFilter() {

        this.converterProperty = new SimpleObjectProperty<>();
        this.filterStringProperty = new SimpleStringProperty();
        this.caseSensitiveProperty = new SimpleBooleanProperty(true);
        this.regexProperty = new SimpleBooleanProperty(false);
    }

    @Override
    public boolean test(final T value) {

        StringFilter.LOG.debug("Testing " + value);


        if ((this.getFilterString() == null)
                || this.getFilterString().isEmpty()) {

            StringFilter.LOG.debug("Filter string empty -> true");
            return true;
        }

        String stringValue;

        if (this.getConverter() == null) {

            if (value == null) {

                stringValue = "";
            }
            else {

                stringValue = value.toString();
            }
        }
        else {

            try {

                stringValue = this.getConverter().toString(value);
            }
            catch (final Exception e) {

                stringValue = "";
            }
        }

        if ((stringValue == null) || stringValue.isEmpty()) {

            StringFilter.LOG.debug("string value is empty -> false");
            return false;
        }

        String filterString = this.getFilterString();

        if (this.isRegexFilterString()) {

            StringFilter.LOG.debug("String value matches regex: "
                    + stringValue.matches(filterString));
            return stringValue.matches(filterString);
        }
        else {

            if (!this.isCaseSensitive()) {

                filterString = filterString.toLowerCase();
                stringValue = stringValue.toLowerCase();
            }

            StringFilter.LOG.debug("String value contains filterString: "
                    + stringValue.contains(filterString));
            return stringValue.contains(filterString);
        }
    }
}
