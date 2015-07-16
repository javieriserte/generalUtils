package math.confusionmatrix;

import java.util.Set;

public class ConfusionMatrixFactory {
  //////////////////////////////////////////////////////////////////////////////
  // Public interface
  public static ConfusionMatrix createMatrix(Set<? extends Object> positives, 
      Set<? extends Object> predictedPositives, 
      Set<? extends Object> predictedNegatives) {
    int truePositives = ConfusionMatrixFactory.truePosivites(positives,
        predictedPositives);
    int falsePositives = predictedPositives.size() - truePositives;
    int falseNegatives = positives.size() - truePositives;
    int trueNegatives = predictedNegatives.size() - falsePositives;
    return new ConfusionMatrix(truePositives, trueNegatives, falsePositives,
        falseNegatives);
  }
  //////////////////////////////////////////////////////////////////////////////

  //////////////////////////////////////////////////////////////////////////////
  // Class Methods
  private static int truePosivites(Set<? extends Object> positives,
      Set<? extends Object> predictedPositives) {
    int counter = 0;
    for (Object element : predictedPositives) {
      counter += positives.contains(element)?1:0;
    }
    return counter;
  }
  //////////////////////////////////////////////////////////////////////////////
}
