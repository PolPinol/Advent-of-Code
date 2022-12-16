// CPP DAY 11
// @Author: Pol Piñol Castuera

#include "program.h"

namespace day11 {

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

int operate(int id, int old) {
  switch (id) {
  case 0:
    return old * 7;
  case 1:
    return old + 4;
  case 2:
    return old + 2;
  case 3:
    return old + 7;
  case 4:
    return old * 17;
  case 5:
    return old + 8;
  case 6:
    return old + 6;
  case 7:
    return old * old;
  }

  return -1;
}

long long operateBig(int id, long long old) {
  switch (id) {
  case 0:
    return old * 7;
  case 1:
    return old + 4;
  case 2:
    return old + 2;
  case 3:
    return old + 7;
  case 4:
    return old * 17;
  case 5:
    return old + 8;
  case 6:
    return old + 6;
  case 7:
    return old * old;
  }

  return -1;
}

class Monkey {
public:
  Monkey(std::queue<int> items, int divisible, int monkeyThrowTrue,
         int monkeyThrowFalse)
      : items(items), divisible(divisible), monkeyThrowTrue(monkeyThrowTrue),
        monkeyThrowFalse(monkeyThrowFalse) {}
  std::queue<int> items;
  int divisible;
  int monkeyThrowTrue;
  int monkeyThrowFalse;
  int inspected = 0;
};

class BigMonkey {
public:
  BigMonkey(std::queue<long long> items, int divisible, int monkeyThrowTrue,
            int monkeyThrowFalse)
      : items(items), divisible(divisible), monkeyThrowTrue(monkeyThrowTrue),
        monkeyThrowFalse(monkeyThrowFalse) {}
  std::queue<long long> items;
  int divisible;
  int monkeyThrowTrue;
  int monkeyThrowFalse;
  int inspected = 0;
};

int Program::silver() {
  std::vector<Monkey> monkeys;

  for (int i = 0; i < (int)_lines.size(); i++) {
    i++;
    std::string line = _lines[i];
    std::vector<std::string> items = utils::split(line, ' ');
    std::queue<int> itemsInt;

    for (int k = 0; k < (int)items.size(); k++) {
      if (k < 4) {
        continue;
      }
      itemsInt.push(std::stoi(utils::split(items[k], ',')[0]));
    }

    i++;
    i++;
    int divisible;
    line = _lines[i];
    std::vector<std::string> div = utils::split(line, ' ');
    divisible = std::stoi(div[5]);

    i++;
    line = _lines[i];
    int throwTrue = std::stoi(utils::split(line, ' ')[9]);

    i++;
    line = _lines[i];
    int throwFalse = std::stoi(utils::split(line, ' ')[9]);
    i++;

    monkeys.push_back(Monkey(itemsInt, divisible, throwTrue, throwFalse));
  }

  for (int round = 0; round < 20; round++) {
    for (int i = 0; i < (int)monkeys.size(); i++) {
      int divisible = monkeys[i].divisible;
      int throwIdTrue = monkeys[i].monkeyThrowTrue;
      int throwIdFalse = monkeys[i].monkeyThrowFalse;

      while (!monkeys[i].items.empty()) {
        int item = monkeys[i].items.front();
        monkeys[i].items.pop();
        monkeys[i].inspected++;

        int level = operate(i, item);
        level = level / 3;

        if (level % divisible == 0) {
          monkeys[throwIdTrue].items.push(level);
        } else {
          monkeys[throwIdFalse].items.push(level);
        }
      }
    }
  }

  int max = 0;
  for (int i = 0; i < (int)monkeys.size(); i++) {
    if (monkeys[i].inspected > max) {
      max = monkeys[i].inspected;
    }
  }

  int max2 = 0;
  for (int i = 0; i < (int)monkeys.size(); i++) {
    if (monkeys[i].inspected > max2 && monkeys[i].inspected < max) {
      max2 = monkeys[i].inspected;
    }
  }

  return max * max2;
}

long long Program::gold() {
  std::vector<BigMonkey> monkeys;

  for (int i = 0; i < (int)_lines.size(); i++) {
    i++;
    std::string line = _lines[i];
    std::vector<std::string> items = utils::split(line, ' ');
    std::queue<long long> itemsInt;

    for (int k = 0; k < (int)items.size(); k++) {
      if (k < 4) {
        continue;
      }
      itemsInt.push(std::stoi(utils::split(items[k], ',')[0]));
    }

    i++;
    i++;
    int divisible;
    line = _lines[i];
    std::vector<std::string> div = utils::split(line, ' ');
    divisible = std::stoi(div[5]);

    i++;
    line = _lines[i];
    int throwTrue = std::stoi(utils::split(line, ' ')[9]);

    i++;
    line = _lines[i];
    int throwFalse = std::stoi(utils::split(line, ' ')[9]);
    i++;

    monkeys.push_back(BigMonkey(itemsInt, divisible, throwTrue, throwFalse));
  }

  for (int round = 0; round < 10000; round++) {
    for (int i = 0; i < (int)monkeys.size(); i++) {
      int divisible = monkeys[i].divisible;
      int throwIdTrue = monkeys[i].monkeyThrowTrue;
      int throwIdFalse = monkeys[i].monkeyThrowFalse;

      while (!monkeys[i].items.empty()) {
        long long item = monkeys[i].items.front();
        monkeys[i].items.pop();
        monkeys[i].inspected++;

        item = item % (17 * 3 * 5 * 7 * 11 * 19 * 2 * 13);
        long long level = operateBig(i, item);

        if (level % divisible == 0) {
          monkeys[throwIdTrue].items.push(level);
        } else {
          monkeys[throwIdFalse].items.push(level);
        }
      }
    }
  }

  long long max = 0;
  for (int i = 0; i < (int)monkeys.size(); i++) {
    if (monkeys[i].inspected > max) {
      max = monkeys[i].inspected;
    }
  }

  long long max2 = 0;
  for (int i = 0; i < (int)monkeys.size(); i++) {
    if (monkeys[i].inspected > max2 && monkeys[i].inspected < max) {
      max2 = monkeys[i].inspected;
    }
  }

  return max * max2;
}

} // namespace day11
