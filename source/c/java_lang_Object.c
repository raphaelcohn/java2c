#include "java_lang_Object.h"
#include <pthread.h>
// just <time.h> on Linux
#include <sys/time.h>
#include <stdbool.h>
java2c_ExternCStart

// native, final, generic
java_lang_Class * const java_lang_Object_getClass(java_lang_Object * const this)
{
	return NULL;
}

// should return object's pointer
uint32_t java_lang_Object_hashCode(java_lang_Object * const this)
{
	return (uint32_t) this;
}

bool java_lang_Object_equals(java_lang_Object * const this, java_lang_Object * const obj)
{
	return (this == obj);
}

// protected
java_lang_Object * const java_lang_Object_clone(java_lang_Object * const this)
{
	//throw new CloneNoteSupportedException();
}

java_lang_String * const java_lang_Object_toString(java_lang_Object * const this)
{
	// return getClass().getName() + "@" + Integer.toHexString(hashCode());
	return java_lang_Object_getClass(this)->getName() + "@" + java_lang_Integer_toHexString(java_lang_Object_hashCode(this));
}

void java_lang_Object_notify(java_lang_Object * const this)
{
	pthread_cond_t instanceCondVariable = PTHREAD_COND_INITIALIZER;
	pthread_cond_signal(&instanceCondVariable);
}

void java_lang_Object_notifyAll(java_lang_Object * const this)
{
	pthread_cond_t instanceCondVariable = PTHREAD_COND_INITIALIZER;
	pthread_cond_broadcast(&instanceCondVariable);
}

void java_lang_Object_wait(java_lang_Object * const this, uint64_t timeout)
{
	// TODO: Support IllegalMonitorStateException
	// TODO: Thread interuption
	java_lang_Object_wait$A(this, timeout, 0L);
}

// http://gcc.gnu.org/onlinedocs/gcc/_005f_005fatomic-Builtins.html
// Legacy: https://gcc.gnu.org/onlinedocs/gcc/_005f_005fsync-Builtins.html#g_t_005f_005fsync-Builtins
// https://gcc.gnu.org/wiki/Atomic/GCCMM/AtomicSync
// http://svnweb.freebsd.org/base/head/include/stdatomic.h?revision=234958&view=markup&pathrev=234958
void java_lang_Object_wait$A(java_lang_Object * const this, uint64_t timeout, uint32_t nanos)
{
	// TODO: Support IllegalMonitorStateException

	if (timeout < 0)
	{
		//throw new IllegalArgumentException("timeout value is negative");
	}

	if (nanos < 0 || nanos > 999999)
	{
		//throw new IllegalArgumentException("nanosecond timeout value out of range");
	}

	struct timeval now;
	gettimeofday(&now, NULL);

	struct timespec timeoutAt;
	xxxx ? not sure this is right
	TIMEVAL_TO_TIMESPEC(&now, &timeoutAt)
	timeoutAt.tv_sec = now.tv_sec + (timeout / 1000LL);
	timeoutAt.tv_nsec = (timeout % 1000LL) * 1000000LL + nanos;

	// (A) inline the structures in our object's data
	// (B) have a pointer to some state; if NULL or 0, then not init'd
	pthread_mutex_t instanceMutexVariable = PTHREAD_MUTEX_INITIALIZER;
	pthread_cond_t instanceCondVariable = PTHREAD_COND_INITIALIZER;
	int result = pthread_cond_timedwait(&instanceCondVariable, &instanceMutexVariable, &timeoutAt);
}

void java_lang_Object_wait$B(java_lang_Object * const this)
{
	// TODO: Support IllegalMonitorStateException


	do
	{
		pthread_mutex_t mutex = __atomic_load_n(&(this->$mutex), __ATOMIC_CONSUME);

	} while(0);


	// needs atomics
	//this->synchronisationInitialised

	pthread_mutex_t instanceMutexVariable = PTHREAD_MUTEX_INITIALIZER;
	pthread_cond_t instanceCondVariable = PTHREAD_COND_INITIALIZER;
	pthread_cond_wait(&instanceCondVariable, &instanceMutexVariable);
}

void java_lang_Object_finalize(java_lang_Object * const this)
{
	// A moderately acceptable place to clean up the mutex and cond variables
}





/* const
	int       *      mutable_pointer_to_mutable_int;
	int const *      mutable_pointer_to_constant_int;
	int       *const constant_pointer_to_mutable_int;
	int const *const constant_pointer_to_constant_int;

 * const always modifies the thing that comes before it, except when it's the first thing (but the above syntax tidies this up)
 * having the 2nd const is equivalent to 'final'
 * having the 1st const is possible when an object is immutable
 */

/* Restrict
 * Can't be used for 'this' unless there are not other arguments of the same (or super) type
 * Not needed 'apparently' if a function is inlined
 * https://duckduckgo.com/l/?kh=-1&uddg=http%3A%2F%2Fwww.research.scea.com%2Fresearch%2Fpdfs%2FGDC2003_Memory_Optimization_18Mar03.pdf
 * https://stackoverflow.com/questions/2005473/rules-for-using-the-restrict-keyword-in-c#2006152
 * http://cellperformance.beyond3d.com/articles/2006/05/demystifying-the-restrict-keyword.html
  */

/*
 * Overloading via:-
 * Clang's overloadable attribute (but might end up being nasty)
 * _Generic (but looks horrendous)
 * Name mangling
 * Order of occurrence (probably best)
 */

java2c_ExternCEnd
