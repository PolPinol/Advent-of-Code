// CPP DAY 2
// @Author: Pol Piñol Castuera

#include "program.h"

namespace day2 {

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
  for (const auto &line : _lines) {
  }

  return 0;
}

int Program::gold() {
  for (const auto &line : _lines) {
  }

  return 0;
}

} // namespace day2
