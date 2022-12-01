// HEADER DAY 2
// @Author: Pol Piñol Castuera

#ifndef DAY2_PROGRAM_H_
#define DAY2_PROGRAM_H_

#include "../utils.h"
#include <array>
#include <fstream>
#include <iostream>
#include <string>
#include <vector>

namespace day2 {
class Program {
public:
  Program();
  void readFile();
  int silver();
  int gold();

private:
  std::vector<std::string> _lines;
};

} // namespace day2

#endif // DAY2_PROGRAM_H_
