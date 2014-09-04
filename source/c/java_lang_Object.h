#ifndef _java_lang_Object_h_
#define _java_lang_Object_h_
#include "java2c.h"
#include "java_lang_Object.typedef.h"
#include "java_lang_String.typedef.h"
#include "java_lang_Class.typedef.h"
#include <stdint.h>
#include <pthread.h>
#import <sys/types.h>

java2c_ExternCStart

java_lang_Class * const java_lang_Object_getClass(java_lang_Object * const this);

uint32_t java_lang_Object_hashCode(java_lang_Object * const this);

_Bool java_lang_Object_equals(java_lang_Object * const this, java_lang_Object * const obj);

java_lang_Object * const java_lang_Object_clone(java_lang_Object * const this);

java_lang_String * const java_lang_Object_toString(java_lang_Object * const this);

void java_lang_Object_notify(java_lang_Object * const this);

void java_lang_Object_notifyAll(java_lang_Object * const this);

void java_lang_Object_wait(java_lang_Object * const this, uint64_t timeout);

void java_lang_Object_wait$A(java_lang_Object * const this, uint64_t timeout, uint32_t nanos);

void java_lang_Object_wait$B(java_lang_Object * const this);

void java_lang_Object_finalize(java_lang_Object * const this);

// these are the 'methods' of Object. We also have fields. Final methods have been omitted (this may be problematic with inheritance, though
// ? do we need to include protected methods ?
// should we split data from methods ? ie have a vtable ?
// prepopulation of this structure is important
typedef struct java_lang_Object
{
	pthread_mutex_t $mutex;

	pthread_cond_t $cond;

	ushort synchronisationInitialised;

	// final
	// java_lang_Class * const (* getClass) (java_lang_Object * const this);

	uint32_t (* hashCode) (java_lang_Object * const this);

	_Bool (* equals) (java_lang_Object * const this, java_lang_Object * const obj);

	java_lang_Object * const (* clone) (java_lang_Object * const this);

	// protected
	java_lang_String * const (* toString) (java_lang_Object * const this);

	// final
	// void (* notify) (java_lang_Object * const this);

	// final
	// void (* notifyAll) (java_lang_Object * const this);

	// final
	// void (* wait) (java_lang_Object * const this, uint64_t timeout);

	// final
	// void (* wait$A) (java_lang_Object * const this, uint64_t timeout, uint32_t nanos);

	// final
	// void (* wait$B) (java_lang_Object * const this);

	// protected
	void (* finalize) (java_lang_Object * const this);

} java_lang_Object;

java2c_ExternCEnd
#endif
