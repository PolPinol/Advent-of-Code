// HEADER DAY 5
// @Author: Pol Piñol Castuera

#ifndef DAY5_PROGRAM_H_
#define DAY5_PROGRAM_H_

#include "../utils.h"
#include <array>
#include <fstream>
#include <iostream>
#include <string>
#include <unordered_map>
#include <vector>

namespace day5 {
class Program {
public:
  Program();
  void readFile();
  std::string silver();
  std::string gold();

private:
  std::vector<std::string> _lines;
};

} // namespace day5

#endif // DAY5_PROGRAM_H_
