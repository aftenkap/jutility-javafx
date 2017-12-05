package org.jutility.javafx.stringconverter;


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


import javafx.beans.property.ObjectProperty;
import javafx.util.StringConverter;



/**
 * The {@code IConfigurableStringConverter} interface provides a contract for
 * configurable {@link StringConverter StringConverters}.
 *
 * @param <T>
 *            the type of the string converter.
 *
 * @author Peter J. Radics
 * @version 0.1.2
 * @since 0.1.0
 */
public interface IConfigurableStringConverter<T> {


    /**
     * Returns the configuration property.
     *
     * @return the configuration property.
     */
    ObjectProperty<? extends IStringConverterConfiguration> configuration();

    /**
     * Sets the configuration of the string converter.
     *
     * @param configuration
     *            the configuration.
     */
    void configure(final IStringConverterConfiguration configuration);

    /**
     * Returns the configuration of the string converter.
     *
     * @return the configuration.
     */
    IStringConverterConfiguration getConfiguration();
}
