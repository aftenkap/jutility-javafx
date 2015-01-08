package org.jutility.javafx;


import java.util.Arrays;
import java.util.List;

import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.scene.Scene;
import javafx.scene.layout.GridPane;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

import org.jutility.javafx.control.OldListViewWithSearchPanel;
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
        
        ListViewWithSearchPanel<String> tester = new ListViewWithSearchPanel<>(
                FXCollections.observableArrayList(items), null, "TestList");
        OldListViewWithSearchPanel<String> tester2 = new OldListViewWithSearchPanel<>("TestList2");

        root.add(tester, 0, 0);
        root.add(tester2, 1, 0);
        
        this.mainScene = new Scene(root, 1440, 900, Color.LIGHTSLATEGREY);



        this.stage.setScene(this.mainScene);
        this.stage.show();
    }

}
