// HEADER DAY 8
// @Author: Pol Piñol Castuera

#ifndef DAY8_PROGRAM_H_
#define DAY8_PROGRAM_H_

#include "../utils.h"
#include <array>
#include <fstream>
#include <iostream>
#include <string>
#include <unordered_map>
#include <vector>

namespace day8 {
class Program {
public:
  Program();
  void readFile();
  int silver();
  int gold();

private:
  std::vector<std::string> _lines;
};

} // namespace day8

#endif // DAY8_PROGRAM_H_
