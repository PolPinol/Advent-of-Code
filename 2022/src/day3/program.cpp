// CPP DAY 3
// @Author: Pol Piñol Castuera

#include "program.h"

namespace day3 {

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

int getPriority(char c) {
  return utils::isUpper(c) ? c - 'A' + 27 : c - 'a' + 1;
}

int Program::silver() {
  int sum = 0;

  for (const auto &line : _lines) {
    std::unordered_map<char, int> map;
    std::string half = line.substr(0, line.length() / 2);

    for (const auto &c : half) {
      map[c] = 1;
    }

    std::string otherHalf = line.substr(line.length() / 2);

    for (const auto &c : otherHalf) {
      if (map[c] > 0) {
        sum += getPriority(c);
        break;
      }
    }
  }

  return sum;
}

int Program::gold() {
  int sum = 0;

  for (auto it = _lines.begin(); it != _lines.end(); it++) {
    std::unordered_map<char, int> map;

    std::string line1 = *it;
    for (const auto &c : line1) {
      map[c] = 1;
    }

    it++;
    std::string line2 = *it;
    for (const auto &c : line2) {
      if (map[c] > 0) {
        map[c] = 2;
      }
    }

    it++;
    std::string line3 = *it;
    for (const auto &c : line3) {
      if (map[c] == 2) {
        sum += getPriority(c);
        break;
      }
    }
  }

  return sum;
}

} // namespace day3
