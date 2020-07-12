import java.util.*;

/**
 * sudoku
 */
public class sudoku {
    private List<Set<Integer>> rows=new ArrayList<Set<Integer>>(10),columns=new ArrayList<Set<Integer>>(10),boxes=new ArrayList<Set<Integer>>(10);
    private int[][] helper=new int[10][10];
    private void sudokufiller(int[][] arr,int i,int j,int count){
        for(int temp:new LinkedList<Integer>(rows.get(i))){
            if(columns.get(j).contains(temp) && boxes.get(helper[i][j]).contains(temp) ){
                rows.get(i).remove(temp);
                columns.get(j).remove(temp);
                boxes.get(helper[i][j]).remove(temp);
                arr[i][j]=temp;
                for (int a = 1; a <= 9; a++) {
                    for (int b = 1; b <= 9; b++){
                        System.out.println(count);
                        if(arr[a][b]==0) sudokufiller(arr,a,b,count-1);
                        if(count==0) return;
                    }
                }
                rows.get(i).add(temp);
                columns.get(j).add(temp);
                boxes.get(helper[i][j]).add(temp);
                arr[i][j]=0;
            }   
        }
        return;
    }
    void sudokusolve(int[][] arr,int count){
        for(int i=1;i<=3;i++){
            for(int j=1;j<=3;j++){
                helper[i][j]=1;
                helper[i][j+3]=2;
                helper[i][j+6]=3;
                helper[i+3][j]=4;
                helper[i+3][j+3]=5;
                helper[i+3][j+6]=6;
                helper[i+6][j]=7;
                helper[i+6][j+3]=8;
                helper[i+6][j+6]=9;
            }
        }
        rows.add(new HashSet<>());
        columns.add(new HashSet<>());
        boxes.add(new HashSet<>());
        for (int i = 1; i < arr.length; i++) {
            rows.add(new HashSet<>());
            columns.add(new HashSet<>());
            boxes.add(new HashSet<>());
            for(int j=1;j < arr.length;j++){
                rows.get(i).add(j);
                columns.get(i).add(j);
            }
        }
        for (int i = 1; i < arr.length; i++) {
            Set<Integer> temp= boxes.get(i);
            for (int j = 1; j < arr.length; j++) temp.add(j);

        }
        for (int i = 1; i <= 9; i++) {
            for (int j = 1; j <= 9; j++) {
                if(arr[i][j]==0) continue;
                rows.get(i).remove(arr[i][j]);
                columns.get(j).remove(arr[i][j]);
                boxes.get(helper[i][j]).remove(arr[i][j]);
            }
        }
        sudokufiller(arr, 1, 1, count);
    }
    public static void main(String[] args) {
        System.out.println("Hii what's up?");
        int[][] arr=new int[10][10];
        //int count=0;
        Scanner scan= new Scanner(System.in);
        sudoku test=new sudoku();
        for(int i=1;i<=9;i++){
            for(int j=1;j<=9;j++)
                arr[i][j]=scan.nextInt();
                //count+=(arr[i-1][j-1]==0?1:0);
        }
        test.sudokusolve(arr,42);
        for (int i = 1; i <= 9; i++) {
            System.out.println();
            for (int j = 1; j <= 9; j++) 
                System.out.print(arr[i][j]+" ");
        }
        scan.close();
    }
}
