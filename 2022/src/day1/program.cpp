// CPP DAY 1
// @Author: Pol Piñol Castuera

#include "program.h"

namespace day1 {

Program::Program() {}

void Program::readFile() {
  std::ifstream myfile("file.dat");
  if (myfile.is_open()) {
    std::string line;
    while (std::getline(myfile, line)) {
      _lines.push_back(line);
    }
    myfile.close();
  }

  // add empty line at the end
  _lines.push_back("");
}

int Program::silver() {
  int max = 0;
  int elf = 0;

  for (const auto &line : _lines) {
    if (line.empty()) {
      if (elf > max) {
        max = elf;
      }
      elf = 0;
    } else {
      elf += utils::atoi(line);
    }
  }

  return max;
}

int Program::gold() {
  // temporal elf
  int elf = 0;

  // solution elfs
  std::array<int, 3> elfs = {0, 0, 0};

  for (const auto &line : _lines) {
    if (line.empty()) {
      std::sort(std::begin(elfs), std::end(elfs));

      if (elf > elfs[2]) {
        int temp = elfs[2];
        elfs[2] = elf;
        int temp1 = elfs[1];
        elfs[1] = temp;
        elfs[0] = temp1;
      } else if (elf > elfs[1]) {
        int temp = elfs[1];
        elfs[1] = elf;
        elfs[0] = temp;
      } else if (elf > elfs[0]) {
        elfs[0] = elf;
      }
      elf = 0;
    } else {
      elf += utils::atoi(line);
    }
  }

  return elfs[0] + elfs[1] + elfs[2];
}

} // namespace day1
