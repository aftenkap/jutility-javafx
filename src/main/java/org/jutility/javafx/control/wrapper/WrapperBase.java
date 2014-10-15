/**
 * 
 */
package org.jutility.javafx.control.wrapper;

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


import java.util.LinkedList;

import org.controlsfx.control.action.Action;
import org.controlsfx.control.action.ActionUtils;

import javafx.beans.InvalidationListener;
import javafx.beans.Observable;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ListChangeListener;
import javafx.collections.ObservableList;
import javafx.scene.Node;
import javafx.scene.control.ContextMenu;
import javafx.scene.control.Control;
import javafx.scene.control.Skin;
import javafx.scene.control.Skinnable;
import javafx.scene.control.TextField;
import javafx.scene.control.Tooltip;
import javafx.scene.layout.GridPane;


/**
 * The abstract {@link WrapperBase} class provides base functionality for
 * wrapping a {@link Node} within a 3x3 {@link GridPane}.
 * 
 * @author Peter J. Radics
 * @version 1.0
 * @since 1.0
 * @param <T>
 *            the type of the Node to be wrapped.
 * 
 */
public abstract class WrapperBase<T extends Control>
        extends GridPane {


    private final ObjectProperty<Node>   north;
    private final ObjectProperty<Node>   northEast;
    private final ObjectProperty<Node>   east;
    private final ObjectProperty<Node>   southEast;
    private final ObjectProperty<Node>   south;
    private final ObjectProperty<Node>   southWest;
    private final ObjectProperty<Node>   west;
    private final ObjectProperty<Node>   northWest;

    private final ObjectProperty<T>      wrapped;

    private final ObservableList<Action> contextMenuActions;

    /**
     * Returns the property containing the north {@link Node}.
     * 
     * @return the property containing the north {@link Node}.
     */
    protected ObjectProperty<Node> north() {

        return this.north;
    }

    /**
     * Returns the north {@link Node}.
     * 
     * @return the north {@link Node}.
     */
    protected Node getNorth() {

        return this.north.get();
    }

    /**
     * Sets the north {@link Node}.
     * 
     * @param north
     *            the north {@link Node}.
     */
    protected void setNorth(Node north) {

        this.north.set(north);
    }

    /**
     * Returns the property containing the north-east {@link Node}.
     * 
     * @return the property containing the north-east {@link Node}.
     */
    protected ObjectProperty<Node> northEast() {

        return this.northEast;
    }

    /**
     * Returns the north-east {@link Node}.
     * 
     * @return the north-east {@link Node}.
     */
    protected Node getNorthEast() {

        return this.northEast.get();
    }


    /**
     * Sets the north-east {@link Node}.
     * 
     * @param northEast
     *            the north-east {@link Node}.
     */
    protected void setNorthEast(Node northEast) {

        this.northEast.set(northEast);
    }


    /**
     * Returns the property containing the east {@link Node}.
     * 
     * @return the property containing the east {@link Node}.
     */
    protected ObjectProperty<Node> east() {

        return this.east;
    }

    /**
     * Returns the east {@link Node}.
     * 
     * @return the east {@link Node}.
     */
    protected Node getEast() {

        return this.east.get();
    }


    /**
     * Sets the east {@link Node}.
     * 
     * @param east
     *            the east {@link Node}.
     */
    protected void setEast(Node east) {

        this.east.set(east);
    }


    /**
     * Returns the property containing the south-east {@link Node}.
     * 
     * @return the property containing the south-east {@link Node}.
     */
    protected ObjectProperty<Node> southEast() {

        return this.southEast;
    }

    /**
     * Returns the south-east {@link Node}.
     * 
     * @return the south-east {@link Node}.
     */
    protected Node getSouthEast() {

        return this.southEast.get();
    }


    /**
     * Sets the south-east {@link Node}.
     * 
     * @param southEast
     *            the south-east {@link Node}.
     */
    protected void setSouthEast(Node southEast) {

        this.southEast.set(southEast);
    }

    /**
     * Returns the property containing the south {@link Node}.
     * 
     * @return the property containing the south {@link Node}.
     */
    protected ObjectProperty<Node> south() {

        return this.south;
    }

    /**
     * Returns the south {@link Node}.
     * 
     * @return the south {@link Node}.
     */
    protected Node getSouth() {

        return this.south.get();
    }


    /**
     * Sets the south {@link Node}.
     * 
     * @param south
     *            the south {@link Node}.
     */
    protected void setSouth(Node south) {

        this.south.set(south);
    }

    /**
     * Returns the property containing the south-west {@link Node}.
     * 
     * @return the property containing the south-west {@link Node}.
     */
    protected ObjectProperty<Node> southWest() {

        return this.southWest;
    }

    /**
     * Returns the south-west {@link Node}.
     * 
     * @return the south-west {@link Node}.
     */
    protected Node getSouthWest() {

        return this.southWest.get();
    }


    /**
     * Sets the south-west {@link Node}.
     * 
     * @param southWest
     *            the south-west {@link Node}.
     */
    protected void setSouthWest(Node southWest) {

        this.southWest.set(southWest);
    }


    /**
     * Returns the property containing the west {@link Node}.
     * 
     * @return the property containing the west {@link Node}.
     */
    protected ObjectProperty<Node> west() {

        return this.west;
    }

    /**
     * Returns the west {@link Node}.
     * 
     * @return the west {@link Node}.
     */
    protected Node getWest() {

        return this.west.get();
    }


    /**
     * Sets the west {@link Node}.
     * 
     * @param west
     *            the west {@link Node}.
     */
    protected void setWest(Node west) {

        this.west.set(west);
    }


    /**
     * Returns the property containing the north-west {@link Node}.
     * 
     * @return the property containing the north-west {@link Node}.
     */
    protected ObjectProperty<Node> northWest() {

        return this.northWest;
    }

    /**
     * Returns the north-west {@link Node}.
     * 
     * @return the north-west {@link Node}.
     */
    protected Node getNorthWest() {

        return this.northWest.get();
    }


    /**
     * Sets the north-west {@link Node}.
     * 
     * @param northWest
     *            the north-west {@link Node}.
     */
    protected void setNorthWest(Node northWest) {

        this.northWest.set(northWest);
    }



    /**
     * Returns the property containing the wrapped {@link Node}.
     * 
     * @return the property containing the wrapped {@link Node}.
     */
    protected ObjectProperty<T> wrapped() {

        return this.wrapped;
    }

    /**
     * Returns the wrapped {@link Node}.
     * 
     * @return the wrapped{@link Node}.
     */
    protected T getWrapped() {

        return this.wrapped.get();
    }


    /**
     * Sets the wrapped {@link Node}.
     * 
     * @param wrapped
     *            the wrapped {@link Node}.
     */
    protected void setWrapped(T wrapped) {

        this.wrapped.set(wrapped);
    }


    /**
     * Adds the provided {@link Node} at the desired {@link Position}.
     * 
     * @param node
     *            the {@link Node} to add.
     * @param position
     *            the desired {@link Position}.
     */
    protected void addNode(Node node, Position position) {

        // System.out.println("Node to add: " + node);
        // System.out.println("Position: " + position);
        //
        // System.out.println("East Node: " + this.getEast());
        // System.out.println("North Node: " + this.getNorth());
        // System.out.println("North-East Node: " + this.getNorthEast());
        // System.out.println("North-West Node: " + this.getNorthWest());
        // System.out.println("South Node: " + this.getSouth());
        // System.out.println("South-East Node: " + this.getSouthEast());
        // System.out.println("South-West Node: " + this.getSouthWest());
        // System.out.println("West Node: " + this.getWest());

        switch (position) {
        case EAST:
            this.setEast(node);
            break;
        case NORTH:
            this.setNorth(node);
            break;
        case NORTHEAST:
            this.setNorthEast(node);
            break;
        case NORTHWEST:
            this.setNorthWest(node);
            break;
        case SOUTH:
            this.setSouth(node);
            break;
        case SOUTHEAST:
            this.setSouthEast(node);
            break;
        case SOUTHWEST:
            this.setSouthWest(node);
            break;
        case WEST:
            this.setWest(node);
            break;
        default:
            break;
        }
    }

    /**
     * Returns the {@link Node} at the provided {@link Position}.
     * 
     * @param position
     *            the desired {@link Position}.
     * @return the {@link Node} at the provided {@link Position}.
     */
    protected Node getNode(Position position) {

        switch (position) {
        case EAST:
            return this.getEast();
        case NORTH:
            return this.getNorth();
        case NORTHEAST:
            return this.getNorthEast();
        case NORTHWEST:
            return this.getNorthWest();
        case SOUTH:
            return this.getSouth();
        case SOUTHEAST:
            return this.getSouthEast();
        case SOUTHWEST:
            return this.getSouthWest();
        case WEST:
            return this.getWest();
        default:
            return null;
        }
    }


    /**
     * Removes the {@link Node} from the provided {@link Position}.
     * 
     * @param position
     *            the {@link Position} of the {@link Node} to remove.
     * @return the removed {@link Node} or {@code null}, if no element was found
     *         at the provided {@link Position}.
     */
    protected Node removeNode(Position position) {

        Node node = null;

        switch (position) {
        case EAST:
            node = this.getEast();
            break;
        case NORTH:
            node = this.getNorth();
            break;
        case NORTHEAST:
            node = this.getNorthEast();
            break;
        case NORTHWEST:
            node = this.getNorthWest();
            break;
        case SOUTH:
            node = this.getSouth();
            break;
        case SOUTHEAST:
            node = this.getSouthEast();
            break;
        case SOUTHWEST:
            node = this.getSouthWest();
            break;
        case WEST:
            node = this.getWest();
            break;
        default:
            return null;
        }
        this.addNode(null, position);
        super.getChildren().remove(node);

        return node;
    }


    /**
     * Removes the {@link Node} from the provided {@link Position}.
     * 
     * @param nodeToRemove
     *            the node to remove.
     * @param position
     *            the {@link Position} of the {@link Node} to remove.
     * @return the removed {@link Node} or {@code null}, if no element was found
     *         at the provided {@link Position}.
     */
    protected Node removeNode(Node nodeToRemove, Position position) {

        Node node = null;

        switch (position) {
        case EAST:
            node = this.getEast();
            break;
        case NORTH:
            node = this.getNorth();
            break;
        case NORTHEAST:
            node = this.getNorthEast();
            break;
        case NORTHWEST:
            node = this.getNorthWest();
            break;
        case SOUTH:
            node = this.getSouth();
            break;
        case SOUTHEAST:
            node = this.getSouthEast();
            break;
        case SOUTHWEST:
            node = this.getSouthWest();
            break;
        case WEST:
            node = this.getWest();
            break;
        default:
            return null;
        }
        if (node != null && node.equals(nodeToRemove)) {

            this.addNode(null, position);
            super.getChildren().remove(node);
        }

        return node;
    }


    /**
     * Provides access to the context menu actions.
     * 
     * @return the context menu actions list.
     */
    public ObservableList<Action> contextMenuActions() {

        return this.contextMenuActions;
    }


    /**
     * The ContextMenu to show for this control.
     * 
     * @return the ContextMenu to show for this control.
     * 
     * @see: {@link TextField#getContextMenu()},
     *       {@link TextField#setContextMenu(ContextMenu)}
     */
    public final ObjectProperty<ContextMenu> contextMenuProperty() {

        return this.getWrapped().contextMenuProperty();
    }

    /**
     * The Tooltip for this control.
     * 
     * @return the Tooltip for this control.
     * 
     * @see: {@link TextField#getTooltip()},
     *       {@link TextField#setTooltip(Tooltip)}
     */
    public final ObjectProperty<Tooltip> tooltipProperty() {

        return this.getWrapped().tooltipProperty();
    }

    /**
     * Gets the value of the property contextMenu.
     * 
     * @return the value of the property contextMenu.
     */
    public final ContextMenu getContextMenu() {

        return this.contextMenuProperty().get();
    }

    /**
     * Sets the value of the property contextMenu.
     * 
     * @param contextMenu
     *            the value of the property contextMenu.
     */
    public final void setContextMenu(final ContextMenu contextMenu) {

        this.contextMenuProperty().set(contextMenu);
    }

    /**
     * Gets the value of the property tooltip.
     * 
     * @return the value of the property tooltip.
     */
    public final Tooltip getTooltip() {

        return this.tooltipProperty().get();
    }

    /**
     * Sets the value of the property tooltip.
     * 
     * @param tooltip
     *            the value of the property tooltip.
     */
    public final void setContextMenu(final Tooltip tooltip) {

        this.tooltipProperty().set(tooltip);
    }

    /**
     * Skin is responsible for rendering this Control. From the perspective of
     * the Control, the Skin is a black box. It listens and responds to changes
     * in state in a Control. There is a one-to-one relationship between a
     * Control and its Skin. Every Skin maintains a back reference to the
     * Control via the Skin.getSkinnable() method.
     * 
     * A skin may be null.
     * 
     * Specified by: skinProperty in interface {@link Skinnable}
     * 
     * @see Control#getSkin()
     * @see Control#setSkin(Skin)
     * 
     * @return the Skin property.
     */
    public final ObjectProperty<Skin<?>> skinProperty() {

        return this.getWrapped().skinProperty();
    }

    /**
     * Returns the skin.
     * 
     * @return the Skin.
     */
    public final Skin<?> getSkin() {

        return this.getWrapped().getSkin();
    }


    /**
     * Creates a new instance of the {@link WrapperBase} class.
     */
    public WrapperBase() {

        this.north = new SimpleObjectProperty<>("wrapper", "north");
        this.northEast = new SimpleObjectProperty<>("wrapper", "north-east");
        this.east = new SimpleObjectProperty<>("wrapper", "east");
        this.southEast = new SimpleObjectProperty<>("wrapper", "south-east");
        this.south = new SimpleObjectProperty<>("wrapper", "south");
        this.southWest = new SimpleObjectProperty<>("wrapper", "south-west");
        this.west = new SimpleObjectProperty<>("wrapper", "west");
        this.northWest = new SimpleObjectProperty<>("wrapper", "north-west");

        this.wrapped = new SimpleObjectProperty<>("wrapper", "wrapped");



        this.contextMenuActions = FXCollections
                .observableList(new LinkedList<Action>());

        this.setUpEventHandlers();
    }

    private void setUpEventHandlers() {

        this.setUpEventHandler(this.north);
        this.setUpEventHandler(this.northEast);
        this.setUpEventHandler(this.east);
        this.setUpEventHandler(this.southEast);
        this.setUpEventHandler(this.south);
        this.setUpEventHandler(this.southWest);
        this.setUpEventHandler(this.west);
        this.setUpEventHandler(this.northWest);
        this.setUpEventHandler(this.wrapped);

        this.contextMenuActions.addListener(new ListChangeListener<Action>() {

            @Override
            public void onChanged(Change<? extends Action> change) {

                WrapperBase.this
                        .getWrapped()
                        .contextMenuProperty()
                        .set(ActionUtils
                                .createContextMenu(WrapperBase.this.contextMenuActions()));

            }
        });
    }

    private void setUpEventHandler(ObjectProperty<?> property) {

        property.addListener(new ChangeListener<Object>() {

            @Override
            public void changed(ObservableValue<? extends Object> observable,
                    Object oldValue, Object newValue) {

                WrapperBase.this.performLayout();
            }
        });

        property.addListener(new InvalidationListener() {

            @Override
            public void invalidated(Observable observable) {

                WrapperBase.this.performLayout();
            }
        });
    }

    /**
     * Performs the layouting operations.
     */
    protected void performLayout() {

        boolean northPresent = this.getNorth() != null;
        boolean northEastPresent = this.getNorthEast() != null;
        boolean eastPresent = this.getEast() != null;
        boolean southEastPresent = this.getSouthEast() != null;
        boolean southPresent = this.getSouth() != null;
        boolean southWestPresent = this.getSouthWest() != null;
        boolean westPresent = this.getWest() != null;
        boolean northWestPresent = this.getNorthWest() != null;
        boolean wrappedPresent = this.getWrapped() != null;


        boolean northOccupied = northPresent || northEastPresent
                || northWestPresent;
        boolean westOccupied = westPresent || northWestPresent
                || southWestPresent;

        int centerRow = 0;
        int centerCol = 0;

        if (northOccupied) {

            centerRow++;
        }
        if (westOccupied) {

            centerCol++;
        }


        this.getChildren().clear();


        if (northWestPresent) {

            super.add(this.getNorthWest(), 0, 0);
        }
        if (northPresent) {

            super.add(this.getNorth(), centerCol, 0);
        }
        if (northEastPresent) {

            super.add(this.getNorthEast(), centerCol + 1, 0);
        }

        if (westPresent) {

            super.add(this.getWest(), 0, centerRow);
        }


        if (wrappedPresent) {

            super.add(this.getWrapped(), centerCol, centerRow);
        }


        if (eastPresent) {

            super.add(this.getEast(), centerCol + 1, centerRow);
        }

        if (southWestPresent) {

            super.add(this.getSouthWest(), 0, centerRow + 1);
        }
        if (southPresent) {

            super.add(this.getSouth(), centerCol, centerRow + 1);
        }
        if (southEastPresent) {

            super.add(this.getSouthWest(), centerCol + 1, centerRow + 1);
        }

    }

    @Override
    public void add(Node child, int columnIndex, int rowIndex) {

        throw new UnsupportedOperationException(
                "Read-write access to the children of "
                        + this.getClass().getCanonicalName()
                        + " is disallowed!");
    }

    @Override
    public void add(Node child, int columnIndex, int rowIndex, int colspan,
            int rowspan) {

        throw new UnsupportedOperationException(
                "Read-write access to the children of "
                        + this.getClass().getCanonicalName()
                        + " is disallowed!");
    }

    @Override
    public void addColumn(int columnIndex, Node... children) {

        throw new UnsupportedOperationException(
                "Read-write access to the children of "
                        + this.getClass().getCanonicalName()
                        + " is disallowed!");
    }

    @Override
    public void addRow(int rowIndex, Node... children) {

        throw new UnsupportedOperationException(
                "Read-write access to the children of "
                        + this.getClass().getCanonicalName()
                        + " is disallowed!");
    }

    // @Override
    // public ObservableList<Node> getChildren() {
    //
    // throw new UnsupportedOperationException(
    // "Read-write access to the children of "
    // + this.getClass().getCanonicalName()
    // + " is disallowed!");
    // }

    /**
     * Returns the {@Node Nodes} within this wrapper.
     * 
     * @return the {@Node Nodes} within this wrapper.
     */
    protected ObservableList<Node> getNodes() {

        return super.getChildren();
    }


    /**
     * The {@link Position} enum enumerates the positions of a decoration
     * {@link Node} in a {@link WrapperBase Wrapper}.
     * 
     * @author Peter J. Radics
     * @version 1.0
     * @since 1.0
     */
    public static enum Position {

        /**
         * Positions the {@link Node} north of the wrapped {@link Node}
         */
        NORTH,
        /**
         * Positions the {@link Node} north-east of the wrapped {@link Node}
         */
        NORTHEAST,
        /**
         * Positions the {@link Node} east of the wrapped {@link Node}
         */
        EAST,
        /**
         * Positions the {@link Node} south-east of the wrapped {@link Node}
         */
        SOUTHEAST,
        /**
         * Positions the {@link Node} south of the wrapped {@link Node}
         */
        SOUTH,
        /**
         * Positions the {@link Node} south-west of the wrapped {@link Node}
         */
        SOUTHWEST,
        /**
         * Positions the {@link Node} west of the wrapped {@link Node}
         */
        WEST,
        /**
         * Positions the {@link Node} north-west of the wrapped {@link Node}
         */
        NORTHWEST;
    }
}
