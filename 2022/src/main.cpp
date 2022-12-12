#include "day1/program.h"
#include "day10/program.h"
#include "day11/program.h"
#include "day2/program.h"
#include "day3/program.h"
#include "day4/program.h"
#include "day5/program.h"
#include "day7/program.h"
#include "day8/program.h"
#include "day9/program.h"
#include <iostream>

int main() {
  int day = 11;

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
  case 3: {
    day3::Program program;
    program.readFile();
    std::cout << "Solution for silver: " << program.silver() << std::endl;
    std::cout << "Solution for gold: " << program.gold() << std::endl;
    break;
  }
  case 4: {
    day4::Program program;
    program.readFile();
    std::cout << "Solution for silver: " << program.silver() << std::endl;
    std::cout << "Solution for gold: " << program.gold() << std::endl;
    break;
  }
  case 5: {
    day5::Program program;
    program.readFile();
    std::cout << "Solution for silver: " << program.silver() << std::endl;
    std::cout << "Solution for gold: " << program.gold() << std::endl;
    break;
  }
  case 7: {
    day7::Program program;
    program.readFile();
    std::cout << "Solution for silver: " << program.silver() << std::endl;
    std::cout << "Solution for gold: " << program.gold() << std::endl;
    break;
  }
  case 8: {
    day8::Program program;
    program.readFile();
    std::cout << "Solution for silver: " << program.silver() << std::endl;
    std::cout << "Solution for gold: " << program.gold() << std::endl;
    break;
  }
  case 9: {
    day9::Program program;
    program.readFile();
    std::cout << "Solution for silver: " << program.silver() << std::endl;
    std::cout << "Solution for gold: " << program.gold() << std::endl;
    break;
  }
  case 10: {
    day10::Program program;
    program.readFile();
    std::cout << "Solution for silver: " << program.silver() << std::endl;
    std::cout << "Solution for gold: " << program.gold() << std::endl;
    break;
  }
  case 11: {
    day11::Program program;
    program.readFile();
    std::cout << "Solution for silver: " << program.silver() << std::endl;
    std::cout << "Solution for gold: " << program.gold() << std::endl;
    break;
  }
  default:
    std::cout << "No solution for day " << day << std::endl;
    break;
  }

  return 0;
}
