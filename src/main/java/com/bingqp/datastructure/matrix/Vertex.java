package com.bingqp.datastructure.matrix;

/**
 * 邻接矩阵表示图
 *
 * @author Ranter
 */
public class Vertex {
  private char lable;    //矩阵元素
  private int[][] list = new int[20][20];
  private char[] vertexList = new char[20];
  private int nVerts;    //当前顶点下标

  Vertex() {
    this.nVerts = 0;
  }

  //增加一个顶点
  public void addVertex(char lable) {
    vertexList[nVerts++] = lable;
  }

  //增加一条边
  public void addEdge(int start, int end) {
    list[start][end] = 1;
    list[end][start] = 1;
  }

  //打印矩阵
  public void printMatrix() {
    for (int i = 0; i < nVerts; i++) {
      for (int j = 0; j < nVerts; j++) {
        System.out.print(list[i][j]);
      }
      System.out.println();
    }
  }

  public static void main(String[] args) {
    Vertex v = new Vertex();
    v.addVertex('a');    //0
    v.addVertex('b');    //1
    v.addVertex('c');    //2
    v.addVertex('d');    //3
    v.addVertex('e');    //4
    v.addEdge(0, 2);    //a-c
    v.addEdge(1, 4);    //b-e
    v.addEdge(2, 4);    //b-e
    v.addEdge(0, 4);    //a-e
    v.printMatrix();
  }

}
