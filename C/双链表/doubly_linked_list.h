#ifndef D_LIST
#define D_LIST
#include <stdlib.h>

/* 定义双链表元素结构体 */
typedef struct DListElmt_ 
{
    void *data;
    struct DListElmt_ *previous;
    struct DListElmt_ *next;
} DListElmt;
/* 定义双链表对象结构体 */
typedef struct DList_ 
{
    int size;
    void (*destroy)(void *data);
    DListElmt *head;
    DListElmt *tail;
} DList;
/* 公共接口 */
void d_list_init(DList *list, void (*destroy)(void *data));
void d_list_destroy(DList *list);
/* **data 传入一个指向数据指针的指针, 以便于在外部调用数据指针 */
int d_list_ins_next(DList *list, DListElmt *element, const void *data);
int d_list_rem_next(DList *list, DListElmt *element, void **data);
int d_list_ins_previous(DList *list, DListElmt *element, const void *data);
int d_list_rem_previous(DList *list, DListElmt *element, void **data);
/* 返回某个元素在表中的位置 */
int d_list_find(DList *list, DListElmt *element);
int d_list_find_reverse(DList *list, DListElmt *element);
/* 宏 */
#define d_list_size(List) ((List)->size)
#define d_list_head(List) ((List)->head)
#define d_list_tail(List) ((List)->tail)
#define d_list_is_head(List, element) ((element) == (List)->head ? 1 : 0)
#define d_list_is_tail(List, element) ((element) == (List)->tail ? 1 : 0)
#define d_list_data(element) ((element)->data)
#define d_list_next(element) ((element)->next)
#define d_list_previous(element) ((element)->previous)
#endif