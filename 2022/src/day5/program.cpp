// CPP DAY 5
// @Author: Pol Piñol Castuera

#include "program.h"

namespace day5 {

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

std::string Program::silver() {
  bool readingStacks = true;

  // initiate stacks
  std::vector<std::stack<char>> stacks;
  std::vector<std::vector<char>> tempStacks;
  std::vector<char> temp = {'F', 'H', 'B', 'V', 'R', 'Q', 'D', 'P'};
  tempStacks.push_back(temp);
  temp = {'L', 'D', 'Z', 'Q', 'W', 'V'};
  tempStacks.push_back(temp);
  temp = {'H', 'L', 'Z', 'Q', 'G', 'R', 'P', 'C'};
  tempStacks.push_back(temp);
  temp = {'R', 'D', 'H', 'F', 'J', 'V', 'B'};
  tempStacks.push_back(temp);
  temp = {'Z', 'W', 'L', 'C'};
  tempStacks.push_back(temp);
  temp = {'J', 'R', 'P', 'N', 'T', 'G', 'V', 'M'};
  tempStacks.push_back(temp);
  temp = {'J', 'R', 'L', 'V', 'M', 'B', 'S'};
  tempStacks.push_back(temp);
  temp = {
      'D',
      'P',
      'J',
  };
  tempStacks.push_back(temp);
  temp = {'D', 'C', 'N', 'W', 'V'};
  tempStacks.push_back(temp);

  for (auto temp : tempStacks) {
    std::stack<char> stack;
    for (auto c : temp) {
      stack.push(c);
    }
    stacks.push_back(stack);
  }

  for (const auto &line : _lines) {
    if (line.empty()) {
      readingStacks = false;
      continue;
    }

    // read moves
    if (!readingStacks) {
      const auto moves = utils::split(line, ' ');
      int numMoves = utils::atoi(moves.at(1));
      int initStack = utils::atoi(moves.at(3)) - 1;
      int endStack = utils::atoi(moves.at(5)) - 1;

      // move
      for (int i = 0; i < numMoves; i++) {
        stacks.at(endStack).push(stacks.at(initStack).top());
        stacks.at(initStack).pop();
      }
    }
  }

  // calculate score
  std::stringstream string_stream;
  for (auto &stack : stacks) {
    string_stream << stack.top();
  }

  return string_stream.str();
}

std::string Program::gold() {
  bool readingStacks = true;

  // initiate stacks
  std::vector<std::stack<char>> stacks;
  std::vector<std::vector<char>> tempStacks;
  std::vector<char> temp = {'F', 'H', 'B', 'V', 'R', 'Q', 'D', 'P'};
  tempStacks.push_back(temp);
  temp = {'L', 'D', 'Z', 'Q', 'W', 'V'};
  tempStacks.push_back(temp);
  temp = {'H', 'L', 'Z', 'Q', 'G', 'R', 'P', 'C'};
  tempStacks.push_back(temp);
  temp = {'R', 'D', 'H', 'F', 'J', 'V', 'B'};
  tempStacks.push_back(temp);
  temp = {'Z', 'W', 'L', 'C'};
  tempStacks.push_back(temp);
  temp = {'J', 'R', 'P', 'N', 'T', 'G', 'V', 'M'};
  tempStacks.push_back(temp);
  temp = {'J', 'R', 'L', 'V', 'M', 'B', 'S'};
  tempStacks.push_back(temp);
  temp = {
      'D',
      'P',
      'J',
  };
  tempStacks.push_back(temp);
  temp = {'D', 'C', 'N', 'W', 'V'};
  tempStacks.push_back(temp);

  for (auto temp : tempStacks) {
    std::stack<char> stack;
    for (auto c : temp) {
      stack.push(c);
    }
    stacks.push_back(stack);
  }

  for (const auto &line : _lines) {
    if (line.empty()) {
      readingStacks = false;
      continue;
    }

    // read moves
    if (!readingStacks) {
      const auto moves = utils::split(line, ' ');
      int numMoves = utils::atoi(moves.at(1));
      int initStack = utils::atoi(moves.at(3)) - 1;
      int endStack = utils::atoi(moves.at(5)) - 1;

      // move
      std::stack<char> outStack;
      for (int i = 0; i < numMoves; i++) {
        outStack.push(stacks.at(initStack).top());
        stacks.at(initStack).pop();
      }

      for (int i = 0; i < numMoves; i++) {
        stacks.at(endStack).push(outStack.top());
        outStack.pop();
      }
    }
  }

  // calculate score
  std::stringstream string_stream;
  for (auto &stack : stacks) {
    string_stream << stack.top();
  }

  return string_stream.str();
}

} // namespace day5
