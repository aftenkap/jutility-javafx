package org.jutility.io.xml;


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

import javafx.scene.paint.Color;

import javax.xml.bind.annotation.adapters.XmlAdapter;


/**
 * The {@code XmlColorTypeAdapter} class provides an adapter for XML
 * serialization of {@link Color Colors}.
 *
 * @author Peter J. Radics
 * @version 0.1.2
 * @since 0.1.0
 */
public class XmlColorTypeAdapter
        extends XmlAdapter<String, Color> {

    @Override
    public String marshal(Color color)
            throws Exception {

        return color != null ? color.toString() : null;
    }

    @Override
    public Color unmarshal(String serializedColor)
            throws Exception {

        return serializedColor != null ? Color.web(serializedColor) : null;
    }
}
