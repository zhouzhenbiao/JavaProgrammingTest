package javaFX.chapter15;

import javafx.application.Application;
import javafx.geometry.HPos;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;

/**
 * 本例采用事件驱动编程以及 GUI 组件开发一个贷款计算器
 */
public class LoanCalculator extends Application {

    private TextField tfAnnualInterestRate = new TextField();
    private TextField tfNumberOfYears = new TextField();
    private TextField tfLoanAmount = new TextField();
    private TextField tfMonthlyPayment = new TextField();
    private TextField tfTotalPayment = new TextField();
    private Button btCalculator = new Button("Calculator(计算器)");
    private Button btReset = new Button("reset(重置文本框)");


    @Override
    public void start(Stage primaryStage) throws Exception {
        GridPane gridPane = new GridPane();
        //Hgap 水平间距
        gridPane.setHgap(5);
        //Vgap 垂直间距
        gridPane.setVgap(5);
        gridPane.setPadding(new Insets(15, 15, 15, 15));

        gridPane.add(new Label("Annual Interest Rate(年利率) : "), 0, 0);
        gridPane.add(tfAnnualInterestRate, 1, 0);
        gridPane.add(new Label("Number of Years(贷款的年数) : "), 0, 1);
        gridPane.add(tfNumberOfYears, 1, 1);
        gridPane.add(new Label("Loan Amount(贷款额) : "), 0, 2);
        gridPane.add(tfLoanAmount, 1, 2);
        gridPane.add(new Label("Monthly Payment(月付款数) : "), 0, 3);
        gridPane.add(tfMonthlyPayment, 1, 3);
        gridPane.add(new Label("Total Payment(付款总额) : "), 0, 4);
        gridPane.add(tfTotalPayment, 1, 4);
        gridPane.add(btReset, 0, 5);
        gridPane.add(btCalculator, 1, 5);

        //每个网格居中对齐
        gridPane.setAlignment(Pos.CENTER);
        //所有文本域右对齐
        tfAnnualInterestRate.setAlignment(Pos.BOTTOM_RIGHT);
        tfNumberOfYears.setAlignment(Pos.BOTTOM_RIGHT);
        tfLoanAmount.setAlignment(Pos.BOTTOM_RIGHT);
        tfMonthlyPayment.setAlignment(Pos.BOTTOM_RIGHT);
        tfTotalPayment.setAlignment(Pos.BOTTOM_RIGHT);
        //自动得出月付款数和付款总额，并且不可修改的那种
        tfMonthlyPayment.setEditable(false);
        tfTotalPayment.setEditable(false);
        //按钮右对齐
        gridPane.setHalignment(btReset, HPos.CENTER);
        gridPane.setHalignment(btCalculator, HPos.RIGHT);

        //处理按钮事件
        btCalculator.setOnAction(
                event -> {
                    double interest = Double.parseDouble(tfAnnualInterestRate.getText());
                    int year = Integer.parseInt(tfNumberOfYears.getText());
                    double loanAmount = Double.parseDouble(tfLoanAmount.getText());
                    double tatolPayment = loanAmount * Math.pow((1 + interest), year);
                    tfMonthlyPayment.setText(String.format("%.2f", tatolPayment / year / 12));
                    tfTotalPayment.setText(String.format("%.2f", tatolPayment));
                });
        btReset.setOnAction(
                event -> {
                    tfAnnualInterestRate.setText("");
                    tfNumberOfYears.setText("");
                    tfLoanAmount.setText("");
                    tfMonthlyPayment.setText("");
                    tfTotalPayment.setText("");
                });

        Scene scene = new Scene(gridPane);
        primaryStage.setScene(scene);
        primaryStage.setTitle("LoanCalculator(贷款计算器)");
        primaryStage.show();
    }
}