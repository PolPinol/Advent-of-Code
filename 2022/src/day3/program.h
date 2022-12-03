// HEADER DAY 3
// @Author: Pol Piñol Castuera

#ifndef DAY3_PROGRAM_H_
#define DAY3_PROGRAM_H_

#include "../utils.h"
#include <array>
#include <fstream>
#include <iostream>
#include <string>
#include <unordered_map>
#include <vector>

namespace day3 {
class Program {
public:
  Program();
  void readFile();
  int silver();
  int gold();

private:
  std::vector<std::string> _lines;
};

} // namespace day3

#endif // DAY3_PROGRAM_H_
