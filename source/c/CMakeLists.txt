cmake_minimum_required(VERSION 2.8.4)

# Change compiler with 1 of 3 methods
# 1: CC=gcc-4.2 CXX=/usr/bin/g++-4.2 cmake -G "Your Generator" path/to/your/source
# 2: cmake -G "Your Generator" -D CMAKE_C_COMPILER=gcc-4.2 -D CMAKE_CXX_COMPILER=g++-4.2 path/to/your/source
# 3: set(CMAKE_C_COMPILER "gcc-4.2")
# 3: set(CMAKE_CXX_COMPILER "/usr/bin/g++-4.2")

project(c)

# Setting variables
set(java2c_VERSION 1234)
message("$ENV{PATH}")
set($ENV{HELLO} "world")
# set(CMAKE_CXX_FLAGS "-g -Wall")

# Simple templating
# PROJECT_SOURCE_DIR is the source directory
# PROJECT_BINARY_DIR is the build directory
# So we're outputing a header to the build dir...
#configure_file(
# "$(PROJECT_SOURCE_DIR)/TutorialConfig.h.in"
# "$(PROJECT_BINARY_DIR)/TutorialConfig.h"
#)

configure_file(
 "TutorialConfig.h.in"
 "output/TutorialConfig.h"
)
# add the binary tree to the search path for include files so that we will find TutorialConfig.h
include_directories("output")

#include_directories("/usr/local/Cellar/gcc/4.9.1//lib/gcc/x86_64-apple-darwin12.0.0/4.9.1/include")

set(SOURCE_FILES main.cpp)
add_executable(c ${SOURCE_FILES})
