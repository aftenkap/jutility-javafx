package org.jutility.javafx;

/*
 * #%L
 * jutility-javafx
 * %%
 * Copyright (C) 2013 - 2015 jutility.org
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


//@formatter:off
/*
* #%L
 * * jutility-javafx 
 * *
 * %%
 * Copyright (C) 2013 - 2014 jutility.org 
 * *
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
 * Simple sampler/test application.
 * 
 * @author Peter J. Radics
 * @version 0.1.2
 * @since 0.1.2
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
     *             if the application is interrupted.
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
        tester.getLabel().setPadding(new Insets(0, 0, 10, 0));

        GridPane.setVgrow(tester, Priority.ALWAYS);
        root.add(tester, 0, 0);

        this.mainScene = new Scene(root, 1440, 900, Color.LIGHTSLATEGREY);


        this.stage.setScene(this.mainScene);
        this.stage.show();
    }

}
