package org.jutility.javafx.stringconverter;


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
