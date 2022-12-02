#include "day1/program.h"
#include "day2/program.h"
#include <iostream>

int main() {
  int day = 2;
  std::cout << "Day ";

  switch (day) {
  case 1: {
    day1::Program program;
    program.readFile();
    std::cout << "Solution for silver: " << program.silver() << std::endl;
    std::cout << "Solution for gold: " << program.gold() << std::endl;
    break;
  }
  case 2: {
    day2::Program program;
    program.readFile();
    std::cout << "Solution for silver: " << program.silver() << std::endl;
    std::cout << "Solution for gold: " << program.gold() << std::endl;
    break;
  }
  }

  return 0;
}
