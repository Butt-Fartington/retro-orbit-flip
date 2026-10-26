import com.retro.launcher.WheelMath;
public class WheelMathTest {
 public static void main(String[] args){
 for(int size=0;size<100;size++)for(int index=-1000;index<=1000;index++){
 int actual=WheelMath.wrap(index,size);
 if(size==0){if(actual!=0)throw new AssertionError("empty");continue;}
 if(actual<0||actual>=size)throw new AssertionError("bounds");
 if(WheelMath.wrap(actual+1,size)!=WheelMath.wrap(index+1,size))throw new AssertionError("forward");
 if(WheelMath.wrap(actual-1,size)!=WheelMath.wrap(index-1,size))throw new AssertionError("reverse");
 }
 System.out.println("200100 carousel cases passed");
 }
}
