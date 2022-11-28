// HEADER DAY 1
// @Author: Pol Piñol Castuera

#ifndef DAY1_PROGRAM_H_
#define DAY1_PROGRAM_H_

#include "../utils.h"
#include <fstream>
#include <iostream>
#include <string>
#include <vector>

namespace day1 {
class Program {
public:
  Program();
  void readFile();
  int silver();
  int gold();

private:
  std::vector<std::string> _lines;
};

} // namespace day1

#endif // DAY1_PROGRAM_H_
