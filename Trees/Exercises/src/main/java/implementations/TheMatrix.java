package implementations;

public class TheMatrix {
    private char[][] matrix;
    private char fillChar;
    private char toBeReplaced;
    private int startRow;
    private int startCol;

    public TheMatrix(char[][] matrix, char fillChar, int startRow, int startCol) {
        this.matrix = matrix;
        this.fillChar = fillChar;
        this.startRow = startRow;
        this.startCol = startCol;
        this.toBeReplaced = this.matrix[this.startRow][this.startCol];
    }

    public void solve() {
        toBeReplaced = matrix[startRow][startCol];
        fillMatrix(startRow,startCol,toBeReplaced,fillChar);
    }

    public String toOutputString() {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < matrix.length ; i++) {
            for (int j = 0; j < matrix[i].length ; j++) {
                sb.append(matrix[i][j]);
            }
            if (i == matrix.length -1){
                break;
            }
            sb.append(System.getProperty("line.separator"));
        }
        return sb.toString();
    }

    private boolean checkInRange(int row, int col){
        return (row >= 0 && row< matrix.length && col >=0 && col<=matrix[0].length);
    }

    private void fillMatrix(int row, int col, char startChar, char fillChar){
        if (row < 0 || row >= matrix.length || col < 0  || col >= matrix[0].length){
            return;
        }

        if (matrix[row][col] != startChar){
            return;
        }

        matrix[row][col] = fillChar;
        fillMatrix(row+1, col,startChar,fillChar); //down
        fillMatrix(row-1, col,startChar,fillChar); //up
        fillMatrix(row, col+1,startChar,fillChar); //right
        fillMatrix(row, col-1,startChar,fillChar); //left
    }
}