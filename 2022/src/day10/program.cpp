// CPP DAY 10
// @Author: Pol Piñol Castuera

#include "program.h"

namespace day10 {

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
  long long x = 1;
  int sum = 0;
  int cycle = 0;
  std::vector<int> mults = {20, 60, 100, 140, 180, 220};

  for (const auto &line : _lines) {
    const auto cmd = utils::split(line, ' ');
    if (cmd.at(0).compare("noop") == 0) {
      // noop operation
      cycle++;
      if (std::find(mults.begin(), mults.end(), cycle) != mults.end()) {
        sum += x * cycle;
      }
    } else {
      // addx operation
      cycle++;
      if (std::find(mults.begin(), mults.end(), cycle) != mults.end()) {
        sum += x * cycle;
      }

      cycle++;
      if (std::find(mults.begin(), mults.end(), cycle) != mults.end()) {
        sum += x * cycle;
      }

      x += std::stoi(cmd.at(1));
    }
  }

  return sum;
}

int Program::gold() {
  int x = 0;
  int sum = 0;
  int cycle = -1;
  int row = 0;
  std::vector<int> mults = {40, 80, 120, 160, 200, 240};

  std::cout << std::endl;

  // inititate matrix
  auto matrix = utils::initMatrix(6, 40, '.');

  for (const auto &line : _lines) {
    const auto cmd = utils::split(line, ' ');
    if (cmd.at(0).compare("noop") == 0) {
      // noop operation
      cycle++;
      if (cycle == x || cycle == x + 1 || cycle == x + 2) {
        matrix[row][cycle] = '#';
      }
      if (std::find(mults.begin(), mults.end(), cycle) != mults.end()) {
        row++;
        cycle -= 40;
      }
    } else {
      // addx operation
      cycle++;
      if (cycle == x || cycle == x + 1 || cycle == x + 2) {
        matrix[row][cycle] = '#';
      }
      if (std::find(mults.begin(), mults.end(), cycle) != mults.end()) {
        row++;
        cycle -= 40;
      }

      cycle++;
      if (cycle == x || cycle == x + 1 || cycle == x + 2) {
        matrix[row][cycle] = '#';
      }
      if (std::find(mults.begin(), mults.end(), cycle) != mults.end()) {
        row++;
        cycle -= 40;
      }

      x += std::stoi(cmd.at(1));
    }
  }

  for (int i = 0; i < (int)matrix.size(); i++) {
    for (int j = 0; j < (int)matrix[i].size(); j++) {
      if (j == 0) {
        std::cout << "#";
      } else {
        std::cout << (char)matrix[i][j];
      }
    }
    std::cout << std::endl;
  }
  std::cout << std::endl;

  return sum;
}

} // namespace day10
