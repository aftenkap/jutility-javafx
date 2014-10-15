package org.jutility.javafx.stringconverter;

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


import javafx.beans.property.ObjectProperty;
import javafx.util.StringConverter;



/**
 * The {@link IConfigurableStringConverter} interface provides a contract for
 * configurable {@link StringConverter StringConverters}.
 * 
 * @author Peter J. Radics
 * @version 0.1
 * 
 * @param <T>
 *            the type of the string converter.
 */
public interface IConfigurableStringConverter<T> {


    /**
     * Returns the configuration property.
     * 
     * @return the configuration property.
     */
    public ObjectProperty<? extends IStringConverterConfiguration> configuration();

    /**
     * Sets the configuration of the string converter.
     * 
     * @param configuration
     *            the configuration.
     */
    public abstract void configure(IStringConverterConfiguration configuration);

    /**
     * Returns the configuration of the string converter.
     * 
     * @return the configuration.
     */
    public abstract IStringConverterConfiguration getConfiguration();
}
