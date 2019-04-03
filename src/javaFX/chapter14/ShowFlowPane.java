package javaFX.chapter14;

import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.FlowPane;
import javafx.stage.Stage;

public class ShowFlowPane extends Application {
    /**
     * 这里有点东西要声明，同一个节点有且仅且能加入面板一次，也只能加入一次。不能同时加入两个或两个以上的面板。
     * @param primaryStage
     * @throws Exception
     */
    @Override
    public void start(Stage primaryStage) throws Exception {

        FlowPane flowPane = new FlowPane();
//        flowPane.setOrientation(Orientation.VERTICAL);
        flowPane.setPadding(new Insets(11, 12, 13, 14));
        flowPane.setHgap(5);//每个节点之间的水平间距
        flowPane.setVgap(5);//每个节点之间的垂直间距

        flowPane.getChildren().addAll(new Label("First Name : "),
                new TextField(), new Label("MI : "));
        TextField tfMi = new TextField();
        //感觉很像 设置参考列 （累加数大小），起码强行改变了文本框的长度。
        tfMi.setPrefColumnCount(0);
        flowPane.getChildren().addAll(tfMi, new Label("Last Name : "),
                new TextField());

        Scene scene = new Scene(flowPane, 400, 250);
        primaryStage.setTitle("ShowFlowPane");
        primaryStage.setScene(scene);
        primaryStage.show();
        primaryStage.setResizable(false);
    }
}
