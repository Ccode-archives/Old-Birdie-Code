package team.anthembolts.utils;
public class boltsarray{
    private double [] in_vals;
    public boltsarray()
    {
        this(100);
    }
    public boltsarray(int size){
        in_vals = new double[size];
    }
    public int size(){
        return in_vals.length;
    }
    public double latest(){
        return in_vals[0];
    }
    /*public void push(double d){
        double buck1=in_vals[0];
        double buck2=0;
        for (int i=1;i<in_vals.length;i++){
            buck2 = in_vals[i];
            in_vals[i]=buck1;
            buck1=buck2;
        }
    }
    */
    public void push(double d)
    {
        

        for(int i = in_vals.length -1;i > 0;i--)
        {
         in_vals[i] = in_vals[i-1];
        }
        in_vals[0] = d;
        //System.out.println("inVals: " + in_vals[0] + " " +  in_vals[24] + " " + in_vals[49] + " " + in_vals[99] );
    }
    public double pop(){
        double buck1=in_vals[in_vals.length-1];
        double buck2=0;
        double ret = in_vals[0];
        for (int i=in_vals.length-2;i>-1;i--){
            buck2 = in_vals[i];
            in_vals[i]=buck1;
            buck1=buck2;
        }
        return ret;
    }
    public double avg(){
        double avg = 0;
        for (int i=0;i<in_vals.length;i++){
            avg+=in_vals[i];
        }
        //System.out.println(avg + " length: " + in_vals.length);
        return avg/(double)in_vals.length;
    }
}