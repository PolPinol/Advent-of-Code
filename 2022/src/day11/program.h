// HEADER DAY 11
// @Author: Pol Piñol Castuera

#ifndef DAY11_PROGRAM_H_
#define DAY11_PROGRAM_H_

#include "../utils.h"
#include <array>
#include <fstream>
#include <iostream>
#include <string>
#include <unordered_map>
#include <vector>

namespace day11 {
class Program {
public:
  Program();
  void readFile();
  int silver();
  long long gold();

private:
  std::vector<std::string> _lines;
};

} // namespace day11

#endif // DAY11_PROGRAM_H_
