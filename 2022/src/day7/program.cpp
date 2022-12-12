// CPP DAY 7
// @Author: Pol Piñol Castuera

#include "program.h"
#include <stack>

namespace day7 {

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

Node::Node(std::string name) {
  this->value = 0;
  this->name = name;
  this->father = NULL;
}

Node::Node(std::string name, Node *father) {
  this->value = 0;
  this->name = name;
  this->father = father;
}

Node::Node(std::string name, int value, Node *father) {
  this->name = name;
  this->value = value;
  this->father = father;
}

void Program::dfsSilver(Node *node) {
  int suma = 0;

  for (auto &son : node->sons) {
    dfsSilver(son.get());
  }

  // node is a dir
  if (node->value == 0) {
    for (auto &son : node->sons) {
      suma += son->value;
    }
    node->value = suma;
  }

  // Solution for silver
  if (!node->sons.empty() && node->value <= 100000) {
    _sumSilver += node->value;
  }
}

void Program::dfsGold(Node *node) {
  for (auto &son : node->sons) {
    dfsGold(son.get());
  }

  // Solution for gold
  int unusedSpace = 70000000 - _totalSize;
  if (!node->sons.empty() && unusedSpace + node->value >= 30000000) {
    if (_totalSize - node->value > _maxGold) {
      _maxGold = _totalSize - node->value;
      _solGold = node->value;
    }
  }
}

int Program::silver() {
  Node tree("\\");
  _tree = &tree;
  Node *actual = &tree;

  for (const auto &line : _lines) {
    auto cmd = utils::split(line, ' ');
    if (cmd.at(0).compare("$") == 0) {
      if (cmd.at(1).compare("cd") == 0) {
        // cd
        if (cmd.at(2).compare("\\") == 0) {
        } else if (cmd.at(2).compare("..") == 0) {
          actual = actual->father;
        } else {
          for (auto &son : actual->sons) {
            if (son->name.compare(cmd.at(2)) == 0) {
              actual = son.get();
              break;
            }
          }
        }
      } else {
        // ls
      }
    } else {
      if (cmd.at(0).compare("dir") == 0) {
        // create dir
        actual->sons.push_back(std::make_unique<Node>(cmd.at(1), actual));
      } else {
        // create file
        actual->sons.push_back(
            std::make_unique<Node>(cmd.at(1), std::stoi(cmd.at(0)), actual));
      }
    }
  }

  dfsSilver(_tree);

  _totalSize = _tree->value;

  dfsGold(_tree);

  return _sumSilver;
}

int Program::gold() { return _solGold; }

} // namespace day7
