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
}

int addPlay(char op, char me) {
  if ((op == 'C' && me == 'X') || (op == 'A' && me == 'Y') ||
      (op == 'B' && me == 'Z')) {
    // win
    return 6;
  } else if ((op == 'A' && me == 'X') || (op == 'B' && me == 'Y') ||
             (op == 'C' && me == 'Z')) {
    // draw
    return 3;
  } else {
    // lose
    return 0;
  }
}

int addSymbol(char me) {
  if (me == 'X') {
    return 1;
  } else if (me == 'Y') {
    return 2;
  } else {
    return 3;
  }
}

int addPlayGold(char op, char me) {
  if ((op == 'B' && me == 'X') || (op == 'A' && me == 'Y') ||
      (op == 'C' && me == 'Z')) {
    // plays X
    return 1;
  } else if ((op == 'C' && me == 'X') || (op == 'B' && me == 'Y') ||
             (op == 'A' && me == 'Z')) {
    // plays Y
    return 2;
  } else {
    // plays Z
    return 3;
  }
}

int addSymbolGold(char me) {
  if (me == 'X') {
    return 0;
  } else if (me == 'Y') {
    return 3;
  } else {
    return 6;
  }
}

int Program::silver() {
  int sum = 0;
  for (const auto &line : _lines) {
    const auto play = utils::split(line, ' ');
    sum += addSymbol(play.at(1)[0]);
    sum += addPlay(play.at(0)[0], play.at(1)[0]);
  }

  return sum;
}

int Program::gold() {
  int sum = 0;
  for (const auto &line : _lines) {
    const auto play = utils::split(line, ' ');
    sum += addSymbolGold(play.at(1)[0]);
    sum += addPlayGold(play.at(0)[0], play.at(1)[0]);
  }

  return sum;
}

} // namespace day2
