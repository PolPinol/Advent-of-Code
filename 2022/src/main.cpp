
#include "day1/program.h"
#include <iostream>

int main() {
  int day;
  std::cout << "Day ";
  std::cin >> day;

  switch (day) {
  case 1: {
    day1::Program program;
    program.readFile();
    std::cout << "Solution for silver: " << program.silver() << std::endl;
    std::cout << "Solution for gold: " << program.gold() << std::endl;
    break;
  }
  case 2: {
    break;
  }
  }

  return 0;
}
