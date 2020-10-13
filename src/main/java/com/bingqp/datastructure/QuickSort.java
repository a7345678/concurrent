package com.bingqp.datastructure;

public class QuickSort {

  public static void sort(int arr[], int left, int right) {
    int l = left;
    int r = right;
    int povit = arr[left];
    while (l < r) {
      //顺序很重要，要先从右往左找
      while (l < r && arr[r] >= povit) {
        r--;
      }
      //再从左往右找
      while (l < r && arr[l] <= povit) {
        l++;
      }
      //交换两个数在数组中的位置
      if(l<r)//当哨兵i和哨兵j没有相遇时
      {
        int t=arr[l];
        arr[l]=arr[r];
        arr[r]=t;
      }
    }
    //最终将基准数归位
    arr[left]= arr[l];
    arr[l] = povit;
    print(arr);
    System.out.print("l=" + (l + 1) + " h=" + (r + 1) + " povit=" + povit + "\n");
    if (l - 1 > left)
      sort(arr, left, l - 1); //继续处理左边的，这里是一个递归的过程
    if (r + 1 < right)
      sort(arr, r + 1, right); //继续处理右边的，这里是一个递归的过程
  }

  static void print(int[] arr) {
    for (int i = 0; i < arr.length; i++) {
      System.out.print(arr[i] + " -> ");
    }
    System.out.println();
  }

  public static void main(String[] args) {
    int left = 0;
    int right = 9;
    int[] arr = {6, 1, 2, 7, 9, 3, 4, 5, 8, 10};
    QuickSort.sort(arr, left, right);
  }
}
