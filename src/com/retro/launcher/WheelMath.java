package com.retro.launcher;
public final class WheelMath {
 private WheelMath(){}
 public static int wrap(int n,int size){return size<=0?0:((n%size)+size)%size;}
}
