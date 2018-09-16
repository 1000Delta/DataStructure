#include <stdlib.h>
#include <string.h>
#include "doubly_linked_list.h"

void d_list_init(DList *list, void (*destroy)(void *data)) {

    list->size = 0;
    list->destroy = destroy;
    list->head = NULL;
    list->tail = NULL;
}

void d_list_destroy(DList *list) {

    void *data;
    while (list->size > 0) {

        if (d_list_rem(list, NULL, (void **)&data) == 0 && list->destroy != NULL) {

            list->destroy(data);
        }
        memset(list, 0, sizeof(list));
        return; 
    }
}

int d_list_ins_next(DList *list, DListElmt *element, void **data) {

    DListElmt *new_element;
    /* 分配内存 */
    if (new_element = (DListElmt *)malloc(sizeof(DListElmt) == NULL)) {
        return -1;
    }
    new_element->data = data;
    list->head = new_element;
    if (element == NULL) {
        if (list_size(list) == 0)
            list->tail == new_element;
        /* 指定前驱结点 */
        new_element->previous = NULL;
        new_element->next = list->head;
        list->head = new_element;
    }
    else {
        if (element->next == NULL)
            list->tail = new_element;
        /* 指定前驱结点 */
        new_element->previous = element;
        new_element->next = element->next;
        element->next = new_element;
    }
    list->size++;
    return 0;
}

int d_list_rem_next(DList *list, DListElmt *element, void **data)
{
    DListElmt *old_element;
    /* 空链表删除失败 */
    if (list->size == 0) {
        return -1;
    }
    if(element == NULL) {

        data = list->head->data;
        old_element = list->head;
        list->head = list->head->next;
        if (d_list_size(list) == 1) {
            list->tail = NULL;
        }
    }
    else {

        if (element->next == NULL) {
            return -1;
        }
        *data = element->next->data;
        old_element = element->next;
        element->next = element->next->next;
        if (element->next == NULL) {
            list->tail = element;
        }
        else {
            element->next->previous = element;
        }
    }
    free(old_element);
    list->size--;
    return 0;
}
int d_list_ins_previous(DList *list, DListElmt *element, void **data);
int d_list_rem_previous(DList *list, DListElmt *element, void **data);
/* 返回某个元素在表中的位置 */
int d_list_find(DList *list, DListElmt *element);
int d_list_find_reverse(DList *list, DListElmt *element);