#include <iostream>
#include "TutorialConfig.h"
/*#include "stdatomic.h"*/

extern "C"
{
	typedef _Atomic bool atomic_bool;

}

using namespace std;

int main() {
  cout << "Hello, World!" << endl;
	fprintf(stdout, "Version %d", java2c_VERSION);
  return 0;
}
