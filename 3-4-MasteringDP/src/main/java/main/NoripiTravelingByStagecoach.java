package main;

public class NoripiTravelingByStagecoach implements TravelingByStagecoachInterface {

  @Override
  public double solve(int n, int m, int a, int b, int[] t, int[][] d) {
    double[][] dpTable = new double[(int)Math.pow(2, n)][m];
    
    for (int i = 0; i < dpTable.length; i++) {
      for (int j = 0; j < dpTable[i].length; j++) {
        dpTable[i][j] = Math.floor(Double.MAX_VALUE / 2.0); 
      }
    }
    
    // 1個も使われてない状態の場合は、スタート地点のみコスト0、他はMAX
    dpTable[0][a-1] = 0;
    
    for (int usedTicketFlag = 1; usedTicketFlag < dpTable.length; usedTicketFlag++) {
      String usedTicketFlags = String.format("%0" + n + "d", Integer.parseInt(Integer.toBinaryString(usedTicketFlag)));
      for (int flagIdx = 0; flagIdx < usedTicketFlags.length(); flagIdx++) {
        if (usedTicketFlags.charAt(flagIdx) == '1') {
          // チケットの頭数
          int horseNum = t[flagIdx];
          
          // そのチケットが使われている場合、使わなかった場合から漸化式を作る
          byte[] usedTicketFlagsByte = usedTicketFlags.getBytes();
          usedTicketFlagsByte[flagIdx] = '0';
          String prevTicketFlags = new String(usedTicketFlagsByte);
          
          int prevFlagIdx = Integer.parseInt(prevTicketFlags, 2);
          
          // 時間最短を求める
          for (int currentCityIdx = 0; currentCityIdx < dpTable[usedTicketFlag].length; currentCityIdx++) {
            for (int prevCityIdx = 0; prevCityIdx < dpTable[usedTicketFlag].length; prevCityIdx++) {
              // 道がなければ何もしない
              if (d[prevCityIdx][currentCityIdx] == 0) continue;
              
              double time = d[prevCityIdx][currentCityIdx] / (horseNum * 1.0);
              dpTable[usedTicketFlag][currentCityIdx] = Math.min(dpTable[prevFlagIdx][prevCityIdx] + time, 
                  dpTable[usedTicketFlag][currentCityIdx]);
            }
          }
        }
      }
    }
    this.printTable(dpTable, n);
     
    // check min
    double result = Double.MAX_VALUE / 2.0;
    for (int i = 0; i < dpTable.length; i++) {
      result = Math.min(result, dpTable[i][b-1]);
    }
    
    return result;
  }
  
  private void printTable(double[][] dpTable, int count) {
    System.out.println("***********************************");
    for (int i=0; i<dpTable.length; i++) {
      System.out.format("%0"+count+"d: ", Integer.parseInt(Integer.toBinaryString(i)));
      for (int j=0; j<dpTable[i].length; j++) {
        System.out.print(dpTable[i][j] >= Double.MAX_VALUE / 2.0 ? 
            String.format("%7s", "-    ") : 
            String.format("%7.3f", dpTable[i][j]));
        System.out.print(" ");
      }
      System.out.println();
    }
    System.out.println("***********************************");
  }

}
