# Abstract-syntax trees

## 2.5.1 Abstract and Concrete Syntax

Der AST ist eine Datenstruktur.

## 2.8 intermediate representation
The front end of a compiler constructs an intermediate representation of the source program from which the back end generates the target program. In this section, we consider intermediate representations for expressions and state­ ments, and give tutorial examples of how to produce such representations.

Das compiler frontend produziert eine Zwischenrepräsentation des Quellcodes.
Aus dieser Zwischenrepräsentation wird schlussendlich das Zielprogramm generiert.

Die zwei wichtigsten Zwischenrepräsentationen sind Bäume (parse trees und abstract syntax trees) und Linearrepräsentationen (insbesondere three-address code).

## 2.8.1
During parsing, syntax-tree nodes are created to represent significant programming constructs. As analysis proceeds, information is added to the nodes in the form of attributes associated with the nodes. The choice of attributes depends on the translation to be performed.

## 5.3.1
each node in a syntax tree represents a construct; the children of the node represent the meaningful components of the construct.

A syntax-tree node representing an expression E1 + E2 has label + and two children representing the subexpressions E1 and E2 ·


# Quelle

http://sei.pku.edu.cn/~yaoguo/ACT11/DragonBook-2v2.pdf

Syntax tree 41, 69-70, 92-93, 318- 321, 358, 367, 981-986