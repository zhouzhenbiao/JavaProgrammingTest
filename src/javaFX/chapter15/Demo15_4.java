package javaFX.chapter15;

import javafx.application.Application;
import javafx.beans.property.DoubleProperty;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.text.Text;
import javafx.stage.Stage;

import java.math.BigDecimal;
import java.util.function.DoublePredicate;

/**
 * 创建一个简单的计算器，完成加减乘除操作
 */
public class Demo15_4 extends Application {
    private TextField tfNumber1 = new TextField();
    private TextField tfNumber2 = new TextField();
    private TextField tfResult = new TextField();
    private Text text = new Text("加减乘除的除数！");
    private String number1 = "";
    private String number2 = "";

    @Override
    public void start(Stage primaryStage) throws Exception {
        HBox hBox1 = new HBox();
        hBox1.setSpacing(7);
        hBox1.setAlignment(Pos.CENTER);
        hBox1.setPadding(new Insets(10));
        tfNumber1.setAlignment(Pos.BOTTOM_RIGHT);
        tfNumber2.setAlignment(Pos.BOTTOM_RIGHT);
        tfResult.setAlignment(Pos.BOTTOM_RIGHT);
        tfResult.setEditable(false);
        hBox1.getChildren().addAll(new Label("Number1:"), tfNumber1);
        hBox1.getChildren().addAll(new Label("Number2:"), tfNumber2);
        hBox1.getChildren().addAll(new Label("Result:"), tfResult);
        Button bt_add = new Button("Add");
        Button bt_subtract = new Button("Subtract");
        Button bt_multiply = new Button("Multiply");
        Button bt_divide = new Button("Divide");
        HBox hBox2 = new HBox();
        hBox2.setSpacing(7);
        hBox2.setAlignment(Pos.CENTER);
        hBox2.setPadding(new Insets(10));
        hBox2.getChildren().addAll(bt_add, bt_subtract, bt_multiply, bt_divide);
        BorderPane borderPane = new BorderPane();
        borderPane.setTop(hBox1);
        borderPane.setBottom(hBox2);
        borderPane.setCenter(text);
        borderPane.setAlignment(text, Pos.CENTER);

        Scene scene = new Scene(borderPane);
        primaryStage.setScene(scene);
        primaryStage.setTitle("Demo15_4");
        primaryStage.show();

        bt_add.setOnAction(event -> {
            if (!isEmpty()) {
                try {
                    double numberA = Double.parseDouble(number1);
                    double numberB = Double.parseDouble(number2);
                    tfResult.setText(String.valueOf(numberA + numberB));
                } catch (NumberFormatException e) {
                    tfNumber1.setText("");
                    tfNumber2.setText("");
                    text.setText("请输入数字！！！");
                    e.printStackTrace();
                }
            }
        });
        bt_subtract.setOnAction(event -> {
            if (!isEmpty()) {
                try {
                    double numberA = Double.parseDouble(number1);
                    double numberB = Double.parseDouble(number2);
                    tfResult.setText(String.valueOf(numberA - numberB));
                } catch (NumberFormatException e) {
                    tfNumber1.setText("");
                    tfNumber2.setText("");
                    text.setText("请输入数字！！！");
                    e.printStackTrace();
                }
            }
        });
        bt_multiply.setOnAction(event -> {
            if (!isEmpty()) {
                try {
                    double numberA = Double.parseDouble(number1);
                    double numberB = Double.parseDouble(number2);
                    tfResult.setText(String.valueOf(numberA * numberB));
                } catch (NumberFormatException e) {
                    tfNumber1.setText("");
                    tfNumber2.setText("");
                    text.setText("请输入数字！！！");
                    e.printStackTrace();
                }
            }
        });
        bt_divide.setOnAction(event -> {
            if (!isEmpty()) {
                try {
                    BigDecimal numberA = new BigDecimal(number1);
                    BigDecimal numberB = new BigDecimal(this.number2);
                    tfResult.setText(numberA.divide(numberB).toString());//ArithmeticException
                } catch (NumberFormatException e) {
                    tfNumber1.setText("");
                    tfNumber2.setText("");
                    text.setText("请输入数字！！！");
                    e.printStackTrace();
                } catch (ArithmeticException e) {
                    tfNumber1.setText("");
                    tfNumber2.setText("");
                    text.setText("除数不能为零！！！");
                }
            }
        });
        tfNumber1.setOnMouseClicked(event -> text.setText("确认输入是否正确"));
        tfNumber2.setOnMouseClicked(event -> text.setText("确认输入是否正确"));
    }

    private boolean isEmpty() {
        number1 = tfNumber1.getText().trim();
        number2 = tfNumber2.getText().trim();
        return (number1.equals("") || number2.equals(""));
    }
}
