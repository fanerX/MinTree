package myClass;

import android.util.Log;

import myView.DrawTree;

public class Prime {
     private final int MaxInt = 999;
     private Closedge [] closedge;
     private int[][] arcs;
     private int vexnum;
     private int arcnum;
     private DrawTree drawTree;
     public Prime(DrawTree drawTree){
         Log.d("Prim", "Prime: 初始化");
         this.drawTree=drawTree;
         vexnum=drawTree.points.size();
         closedge=new Closedge[vexnum];
         Log.d("Prim", "closedge.length"+closedge.length);
         arcnum=drawTree.sides.size();
         arcs=new int[vexnum][vexnum];
         int i,j,k;
         for(i=0;i<vexnum;i++){
             for(j=0;j<vexnum;j++){
                 arcs[i][j]=MaxInt;
             }
         }
         DrawTree.Point v1,v2;
         DrawTree.Side side;
         for(k=0;k<arcnum;k++){
             side=drawTree.sides.get(k);
             v1=side.p1;
             v2=side.p2;
             i=drawTree.pointAt(v1);
             j=drawTree.pointAt(v2);
             arcs[j][i]=arcs[i][j]=side.weigth;
             Log.d("Prim", i+" "+j+side.weigth);
         }
         Log.d("Prim", "Prim创建成功！");

    }

    public int MiniSpanTree_Prim(int point){
         drawTree.min_sides.clear();
         int k = point;
         int i,j,num=0;
         for(j=0;j<vexnum;j++){
             if(j!=k){
                 closedge[j]=new Closedge(k,arcs[k][j]);
             }
         }
         closedge[k]=new Closedge(0,0);
         DrawTree.Side side;
         for (i=1;i<vexnum;i++){
             k=Min_closedge();
             if(closedge[k].lowcost==MaxInt){
                 break;
             }
             side=new DrawTree.Side(drawTree.points.get(closedge[k].adjvex),drawTree.points.get(k),closedge[k].lowcost);
             drawTree.min_sides.add(side);
             Log.d("Prim", side.toString());
             num+=closedge[k].lowcost;
             closedge[k].lowcost=0;
             for(j=0;j<vexnum;j++){
                 if(arcs[k][j]<closedge[j].lowcost){
                     closedge[j].adjvex=k;
                     closedge[j].lowcost=arcs[k][j];
                 }
             }
         }
         return num;
    }

    private int Min_closedge(){
        int i,k=-1;
        for(i=0;i<vexnum;i++){
            if(closedge[i].lowcost!=0){
                if(k==-1){
                    k=i;
                }else if(closedge[k].lowcost>closedge[i].lowcost){
                    k=i;
                }
            }
        }
        return k;
    }

    class Closedge{
         public int adjvex;
         public int lowcost;
         public Closedge(int adjvex,int lowcost){
             this.adjvex=adjvex;
             this.lowcost=lowcost;
         }
    }
}


