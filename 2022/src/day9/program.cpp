// CPP DAY 9
// @Author: Pol Piñol Castuera

#include "program.h"

namespace day9 {

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

std::pair<int, int> Program::moveTail(std::pair<int, int> _head,
                                      std::pair<int, int> _tail) {
  int diff1 = abs(_head.first - _tail.first);
  int diff2 = abs(_head.second - _tail.second);

  // don't move tail
  if ((diff1 == 0 && diff2 == 1) || (diff2 == 0 && diff1 == 1) ||
      (diff1 == 1 && diff2 == 1) || (diff1 == 0 && diff2 == 0)) {
    return _tail;
  }

  // diagonal cases
  if (diff1 == 2 && diff2 == 2) {
    if (_head.first > _tail.first && _head.second > _tail.second) {
      _tail.first += 1;
      _tail.second += 1;
    } else if (_head.first > _tail.first && _head.second < _tail.second) {
      _tail.first += 1;
      _tail.second -= 1;
    } else if (_head.first < _tail.first && _head.second > _tail.second) {
      _tail.first -= 1;
      _tail.second += 1;
    } else if (_head.first < _tail.first && _head.second < _tail.second) {
      _tail.first -= 1;
      _tail.second -= 1;
    }
  } else if ((diff1 == 2 && diff2 == 1) || (diff1 == 1 && diff2 == 2)) {
    if (diff1 == 2) {
      _tail.second = _head.second;
      if (_head.first > _tail.first) {
        _tail.first += 1;
      } else {
        _tail.first -= 1;
      }
    } else {
      _tail.first = _head.first;
      if (_head.second > _tail.second) {
        _tail.second += 1;
      } else {
        _tail.second -= 1;
      }
    }
  } else if (diff1 > 0) {
    if (_head.first > _tail.first) {
      _tail.first += 1;
    } else {
      _tail.first -= 1;
    }
  } else if (diff2 > 0) {
    if (_head.second > _tail.second) {
      _tail.second += 1;
    } else {
      _tail.second -= 1;
    }
  } else {
    return _tail;
  }

  return _tail;
}

int Program::silver() {
  int sum = 0;

  std::pair<int, int> _tail = {0, 0};
  std::pair<int, int> _head = {0, 0};
  std::unordered_map<std::string, int> _visited;
  _visited["0,0"] = 1;

  for (const auto &line : _lines) {
    const auto movs = utils::split(line, ' ');
    switch (movs[0].at(0)) {
    case 'R': {
      int times = std::stoi(movs[1]);
      for (int i = 0; i < times; ++i) {
        // move head
        _head.second += 1;
        _tail = moveTail(_head, _tail);

        // mark coordinate as visited
        std::string key =
            std::to_string(_tail.first) + "," + std::to_string(_tail.second);
        _visited[key] = 1;
      }

      break;
    }
    case 'L': {
      int times = std::stoi(movs[1]);
      for (int i = 0; i < times; ++i) {
        // move head
        _head.second -= 1;
        _tail = moveTail(_head, _tail);

        // mark coordinate as visited
        std::string key =
            std::to_string(_tail.first) + "," + std::to_string(_tail.second);
        _visited[key] = 1;
      }
      break;
    }
    case 'U': {
      int times = std::stoi(movs[1]);
      for (int i = 0; i < times; ++i) {
        // move head
        _head.first += 1;
        _tail = moveTail(_head, _tail);

        // mark coordinate as visited
        std::string key =
            std::to_string(_tail.first) + "," + std::to_string(_tail.second);
        _visited[key] = 1;
      }
      break;
    }
    case 'D': {
      int times = std::stoi(movs[1]);
      for (int i = 0; i < times; ++i) {
        // move head
        _head.first -= 1;
        _tail = moveTail(_head, _tail);

        // mark coordinate as visited
        std::string key =
            std::to_string(_tail.first) + "," + std::to_string(_tail.second);
        _visited[key] = 1;
      }
      break;
    }
    }
  }

  // count visited
  for (const auto &v : _visited) {
    if (v.second == 1) {
      sum++;
    }
  }

  return sum;
}

int Program::gold() {
  int sum = 0;

  std::pair<int, int> _head = {0, 0};
  std::vector<std::pair<int, int>> tails;
  std::unordered_map<std::string, int> _visited;
  _visited["0,0"] = 1;
  for (int i = 0; i < 9; ++i) {
    tails.push_back({0, 0});
  }

  for (const auto &line : _lines) {
    const auto movs = utils::split(line, ' ');
    switch (movs[0].at(0)) {
    case 'R': {
      int times = std::stoi(movs[1]);
      for (int i = 0; i < times; ++i) {
        // move head
        _head.second += 1;

        // move tails
        auto copyHead = _head;
        for (int j = 0; j < 9; ++j) {
          tails[j] = moveTail(copyHead, tails[j]);
          copyHead = tails[j];
        }

        // mark coordinate as visited
        std::string key = std::to_string(tails[8].first) + "," +
                          std::to_string(tails[8].second);
        _visited[key] = 1;
      }

      break;
    }
    case 'L': {
      int times = std::stoi(movs[1]);
      for (int i = 0; i < times; ++i) {
        // move head
        _head.second -= 1;

        // move tails
        auto copyHead = _head;
        for (int j = 0; j < 9; ++j) {
          tails[j] = moveTail(copyHead, tails[j]);
          copyHead = tails[j];
        }

        // mark coordinate as visited
        std::string key = std::to_string(tails[8].first) + "," +
                          std::to_string(tails[8].second);
        _visited[key] = 1;
      }
      break;
    }
    case 'U': {
      int times = std::stoi(movs[1]);
      for (int i = 0; i < times; ++i) {
        // move head
        _head.first += 1;

        // move tails
        auto copyHead = _head;
        for (int j = 0; j < 9; ++j) {
          tails[j] = moveTail(copyHead, tails[j]);
          copyHead = tails[j];
        }

        // mark coordinate as visited
        std::string key = std::to_string(tails[8].first) + "," +
                          std::to_string(tails[8].second);
        _visited[key] = 1;
      }
      break;
    }
    case 'D': {
      int times = std::stoi(movs[1]);
      for (int i = 0; i < times; ++i) {
        // move head
        _head.first -= 1;

        // move tails
        auto copyHead = _head;
        for (int j = 0; j < 9; ++j) {
          tails[j] = moveTail(copyHead, tails[j]);
          copyHead = tails[j];
        }

        // mark coordinate as visited
        std::string key = std::to_string(tails[8].first) + "," +
                          std::to_string(tails[8].second);
        _visited[key] = 1;
      }
      break;
    }
    }
  }

  // count visited
  for (const auto &v : _visited) {
    if (v.second == 1) {
      sum++;
    }
  }

  return sum;
}

} // namespace day9
