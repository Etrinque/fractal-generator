package trinque.eric.fractalGenerator.drawable.lsystem

// L-System is a lexical analysis - or parallel rewriting system - used to generate geometric structure output.
// It consists of an:
//      - Alphabet - the set pf symbols used to create strings
//      - Rules - The set of steps/actions to perform when a match is found
//      - Axiom - the starting condition - subset of Alphabet
//      - Angle of List<Angle>- optional but recommended
//class Rule(val key: Char, val value: String)  {
//
//}

typealias Alphabet = Char
typealias Rule = Pair<Alphabet, String>

class LSystem(
    var alphabet: List<Char>, // Alpha ex. A, B, F, G or Symbols ex. +, - , [, ]
    var axiom: String,  // ex. F+-F-G
    var rules: List<Rule>, // ex. Rule< key: F, value: F+-F-G> replace every key with value.
    var iterations: Int, // How many times to repeat the algorithm.
    var angle: Double?,
    val angleList: List<Double>?
) {

}