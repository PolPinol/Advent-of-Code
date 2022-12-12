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
    return old * 19;
    // return old * 7;
  case 1:
    return old + 6;
    // return old + 4;
  case 2:
    return old * old;
    // return old + 2;
  case 3:
    return old + 3;
    // return old + 7;
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
    return old * 19;
    // return old * 7;
  case 1:
    return old + 6;
    // return old + 4;
  case 2:
    return old * old;
    // return old + 2;
  case 3:
    return old + 3;
    // return old + 7;
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

int Program::gold() {
  std::cout << std::endl;
  std::vector<BigMonkey> monkeys;
  std::vector<std::tuple<int, std::array<int, 4>, std::vector<int>>> prizes;

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
      std::array<int, 4> arr = {0, 0, 0, 0};
      arr[monkeys.size()] = 1;
      std::vector<int> vec;
      prizes.push_back(
          std::make_tuple(std::stoi(utils::split(items[k], ',')[0]), arr, vec));
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

  // print prizes
  for (int round = 0; round < 20; round++) {
    // print prizes start

    // iterate vector from tuple
    for (int i = 0; i < (int)prizes.size(); i++) {
      // get first from tuple
      const auto get = std::get<2>(prizes[i]);
      for (int k = 0; k < (int)get.size(); k++) {
        std::cout << get[k] << " -> ";
      }
      std::cout << std::endl;
    }
    std::cout << std::endl;

    for (int i = 0; i < (int)prizes.size(); i++) {
      // get first from tuple
      std::cout << std::get<0>(prizes[i]) << " -> ";
      for (int k = 0; k < 4; k++) {
        std::cout << std::get<1>(prizes[i])[k] << " ";
      }
      std::cout << std::endl;
    }
    std::cout << std::endl;
    std::cout << std::endl;

    // iterate monkeys
    for (int i = 0; i < (int)prizes.size(); i++) {
      int item = std::get<0>(prizes[i]);

      for (int k = 0; k < (int)monkeys.size(); k++) {
        if (std::get<1>(prizes[i])[k] == 1) {
          int idMonkey = k;
          int divisible = monkeys[idMonkey].divisible;
          int throwIdTrue = monkeys[idMonkey].monkeyThrowTrue;
          int throwIdFalse = monkeys[idMonkey].monkeyThrowFalse;

          long long level = operateBig(idMonkey, item);
          if (level % divisible == 0) {
            std::get<0>(prizes[i]) = level;
            std::get<1>(prizes[i])[throwIdTrue] = 1;
            std::get<1>(prizes[i])[idMonkey] = 0;
            std::get<2>(prizes[i]).push_back(idMonkey);
          } else {
            std::get<0>(prizes[i]) = level;
            std::get<1>(prizes[i])[throwIdFalse] = 1;
            std::get<1>(prizes[i])[idMonkey] = 0;
            std::get<2>(prizes[i]).push_back(idMonkey);
          }
          break;
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

  std::cout << max << " " << max2 << std::endl;

  return max * max2;
}

} // namespace day11
