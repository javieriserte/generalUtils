package math.confusionmatrix;

import io.onelinelister.OneLineListReader;
import io.onelinelister.LineParser;
import io.onelinelister.lineparsers.StringLineParser;

import java.io.File;
import java.io.PrintStream;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import cmdGA2.CommandLine;
import cmdGA2.OptionsFactory;
import cmdGA2.SingleArgumentOption;
import cmdGA2.returnvalues.InfileValue;
import cmdGA2.returnvalues.IntegerValue;

public class ConfusionMatrixCli {

  public static void main(String[] args) {
    ////////////////////////////////////////////////////////////////////////////
    // Command line
    CommandLine cmd = new CommandLine();
    // Add Command line arguments
    SingleArgumentOption<File> mpbcAPOpt = new SingleArgumentOption<File>(cmd,
        "--mpbcActPos", new InfileValue(), null);
    SingleArgumentOption<Integer> mpbcTotalSamplesOpt = new 
        SingleArgumentOption<Integer>(cmd, "--mpbcPos", new IntegerValue(), 0);
    SingleArgumentOption<File> mpbcPPOpt = new SingleArgumentOption<File>(cmd,
        "--mpbcPredPos", new InfileValue(), null);
    SingleArgumentOption<PrintStream> outPut = OptionsFactory.
        createBasicPrintStreamArgument(cmd);
    // Parse command line
    cmd.readAndExitOnError(args);
    // Get command line values
    File mpbcActualPosFile = mpbcAPOpt.getValue();
    File mpbcPredictedPosFile = mpbcPPOpt.getValue();
    int mpbcTotalSamples = mpbcTotalSamplesOpt.getValue();
    PrintStream out = outPut.getValue();
    ////////////////////////////////////////////////////////////////////////////
    
    ////////////////////////////////////////////////////////////////////////////
    // Get positive set
    Set<String> actPosSet = new HashSet<>();
    actPosSet.addAll((new OneLineListReader<String>(new StringLineParser()))
        .read(mpbcActualPosFile));
    Set<String> predPosSet = new HashSet<>();
    predPosSet.addAll((new OneLineListReader<String>(
        new mpbcResultLineParser())).read(mpbcPredictedPosFile));
    Set<String> predNegSet = new HashSet<>();
    predNegSet.addAll(createPredNegList(predPosSet,mpbcTotalSamples));
    ////////////////////////////////////////////////////////////////////////////
    
    ////////////////////////////////////////////////////////////////////////////
    // Create the confusion matrix
    ConfusionMatrix matrix = ConfusionMatrixFactory.createMatrix(actPosSet, predPosSet,
        predNegSet);
    ////////////////////////////////////////////////////////////////////////////
    
    ////////////////////////////////////////////////////////////////////////////
    // Print output 
    out.println(matrix.accuracy());
    ////////////////////////////////////////////////////////////////////////////
    
  }
  private static List<String> createPredNegList(Set<String> predPosList,
      int mpbcTotalSamples) {
    List<String> predNeg = new ArrayList<>();
    for (int i=1; i<=mpbcTotalSamples; i++) {
      String sample = "ACN|" + String.format("%03d", i);
      if (!predPosList.contains(sample)) {
        predNeg.add(sample);
      }
    }
    return predNeg;
  }
  private static class mpbcResultLineParser implements LineParser<String> {
    @Override
    public String parse(String line) {
      String[] fields = line.split(" ");
      for (String field:fields) {
        field = field.trim();
        if (field.toUpperCase().startsWith("ACN")) {
          return field;
        }
      }
      return "";
    }
  }
}
