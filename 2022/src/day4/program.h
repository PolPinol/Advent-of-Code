// HEADER DAY 4
// @Author: Pol Piñol Castuera

#ifndef DAY4_PROGRAM_H_
#define DAY4_PROGRAM_H_

#include "../utils.h"
#include <array>
#include <fstream>
#include <iostream>
#include <string>
#include <unordered_map>
#include <vector>

namespace day4 {
class Program {
public:
  Program();
  void readFile();
  int silver();
  int gold();

private:
  std::vector<std::string> _lines;
};

} // namespace day4

#endif // DAY4_PROGRAM_H_
