// HEADER UTILS
// @Author: Pol Piñol Castuera

#ifndef UTILS_PROGRAM_H_
#define UTILS_PROGRAM_H_

#include <cstdlib>
#include <fstream>
#include <iostream>
#include <queue>
#include <sstream>
#include <string>
#include <tuple>
#include <unordered_set>
#include <vector>

namespace utils {
// Convert string to int
int atoi(std::string s);

// Convert int to string
std::string itoa(int i);

// Split string
std::vector<std::string> split(std::string s, char delimiter);

// Sort vector
void sort(std::vector<int> &v);

// Convert char to int
int atoai(char c);

// Convert int to char
char itoac(int i);

// Create matrix
std::vector<std::vector<int>> initMatrix(int rows, int cols, int defaultValue);

// Is upper case
bool isUpper(char c);

// Search in vector
bool search(std::vector<int> v, int value);

// search in unordered_set
bool searchHashMap(std::unordered_set<int> s, int value);

} // namespace utils

#endif // UTILS_PROGRAM_H_
