// CPP DAY 8
// @Author: Pol Piñol Castuera

#include "program.h"

namespace day8 {

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

  std::vector<std::vector<int>> matrix;
  std::vector<std::vector<int>> visibleTrees =
      utils::initMatrix(_lines.size(), _lines[0].size(), 0);

  for (const auto &line : _lines) {
    std::vector<int> row;
    for (const auto &c : line) {
      row.push_back(c - '0');
    }
    matrix.push_back(row);
  }

  // left to right
  for (int i = 0; i < (int)matrix.size(); i++) {
    int max = -1;
    for (int j = 0; j < (int)matrix[0].size(); j++) {
      if (max < matrix[i][j]) {
        max = matrix[i][j];
        visibleTrees[i][j] = 1;
      }
    }
  }

  // top to bottom
  for (int i = 0; i < (int)matrix[0].size(); i++) {
    int max = -1;
    for (int j = 0; j < (int)matrix.size(); j++) {
      if (max < matrix[j][i]) {
        max = matrix[j][i];
        visibleTrees[j][i] = 1;
      }
    }
  }

  // bottom to top
  for (int i = matrix[0].size() - 1; i >= 0; i--) {
    int max = -1;
    for (int j = matrix.size() - 1; j >= 0; j--) {
      if (max < matrix[j][i]) {
        max = matrix[j][i];
        visibleTrees[j][i] = 1;
      }
    }
  }

  // right to left
  for (int i = matrix.size() - 1; i >= 0; i--) {
    int max = -1;
    for (int j = matrix[0].size() - 1; j >= 0; j--) {
      if (max < matrix[i][j]) {
        max = matrix[i][j];
        visibleTrees[i][j] = 1;
      }
    }
  }

  for (int i = 0; i < (int)matrix.size(); i++) {
    for (int j = 0; j < (int)matrix[0].size(); j++) {
      if (visibleTrees[i][j] == 1) {
        sum++;
      }
    }
  }

  return sum;
}

int Program::gold() {
  int max = 0;

  std::vector<std::vector<int>> matrix;
  std::vector<std::vector<int>> trees =
      utils::initMatrix(_lines.size(), _lines[0].size(), 0);

  for (const auto &line : _lines) {
    std::vector<int> row;
    for (const auto &c : line) {
      row.push_back(c - '0');
    }
    matrix.push_back(row);
  }

  for (int i = 0; i < (int)matrix.size(); i++) {
    for (int j = 0; j < (int)matrix[0].size(); j++) {
      int val = matrix[i][j];

      // look up
      int sum = 0;
      for (int k = i - 1; k >= 0; k--) {
        sum++;
        if (matrix[k][j] >= val) {
          break;
        }
      }
      trees[i][j] = sum;

      // look down
      sum = 0;
      for (int k = i + 1; k < (int)matrix.size(); k++) {
        sum++;
        if (matrix[k][j] >= val) {
          break;
        }
      }
      trees[i][j] *= sum;

      // look left
      sum = 0;
      for (int k = j - 1; k >= 0; k--) {
        sum++;
        if (matrix[i][k] >= val) {
          break;
        }
      }
      trees[i][j] *= sum;

      // look right
      sum = 0;
      for (int k = j + 1; k < (int)matrix[0].size(); k++) {
        sum++;
        if (matrix[i][k] >= val) {
          break;
        }
      }
      trees[i][j] *= sum;
    }
  }

  for (int i = 0; i < (int)matrix.size(); i++) {
    for (int j = 0; j < (int)matrix[0].size(); j++) {
      if (i == 0 || j == 0 || i == (int)matrix.size() - 1 ||
          j == (int)matrix[0].size() - 1) {
      } else {
        if (max < trees[i][j]) {
          max = trees[i][j];
        }
      }
    }
  }

  return max;
}

} // namespace day8
