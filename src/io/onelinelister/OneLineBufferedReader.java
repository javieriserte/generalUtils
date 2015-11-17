package io.onelinelister;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.Reader;
import java.nio.CharBuffer;

public class OneLineBufferedReader<T> extends BufferedReader {

  //////////////////////////////////////////////////////////////////////////////
  // Instance Variables
  private final LineParser<T> parser;
  //////////////////////////////////////////////////////////////////////////////

  //////////////////////////////////////////////////////////////////////////////
  // Constructor
  public OneLineBufferedReader(Reader in, LineParser<T> parser) {
    super(in);
    this.parser = parser;
  }
  //////////////////////////////////////////////////////////////////////////////

  //////////////////////////////////////////////////////////////////////////////
  // Public interface
  public LineParser<T> getParser() {
    return parser;
  }
  
  public T readObjectLine() throws IOException {
    String nextLine = this.readLine();
    if (nextLine!=null) {
      return this.parser.parse(nextLine);
    } else {
      return null;
    }
  }
  
  public String readLine() {
    throw new UnsupportedOperationException();
  }
  public void skip() {
    throw new UnsupportedOperationException();
  }
  public int read() {
    throw new UnsupportedOperationException();
  }
  public int read( char[] cbuff) {
    throw new UnsupportedOperationException();
  }
  public int read(CharBuffer target) {
    throw new UnsupportedOperationException();
  }
  public int read(char[] cbuf, int off, int len) {
    throw new UnsupportedOperationException();
  }
  public void reset() {
    throw new UnsupportedOperationException();
  }
  public void mark (int readAheadLimit) {
    throw new UnsupportedOperationException();
  }
  public boolean markSupported() {
    return false;
  }
  
  
  //////////////////////////////////////////////////////////////////////////////
  
}
