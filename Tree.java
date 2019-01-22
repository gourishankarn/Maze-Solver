package mazesolver;

import java.awt.Color;
import java.awt.Graphics;
import java.util.ArrayList;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;
import javax.swing.JFrame;
import javax.swing.SwingUtilities;
import java.lang.Math;

class Node
{
    int[] val = new int[2];
    Node left;
    Node right;
    public Node(int x, int y)
    {
            val[0]=x;
            val[1]=y;
            left=right=null;
    }
}

public class Tree extends JFrame {
    Node root;
    Node current;
    
    Tree(int x, int y)
    {
        root = new Node(x,y);
    }
    Tree()
    {
        root = null;
    }
    public void addNode(int x,int y)
    {
        Node newNode = new Node(x,y); 
        if(current.left==null)
        {
            current.left=newNode;
            current=current.left;
        }
        else
        {
            current.right=newNode;
            current=current.right;
        }
    }
    public void printPreOrder(Node traverser)
    {
        if(traverser == null)
            return;
        System.out.println(traverser.val[0]+" "+traverser.val[1]);
        System.out.println("Left:");
        printPreOrder(traverser.left);
        System.out.println("Right:");
        printPreOrder(traverser.right);     
    }
    
    public int contains(Node checker, int x, int y)
    {
        int le=0,re=0;
        if(checker == null)
            return 0;
        if(checker.val[0]==x && checker.val[1]==y)
        {
            current=checker;
            return 1;
        }
        le = le+ contains(checker.left,x,y);
        re = re+ contains(checker.right,x,y);
        return le+re;
    }   
    public void finalP(int ix)
    {
        for(int i=ix;i<Astarpath.size()-2;i=i+2)
        {
            if(((Astarpath.get(i+2)==Astarpath.get(i)))&&((Astarpath.get(i+3)!=Astarpath.get(i+1))))
            {
                if((Astarpath.get(i+3))>(Astarpath.get(i+1)))
                {
                for(int j=Astarpath.get(i+1);j<Astarpath.get(i+3);j++)
                {
                    finalpath.add(Astarpath.get(i));
                    finalpath.add(j+1);
                }
                }
                else if((Astarpath.get(i+3))<(Astarpath.get(i+1))) 
                {
                for(int j=Astarpath.get(i+1);j>Astarpath.get(i+3);j--)
                {
                    finalpath.add(Astarpath.get(i));
                    finalpath.add(j-1);
                }  
                }
            }  
            else if(((Astarpath.get(i+2)!=Astarpath.get(i)))&&((Astarpath.get(i+3)==Astarpath.get(i+1))))
            {
                if((Astarpath.get(i+2))>(Astarpath.get(i)))
                {
                for(int j=Astarpath.get(i);j<Astarpath.get(i+2);j++)
                {
                    finalpath.add(j+1);
                    finalpath.add(Astarpath.get(i+1));
                }
                }
                else if((Astarpath.get(i+2))<(Astarpath.get(i)))
                {
                for(int j=Astarpath.get(i);j>Astarpath.get(i+2);j--)
                {
                    finalpath.add(j-1);
                    finalpath.add(Astarpath.get(i+1));
                }
                }
            }  
        }
    }
    public void Astar(Node start)
    {
        System.out.println("Astar Started");
        double d1=distance,dm1=0,d2=distance,dm2=0;
        int x1,x2,y1,y2;
        x1=start.val[0];
        y1=start.val[1];
        if(start.left!=null)
        {
            System.out.println("Astar Left");
            x2=start.left.val[0];
            y2=start.left.val[1];
            d1=d1+Math.sqrt(((y1-y2)*(y1-y2))+((x1-x2)*(x1-x2)));
            dm1=d1+Math.sqrt(((8-y2)*(8-y2))+((11-x2)*(11-x2)));
        }
        else
        {   
            System.out.println("d1 is 100");
            
            dm1=100;           
        }
        if(start.right!=null)
        {
            System.out.println("Astar Right");
            x2=start.right.val[0];
            y2=start.right.val[1];
            d2=d2+Math.sqrt(((y1-y2)*(y1-y2))+((x1-x2)*(x1-x2)));
            dm2=d2+Math.sqrt(((8-y2)*(8-y2))+((11-x2)*(11-x2)));
        }
        else
        {   
            System.out.println("d2 is 100");
            dm2=100;           
        }
        if(dm1!=100||dm2!=100)
        {    
            System.out.println("both d1 n d2 are not 100");
            if(dm1<dm2)
            {
                Astarpath.add(start.left.val[0]);
                Astarpath.add(start.left.val[1]);
                System.out.println("GO LEFT");
                distance=d1;
                Astar(start.left);
            }
            else
            {
                Astarpath.add(start.right.val[0]);
                Astarpath.add(start.right.val[1]);
                System.out.println("GO RIGHT");
                distance=d2;
                Astar(start.right);
            }
        }
    }
    
    private int [][] maze = 
        { {1,1,1,1,1,1,1,1,1,1,1,1,1},
          {1,0,1,0,1,0,1,0,0,0,0,0,1},
          {1,0,1,0,0,0,1,0,1,1,1,0,1},
          {1,0,0,0,1,1,1,0,0,0,0,0,1},
          {1,0,1,0,0,0,0,0,1,1,1,0,1},
          {1,0,1,0,8,1,1,1,1,0,0,0,1},
          {1,0,1,0,1,0,0,0,1,1,1,0,1},
          {1,0,1,0,1,1,1,0,1,0,1,0,1},
          {1,0,0,0,0,0,0,0,0,0,1,0,1},
          {1,1,1,1,1,1,1,1,1,1,1,1,1}
        };
    int flag = 0;
    private final List<Integer> ins = new ArrayList();
    private final List<Integer> path = new ArrayList();
    private final List<Integer> temppath = new ArrayList();
    private int pathIndex = 0;
    private final List<Integer> Astarpath = new ArrayList();
    private final List<Integer> finalpath = new ArrayList();
    double distance=0;
    int smax=0;
    char prev='S';
    int fullflag=0;
    
    public void View() {
        setTitle("Simple Maze Solver");
        setSize(640, 480);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        searchPath(maze, 1, 1);

	/*System.out.println("-----------------------");
        for (int path : path) {
		System.out.println(path);
		}*/   
	System.out.println("-----------------------");
        for (int ins : ins) {
		System.out.println(ins);
		}   
        System.out.println("Terminated");
        printPreOrder(root);   
        Astarpath.add(1);
        Astarpath.add(1);
        Astar(root);  
        System.out.println("-----------------------");
        for (int Astarpath : Astarpath) {
		System.out.println(Astarpath);
		}  
        finalpath.add(Astarpath.get(0));
        finalpath.add(Astarpath.get(1));
        finalP(0);
        System.out.println("-----------------------");
        for (int finalpath : finalpath) {
		System.out.println(finalpath);
		}  
        flag=1;
            new Timer().schedule(new TimerTask() {
            @Override
            public void run() {
                if(pathIndex < (path.size()-2))
                {
                    pathIndex = pathIndex + 2;
                    repaint();
                }
                if(pathIndex == path.size()-2)
                {
                    fullflag=1;
                    repaint();
                    cancel();
                }
            }
        }, 100, 250);    
    }
       
    public int max()
    {
        int max = 0;
             for(int i=0; i<10; i++){
                for(int j=0; j<13; j++){
                    if(maze[i][j]>max)
                    {
                        smax=max;
                        max = maze[i][j];
                    }
                }
            }
        return max;
    }
    
    public void searchPath(int[][] maze, int x, int y) {        
        System.out.println(x+" "+y);
        if(max()==7 && smax==2)
        {
            System.out.println("Fully traversed");
            return;
        }
        if(maze[y][x]==8)
        {
            maze[y][x]=7;
            path.add(x);
            path.add(y);
            System.out.println("SOLVED");
            if(contains(root,x,y)==0)
                addNode(x,y);
            if(max()==9)
            {
            for(int i=0; i<10; i++){
                for(int j=0; j<13; j++){
                    if(maze[i][j] == 9)
                    {
                        x=j;
                        y=i;
                    }
                }
            }
            prev='E';
            searchPath(maze,x,y);
            }
            else
                System.out.println("Fully traversed");
            return;
        }
        else if(maze[y][x]==0||maze[y][x]==9)
        {
            path.add(x);
            path.add(y);
            if(maze[y+1][x]==0||maze[y+1][x]==8)
            {
            if(prev=='S')
                prev='D';
            else if('D'!=prev)
            {  
                if(contains(root,x,y)==0)
                    addNode(x,y);
                prev='D';
            }
            System.out.println("Went Down");
		if((maze[y-1][x]==0)||(maze[y][x+1]==0)||(maze[y][x-1]==0))
		{
			maze[y][x]=9;
                        ins.add(x);
                        ins.add(y);
                        System.out.println("intersection added");
                        if(contains(root,x,y)==0)
                            addNode(x,y);
		}
		else
		{
                    if(maze[y][x]!=8)
                    {   
			maze[y][x]=2;
                    }
                }
                searchPath(maze,x,y+1);
            }
            else if(maze[y][x+1]==0||maze[y][x+1]==8)
            {     
            if(prev=='S')
                prev='R';
            else if('R'!=prev)
            {  
                if(contains(root,x,y)==0)
                    addNode(x,y);
                prev='R';
            }                
            System.out.println("Went Right");
		if((maze[y-1][x]==0)||(maze[y+1][x]==0)||(maze[y][x-1]==0))
		{
			maze[y][x]=9;                     
                        ins.add(x);
                        ins.add(y); 
                        System.out.println("intersection added");
                        if(contains(root,x,y)==0)
                            addNode(x,y);
                }
		else
		{
                    if(maze[y][x]!=8)
                    {
			maze[y][x]=2;
                    }                     
		}
                    searchPath(maze,x+1,y);
            }
            else if(maze[y][x-1]==0||maze[y][x-1]==8)
            {             
            if(prev=='S')
                prev='L';
            else if('L'!=prev)
            {  
                if(contains(root,x,y)==0)
                    addNode(x,y);
                prev='L';
            }
                System.out.println("Went Left");
                if((maze[y-1][x]==0)||(maze[y+1][x]==0)||(maze[y][x+1]==0))
                {
			maze[y][x]=9;
                        ins.add(x);
                        ins.add(y);
                        System.out.println("intersection added");
                        if(contains(root,x,y)==0)
                            addNode(x,y);
                }
		else
		{
                    if(maze[y][x]!=8)
                    {
			maze[y][x]=2;
                    }
		}
                    searchPath(maze,x-1,y);
            }
            else if(maze[y-1][x]==0||maze[y-1][x]==8)
            {             
            if(prev=='S')
                prev='U';
            else if('U'!=prev)
            {  
                if(contains(root,x,y)==0)
                    addNode(x,y);
                prev='U';
            }
                System.out.println("Went Up");
		if((maze[y][x+1]==0)||(maze[y+1][x]==0)||(maze[y][x-1]==0))
		{
			maze[y][x]=9;
                        ins.add(x);
                        ins.add(y);
                        System.out.println("intersection added");
                        if(contains(root,x,y)==0)
                            addNode(x,y);
                }
		else
		{
                    if(maze[y][x]!=8)
                    {
			maze[y][x]=2;
                    }
                }
		searchPath(maze,x,y-1);
            }
            else
            {
                System.out.println("ENTERED");
                prev='E';
                if(maze[y][x]==8)
                {
                    path.add(x);
                    path.add(y);
                    searchPath(maze,x,y);
                }
                if((maze[y+1][x]==9)||(maze[y][x+1]==9)||(maze[y-1][x]==9)||(maze[y][x-1]==9))
                {
                    if(maze[y+1][x]==9)
                    {
                        if(contains(root,x,y)==0)
                            addNode(x,y+1);
                    }
                    else if(maze[y][x+1]==9)
                    {
                        if(contains(root,x,y)==0)
                            addNode(x+1,y);
                    }
                    else if(maze[y-1][x]==9)
                    {
                        if(contains(root,x,y)==0)
                            addNode(x,y-1);
                    }
                    else if(maze[y][x-1]==9)
                    {
                        if(contains(root,x,y)==0)
                            addNode(x-1,y);
                    }
                }
                if((maze[y][x]!=8)&&(maze[y-1][x]!=2)||(maze[y][x+1]!=2)||(maze[y+1][x]!=2)||(maze[y][x-1]!=2))
                {
                    maze[y][x]=2;
                }                      
                if(ins.size()!=0)
                {
                    x=ins.get(0);
                    y=ins.get(1);
                    ins.remove(0);
                    ins.remove(0);
                    contains(root,x,y);
                    searchPath(maze,x,y);
                }
            }
        }
    }
    
    @Override
    public void paint(Graphics g) {
        super.paint(g);
        
        g.translate(50, 50);
        
        // draw the maze
        for (int row = 0; row < maze.length; row++) {
            for (int col = 0; col < maze[0].length; col++) {
                Color color;
                switch (maze[row][col]) {
                    case 1 : color = Color.BLACK; break;
                    case 8 : case 7: color = Color.RED; break;
                    default : color = Color.WHITE;
                }
                g.setColor(color);
                g.fillRect(30 * col, 30 * row, 30, 30);
                g.setColor(Color.BLACK);
                g.drawRect(30 * col, 30 * row, 30, 30);
            }
        }
        if(fullflag==0)
        {
        if(pathIndex < path.size()-2)
        {
            int p=0;
            temppath.add(path.get(pathIndex));
            temppath.add(path.get(pathIndex));
            for (p = 0; p < temppath.size(); p += 2) {
                int temppathX = path.get(p);
                int temppathY = path.get(p + 1);
                g.setColor(Color.GREEN);
                g.fillRect(temppathX * 30, temppathY * 30, 30, 30);
            }
        }
        }
        else
        {
            for (int p = 0; p < finalpath.size(); p += 2) {
                int finalpathX = finalpath.get(p);
                int finalpathY = finalpath.get(p + 1);
                g.setColor(Color.GREEN);
                g.fillRect(finalpathX * 30, finalpathY * 30, 30, 30);
                }
        }    
        //g.fillOval(pathX * 30, pathY * 30, 30, 30);
    }
    
    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                Tree tree = new Tree();
                tree.root=new Node(1,1);
                tree.current = tree.root;
                tree.View();
                if(tree.flag!=0)
                    System.out.println("WAITING");
                    tree.setVisible(true);
            }
        });
    }
    
}
