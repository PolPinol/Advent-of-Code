// HEADER DAY 7
// @Author: Pol Piñol Castuera

#ifndef DAY7_PROGRAM_H_
#define DAY7_PROGRAM_H_

#include "../utils.h"
#include <array>
#include <fstream>
#include <iostream>
#include <string>
#include <unordered_map>
#include <vector>

namespace day7 {
class Node {
public:
  Node(std::string name);
  Node(std::string name, Node *father);
  Node(std::string name, int value, Node *father);
  Node *father;
  int value;
  std::string name;
  std::vector<std::unique_ptr<Node>> sons;
};

class Program {
public:
  Program();
  void readFile();
  int silver();
  int gold();

private:
  void dfsSilver(Node *node);
  void dfsGold(Node *node);
  std::vector<std::string> _lines;
  Node *_tree;
  int _sumSilver = 0;
  int _solGold = 0;
  int _maxGold = 0;
  int _totalSize = 0;
};

} // namespace day7

#endif // DAY7_PROGRAM_H_
