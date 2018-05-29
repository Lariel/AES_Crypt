/*
 
   Routines to convert 
      - a byte array to a hex string
      - a hex string to an array of bytes
      - a string to hex
      - a hex string 
   Author: Avelino Zorzo
   Date  : 13.05.2013
*/

public class HexToString {
 
    
    // ==========================================================================
    // Auxiliary functions - conversion from byte arrays to string and vice-versa
    // ==========================================================================
    
    public String byteArrayToHexString(byte[] b)
    {
        StringBuffer sb = new StringBuffer(b.length * 2);
        for (int i = 0; i < b.length; i++) {
            int v = b[i] & 0xff;
            if (v < 16) {
                sb.append('0');
            }
            sb.append(Integer.toHexString(v));
        }
        return sb.toString().toUpperCase();
    }
    
    public static byte[] hexStringToByteArray(String s)
    {
        int len = s.length();
        byte[] data = new byte[len / 2];
        for (int i = 0; i < len; i += 2)
        {
            data[i / 2] = (byte) ((Character.digit(s.charAt(i), 16) << 4) + Character.digit(s.charAt(i+1), 16));
        }
        return data;
    }

   public String stringToHexString(String str){
 
	  char[] chars = str.toCharArray();
 
	  StringBuffer hex = new StringBuffer();
	  for(int i = 0; i < chars.length; i++){
	    hex.append(Integer.toHexString((int)chars[i]));
	  }
 
	  return hex.toString();
  }
 
   public String hexStringToString(String hex){
 
	  StringBuilder sb = new StringBuilder();
	  StringBuilder temp = new StringBuilder();
 
	  for( int i=0; i<hex.length()-1; i+=2 ){
 
	      String output = hex.substring(i, (i + 2));
	      int decimal = Integer.parseInt(output, 16);
	      sb.append((char)decimal);
 	      temp.append(decimal);
	  }
 
	  return sb.toString();
  }
 
  /* Main program - receives a hex string and converts it to ASCII 
     and to an array of bytes, then back to a string */
  public static void main(String[] args) {
      HexToString hs = new HexToString();
      
      if (args.length < 2) {
          String hex = args[0];
  
          System.out.println("\n***** Convert Hex to ASCII *****");
          System.out.println("ASCII : " + hs.hexStringToString(hex));
          System.out.println("Hex : " + hs.stringToHexString(hs.hexStringToString(hex)));

          byte[] b = hs.hexStringToByteArray(hex);
      
          System.out.println("Bytes:");
          for (int i=0; i<b.length; i++) System.out.format("%02x ",b[i]);
          System.out.println();
          System.out.println("String again: "+ hs.byteArrayToHexString(b));
      }
      else {
          String s = args[0];

          System.out.println("\n***** Convert ASCII to Hex *****");
          System.out.println("ASCII repetida: " + s);
          System.out.println("Hex : " + hs.stringToHexString(s));
          
      }
  }
}