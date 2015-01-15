package org.jutility.javafx;


import java.util.Arrays;
import java.util.List;

import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Priority;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.stage.Stage;

import org.jutility.javafx.control.ListViewWithSearchPanel;


/**
 * @author peter
 * @version
 * @since
 *
 */
public class TestApplication
        extends Application {

    private String title = "Test Application";

    private Stage  stage;
    private Scene  mainScene;



    /**
     * Main method.
     * 
     * @param args
     *            optional arguments (unused)
     * @throws InterruptedException
     */
    public static void main(String[] args)
            throws InterruptedException {

        Application.launch(args);
    }



    @Override
    public void start(final Stage initStage)
            throws Exception {


        this.stage = initStage;
        this.stage.setTitle(title);

        List<String> items = Arrays.asList("foo", "bar", "baz", "meh");
        GridPane root = new GridPane();
        root.setPadding(new Insets(5));

        ListViewWithSearchPanel<String> tester = new ListViewWithSearchPanel<>(
                FXCollections.observableArrayList(items), "TestList", null);

        tester.getLabel().setFont(Font.font("verdana", 16));
        tester.getLabel().setPadding(new Insets(0, 0 ,10, 0));
        
        GridPane.setVgrow(tester, Priority.ALWAYS);
        root.add(tester, 0, 0);

        this.mainScene = new Scene(root, 1440, 900, Color.LIGHTSLATEGREY);



        this.stage.setScene(this.mainScene);
        this.stage.show();
    }

}
