package implementations;

import interfaces.Solvable;

import java.util.Stack;

public class BalancedParentheses implements Solvable {
    private String parentheses;
    private  final char[][] Parenthesis = {{'(',')'},{'{','}'},{'[',']'}};
    public BalancedParentheses(String parentheses) {
        this.parentheses = parentheses;
    }
    private boolean IsOpenParenthesis(char clamp){
        for (char[] tokens : Parenthesis) {
            if (clamp == tokens[0] ){
                return true;
            }
        }
        return false;
    }

    @Override
    public Boolean solve() {
        Stack<Character> stack = new Stack<>();
        char[] parenthesesArr = this.parentheses.toCharArray();
        for (char part : parenthesesArr) {
            if (IsOpenParenthesis(part)) {
                stack.push(part);
            } else {
                if(stack.isEmpty() || (!(IsMatch(stack.pop(),part)))){
                    return false;
                }
            }
        }
        return true;
    }
    
    private boolean IsMatch(char openChar, char closeChar){
        for (char[] charPair :this.Parenthesis) {
            if (openChar == charPair[0]){
                if (closeChar == charPair[1]){
                    return true;
                }
                return false;
            }
        }
        return false;
    }
}