package myClass;

import myView.DrawTree;

public class Kruskal {
    private final int MaxInt = 999;
    private Edge [] edge;
    private int [] vexset;
    private int arcnum;
    private int vexnum;
    private DrawTree drawTree;

    public Kruskal(DrawTree drawTree){
        this.drawTree=drawTree;
        vexnum=drawTree.points.size();
        arcnum=drawTree.sides.size();
        edge=new Edge[arcnum];
        vexset=new int[vexnum];
    }


    public int MiniSpanTree_Kruskal(){
        int i,j,k,v1,v2,vs1,vs2,num;
        drawTree.min_sides.clear();
        num=0;
        DrawTree.Side side;
        for(i=0;i<arcnum;i++){
            side=drawTree.sides.get(i);
            v1=drawTree.pointAt(side.p1);
            v2=drawTree.pointAt(side.p2);
            edge[i]=new Edge(v1,v2,side.weigth);
        }
        Edge e;
        for(i=0;i<arcnum-1;i++){
            k=i;
            for(j=i+1;j<arcnum;j++){
                if(edge[k].lowcost>edge[j].lowcost){
                    k=j;
                }
            }
            if(k!=i){
                e=edge[i];
                edge[i]=edge[k];
                edge[k]=e;
            }
        }
        for (i=0;i<vexnum;i++){
            vexset[i]=i;
        }

        for(i=0;i<arcnum;i++){
            v1=edge[i].head;
            v2=edge[i].tail;
            vs1=vexset[v1];
            vs2=vexset[v2];
            if(vs1!=vs2){
                drawTree.min_sides.add(new DrawTree.Side(drawTree.points.get(edge[i].head),drawTree.points.get(edge[i].tail),edge[i].lowcost));
                num+=edge[i].lowcost;
                for(j=0;j<vexnum;j++){
                    if(vexset[j]==vs2){
                        vexset[j]=vs1;
                    }
                }
            }
        }
        return num;
    }

    class Edge{
        public int head;
        public int tail;
        public int lowcost;
        public Edge(int head,int tail,int lowcost){
            this.head=head;
            this.tail=tail;
            this.lowcost=lowcost;
        }
    }
}

