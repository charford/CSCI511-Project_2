/**
 *  class for ParseException
 *  provides detailed exceptions for SiteDeveloper
 *  extends Exception
*/
class ParseException extends Exception {
  /**
   *  constructor for ParseException
  */
  String err;
  public ParseException() {
   //default constructor goes here, initializes mistake to unknown
   super();
   err = "undefined";
  }

  public ParseException(String parseError) {
    super(parseError);
    err = parseError;
  }

  public String getException() {
    System.out.println("getException");
    return err;
  }
};
