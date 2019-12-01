package myView;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.text.TextPaint;
import android.util.AttributeSet;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;

import java.util.ArrayList;
import androidx.annotation.Nullable;

public class DrawTree extends View {

    private static int radius = 120;
    private int weight = 1;
    private int my_flag = 0;
    private boolean can_touch;
    private float preX;                  //起始点的x坐标
    private float preY;                  //起始点的y坐标
    private Paint paint = null;          //画笔
    private TextPaint textPaint = null;
    private Paint sidePaint = null;
    public ArrayList<Point> points;
    public ArrayList<Side> sides;
    public ArrayList<Side> min_sides;
    private int min_size;
    private int select=0;



    public DrawTree(Context context) {
        super(context);
        paint = new Paint(Paint.ANTI_ALIAS_FLAG);
        paint.setStrokeWidth(120);                         //设置默认比触的宽度为1像素
        paint.setAntiAlias(true);                        //使用抗锯齿功能
        paint.setStrokeCap(Paint.Cap.ROUND);
        paint.setColor(Color.GREEN);
        textPaint = new TextPaint();
        textPaint.setTextSize(80);
        textPaint.setColor(Color.BLACK);
        sidePaint = new Paint();
        sidePaint.setStrokeWidth(10);
        sidePaint.setStrokeCap(Paint.Cap.ROUND);
        sidePaint.setColor(Color.RED);
        sidePaint.setAntiAlias(true);
        sidePaint.setTextAlign(Paint.Align.CENTER);
        points = new ArrayList<Point>();
        sides = new ArrayList<Side>();
        min_sides=new ArrayList<Side>();
        can_touch=true;
        min_size=0;

    }

    public DrawTree(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        paint = new Paint(Paint.ANTI_ALIAS_FLAG);
        paint.setStrokeWidth(120);                         //设置默认比触的宽度为1像素
        paint.setAntiAlias(true);                        //使用抗锯齿功能
        paint.setStrokeCap(Paint.Cap.ROUND);
        paint.setColor(Color.GREEN);
        textPaint = new TextPaint();
        textPaint.setTextSize(80);
        textPaint.setColor(Color.BLACK);
        sidePaint = new Paint();
        sidePaint.setStrokeWidth(10);
        sidePaint.setStrokeCap(Paint.Cap.ROUND);
        sidePaint.setAntiAlias(true);
        sidePaint.setColor(Color.RED);
        points = new ArrayList<Point>();
        sides = new ArrayList<Side>();
        min_sides=new ArrayList<Side>();
        can_touch=true;
        min_size=0;

    }


    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        Side side;
        textPaint.setTextSize(40);
        sidePaint.setColor(Color.RED);
        for(int i=0;i<sides.size();i++){
            side = sides.get(i);
            canvas.drawLine(side.p1.x,side.p1.y,side.p2.x,side.p2.y,sidePaint);
        }
        sidePaint.setColor(Color.BLACK);
        for(int i=0;i<min_size;i++){
            side=min_sides.get(i);
            canvas.drawLine(side.p1.x,side.p1.y,side.p2.x,side.p2.y,sidePaint);
        }
        textPaint.setTextSize(80);
        String degree="";
        for(int i=0;i<points.size();i++){
            degree=(char)(i+'A')+"";
            canvas.drawPoint(points.get(i).x,points.get(i).y,paint);
            canvas.drawText(degree,points.get(i).x-textPaint.measureText(degree)/2,points.get(i).y+textPaint.measureText(degree)/2,textPaint);
        }
        textPaint.setTextSize(40);
        for(int i=0;i<sides.size();i++){
            side = sides.get(i);
            canvas.drawText(side.weigth+"",(side.p1.x+side.p2.x)/2-20,(side.p1.y+side.p2.y)/2+textPaint.measureText(side.weigth+"")/2,textPaint);
        }
    }


    @Override
    public boolean onTouchEvent(MotionEvent event) {
        if (!can_touch){
            return false;
        }
        float x = event.getX();
        float y = event.getY();
        float dx;
        float dy;
        Log.d("DrawTree", "onTouchEvent: "+"X:"+x+" Y:"+y);
        switch (event.getAction()) {
            case MotionEvent.ACTION_DOWN:
                //path.moveTo(x,y);       //将绘图的起始点一道（x，y）坐标的位置
                preX = x;
                preY = y;
                //canvas.drawPoint(preX , preY, paint);
                //points.add(new Point(x,y));
                break;
            case MotionEvent.ACTION_MOVE:



                break;
            case MotionEvent.ACTION_UP:
                dx = Math.abs(x - preX);
                dy = Math.abs(y - preY);
                //判断是否在允许的范围内
                if(dx>=5||dy>=5) {
                    addSide(new Point(preX,preY),new Point(x,y));
                }else {
                    addPoint(preX,preY);
                }
                break;
        }

        invalidate();
        //返回true表明处理方法已经处理该事务
        return true;
    }
    public static class Point{
        float x;
        float y;
        public Point(float x,float y){
            this.x=x;
            this.y=y;
        }

        public Point(String s){
            Log.d("Fragment_2", s);
            int i;
            char [] chs=s.toCharArray();
            for (i=1;i<s.length();i++){
                if(chs[i] == ' '){
                    break;
                }
            }
            this.x=Float.parseFloat(s.substring(0,i));
            this.y=Float.parseFloat(s.substring(i+1));
        }



        public boolean isOverlapping(Point point){
            if( radius*radius + 4 >= (this.x-point.x)*(this.x-point.x)+(this.y-point.y)*(this.y-point.y)){
                return true;
            }else {
                return false;
            }
        }

        public boolean isOverlapping(float x,float y){
            if( radius*radius + 4 >= (this.x - x)*(this.x - x)+(this.y - y)*(this.y - y)){
                return true;
            }else {
                return false;
            }
        }

        public String toString(){
            return x+" "+y+"\n";
        }

        public boolean equals( Point obj) {
            return (this.x==obj.x&&this.y==obj.y);
        }
    }

    public static class Side{
        public Point p1;
        public Point p2;
        public int weigth;

        public Side(Point a,Point b){
            this.p1=a;
            this.p2=b;
            this.weigth=1;
        }

        public Side(Point a,Point b,int weigth){
            this.p1=a;
            this.p2=b;
            this.weigth=weigth;
        }

        public Side(String s){
            Log.d("Fragment_2", s);
            char [] chars=s.toCharArray();
            int i,k,j;
            k=0;
            j=0;
            for(i=1;i<s.charAt(i);i++){
                if(chars[i]==' '){
                    k++;
                    if(k==2){
                        this.p1=new Point(s.substring(j,i));
                        j=i;
                    }else if(k==4){
                        this.p2=new Point(s.substring(j+1,i));
                        this.weigth=Integer.parseInt(s.substring(i+1));
                        break;
                    }
                }
            }
        }

        public boolean equals(Side obj) {
            return (this.p1==obj.p1&&this.p2==obj.p2)||(this.p1==obj.p2&&this.p2==obj.p1);
        }


        public String toString() {
            return p1.x+" "+p1.y+" "+p2.x+" "+p2.y+" "+weigth+"\n";
        }

    }

    public void setRadius(int radius) {
        this.radius = radius;
    }

    public int getRadius() {
        return radius;
    }

    public int getWeight() {
        return weight;
    }

    public void setWeight(int weight) {
        this.weight = weight;
    }

    private void addPoint(float x, float y){
        boolean flag = true;
        if(points.size()>=26)
            return;
        for(int i=0;i<points.size();i++){
            if(points.get(i).isOverlapping(x,y)){
                flag = false;
                break;
            }
        }
        if(flag){
            points.add(new Point(x,y));
            my_flag=1;
        }
    }

    private void addSide(Point a,Point b){
        int index_1 = -1;
        int index_2 = -1;
        for(int i=0;i<points.size();i++){
            if(points.get(i).isOverlapping(a)){
                index_1 = i;
            }
            if(points.get(i).isOverlapping(b)){
                index_2 = i;
            }
        }
        if(index_1!=-1 && index_2!=-1){
            if(points.get(index_1)==points.get(index_2)){
                return;
            }
            Side side = new Side(points.get(index_1),points.get(index_2),weight);
            index_1 = -1;
            for(int i=0;i<sides.size();i++){
                if (sides.get(i).equals(side)){
                    index_1=i;
                }
            }
            if(index_1 == -1){
                sides.add(side);
                my_flag = 2;
            }
        }
    }

    public void cancel(){
        if(my_flag==1){
            points.remove(points.size()-1);
            my_flag = 0;
            invalidate();
        }else if(my_flag==2){
            sides.remove(sides.size()-1);
            my_flag=0;
            invalidate();
        }
    }

    public void setMin_size(int min_size) {
        if(min_size>min_sides.size()){
            return;
        }
        this.min_size = min_size;
        invalidate();
    }

    public int getMin_size() {
        return min_size;
    }

    public boolean isCan_touch() {
        return can_touch;
    }

    public void setCan_touch(boolean can_touch) {
        this.can_touch = can_touch;
    }

    public int getSelect() {
        return select;
    }

    public void setSelect(int select) {
        this.select = select;
    }

    public int pointAt(Point v){
        int i;
        for(i=0;i<points.size();i++){
            if(points.get(i).equals(v)){
                return i;
            }
        }
        return -1;
    }


}
