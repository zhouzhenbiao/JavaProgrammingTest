package chapter20;

import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Text;
import javafx.stage.Stage;

import java.util.*;

/**
 * 纸牌游戏,true
 */
public class Demo20_13 extends Application {
    private ImageView[] imageViews = new ImageView[4];
    private Image[] images;
    private Label label = new Label("输入一个表达式：");
    private TextField tf_expression = new TextField();
    private LinkedList<Integer> list1 = new LinkedList<>();
    private LinkedList<Integer> list2 = new LinkedList<>();

    @Override
    public void start(Stage primaryStage) throws Exception {
        HBox hBox_top = new HBox();
        hBox_top.setSpacing(10);
        hBox_top.setPadding(new Insets(10));
        hBox_top.setAlignment(Pos.CENTER_RIGHT);
        Button bt_shuffler = new Button("Shuffler");
        Text text = new Text("");
        text.setStroke(Color.BLACK);
        hBox_top.getChildren().addAll(text, bt_shuffler);

        HBox hBox_center = new HBox();
        hBox_center.setPadding(new Insets(10));
        hBox_center.setSpacing(7);

        for (int i = 0; i < imageViews.length; i++) {
            imageViews[i] = new ImageView();
        }
        images = getImages();
        imageViews = getImageView(imageViews, images);
        hBox_center.getChildren().addAll(imageViews[0], imageViews[1], imageViews[2], imageViews[3]);

        HBox hBox_bottom = new HBox();
        hBox_bottom.setAlignment(Pos.CENTER_RIGHT);
        hBox_bottom.setSpacing(8);
        hBox_bottom.setPadding(new Insets(10));
        Button bt_verify = new Button("验证");
        hBox_bottom.getChildren().addAll(label, tf_expression, bt_verify);

        BorderPane borderPane = new BorderPane();
        borderPane.setTop(hBox_top);
        borderPane.setCenter(hBox_center);
        borderPane.setBottom(hBox_bottom);
        Scene scene = new Scene(borderPane);
        primaryStage.setScene(scene);
        primaryStage.setTitle("Demo20_13");
        primaryStage.show();

        bt_shuffler.setOnAction(event -> imageViews = getImageView(imageViews, images));
        bt_verify.setOnAction(event -> {
            String s = tf_expression.getText().trim().replaceAll("\\s+", "");
            if (s.length() != 0 || !s.equals("")) {
                try {
                    list2.clear();
                    int expressionValue = evaluateExpression(s);
                    Collections.sort(list1);
                    Collections.sort(list2);
                    if (list1.equals(list2)) {
                        text.setText(expressionValue == 24 ? "正确的表达式：" + expressionValue : "错误的表达式求值：" + expressionValue);
                    } else {
                        text.setText("输入的数字不是 扑克 显示的数字！");
                    }
                } catch (NullPointerException e) {
                    text.setText("请输入 expression");
                } catch (EmptyStackException e) {
                    text.setText("expression 格式错误");
                } catch (Exception e) {
                    text.setText("未知错误");
                }
            } else {
                text.setText("expression null");
            }
        });


    }

    private Image[] getImages() {
        Image[] images = new Image[52];
        for (int i = 0; i < images.length; i++) {
            Image image = new Image("javaFX/chapter15/resource/image/card/" + (i + 1) + ".png");
            images[i] = image;
        }
        return images;
    }

    private ImageView[] getImageView(ImageView[] imageViews, Image[] images) {
        list1.clear();
        for (int i = 0; i < 4; i++) {
            int random = (int) (Math.random() * 52);
            list1.add((random % 13 + 1));
            imageViews[i].setImage(images[random]);
        }
        return imageViews;
    }

    /**
     * Evaluate an expression
     */
    private int evaluateExpression(String expression) {
        Stack<Integer> operandStack = new Stack<>();
        Stack<Character> operatorStack = new Stack<>();
        //因为不能在数字之间的空格和运算符这件的空格权衡，怎么连接数字呀
        for (int i = 0; i < expression.length(); i++) {
            char charAt = expression.charAt(i);
            if (charAt == '+' || charAt == '-') {
                while (!operatorStack.empty() &&
                        (operatorStack.peek() == '+' ||
                                operatorStack.peek() == '-' ||
                                operatorStack.peek() == '*' ||
                                operatorStack.peek() == '/')) {
                    processAnOperator(operandStack, operatorStack);
                }
                operatorStack.push(charAt);
            } else if (charAt == '*' || charAt == '/') {
                while (!operatorStack.empty() &&
                        (operatorStack.peek() == '*' || operatorStack.peek() == '/')) {
                    processAnOperator(operandStack, operatorStack);
                }
                operatorStack.push(charAt);
            } else if (charAt == '(') {
                operatorStack.push(charAt);
            } else if (charAt == ')') {
                //只要看到的不是 '(' 它，就一直处理里面的内容，当看到的是 '(' ，就要把它弹出来
                while (!(operatorStack.peek() == '('))
                    processAnOperator(operandStack, operatorStack);
                operatorStack.pop();
            } else if (charAt >= '0' && charAt <= '9') {
                StringBuilder stringBuilder = new StringBuilder();
                stringBuilder.append(charAt);
                while (i + 1 < expression.length()
                        && expression.charAt(i + 1) >= '0' && expression.charAt(i + 1) <= '9') {
                    stringBuilder.append(expression.charAt(i + 1));
                    i++;
                }
                list2.add(Integer.valueOf(stringBuilder.toString()));
                operandStack.push(Integer.valueOf(stringBuilder.toString()));
            }
        }

        while (!operatorStack.empty()) {
            processAnOperator(operandStack, operatorStack);
        }
        return operandStack.pop();
    }

    /**
     * Process one operator: Take an operator from operatorStack and
     * apply it on the operands in the operandStack 1 2 + 2 2 1 * (1 + 1 1)
     */
    public void processAnOperator(
            Stack<Integer> operandStack, Stack<Character> operatorStack) {
        Integer op1 = operandStack.pop();
        Integer op2 = operandStack.pop();
        Character op = operatorStack.pop();
        if (op == '+')
            operandStack.push(op2 + op1);
        else if (op == '-')
            operandStack.push(op2 - op1);
        else if (op == '*')
            operandStack.push(op2 * op1);
        else if (op == '/')
            operandStack.push(op2 / op1);
    }
}
