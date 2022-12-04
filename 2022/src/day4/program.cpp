// CPP DAY 4
// @Author: Pol Piñol Castuera

#include "program.h"

namespace day4 {

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
}

int Program::silver() {
  int sum = 0;

  for (const auto &line : _lines) {
    const auto sections = utils::split(line, ',');
    const auto section0 = utils::split(sections.at(0), '-');
    const auto section1 = utils::split(sections.at(1), '-');

    // Check if section1 is inside section0
    int min = std::stoi(section0.at(0));
    int max = std::stoi(section0.at(1));

    int x0 = std::stoi(section1.at(0));
    int x1 = std::stoi(section1.at(1));

    if (min <= x0 && x1 <= max) {
      sum++;
      continue;
    }

    // Check if section0 is inside section1
    min = std::stoi(section1.at(0));
    max = std::stoi(section1.at(1));

    x0 = std::stoi(section0.at(0));
    x1 = std::stoi(section0.at(1));

    if (min <= x0 && x1 <= max) {
      sum++;
    }
  }

  return sum;
}

int Program::gold() {
  int sum = 0;

  for (const auto &line : _lines) {
    const auto sections = utils::split(line, ',');
    const auto section0 = utils::split(sections.at(0), '-');
    const auto section1 = utils::split(sections.at(1), '-');

    int x0_0 = std::stoi(section0.at(0));
    int x1_0 = std::stoi(section0.at(1));

    int x0_1 = std::stoi(section1.at(0));
    int x1_1 = std::stoi(section1.at(1));

    if (x0_0 < x1_1) {
      if (x1_0 >= x0_1) {
        sum++;
      }
    } else {
      if (x1_1 >= x0_0) {
        sum++;
      }
    }
  }

  return sum;
}

} // namespace day4
