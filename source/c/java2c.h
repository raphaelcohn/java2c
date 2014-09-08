#ifndef _java2c_h_
#define _java2c_h_
#include <stdint.h>
#include <stdlib.h>

#ifdef __cplusplus
#define java2c_ExternCStart extern "C" {
#define java2c_ExternCEnd }
#define java2c_restrict
#else
#define java2c_ExternCStart
#define java2c_ExternCEnd
#define java2c_restrict restrict
#endif

java2c_ExternCStart

typedef void (*java2c_functionPointer)(void);

// reserved methods available to us include
// super, this, new, do, while, for, strictfp, float, double, int, long, void, etc

java2c_ExternCEnd

#endif
