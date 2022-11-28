// CPP UTILS
// @Author: Pol Piñol Castuera

#include "utils.h"

namespace utils {

int atoi(std::string s) { return std::stoi(s); }

std::string itoa(int i) {
  std::stringstream ss;
  ss << i;
  return ss.str();
}

std::vector<std::string> split(std::string s, char delimiter) {
  std::vector<std::string> tokens;
  std::string token;
  std::istringstream tokenStream(s);
  while (std::getline(tokenStream, token, delimiter)) {
    tokens.push_back(token);
  }
  return tokens;
}

void sort(std::vector<int> &v) { std::sort(v.begin(), v.end()); }

// ascii to int
int atoai(char c) { return c - '0'; }

// int to ascii
char itoac(int i) { return i + '0'; }

// create matrix
std::vector<std::vector<int>> initMatrix(int rows, int cols, int defaultValue) {
  std::vector<std::vector<int>> matrix(rows,
                                       std::vector<int>(cols, defaultValue));

  // iterate matrix
  // for (int i = 0; i < matrix.size(); i++) {
  //   for (int j = 0; j < matrix[i].size(); j++) {
  //     std::cout << matrix[i][j] << " ";
  //   }

  return matrix;
}

// is upper case
bool isUpper(char c) { return c >= 'A' && c <= 'Z'; }

// search in vector
bool search(std::vector<int> v, int value) {
  return std::find(v.begin(), v.end(), value) != v.end();
}

// search in unordered_set
bool searchHashMap(std::unordered_set<int> s, int value) {
  return s.find(value) != s.end();
}

////////////////////////////////////////////////////////////
// FIFO LIFO
//
// initialitze a queue
// std::queue<int> queue;
// queue.push(1);
//
// initialize a stack
// std::stack<int> stack;
// stack.push(1);
//
////////////////////////////////////////////////////////////
// HASH MAP
//
// initialize a unordered map
// std::unordered_map<int, int> unordered_map;
// unordered_map.insert(std::pair<int, int>(1, 1));
//
////////////////////////////////////////////////////////////
// STRING BUILDER
//
// initialize a string stream
// std::stringstream string_stream;
// string_stream << "string";
//
////////////////////////////////////////////////////////////
// TUPLE
//
// initialize a pair
// std::pair<int, int> pair;
// pair = std::make_pair(1, 1);
//
// initialize a tuple
// std::tuple<int, int, int> tuple;
// tuple = std::make_tuple(1, 1, 1);
//
////////////////////////////////////////////////////////////
// ARRAYS
//
// initialize a vector
// std::vector<int> vector;
// vector.push_back(1);
//
// initialize a array
// std::array<int, 3> array;
// array = {1, 1, 1};
//
// initialize a unordered set
// std::unordered_set<int> unordered_set;
// unordered_set.insert(1);
//
////////////////////////////////////////////////////////////

} // namespace utils
