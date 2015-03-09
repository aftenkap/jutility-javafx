package org.jutility.javafx.control.icon;



//@formatter:off
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
//@formatter:on


import java.util.ArrayList;
import java.util.List;

import javafx.beans.value.ChangeListener;
import javafx.scene.effect.BlurType;
import javafx.scene.effect.BoxBlur;
import javafx.scene.effect.DropShadow;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.paint.CycleMethod;
import javafx.scene.paint.LinearGradient;
import javafx.scene.paint.Paint;
import javafx.scene.paint.Stop;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Ellipse;
import javafx.scene.shape.Rectangle;


/**
 * The {@code ErrorIcon} class provides an error icon.
 *
 * @author Peter J. Radics
 * @version 0.1.2
 * @since 0.1.0
 */
public class ErrorIcon
        extends Pane {

    private final double frame1Ratio             = .95;
    private final double frame2Ratio             = .9;


    private Circle       frame;
    private Circle       frame1;
    private Circle       frame2;
    private DropShadow   shadow;
    private Ellipse      lightEffect;
    private Rectangle    r1;
    private Rectangle    r2;
    private Rectangle    r3;

    private final double rectangleXRatio         = 0.7;
    private final double rectangleYRatio         = 0.14;
    private final double lightEffectRotate       = 1;
    private final double lightEffectXRatio       = 0.2;
    private final double lightEffectYRatio       = 0.2;
    private final double lightEffectXRadiusRatio = 0.2;
    private final double lightEffectYRadiusRatio = 0.2;
    private final double shadowXOffset           = 0.02;
    private final double shadowYOffset           = 0.02;
    private final double shadowSizeOffset        = 0.02;



    /**
     * Creates a new instance of the {@link ErrorIcon} class.
     */
    public ErrorIcon() {

        this(20);
    }

    /**
     * Creates a new instance of the {@link ErrorIcon} class with the provided
     * size.
     *
     * @param size
     *            the desired size of the icon.
     */
    public ErrorIcon(final double size) {

        super();

        this.setWidth(400);
        this.setHeight(400);

        this.initGraphics();
        this.resize();

        this.setWidth(size);
        this.setHeight(size);
        this.setMaxSize(size, size);
        this.resize();
        final ChangeListener<Number> resizeListener = (observable, oldValue,
                newValue) -> {

            System.out.println("Resizing! new value: " + newValue);
            ErrorIcon.this.resize();
        };
        this.widthProperty().addListener(resizeListener);
        this.heightProperty().addListener(resizeListener);

    }

    private Rectangle createRectangle() {

        final double size = this.getWidth() < this.getHeight() ? this
                .getWidth() : this.getHeight();


        final double width = size * this.rectangleXRatio;
        final double height = size * this.rectangleYRatio;

        final Rectangle rectangle = new Rectangle();

        rectangle.setWidth(width);
        rectangle.setHeight(height);
        rectangle.setArcHeight(height / 2);
        rectangle.setArcWidth(height / 2);
        rectangle.setStrokeWidth(1);
        rectangle.setFill(this.whiteBackground());
        rectangle.setX((size - width) / 2.0);
        rectangle.setY((size - height) / 2.0);
        return rectangle;
    }


    private void initGraphics() {

        this.frame = new Circle();

        // linear-gradient(from 27.1% 6.5% to 77.35% 91%,
        // #e8e8e8 0%,
        // #c6c6c6 50%,
        // #a6a6a6 100%
        final List<Stop> stops = new ArrayList<>(3);
        stops.add(new Stop(0.0f, Color.web("#e8e8e8")));
        stops.add(new Stop(0.5f, Color.web("#c6c6c6")));
        stops.add(new Stop(1.0f, Color.web("#a6a6a6")));
        final LinearGradient FRAME_FILL = new LinearGradient(0.271, 0.065,
                0.7735, 0.91, true, CycleMethod.NO_CYCLE, stops);

        this.frame.setFill(FRAME_FILL);

        this.shadow = new DropShadow();
        this.shadow.setBlurType(BlurType.ONE_PASS_BOX);
        this.shadow.setColor(Color.rgb(0, 0, 0, 0.4));
        this.frame.setEffect(this.shadow);

        this.frame1 = new Circle();

        final List<Stop> stops2 = new ArrayList<>();
        // #fdfdfd 0%,
        stops2.add(new Stop(0.0f, Color.web("#fdfdfd")));
        // #ededed 3.552646%,
        stops2.add(new Stop(0.03552646f, Color.web("#ededed")));
        // #d7d7d7 7.277831%,
        stops2.add(new Stop(0.07277831f, Color.web("#d7d7d7")));
        // #d2d2d2 11.973317%,
        stops2.add(new Stop(0.11973317f, Color.web("#d2d2d2")));
        // #c7c7c7 18.269639%,
        stops2.add(new Stop(0.18269639f, Color.web("#c7c7c7")));
        // #c1c1c1 25.449407%,
        stops2.add(new Stop(0.25449407f, Color.web("#c1c1c1")));
        // #b0b0b0 32.21809%,
        stops2.add(new Stop(0.3221809f, Color.web("#b0b0b0")));
        // #999999 37.210315%,
        stops2.add(new Stop(0.37210315f, Color.web("#999999")));
        // #868686 43.145844%,
        stops2.add(new Stop(0.43145844f, Color.web("#868686")));
        // #747474 49.577036%,
        stops2.add(new Stop(0.49577036f, Color.web("#747474")));
        // #5c5c5c 55.667913%,
        stops2.add(new Stop(0.55667913f, Color.web("#5c5c5c")));
        // #5a5a5a 61.299348%,
        stops2.add(new Stop(0.61299348f, Color.web("#5a5a5a")));
        // #5e5e5e 68.340749%,
        stops2.add(new Stop(0.68340749f, Color.web("#5e5e5e")));
        // #676767 76.115692%,
        stops2.add(new Stop(0.76115692f, Color.web("#676767")));
        // #706e6f 82.365692%,
        stops2.add(new Stop(0.82365692f, Color.web("#706e6f")));
        // #838383 88.148153%,
        stops2.add(new Stop(0.88148153f, Color.web("#838383")));
        // #959595 93.637025%,
        stops2.add(new Stop(0.93637025f, Color.web("#959595")));
        // #a8a8a8 100%
        stops2.add(new Stop(1.0f, Color.web("#a8a8a8")));


        // -fx-background-color : linear-gradient(from 27.1% 6.5% to 77.35% 91%,
        final LinearGradient FRAME1_FILL = new LinearGradient(0.271, 0.065,
                0.7735, 0.91, true, CycleMethod.NO_CYCLE, stops2);


        this.frame1.setFill(FRAME1_FILL);

        // -fx-border-color : linear-gradient(from 27.1% 6.5% to 77.35% 91%,
        // #d5d5d5 0%,
        // #747474 50%,
        // #8f8f8f 100%);
        // }

        final List<Stop> stops3 = new ArrayList<>(3);
        // #d5d5d5 0%,
        stops3.add(new Stop(0.0f, Color.web("#d5d5d5")));
        // #747474 50%,
        stops3.add(new Stop(0.5f, Color.web("#747474")));
        // #8f8f8f 100%
        stops3.add(new Stop(1.0f, Color.web("#8f8f8f")));
        final LinearGradient FRAME1_STROKE = new LinearGradient(0.271, 0.065,
                0.7735, 0.91, true, CycleMethod.NO_CYCLE, stops3);



        this.frame1.setStroke(FRAME1_STROKE);
        this.frame1.setStrokeWidth(2.0);

        this.frame2 = new Circle();
        // -fx-background-color : linear-gradient(from 27.1% 6.5% to 77.35% 91%,
        final Paint FRAME2_FILL = this.redIconBackground();


        // -fx-border-width : 1;
        // -fx-border-color : #212121;
        this.frame2.setFill(FRAME2_FILL);
        final Paint FRAME2_STROKE = Color.web("#212121");
        this.frame2.setStroke(FRAME2_STROKE);

        this.lightEffect = new Ellipse();
        this.lightEffect.setFill(Color.rgb(255, 255, 255, 0.7));
        this.lightEffect.setEffect(new BoxBlur(100, 100, 5));
        this.lightEffect.setCache(true);

        this.r1 = this.createRectangle();
        this.r1.setRotate(-45);
        this.r1.setStrokeWidth(1);
        this.r1.setStroke(FRAME1_STROKE);

        this.r2 = this.createRectangle();
        this.r2.setRotate(45);
        this.r2.setStrokeWidth(1);
        this.r2.setStroke(FRAME1_STROKE);

        this.r3 = this.createRectangle();
        this.r3.setRotate(-45);
        this.r3.setStroke(Color.TRANSPARENT);
        this.r3.setStrokeWidth(1);



        this.getChildren().addAll(this.frame, this.frame1, this.frame2,
                this.r1, this.r2, this.r3, this.lightEffect);

    }

    @SuppressWarnings("unused")
    private LinearGradient blackIconBackground() {

        final List<Stop> stops = new ArrayList<>(3);
        // #1c1715 0%,
        stops.add(new Stop(0.0f, Color.web("#1c1715")));
        // #181818 50%,
        stops.add(new Stop(0.5f, Color.web("#181818")));
        // #3a3a3a 100%);
        stops.add(new Stop(1.0f, Color.web("#3a3a3a")));
        final LinearGradient blackIconBackground = new LinearGradient(0.271,
                0.065, 0.7735, 0.91, true, CycleMethod.NO_CYCLE, stops);

        return blackIconBackground;
    }

    private LinearGradient whiteBackground() {

        final List<Stop> stops = new ArrayList<>(3);
        // #fdfdfd 0%,
        stops.add(new Stop(0.0f, Color.web("#fdfdfd")));
        // #ebebeb 50%,
        stops.add(new Stop(0.5f, Color.web("#ebebeb")));
        // #ffffff 100%);
        stops.add(new Stop(1.0f, Color.web("#ffffff")));
        final LinearGradient blackIconBackground = new LinearGradient(0.271,
                0.065, 0.7735, 0.91, true, CycleMethod.NO_CYCLE, stops);

        return blackIconBackground;
    }


    private LinearGradient redIconBackground() {

        final List<Stop> stops = new ArrayList<>(3);
        // #fe2020 0%,
        stops.add(new Stop(0.0f, Color.web("#fe2020")));
        // #b70b0b 50%
        stops.add(new Stop(0.5f, Color.web("#b70b0b")));
        // #d96868 100%)
        stops.add(new Stop(1.0f, Color.web("#d96868")));
        final LinearGradient redIconBackground = new LinearGradient(0.271,
                0.065, 0.7735, 0.91, true, CycleMethod.NO_CYCLE, stops);

        return redIconBackground;
    }


    private void resize() {

        final double size = this.getWidth() < this.getHeight() ? this
                .getWidth() : this.getHeight();

        final double halfSize = size / 2.0;


        final double width = size * this.rectangleXRatio;
        final double height = size * this.rectangleYRatio;

        final double centerX = (size - width) / 2.0;
        final double centerY = (size - height) / 2.0;

        this.frame.setRadius(halfSize);
        this.frame.setCenterX(halfSize);
        this.frame.setCenterY(halfSize);


        this.frame1.setRadius(this.frame1Ratio * halfSize);
        this.frame1.setCenterX(halfSize);
        this.frame1.setCenterY(halfSize);

        this.shadow.setOffsetX(size * this.shadowXOffset);
        this.shadow.setOffsetY(size * this.shadowYOffset);
        this.shadow.setRadius(size * this.shadowSizeOffset);
        this.shadow.setSpread(0.099);

        this.frame2.setRadius(this.frame2Ratio * halfSize);
        this.frame2.setLayoutX(halfSize);
        this.frame2.setLayoutY(halfSize);

        this.r1.setWidth(width);
        this.r1.setHeight(height);
        this.r1.setX(centerX);
        this.r1.setY(centerY);
        this.r1.setArcHeight(height / 2.0);
        this.r1.setArcWidth(width / 2.0);

        this.r2.setWidth(width);
        this.r2.setHeight(height);
        this.r2.setX(centerX);
        this.r2.setY(centerY);
        this.r2.setArcHeight(height / 2.0);
        this.r2.setArcWidth(width / 2.0);

        this.r3.setWidth(width);
        this.r3.setHeight(height);
        this.r3.setX(centerX);
        this.r3.setY(centerY);
        this.r3.setArcHeight(height / 2.0);
        this.r3.setArcWidth(width / 2.0);

        this.lightEffect.setRotate(this.lightEffectRotate);
        this.lightEffect.setTranslateX(this.lightEffectXRatio * size);
        this.lightEffect.setTranslateY(this.lightEffectYRatio * size);
        this.lightEffect.setRadiusX(this.lightEffectXRadiusRatio * size);
        this.lightEffect.setRadiusY(this.lightEffectYRadiusRatio * size);

        if (this.lightEffect.getEffect() instanceof BoxBlur) {

            final BoxBlur blur = (BoxBlur) this.lightEffect.getEffect();

            blur.setHeight(0.25 * size);
            blur.setWidth(0.25 * size);
        }
    }
}
