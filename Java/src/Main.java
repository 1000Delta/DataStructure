import LinkedList.LinkedList;

import java.util.Objects;

public class Main {

    private static final int CIRCLE_MAX_TIMES = 10;

    public static void main (String[] args) {

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
        for (int i = 0; i < CIRCLE_MAX_TIMES; i++) {

            System.out.println("这是位置为["+i+"]的结点, 数据为"+list.getNode(i).getData());
        }
    }
}
