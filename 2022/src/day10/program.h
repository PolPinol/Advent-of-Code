// HEADER DAY 10
// @Author: Pol Piñol Castuera

#ifndef DAY10_PROGRAM_H_
#define DAY10_PROGRAM_H_

#include "../utils.h"
#include <array>
#include <fstream>
#include <iostream>
#include <string>
#include <unordered_map>
#include <vector>

namespace day10 {
class Program {
public:
  Program();
  void readFile();
  int silver();
  int gold();

private:
  std::vector<std::string> _lines;
};

} // namespace day10

#endif // DAY10_PROGRAM_H_
