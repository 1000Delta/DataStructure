import linkedlist.LinkedList;
import stack.Stack;

import java.security.InvalidParameterException;
import java.util.Objects;
import java.util.Scanner;

public class Main {

    private static final int CIRCLE_MAX_TIMES = 10;

    public static void main (String[] args) {

        // 链表
        // testLinkedlist();
        // 栈
        // testStack();
        // 后缀表达式();
    }

    private static void testLinkedlist() {

        Object data = 0;
        LinkedList list = new LinkedList();
        // 测试isEmpty()
        if (list.isEmpty()) {

            System.out.println("链表为空");
        } else {

            System.out.println("链表有"+list.getSize()+"个结点");
        }
        for (int i = 0; i < CIRCLE_MAX_TIMES; i++) {

            Integer tmp = i;
            list.add(tmp);
        }
        if (list.isEmpty()) {

            System.out.println("链表为空");
        } else {

            System.out.println("链表有"+list.getSize()+"个结点");
        }
        /* 添加操作 */
        data = list.remNext(list.getNode(2));
        System.out.println("移除了位置为[3]的结点, 值为: "+data);
        list.insNext(list.getNode(2), Integer.parseInt(data.toString())+5);
        System.out.println("插入了位置为[3]的结点, 值为: "+list.getNode(3).getData());
        System.out.println("插入数据为["+(Integer.parseInt(data.toString())+5)+"]的结点在位置["+list.find(list.getNode(3))+"]");
        for (int i = 0; i < CIRCLE_MAX_TIMES; i++) {

            System.out.println("这是位置为["+i+"]的结点, 数据为"+list.getNode(i).getData());
        }
        list.reverse();
        System.out.println("对链表进行反转");
        for (int i = 0; i < CIRCLE_MAX_TIMES; i++) {

            System.out.println("这是位置为["+i+"]的结点, 数据为"+list.getNode(i).getData());
        }
    }

    private static void testStack() {

        Integer[] valList = {1, 2, 3};
        Stack stack = new Stack(valList);
        System.out.println("初始栈大小: 3");
        while (stack.getSize() > 0) {

            System.out.println("出栈["+stack.pop()+"]"+", 栈大小:["+stack.getSize()+"]");
        }
        int i = 0;
        while (stack.getSize() < 9) {

            stack.push(valList[i]);
            i += 1;
            i %= 3;
            System.out.println("入栈["+stack.view()+"]"+", 栈大小:["+stack.getSize()+"]");
        }
        while (stack.getSize() > 0) {

            System.out.println("出栈["+stack.pop()+"]"+", 栈大小:["+stack.getSize()+"]");
        }
    }

    private static void 后缀表达式() {

        Stack optStack = new Stack();
        Stack postfixExp = new Stack();
        Stack calcuStack = new Stack();
        Scanner in = new Scanner(System.in);
        String expString = in.nextLine();
        char[] expChars = expString.toCharArray();
        StringBuffer num = new StringBuffer();
        char opt;
        for (char i : expChars) {

            if (i >= '0' && i <= '9') {

                num.append(i);
            } else if (i == '+' ||
                    i == '-' ||
                    i == '*' ||
                    i == '/' ||
                    i == '(' ||
                    i == ')') {

                if (!"".equals(num.toString())) {

                    postfixExp.push(Integer.parseInt(num.toString()));
                    num.delete(0, num.capacity());
                }
                char preOpt = ((optStack.getSize() == 0)?0:(char)optStack.view());
                switch (i) {

                    case '+' :
                    case '-' :
                        while (preOpt != '(' && preOpt != 0) {

                            postfixExp.push(optStack.pop());
                            preOpt = ((optStack.getSize() == 0)?0:(char)optStack.view());
                        }
                        optStack.push(i);
                        break;
                    case '*' :
                    case '/' :
                        while (preOpt == '*' || preOpt == '/') {

                            postfixExp.push(optStack.pop());
                            preOpt = ((optStack.getSize() == 0)?0:(char)optStack.view());
                        }
                        optStack.push(i);
                        break;
                    case '(' :
                        optStack.push(i);
                        break;
                    case ')' :
                        while (preOpt != '(') {

                            postfixExp.push(optStack.pop());
                            preOpt = ((optStack.getSize() == 0)?0:(char)optStack.view());
                        }
                        optStack.pop();
                        break;
                    default:
                }
            }
        }
        if (!"".equals(num.toString())) {

            postfixExp.push(Integer.parseInt(num.toString()));
            num.delete(0, num.capacity());
        }
        while (optStack.getSize() > 0) {

            postfixExp.push(optStack.pop());
        }
        // 后缀表达式计算
        postfixExp.reverse();
        while (postfixExp.getSize() > 0) {

            try {

                int val = Integer.parseInt(postfixExp.view().toString());
                postfixExp.pop();
                calcuStack.push(val);
            } catch (NumberFormatException e) {

                int val2 = (int)calcuStack.pop();
                int val1 = (int)calcuStack.pop();
                opt = (char)postfixExp.pop();
                switch (opt) {

                    case '+':
                        val1 += val2;
                        break;
                    case '-':
                        val1 -= val2;
                        break;
                    case '*':
                        val1 *= val2;
                        break;
                    case '/':
                        val1 /= val2;
                        break;
                    default:
                }
                calcuStack.push(val1);
            }
        }
        System.out.println("结果为:"+calcuStack.view());
    }
}

