package pairedtotable;

import io.onelinelister.OneLineListReader;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintStream;
import java.util.List;

import cmdGA2.CommandLine;
import cmdGA2.NoArgumentOption;
import cmdGA2.OptionsFactory;
import cmdGA2.SingleArgumentOption;

public class PairedToTableCli {

  public static void main(String[] args) {
    ////////////////////////////////////////////////////////////////////////////
    // Create command line
    CommandLine cmd = new CommandLine();
    ////////////////////////////////////////////////////////////////////////////

    ////////////////////////////////////////////////////////////////////////////
    // Add options to the command line
    SingleArgumentOption<InputStream> inOpt = 
        OptionsFactory.createBasicInputStreamArgument(cmd);
    SingleArgumentOption<PrintStream> outOpt =
        OptionsFactory.createBasicPrintStreamArgument(cmd);
    NoArgumentOption firstIsColumnOpt = new NoArgumentOption(cmd, "--firstIsColumn");
    ////////////////////////////////////////////////////////////////////////////
    
    ////////////////////////////////////////////////////////////////////////////
    // Parse command line
    cmd.readAndExitOnError(args);
    ////////////////////////////////////////////////////////////////////////////
    
    ////////////////////////////////////////////////////////////////////////////
    // Get values from command line
    BufferedReader in = new BufferedReader(
        new InputStreamReader(inOpt.getValue()));
    PrintStream out = outOpt.getValue();
    boolean firstIsColumn = firstIsColumnOpt.isPresent();
    ////////////////////////////////////////////////////////////////////////////
    
    ////////////////////////////////////////////////////////////////////////////
    // Get input text
    OneLineListReader<List<String>> reader = new OneLineListReader<>(
        new TripletLineParser());
    try {
       List<List<String>> triplets = reader.read(in);
       in.close();
       /////////////////////////////////////////////////////////////////////////
       // Convert To Text 
       PairedToTable converter = new PairedToTable();
       String outText = converter.convertToTextTable(triplets, firstIsColumn);
       /////////////////////////////////////////////////////////////////////////
       
       /////////////////////////////////////////////////////////////////////////
       // Export to output 
       out.print(outText);
       out.close();
       /////////////////////////////////////////////////////////////////////////
    } catch (IOException e) {
      e.printStackTrace();
    }
    ////////////////////////////////////////////////////////////////////////////
 catch (Exception e) {
      e.printStackTrace();
    }
  }
}
