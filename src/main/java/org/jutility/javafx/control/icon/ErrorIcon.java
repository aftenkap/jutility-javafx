package org.jutility.javafx.control.icon;


import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.scene.effect.BlurType;
import javafx.scene.effect.BoxBlur;
import javafx.scene.effect.DropShadow;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.paint.CycleMethod;
import javafx.scene.paint.LinearGradient;
import javafx.scene.paint.LinearGradientBuilder;
import javafx.scene.paint.Paint;
import javafx.scene.paint.Stop;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Ellipse;
import javafx.scene.shape.Rectangle;
import javafx.scene.shape.RectangleBuilder;


/**
 * @author Peter J. Radics
 * 
 */
public class ErrorIcon
        extends Pane {

    private double     frame1Ratio             = .95;
    private double     frame2Ratio             = .9;


    private Circle     frame;
    private Circle     frame1;
    private Circle     frame2;
    private DropShadow shadow;
    private Ellipse    lightEffect;
    private Rectangle  r1;
    private Rectangle  r2;
    private Rectangle  r3;

    private double     rectangleXRatio         = 0.7;
    private double     rectangleYRatio         = 0.14;
    private double     lightEffectRotate       = 1;
    private double     lightEffectXRatio       = 0.2;
    private double     lightEffectYRatio       = 0.2;
    private double     lightEffectXRadiusRatio = 0.2;
    private double     lightEffectYRadiusRatio = 0.2;
    private double     shadowXOffset           = 0.02;
    private double     shadowYOffset           = 0.02;
    private double     shadowSizeOffset        = 0.02;



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
    public ErrorIcon(double size) {

        super();

        this.setWidth(400);
        this.setHeight(400);

        this.initGraphics();
        this.resize();

        this.setWidth(size);
        this.setHeight(size);
        this.setMaxSize(size, size);
        this.resize();
        ChangeListener<Number> resizeListener = new ChangeListener<Number>() {

            @Override
            public void changed(ObservableValue<? extends Number> observable,
                    Number oldValue, Number newValue) {

                System.out.println("Resizing! new value: " + newValue);
                ErrorIcon.this.resize();
            }
        };
        this.widthProperty().addListener(resizeListener);
        this.heightProperty().addListener(resizeListener);

    }

    private Rectangle createRectangle() {

        double size = getWidth() < getHeight() ? getWidth() : getHeight();


        double width = size * rectangleXRatio;
        double height = size * rectangleYRatio;

        Rectangle rectangle = new Rectangle();

        rectangle.setWidth(width);
        rectangle.setHeight(height);
        rectangle.setArcHeight(height / 2);
        rectangle.setArcWidth(height / 2);
        rectangle.setStrokeWidth(1);
        rectangle.setFill(whiteBackground());
        rectangle.setX((size - width) / 2.0);
        rectangle.setY((size - height) / 2.0);
        return rectangle;
    }


    private void initGraphics() {

        frame = new Circle();

        // linear-gradient(from 27.1% 6.5% to 77.35% 91%,
        // #e8e8e8 0%,
        // #c6c6c6 50%,
        // #a6a6a6 100%
        Paint FRAME_FILL = LinearGradientBuilder
                .create()
                .proportional(true)
                .startX(0.271)
                .startY(0.065)
                .endX(0.7735)
                .endY(0.91)
                .cycleMethod(CycleMethod.NO_CYCLE)
                .stops(new Stop(0.0f, Color.web("#e8e8e8")),
                        new Stop(0.5f, Color.web("#c6c6c6")),
                        new Stop(1.0f, Color.web("#a6a6a6"))).build();

        frame.setFill(FRAME_FILL);

        shadow = new DropShadow();
        shadow.setBlurType(BlurType.ONE_PASS_BOX);
        shadow.setColor(Color.rgb(0, 0, 0, 0.4));
        frame.setEffect(shadow);

        frame1 = new Circle();
        // -fx-background-color : linear-gradient(from 27.1% 6.5% to 77.35% 91%,
        Paint FRAME1_FILL = LinearGradientBuilder.create().proportional(true)
                .startX(0.271).startY(0.065).endX(0.7735).endY(0.91)
                .cycleMethod(CycleMethod.NO_CYCLE).stops(

                // #fdfdfd 0%,
                        new Stop(0.0f, Color.web("#fdfdfd")),
                        // #ededed 3.552646%,
                        new Stop(0.03552646f, Color.web("#ededed")),
                        // #d7d7d7 7.277831%,
                        new Stop(0.07277831f, Color.web("#d7d7d7")),
                        // #d2d2d2 11.973317%,
                        new Stop(0.11973317f, Color.web("#d2d2d2")),
                        // #c7c7c7 18.269639%,
                        new Stop(0.18269639f, Color.web("#c7c7c7")),
                        // #c1c1c1 25.449407%,
                        new Stop(0.25449407f, Color.web("#c1c1c1")),
                        // #b0b0b0 32.21809%,
                        new Stop(0.3221809f, Color.web("#b0b0b0")),
                        // #999999 37.210315%,
                        new Stop(0.37210315f, Color.web("#999999")),
                        // // #868686 43.145844%,
                        new Stop(0.43145844f, Color.web("#868686")),
                        // // #747474 49.577036%,
                        new Stop(0.49577036f, Color.web("#747474")),
                        // // #5c5c5c 55.667913%,
                        new Stop(0.55667913f, Color.web("#5c5c5c")),
                        // // #5a5a5a 61.299348%,
                        new Stop(0.61299348f, Color.web("#5a5a5a")),
                        // // #5e5e5e 68.340749%,
                        new Stop(0.68340749f, Color.web("#5e5e5e")),
                        // // #676767 76.115692%,
                        new Stop(0.76115692f, Color.web("#676767")),
                        // // #706e6f 82.365692%,
                        new Stop(0.82365692f, Color.web("#706e6f")),
                        // // #838383 88.148153%,
                        new Stop(0.88148153f, Color.web("#838383")),
                        // // #959595 93.637025%,
                        new Stop(0.93637025f, Color.web("#959595")),
                        // #a8a8a8 100%
                        new Stop(1.0f, Color.web("#a8a8a8"))

                ).build();



        frame1.setFill(FRAME1_FILL);

        // -fx-border-color : linear-gradient(from 27.1% 6.5% to 77.35% 91%,
        // #d5d5d5 0%,
        // #747474 50%,
        // #8f8f8f 100%);
        // }
        Paint FRAME1_STROKE = LinearGradientBuilder.create().proportional(true)
                .startX(0.271).startY(0.065).endX(0.7735).endY(0.91)
                .cycleMethod(CycleMethod.NO_CYCLE).stops(

                // #d5d5d5 0%,
                        new Stop(0.0f, Color.web("#d5d5d5")),
                        // #747474 50%,
                        new Stop(0.5f, Color.web("#747474")),
                        // #8f8f8f 100%
                        new Stop(1.0f, Color.web("#8f8f8f"))

                ).build();



        frame1.setStroke(FRAME1_STROKE);
        frame1.setStrokeWidth(2.0);

        frame2 = new Circle();
        // -fx-background-color : linear-gradient(from 27.1% 6.5% to 77.35% 91%,
        Paint FRAME2_FILL = this.redIconBackground();


        // -fx-border-width : 1;
        // -fx-border-color : #212121;
        frame2.setFill(FRAME2_FILL);
        Paint FRAME2_STROKE = Color.web("#212121");
        frame2.setStroke(FRAME2_STROKE);

        lightEffect = new Ellipse();
        lightEffect.setFill(Color.rgb(255, 255, 255, 0.7));
        lightEffect.setEffect(new BoxBlur(100, 100, 5));
        lightEffect.setCache(true);

        r1 = createRectangle();
        r1.setRotate(-45);
        r1.setStrokeWidth(1);
        r1.setStroke(FRAME1_STROKE);

        r2 = createRectangle();
        r2.setRotate(45);
        r2.setStrokeWidth(1);
        r2.setStroke(FRAME1_STROKE);

        r3 = createRectangle();
        r3.setRotate(-45);
        r3.setStroke(Color.TRANSPARENT);
        r3.setStrokeWidth(1);



        this.getChildren().addAll(frame, frame1, frame2, r1, r2, r3,
                lightEffect);

    }

    @SuppressWarnings("unused")
    private LinearGradient blackIconBackground() {

        LinearGradient blackIconBackground = LinearGradientBuilder.create()
                .proportional(true).startX(0.271).startY(0.065).endX(0.7735)
                .endY(0.91).cycleMethod(CycleMethod.NO_CYCLE).stops(

                // #1c1715 0%,
                        new Stop(0.0f, Color.web("#1c1715")),
                        // #181818 50%,
                        new Stop(0.5f, Color.web("#181818")),
                        // #3a3a3a 100%);
                        new Stop(1.0f, Color.web("#3a3a3a"))

                ).build();

        return blackIconBackground;
    }

    private LinearGradient whiteBackground() {

        LinearGradient blackIconBackground = LinearGradientBuilder.create()
                .proportional(true).startX(0.271).startY(0.065).endX(0.7735)
                .endY(0.91).cycleMethod(CycleMethod.NO_CYCLE).stops(

                // #1c1715 0%,
                        new Stop(0.0f, Color.web("#fdfdfd")),
                        // #181818 50%,
                        new Stop(0.5f, Color.web("#ebebeb")),
                        // #3a3a3a 100%);
                        new Stop(1.0f, Color.web("#ffffff"))

                ).build();

        return blackIconBackground;
    }


    private LinearGradient redIconBackground() {

        LinearGradient redIconBackground = LinearGradientBuilder.create()
                .proportional(true).startX(0.271).startY(0.065).endX(0.7735)
                .endY(0.91).cycleMethod(CycleMethod.NO_CYCLE).stops(

                // #1c1715 0%,
                        new Stop(0.0f, Color.web("#fe2020")),
                        // #181818 50%,
                        new Stop(0.5f, Color.web("#b70b0b")),
                        // #3a3a3a 100%);
                        new Stop(1.0f, Color.web("#d96868"))

                ).build();

        return redIconBackground;
    }



    private void resize() {

        double size = getWidth() < getHeight() ? getWidth() : getHeight();

        double halfSize = size / 2.0;


        double width = size * rectangleXRatio;
        double height = size * rectangleYRatio;

        double centerX = (size - width) / 2.0;
        double centerY = (size - height) / 2.0;

        frame.setRadius(halfSize);
        frame.setCenterX(halfSize);
        frame.setCenterY(halfSize);


        frame1.setRadius(frame1Ratio * halfSize);
        frame1.setCenterX(halfSize);
        frame1.setCenterY(halfSize);

        shadow.setOffsetX(size * shadowXOffset);
        shadow.setOffsetY(size * shadowYOffset);
        shadow.setRadius(size * shadowSizeOffset);
        shadow.setSpread(0.099);

        frame2.setRadius(frame2Ratio * halfSize);
        frame2.setLayoutX(halfSize);
        frame2.setLayoutY(halfSize);

        r1.setWidth(width);
        r1.setHeight(height);
        r1.setX(centerX);
        r1.setY(centerY);
        r1.setArcHeight(height / 2.0);
        r1.setArcWidth(width / 2.0);

        r2.setWidth(width);
        r2.setHeight(height);
        r2.setX(centerX);
        r2.setY(centerY);
        r2.setArcHeight(height / 2.0);
        r2.setArcWidth(width / 2.0);

        r3.setWidth(width);
        r3.setHeight(height);
        r3.setX(centerX);
        r3.setY(centerY);
        r3.setArcHeight(height / 2.0);
        r3.setArcWidth(width / 2.0);

        lightEffect.setRotate(lightEffectRotate);
        lightEffect.setTranslateX(lightEffectXRatio * size);
        lightEffect.setTranslateY(lightEffectYRatio * size);
        lightEffect.setRadiusX(lightEffectXRadiusRatio * size);
        lightEffect.setRadiusY(lightEffectYRadiusRatio * size);

        if (lightEffect.getEffect() instanceof BoxBlur) {

            BoxBlur blur = (BoxBlur) lightEffect.getEffect();

            blur.setHeight(0.25 * size);
            blur.setWidth(0.25 * size);
        }
    }
}
