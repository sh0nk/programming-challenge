package main;

/**
 * クレーンがあります。クレーンは、N個の線分が端点で接続されたものと考えます。i番目の線分の長さは Liです。はじめはすべての線分がまっすぐ接続され、上を向いています。 <br>
 * <br>
 * C個のクレーンを動かす命令がきます。各命令iは2つの整数、Si, Aiで与えられます。これは、線分Siと線分Si+1の間の角度をAi度にするという意味です。なお、角度は線分Si
 * から反時計回りに線分 Si+1を見た際の角度で、最初の角度は180度です。<br>
 * <br>
 * C個の命令は順次実行していきます。各命令に対し、それが終わったときのクレーンの先端(N 番目の線分の端点)の座標を出力してください。座標はクレーンの支点を(0, 0)とします。<br>
 * <br>
 * 制約
 * <li>1≦N,C≦10000
 * <li>1≦Li≦100
 * <li>1≦Si<N, 0≦Ai≦359
 *
 */
public interface CraneInterface {
  public double[][] solve(int N, int C, int[] L, int[] S, int[] A);
}
