import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Arrays;
import java.util.Scanner;

public class SegmentMin{
    static File input;
    static Scanner scan;
    static PrintWriter out;
    int seg[];
    SegmentMin(int n){
        seg=new int[4*n];
    }
    void build(int ind,int low,int high,int arr[]){
        if(high==low){
            seg[ind]=arr[low];
            return;
        }
        int mid=low+(high-low)/2;
        build(2*ind+1,low,mid,arr);
        build(2*ind+2,mid+1,high,arr);
        seg[ind]=Math.min(seg[2*ind+1],seg[2*ind+2]);
    }
    int query(int ind,int low,int high,int l,int r){
        if(low>r||l>high)return Integer.MAX_VALUE;
        if(l<=low&&high<=r)return seg[ind];
        int mid=low+(high-low)/2;
        int left=query(2*ind+1,low,mid,l,r);
        int right=query(2*ind+2,mid+1,high,l,r);
        return Math.min(left,right);
    }
    void update(int ind,int low,int high,int i,int val){
        if(low==high){
            seg[ind]=val;
            return;
        }
        int mid=low+(high-low)/2;
        if(i<=mid)update(2*ind+1,low,mid,i,val);
        else update(2*ind+2,mid+1,high,i,val);
        seg[ind]=Math.min(seg[2*ind+1],seg[2*ind+2]);
    }
    static void solve1(){
        int n=scan.nextInt();
        SegmentMin obj=new SegmentMin(n);
        int arr[]=new int[n];
        for(int i=0;i<n;i++){
            arr[i]=scan.nextInt();
        }
        obj.build(0, 0, n-1, arr);
        int q=scan.nextInt();
        while(q-- >0){
            int type=scan.nextInt();
            if(type==1){
                int l=scan.nextInt(),r=scan.nextInt();
                out.println(obj.query(0, 0, n-1, l, r));
            }
            else {
                int i=scan.nextInt();
                int val=scan.nextInt();
                obj.update(0, 0, n-1, i, val);
            }
        }
    }
    static void solve2(){
        int n1=scan.nextInt();
        int arr1[]=new int[n1];
        for(int i=0;i<n1;i++){
            arr1[i]=scan.nextInt();
        }
        SegmentMin obj1 = new SegmentMin(n1);
        obj1.build(0 , 0, n1-1, arr1);


        int n2=scan.nextInt();
        int arr2[]=new int[n2];
        for(int i=0;i<n2;i++){
            arr2[i]=scan.nextInt();
        }
        SegmentMin obj2=new SegmentMin(n2);
        obj2.build(0,0,n2-1,arr2);

        int q=scan.nextInt();
        while(q-- >0){
            int type=scan.nextInt();
            if(type==1){
                int l1=scan.nextInt(), r1=scan.nextInt();
                int l2=scan.nextInt(), r2=scan.nextInt();
                int min1=obj1.query(0, 0, n1-1, l1, r1);
                int min2=obj2.query(0,0,n2-1,l2,r2);
                out.println(Math.min(min1,min2));
            }
            else{
                int arrNo=scan.nextInt();
                int i=scan.nextInt();
                int val=scan.nextInt();
                if(arrNo==1){
                    obj1.update(0, 0, n1-1, i, val);
                }else{
                    obj2.update(0, 0, n2-1, i, val);
                }
            }
        }

    }
    public static void main(String[] args)throws IOException {
        //int arr[]={2,1,0,4,3,7};
        //int n=arr.length;
        input = new File("SegmentMinInput.txt");
        scan= new Scanner(input);
        out = new PrintWriter("SegmentMinOutput.txt");
        solve2();
        out.close();
    }
}