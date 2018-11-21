import linkedList.LinkedList;
import queue.Queue;
import stack.Stack;

import java.util.Scanner;

public class Main {

    private static final int CIRCLE_MAX_TIMES = 10;

    public static void main (String[] args) {

        // 链表
//         testLinkedList();
        // 栈
//         testStack();
//         后缀表达式();
        // 队列
//        testQueue();
//        test();
    }

    private static void test() {
    
    
    }
    
    private static void testLinkedList() {

        int data = 0;
        LinkedList<Integer> list = new LinkedList<>();
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
        list.insNext(list.getNode(2), data+5);
        System.out.println("插入了位置为[3]的结点, 值为: "+list.getNode(3).getData());
        System.out.println("插入数据为["+(data+5)+"]的结点在位置["+list.find(list.getNode(3))+"]");
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
        Stack<Integer> stack = new Stack<>(valList);
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

        Stack<String> optStack = new Stack<>();
        Stack<String> postfixExp = new Stack<>();
        Stack<String> calcuStack = new Stack<>();
        Scanner in = new Scanner(System.in);
        // 读入一行数据并解析
        String expString = in.nextLine();
        char[] expChars = expString.toCharArray();
        StringBuffer num = new StringBuffer();
        String opt;
        for (char i : expChars) {

            if (i >= '0' && i <= '9') {

                num.append(String.valueOf(i));
            } else if (i == '+' ||
                    i == '-' ||
                    i == '*' ||
                    i == '/' ||
                    i == '(' ||
                    i == ')') {

                if (!"".equals(num.toString())) {

                    // 将读取的数值作为字符串存储
                    postfixExp.push(num.toString());
                    // 清空数字存储器
                    num.delete(0, num.capacity());
                }
                String preOpt = ((optStack.getSize() == 0)?"":optStack.view());
                switch (String.valueOf(String.valueOf(i))) {

                    case "+" :
                    case "-" :
                        while (!"(".equals(preOpt) && !"".equals(preOpt)) {

                            postfixExp.push(optStack.pop());
                            preOpt = ((optStack.getSize() == 0)?"":optStack.view());
                        }
                        optStack.push(String.valueOf(i));
                        break;
                    case "*" :
                    case "/" :
                        while ("*".equals(preOpt) || "/".equals(preOpt)) {

                            postfixExp.push(optStack.pop());
                            preOpt = ((optStack.getSize() == 0)?"":optStack.view());
                        }
                        optStack.push(String.valueOf(i));
                        break;
                    case "(" :
                        optStack.push(String.valueOf(i));
                        break;
                    case ")" :
                        while (!"(".equals(preOpt)) {

                            postfixExp.push(optStack.pop());
                            preOpt = ((optStack.getSize() == 0)?"":optStack.view());
                        }
                        optStack.pop();
                        break;
                    default:
                }
            }
        }
        if (!"".equals(num.toString())) {

            postfixExp.push(num.toString());
            num.delete(0, num.capacity());
        }
        while (optStack.getSize() > 0) {

            postfixExp.push(optStack.pop());
        }
        // 后缀表达式计算
        postfixExp.reverse();
        while (postfixExp.getSize() > 0) {

            try {

                int val = Integer.parseInt(postfixExp.view());
                postfixExp.pop();
                calcuStack.push(String.valueOf(val));
            } catch (NumberFormatException e) {

                int val2 = Integer.parseInt(calcuStack.pop());
                int val1 = Integer.parseInt(calcuStack.pop());
                opt = postfixExp.pop();
                switch (opt) {

                    case "+":
                        val1 += val2;
                        break;
                    case "-":
                        val1 -= val2;
                        break;
                    case "*":
                        val1 *= val2;
                        break;
                    case "/":
                        val1 /= val2;
                        break;
                    default:
                }
                calcuStack.push(String.valueOf(val1));
            }
        }
        System.out.println(expString+"="+calcuStack.view());
    }
    
    private static void testQueue() {
    
        Queue<Integer> q = new Queue<>();
        for (int i = 0; i < 10 * 3; i += 3) {
            
            q.in(i);
            System.out.println("在队列中存入【"+i+"】");
        }
        try {
    
            for (int i = 0; i < 10; i++) {
        
                System.out.println("当前队首为【"+q.read()+"】"+"从队列中取出【"+q.out()+"】");
            }
        } catch (Exception e) {
            
            System.out.println(e.getMessage());
        }
    }
}

