package chapter20;

import java.util.Scanner;
import java.util.Stack;

public class EvaluateExpression {
    public static void main(String[] args) {
        // Check number of arguments passed
        Scanner input = new Scanner(System.in);
        String s = input.nextLine().trim();
        s = s.replaceAll("\\s+", "");
        System.out.println(s);
        try {
            System.out.println(evaluateExpression(s));
        } catch (Exception ex) {
            System.out.println("Wrong expression: " + s);
        }
    }

    /**
     * Evaluate an expression
     */
    public static int evaluateExpression(String expression) {
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
    public static void processAnOperator(
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