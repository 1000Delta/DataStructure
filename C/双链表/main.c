#include "doubly_linked_list.h"
#include <stdlib.h>
#include <stdio.h>

void destroy(void * data) {

    free((int *)data);
}

int main (void) {

    DList* new_list;
    int i;
    DListElmt *element;

    d_list_init(new_list, destroy);

    puts("请输入数据:");
    for (int i = 0; i < 10; i++) {

        int *tmp = (int *)malloc(sizeof(int));
        scanf("%d", tmp);
        d_list_ins_previous(new_list, NULL, tmp);
    }
    element = d_list_head(new_list);
    i = 0;
    while (element != NULL) {

        printf("第%d个链表元素存放的数字是: %d", i++, d_list_data(element));
        element = element->next;    
    }
    d_list_destroy(new_list);
    getchar();
}