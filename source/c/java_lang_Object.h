#ifndef _java_lang_Object_h_
#define _java_lang_Object_h_
#include "java2c.h"
#include "java_lang_Object.typedef.h"
#include "java_lang_String.typedef.h"
#include "java_lang_Class.typedef.h"
#include "java_lang_Object.c"
#include <stdint.h>
#include <pthread.h>

java2c_ExternCStart

typedef java_lang_Class *const (*java_lang_Object_getClass)(java_lang_Object *const this);

java_lang_Class *const java_lang_Object_getClass(java_lang_Object *const this);


typedef uint32_t (*java_lang_Object_hashCode)(java_lang_Object *const this);

uint32_t java_lang_Object_hashCode(java_lang_Object *const this);


typedef bool (*java_lang_Object_equals)(java_lang_Object *const this, java_lang_Object *const obj);

bool java_lang_Object_equals(java_lang_Object *const this, java_lang_Object *const obj);


typedef java_lang_Object *const (*java_lang_Object_clone)(java_lang_Object *const this);

java_lang_Object *const java_lang_Object_clone(java_lang_Object *const this);


typedef java_lang_String *const (*java_lang_Object_toString)(java_lang_Object *const this);

java_lang_String *const java_lang_Object_toString(java_lang_Object *const this);


typedef void (*java_lang_Object_notify)(java_lang_Object *const this);

void java_lang_Object_notify(java_lang_Object *const this);


typedef void (*java_lang_Object_notifyAll)(java_lang_Object *const this);

void java_lang_Object_notifyAll(java_lang_Object *const this);


typedef void (*java_lang_Object_wait)(java_lang_Object *const this);

void java_lang_Object_wait(java_lang_Object *const this, uint64_t timeout);


typedef void (*java_lang_Object_wait$A)(java_lang_Object *const this, uint64_t timeout, uint32_t nanos);

void java_lang_Object_wait$A(java_lang_Object *const this, uint64_t timeout, uint32_t nanos);


typedef void (*java_lang_Object_wait$B)(java_lang_Object *const this);

void java_lang_Object_wait$B(java_lang_Object *const this);


typedef void (*java_lang_Object_finalize)(java_lang_Object *const this);

void java_lang_Object_finalize(java_lang_Object *const this);

// https://stackoverflow.com/questions/351733/can-you-write-object-oriented-code-in-c?lq=1
// https://stackoverflow.com/questions/415452/object-orientation-in-c?lq=1
struct java_lang_Object_$vtable
{
	// final
	java_lang_Object_getClass java_lang_Object_getClass;

	java_lang_Object_hashCode java_lang_Object_hashCode;

	java_lang_Object_equals java_lang_Object_equals;

	java_lang_Object_clone java_lang_Object_clone;

	// protected
	java_lang_Object_clone java_lang_Object_clone;

	// final
	java_lang_Object_notify java_lang_Object_notify;

	// final
	java_lang_Object_notifyAll java_lang_Object_notifyAll;

	// final
	java_lang_Object_wait java_lang_Object_wait;

	// final
	java_lang_Object_wait$A java_lang_Object_wait$A;

	// final
	java_lang_Object_wait$B java_lang_Object_wait$B;

	// protected
	java_lang_Object_finalize java_lang_Object_finalize;
} java_lang_Object_vtable =
{
	&java_lang_Object_getClass,
	&java_lang_Object_hashCode,
	&java_lang_Object_equals,
	&java_lang_Object_clone,
	&java_lang_Object_toString,
	&java_lang_Object_notify,
	&java_lang_Object_notifyAll,
	&java_lang_Object_wait,
	&java_lang_Object_wait$A,
	&java_lang_Object_wait$B,
	&java_lang_Object_finalize
};

typedef struct java_lang_Object
{
	struct java_lang_Object_$vtable * vtable;

	pthread_mutex_t $mutex;

	pthread_cond_t $cond;

	ushort synchronisationInitialised;

	/*
		fields go here. Note that private fields can have their name duplicated, so should probably something like 'java_lang_Object_<fieldName>'
	 */
} java_lang_Object;

java2c_ExternCEnd
#endif
