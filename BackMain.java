package BackTracking;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;

import java.util.Scanner;

public class BackMain
{

    // permutation of an array using backtracking
    private void permBacktracking( int [] nums, ArrayList<Integer> temp, ArrayList<ArrayList<Integer>> result)
    {
        // base case
        if(temp.size() == nums.length)
        {
            result.add( new ArrayList<>(temp));
            return;
        }

        // logic
        for (int num : nums) {
            if (temp.contains(num)) continue;
            temp.add(num);
            permBacktracking(nums, temp, result);
            temp.remove(temp.size() - 1);
        }
    }
    private ArrayList<ArrayList<Integer>> permutation(int[] nums)
    {
        ArrayList<ArrayList<Integer>> result = new ArrayList<>();
        permBacktracking(nums, new ArrayList<Integer>(), result);
        return result;
    }

    // combination of an array using backtracking
    private ArrayList<ArrayList<Integer>> combination(int [] nums, int k)
    {
        ArrayList<ArrayList<Integer>> result = new ArrayList<>();
        combBacktracking(nums, 0, k, new ArrayList<Integer>(), result);
        return result;
    }
    private void combBacktracking(int[] nums, int start, int k, ArrayList<Integer> temp, ArrayList<ArrayList<Integer>> result)
    {
        if(temp.size() == k)
        {
            result.add(new ArrayList<>(temp));
            return;
        }

        for (int i=start; i<nums.length; i++)
        {
            if(temp.isEmpty() && i == nums.length-1)    continue;
            if(temp.contains(nums[i]))  continue;
            temp.add(nums[i]);
            combBacktracking(nums, start+1, k, temp, result);
            temp.remove(temp.size()-1);
        }
    }

    // Rat in a maze
    private ArrayList<String> ratInMaze(int [][]maze, int order)
    {
        ArrayList<String> result = new ArrayList<>();
        int [][]visited = new int[order][order];
        String temp = "";
        visited[0][0] = 1;
        mazeBacktracking(0, 0, temp, maze, visited, result);
        return result;
    }
    private void mazeBacktracking(int row, int col, String temp, int[][] maze, int[][] visited, ArrayList<String> result)
    {
        // base case
        if(row == visited.length-1 && col == visited.length-1)
        {
            result.add(new String(temp));
            return;
        }

        // down
        if(row+1 <visited.length && visited[row+1][col] == 0 && maze[row+1][col] == 1)
        {
            visited[row+1][col] = 1;
            mazeBacktracking(row+1, col, temp+"D", maze, visited, result);
            visited[row+1][col] = 0;
        }

        // right
        if(col+1 <visited.length && visited[row][col+1] == 0 && maze[row][col+1] == 1)
        {
            visited[row][col+1] = 1;
            mazeBacktracking(row, col+1, temp+"R", maze, visited, result);
            visited[row][col+1] = 0;
        }

        // up
        if(row-1 >=0 && visited[row-1][col] == 0 && maze[row-1][col] == 1)
        {
            visited[row-1][col] = 1;
            mazeBacktracking(row-1, col, temp+"U", maze, visited, result);
            visited[row-1][col] = 0;
        }

        // left
        if(col-1 >=0 && visited[row][col-1] == 0 && maze[row][col-1] == 1)
        {
            visited[row][col-1] = 1;
            mazeBacktracking(row, col-1, temp+"L", maze, visited, result);
            visited[row][col-1] = 0;
        }

    }

    // N queens
    private ArrayList<ArrayList<String>> nQueens(int n)
    {
        char [][] board = new char[n][n];
        for(int i=0; i<n; i++)
            for(int j=0; j<n; j++)
                board[i][j] = '.';

        ArrayList<ArrayList<String>> result = new ArrayList<>();
        queensBacktracking(board, 0, result);
        return result;
    }
    private void queensBacktracking(char[][] board, int row, ArrayList<ArrayList<String>> result)
    {
        // base case
        if(row == board.length)
        {
            ArrayList<String> ans = new ArrayList<>();
            for (char []roww : board)
            {
                ans.add(Arrays.toString(roww));
            }
            result.add(ans);
        }

        for(int j=0; j<board.length; j++)
        {
            if(isSafe(board, row, j))
            {
                board[row][j] = 'Q';
                queensBacktracking(board, row+1, result);
                board[row][j] = '.';
            }
        }
    }
    private boolean isSafe(char[][] board, int row, int col)
    {
        // checking vertical
        for(int i=0; i< board.length; i++)
        {
            if(board[i][col] == 'Q' && i != row)
                return false;
        }

        // checking diagonal 1
        int i=row, j=col;
        while(i>=0 && j>=0)
        {
            i=i-1; j=j-1;
            if(i<0 || j<0)  break;
            if(board[i][j] == 'Q')
                return false;
        }
        i=row; j=col;
        while(i<= board.length-1 && j<= board.length-1)
        {
            i=i+1; j=j+1;
            if(i>board.length-1 || j>board.length-1)  break;
            if(board[i][j] == 'Q')
                return false;
        }

        // checking diagonal 2
        i=row; j=col;
        while(i>=0 && j<=board.length-1)
        {
            i=i-1; j=j+1;
            if(i<0 || j> board.length-1)  break;
            if(board[i][j] == 'Q')
                return false;
        }

        i=row; j=col;
        while(i<= board.length-1 && j>=0)
        {
            i=i+1; j=j-1;
            if( i> board.length-1 || j<0)  break;
            if(board[i][j] == 'Q')
                return false;
        }
        return true;
    }

    public void mainn()
    {
        Scanner scn = new Scanner(System.in);
        System.out.println("Enter the size of the board: ");
        int n = scn.nextInt();
        ArrayList<ArrayList<String>> result = nQueens(n);

       for(ArrayList<String> board : result)
       {
           System.out.println("solution: ");
           for(String row : board)
               System.out.println(row);
           System.out.println();
       }

    }
}
