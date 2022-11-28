// CPP DAY 1
// @Author: Pol Piñol Castuera

#include "program.h"

namespace day1 {

Program::Program() {}

void Program::readFile() {
  std::ifstream myfile("input.txt");
  if (myfile.is_open()) {
    std::string line;
    while (std::getline(myfile, line)) {
      _lines.push_back(line);
    }
    myfile.close();
  }

  for (const auto &line : _lines) {
    std::cout << line << std::endl;
  }
}

int Program::silver() {
  //
  return 1;
}

int Program::gold() {
  //
  return 1;
}

} // namespace day1
