// HEADER DAY 9
// @Author: Pol Piñol Castuera

#ifndef DAY9_PROGRAM_H_
#define DAY9_PROGRAM_H_

#include "../utils.h"
#include <array>
#include <fstream>
#include <iostream>
#include <string>
#include <unordered_map>
#include <vector>

namespace day9 {
class Program {
public:
  Program();
  void readFile();
  int silver();
  int gold();

private:
  std::pair<int, int> moveTail(std::pair<int, int> _head,
                               std::pair<int, int> _tail);
  std::vector<std::string> _lines;
};

} // namespace day9

#endif // DAY9_PROGRAM_H_
