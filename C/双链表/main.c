#include "doubly_linked_list.h"
#include <stdlib.h>
#include <stdio.h>

void destroy(void * data) {

    free(data);
}

int main (void) {

    DList* new_list;
    int i;
    int data;
    DListElmt *element;

    d_list_init(new_list, destroy);

    puts("请输入数据:");
    while (scanf("%d", &data) != EOF) {

        int *tmp = (int *)malloc(sizeof(int));
        *tmp = data;
        d_list_ins_previous(new_list, NULL, (void *)tmp);
    }
    element = new_list->head;
    i = 0;
    while (element != NULL) {

        printf("第%d个链表元素存放的数字是: %d", i++, d_list_data(element));
        element = element->next;    
    }
    d_list_destroy(new_list);
    getchar();
}